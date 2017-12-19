package lapr.project.controller;

import java.sql.SQLException;
import java.util.List;
import lapr.project.model.Project;
import lapr.project.utils.DatabaseConnection;
import lapr.project.utils.SQLConnection;
import lapr.project.utils.Session;

public class OpenProjectController {

    private SQLConnection sql;

    public OpenProjectController() {
        this.sql = new DatabaseConnection().getDatabase();
    }

    private SQLConnection getSQLConnection() {
        return sql == null ? sql = new DatabaseConnection().getDatabase() : sql;
    }

    public List<String> getProjects() throws SQLException {
        return this.getSQLConnection().getProjects();
    }

    public void setActiveProject(String proj) throws SQLException {
        Project project = this.getSQLConnection().getProjectByName(proj);
        Session.setActiveProject(project);
    }

}
