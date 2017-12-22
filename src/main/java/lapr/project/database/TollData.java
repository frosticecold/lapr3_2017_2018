package lapr.project.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleTypes;

public class TollData extends DataAccess<Double> {

    public TollData(Connection connection) {
        super(connection);
    }
    
    public void insert(int key, double price) throws SQLException {
        List<SQLArgument> args1 = new ArrayList<>();
        
        args1.add(new SQLArgument(Integer.toString(key),OracleTypes.NUMBER));
        ResultSet rs = super.callFunction("getTollByID",args1);
        if(rs.next()) {
            rs.close();
            return;
        }
        args1.add(new SQLArgument(Double.toString(price), OracleTypes.NUMBER));
        super.callProcedure("insertToll", args1);
    }

}
