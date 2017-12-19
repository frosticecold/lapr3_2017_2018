/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.networkanalysis;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import lapr.project.calculations.UnitConversion;
import lapr.project.model.Junction;
import lapr.project.model.Project;
import lapr.project.model.Section;
import lapr.project.model.Segment;
import lapr.project.model.Vehicle;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public class NetworkAnalysis {

    private Junction m_begin;
    private Junction m_end;
    private Project m_project;
    private ArrayList<Vehicle> listOfVehicles;

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
        LinkedList<Section> fastestPath = new LinkedList<>();
        for (LinkedList<Section> path : paths) {
            double result = 0;
            for (Section section : path) {
                for(Segment segment : section.getSequenceOfSegments()){
                    double vel = getMaximumVelocityIn(segment, vehicle, section);

                    result += segment.getLength() / vel;
                }
            }

            if (lowest > result) {
                lowest = result;
                fastestPath = path;
            }
        }}
    

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

}
