package lapr.project.model;

import java.util.ArrayList;
import java.util.List;

public class Section {

    private Junction m_beginning_junction;
    private Junction m_ending_junction;
    private String m_typology;
    private String m_direction;
    private List<Segment> sequenceOfSegments;

    public Section() {
        sequenceOfSegments = new ArrayList<>();
    }

    public Junction getBeginning_junction() {
        return m_beginning_junction;
    }

    public Junction getEnding_junction() {
        return m_ending_junction;
    }

    public String getTypology() {
        return m_typology;
    }

    public String getDirection() {
        return m_direction;
    }

    public List<Segment> getSequenceOfSegments() {
        return sequenceOfSegments;
    }

}
