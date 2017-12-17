package lapr.project.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import lapr.project.utils.graphbase.Edge;
import lapr.project.utils.graphbase.Graph;
import lapr.project.utils.graphbase.GraphAlgorithms;

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
        return GraphAlgorithms.allPaths(m_road_network, source, target);
    }

}
