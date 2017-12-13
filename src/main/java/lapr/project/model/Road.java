package lapr.project.model;

import java.util.ArrayList;
import java.util.List;

public class Road {

    private String m_name;
    private List<Section> m_listOfSections;

    /**
     *
     * @param name
     */
    public Road(String name) {
        m_name = name;
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

}
