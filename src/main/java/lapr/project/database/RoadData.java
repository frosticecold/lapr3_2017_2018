package lapr.project.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import lapr.project.model.Road;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author pc asus
 */
public class RoadData extends DataAccess<Road> {

    /**
     *
     * @param connection
     */
    public RoadData(Connection connection) {
        super(connection);
    }

    /**
     *
     * @param projectName
     * @return
     * @throws SQLException
     */
    public List<Road> get(String projectName) throws SQLException {
        if (connection == null) {
            return new ArrayList<>();
        }
        List<Road> list = new LinkedList<>();
        List<SQLArgument> args = new ArrayList<>();
        args.add(new SQLArgument(projectName, OracleTypes.VARCHAR));
        try (ResultSet rs = super.callFunction("getRoad", args)) {
            while (rs.next()) {
                Road r = new Road();
                int rID = rs.getInt("id");
                String roadID = rs.getString("id_road");
                String roadName = rs.getString("name");
                String roadType = rs.getString("tipology");
                
                TollData td = new TollData(connection);
                Map<Integer, Double> map = td.getRoadToll(rID);
                
                r.setRoadID(roadID);
                r.setName(roadName);
                r.setTypology(roadType);
                r.setTollFare(map);
                list.add(r);
            }
        }
        return list;
    }

    /**
     *
     * @param pName
     * @param r
     * @throws SQLException
     */
    public void insert(String pName, Road r) throws SQLException {
        List<SQLArgument> args1 = new ArrayList<>();

        args1.add(new SQLArgument(pName, OracleTypes.VARCHAR));
        args1.add(new SQLArgument(r.getRoadID(), OracleTypes.VARCHAR));
        ResultSet rs = super.callFunction("getRoadByID", args1);
        if (rs.next()) {
            rs.close();
            return;
        }
        args1.clear();
        args1.add(new SQLArgument(r.getRoadID(), OracleTypes.VARCHAR));
        args1.add(new SQLArgument(pName, OracleTypes.VARCHAR));
        args1.add(new SQLArgument(r.getName(), OracleTypes.VARCHAR));
        args1.add(new SQLArgument(r.getTypology(), OracleTypes.VARCHAR));

        super.callProcedure("insertRoad", args1);

        for (int i : r.getTollFare().keySet()) {
            args1.clear();
            args1.add(new SQLArgument(pName, OracleTypes.VARCHAR));
            args1.add(new SQLArgument(r.getRoadID(), OracleTypes.VARCHAR));
            args1.add(new SQLArgument(Integer.toString(i), OracleTypes.NUMBER));
            args1.add(new SQLArgument(String.valueOf(r.getTollValue(i)), OracleTypes.NUMBER));
            super.callProcedure("insertRoadToll", args1);
        }
    }
}
