package lapr.project.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import lapr.project.model.Junction;
import lapr.project.model.Section;
import oracle.jdbc.OracleTypes;

public class SectionData extends DataAccess<Section> {

    public SectionData(Connection connection) {
        super(connection);
    }

    public List<Section> get(String projectName) throws SQLException {
        if (connection == null) {
            return new ArrayList<>();
        }
        List<Section> list = new LinkedList<>();

        List<SQLArgument> args = new ArrayList<>();
        args.add(new SQLArgument(projectName, OracleTypes.VARCHAR));
        ResultSet rs = super.callFunction("getSections", args);
        while (rs.next()) {
            int begginingJunction = rs.getInt("id_BegginingJ");
            int endingJunction = rs.getInt("id_EndingJ");
            int directionID = rs.getInt("id_Direction");
            int roadID = rs.getInt("id_Road");
            
            JunctionData j = new JunctionData(connection);
            Junction beginJunction = j.get(Integer.toString(begginingJunction));
            Junction endJunction = j.get(Integer.toString(endingJunction));
            
            DirectionData d = new DirectionData(connection);
            Section.Direction direction = d.get(directionID);
            
          
            Section s = new Section();
            s.setBeginJunction(beginJunction);
            s.setEndJunction(endJunction);
            s.setDirection(direction);
            s.setRoadID(String.valueOf(roadID));
            list.add(s);

        }
        rs.close();

        return list;
    }

}
