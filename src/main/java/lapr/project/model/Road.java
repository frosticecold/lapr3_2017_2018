package lapr.project.model;

import java.util.LinkedHashMap;
import java.util.Map;
import lapr.project.calculations.Constants;

public class Road {

    private String id;
    private String name;
    private String typology;
    private Map<Integer, Double> tollFare;

    public Road() {
        id = "";
        name = "";
        typology = "";
        tollFare = new LinkedHashMap<>();
    }

    public Road(String roadID, String name, String typology) {
        setRoadID(roadID);
        setName(name);
        setTypology(typology);
        tollFare = new LinkedHashMap<>();
    }

    public Road(Road r) {
        this.id = r.id;
        this.name = r.name;
        this.typology = r.typology;
        this.tollFare = new LinkedHashMap<>();
        for (Integer i : r.tollFare.keySet()) {
            this.tollFare.put(i, r.tollFare.get(i));
        }
    }

    public String getRoadID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTypology() {
        return typology;
    }

    public void setRoadID(String roadID) {
        if (roadID == null || roadID.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid road id.");
        }
        this.id = roadID;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid road name.");
        }
        this.name = name;
    }

    public void setTypology(String typology) {
        if (typology == null || typology.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid typology.");
        }
        this.typology = typology;
    }

    public void setTollFare(Map<Integer, Double> map) {
        if (map == null) {
            throw new IllegalArgumentException("Invalid map of toll fares");
        }
        tollFare = map;
    }

    public void addTollFare(int vehicleID, double tollValue) {
        if (vehicleID <= 0 || tollValue < 0) {
            throw new IllegalArgumentException("Invalid values, "
                    + "must be greater than zero for vehicle and "
                    + "toll_value greater than zero");
        }
        tollFare.put(vehicleID, tollValue);
    }

    public double getTollValue(int vehicleID) {
        if (vehicleID <= 0) {
            throw new IllegalArgumentException("Vehicle id must be greater than zero");
        }
        if (tollFare.containsKey(vehicleID)) {
            return tollFare.get(vehicleID);
        }

        return -1;
    }

    public boolean validate() {
        if (name.trim().isEmpty() || id.trim().isEmpty()
                || typology.trim().isEmpty()) {
            throw new IllegalArgumentException("Road is invalid");
        }
        return true;
    }

    /**
     * Returns the textual description of the object in html format.
     *
     * @return Textual description of the object.
     */
    public String toStringHTML() {
        StringBuilder sb = new StringBuilder();

        sb.append("\t<li>Road id: ").append(this.id).append(Constants.HTML_END_OF_LINE);
        sb.append("\t<li>Name: ").append(this.name).append(Constants.HTML_END_OF_LINE);
        sb.append("\t<li>Typology: ").append(this.typology).append(Constants.HTML_END_OF_LINE);

        return sb.toString();
    }
}
