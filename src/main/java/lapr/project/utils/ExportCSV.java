/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.util.Arrays;
import lapr.project.calculations.UnitConversion;
import lapr.project.model.AlgorithmResults;

/**
 *
 * @author Miguel Santos <1161386@isep.ipp.pt>
 */
public class ExportCSV {

    private String path;
    private final String format = ".csv";
    private AlgorithmResults results;

    public ExportCSV(AlgorithmResults results, String path) {
        this.path = path;
        this.results = results;
    }

    public void createFile() throws Exception {
        FileWriter writer = new FileWriter(path + format);

        CSVUtils.writeLine(writer, Arrays.asList("Project", "Path", "Vehicle", "Travel Time", "Cost", "Energy", "Distance"));
        CSVUtils.writeLine(writer, Arrays.asList("\n" + results.getProject().toString(), results.getSectionPath().toString(), results.getVehicle().toString(), UnitConversion.convertSecondstoHoursMinSec(results.getTravelTime()) + " h", String.valueOf(new DecimalFormat("#.##").format(results.getCost()) + " €"), String.valueOf(String.format("%.2f",results.getEnergy() / 1000000)) + " MJ", String.valueOf(results.getDistance()) + "km"), ',');

        writer.flush();
        writer.close();

    }
}
