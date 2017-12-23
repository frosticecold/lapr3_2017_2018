package lapr.project.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import lapr.project.networkanalysis.AlgorithmResults;
import lapr.project.utils.graphbase.Edge;
import lapr.project.utils.graphbase.Graph;
import lapr.project.utils.graphbase.Vertex;

public class Project {

    private Graph<Junction, Section> m_road_network;
    private VehicleList m_list_vehicles;
    private List<Road> m_list_roads;
    private String m_name;
    private String m_description;
    private Map<Vehicle, List<AlgorithmResults>> m_results;

    public Project() {
        m_name = "";
        m_description = "";
        m_road_network = new Graph<>(true);
        m_list_vehicles = new VehicleList();
        m_list_roads = new ArrayList<>();
        m_results = new LinkedHashMap<>();
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

    public Map<Vehicle, List<AlgorithmResults>> getResults() {
        return m_results;
    }

    public void setResults(Map<Vehicle, List<AlgorithmResults>> m_results) {
        this.m_results = m_results;
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

    public List<Road> getListRoads() {
        return m_list_roads;
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

    public Section getSection(Junction j1, Junction j2) {
        if (!m_road_network.validVertex(j1) || !m_road_network.validVertex(j2)) {
            throw new IllegalArgumentException("Invalid junction");
        }
        return m_road_network.getEdge(j1, j2).getElement();
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
            Section sec = s.reverseSegment();
            m_road_network.insertEdge(dest, orig, sec, s.getSectionLength());
            return true;
        }

        s.setKey(m_road_network.numEdges());

        return m_road_network.insertEdge(orig, dest, s, s.getSectionLength());

    }

//    public ArrayList<LinkedList<Section>> allPaths(Junction source, Junction target) {
//        if (!m_road_network.validVertex(source)) {
//            throw new IllegalArgumentException(("Source junction is invalid"));
//        }
//        if (!m_road_network.validVertex(target)) {
//            throw new IllegalArgumentException(("Target junction is invalid"));
//        }
//
//        Vertex<Junction, Section> vSource = m_road_network.getVertex(source);
//        Vertex<Junction, Section> vTarget = m_road_network.getVertex(target);
//        ArrayList<LinkedList<Section>> paths = new ArrayList<>();
//        allPaths(vSource, vTarget, new boolean[m_road_network.numEdges()], new LinkedList<>(), paths);
//
//        return paths;
//
//    }
//
//    private void allPaths(Vertex<Junction, Section> vOrig, Vertex<Junction, Section> vDest, boolean[] visited,
//            LinkedList<Section> path, ArrayList<LinkedList<Section>> paths) {
//        for (Edge<Junction, Section> edge : vOrig.getAllOutEdges()) {
//            if (!visited[edge.getElement().getKey()] && verifySection(path, edge)) {
//                visited[edge.getElement().getKey()] = true;
//                path.add(edge.getElement());
//
//                if (edge.getVDest().equals(vDest)) {
//                    paths.add(new LinkedList<>(path));
//                    path.removeLast();
//                } else {
//                    allPaths(edge.getVDestVertex(), vDest, visited, path, paths);
//                }
//            }
//
//            if (visited[edge.getElement().getKey()] && !edge.getVDest().equals(vDest)) {
//                path.removeLast();
//            }
//            visited[edge.getElement().getKey()] = false;
//        }
//    }

//    public boolean verifySection(LinkedList<Section> path, Edge<Junction, Section> edge) {
//        for (Section section : path) {
//            if (edge.getVDest().equals(getCorrespondentEdge(section).getVOrig())) {
//                return false;
//            }
//        }
//
//        return true;
//    }

//    public Edge<Junction, Section> getCorrespondentEdge(Section section) {
//        for (Edge<Junction, Section> edge : this.m_road_network.edges()) {
//            if (edge.getElement().equals(section)) {
//                return edge;
//            }
//        }
//
//        return null;
//    }

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

    @Override
    public String toString() {
        return "Project{" + "m_road_network=" + m_road_network + ", m_list_vehicles=" + m_list_vehicles + ", m_list_roads=" + m_list_roads + ", m_name=" + m_name + ", m_description=" + m_description + '}';
    }


}
