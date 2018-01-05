/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.SQLException;
import java.util.List;
import lapr.project.model.Project;
import lapr.project.utils.DatabaseConnection;
import lapr.project.utils.SQLConnection;
import lapr.project.utils.Session;

/**
 *
 * @author Miguel Santos <1161386@isep.ipp.pt>
 */
public class DataBaseConnectionController {
    
    private SQLConnection sql;
    
    public DataBaseConnectionController(){
        this.sql = new DatabaseConnection().getDatabase();
    }
    
    private SQLConnection getSQLConnection() {
        if(sql == null) {
            sql = new DatabaseConnection().getDatabase();
            return sql;
        }
        return sql;
    }
    
    public List<String> getProjects() throws SQLException {
        return this.getSQLConnection().getProjects();
    }

    public void setActiveProject(String proj) throws SQLException {
        Project project = this.getSQLConnection().getProjectByName(proj);
        Session.setActiveProject(project);
    }
    
}
