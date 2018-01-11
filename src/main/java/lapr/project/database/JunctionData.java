package lapr.project.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lapr.project.model.Junction;
import oracle.jdbc.OracleTypes;

public class JunctionData extends DataAccess<Junction> {

    public JunctionData(Connection connection) {
        super(connection);
    }

    /**
     * Get the junctions with the specified id
     * 
     * @param junctionId Junction id
     * @return Junction
     * @throws SQLException
     */
    public Junction get(String junctionId) throws SQLException {
        if (connection == null) {
            return null;
        }
        List<SQLArgument> args = new ArrayList<>();
        args.add(new SQLArgument(junctionId, OracleTypes.NUMBER));
        ResultSet rs = super.callFunction("getJunction", args);

        if (rs.next()) {
            String junctionID = rs.getString("name");
            return new Junction(junctionID);
        }
        return null;
    }
    
    /**
     * Returns the list of junctions of a project in the database.
     *
     * @param pName Project name
     * @return List of the junctions of a project
     * @throws SQLException
     */
    public List<Junction> getAllJunctions(String pName) throws SQLException {
        if (connection == null) {
            return new ArrayList<>();
        }
        List<Junction> list = new ArrayList<>();
        List<SQLArgument> args = new ArrayList<>();
        args.add(new SQLArgument(pName, OracleTypes.NUMBER));
        ResultSet rs = super.callFunction("getAllJunctions", args);

        while (rs.next()) {
            String junctionID = rs.getString("name");
            list.add(new Junction(junctionID));
        }
        return list;
    }

    /**
     * Insert junction in the specified project
     *
     * @param pName Project name
     * @param j Junction
     * @throws SQLException
     */
    public void insert(String pName, Junction j) throws SQLException {
        List<SQLArgument> args = new ArrayList<>();

        args.add(new SQLArgument(pName, OracleTypes.VARCHAR));
        args.add(new SQLArgument(j.getName(), OracleTypes.VARCHAR));
        ResultSet rs = super.callFunction("getJunctionByName", args);
        if (rs.next()) {
            rs.close();
            return;
        }

        args.clear();
        args.add(new SQLArgument(pName, OracleTypes.VARCHAR));
        args.add(new SQLArgument(j.getName(), OracleTypes.VARCHAR));
        super.callProcedure("insertJunction", args);
    }

}
