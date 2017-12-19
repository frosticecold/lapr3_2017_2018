package lapr.project.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lapr.project.model.Accelerator;
import lapr.project.model.Regime;
import lapr.project.model.Throttle;
import oracle.jdbc.OracleTypes;

public class AcceleratorData extends DataAccess<Accelerator>{
    
    public AcceleratorData(Connection connection) {
        super(connection);
    }

    public Accelerator get(String v_name) throws SQLException {
        if(connection == null) {
            return null;
        }
        Accelerator a = new Accelerator();
        Map<Integer, Throttle> m_throttle_list = new HashMap<>();
        List<SQLArgument> args = new ArrayList<>();
        args.add(new SQLArgument(v_name, OracleTypes.VARCHAR));
        ResultSet rs = super.callFunction("getThrottle", args);
        
        while(rs.next()) {
            int id = rs.getInt("id");
            RegimeData r = new RegimeData(connection);
            List<Regime> m_regime_list = r.get(v_name);
            
            
            Throttle t = new Throttle(m_regime_list);
            m_throttle_list.put(id, t);
        }
        
        a.setThrottleList(m_throttle_list);
        return a;
    }
    
    

}
