/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.pathalgorithms;

import java.util.LinkedList;
import java.util.List;
import lapr.project.model.Junction;
import lapr.project.model.Section;
import lapr.project.model.Vehicle;
import lapr.project.networkanalysis.AlgorithmResults;
import lapr.project.utils.graphbase.Graph;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public interface PathAlgorithm {

    AlgorithmResults bestPath(Graph<Junction, Section> graph, Junction start, Junction end, Vehicle v, LinkedList<Junction> path);

    String toString();

}
