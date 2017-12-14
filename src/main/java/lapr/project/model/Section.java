package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Section {

    private Junction m_beginning_junction;
    private Junction m_ending_junction;
    private String m_typology;
    private Direction m_direction;
    private List<Segment> sequenceOfSegments;

    public enum Direction {
        DIRECT("DIRECT"), REVERSE("REVERSE"), BIDIRECTIONAL("BIDIRECTIONAL");
        private String d_name;

        Direction(String s) {
            d_name = s;
        }
    }

    public Section() {
        sequenceOfSegments = new ArrayList<>();
    }

    public Junction getBeginningJunction() {
        return m_beginning_junction;
    }

    public Junction getEndingJunction() {
        return m_ending_junction;
    }

    public String getTypology() {
        return m_typology;
    }

    public Direction getDirection() {
        return m_direction;
    }

    public List<Segment> getSequenceOfSegments() {
        return sequenceOfSegments;
    }

    public double getSectionLength() {
        double length = 0;
        for (Segment s : sequenceOfSegments) {
            length += s.getLength();

        }
        return length;

    }

    public void setDirection(Direction d) {
        this.m_direction = d;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.m_beginning_junction);
        hash = 53 * hash + Objects.hashCode(this.m_ending_junction);
        hash = 53 * hash + Objects.hashCode(this.m_typology);
        hash = 53 * hash + Objects.hashCode(this.m_direction);
        hash = 53 * hash + Objects.hashCode(this.sequenceOfSegments);
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
        return Objects.equals(this.sequenceOfSegments, other.sequenceOfSegments);
    }

    
}
