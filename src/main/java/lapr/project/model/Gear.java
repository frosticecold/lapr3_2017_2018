package lapr.project.model;

public class Gear {

    private int id;
    private double ratio;

    /**
     *
     * @param id_of_gear
     * @param m_ratio
     */
    public Gear(int id_of_gear, double m_ratio) {
        this.id = id_of_gear;
        this.ratio = m_ratio;
    }

    public Gear() {
        id = 0;
        ratio = 0;
    }

    public Gear(Gear otherGear) {
        this.id = otherGear.id;
        this.ratio = otherGear.ratio;
    }

    public int getGearID() {
        return id;
    }

    public void setGearID(int m_gearID) {
        this.id = m_gearID;
    }

    public void setRatio(double m_ratio) {
        this.ratio = m_ratio;
    }

    public double getRatio() {
        return ratio;
    }

    @Override
    public String toString() {
        return "Gear{" + "m_gearID=" + id + ", m_ratio=" + ratio + '}';
    }
    
}
