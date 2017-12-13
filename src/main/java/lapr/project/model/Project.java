package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import lapr.project.utils.graphbase.Graph;

public class Project {

    private Graph<Junction, Section> m_road_network;
    private List<Vehicle> m_list_vehicles;
    private List<Road> m_list_roads;

    /**
     *
     * @param roadNetwork
     * @param vehiclesList
     */
    public Project(Graph<Junction, Section> roadNetwork, List<Vehicle> vehiclesList, List<Road> roadList) {
        m_road_network = roadNetwork;
        m_list_vehicles = vehiclesList;
        m_list_roads = roadList;
    }

    public Project() {
        m_road_network = new Graph<>(true);
        m_list_vehicles = new ArrayList<>();
        m_list_roads = new ArrayList<>();
    }

    public void setRoadNetwork(Graph<Junction, Section> m_road_network) {
        this.m_road_network = m_road_network;
    }

    public void setListVehicles(List<Vehicle> m_list_vehicles) {
        this.m_list_vehicles = m_list_vehicles;
    }

    public Graph<Junction, Section> getRoadNetwork() {
        return m_road_network;
    }

    public List<Vehicle> getListVehicles() {
        return m_list_vehicles;
    }

    /**
     *
     * @param roadNetwork
     */
    public void addRoad(Junction junction) {
        m_road_network.insertVertex(junction);
    }

}
