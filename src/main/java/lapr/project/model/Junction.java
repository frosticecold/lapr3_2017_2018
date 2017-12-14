package lapr.project.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Junction {

    private String m_name;
    private Set<Road> m_listOfRoads;

    public Junction(String name) {
        m_name = name;
        m_listOfRoads = new HashSet<>();
    }

    public String getName() {
        return m_name;
    }

    public void addRoad(Road r) {
        m_listOfRoads.add(r);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.m_name);
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
        final Junction other = (Junction) obj;
        if (!Objects.equals(this.m_name, other.m_name)) {
            return false;
        }
        return true;
    }

}
