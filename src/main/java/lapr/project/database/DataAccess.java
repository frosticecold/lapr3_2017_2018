package lapr.project.database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import oracle.jdbc.OracleTypes;

/**
 *
 *
 * @param <T> Type of data
 */
public abstract class DataAccess<T> {

    /**
     * Connection to the database
     */
    protected Connection connection;

    /**
     * Creates a connection
     *
     * @param connection SQL Connection (Must be open)
     */
    protected DataAccess(Connection connection) {
        this.connection = connection;
    }

    /**
     * Executes a stored SQL function.
     *
     * @param function Name of the function.
     * @return ResultSet
     * @throws SQLException
     */
    protected CachedRowSet callFunction(String function) throws SQLException {
        return this.callFunction(function, new ArrayList<>());
    }

    /**
     * Executes a SQL function
     *
     * @param function Name of the function.
     * @param args Arguments of the function
     * @return ResultSet
     * @throws SQLException
     */
    protected CachedRowSet callFunction(String function, Iterable<SQLArgument> args) throws SQLException {
        StringBuilder arg = new StringBuilder();

        boolean first = true;
        for (SQLArgument s : args) {
            if (!first) {
                arg.append(",'");
            } else {
                arg.append("'");
            }
            first = false;
            String value = s.getType() == OracleTypes.VARCHAR ? s.getValue() : s.getValue().replace('.', ',');
            value = s.getType() == OracleTypes.VARCHAR ? value.replace("'", "''") : value;
            arg.append(value).append("'");
        }

        String sql = new StringBuilder().append("{? = call %s(").append(arg).append(")}").toString();
        CachedRowSet c;
        try (CallableStatement cstmt = connection.prepareCall(String.format(sql, function))) {
            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
            cstmt.execute();
            ResultSet rs = (ResultSet) cstmt.getObject(1);
            c = RowSetProvider.newFactory().createCachedRowSet();
            c.populate(rs);
        }

        return c;
    }

    /**
     * Executes a stores SQL Procedures
     *
     * @param procedure Procedure name
     * @param args Procedures arguments
     * @throws SQLException
     */
    protected void callProcedure(String procedure, Iterable<SQLArgument> args) throws SQLException {
        StringBuilder arg = new StringBuilder();

        boolean first = true;
        for (SQLArgument s : args) {
            if (!first) {
                arg.append(",'");
            } else {
                arg.append("'");
            }
            first = false;
            String value;
            if (s.getType() == OracleTypes.VARCHAR) {
                value = s.getValue();
            } else {
                value = s.getValue().replace('.', ',');
            }
            if (s.getType() == OracleTypes.VARCHAR) {
                value = value.replace("'", "''");
            }
            arg.append(value).append("'");
        }

        String sql = String.format("{call %s(%s)}", procedure, arg);
        try (CallableStatement cstmt = connection.prepareCall(sql)) {
            cstmt.execute();
        }
    }

}
