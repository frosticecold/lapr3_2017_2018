package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import lapr.project.utils.graphbase.Graph;

public class Project {

    private Graph<Junction, Section> m_road_network;
    private List<Vehicle> m_list_vehicles;
    private List<Road> m_list_roads;
    private String name;
    private String description;

    public Project() {
        name = "";
        description = "";
        m_road_network = new Graph<>(true);
        m_list_vehicles = new ArrayList<>();
        m_list_roads = new ArrayList<>();
    }

    /**
     *
     * @param roadNetwork
     * @param vehiclesList
     * @param roadList
     */
    public Project(Graph<Junction, Section> roadNetwork, List<Vehicle> vehiclesList, List<Road> roadList) {
        m_road_network = roadNetwork;
        m_list_vehicles = vehiclesList;
        m_list_roads = roadList;
    }

    public Project(Project p) {
        name = p.name;
        description = p.description;
        m_road_network = p.getRoadNetwork().copyGraph();
        m_list_vehicles = new ArrayList<>();
        for (Vehicle v : p.getListVehicles()) {
            m_list_vehicles.add(new Vehicle(v));
        }
        m_list_roads = new ArrayList<>();
        for (Road r : p.m_list_roads) {
            m_list_roads.add(new Road(r));
        }

    }

    public void setRoadNetwork(Graph<Junction, Section> m_road_network) {
        this.m_road_network = m_road_network;
    }

    public void setListVehicles(List<Vehicle> m_list_vehicles) {
        this.m_list_vehicles = m_list_vehicles;
    }

    public void setListRoads(List<Road> listOfRoads) {
        this.m_list_roads = listOfRoads;
    }

    public Graph<Junction, Section> getRoadNetwork() {
        return m_road_network;
    }

    public List<Vehicle> getListVehicles() {
        return m_list_vehicles;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    

    /**
     *
     * @param junction
     */
    public void addRoad(Junction junction) {
        m_road_network.insertVertex(junction);
    }

}
