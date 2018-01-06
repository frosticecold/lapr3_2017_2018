/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.pathalgorithms;

import java.util.LinkedList;
import lapr.project.calculations.PhysicsCalculus;
import lapr.project.model.Junction;
import lapr.project.model.Section;
import lapr.project.model.Segment;
import lapr.project.model.Vehicle;
import lapr.project.networkanalysis.AlgorithmResults;
import lapr.project.utils.Session;
import lapr.project.utils.graphbase.Edge;
import lapr.project.utils.graphbase.Graph;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public class TheoreticalEnergyEfficientAlgorithm implements PathAlgorithm {

    public static final String ALG_NAME = "Theoretical Most Energy Efficient Path";

    @Override
    public AlgorithmResults bestPath(Graph<Junction, Section> graph, Junction start, Junction end, Vehicle v, double acceleration) {
        LinkedList<Junction> path = new LinkedList<>();
        LinkedList<Section> sectionpath = new LinkedList<>();
        double results[] = efficientPath(graph, start, end, v, path, sectionpath, acceleration);
        AlgorithmResults alg = new AlgorithmResults(Session.getActiveProject(), path, sectionpath, v, results, this.toString());
        alg.calculate();

        return alg;
    }

    private static double[] efficientPath(Graph<Junction, Section> graph, Junction vOrig, Junction vDest, Vehicle vehicle, LinkedList<Junction> shortPath, LinkedList<Section> sectionpath, double acceleration) {
        double results[] = {-1, -1};//Results 0)Energy 1) Time
        if (!graph.validVertex(vOrig) || !graph.validVertex(vDest)) {
            return results;
        }
        boolean[] visited = new boolean[graph.numVertices()];
        int[] pathKeys = new int[graph.numVertices()];
        double[] time = new double[graph.numVertices()];
        double[] energy = new double[graph.numVertices()];
        double[] velocity = new double[graph.numVertices()];
        double[] torque = new double[graph.numVertices()];
        double[] rpm = new double[graph.numVertices()];
        double[] gear = new double[graph.numVertices()];
        Junction[] vertices = graph.allkeyVerts();
        shortPath.clear();
        sectionpath.clear();

        for (Junction v : graph.vertices()) {
            int key = graph.getKey(v);
            pathKeys[key] = -1;
            visited[key] = false;
            time[key] = Double.MAX_VALUE;
            energy[key] = 0;
            velocity[key] = 0;
            torque[key] = 0;
            rpm[key] = 0;
            gear[key] = 0;
        }

        efficientPathLength(graph, vOrig, vertices, visited, pathKeys, time, energy, velocity, torque, rpm, gear, vehicle, acceleration);

        //double timePath = time[graph.getKey(vDest)];
        results[1] = time[graph.getKey(vDest)];
        results[0] = energy[graph.getKey(vDest)];

        if (Double.MAX_VALUE - results[0] > 0) {
            getPath(graph, vOrig, vDest, vertices, pathKeys, shortPath, sectionpath);
            return results;
        }
        return results;
    }

    private static <V, E> void efficientPathLength(Graph<Junction, Section> g, Junction vOrig, Junction[] vertices,
            boolean[] visited, int[] pathKeys, double[] time, double[] energy, double[] velocity, double[] torque, double[] rpm, double[] gear, Vehicle vehicle, double acceleration) {

        Section section = new Section();
        int i = g.getKey(vOrig);
        time[i] = 0;
        Junction vOrigin;
        while (i != -1) {

            vOrigin = vertices[i];
            int kOrg = g.getKey(vOrigin);
            visited[kOrg] = true;
            for (Edge<Junction, Section> edg : g.outgoingEdges(vOrigin)) {
                Junction vAdj = g.opposite(vOrigin, edg);
                int kAdj = g.getKey(vAdj);
                section = edg.getElement();
                double previous_velocity = velocity[kOrg];
                double previous_torque = torque[kOrg];
                double previous_rpm = rpm[kOrg];
                double previous_gear = gear[kOrg];
                //Results
                //0) Time
                //1) Energy
                //2) Velocity
                //3) torque
                //4) rpm
                //5) gear
                double results[] = calcEfficientPath(section, vehicle, acceleration, previous_velocity, previous_torque, previous_rpm, previous_gear);
                if (results[0] != -1) {
                    if (!visited[kAdj] && time[kAdj] > (time[kOrg] + results[1])) {
                        time[kAdj] = time[kOrg] + results[1];
                        pathKeys[kAdj] = kOrg;
                        energy[kAdj] = energy[kAdj] + results[1];
                    }
                }
            }
            i = getVertMinDist(time, visited);
        }
    }

    private static double[] calcEfficientPath(Section section, Vehicle car, double acceleration, double previousVelocity, double previousTorque, double previousRpm, double previousGear) {
        double fma = car.getTotalWeight() * acceleration;
        double[] results = new double[6];
        //Results
        //0) Time
        //1) Energy
        //2) Velocity
        //3) torque
        //4) rpm
        //5) gear
        for (Segment seg : section.getSequenceOfSegments()) {
            boolean accel = false;
            boolean braking = false;
            double maximumvel = car.getMaximumPermitedVelocity2(seg, section.getRoadID()) / 3.6;
            double deltatimeaccel = 0;
            double acceldistance = 0;
            double segmentLength = seg.getLength() * 1000;
            if (previousVelocity < maximumvel) {
                braking = false;
                accel = true;
            } else {
                if (previousVelocity > maximumvel) {
                    accel = false;
                    braking = true;
                }
            }
            if (accel) {
                deltatimeaccel = PhysicsCalculus.calcTimeBasedOnVelocityAndAcceleration(maximumvel, previousVelocity, acceleration);
                acceldistance = PhysicsCalculus.calcDistance(previousVelocity, acceleration, deltatimeaccel);
            } else {
                if (braking) {
                    deltatimeaccel = PhysicsCalculus.calcTimeBasedOnVelocityAndAcceleration(maximumvel, previousVelocity, acceleration * -1);
                    acceldistance = PhysicsCalculus.calcDistance(previousVelocity, acceleration, deltatimeaccel);
                }
            }
            //If accel or brake distance is valid
            if (acceldistance < segmentLength) {
                double remainingtime = (segmentLength - acceldistance) / maximumvel;
                results[0] += deltatimeaccel + remainingtime;
            } else { // Else it is a invalid path
                if (accel) {
                    deltatimeaccel = PhysicsCalculus.calcTimeBasedOnDistanceAndAcceleration(previousVelocity, segmentLength, acceleration);
                    previousVelocity = PhysicsCalculus.calcVelocityBasedOnInitialVelocityAccelerationTime(previousVelocity, acceleration, deltatimeaccel);
                    results[0] += deltatimeaccel;
                    continue;
                }
                if (braking) {
                    for (int i = 0; i < results.length; i++) {
                        results[i] = -1;
                    }
                }
                break;
            }
        }
        return results;
    }

    /**
     * Extracts from pathKeys the minimum path between voInf and vdInf The path
     * is constructed from the end to the beginning
     *
     * @param g Graph instance
     * @param voInf information of the Vertex origin
     * @param vdInf information of the Vertex destination
     * @param pathkeys minimum path vertices keys
     * @param path stack with the minimum path (correct order)
     */
    private static void getPath(Graph<Junction, Section> g, Junction vOrig, Junction vDest, Junction[] verts, int[] pathKeys, LinkedList<Junction> path, LinkedList<Section> sectionpath) {

        if (!vOrig.equals(vDest)) {
            path.push(vDest);

            int vKey = g.getKey(vDest);
            int prevVKey = pathKeys[vKey];
            Junction vDestin;
            vDestin = verts[prevVKey];
            sectionpath.push(g.getEdge(vDest, vDestin).getElement());

            getPath(g, vOrig, vDestin, verts, pathKeys, path, sectionpath);
        } else {
            path.push(vOrig);
        }
    }

    private static int getVertMinDist(double[] dist, boolean[] visited) {
        double min = Double.MAX_VALUE;
        int indice = -1;

        for (int i = 0; i < dist.length; i++) {
            if (!visited[i] && (dist[i] < min)) {
                min = dist[i];
                indice = i;
            }
        }

        return indice;
    }

    @Override
    public String toString() {
        return ALG_NAME;
    }

}
