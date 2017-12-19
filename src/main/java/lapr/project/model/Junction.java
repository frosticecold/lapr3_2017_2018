package lapr.project.model;

import java.util.Objects;

public class Junction {

    private String m_id;

    public Junction(String id) {
        m_id = id;
    }

    public String getID() {
        return m_id;
    }

    public Junction(Junction j) {
        this.m_id = j.m_id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.m_id);
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
        if (!Objects.equals(this.m_id, other.m_id)) {
            return false;
        }
        return true;
    }

    public boolean validate() {
        if (this.m_id == null || this.m_id.trim().isEmpty()) {
            throw new IllegalArgumentException("The junction's name cannot be"
                    + "empty.");
        }

        return true;

    }

    @Override
    public String toString() {
        return "Junction " + m_id;
    }
    
    

}
