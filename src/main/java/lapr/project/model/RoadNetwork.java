package lapr.project.model;

import lapr.project.utils.graphbase.Graph;

public class RoadNetwork {

    private Graph<Road, Junction> m_graph;

    public RoadNetwork(boolean isDirected) {
        m_graph = new Graph<>(isDirected);
    }

    /**
     *
     * @param r
     */
    public void addRoad(Road r) {
        m_graph.insertVertex(r);
    }

    /**
     *
     * @param j
     */
    public void addJunction(Road road1, Road road2, Junction j, Double weight) {
        if (m_graph.getEdge(road1, road2) == null) {
            m_graph.insertEdge(road1, road2, j, weight);
        }
    }

}
