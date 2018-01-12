/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.LinkedList;
import java.util.List;
import lapr.project.model.Junction;
import lapr.project.model.Project;
import lapr.project.model.Vehicle;
<<<<<<< HEAD
import lapr.project.networkanalysis.AlgorithmResults;
import lapr.project.model.FastestPathAlgorithm;
import lapr.project.model.PathAlgorithm;
=======
import lapr.project.model.AlgorithmResults;
import lapr.project.pathalgorithms.FastestPathAlgorithm;
import lapr.project.pathalgorithms.PathAlgorithm;
>>>>>>> 7c5420ed46e4ab92171dd0fd27424a9cf3fd2340
import lapr.project.model.TheoreticalEnergyEfficientAlgorithm;
import lapr.project.model.EnergySavingModeAlgorithm;
import lapr.project.utils.Session;

/**
 *
 * @author Miguel Santos <1161386@isep.ipp.pt>
 */
public class VehicleComparisonController {
    
    private Project project;
    private AlgorithmResults result;
    private List<AlgorithmResults> resultsList;
    
    public VehicleComparisonController(){
        this.project = Session.getActiveProject();
        this.resultsList = new LinkedList<>();
    }
    
    public String getProjectName(){
        return project.getName();
    }
    
    public Iterable<Vehicle> getProjectVehicleList(){
        return project.getListVehicles().getVehicleList();
    }
    
    public Iterable<Junction> getProjectJunctions(){
        return project.getRoadNetwork().vertices();
    }
    
    public void bestPath(boolean fastest, boolean efficient, boolean saving, Junction start, Junction end, Vehicle v, double acceleration) {
        if (fastest) {
            fastestPath(start, end, v);
        }
        if (efficient) {
            theoricalMostEnergyEfficientPath(start, end, v, acceleration);
        }
        if (saving) {
            mostEfficientPathInEnergySavingMode(start, end, v, acceleration);
        }
    }
    
    private void fastestPath(Junction start, Junction end, Vehicle v) {
        PathAlgorithm alg = new FastestPathAlgorithm();
        result = alg.bestPath(project.getRoadNetwork(), start, end, v, 0);
    }

    private void theoricalMostEnergyEfficientPath(Junction start, Junction end, Vehicle v, double acceleration) {
        PathAlgorithm alg = new TheoreticalEnergyEfficientAlgorithm();
        result = alg.bestPath(project.getRoadNetwork(), start, end, v, acceleration);

    }

    private void mostEfficientPathInEnergySavingMode(Junction start, Junction end, Vehicle v, double acceleration) {
        PathAlgorithm alg = new EnergySavingModeAlgorithm();
        result = alg.bestPath(project.getRoadNetwork(), start, end, v, acceleration);
    }

    public List<AlgorithmResults> getResultsList() {
        return resultsList;
    }
    
    public void addResult(){
        resultsList.add(result);
    }
}