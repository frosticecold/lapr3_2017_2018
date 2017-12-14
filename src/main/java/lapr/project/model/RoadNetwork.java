/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.List;
import lapr.project.utils.graphbase.Edge;
import lapr.project.utils.graphbase.Graph;

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

}
