package lapr.project.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import lapr.project.model.Regime;
import oracle.jdbc.OracleTypes;

public class RegimeData extends DataAccess<Regime> {

    public RegimeData(Connection connection) {
        super(connection);
    }

    public List<Regime> get(String v_name) throws SQLException {
        if (connection == null) {
            return null;
        }
        List<Regime> list = new LinkedList<>();
        List<SQLArgument> args = new ArrayList<>();
        args.add(new SQLArgument(v_name, OracleTypes.VARCHAR));
        ResultSet rs = super.callFunction("getRegime", args);
        while (rs.next()) {
            double v_torque = rs.getDouble("torque");
            double v_rpm_low = rs.getDouble("rpm_low");
            double v_rpm_high = rs.getDouble("rpm_high");
            double v_SFC = rs.getDouble("SFC");
            
            list.add(new Regime(v_torque, v_rpm_low, v_rpm_high, v_SFC));
        }
        return list;
    }

}
