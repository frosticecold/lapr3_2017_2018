package lapr.project.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lapr.project.model.Gear;
import lapr.project.model.Gearbox;
import oracle.jdbc.OracleTypes;

public class GearboxData extends DataAccess<Gearbox> {

    public GearboxData(Connection connection) {
        super(connection);
    }

    /**
     * Get the gearbox of a specified vehicle
     * 
     * @param vehicleID Id of the specified vehicle
     * @return Gearbox of the vehicle
     * @throws SQLException
     */
    public Gearbox get(int vehicleID) throws SQLException {
        if (connection == null) {
            return null;
        }
        Gearbox g = new Gearbox();
        List<SQLArgument> args = new ArrayList<>();
        args.add(new SQLArgument(Integer.toString(vehicleID), OracleTypes.NUMBER));
        ResultSet rs = super.callFunction("getGearbox", args);
        while (rs.next()) {
            int gearID = rs.getInt("id_gear");
            double ratio = rs.getDouble("ratio");
            g.addGear(new Gear(gearID, ratio));
        }
        return g;
    }

    /**
     * Inserts the gearbox for a specified vehicle in the project
     * 
     * @param pName Project name
     * @param vName Vehicle name
     * @param gearbox Gearbox
     * @throws SQLException
     */
    public void insert(String pName, String vName, Gearbox gearbox) throws SQLException {
        List<SQLArgument> args = new ArrayList<>();

        args.add(new SQLArgument(pName, OracleTypes.VARCHAR));
        args.add(new SQLArgument(vName, OracleTypes.VARCHAR));
        ResultSet rs = super.callFunction("getGearboxByVehicleName", args);
        if (rs.next()) {
            rs.close();
            return;
        }
        super.callProcedure("insertGearbox", args);

        for (int i = 1; i <= gearbox.getNumberOfGears(); i++) {
            Gear g = gearbox.getGear(i);
            args.clear();
            args.add(new SQLArgument(pName, OracleTypes.VARCHAR));
            args.add(new SQLArgument(vName, OracleTypes.VARCHAR));
            args.add(new SQLArgument(Integer.toString(g.getGearID()), OracleTypes.NUMBER));
            args.add(new SQLArgument(Double.toString(g.getRatio()), OracleTypes.NUMBER));

            super.callProcedure("insertGear", args);
        }

    }
}
