package lapr.project.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class Road {

    private String m_road_id;
    private String m_name;
    private String m_typology;
    private Map<Integer, Double> m_toll_fare;

    public Road() {
        m_road_id = "";
        m_name = "";
        m_typology = "";
        m_toll_fare = new LinkedHashMap<>();
    }

    public Road(String road_id, String name, String typology) {
        setRoadID(road_id);
        setName(name);
        setTypology(typology);
        m_toll_fare = new LinkedHashMap<>();
    }

    public Road(Road r) {
        this.m_road_id = r.m_road_id;
        this.m_name = r.m_name;
        this.m_typology = r.m_typology;
        this.m_toll_fare = new LinkedHashMap<>();
        for (Integer i : r.m_toll_fare.keySet()) {
            this.m_toll_fare.put(i, r.m_toll_fare.get(i));
        }
    }

    public String getRoadID() {
        return m_road_id;
    }

    public String getName() {
        return m_name;
    }

    public String getTypology() {
        return m_typology;
    }

    public void setRoadID(String road_id) {
        if (road_id == null || road_id.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid road id.");
        }
        this.m_road_id = road_id;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid road name.");
        }
        this.m_name = name;
    }

    public void setTypology(String typology) {
        if (typology == null || typology.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid typology.");
        }
        m_typology = typology;
    }

    public void setTollFare(Map<Integer, Double> map) {
        if (map == null) {
            throw new IllegalArgumentException("Invalid map of toll fares");
        }
        m_toll_fare = map;
    }

    public void addTollFare(int vehicle_id, double toll_value) {
        if (vehicle_id <= 0 || toll_value < 0) {
            throw new IllegalArgumentException("Invalid values, "
                    + "must be greater than zero for vehicle and "
                    + "toll_value greater than zero");
        }
        m_toll_fare.put(vehicle_id, toll_value);
    }

    public double getTollValue(int vehicle_id) {
        if (vehicle_id <= 0) {
            throw new IllegalArgumentException("Vehicle id must be greater than zero");
        }
        if (m_toll_fare.containsKey(vehicle_id)) {
            return m_toll_fare.get(vehicle_id);
        }

        return -1;
    }

    public boolean validate() {
        if (m_name.trim().isEmpty() || m_road_id.trim().isEmpty()
                || m_typology.trim().isEmpty() || m_toll_fare == null) {
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

        sb.append("\t<li>Road id: ").append(this.m_road_id).append("</li>\n");
        sb.append("\t<li>Name: ").append(this.m_name).append("</li>\n");
        sb.append("\t<li>Typology: ").append(this.m_typology).append("</li>\n");

        return sb.toString();
    }
}
