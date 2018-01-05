package lapr.project.controller;

import java.io.IOException;
import lapr.project.model.Project;
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
     * @param path (String) The file path.
     */
    public void exportHTML(String path) throws IOException {
        ExportHTML export = new ExportHTML();
        export.exportProject(this.getActiveProject(), path);
    }
}
