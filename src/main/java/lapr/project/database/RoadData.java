package lapr.project.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import lapr.project.model.Road;
import oracle.jdbc.OracleTypes;

public class RoadData extends DataAccess<Road> {

    public RoadData(Connection connection) {
        super(connection);
    }

    public List<Road> get(String projectName) throws SQLException {
        if (connection == null) {
            return new ArrayList<>();
        }
        List<Road> list = new LinkedList<>();
        List<SQLArgument> args = new ArrayList<>();
        args.add(new SQLArgument(projectName, OracleTypes.VARCHAR));
        ResultSet rs = super.callFunction("getRoad", args);
        while (rs.next()) {
            String roadID = String.valueOf(rs.getInt("id_road"));
            String roadName = rs.getString("name");
            String roadType = rs.getString("Tipology");
            list.add(new Road(roadID, roadName, roadType));
        }
        rs.close();
        return list;
    }

    public void insert(String pName, Road r) throws SQLException {
        List<SQLArgument> args1 = new ArrayList<>();
        
        args1.add(new SQLArgument(r.getRoadID(),OracleTypes.VARCHAR)); // id road na bd e id, auto inc e cenas
        ResultSet rs = super.callFunction("getRoadByID",args1);
        if(rs.next()) {
            rs.close();
            return;
        }
        args1.add(new SQLArgument(pName,OracleTypes.VARCHAR));
        args1.add(new SQLArgument(r.getName(),OracleTypes.VARCHAR));
        args1.add(new SQLArgument(r.getTypology(),OracleTypes.VARCHAR));
        
        super.callProcedure("insertRoad", args1);
    }
}
