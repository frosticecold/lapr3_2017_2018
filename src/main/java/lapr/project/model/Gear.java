package lapr.project.model;

public class Gear {

    private int m_gearID;
    private double m_ratio;

    /**
     *
     * @param id_of_gear
     * @param m_ratio
     */
    public Gear(int id_of_gear, double m_ratio) {
        this.m_gearID = id_of_gear;
        this.m_ratio = m_ratio;
    }

    public Gear() {
        m_gearID = 0;
        m_ratio = 0;
    }

    public Gear(Gear otherGear) {
        this.m_gearID = otherGear.m_gearID;
        this.m_ratio = otherGear.m_ratio;
    }

    public int getGearID() {
        return m_gearID;
    }

    public void setGearID(int m_gearID) {
        this.m_gearID = m_gearID;
    }

    public void setRatio(double m_ratio) {
        this.m_ratio = m_ratio;
    }

    public double getM_ratio() {
        return m_ratio;
    }

    @Override
    public String toString() {
        return "Gear{" + "m_gearID=" + m_gearID + ", m_ratio=" + m_ratio + '}';
    }
}
