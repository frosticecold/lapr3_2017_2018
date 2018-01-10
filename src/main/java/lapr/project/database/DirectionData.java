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

    /**
     * Get the directions present in the database.
     * 
     * @param directionID Direction ID
     * @return Direction
     * @throws SQLException
     */
    public Section.Direction get(int directionID) throws SQLException {
        if (connection == null) {
            return null;
        }
        List<SQLArgument> args = new ArrayList<>();
        args.add(new SQLArgument(Integer.toString(directionID), OracleTypes.NUMBER));
        try (ResultSet rs = super.callFunction("getDirection", args)) {
            if (rs.next()) {
                return Section.Direction.valueOf(rs.getString("description"));
            }
        }
        return null;
    }

    /**
     * Inserts directions in the database
     * 
     * @param name Direction name
     * @throws SQLException
     */
    public void insert(String name) throws SQLException {
        List<SQLArgument> args = new ArrayList<>();

        args.add(new SQLArgument(name, OracleTypes.VARCHAR));
        ResultSet rs = super.callFunction("getDirectionByName", args);
        if (rs.next()) {
            rs.close();
            return;
        }
        super.callProcedure("insertDirection", args);
    }

}
