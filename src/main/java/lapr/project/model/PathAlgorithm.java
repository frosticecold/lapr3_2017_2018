/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import lapr.project.model.Junction;
import lapr.project.model.Section;
import lapr.project.model.Vehicle;
import lapr.project.model.AlgorithmResults;
import lapr.project.utils.graphbase.Graph;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public interface PathAlgorithm {

    public AlgorithmResults bestPath(Graph<Junction, Section> graph, Junction start, Junction end, Vehicle v,double acceleration);

    public String toString();

}
