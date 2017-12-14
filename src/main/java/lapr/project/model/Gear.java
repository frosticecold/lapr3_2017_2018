package lapr.project.model;

public class Gear {

    private String m_gearID;
    private double m_ratio;

    /**
     *
     * @param name_of_gear
     * @param m_ratio
     */
    public Gear(String id_of_gear, double m_ratio) {
        this.m_gearID = id_of_gear;
        this.m_ratio = m_ratio;
    }

    public void setGearID(String m_gearID) {
        this.m_gearID = m_gearID;
    }

    public void setRatio(double m_ratio) {
        this.m_ratio = m_ratio;
    }

    
    
    @Override
    public String toString() {
        return "Gear{" + "m_gearID=" + m_gearID + ", m_ratio=" + m_ratio + '}';
    }

}
