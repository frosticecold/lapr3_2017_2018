package lapr.project.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Road {

    private String m_road_id;
    private String m_name;
    private String m_typology;
    private List<Section> m_listOfSections;
    private Map<Integer, Double> m_toll_fare;

    public Road() {
        m_road_id = "";
        m_name = "";
        m_typology = "";
        m_listOfSections = new ArrayList<>();
        m_toll_fare = new LinkedHashMap<>();
    }

    public Road(String road_id, String name, String typology) {
        setRoadID(road_id);
        setName(name);
        setTypology(typology);
        m_listOfSections = new ArrayList<>();
        m_toll_fare = new LinkedHashMap<>();
    }

    public Road(Road r) {
        setRoadID(r.m_road_id);
        setName(r.m_name);
        setTypology(r.m_typology);
        m_listOfSections = r.m_listOfSections;
        m_toll_fare = r.m_toll_fare;
    }

    public boolean addSection(Section s) {
        if (s == null) {
            throw new IllegalArgumentException("Invalid section");
        }
        boolean added = false;
        if (!m_listOfSections.contains(s)) {
            m_listOfSections.add(s);
            added = true;
        }
        return added;
    }

    public String getRoadID() {
        return m_road_id;
    }

    public String getName() {
        return m_name;
    }

    public List<Section> getListOfSections() {
        return m_listOfSections;
    }

    public String getTypology() {
        return m_typology;
    }

    public void setRoadID(String m_road_id) {
        this.m_road_id = m_road_id;
    }

    public void setName(String m_name) {
        this.m_name = m_name;
    }

    public void setTypology(String typology) {
        m_typology = typology;
    }

    public void setListOfSections(List<Section> m_listOfSections) {
        this.m_listOfSections = m_listOfSections;
    }

    public void setTollFare(Map<Integer, Double> map) {
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

    public boolean validate() {
        if (m_name.trim().isEmpty() || m_road_id.trim().isEmpty()
                || m_typology.trim().isEmpty() || m_listOfSections == null || m_toll_fare == null) {
            throw new IllegalArgumentException("Road is invalid");
        }
        return true;
    }
}
