package lapr.project.model;

import java.util.ArrayList;
import java.util.List;

public class Gearbox {

    private List<Gear> m_gears;

    public Gearbox(List<Gear> m_gears) {
        this.m_gears = m_gears;
    }

    public Gearbox() {
    }

    /**
     *
     * @param g
     */
    public void addGear(Gear g) {
        m_gears.add(g);
    }

    /**
     *
     * @param nameOfGear
     */
    public Gear getGear(String nameOfGear) {
        for (Gear gear : m_gears) {
            if(gear.toString().equalsIgnoreCase(nameOfGear)){
                return gear;
            }
        }
        return null;
        }

}
