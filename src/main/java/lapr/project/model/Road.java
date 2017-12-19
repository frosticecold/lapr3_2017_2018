package lapr.project.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Road {

    private String m_road_id;
    private String m_name;
    private String m_typology;
    private List<Section> m_listOfSections;
    private Map<Integer, Double> m_toll_fare;
    private int m_num_of_section;

    public Road() {
        m_road_id = "";
        m_name = "";
        m_typology = "";
        m_listOfSections = new ArrayList<>();
        m_toll_fare = new LinkedHashMap<>();
        m_num_of_section = 0;

    }

    public Road(String road_id, String name, String typology) {
        setRoadID(road_id);
        setName(name);
        setTypology(typology);
        m_listOfSections = new ArrayList<>();
        m_toll_fare = new LinkedHashMap<>();
    }

    public Road(Road r) {
        this.m_road_id = r.m_road_id;
        this.m_name = r.m_name;
        this.m_typology = r.m_typology;

        m_listOfSections = new ArrayList<>();
        for (Section section : r.m_listOfSections) {
            r.m_listOfSections.add(section.copy());
        }

        Map<Integer, Double> map = new LinkedHashMap<>();
        for (Integer i : map.keySet()) {
            map.put(i, m_toll_fare.get(i));
        }

        this.m_num_of_section = r.m_num_of_section;
    }

    public boolean addSection(Section s) {
        if (s == null) {
            throw new IllegalArgumentException("Invalid section");
        }
        boolean added = false;
        if (!m_listOfSections.contains(s)) {
            s.setID(m_num_of_section);
            m_num_of_section++;
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

    public void setListOfSections(List<Section> listofsections) {
        if (listofsections == null) {
            throw new IllegalArgumentException("Invalid list of sections");
        }
        this.m_listOfSections = listofsections;
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
                || m_typology.trim().isEmpty() || m_listOfSections == null || m_toll_fare == null) {
            throw new IllegalArgumentException("Road is invalid");
        }
        return true;
    }

    @Override
    protected Object clone() {
        return new Road(this);
    }

}
