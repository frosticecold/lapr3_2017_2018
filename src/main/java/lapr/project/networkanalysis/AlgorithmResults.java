package lapr.project.networkanalysis;

import java.util.LinkedList;
import lapr.project.model.Junction;
import lapr.project.model.Project;
import lapr.project.model.Road;
import lapr.project.model.Section;
import lapr.project.model.Vehicle;

public class AlgorithmResults {

    private Project project;
    private LinkedList<Junction> junctionpath;
    private LinkedList<Section> sectionpath;
    private Vehicle vehicle;
    private double cost;
    private double travelTime;
    private double energy;
    private double distance;

    public AlgorithmResults(Project project, LinkedList<Junction> junctionPath, LinkedList<Section> fastestPath, Vehicle vehicle, double travelTime) {
        this.project = project;
        this.junctionpath = junctionPath;
        this.sectionpath = fastestPath;
        this.vehicle = vehicle;
        this.travelTime = travelTime;
    }

    public void calculate() {
        double temp_cost = 0;
        double toll_value = 0;
        double temp_distance = 0;
        for (Section section : sectionpath) {
            temp_distance += section.getSectionLength();
            String sectionID = section.getRoadID();
            Road rd = project.getRoadByRoadID(sectionID);
            if (rd.getTypology().equalsIgnoreCase("toll highway")) {
                if (rd.getTollValue(vehicle.getVehicleClass()) > 0) {
                    toll_value = rd.getTollValue(vehicle.getVehicleClass());
                    temp_cost += toll_value * distance;
                }

            }
            if (rd.getTypology().equalsIgnoreCase("gantry toll highway")) {
                if (section.getTollValue(vehicle.getVehicleClass()) > 0) {
                    temp_cost += section.getTollValue(vehicle.getVehicleClass());
                }
            }
        }
        cost = temp_cost;
        distance = temp_distance;

    }

    public double getEnergy() {
        return energy;
    }

    public double getTravelTime() {
        return travelTime;
    }

    public double getCost() {
        return cost;
    }

    public double getDistance() {
        return distance;
    }

    public LinkedList<Section> getSectionPath() {
        return sectionpath;
    }

    public LinkedList<Junction> getJunctionPath() {
        return junctionpath;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(project.getName()).append("\n").append(project.getDescription());
        sb.append("\n");
        sb.append(vehicle.getName());
        sb.append("\nJunctions:\n");
        for (Junction j : junctionpath) {
            sb.append(j);
            sb.append("\n");
        }
        sb.append("\nSections:\n");
        for (Section s : sectionpath) {
            sb.append(s);
            sb.append("\n");
        }
        sb.append("\nDistance:").append(distance).append(" Km");
        sb.append("\nTravel time:").append(travelTime).append(" s");
        sb.append("\nCost:").append(cost).append(" €");
        sb.append("\nEnergy:").append(energy).append(" J");
        return sb.toString();
    }

    /**
     * Returns the textual description of the object in html format.
     *
     * @return Textual description of the object.
     */
    public String toStringHTML() {
        StringBuilder sb = new StringBuilder();

        sb.append("<tr>"
                + "<td>").append(this.vehicle.getName()).append("</td>"
                + "<td>").append(this.travelTime).append(" s</td>"
                + "<td>").append(this.energy).append(" J</td>"
                + "<td>").append(this.cost).append(" €</td>");
        sb.append("</tr>\n");

        return sb.toString();
    }
}
