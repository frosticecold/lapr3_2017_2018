package lapr.project.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.database.JunctionData;
import lapr.project.database.ProjectData;
import lapr.project.database.RoadData;
import lapr.project.database.SectionData;
import lapr.project.database.VehicleData;
import lapr.project.model.Junction;
import lapr.project.model.Project;
import lapr.project.model.Road;
import lapr.project.model.Section;
import lapr.project.model.Vehicle;
import lapr.project.model.VehicleList;
import lapr.project.utils.ImportException;
import lapr.project.utils.NetworkXML;
import lapr.project.utils.Pair;
import lapr.project.utils.SQLConnection;
import lapr.project.utils.Session;
import lapr.project.utils.VehicleXML;
import lapr.project.utils.graphbase.Edge;
import lapr.project.utils.graphbase.Graph;

public class EditProjectController {

    private Project project;
    private VehicleList totalVehicleList;
    private VehicleList newVehicleList;
    private Graph<Junction, Section> newRoadNetwork;
    private List<Road> newRoadList;
    private List<Junction> newJunctionList;
    private List<Section> newSectionList;
    private SQLConnection connection;

    public EditProjectController() {
        project = Session.getActiveProject();
        totalVehicleList = project.copyVehicleList();
        newVehicleList = new VehicleList();
        newJunctionList = new ArrayList<>();
        newSectionList = new ArrayList<>();
        connection = Session.getConnection();
    }

    public void editNewProject(String name, String description) throws SQLException {
        ProjectData pd = new ProjectData(connection.getConnection());
        pd.editProject(project, name, description);
        project.setName(name);
        project.setDescription(description);
    }

    public void editNewListVehicles() throws SQLException {
        addVehicles();
    }

    public void editNewListRoadNetwork() throws SQLException {
        addRoadNetwork();
    }

    private void addVehicles() throws SQLException {
        VehicleData vd = new VehicleData(connection.getConnection());
        for (Vehicle vehicle : totalVehicleList.getVehicleList()) {
            if (!(project.getListVehicles().getVehicleList().contains(vehicle))) {
                vd.insert(project.getName(), vehicle);
                newVehicleList.getVehicleList().add(vehicle);
            }
        }
        project.setListVehicles(totalVehicleList);
    }

    public String getActiveProjectDescription() {
        return project.getDescription();
    }

    public String getActiveProjectName() {
        return project.getName();
    }

    public Project getActiveProject() {
        return project;
    }

    public void addVehicles(File file) throws FileNotFoundException, ImportException {
        VehicleXML vxml = new VehicleXML();
        List<Vehicle> importVehicles = vxml.importVehicles(file);
        if (!importVehicles.isEmpty()) {
            for (Vehicle v : importVehicles) {
                if (!totalVehicleList.getVehicleList().contains(v)) {
                    totalVehicleList.addVehicle(v);
                }
            }
        } else {
            throw new ImportException();
        }
    }

    public void addRoadNetwork(File file) throws FileNotFoundException, ImportException {
        try {
            NetworkXML xml = new NetworkXML();
            Pair<Graph<Junction, Section>, List<Road>> pair;
            pair = xml.importNetwork(file);
            newRoadNetwork = pair.getFirstElement();
            newRoadList = pair.getSecondElement();
        } catch (ImportException ex) {
            Logger.getLogger(EditProjectController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addRoadNetwork() throws SQLException {
        List<Road> roadList = project.getListRoads();
        RoadData rd = new RoadData(connection.getConnection());
        for (Road road : newRoadList) {
            if (!roadList.contains(road)) {
                rd.insert(project.getName(), road);
                roadList.add(road);
            }
        }
        JunctionData jd = new JunctionData(connection.getConnection());
        Graph<Junction, Section> graph = project.getRoadNetwork().copyGraph();
        for (Junction junction : newRoadNetwork.vertices()) {
            if (!graph.validVertex(junction)) {
                jd.insert(project.getName(), junction);
                newJunctionList.add(junction);
            }
            graph.insertVertex(junction);
        }

        SectionData sd = new SectionData(connection.getConnection());
        for (Edge<Junction, Section> edge : newRoadNetwork.edges()) {
            if (graph.getEdge(edge.getVOrig(), edge.getVDest()) == null) {
                sd.insert(project.getName(), edge.getElement());
                newSectionList.add(edge.getElement());
            }
            graph.insertEdge(edge.getVOrig(), edge.getVDest(), edge.getElement(), edge.getWeight());
        }
        project.setRoadNetwork(graph);
    }

    public VehicleList getTotalVehicleList() {
        return totalVehicleList;
    }

    public Graph<Junction, Section> getNewRoadNetwork() {
        return newRoadNetwork;
    }

    public List<Road> getNewRoadList() {
        return newRoadList;
    }

    public boolean updateProject(boolean nameChanges, String nameProject, String descriptionProject, boolean vehicleUpdate, boolean roadUpdate) throws SQLException {
        if (nameChanges) {
            editNewProject(nameProject, descriptionProject);
        }
        if (vehicleUpdate) {
            editNewListVehicles();
        }
        if (roadUpdate) {
            editNewListRoadNetwork();
        }
        return roadUpdate || vehicleUpdate || nameChanges;
    }

    public boolean updateProjectFields(String nameProject, String descriptionProject) {
        return !(nameProject.equals(getActiveProjectName()) && descriptionProject.equals(getActiveProjectDescription()));
    }

    public boolean updateProjectFiles(String filename) {
        return "Imported".equalsIgnoreCase(filename);
    }
}
