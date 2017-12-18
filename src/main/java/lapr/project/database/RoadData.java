package lapr.project.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lapr.project.model.Road;
import oracle.jdbc.OracleTypes;

public class RoadData extends DataAccess<Road> {

    public RoadData(Connection connection) {
        super(connection);
    }

    public Road get(String roadID) throws SQLException {
        if (connection == null) {
            return null;
        }
        List<SQLArgument> args = new ArrayList<>();
        args.add(new SQLArgument(roadID, OracleTypes.VARCHAR));
        ResultSet rs = super.callFunction("getRoad", args);
        while (rs.next()) {
            String roadName = rs.getString("name");
            String roadType = rs.getString("Tipology");
            rs.close();
            return new Road(roadID, roadName, roadType);
        }
        return null;
    }

}
