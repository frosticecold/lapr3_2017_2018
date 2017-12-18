package lapr.project.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lapr.project.model.Section;
import oracle.jdbc.OracleTypes;

public class DirectionData extends DataAccess<Section.Direction> {

    public DirectionData(Connection connection) {
        super(connection);
    }

    public Section.Direction get(int directionID) throws SQLException {
        List<SQLArgument> args = new ArrayList<>();
        args.add(new SQLArgument(Integer.toString(directionID), OracleTypes.NUMBER));
        ResultSet rs = super.callFunction("getDirection", args);

        if (rs.next()) {
            String direction = rs.getString("description");
            Section.Direction de = Section.Direction.valueOf(direction);
            return de;
        }
        rs.close();
        return null;
    }

}
