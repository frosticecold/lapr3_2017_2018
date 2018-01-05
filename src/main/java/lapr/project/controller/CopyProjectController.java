package lapr.project.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.database.ProjectData;
import lapr.project.model.Project;
import lapr.project.utils.Session;

public class CopyProjectController {

    private Project proj;

    public boolean copyProject(String name) {

        try {
            ProjectData pd = new ProjectData(Session.getConnection().getConnection());
            List<String> projectNames = pd.getAllProjectsNames();
            Project p = Session.getActiveProject();

            if (!projectNames.contains(name)) {
                p.setName(name);
                p.getResults().clearList();

                pd.insertProject(p);

                Session.setActiveProject(p);
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CopyProjectController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public Project getActiveProject() {
        return proj;
    }
}
