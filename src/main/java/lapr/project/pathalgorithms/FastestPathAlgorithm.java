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

public class FastestPathAlgorithm implements PathAlgorithm {

    @Override
    public AlgorithmResults bestPath(Graph<Junction, Section> graph, Junction start, Junction end, Vehicle v, LinkedList<Junction> path) {

        LinkedList<Section> sectionpath = new LinkedList<>();
        double results[] = shortestPath(graph, start, end, v, path, sectionpath);
        AlgorithmResults alg = new AlgorithmResults(Session.getActiveProject(), path, sectionpath, v, results);
        alg.calculate();

        return alg;
    }

    public static double[] shortestPath(Graph<Junction, Section> graph, Junction vOrig, Junction vDest, Vehicle vehicle, LinkedList<Junction> shortPath, LinkedList<Section> sectionpath) {
        double results[] = {-1, -1};//Results 0)Time 1) Energy
        if (!graph.validVertex(vOrig) || !graph.validVertex(vDest)) {
            return results;
        }
        boolean[] visited = new boolean[graph.numVertices()];
        int[] pathKeys = new int[graph.numVertices()];
        double[] time = new double[graph.numVertices()];
        double[] energy = new double[graph.numVertices()];
        Junction[] vertices = graph.allkeyVerts();
        shortPath.clear();
        sectionpath.clear();

        for (Junction v : graph.vertices()) {
            time[graph.getKey(v)] = Double.MAX_VALUE;
            pathKeys[graph.getKey(v)] = -1;
            visited[graph.getKey(v)] = false;
        }

        shortestPathLength(graph, vOrig, vertices, visited, pathKeys, time, energy, vehicle);

        //double timePath = time[graph.getKey(vDest)];
        results[0] = time[graph.getKey(vDest)];
        results[1] = energy[graph.getKey(vDest)];

        if (Double.MAX_VALUE - results[0] > 0) {
            getPath(graph, vOrig, vDest, vertices, pathKeys, shortPath, sectionpath);
            return results;
        }
        return results;
    }

    private static <V, E> void shortestPathLength(Graph<Junction, Section> g, Junction vOrig, Junction[] vertices,
            boolean[] visited, int[] pathKeys, double[] time, double[] energy, Vehicle vehicle) {

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
                //1) Work
                double results[] = calcFastestTime(section, vehicle);
                if (!visited[kAdj] && time[kAdj] > (time[kOrg] + results[0])) {
                    time[kAdj] = time[kOrg] + results[0];
                    pathKeys[kAdj] = kOrg;
                    energy[kAdj] = energy[kAdj] + results[1];
                }
            }
            i = getVertMinDist(time, visited);
        }
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

    public String toString() {
        return "Fastest Path";
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

    private static double[] calcFastestTime(Section s, Vehicle car) {

        //Vector with results
        //0) Time
        //1) Work
        double results[] = new double[2];
        double final_time = 0;
        double energy = 0;

        for (Segment segment : s.getSequenceOfSegments()) {
            double force = PhysicsCalculus.calcForceInSegment(segment, car, s);
            double carMaxVel = PhysicsCalculus.calcMaximumVelocity(segment, car, s);
            double time_segment = (segment.getLength() * 1000) / (carMaxVel);
            double[] ideal_motor_force = PhysicsCalculus.calculateIdealMotorForce(car, segment, force,carMaxVel);
            double power_generated = PhysicsCalculus.calcEnginePower(ideal_motor_force[PhysicsCalculus.TORQUE_VEC], ideal_motor_force[PhysicsCalculus.RPM_VEC]);
            energy += (power_generated * time_segment);
            final_time += time_segment;
//            }
        }
        results[0] = final_time;
        results[1] = energy;

        return results;

    }
}
