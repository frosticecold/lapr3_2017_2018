/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import lapr.project.utils.graphbase.Edge;
import lapr.project.utils.graphbase.Graph;
import lapr.project.utils.graphbase.GraphAlgorithms;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public class RoadNetwork {

    private String m_id;
    private String m_description;
    private Graph<Junction, Section> m_graph;
    private List<Road> m_listOfRoads;

    public RoadNetwork(String id, String description, Graph<Junction, Section> graph, List<Road> listOfRoads) {
        this.m_id = id;
        this.m_description = description;
        this.m_graph = graph;
        this.m_listOfRoads = listOfRoads;
    }

    public RoadNetwork(String id, String description) {
        m_id = id;
        m_description = description;
        m_graph = new Graph<>(true);
        m_listOfRoads = new ArrayList<>();

    }

    public boolean addRoad(Road r) {
        boolean added = false;
        if (!m_listOfRoads.contains(r)) {
            added = m_listOfRoads.add(r);
        }
        return added;
    }

    public boolean addJunction(Junction j) {
        return m_graph.insertVertex(j);
    }

    public boolean addSection(Section s) {
        Junction orig = s.getBeginningJunction();
        Junction dest = s.getEndingJunction();
        if (orig == null || dest == null) {
            return false;
        }
        if (!m_graph.validVertex(orig) || !m_graph.validVertex(dest)) {
            return false;
        }

        for (Edge<Junction, Section> edge : m_graph.edges()) {
            if (edge.getElement().equals(s)) {
                return false;
            }
        }

        if (s.getDirection() == Section.Direction.REVERSE) {
            orig = s.getEndingJunction();
            dest = s.getBeginningJunction();
            return m_graph.insertEdge(orig, dest, s, s.getSectionLength());
        }
        if (s.getDirection() == Section.Direction.BIDIRECTIONAL) {
            m_graph.insertEdge(orig, dest, s, s.getSectionLength());
            m_graph.insertEdge(dest, orig, s, s.getSectionLength());
            return true;
        }

        return m_graph.insertEdge(orig, dest, s, s.getSectionLength());

    }

    public Junction getJunction(String junction_id) {
        for (Junction j : m_graph.vertices()) {
            if (j.getID().equalsIgnoreCase(junction_id)) {
                return j;
            }
        }
        return null;
    }

    public Road getRoadByRoadID(String road_id) {
        for (Road r : m_listOfRoads) {
            if (r.getRoadID().equals(road_id)) {
                return r;
            }
        }
        return null;
    }

    /**
     * Method that returns all paths from one junction source to another
     * junction destiny
     *
     * @param source Junction source
     * @param target Junction target
     * @return
     */
    public ArrayList<LinkedList<Junction>> allPaths(Junction source, Junction target) {
        return GraphAlgorithms.allPaths(m_graph, source, target);
    }

    @Override
    public String toString() {
        return "RoadNetwork{" + "m_id=" + m_id + ", m_description=" + m_description + ", m_graph=" + m_graph + ", m_listOfRoads=" + m_listOfRoads + '}';
    }

}
