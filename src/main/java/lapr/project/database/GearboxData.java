package lapr.project.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lapr.project.model.Gear;
import lapr.project.model.Gearbox;
import oracle.jdbc.OracleTypes;

public class GearboxData extends DataAccess<Gearbox>{
    
    public GearboxData(Connection connection) {
        super(connection);
    }

    public Gearbox get(String v_name) throws SQLException {
        if(connection == null) {
            return null;
        }
        Gearbox g = new Gearbox();
        List<SQLArgument> args = new ArrayList<>();
        args.add(new SQLArgument(v_name, OracleTypes.VARCHAR));
        ResultSet rs = super.callFunction("getGearbox", args);
        while(rs.next()) {
            int gearID = rs.getInt("id_gear");
            double ratio = rs.getDouble("ratio");
            g.addGear(new Gear(gearID, ratio));
        }
        return g;
    }
}
