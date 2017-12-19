package lapr.project.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lapr.project.model.Junction;
import lapr.project.model.Project;
import lapr.project.model.Road;
import lapr.project.model.Section;
import lapr.project.model.Segment;
import lapr.project.model.VehicleList;
import lapr.project.utils.graphbase.Graph;
import oracle.jdbc.OracleTypes;

public class ProjectData extends DataAccess<Project> {

    public ProjectData(Connection connection) {
        super(connection);
    }

    public List<String> getAllProjectsNames() throws SQLException {
        if (connection == null) {
            return Collections.EMPTY_LIST;
        }

        List<String> list = new ArrayList<>();

        try (ResultSet rs = super.callFunction("getAllProjects")) {
            while (rs.next()) {
                String name = rs.getString("name");
                list.add(name);
            }
        }
        return list;
    }

    public Project get(String name) throws SQLException {
        if (connection == null) {
            return null;
        }

        List<SQLArgument> args = new ArrayList<>();
        args.add(new SQLArgument(name, OracleTypes.VARCHAR));
        ResultSet rs = super.callFunction("getProject", args);
        Project p = null;
        while (rs.next()) {
            String description = rs.getString("description");
            p = new Project();

            p.setName(name);
            p.setDescription(description);

            SectionData s = new SectionData(connection);
            List<Section> sections = s.get(name);

            Graph<Junction, Section> g = new Graph<>(true);
            for (Section section : sections) {
                Junction j1 = section.getBeginningJunction();
                Junction j2 = section.getEndingJunction();

                double distance = section.getSectionLength();
                SegmentData sd = new SegmentData(connection);
                List<Segment> segments = sd.get(String.valueOf(section.getID()));
                section.setSegmentList(segments);
                
                switch (section.getDirection()) {
                    case DIRECT:
                        g.insertEdge(j1, j2, section, distance);
                        break;
                    case REVERSE:
                        g.insertEdge(j2, j1, section, distance);
                        break;
                    case BIDIRECTIONAL:
                        g.insertEdge(j1, j2, section, distance);
                        g.insertEdge(j2, j1, section, distance);
                        break;
                }
            }
            p.setRoadNetwork(g);
            
            RoadData r = new RoadData(connection);
            List<Road> roads = r.get(name);
            
            p.setListRoads(roads);
            
            VehicleData v = new VehicleData(connection);
            VehicleList vehicles = v.get(name);
            
            p.setListVehicles(vehicles);

        }
        return p;
    }

    public void insertProject(Project p) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
