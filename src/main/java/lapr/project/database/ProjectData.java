package lapr.project.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lapr.project.model.Project;
import oracle.jdbc.OracleTypes;

public class ProjectData extends DataAccess<Project> {

    public ProjectData(Connection connection) {
        super(connection);
    }

    public ArrayList<String> getAllProjectsNames() throws SQLException {
        if (connection == null) {
            return null;
        }

        ArrayList<String> list = new ArrayList<>();

        ResultSet rs = super.callFunction("getAllProjects");
        while (rs.next()) {
            String name = rs.getString("name");

            list.add(name);
        }
        return list;
    }

    public Project get(String name) throws SQLException {
        if (connection == null) {
            return null;
        }

        List<SQLArgument> args = new ArrayList<>();
        args.add(new SQLArgument(name, OracleTypes.VARCHAR));
        ResultSet rs = super.callFunction("getProject", args);
        Project p = null;
        while (rs.next()) {
            String description = rs.getString("description");
            p = new Project();
            
            p.setName(name);
            p.setDescription(description);
            
            
        }
        return p;
    }

}
