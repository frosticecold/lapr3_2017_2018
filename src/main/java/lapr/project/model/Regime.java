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
        m_torque = 0.0;
        m_rpm_low = 0.0;
        m_rpm_high = 0.0;
        m_SFC = 0.0;
    }

    public Regime(Regime reg) {
        this.m_torque = reg.m_torque;
        this.m_rpm_low = reg.m_rpm_low;
        this.m_rpm_high = reg.m_rpm_high;
        this.m_SFC = reg.m_SFC;
    }

    public double getM_torque() {
        return m_torque;
    }

    public double getM_rpm_low() {
        return m_rpm_low;
    }

    public double getM_rpm_high() {
        return m_rpm_high;
    }

    public double getM_SFC() {
        return m_SFC;
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

    /**
     * Validate the regime.
     *
     * @return True if regime is valid else returns false.
     */
    public boolean validateRegime() {

        if (m_torque <= 0) {
            throw new IllegalArgumentException("Torque must be positive.");
        }

        if (m_rpm_low < 0) {
            throw new IllegalArgumentException("RPM low must be positive.");
        }

        if (m_rpm_high <= 0) {
            throw new IllegalArgumentException("RPM high must be positive.");
        }

        if (m_rpm_low > m_rpm_high) {
            throw new IllegalArgumentException("RPM low should be less than RPM high.");
        }

        if (m_SFC < 0) {
            throw new IllegalArgumentException("SFC must be positive.");
        }

        return true;
    }

    @Override
    public String toString() {
        return "Regime{" + "m_torque=" + m_torque + ", m_rpm_low=" + m_rpm_low + ", m_rpm_high=" + m_rpm_high + ", m_SFC=" + m_SFC + '}';
    }
}
