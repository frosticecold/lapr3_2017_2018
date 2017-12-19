package lapr.project.database;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import lapr.project.model.Accelerator;
import lapr.project.model.Throttle;

public class AcceleratorData extends DataAccess<Accelerator>{
    
    public AcceleratorData(Connection connection) {
        super(connection);
    }

    public Accelerator get(String v_name) {
        Accelerator a = new Accelerator();
        Map<Integer, Throttle> m_throttle_list = new HashMap<>();
        
        
        a.setThrottleList(m_throttle_list);
        return a;
    }
    
    

}
