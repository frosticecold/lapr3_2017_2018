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
        Junction[] vertices = graph.allkeyVerts();
        shortPath.clear();
        sectionpath.clear();

        for (Junction v : graph.vertices()) {
            time[graph.getKey(v)] = Double.MAX_VALUE;
            pathKeys[graph.getKey(v)] = -1;
            velocity[graph.getKey(v)] = 0;
            visited[graph.getKey(v)] = false;
        }

        efficientPathLength(graph, vOrig, vertices, visited, pathKeys, time, energy, velocity, vehicle, acceleration);

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
            boolean[] visited, int[] pathKeys, double[] time, double[] energy, double[] velocity, Vehicle vehicle, double acceleration) {

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
                //Results
                //0) Time
                //1) Energy
                //2) Velocity
                double previous_velocity = velocity[kOrg];
                double results[] = calcEfficientPath(section, vehicle, acceleration, previous_velocity);
                if (!visited[kAdj] && time[kAdj] > (time[kOrg] + results[1])) {
                    time[kAdj] = time[kOrg] + results[1];
                    pathKeys[kAdj] = kOrg;
                    energy[kAdj] = energy[kAdj] + results[1];
                    //velocity[kAjd] = result[1];
                }
            }
            i = getVertMinDist(time, visited);
        }
    }

    private static double[] calcEfficientPath(Section section, Vehicle car, double acceleration, double previousVelocity) {
        double fma = car.getTotalWeight() * acceleration;
        for (Segment seg : section.getSequenceOfSegments()) {
            double neededforce = PhysicsCalculus.calcForceInSegment(seg, car, section);
            double maximumvel = car.getMaximumPermitedVelocity(section.getTypology());
            if (maximumvel > seg.getMaximumVelocity()) {
                maximumvel = seg.getMaximumVelocity();
            }
            double deltatimeaccel = PhysicsCalculus.calcTimeBasedOnVelocityAndAcceleration(maximumvel, previousVelocity, acceleration);
        }
        return new double[2];
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
