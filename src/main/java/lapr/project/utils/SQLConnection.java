package lapr.project.utils;

import java.sql.*;
import java.util.ArrayList;
import lapr.project.database.ProjectData;
import lapr.project.model.Project;
import oracle.jdbc.pool.OracleDataSource;

public class SQLConnection {

    private final String host;
    private final String username;
    private final String password;
    private Connection connection;

    private CallableStatement callStmt;

    public SQLConnection(String host, String username, String password) {
        if (host == null || username == null || password == null) {
            throw new NullPointerException("Please insert the host url, username and password.");
        }
        this.host = host;
        this.username = username;
        this.password = password;
    }

    public void openConnection() throws SQLException {
        OracleDataSource ds = new OracleDataSource();
        ds.setURL(host);
        connection = ds.getConnection(username, password);
    }

    public void closeAll() throws SQLException {
        if (callStmt != null) {
            callStmt.close();
            callStmt = null;
        }
        if (connection != null) {
            connection.close();
            connection = null;
        }
    }

    public ArrayList<String> getProjects() throws SQLException {
        if (connection == null) {
            openConnection();
        }
        ProjectData pd = new ProjectData(connection);
        
        return pd.getAllProjectsNames();
    }
    
    public Project getProjectByName(String name) throws SQLException {
        if (connection == null) {
            openConnection();
        }
        ProjectData p = new ProjectData(connection);

        return p.get(name);
    }

}
