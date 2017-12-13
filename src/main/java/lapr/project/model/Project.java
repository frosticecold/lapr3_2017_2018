package lapr.project.model;

import java.util.List;
import lapr.project.utils.graphbase.Graph;

public class Project {

    private Graph<Junction, Road> m_road_network;
    private List<Vehicle> m_list_vehicles;

    /**
     *
     * @param roadNetwork
     * @param vehiclesList
     */
    public Project(Graph<Junction, Road> roadNetwork, List<Vehicle> vehiclesList) {
        m_road_network = roadNetwork;
        m_list_vehicles = vehiclesList;
    }

    public void setM_road_network(Graph<Junction, Road> m_road_network) {
        this.m_road_network = m_road_network;
    }

    public void setM_list_vehicles(List<Vehicle> m_list_vehicles) {
        this.m_list_vehicles = m_list_vehicles;
    }
    
    public Graph<Junction, Road> getM_road_network() {
        return m_road_network;
    }

    public List<Vehicle> getM_list_vehicles() {
        return m_list_vehicles;
    }

    public Project() {
    }

    /**
     *
     * @param roadNetwork
     */
    public void addRoad(Junction junction) {
        m_road_network.insertVertex(junction);
    }

    /**
     *
     * @param junction
     */
    public void addJunction(Junction junction1, Junction junction2, Road road, Double weight) {
        if (m_road_network.getEdge(junction1, junction2) == null) {
            m_road_network.insertEdge(junction1, junction2, road, weight);
        }
    }

    
}
