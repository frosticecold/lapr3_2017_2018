/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.networkanalysis;

import java.util.ArrayList;
import java.util.LinkedList;
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

    private void calculateFastestPath(Vehicle vh) {
        if (vh == null) {
            throw new IllegalArgumentException("Invalid car.");
        }
        ArrayList<LinkedList<Junction>> paths = m_project.allPaths(m_begin, m_end);
        LinkedList<Junction> fastestPath = null;
        double fastesttime = 0;
        for (LinkedList<Junction> listOfJunction : paths) {
            if (fastestPath == null) {
                fastestPath = listOfJunction;
            }
            double time = 0;
            if (listOfJunction.size() >= 2) {
                for (int i = 0; i < listOfJunction.size() - 1; i++) {
                    Junction j1 = listOfJunction.get(i);
                    Junction j2 = listOfJunction.get(i + 1);
                    Section section = m_project.getSection(j1, j2);
                    for (Segment seg : section.getSequenceOfSegments()) {
                        time += getMaximumVelocityIn(seg, vh, section) / UnitConversion.convertKmToMeters(seg.getLength());
                    }
                }
            }
            if(time <fastesttime ){
                
            }
        }

    }

    private void setBeginJunction(Junction begin) {
        this.m_begin = begin;
    }

    private void setEndJunction(Junction end) {
        this.m_end = end;
    }

    protected static final double getMaximumVelocityIn(Segment segment, Vehicle vehicle, Section section) {
        double vel = vehicle.getRoadVelocityLimit(section.getTypology());
        double windSpeed = segment.getWindSpeed() * Math.cos(Math.toRadians(segment.getWindDirection()));

        vel -= windSpeed;
        double maxVel = segment.getMaximumVelocity();
        vel = Math.min(vel, (maxVel > 0) ? maxVel : vel);
        return vel;
    }

}
