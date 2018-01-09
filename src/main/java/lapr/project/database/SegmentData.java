package lapr.project.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import lapr.project.model.Segment;
import oracle.jdbc.OracleTypes;

public class SegmentData extends DataAccess<Segment> {

    public SegmentData(Connection connection) {
        super(connection);
    }

    public List<Segment> get(String sectionID) throws SQLException {
        if (connection == null) {
            return new ArrayList<>();
        }
        List<Segment> list = new LinkedList<>();
        List<SQLArgument> args = new ArrayList<>();
        args.add(new SQLArgument(sectionID, OracleTypes.VARCHAR));
        ResultSet rs = super.callFunction("getSegments", args);
        while (rs.next()) {
            int segmentID = rs.getInt("id_Segment");
            double initialHeight = rs.getDouble("initial_height");
            double finalHeight = rs.getDouble("final_height");
            double length = rs.getDouble("length");
            double windDirection = rs.getDouble("wind_direction");
            double windSpeed = rs.getDouble("wind");
            double maximumVelocity = rs.getDouble("maximum_v");
            double minimumVelocity = rs.getDouble("minimum_v");
            
            Segment s = new Segment(segmentID, initialHeight, finalHeight, length, windDirection, windSpeed, maximumVelocity, minimumVelocity);

            list.add(s);
        }
        return list;
    }

    public void insert(int sectionID, Segment s) throws SQLException {
        List<SQLArgument> args1 = new ArrayList<>();

        args1.add(new SQLArgument(Integer.toString(s.getSegmentIndex()), OracleTypes.VARCHAR));
        args1.add(new SQLArgument(Integer.toString(sectionID), OracleTypes.VARCHAR));
        ResultSet rs = super.callFunction("getSegmentByIndex", args1);
        if (rs.next()) {
            rs.close();
            return;
        }
        args1.add(new SQLArgument(Double.toString(s.getInitialHeight()), OracleTypes.NUMBER));
        args1.add(new SQLArgument(Double.toString(s.getFinalHeight()), OracleTypes.NUMBER));
        args1.add(new SQLArgument(Double.toString(s.getLength()), OracleTypes.NUMBER));
        args1.add(new SQLArgument(Double.toString(s.getWindSpeed()), OracleTypes.NUMBER));
        args1.add(new SQLArgument(Double.toString(s.getWindDirection()), OracleTypes.NUMBER));
        args1.add(new SQLArgument(Double.toString(s.getMaximumVelocity()), OracleTypes.NUMBER));
        args1.add(new SQLArgument(Double.toString(s.getMinimumVelocity()), OracleTypes.NUMBER));
        
        super.callProcedure("insertSegment", args1);
    }

}
