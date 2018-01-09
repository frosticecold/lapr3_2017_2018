package lapr.project.model;

public class Gear {


    private int id;
    private double ratio;

    /**
     *
     * @param idOfGear
     * @param ratio
     */
    public Gear(int idOfGear, double ratio) {
        this.id = idOfGear;
        this.ratio = ratio;
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

    public void setGearID(int gearID) {
        this.id = gearID;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }

    public double getRatio() {
        return ratio;
    }

    @Override
    public String toString() {
        return "Gear{" + "gearID=" + id + ", ratio=" + ratio + '}';
    }
    
}
