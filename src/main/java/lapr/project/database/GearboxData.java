package lapr.project.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import lapr.project.model.Gear;
import lapr.project.model.Gearbox;
import oracle.jdbc.OracleTypes;
import oracle.net.aso.g;

public class GearboxData extends DataAccess<Gearbox> {

    public GearboxData(Connection connection) {
        super(connection);
    }

    public Gearbox get(String vehicleName) throws SQLException {
        if (connection == null) {
            return null;
        }
        Gearbox g = new Gearbox();
        List<SQLArgument> args = new ArrayList<>();
        args.add(new SQLArgument(vehicleName, OracleTypes.VARCHAR));
        ResultSet rs = super.callFunction("getGearbox", args);
        while (rs.next()) {
            int gearID = rs.getInt("id_gear");
            double ratio = rs.getDouble("ratio");
            g.addGear(new Gear(gearID, ratio));
        }
        return g;
    }

    public void insert(String vName, Gearbox gearbox) throws SQLException {
        List<SQLArgument> args = new ArrayList<>();

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
            args.add(new SQLArgument(vName, OracleTypes.VARCHAR));
            args.add(new SQLArgument(Integer.toString(g.getGearID()), OracleTypes.NUMBER));
            args.add(new SQLArgument(Double.toString(g.getRatio()), OracleTypes.NUMBER));
            System.out.println(args);

            super.callProcedure("insertGear", args);
        }

    }
}
