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
        String arg = "";
        
        boolean first = true;
        for (SQLArgument s : args) {
            if (!first) {
                arg += ",'";
            } else {
                arg += "'";
            }
            first = false;
            String value = s.getType() == OracleTypes.VARCHAR ? s.getValue() : s.getValue().replace('.', ',');
            value = s.getType() == OracleTypes.VARCHAR ? value.replace("'", "''") : value;
            arg += value + "'";
        }

        String sql = "{? = call %s(" + arg + ")}";
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

}
