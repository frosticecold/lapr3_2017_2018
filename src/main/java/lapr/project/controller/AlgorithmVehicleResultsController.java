/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.io.IOException;
import java.util.List;
import lapr.project.networkanalysis.AlgorithmResults;
import lapr.project.utils.ExportHTML;

/**
 *
 * @author MarioDias
 */
public class AlgorithmVehicleResultsController {

    /**
     * Exports the results of the selected vehicles into the specified path.
     *
     * @param path (String) The file path.
     * @param results
     * @throws java.io.IOException
     */
    public void exportHTML(String path, List<AlgorithmResults> results) throws IOException {
        ExportHTML export = new ExportHTML();
        export.exportListOfResults(results, path);
    }
}
