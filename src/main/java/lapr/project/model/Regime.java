package lapr.project.model;

public class Regime {
    
    private double m_torque;
    private double m_rpm_low;
    private double m_rpm_high;
    private double m_SFC;


    public Regime(double m_torque, double m_rpm_low, double m_rpm_high, double m_SFC) {
        this.m_torque = m_torque;
        this.m_rpm_low = m_rpm_low;
        this.m_rpm_high = m_rpm_high;
        this.m_SFC = m_SFC;
    }
    
    public Regime() {
    }

    public void setTorque(double m_torque) {
        this.m_torque = m_torque;
    }

    public void setRpmLow(double m_rpm_low) {
        this.m_rpm_low = m_rpm_low;
    }

    public void setRpmHigh(double m_rpm_high) {
        this.m_rpm_high = m_rpm_high;
    }

    public void setSFC(double m_SFC) {
        this.m_SFC = m_SFC;
    }

    @Override
    public String toString() {
        return "Regime{" + "m_torque=" + m_torque + ", m_rpm_low=" + m_rpm_low + ", m_rpm_high=" + m_rpm_high + ", m_SFC=" + m_SFC + '}';
    }
    
    
    
}
