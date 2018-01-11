package lapr.project.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import lapr.project.model.Junction;
import lapr.project.model.Section;
import lapr.project.model.Segment;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author pc asus
 */
public class SectionData extends DataAccess<Section> {

    /**
     *
     * @param connection
     */
    public SectionData(Connection connection) {
        super(connection);
    }

    /**
     *
     * @param projectName
     * @return
     * @throws SQLException
     */
    public List<Section> get(String projectName) throws SQLException {
        if (connection == null) {
            return new ArrayList<>();
        }
        List<Section> list = new LinkedList<>();

        List<SQLArgument> args = new ArrayList<>();
        args.add(new SQLArgument(projectName, OracleTypes.VARCHAR));
        try (ResultSet rs = super.callFunction("getSections", args)) {
            while (rs.next()) {
                int id = rs.getInt("ID");
                int sectionID = rs.getInt("ID_SECTION");
                int begginingJunction = rs.getInt("ID_BEGGININGJ");
                int endingJunction = rs.getInt("ID_ENDINGJ");
                int directionID = rs.getInt("ID_DIRECTION");
                String roadID = rs.getString("ID_ROAD");
                String typology = rs.getString("TYPOLOGY");

                JunctionData j = new JunctionData(connection);
                Junction beginJunction = j.get(Integer.toString(begginingJunction));
                Junction endJunction = j.get(Integer.toString(endingJunction));

                DirectionData d = new DirectionData(connection);
                Section.Direction direction = d.get(directionID);

                Section s = new Section();
                s.setSectionID(sectionID);
                s.setBeginJunction(beginJunction);
                s.setEndJunction(endJunction);
                s.setDirection(direction);
                s.setRoadID(roadID);
                s.setTypology(typology);
                TollData td = new TollData(connection);
                Map<Integer, Double> map = td.getSectionToll(id);
                for (Map.Entry<Integer, Double> entry : map.entrySet()) {
                    s.addToll(entry.getKey(), entry.getValue());
                }
                list.add(s);

            }
        }
        return list;
    }

    /**
     *
     * @param pName
     * @param s
     * @throws SQLException
     */
    public void insert(String pName, Section s) throws SQLException {
        List<SQLArgument> args1 = new ArrayList<>();

        args1.add(new SQLArgument(pName, OracleTypes.VARCHAR));
        args1.add(new SQLArgument(String.valueOf(s.getSectionID()), OracleTypes.NUMBER));
        ResultSet rs = super.callFunction("getSectionByID", args1);
        if (rs.next()) {
            rs.close();
            return;
        }
        args1.add(new SQLArgument(s.getBeginningJunction().getName(), OracleTypes.VARCHAR));
        args1.add(new SQLArgument(s.getEndingJunction().getName(), OracleTypes.VARCHAR));
        args1.add(new SQLArgument(s.getTypology(), OracleTypes.VARCHAR));
        args1.add(new SQLArgument(s.getRoadID(), OracleTypes.VARCHAR));
        args1.add(new SQLArgument(s.getDirection().toString(), OracleTypes.VARCHAR));
        super.callProcedure("insertSection", args1);

        SegmentData sd = new SegmentData(connection);
        for (Segment seg : s.getSequenceOfSegments()) {
            sd.insert(pName, s.getSectionID(), seg);
        }

        TollData td = new TollData(connection);
        for (int i : s.getToll().keySet()) {
            td.insert(pName, i, s.getSectionID(), s.getToll().get(i));
        }

    }

}
