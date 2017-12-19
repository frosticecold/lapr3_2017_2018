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
            return null;
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
            double wind_direction = rs.getFloat("wind_direction");
            double wind_speed = rs.getFloat("wind_speed");
            double maximum_velocity = rs.getFloat("max_v");
            double minimum_velocity = rs.getFloat("min_v");
            
            Segment s = new Segment(segmentID, initialHeight, finalHeight, length, wind_direction, wind_speed, maximum_velocity, minimum_velocity);
            
            list.add(s);
        }
        
        return list;
    }

    
    

}
