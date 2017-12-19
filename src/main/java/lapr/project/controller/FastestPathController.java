/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.model.Junction;
import lapr.project.model.Project;
import lapr.project.utils.Session;

public class FastestPathController {
    
    private Project p;

    public FastestPathController() {
        this.p = Session.getActiveProject();
    }
    
    public void fastestPath(Junction begin, Junction end){
     
    }
    
    public Iterable<Junction> getJunctions(){
        return p.getRoadNetwork().vertices();
    }
}
