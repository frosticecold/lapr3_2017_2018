package lapr.project.controller;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.database.ProjectData;
import lapr.project.model.Project;
import lapr.project.utils.Session;

public class CopyProjectController {

    private Project proj;

    public void copyProject(String name) {

        Project p = new Project(proj);

        p.setName(name);

        ProjectData pd = new ProjectData(Session.getConnection().getConnection());

        try {
            pd.insertProject(p);
        } catch (SQLException ex) {
            Logger.getLogger(CopyProjectController.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        Session.setActiveProject(p);
    }

}
