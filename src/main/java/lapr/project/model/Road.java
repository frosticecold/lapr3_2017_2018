package lapr.project.model;

import java.util.ArrayList;
import java.util.HashMap;
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
    }

    public Road(String road_id, String name, String typology) {
        m_road_id = road_id;
        m_name = name;
        m_typology = typology;
        m_listOfSections = new ArrayList<>();
    }

    public boolean addSection(Section s) {
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

    public void setRoadID(String m_road_id) {
        this.m_road_id = m_road_id;
    }

    public String getName() {
        return m_name;
    }

    public void setName(String m_name) {
        this.m_name = m_name;
    }

    public List<Section> getListOfSections() {
        return m_listOfSections;
    }

    public String getTypology() {
        return m_typology;
    }

    public void setTypology(String typology) {
        m_typology = typology;
    }

    public void setListOfSections(List<Section> m_listOfSections) {
        this.m_listOfSections = m_listOfSections;
    }

    public void addTollFare(int vehicle_id, double toll_value) {
        m_toll_fare.put(vehicle_id, toll_value);
    }

    public void setTollFare(Map<Integer, Double> map) {
        m_toll_fare = map;
    }
}
