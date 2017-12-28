package lapr.project.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Section {

    private Junction beginningJunction;
    private Junction endingJunction;
    private String roadID;
    private int sectionID;
    private String typology;
    private Direction direction;
    private List<Segment> listOfSegments;
    private Map<Integer, Double> mapOfTolls;

    public enum Direction {
        DIRECT("DIRECT"), REVERSE("REVERSE"), BIDIRECTIONAL("BIDIRECTIONAL");
        private final String directionName;

        Direction(String s) {
            directionName = s;
        }
    }

    public Section() {
        listOfSegments = new ArrayList<>();
        direction = Direction.DIRECT;
        mapOfTolls = new HashMap<>();
    }

    public Section(Section s) {
        this.beginningJunction = s.beginningJunction;
        this.endingJunction = s.endingJunction;
        this.roadID = s.roadID;
        this.sectionID = s.sectionID;
        this.typology = s.typology;

        this.listOfSegments = new ArrayList<>();
        for (Segment seg : s.listOfSegments) {
            this.listOfSegments.add(new Segment(seg));
        }

        this.mapOfTolls = new HashMap<>();
        for (Integer i : s.mapOfTolls.keySet()) {
            this.mapOfTolls.put(i, s.mapOfTolls.get(i));
        }
    }

    public boolean addToll(int vehicleID, double value) {
        if (vehicleID <= 0 || value < 0) {
            throw new IllegalArgumentException("Invalid values for toll");
        }
        if (!mapOfTolls.containsKey(vehicleID)) {
            mapOfTolls.put(vehicleID, value);
            return true;
        }
        return false;
    }

    public Map<Integer, Double> getToll() {
        return mapOfTolls;
    }

    public Junction getBeginningJunction() {
        return beginningJunction;
    }

    public Junction getEndingJunction() {
        return endingJunction;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getID() {
        return sectionID;
    }

    public List<Segment> getSequenceOfSegments() {
        return listOfSegments;
    }

    public double getSectionLength() {
        double length = 0;
        for (Segment s : listOfSegments) {
            length += s.getLength();

        }
        return length;

    }

    public String getRoadID() {
        return roadID;
    }

    public String getTypology() {
        return typology;
    }

    public double getTollValue(int vehicle_id) {
        if (vehicle_id <= 0) {
            throw new IllegalArgumentException("Invalid toll key");
        }
        if (mapOfTolls.containsKey(vehicle_id)) {
            return mapOfTolls.get(vehicle_id);
        }

        return -1;
    }

    public void setID(int id) {
        this.sectionID = id;

    }

    public void setTypology(String typ) {
        if (typ == null || typ.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid typology");
        }
        typology = typ;
    }

    public void setBeginJunction(Junction begin) {
        this.beginningJunction = begin;
    }

    public void setEndJunction(Junction end) {
        this.endingJunction = end;
    }

    public void setRoadID(String r_id) {
        this.roadID = r_id;
    }

    public void setSegmentList(List<Segment> listOfSegments) {
        this.listOfSegments = listOfSegments;
    }

    public void setDirection(Direction d) {
        if (d == null) {
            throw new IllegalArgumentException("Invalid Direction");
        }
        this.direction = d;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.beginningJunction);
        hash = 53 * hash + Objects.hashCode(this.endingJunction);
        hash = 53 * hash + Objects.hashCode(this.direction);
        hash = 53 * hash + Objects.hashCode(this.listOfSegments);
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
        if (!Objects.equals(this.roadID, other.roadID)) {
            return false;
        }
        if (!Objects.equals(this.typology, other.typology)) {
            return false;
        }
        if (!Objects.equals(this.beginningJunction, other.beginningJunction)) {
            return false;
        }
        if (!Objects.equals(this.endingJunction, other.endingJunction)) {
            return false;
        }
        if (this.direction != other.direction) {
            return false;
        }
        if (!Objects.equals(this.listOfSegments, other.listOfSegments)) {
            return false;
        }
        return true;
    }

    public boolean validate() {
        if (!beginningJunction.validate() || !endingJunction.validate() || roadID.trim().isEmpty()) {
            throw new IllegalArgumentException(("Section is invalid."));
        }
        if (direction == null || listOfSegments.isEmpty() || typology == null || typology.trim().isEmpty()) {
            throw new IllegalArgumentException(("Section is invalid."));
        }
        for (Segment seg : listOfSegments) {
            if (!seg.validate()) {
                return false;
            }
        }
        return true;
    }

    public Section reverseSegment() {
        Section section = new Section();
        section.beginningJunction = this.beginningJunction;
        section.endingJunction = this.endingJunction;
        section.direction = this.direction;
        section.roadID = this.roadID;
        section.mapOfTolls = this.mapOfTolls;
        section.typology = this.typology;

        List<Segment> newlist = new ArrayList<>();
        int index = 0;
        for (int i = listOfSegments.size() - 1; i >= 0; i--) {
            Segment seg = listOfSegments.get(i).reverseSegment(index);
            index++;
            newlist.add(seg);
        }
        section.setSegmentList(newlist);
        return section;
    }

    public String toString() {
        return roadID + " " + beginningJunction + " " + endingJunction;

    }

    /**
     * Returns the textual description of the object in html format.
     *
     * @return Textual description of the object.
     */
    public String toStringHTML() {
        StringBuilder sb = new StringBuilder();

        sb.append("<table>\n");
        sb.append("\t<tr><th>Begin Road Junction</th><th>Road ID</th><th>End Road Junction</th></tr>");
        sb.append("<tr>"
                + "<td>").append(this.beginningJunction.toStringHTML()).append("</td>"
                + "<td>").append(this.roadID).append("</td>"
                + "<td>").append(this.endingJunction.toStringHTML()).append("</td>"
                + "</tr>\n");
        return sb.toString();
    }

}
