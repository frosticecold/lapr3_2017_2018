/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.pathalgorithms;

import java.util.LinkedList;
import java.util.List;
import lapr.project.calculations.PhysicsCalculus;
import lapr.project.model.Junction;
import lapr.project.model.Section;
import lapr.project.model.Segment;
import lapr.project.model.Vehicle;
import lapr.project.utils.graphbase.Edge;
import lapr.project.utils.graphbase.Graph;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public class FastestPathAlgorithm implements PathAlgorithm {

    @Override
    public double bestPath(Graph<Junction, Section> graph, Junction start, Junction end, Vehicle v, LinkedList<Junction> path) {
        Graph<Junction, Section> graphTimeAsWeight = graphTimeAsWeight(graph, v);
        double time = shortestPath(graphTimeAsWeight, start, end, path);
        return time;
    }

    public Graph<Junction, Section> graphTimeAsWeight(Graph<Junction, Section> graph, Vehicle v) {
        Graph<Junction, Section> graphTime = graph.copyGraph();

        for (Edge<Junction, Section> edge : graphTime.edges()) {
            double time = 0;

            for (Segment segment : edge.getElement().getSequenceOfSegments()) {
                double velocity = PhysicsCalculus.getMaximumVelocityIn(segment, v, edge.getElement());
                time += (segment.getLength() * 1000) / velocity;
            }

            edge.setWeight(time);

        }

        return graphTime;
    }
    //shortest-path between voInf and vdInf

    public static <V, E> double shortestPath(Graph<V, E> g, V vOrig, V vDest, LinkedList<V> shortPath) {

        if (!g.validVertex(vOrig) || !g.validVertex(vDest)) {
            return -1;
        }
        boolean[] visited = new boolean[g.numVertices()];
        int[] pathKeys = new int[g.numVertices()];
        double[] dist = new double[g.numVertices()];
        V[] vertices = g.allkeyVerts();
        shortPath.clear();

        for (V v : g.vertices()) {
            dist[g.getKey(v)] = Double.MAX_VALUE;
            pathKeys[g.getKey(v)] = -1;
            visited[g.getKey(v)] = false;
        }

        shortestPathLength(g, vOrig, vertices, visited, pathKeys, dist);

        double lengthPath = dist[g.getKey(vDest)];

        if (Double.MAX_VALUE - lengthPath > 0) {
            getPath(g, vOrig, vDest, vertices, pathKeys, shortPath);
            return lengthPath;
        }
        return -1;
    }

    private static <V, E> void shortestPathLength(Graph<V, E> g, V vOrig, V[] vertices,
            boolean[] visited, int[] pathKeys, double[] dist) {

        int i = g.getKey(vOrig);
        dist[i] = 0;
        V vOrigin;
        while (i != -1) {

            vOrigin = vertices[i];
            int kOrg = g.getKey(vOrigin);
            visited[kOrg] = true;

            for (Edge<V, E> edg : g.outgoingEdges(vOrigin)) {
                V vAdj = g.opposite(vOrigin, edg);
                int kAdj = g.getKey(vAdj);
                if (!visited[kAdj] && dist[kAdj] > (dist[kOrg] + edg.getWeight())) {
                    dist[kAdj] = dist[kOrg] + edg.getWeight();
                    pathKeys[kAdj] = kOrg;
                }
            }
            i = getVertMinDist(dist, visited);
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
    private static <V, E> void getPath(Graph<V, E> g, V vOrig, V vDest, V[] verts, int[] pathKeys, LinkedList<V> path) {

        if (!vOrig.equals(vDest)) {
            path.push(vDest);

            int vKey = g.getKey(vDest);
            int prevVKey = pathKeys[vKey];
            V vDestin;
            vDestin = verts[prevVKey];

            getPath(g, vOrig, vDestin, verts, pathKeys, path);
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

}
