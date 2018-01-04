/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import lapr.project.model.Junction;
import lapr.project.model.ListOfResults;
import lapr.project.model.Project;
import lapr.project.model.Vehicle;
import lapr.project.networkanalysis.AlgorithmResults;
import lapr.project.pathalgorithms.FastestPathAlgorithm;
import lapr.project.pathalgorithms.PathAlgorithm;
import lapr.project.utils.ExportCSV;
import lapr.project.utils.ExportHTML;
import lapr.project.utils.Session;

public class PathAlgorithmsController {

    private Project p;
    private AlgorithmResults result;
    private ListOfResults listResults;

    public PathAlgorithmsController() {
        this.p = Session.getActiveProject();
        listResults = new ListOfResults();
    }

    public Iterable<Junction> getJunctions() {
        return p.getRoadNetwork().vertices();
    }

    public Iterable<Vehicle> getVehicles() {
        return p.getListVehicles().getVehicleList();
    }

    public AlgorithmResults getResults() {
        return result;
    }

    public String getResultsAsText() {
        return result.toString();
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

    public void fastestPath(Junction start, Junction end, Vehicle v) {
        PathAlgorithm alg = new FastestPathAlgorithm();
        result = alg.bestPath(p.getRoadNetwork(), start, end, v, 0);
        listResults.addResult(v, result);
    }

    public void theoricalMostEnergyEfficientPath(Junction start, Junction end, Vehicle v, double acceleration) {
        PathAlgorithm alg = new FastestPathAlgorithm();
        result = alg.bestPath(p.getRoadNetwork(), start, end, v, acceleration);
        listResults.addResult(v, result);
    }

    public void mostEfficientPathInEnergySavingMode(Junction start, Junction end, Vehicle v, double acceleration) {
        PathAlgorithm alg = new FastestPathAlgorithm();
        result = alg.bestPath(p.getRoadNetwork(), start, end, v, acceleration);
        listResults.addResult(v, result);
    }

    /**
     * Exports the results of the selected vehicles into the specified path.
     *
     * @param path (String) The file path.
     */
    public void exportHTML(String path) throws IOException {
        ExportHTML export = new ExportHTML();
        export.exportAnalysisResult(result, path);
    }

    public void exportCSV(String path) throws Exception {
        ExportCSV export = new ExportCSV(result, path);
        export.createFile();
    }
}
