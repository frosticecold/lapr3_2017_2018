package lapr.project.model.roadnetwork;

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
	}

	/**
	 * 
	 * @param j
	 */
	public void addJunction(Junction j) {
		// TODO - implement RoadNetwork.addJunction
		throw new UnsupportedOperationException();
	}

}