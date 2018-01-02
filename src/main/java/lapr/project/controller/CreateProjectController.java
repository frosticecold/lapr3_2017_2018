package lapr.project.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lapr.project.model.Junction;
import lapr.project.model.Project;
import lapr.project.model.Road;
import lapr.project.model.Section;
import lapr.project.model.Vehicle;
import lapr.project.utils.DatabaseConnection;
import lapr.project.utils.ImportException;
import lapr.project.utils.NetworkXML;
import lapr.project.utils.Pair;
import lapr.project.utils.SQLConnection;
import lapr.project.utils.Session;
import lapr.project.utils.VehicleXML;
import lapr.project.utils.graphbase.Graph;

public class CreateProjectController {

    private Graph<Junction, Section> roadNetwork;
    private List<Vehicle> listVehicles;
    private List<Road> listRoads;

    public CreateProjectController() {
        roadNetwork = new Graph<>(true);
        listVehicles = new ArrayList<>();
        listRoads = new ArrayList<>();

    }

    public Graph<Junction, Section> getRoadNetwork() {
        return roadNetwork;
    }

    public List<Vehicle> getVehicleList() {
        return listVehicles;
    }

    public List<Road> getRoadList() {
        return listRoads;
    }

    private static boolean exists(String title) throws SQLException {
        DatabaseConnection db = new DatabaseConnection();
        SQLConnection sql = db.getDatabase();
        List<String> projects = sql.getProjects();
        return projects.contains(title);
    }

    public boolean createProject(String title, String description) throws SQLException {
        if (title.isEmpty() || description.isEmpty() || exists(title)) {
            return false;
        }
        Project p = new Project(roadNetwork, listVehicles, listRoads);
        p.setName(title);
        p.setDescription(description);
        DatabaseConnection db = new DatabaseConnection();
        SQLConnection sql = db.getDatabase();
        sql.insertProject(p);
        Session.setActiveProject(p);
        return true;
    }

    public void importVehicle(File file) throws FileNotFoundException, ImportException {

        VehicleXML vxml = new VehicleXML();
        List<Vehicle> importVehicles = vxml.importVehicles(file);

        if (!importVehicles.isEmpty()) {
            for (Vehicle v : importVehicles) {
                listVehicles.add(v);
            }
        } else {
            throw new ImportException();
        }

    }

    public void importNetwork(File file) throws FileNotFoundException, ImportException {

        NetworkXML xml = new NetworkXML();
        Pair<Graph<Junction, Section>, List<Road>> pair = xml.importNetwork(file);

        roadNetwork = pair.getFirstElement();
        listRoads = pair.getSecondElement();

    }
}
