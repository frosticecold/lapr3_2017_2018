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
    public Project(Graph roadNetwork, List<Vehicle> vehiclesList) {
        m_road_network = roadNetwork;
        m_list_vehicles = vehiclesList;
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
