package lapr.project.networkanalysis;

import java.awt.List;
import java.util.LinkedList;
import lapr.project.model.Section;
import lapr.project.model.Vehicle;

public class AlgorithmResults {

    private LinkedList<Section> path;
    private Vehicle vehicle;
    private double cost;
    private double travelTime;
    private double energy;

    public AlgorithmResults(LinkedList<Section> fastestPath, Vehicle vehicle, double travelTime) {
        this.path = fastestPath;
        this.vehicle = vehicle;
        this.travelTime = travelTime;
    }

    public AlgorithmResults() {
        this.path = new LinkedList<>();
        this.cost = 0.0;
        this.travelTime = 0.0;
        this.energy = 0.0;
    }

    public double calculateTripCost() {
        for (Section section : path) {
            for (Integer integer : section.getToll().keySet()) {
                if (vehicle.getVehicleClass() == integer) {
                    cost += section.getToll().get(integer);
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
