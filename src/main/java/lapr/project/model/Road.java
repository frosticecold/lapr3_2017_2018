package lapr.project.model;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import lapr.project.calculations.Constants;

public class Road {

    /**
     * Road ID
     */
    private String id;

    /**
     * Road name
     */
    private String name;

    /**
     * Road typology
     */
    private String typology;

    /**
     * Road tollfare
     */
    private Map<Integer, Double> tollFare;

    /**
     * Empty Road constructor
     */
    public Road() {
        id = "";
        name = "";
        typology = "";
        tollFare = new LinkedHashMap<>();
    }

    /**
     * Construtor for a Road with 3 parameters
     *
     * @param roadID Road id
     * @param name Name of the Road
     * @param typology Typology of the road
     */
    public Road(String roadID, String name, String typology) {
        setRoadID(roadID);
        setName(name);
        setTypology(typology);
        tollFare = new LinkedHashMap<>();
    }

    /**
     * Copy constructor
     *
     * @param r Road object to copy
     */
    public Road(Road r) {
        this.id = r.id;
        this.name = r.name;
        this.typology = r.typology;
        this.tollFare = new LinkedHashMap<>();
        for (Integer i : r.tollFare.keySet()) {
            this.tollFare.put(i, r.tollFare.get(i));
        }
    }

    /**
     * Returns the road ID
     *
     * @return
     */
    public String getRoadID() {
        return id;
    }

    /**
     * Returns the Roads Name
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the Roads Typology
     *
     * @return
     */
    public String getTypology() {
        return typology;
    }

    /**
     * Changes the roads id
     *
     * @param roadID
     */
    public void setRoadID(String roadID) {
        if (roadID == null || roadID.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid road id.");
        }
        this.id = roadID;
    }

    /**
     * Changes the roads name
     *
     * @param name
     */
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid road name.");
        }
        this.name = name;
    }

    /**
     * Changes the roads typology
     *
     * @param typology
     */
    public void setTypology(String typology) {
        if (typology == null || typology.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid typology.");
        }
        this.typology = typology;
    }

    /**
     * Changes the toll map for the road
     *
     * @param map
     */
    public void setTollFare(Map<Integer, Double> map) {
        if (map == null) {
            throw new IllegalArgumentException("Invalid map of toll fares");
        }
        tollFare = map;
    }

    /**
     * Adds a vehicle id and an value to the toll
     *
     * @param vehicleID Vehicle Class
     * @param tollValue Toll value
     */
    public void addTollFare(int vehicleID, double tollValue) {
        if (vehicleID <= 0 || tollValue < 0) {
            throw new IllegalArgumentException("Invalid values, "
                    + "must be greater than zero for vehicle and "
                    + "toll_value greater than zero");
        }
        tollFare.put(vehicleID, tollValue);
    }

    /**
     * Returns the toll value for a determined vehicle class
     *
     * @param vehicleID Class of a vehicle
     * @return
     */
    public double getTollValue(int vehicleID) {
        if (vehicleID <= 0) {
            throw new IllegalArgumentException("Vehicle id must be greater than zero");
        }
        if (tollFare.containsKey(vehicleID)) {
            return tollFare.get(vehicleID);
        }

        return -1;
    }
    
    /**
     * Returns the toll fare map
     * 
     * @return Map of tolls and respective class
     */
    public Map<Integer, Double> getTollFare() {
        return tollFare;
    }

    /**
     * Validates a road object
     *
     * @return true or IllegalArgumentException
     */
    public boolean validate() {
        if (name.trim().isEmpty() || id.trim().isEmpty()
                || typology.trim().isEmpty()) {
            throw new IllegalArgumentException("Road is invalid");
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.id);
        hash = 37 * hash + Objects.hashCode(this.name);
        hash = 37 * hash + Objects.hashCode(this.typology);
        hash = 37 * hash + Objects.hashCode(this.tollFare);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Road other = (Road) obj;

        if (!this.id.equalsIgnoreCase(other.id)) {
            return false;
        }
        if (!this.name.equalsIgnoreCase(other.name)) {
            return false;
        }
        return this.typology.equalsIgnoreCase(other.typology);
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
