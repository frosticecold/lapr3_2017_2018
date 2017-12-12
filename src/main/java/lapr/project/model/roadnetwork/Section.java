package lapr.project.model.roadnetwork;

import java.util.ArrayList;
import java.util.List;

public class Section {

    private Junction m_beginning_junction;
    private Junction m_ending_junction;
    private Typology m_typology;
    private int m_direction;
    private List<Segment> sequenceOfSegments;

    public Section() {
        sequenceOfSegments = new ArrayList<>();
    }

}
