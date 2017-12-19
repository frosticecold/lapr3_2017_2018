package lapr.project.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Gearbox {

    private List<Gear> m_gears;

    public Gearbox(List<Gear> m_gears) {
        this.m_gears = m_gears;
    }

    public Gearbox() {
        this.m_gears = new LinkedList<>();
    }

    public Gearbox(Gearbox g) {

        m_gears = new ArrayList<>();
        for (Gear gear : g.m_gears) {
            m_gears.add(gear.clone());
        }
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
     *
     * @param gearID
     * @return
     */
    public Gear getGear(int gearID) {
        for (Gear gear : m_gears) {
            if (gear.getGearID() == gearID) {
                return gear;
            }
        }
        return null;
    }

    public List<Gear> getGearList() {

        return m_gears;
    }

    @Override
    public String toString() {
        return "Gearbox{" + "m_gears=" + m_gears + '}';
    }

    public Gearbox clone() {

        return new Gearbox(this);
    }

}
