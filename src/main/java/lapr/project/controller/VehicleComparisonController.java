/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.List;
import lapr.project.model.Junction;
import lapr.project.model.Project;
import lapr.project.model.Vehicle;
import lapr.project.networkanalysis.AlgorithmResults;
import lapr.project.utils.Session;

/**
 *
 * @author Miguel Santos <1161386@isep.ipp.pt>
 */
public class VehicleComparisonController {
    
    private Project project;
    private AlgorithmResults results;
    public VehicleComparisonController(){
        this.project = Session.getActiveProject();
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
}
