package lapr.project.controller;

import lapr.project.utils.DatabaseConnection;
import lapr.project.utils.SQLConnection;
import lapr.project.utils.Session;

public class CopyProjectController {

    private SQLConnection sql;

    public CopyProjectController() {
        this.sql = new DatabaseConnection().getDatabase();
    }

    public void getActiveProject() {
        Session.getActiveProject();
    }

}
