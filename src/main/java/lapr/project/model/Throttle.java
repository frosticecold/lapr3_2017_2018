package lapr.project.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Throttle {

    private List<Regime> regimeList;

    public Throttle(List<Regime> regimeList) {
        this.regimeList = regimeList;
    }

    public Throttle(Throttle thr) {
        this.regimeList = new ArrayList<>();
        for (Regime r : thr.regimeList) {
            this.regimeList.add(new Regime(r));
        }
    }

    public Throttle() {
        regimeList = new LinkedList<>();
    }

    public List<Regime> getRegimeList() {
        return regimeList;
    }

    public double getTorqueByRPM(double rpm) {
        for (Regime r : regimeList) {
            if (rpm >= r.getRpmLow() && rpm <= r.getRpmHigh()) {
                return r.getTorqueByRPM(rpm);
            }
        }
        return -1;
    }

    public double getSFCByRPM(double rpm) {
        for (Regime r : regimeList) {
            if (rpm >= r.getRpmLow() && rpm <= r.getRpmHigh()) {
                return r.getSFCByRPM(rpm);
            }
        }
        return -1;

    }

    public void setRegimeList(List<Regime> regimeList) {
        this.regimeList = regimeList;
    }

    @Override
    public String toString() {
        return "Throttle{" + "regime_list=" + regimeList + '}';
    }
}
