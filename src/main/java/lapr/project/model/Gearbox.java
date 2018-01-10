package lapr.project.model;

import java.util.LinkedList;
import java.util.List;

public class Gearbox {

    private List<Gear> gearList;

    public Gearbox() {
        this.gearList = new LinkedList<>();
    }

    public Gearbox(List<Gear> gearList) {
        this.gearList = gearList;
    }

    public Gearbox(Gearbox gea) {
        this.gearList = new LinkedList<>();
        for (Gear r : gea.gearList) {
            this.gearList.add(new Gear(r));
        }
    }

    /**
     *
     * @param g
     */
    public void addGear(Gear g) {
        gearList.add(g);
    }

    public double getLowestGearRatio() {
        double lowestGear = Double.POSITIVE_INFINITY;
        for (Gear g : gearList) {
            if (g.getRatio() < lowestGear) {
                lowestGear = g.getRatio();
            }
        }
        return lowestGear;
    }

    public Gear getLastGear() {
        int gear_index = Integer.MIN_VALUE;
        Gear gear = null;
        for (Gear g : gearList) {
            if (g.getGearID() > gear_index) {
                gear = g;
            }

        }
        return gear;
    }

    /**
     *
     *
     * @param gearID
     * @return
     */
    public Gear getGear(int gearID) {
        for (Gear gear : gearList) {
            if (gear.getGearID() == gearID) {
                return gear;
            }
        }
        return null;
    }

    public List<Gear> getGearList() {
        return gearList;
    }

    public int getNumberOfGears() {
        return this.gearList.size();
    }

    @Override
    public String toString() {
        return "Gearbox{" + "gears=" + gearList + '}';
    }
}
