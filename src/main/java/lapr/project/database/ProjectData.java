package lapr.project.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lapr.project.model.Junction;
import lapr.project.model.ListOfResults;
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

    /**
     * Get all project names in the database
     *
     * @return List of project names
     * @throws SQLException
     */
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

    /**
     * Get the project with the specified name
     *
     * @param name Project name
     * @return Project
     * @throws SQLException
     */
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

            JunctionData j = new JunctionData(connection);
            List<Junction> junctions = j.getAllJunctions(name);

            for (Junction junction : junctions) {
                if (junction.validate()) {
                    g.insertVertex(junction);
                }
            }
            
            for (Section section : sections) {
                Junction j1 = section.getBeginningJunction();
                Junction j2 = section.getEndingJunction();

                SegmentData sd = new SegmentData(connection);
                List<Segment> segments = sd.get(name, String.valueOf(section.getSectionID()));
                section.setSegmentList(segments);
                double distance = section.getSectionLength();

                if (section.getDirection().compareTo(Direction.REVERSE) == 0) {
                    g.insertEdge(j2, j1, section, distance);
                } else {
                    g.insertEdge(j1, j2, section, distance);
                }
            }
            p.setRoadNetwork(g);

            RoadData r = new RoadData(connection);
            List<Road> roads = r.get(name);

            p.setListRoads(roads);

            VehicleData v = new VehicleData(connection);
            VehicleList vehicles = v.get(name);

            p.setListVehicles(vehicles);

            ResultsData rd = new ResultsData(connection);
            ListOfResults listOfResults = rd.get(p, name);

            p.setListOfResults(listOfResults);

        }
        return p;
    }

    /**
     * Insert the specified project in the database
     *
     * @param p Project
     * @throws SQLException
     */
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

    /**
     * Edit a project
     *
     * @param project Project to edit
     * @param name New name
     * @param description New description
     * @throws SQLException
     */
    public void editProject(Project project, String name, String description) throws SQLException {
        if(connection == null) {
            return;
        }
        
        List<SQLArgument> args = new ArrayList<>();
        args.add(new SQLArgument(project.getName(), OracleTypes.VARCHAR));
        args.add(new SQLArgument(name, OracleTypes.VARCHAR));
        args.add(new SQLArgument(description, OracleTypes.VARCHAR));
        super.callProcedure("editProject", args);
    }

}
