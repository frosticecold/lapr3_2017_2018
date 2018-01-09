/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import lapr.project.model.Junction;
import lapr.project.model.Project;
import lapr.project.model.Road;
import lapr.project.model.Vehicle;
import lapr.project.networkanalysis.AlgorithmResults;

public class ExportHTML implements Exportable {

    /**
     * Creates an instance of ExportHTML, which will serve as a HTML exporter.
     */
    public ExportHTML() {
    }

    public void exportAnalysisResult(AlgorithmResults results, String filePath) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append(openHTML());
        sb.append(results.toStringHTML());
        sb.append(closeHTML());
        writeFileHTML(sb, filePath);
    }
    
    public void exportListOfResults(List<AlgorithmResults> results, String filePath) throws IOException{
        StringBuilder sb = new StringBuilder();
        sb.append(openHTML());
        sb.append("<table>");
        sb.append("\t<tr>");
        for (AlgorithmResults result : results) {
            sb.append("<th>" + result.getVehicle().getName() + "</th>");
        }
        sb.append("</tr>");
        
        sb.append("<tr>");
        for (AlgorithmResults result : results) {
            sb.append("<td>"+ result.toStringHTML() + "</td>");
        }
        sb.append("</tr>");
        sb.append(closeHTML());
        writeFileHTML(sb, filePath);
    }

    public void exportProject(Project activeProject, String filePath) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append(openHTML());
        sb.append(projectToHTML(activeProject));
        sb.append(closeHTML());
        writeFileHTML(sb, filePath);
    }

    private String projectToHTML(Project activeProject) {
        StringBuilder sb = new StringBuilder();

        sb.append("<h1>Project</h1>");
        sb.append("<table>\n");
        sb.append("\t<tr><th>Vehicles</th></tr>\n");
        for (Vehicle vehicle : activeProject.getListVehicles().getVehicleList()) {
            sb.append("\t<tr>" + "<td>").append(vehicle.toStringHTML()).append("</td></tr>\n");
        }
        sb.append("</table>\n");

        sb.append("<h2>Road Network</h2>");
        sb.append("<table>\n");
        sb.append("\t<tr><th>Roads</th></tr>\n");
        for (Road road : activeProject.getListRoads()) {
            sb.append("\t<tr>" + "<td>").append(road.toStringHTML()).append("</td></tr>\n");
        }
        sb.append("</table>\n");
        sb.append("<br/>");
        sb.append("<table>\n");
        sb.append("\t<tr><th>Junctions</th></tr>\n");
        for (Junction junction : activeProject.getRoadNetwork().vertices()) {
            sb.append("\t<tr>" + "<td>").append(junction.toStringHTML()).append("</td></tr>\n");
        }
        sb.append("</table>\n");
        return sb.toString();
    }

    /**
     * String with the opening of an HTML file.
     *
     * @return Opening of an HTML.
     */
    private String openHTML() {
        return ("<!DOCTYPE html>\n<html>\n<head>\n"
                + "\t<title>LAPR3 - Analysis</title>\n"
                + "\t<style>\n"
                + "\t\tbody {\n"
                + "\t\t\tmargin: 50px;\n"
                + "\t\t}\n"
                + "\t\ttd, th {\n"
                + "\t\t\tborder: 1px solid #999;\n"
                + "\t\t\tpadding: 5px;\n"
                + "\t\t}\n"
                + "\t</style>\n"
                + "</head>\n<body>\n");
    }

    /**
     * String with the closure of an HTML file.
     *
     * @return Closure of an HTML.
     */
    private String closeHTML() {
        return ("</body>\n</html>\n");
    }

    /**
     * Writes the contents of a StringBuilder Object to an html.
     *
     * @param stringBuilder Object that contains the output format.
     * @param filePath Path where to export the file.
     */
    private void writeFileHTML(StringBuilder stringBuilder, String filePath) throws IOException {
        try {
            File file = new File(filePath);

            // If the file doesn't exist, create it
            if (!file.exists() && !file.createNewFile()) {
                return;

            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                bw.write(stringBuilder.toString());
            }

        } catch (IOException e) {
            throw new IllegalArgumentException("An error occured while"
                    + " attempting to export the HTML file.");
        } catch (NullPointerException e) {
            throw new NullPointerException("An error occured whule attempting to export the HTML file.");
        }
    }
}
