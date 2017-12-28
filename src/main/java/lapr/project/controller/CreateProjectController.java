package lapr.project.controller;

import java.io.File;
import java.sql.SQLException;
import java.util.List;
import lapr.project.model.Junction;
import lapr.project.model.Project;
import lapr.project.model.Road;
import lapr.project.model.Section;
import lapr.project.model.Vehicle;
import lapr.project.utils.DatabaseConnection;
import lapr.project.utils.SQLConnection;
import lapr.project.utils.Session;
import lapr.project.utils.graphbase.Graph;

public class CreateProjectController {

    private Graph<Junction, Section> m_road_network;
    private List<Vehicle> m_list_vehicles;
    private List<Road> m_list_roads;

    public Graph<Junction, Section> getRoadNetwork() {
        return m_road_network;
    }

    public List<Vehicle> getVehicleList() {
        return m_list_vehicles;
    }


    public List<Road> getRoadList() {
        return m_list_roads;
    }
    
    private boolean exists(String title) throws SQLException {
        DatabaseConnection db = new DatabaseConnection();
        SQLConnection sql = db.getDatabase();
        return sql.getProjectByName(title) != null;
    }

    public boolean createProject(String title, String description) throws SQLException {
        if(title.isEmpty() || description.isEmpty() || exists(title)) {
            return false;
        }
        Project p = new Project(m_road_network, m_list_vehicles, m_list_roads);
        p.setName(title);
        p.setDescription(description);
        DatabaseConnection db = new DatabaseConnection();
        SQLConnection sql = db.getDatabase();
        sql.insertProject(p);
        Session.setActiveProject(p);
        return true;
    }

    public void addVehicle(Vehicle v) {
        m_list_vehicles.add(v);
    }

}
