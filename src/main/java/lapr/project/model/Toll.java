package lapr.project.model;

import java.util.HashMap;
import java.util.Map;

public class Toll {

    Map<Integer, Double> m_tollfare;

    public Toll() {
        m_tollfare = new HashMap<>();
    }

    public boolean addTollFare(int toll_class, double tollfare) {
        boolean added = false;
        if (!m_tollfare.containsKey(toll_class)) {
            m_tollfare.put(toll_class, tollfare);
            added = true;
        }
        return added;
    }

}
