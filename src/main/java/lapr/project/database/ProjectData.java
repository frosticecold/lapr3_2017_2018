package lapr.project.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lapr.project.model.Junction;
import lapr.project.model.Project;
import lapr.project.model.Road;
import lapr.project.model.Section;
import lapr.project.model.Section.Direction;
import lapr.project.model.Segment;
import lapr.project.model.Vehicle;
import lapr.project.model.VehicleList;
import lapr.project.utils.graphbase.Edge;
import lapr.project.utils.graphbase.Graph;
import oracle.jdbc.OracleTypes;

public class ProjectData extends DataAccess<Project> {

    public ProjectData(Connection connection) {
        super(connection);
    }

    public List<String> getAllProjectsNames() throws SQLException {
        if (connection == null) {
            return new ArrayList<>();
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
                List<Segment> segments = sd.get(String.valueOf(section.getSectionID()));
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
        if (connection == null) {
            return;
        }

        List<SQLArgument> args1 = new ArrayList<>();
        args1.add(new SQLArgument(p.getName(), OracleTypes.VARCHAR));
        args1.add(new SQLArgument(p.getDescription(), OracleTypes.VARCHAR));

        super.callProcedure("insertProject", args1);

        String pName = p.getName();

        for (int i = 1; i <= 3; i++) {
            args1.clear();
            args1.add(new SQLArgument(Integer.toString(i), OracleTypes.NUMBER));
            super.callProcedure("insertTollClass", args1);
        }

        JunctionData jd = new JunctionData(connection);
        for (Junction j : p.getRoadNetwork().vertices()) {
            jd.insert(pName, j);
        }

        RoadData rd = new RoadData(connection);
        for (Road r : p.getListRoads()) {
            rd.insert(pName, r);
        }

        DirectionData dd = new DirectionData(connection);
        for (Direction d : Direction.values()) {
            dd.insert(d.name());
        }

        SectionData sd = new SectionData(connection);
        for (Edge<Junction, Section> edge : p.getRoadNetwork().edges()) {
            Section s = edge.getElement();
            sd.insert(pName, s);
        }

        VehicleData vd = new VehicleData(connection);
        for (Vehicle v : p.getListVehicles().getVehicleList()) {
            vd.insert(pName, v);
        }

    }

}
