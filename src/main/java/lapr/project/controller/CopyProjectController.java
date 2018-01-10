package lapr.project.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.database.ProjectData;
import lapr.project.model.Project;
import lapr.project.utils.Session;

public class CopyProjectController {

    public boolean copyProject(String name) {

        Project p = Session.getActiveProject();
        ProjectData pd = new ProjectData(Session.getConnection().getConnection());
        try {
            List<String> projectNames = pd.getAllProjectsNames();

            if (!projectNames.contains(name)) {
                p.setName(name);
                p.getResults().clearList();

                pd.insertProject(p);
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CopyProjectController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        Session.setActiveProject(p);
        return true;
    }
}
