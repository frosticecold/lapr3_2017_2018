package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import lapr.project.utils.graphbase.Edge;
import lapr.project.utils.graphbase.Graph;

public class Project {

    private String name;
    private String description;
    private Graph<Junction, Section> roadNetwork;
    private List<Road> listRoads;
    private VehicleList listOfVehicles;
    private ListOfResults listOfResults;

    public Project() {
        name = "";
        description = "";
        roadNetwork = new Graph<>(true);
        listOfVehicles = new VehicleList();
        listRoads = new ArrayList<>();
        listOfResults = new ListOfResults();
    }

    public Project(String name, String description) {
        this.name = name;
        this.description = description;
        listOfVehicles = new VehicleList();
        listRoads = new ArrayList<>();
        listOfResults = new ListOfResults();
    }

    /**
     *
     * @param roadNetwork
     * @param vehiclesList
     * @param roadList
     */
    public Project(Graph<Junction, Section> roadNetwork, List<Vehicle> vehiclesList, List<Road> roadList) {
        this.roadNetwork = roadNetwork;
        listOfVehicles = new VehicleList();
        for (Vehicle v : vehiclesList) {
            listOfVehicles.addVehicle(v);
        }
        listRoads = roadList;
        listOfResults = new ListOfResults();
    }

    public Junction getJunction(String junctionID) {
        if (junctionID == null || junctionID.trim().isEmpty()) {
            throw new IllegalArgumentException("Junction name is invalid");
        }
        for (Junction j : roadNetwork.vertices()) {
            if (j.getName().equalsIgnoreCase(junctionID)) {
                return j;
            }
        }
        return null;
    }

    public Road getRoadByRoadID(String roadID) {
        for (Road r : listRoads) {
            if (r.getRoadID().equals(roadID)) {
                return r;
            }
        }
        return null;
    }

    public List<Road> getListRoads() {
        return listRoads;
    }

    public Graph<Junction, Section> getRoadNetwork() {
        return roadNetwork;
    }

    public VehicleList getListVehicles() {
        return listOfVehicles;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ListOfResults getResults() {
        return listOfResults;
    }

    public Section getSection(Junction j1, Junction j2) {
        if (!roadNetwork.validVertex(j1) || !roadNetwork.validVertex(j2)) {
            throw new IllegalArgumentException("Invalid junction");
        }
        return roadNetwork.getEdge(j1, j2).getElement();
    }

    public void setRoadNetwork(Graph<Junction, Section> mRoadNetwork) {
        this.roadNetwork = mRoadNetwork;
    }

    public void setListVehicles(VehicleList vehicleList) {
        this.listOfVehicles = vehicleList;
    }

    public void setListRoads(List<Road> listOfRoads) {
        this.listRoads = listOfRoads;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setListOfResults(ListOfResults listOfResults) {
        this.listOfResults = listOfResults;
    }

    public boolean addRoad(Road r) {
        boolean added = false;
        if (!listRoads.contains(r)) {
            added = listRoads.add(r);
        }
        return added;
    }

    public boolean addVehicle(Vehicle v) {
        return listOfVehicles.addVehicle(v);
    }

    public boolean addJunction(Junction j) {
        return roadNetwork.insertVertex(j);
    }

    public boolean addSection(Section s) {
        Junction orig = s.getBeginningJunction();
        Junction dest = s.getEndingJunction();
        if (orig == null || dest == null) {
            return false;
        }
        if (!roadNetwork.validVertex(orig) || !roadNetwork.validVertex(dest)) {
            return false;
        }

        for (Edge<Junction, Section> edge : roadNetwork.edges()) {
            if (edge.getElement().equals(s)) {
                return false;
            }
        }

        if (s.getDirection() == Section.Direction.REVERSE) {
            orig = s.getEndingJunction();
            dest = s.getBeginningJunction();
            return roadNetwork.insertEdge(orig, dest, s, s.getSectionLength());
        }

        if (s.getDirection() == Section.Direction.BIDIRECTIONAL) {
            roadNetwork.insertEdge(orig, dest, s, s.getSectionLength());
            Section sec = s.reverseSection();
            roadNetwork.insertEdge(dest, orig, sec, s.getSectionLength());
            return true;
        }
        return roadNetwork.insertEdge(orig, dest, s, s.getSectionLength());

    }

    public boolean validate() {
        if (this.name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("The project name is invalid.");
        }
        if (this.description == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(("The project description is invalid."));
        }
        if (roadNetwork.numVertices() < 2 || roadNetwork.numEdges() == 0) {
            throw new IllegalArgumentException("The project number of vertices are invalid.");
        }
        if (roadNetwork.numEdges() == 0) {
            throw new IllegalArgumentException("There must be atleast one road");
        }

        return true;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public VehicleList copyVehicleList() {
        VehicleList list = new VehicleList();
        for (Vehicle vehicle : listOfVehicles.getVehicleList()) {
            list.addVehicle(vehicle);
        }
        return list;
    }
}
