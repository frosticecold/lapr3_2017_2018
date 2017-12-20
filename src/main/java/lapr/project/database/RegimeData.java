package lapr.project.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import lapr.project.model.Regime;
import lapr.project.model.Throttle;
import oracle.jdbc.OracleTypes;

public class RegimeData extends DataAccess<Regime> {

    public RegimeData(Connection connection) {
        super(connection);
    }

    public List<Regime> get(String vehicleName) throws SQLException {
        if (connection == null) {
            return new ArrayList<>();
        }
        List<Regime> list = new LinkedList<>();
        List<SQLArgument> args = new ArrayList<>();
        args.add(new SQLArgument(vehicleName, OracleTypes.VARCHAR));
        ResultSet rs = super.callFunction("getRegime", args);
        while (rs.next()) {
            double vTorque = rs.getDouble("torque");
            double vRpmLow = rs.getDouble("rpm_low");
            double vRpmHigh = rs.getDouble("rpm_high");
            double vSFC = rs.getDouble("SFC");

            list.add(new Regime(vTorque, vRpmLow, vRpmHigh, vSFC));
        }
        return list;
    }

    public void insert(Integer throttleID, Throttle t) throws SQLException {
        List<SQLArgument> args = new ArrayList<>();

        args.add(new SQLArgument(Integer.toString(throttleID), OracleTypes.NUMBER));
        ResultSet rs = super.callFunction("getThrottleByID", args);
        if (rs.next()) {
            rs.close();
            return;
        }
        for (Regime r : t.getRegimeList()) {
            args.clear();
            args.add(new SQLArgument(Double.toString(r.getM_torque()), OracleTypes.NUMBER));
            args.add(new SQLArgument(Double.toString(r.getM_rpm_low()), OracleTypes.NUMBER));
            args.add(new SQLArgument(Double.toString(r.getM_rpm_high()), OracleTypes.NUMBER));
            args.add(new SQLArgument(Double.toString(r.getM_SFC()), OracleTypes.NUMBER));
            
            super.callProcedure("insertRegime", args);
        }
    }

}
