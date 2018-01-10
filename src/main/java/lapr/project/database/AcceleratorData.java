package lapr.project.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lapr.project.model.Accelerator;
import lapr.project.model.Regime;
import lapr.project.model.Throttle;
import oracle.jdbc.OracleTypes;

public class AcceleratorData extends DataAccess<Accelerator> {

    public AcceleratorData(Connection connection) {
        super(connection);
    }

    public Accelerator get(String vehicleName) throws SQLException {
        if (connection == null) {
            return null;
        }
        Accelerator a = new Accelerator();
        Map<Integer, Throttle> throttleList = new LinkedHashMap<>();
        List<SQLArgument> args = new ArrayList<>();
        args.add(new SQLArgument(vehicleName, OracleTypes.VARCHAR));
        ResultSet rs = super.callFunction("getThrottle", args);

        while (rs.next()) {
            int id = rs.getInt("description");
            RegimeData r = new RegimeData(connection);
            List<Regime> regimeList = r.get(vehicleName, id);

            Throttle t = new Throttle(regimeList);
            throttleList.put(id, t);
        }
        a.setThrottleList(throttleList);
        return a;
    }

    public void insert(String pName, String vName, Accelerator accelerator) throws SQLException {
        List<SQLArgument> args = new ArrayList<>();

        args.add(new SQLArgument(pName, OracleTypes.VARCHAR));
        args.add(new SQLArgument(vName, OracleTypes.VARCHAR));
        ResultSet rs = super.callFunction("getAcceleratorByVehicleName", args);
        if (rs.next()) {
            rs.close();
            return;
        }
        super.callProcedure("insertAccelerator", args);

        RegimeData rd = new RegimeData(connection);

        for (Integer i : accelerator.getThrottleList().keySet()) {
            args.clear();
            args.add(new SQLArgument(pName, OracleTypes.VARCHAR));
            args.add(new SQLArgument(vName, OracleTypes.VARCHAR));
            args.add(new SQLArgument(Integer.toString(i), OracleTypes.NUMBER));
            super.callProcedure("insertThrottle", args);
            rd.insert(pName, i, vName, accelerator.getThrottleList().get(i));
        }
    }

}
