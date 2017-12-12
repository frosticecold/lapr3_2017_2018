package lapr.project.model;

import lapr.project.model.roadnetwork.RoadNetwork;
import lapr.project.model.vehicle.Vehicle;
import java.util.List;

public class Project {

    private RoadNetwork m_road_network;
    private List<Vehicle> m_list_vehicles;

    /**
     *
     * @param r
     * @param l
     */
    public Project(RoadNetwork r, List<Vehicle> l) {
        m_road_network = r;
        m_list_vehicles = l;
    }

}
