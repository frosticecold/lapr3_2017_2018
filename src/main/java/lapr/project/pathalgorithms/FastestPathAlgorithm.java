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
public class FastestPathAlgorithm implements PathAlgorithm {

//    @Override
//    public AlgorithmResults bestPath(Graph<Junction, Section> graph, Junction start, Junction end, Vehicle v, LinkedList<Junction> path) {
//        Graph<Junction, Section> graphTimeAsWeight = graphTimeAsWeight(graph, v);
//        LinkedList<Section> sectionpath = new LinkedList<>();
//        double time = shortestPath(graphTimeAsWeight, start, end, path, sectionpath);
//        AlgorithmResults alg = new AlgorithmResults(Session.getActiveProject(), path, sectionpath, v, time);
//        alg.calculate();
//
//        return alg;
//    }
    @Override
    public AlgorithmResults bestPath(Graph<Junction, Section> graph, Junction start, Junction end, Vehicle v, LinkedList<Junction> path) {

        LinkedList<Section> sectionpath = new LinkedList<>();
        double time = shortestPath(graph, start, end, v, path, sectionpath);
        AlgorithmResults alg = new AlgorithmResults(Session.getActiveProject(), path, sectionpath, v, time);
        alg.calculate();

        return alg;
    }

    public Graph<Junction, Section> graphTimeAsWeight(Graph<Junction, Section> graph, Vehicle v) {
        Graph<Junction, Section> graphTime = graph.copyGraph();

        for (Edge<Junction, Section> edge : graphTime.edges()) {
            double time = 0;

            for (Segment segment : edge.getElement().getSequenceOfSegments()) {
                //Velocity in m/s
                double velocity = PhysicsCalculus.calcMaximumVelocity(segment, v, edge.getElement());
                //Time = d/v (m / m/s)
                time += (segment.getLength() * 1000) / velocity;
            }

            edge.setWeight(time);

        }

        return graphTime;
    }
    //shortest-path between voInf and vdInf

    public static double shortestPath(Graph<Junction, Section> g, Junction vOrig, Junction vDest, Vehicle vehicle, LinkedList<Junction> shortPath, LinkedList<Section> sectionpath) {

        if (!g.validVertex(vOrig) || !g.validVertex(vDest)) {
            return -1;
        }
        boolean[] visited = new boolean[g.numVertices()];
        int[] pathKeys = new int[g.numVertices()];
        double[] time = new double[g.numVertices()];
        double[] energy = new double[g.numVertices()];
        Junction[] vertices = g.allkeyVerts();
        shortPath.clear();
        sectionpath.clear();

        for (Junction v : g.vertices()) {
            time[g.getKey(v)] = Double.MAX_VALUE;
            pathKeys[g.getKey(v)] = -1;
            visited[g.getKey(v)] = false;
        }

        shortestPathLength(g, vOrig, vertices, visited, pathKeys, time, energy, vehicle);

        double lengthPath = time[g.getKey(vDest)];

        if (Double.MAX_VALUE - lengthPath > 0) {
            getPath(g, vOrig, vDest, vertices, pathKeys, shortPath, sectionpath);
            return lengthPath;
        }
        return -1;
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
                double cur_time = calcFastestTime(section, vehicle);
                if (!visited[kAdj] && time[kAdj] > (time[kOrg] + cur_time)) {
                    time[kAdj] = time[kOrg] + cur_time;
                    pathKeys[kAdj] = kOrg;
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

    
    
    private static double calcFastestTime(Section s, Vehicle car) {

        double time = 0;
        double final_time = 0;
        double work = 0;
        for (Segment segment : s.getSequenceOfSegments()) {
            double carMaxVel = PhysicsCalculus.calcMaximumVelocity(segment, car, s);
            double time_segment = (segment.getLength() * 1000) / (carMaxVel);
            final_time += time_segment;
//            }
        }
        return final_time;
    }
}
