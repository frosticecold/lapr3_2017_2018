/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.io.IOException;
import lapr.project.model.Project;
import lapr.project.networkanalysis.AlgorithmResults;
import lapr.project.utils.ExportHTML;
import lapr.project.utils.Session;

/**
 *
 * @author MarioDias
 */
public class AlgorithmVehicleResultsController {

    private Project p;

    public AlgorithmVehicleResultsController() {
        this.p = Session.getActiveProject();
    }

    /**
     * Exports the results of the selected vehicles into the specified path.
     *
     * @param path (String) The file path.
     * @throws java.io.IOException
     */
    public void exportHTML(String path, AlgorithmResults result) throws IOException {
        ExportHTML export = new ExportHTML();
        export.exportAnalysisResult(result, path);
    }
}
