package lapr.project.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import lapr.project.utils.graphbase.Edge;
import lapr.project.utils.graphbase.Graph;
import lapr.project.utils.graphbase.GraphAlgorithms;

public class Project {

    private Graph<Junction, Section> m_road_network;
    private VehicleList m_list_vehicles;
    private List<Road> m_list_roads;
    private String m_name;
    private String m_description;

    public Project() {
        m_name = "";
        m_description = "";
        m_road_network = new Graph<>(true);
        m_list_vehicles = new VehicleList();
        m_list_roads = new ArrayList<>();
    }

    public Project(String name, String description) {
        this.m_name = name;
        this.m_description = description;
    }

    /**
     *
     * @param roadNetwork
     * @param vehiclesList
     * @param roadList
     */
    public Project(Graph<Junction, Section> roadNetwork, List<Vehicle> vehiclesList, List<Road> roadList) {
        m_road_network = roadNetwork;
        m_list_vehicles = new VehicleList();
        m_list_roads = roadList;
    }

    public Project(Project p) {
        m_name = p.m_name;
        m_description = p.m_description;
        m_road_network = p.getRoadNetwork().copyGraph();
        m_list_vehicles = new VehicleList();
//        for (Vehicle v : p.getListVehicles()) {
//            m_list_vehicles.add(new Vehicle(v));
//        }
        m_list_roads = new ArrayList<>();
        for (Road r : p.m_list_roads) {
            m_list_roads.add(new Road(r));
        }

    }

    public void setRoadNetwork(Graph<Junction, Section> m_road_network) {
        this.m_road_network = m_road_network;
    }

    public void setListVehicles(VehicleList vehicle_list) {
        this.m_list_vehicles = vehicle_list;
    }

    public void setListRoads(List<Road> listOfRoads) {
        this.m_list_roads = listOfRoads;
    }

    public Graph<Junction, Section> getRoadNetwork() {
        return m_road_network;
    }

    public VehicleList getListVehicles() {
        return m_list_vehicles;
    }

    public String getName() {
        return m_name;
    }

    public String getDescription() {
        return m_description;
    }

    public void setName(String name) {
        this.m_name = name;
    }

    public void setDescription(String description) {
        this.m_description = description;
    }

    public Junction getJunction(String junction_id) {
        if (junction_id == null || junction_id.trim().isEmpty()) {
            throw new IllegalArgumentException("Junction name is invalid");
        }
        for (Junction j : m_road_network.vertices()) {
            if (j.getID().equalsIgnoreCase(junction_id)) {
                return j;
            }
        }
        return null;
    }

    public Road getRoadByRoadID(String road_id) {
        for (Road r : m_list_roads) {
            if (r.getRoadID().equals(road_id)) {
                return r;
            }
        }
        return null;
    }

    public boolean addRoad(Road r) {
        boolean added = false;
        if (!m_list_roads.contains(r)) {
            added = m_list_roads.add(r);
        }
        return added;
    }

    public boolean addVehicle(Vehicle v) {
        return m_list_vehicles.addVehicle(v);
    }

    public boolean addJunction(Junction j) {
        return m_road_network.insertVertex(j);
    }

    public boolean addSection(Section s) {
        Junction orig = s.getBeginningJunction();
        Junction dest = s.getEndingJunction();
        if (orig == null || dest == null) {
            return false;
        }
        if (!m_road_network.validVertex(orig) || !m_road_network.validVertex(dest)) {
            return false;
        }

        for (Edge<Junction, Section> edge : m_road_network.edges()) {
            if (edge.getElement().equals(s)) {
                return false;
            }
        }

        if (s.getDirection() == Section.Direction.REVERSE) {
            orig = s.getEndingJunction();
            dest = s.getBeginningJunction();
            return m_road_network.insertEdge(orig, dest, s, s.getSectionLength());
        }
        if (s.getDirection() == Section.Direction.BIDIRECTIONAL) {
            m_road_network.insertEdge(orig, dest, s, s.getSectionLength());
            m_road_network.insertEdge(dest, orig, s, s.getSectionLength());
            return true;
        }

        return m_road_network.insertEdge(orig, dest, s, s.getSectionLength());

    }

    public ArrayList<LinkedList<Junction>> allPaths(Junction source, Junction target) {
        if (!m_road_network.validVertex(source)) {
            throw new IllegalArgumentException(("Source junction is invalid"));
        }
        if (!m_road_network.validVertex(target)) {
            throw new IllegalArgumentException(("Target junction is invalid"));
        }
        return GraphAlgorithms.allPaths(m_road_network, source, target);
    }

    public boolean validate() {
        if (this.m_name == null || m_name.trim().isEmpty()) {
            throw new IllegalArgumentException("The project name is invalid.");
        }
        if (this.m_description == null || m_name.trim().isEmpty()) {
            throw new IllegalArgumentException(("The project description is invalid."));
        }
        if (m_road_network.numVertices() < 2 || m_road_network.numEdges() == 0) {
            throw new IllegalArgumentException("The project number of vertices are invalid.");
        }
        if (m_road_network.numEdges() == 0) {
            throw new IllegalArgumentException("There must be atleast one road");
        }

        return true;
    }
}
