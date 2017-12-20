package lapr.project.controller;

import java.util.List;
import lapr.project.model.Project;
import lapr.project.networkanalysis.NetworkAnalysis;
import lapr.project.utils.ExportHTML;
import lapr.project.utils.Session;

public class MockUpController {

    public MockUpController() {
        
    }

    public Project getActiveProject() {
        return Session.getActiveProject();
    }
    
    /**
     * Exports the results of the selected vehicles into the specified path.
     *
     * @param vehicles (List&lt;String&gt;) The list of vehicle names to export.
     * @param path (String) The file path.
     */
    public void exportHTML(String path) {
        ExportHTML export = new ExportHTML();
        export.exportProject(this.getActiveProject(), path);
    }
}
