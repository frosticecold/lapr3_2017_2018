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

/**
 *
 * @author pc asus
 */
public class RegimeData extends DataAccess<Regime> {

    /**
     *
     * @param connection
     */
    public RegimeData(Connection connection) {
        super(connection);
    }

    /**
     *
     * @param vehicleID
     * @param t
     * @return
     * @throws SQLException
     */
    public List<Regime> get(int vehicleID, int t) throws SQLException {
        if (connection == null) {
            return new ArrayList<>();
        }
        List<Regime> list = new LinkedList<>();

        List<SQLArgument> args = new ArrayList<>();
        args.add(new SQLArgument(Integer.toString(vehicleID), OracleTypes.NUMBER));
        args.add(new SQLArgument(Integer.toString(t), OracleTypes.NUMBER));
        ResultSet rs = super.callFunction("getRegime", args);
        while (rs.next()) {
            double vTorqueLow = rs.getDouble("torque_low");
            double vTorqueHigh = rs.getDouble("torque_high");
            double vRpmLow = rs.getDouble("rpm_low");
            double vRpmHigh = rs.getDouble("rpm_high");
            double vSFC = rs.getDouble("SFC");

            list.add(new Regime(vTorqueLow, vTorqueHigh, vRpmLow, vRpmHigh, vSFC));
        }
        return list;
    }

    /**
     *
     * @param pName
     * @param throttleID
     * @param vName
     * @param t
     * @throws SQLException
     */
    public void insert(String pName, int throttleID, String vName, Throttle t) throws SQLException {
        List<SQLArgument> args = new ArrayList<>();
        for (int i = 0; i < t.getRegimeList().size(); i++) {
            Regime r = t.getRegimeList().get(i);
            args.clear();
            args.add(new SQLArgument(pName, OracleTypes.VARCHAR));
            args.add(new SQLArgument(vName, OracleTypes.VARCHAR));
            args.add(new SQLArgument(Integer.toString(throttleID), OracleTypes.NUMBER));
            args.add(new SQLArgument(Double.toString(r.getTorqueLow()), OracleTypes.NUMBER));
            args.add(new SQLArgument(Double.toString(r.getTorqueHigh()), OracleTypes.NUMBER));
            args.add(new SQLArgument(Double.toString(r.getRpmLow()), OracleTypes.NUMBER));
            args.add(new SQLArgument(Double.toString(r.getRpmHigh()), OracleTypes.NUMBER));
            args.add(new SQLArgument(Double.toString(r.getSFC()), OracleTypes.NUMBER));

            super.callProcedure("insertRegime", args);
        }
    }

}
