package lapr.project.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import lapr.project.model.Segment;
import oracle.jdbc.OracleTypes;

public class SegmentData extends DataAccess<Segment>{

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
            double initialHeight = rs.getFloat("initial_height");
            double finalHeight = rs.getFloat("final_height");
            double length = rs.getFloat("length");
            double windDirection = rs.getFloat("wind_direction");
            double windSpeed = rs.getFloat("wind_speed");
            double maximumVelocity = rs.getFloat("max_v");
            double minimumVelocity = rs.getFloat("min_v");
            
            Segment s = new Segment(segmentID, initialHeight, finalHeight, length, windDirection, windSpeed, maximumVelocity, minimumVelocity);
            
            list.add(s);
        }
        
        return list;
    }

    
    

}
