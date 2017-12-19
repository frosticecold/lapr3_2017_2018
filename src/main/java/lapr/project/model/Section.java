package lapr.project.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Section {

    private int key;
    private Junction m_beginning_junction;
    private Junction m_ending_junction;
    private String m_road_id;
    private int m_section_id;
    private String m_typology;
    private Direction m_direction;
    private List<Segment> m_sequenceOfSegments;
    private Map<Integer, Double> m_toll;

    public enum Direction {
        DIRECT("DIRECT"), REVERSE("REVERSE"), BIDIRECTIONAL("BIDIRECTIONAL");
        private final String d_name;

        Direction(String s) {
            d_name = s;
        }
    }

    public Section() {
        m_sequenceOfSegments = new ArrayList<>();
        m_direction = Direction.DIRECT;
        m_toll = new HashMap<>();
    }

    public Section(Section s) {
        this.m_beginning_junction = m_beginning_junction.copy();
        this.m_ending_junction = m_ending_junction.copy();
        this.m_road_id = s.m_road_id;
        this.m_section_id = s.m_section_id;
        this.m_typology = s.m_typology;
        this.m_direction = s.m_direction;

        m_sequenceOfSegments = new ArrayList<>();
        for (Segment segment : s.m_sequenceOfSegments) {
            m_sequenceOfSegments.add(segment.clone());
        }
        Map<Integer, Double> map = new HashMap<>();
        for (Integer i : map.keySet()) {
            map.put(i, m_toll.get(i));
        }
    }

    public boolean addToll(int vehic_id, double value) {
        if (vehic_id <= 0 || value < 0) {
            throw new IllegalArgumentException("Invalida values for toll");
        }
        if (!m_toll.containsKey(vehic_id)) {
            m_toll.put(vehic_id, value);
            return true;
        }
        return false;
    }

    public Map<Integer, Double> getToll() {
        return m_toll;
    }

    public Junction getBeginningJunction() {
        return m_beginning_junction;
    }

    public Junction getEndingJunction() {
        return m_ending_junction;
    }

    public Direction getDirection() {
        return m_direction;
    }

    public int getID() {
        return m_section_id;
    }

    public List<Segment> getSequenceOfSegments() {
        return m_sequenceOfSegments;
    }

    public double getSectionLength() {
        double length = 0;
        for (Segment s : m_sequenceOfSegments) {
            length += s.getLength();

        }
        return length;

    }

    public String getRoadID() {
        return m_road_id;
    }

    public String getTypology() {
        return m_typology;
    }

    public int getKey() {
        return this.key;
    }

    public double getTollValue(int toll_key) {
        if (toll_key <= 0) {
            throw new IllegalArgumentException("Invalid toll key");
        }
        if (m_toll.containsKey(toll_key)) {
            return m_toll.get(key);
        }

        return -1;
    }

    public void setKey(int key) {
        if (key < 0) {
            throw new IllegalArgumentException("Invalid key.");
        }
        this.key = key;
    }

    public void setID(int id) {
        this.m_section_id = id;

    }

    public void setTypology(String typ) {
        if (typ == null || typ.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid typology");
        }
        m_typology = typ;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.m_beginning_junction);
        hash = 53 * hash + Objects.hashCode(this.m_ending_junction);
        hash = 53 * hash + Objects.hashCode(this.m_direction);
        hash = 53 * hash + Objects.hashCode(this.m_sequenceOfSegments);
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
        final Section other = (Section) obj;
        if (!Objects.equals(this.m_road_id, other.m_road_id)) {
            return false;
        }
        if (!Objects.equals(this.m_typology, other.m_typology)) {
            return false;
        }
        if (!Objects.equals(this.m_beginning_junction, other.m_beginning_junction)) {
            return false;
        }
        if (!Objects.equals(this.m_ending_junction, other.m_ending_junction)) {
            return false;
        }
        if (this.m_direction != other.m_direction) {
            return false;
        }
        if (!Objects.equals(this.m_sequenceOfSegments, other.m_sequenceOfSegments)) {
            return false;
        }
        return true;
    }

    public void setBeginJunction(Junction begin) {
        this.m_beginning_junction = begin;
    }

    public void setEndJunction(Junction end) {
        this.m_ending_junction = end;
    }

    public void setRoadID(String r_id) {
        this.m_road_id = r_id;
    }

    public void setSegmentList(List<Segment> listOfSegments) {
        m_sequenceOfSegments = listOfSegments;
    }

    public void setDirection(Direction d) {
        if (d == null) {
            throw new IllegalArgumentException("Invalid Direction");
        }
        this.m_direction = d;
    }

    public boolean validate() {
        if (!m_beginning_junction.validate() || !m_ending_junction.validate() || m_road_id.trim().isEmpty() || m_direction == null || m_sequenceOfSegments.isEmpty() || m_typology == null || m_typology.trim().isEmpty()) {
            throw new IllegalArgumentException(("Section is invalid."));
        }
        for (Segment seg : m_sequenceOfSegments) {
            if (!seg.validate()) {
                return false;
            }
        }
        return true;
    }

    public Section reverseSegment() {
        Section section = new Section();
        section.m_beginning_junction = this.m_beginning_junction;
        section.m_ending_junction = this.m_ending_junction;
        section.m_direction = this.m_direction;
        section.m_road_id = this.m_road_id;
        section.m_toll = this.m_toll;

        List<Segment> newlist = new ArrayList<>();
        int index = 0;
        for (int i = m_sequenceOfSegments.size() - 1; i <= 0; i++) {
            Segment seg = m_sequenceOfSegments.get(i).reverseSegment(index);
            index++;
            newlist.add(seg);
        }
        section.setSegmentList(newlist);
        return section;
    }

    public Section copy() {
        return new Section(this);
    }

}
