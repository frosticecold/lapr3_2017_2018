package lapr.project.model;

import java.util.List;

public class Throttle {
    
    private int m_throttle_id;
    private List<Regime> m_regime_list;

    public Throttle(int m_throttle_id, List<Regime> m_regime_list) {
        this.m_throttle_id = m_throttle_id;
        this.m_regime_list = m_regime_list;
    }

    public Throttle() {
    }

    public void setM_throttle_id(int m_throttle_id) {
        this.m_throttle_id = m_throttle_id;
    }

    public void setM_regime_list(List<Regime> m_regime_list) {
        this.m_regime_list = m_regime_list;
    }

    @Override
    public String toString() {
        return "Throttle{" + "m_throttle_id=" + m_throttle_id + ", m_regime_list=" + m_regime_list + '}';
    }
    
    
}
