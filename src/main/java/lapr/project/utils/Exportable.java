/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import java.util.List;
import lapr.project.networkanalysis.NetworkAnalysis;

interface Exportable {
   
    /**
     * Exports the data contained in open project in simulator, to file HTML
     *
     * @param networkAnalysis Export the results from the network analysis.
     * @param filePath Path to the file.
     */
    public void exportNetworkAnalysis(NetworkAnalysis networkAnalysis, List<String> vehicles, String filePath);

}
