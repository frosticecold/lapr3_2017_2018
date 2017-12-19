package lapr.project.networkanalysis;

import java.util.LinkedList;
import lapr.project.model.Project;
import lapr.project.model.Road;
import lapr.project.model.Section;
import lapr.project.model.Vehicle;
import lapr.project.utils.Session;

public class AlgorithmResults {

    private Project project;
    private LinkedList<Section> path;
    private Vehicle vehicle;
    private double cost;
    private double travelTime;
    private double energy;
    private double distance;

    public AlgorithmResults(Project project, LinkedList<Section> fastestPath, Vehicle vehicle, double distance, double travelTime) {
        this.project = project;
        this.path = fastestPath;
        this.vehicle = vehicle;
        this.distance = distance;
        this.travelTime = travelTime;
    }

    public AlgorithmResults() {
        this.path = new LinkedList<>();
        this.cost = 0.0;
        this.travelTime = 0.0;
        this.energy = 0.0;
    }

    public void calculate() {
        calculateTripCost();

    }

    public double calculateTripCost() {
        double temp_cost = 0;
        double toll_value = 0;
        for (Section section : path) {
            if (section.getRoadID().equalsIgnoreCase("toll highway")) {
                Road rd = project.getRoadByRoadID(section.getRoadID());
                if (rd.getTollValue(vehicle.getVehicleClass()) != null) {
                    toll_value = rd.getTollValue(vehicle.getVehicleClass());
                    temp_cost+= toll_value * distance;
                }

            }
        }
        return cost;
    }

    public double getEnergy() {
        return energy;
    }

    public double getTravelTime() {
        return travelTime;
    }

    public LinkedList<Section> getPath() {
        return path;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public double getCost() {
        return cost;
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
