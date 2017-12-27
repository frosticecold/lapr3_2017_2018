package lapr.project.model;

import java.util.Objects;

public class Junction {

    private String name;

    public Junction(String name) {
        this.name = name;
    }

    public Junction(Junction j) {
        this.name = j.name;
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.name);
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
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    public boolean validate() {
        if (this.name == null || this.name.trim().isEmpty()) {
            throw new IllegalArgumentException("The junction's name cannot be"
                    + "empty.");
        }
        return true;

    }

    @Override
    public String toString() {
        return "Junction " + name;
    }

    /**
     * Returns the textual description of the object in html format.
     *
     * @return Textual description of the object.
     */
    public String toStringHTML() {
        StringBuilder sb = new StringBuilder();

        sb.append("\t<li>ID: ").append(this.name).append("</li>\n");

        return sb.toString();
    }
}
