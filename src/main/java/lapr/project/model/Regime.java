package lapr.project.model;

public class Regime {

    private double m_torque_low;
    private double m_torque_high;
    private double m_rpm_low;
    private double m_rpm_high;
    private double m_SFC;

    public Regime(double m_torque_low, double m_torque_high, double m_rpm_low, double m_rpm_high, double m_SFC) {
        this.m_torque_low = m_torque_low;
        this.m_torque_high = m_torque_high;
        this.m_rpm_low = m_rpm_low;
        this.m_rpm_high = m_rpm_high;
        this.m_SFC = m_SFC;
    }

    public Regime() {
        m_torque_low = 0.0;
        m_torque_high = 0.0;
        m_rpm_low = 0.0;
        m_rpm_high = 0.0;
        m_SFC = 0.0;
    }

    public Regime(Regime reg) {
        this.m_torque_high = reg.m_torque_high;
        this.m_torque_low = reg.m_torque_low;
        this.m_rpm_low = reg.m_rpm_low;
        this.m_rpm_high = reg.m_rpm_high;
        this.m_SFC = reg.m_SFC;
    }

    public double getTorqueLow() {
        return m_torque_low;
    }

    public double getTorqueHigh() {
        return m_torque_high;
    }

    public void setTorqueLow(double m_torque_low) {
        this.m_torque_low = m_torque_low;
    }

    public void setTorqueHigh(double m_torque_high) {
        this.m_torque_high = m_torque_high;
    }

    public double getRpmLow() {
        return m_rpm_low;
    }

    public double getRpmHigh() {
        return m_rpm_high;
    }

    public double getSFC() {
        return m_SFC;
    }

    public double getTorqueByRPM(double rpm) {
        if (rpm < m_rpm_low || rpm > m_torque_high) {
            throw new IllegalArgumentException("Invalid rpm");
        }
        if (rpm == m_rpm_low) {
            return m_rpm_low;
        }
        if (rpm > m_rpm_low && rpm < m_rpm_high) {
            return (m_torque_high + m_torque_low) / 2;

        }
        if (rpm == m_rpm_high) {
            return m_rpm_high;
        }
        return -1;
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

        if (m_torque_high <= 0) {
            throw new IllegalArgumentException("Torque high must be positive.");
        }

        if (m_torque_low <= 0) {
            throw new IllegalArgumentException("Torque low must be positive.");
        }

        if (m_rpm_low < 0) {
            throw new IllegalArgumentException("RPM low must be positive.");
        }

        if (m_rpm_high <= 0) {
            throw new IllegalArgumentException("RPM high must be positive.");
        }

        if (m_torque_low > m_torque_high) {
            throw new IllegalArgumentException("Torque low should be less than torque high.");
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
        return "Regime{" + "m_torque_low=" + m_torque_low + ", m_torque_high=" + m_torque_high + ", m_rpm_low=" + m_rpm_low + ", m_rpm_high=" + m_rpm_high + ", m_SFC=" + m_SFC + '}';
    }

}
