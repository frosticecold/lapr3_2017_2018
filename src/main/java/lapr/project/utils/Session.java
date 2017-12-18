package lapr.project.utils;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.model.Project;

public class Session {

    private static Project activeProject;
    private static SQLConnection sql;

    public Project getActiveProject() {
        return activeProject;
    }

    public void setActiveProject(Project project) {
        activeProject = project;
    }

    public static SQLConnection getConnection() {
        if (sql == null) {
            DatabaseConnection dbc = new DatabaseConnection();
            sql = dbc.getDatabase();
            try {
                sql.openConnection();
            } catch (SQLException ex) {
                Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
        return sql;
    }

}
