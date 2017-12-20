package lapr.project.database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import oracle.jdbc.OracleTypes;

public abstract class DataAccess<T> {

    protected Connection connection;

    protected DataAccess(Connection connection) {
        this.connection = connection;
    }

    protected CachedRowSet callFunction(String function) throws SQLException {
        return this.callFunction(function, new ArrayList<>());
    }

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
