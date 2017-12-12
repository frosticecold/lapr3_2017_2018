package lapr.project.model;


public class Combustion extends Motorization {

    private int m_lower_rpm_limit;
    private int m_max_rpm_limit;
    private int m_rpm;

    public Combustion(String name, int m_lower_rpm_limit, int m_max_rpm_limit, int rpm) {
        super(name);
        this.m_lower_rpm_limit = m_lower_rpm_limit;
        this.m_max_rpm_limit = m_max_rpm_limit;
        this.m_rpm = rpm;
    }

    /**
     *
     * @param type
     * @param rpm
     * @param l_rpm
     * @param m_rpm
     */
}
