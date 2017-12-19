package lapr.project.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
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
            return Collections.EMPTY_LIST;
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
}
