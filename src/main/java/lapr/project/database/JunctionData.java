package lapr.project.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lapr.project.model.Junction;
import oracle.jdbc.OracleTypes;

public class JunctionData extends DataAccess<Junction>{
    
    public JunctionData(Connection connection) {
        super(connection);
    }
    
    public Junction get(String junctionId) throws SQLException {
        if(connection == null) {
            return null;
        }
        List<SQLArgument> args = new ArrayList<>();
        args.add(new SQLArgument(junctionId, OracleTypes.NUMBER));
        ResultSet rs = super.callFunction("getJunction", args);
        
        if(rs.next()) {
            String junctionID = rs.getString("id_Junction");
            return new Junction(junctionID);
        }
        return null;
    }
    
    

}
