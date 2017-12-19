/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.List;
import lapr.project.model.Junction;
import lapr.project.model.Project;
import lapr.project.networkanalysis.NetworkAnalysis;
import lapr.project.utils.ExportHTML;
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
    
    /**
     * Exports the results of the selected vehicles into the specified path.
     * @param vehicles (List&lt;String&gt;) The list of vehicle names to export.
     * @param path (String) The file path.
     */
    public void exportHTML(List<String> vehicles,String path)
    {
        ExportHTML export = new ExportHTML();
        NetworkAnalysis networkAnalysis = new NetworkAnalysis(p);
        export.exportNetworkAnalysis(networkAnalysis, vehicles, path);
    }
}
