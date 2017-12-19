/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.networkanalysis;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import lapr.project.model.Junction;
import lapr.project.model.Project;
import lapr.project.model.Section;
import lapr.project.model.Segment;
import lapr.project.model.Vehicle;

public class NetworkAnalysis {

    private Junction m_begin;
    private Junction m_end;
    private Project m_project;
    private ArrayList<Vehicle> listOfVehicles;
    private AlgorithmResults fastestResults;

    public NetworkAnalysis(Project project) {
        m_project = project;
    }

    public void addVehicle(Vehicle vehicle) {
        listOfVehicles.add(vehicle);
    }

    public void calculate(boolean fastestPath, boolean efficientPath, boolean realeffecientPath) {
        if (fastestPath) {
            //calculateFastestPath();
        }
    }

    private void calculateFastestPath(Vehicle vehicle) {
        List<LinkedList<Section>> paths = this.m_project.allPaths(this.m_begin, this.m_end);
        double lowest = Double.POSITIVE_INFINITY;
        double final_distance = 0;
        LinkedList<Section> fastestPath = new LinkedList<>();
        for (LinkedList<Section> path : paths) {
            double result = 0;
            double distance = 0;
            for (Section section : path) {
                for (Segment segment : section.getSequenceOfSegments()) {
                    double vel = getMaximumVelocityIn(segment, vehicle, section);
                    distance += segment.getLength();
                    result += segment.getLength() / vel;
                }
            }

            if (lowest > result) {
                lowest = result;
                fastestPath = path;
                final_distance = distance;
            }
        }
        fastestResults = new AlgorithmResults(m_project,fastestPath, vehicle, final_distance, lowest);
        fastestResults.calculate();
    }

    private void setBeginJunction(Junction begin) {
        this.m_begin = begin;
    }

    private void setEndJunction(Junction end) {
        this.m_end = end;
    }

    protected static final double getMaximumVelocityIn(Segment segment, Vehicle vehicle, Section section) {
        double velocity = vehicle.getRoadVelocityLimit(section.getTypology());
        double windSpeed = segment.getWindSpeed() * Math.cos(Math.toRadians(segment.getWindDirection()));

        velocity -= windSpeed;
        double maxVelocity = segment.getMaximumVelocity();
        velocity = Math.min(velocity, (maxVelocity > 0) ? maxVelocity : velocity);
        return velocity;
    }

    public String toStringHTML(List<String> vehicles) {
        StringBuilder sb = new StringBuilder();

        sb.append("<h1>Network Static Analysis Results</h1>");
        sb.append("<br/>");
        sb.append("<h3>Fastest Path Algorithm Results</h3>\n");

        if (!(this.fastestResults == null)) {
            sb.append("<table>\n");
            sb.append("\t<tr><th>Vehicle Name</th><th>Vehicle Path</th><th>Workload Energy</th><th>Time Spent</th></tr>\n");

            for (String vehicleName : vehicles) {
                if (fastestResults.getVehicle().getName().equalsIgnoreCase(vehicleName)) {
                    sb.append("\t<tr>"
                            + "<td>").append(vehicleName).append("</td>"
                            + "<td>").append(fastestResults.getPath().toString()).append("</td>"
                            + "<td>").append(fastestResults.getEnergy()).append(" J</td>"
                            + "<td>").append(fastestResults.getTravelTime()).append(" s</td>"
                            + "</tr>\n");
                }
            }
            sb.append("</table>\n");
        } else {
            sb.append("<ul><li>No vehicle was analised under the fastest path algorithm.</li></ul>\n");
        }
        return sb.toString();
    }
}
