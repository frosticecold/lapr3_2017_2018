package lapr.project.model;

public class Regime {

    private double torqueLow;
    private double torqueHigh;
    private double rpmLow;
    private double rpmHigh;
    private double sfc;

    public Regime(double torqueLow, double torqueHigh, double rpmlow, double rpmhigh, double sfc) {
        this.torqueLow = torqueLow;
        this.torqueHigh = torqueHigh;
        this.rpmLow = rpmlow;
        this.rpmHigh = rpmhigh;
        this.sfc = sfc;
    }

    public Regime() {
        torqueLow = 0.0;
        torqueHigh = 0.0;
        rpmLow = 0.0;
        rpmHigh = 0.0;
        sfc = 0.0;
    }

    public Regime(Regime reg) {
        this.torqueHigh = reg.torqueHigh;
        this.torqueLow = reg.torqueLow;
        this.rpmLow = reg.rpmLow;
        this.rpmHigh = reg.rpmHigh;
        this.sfc = reg.sfc;
    }

    public double getTorqueLow() {
        return torqueLow;
    }

    public double getTorqueHigh() {
        return torqueHigh;
    }

    public double getRpmLow() {
        return rpmLow;
    }

    public double getRpmHigh() {
        return rpmHigh;
    }

    public double getSFC() {
        return sfc;
    }

    public double getTorqueByRPM(double rpm) {
        if (rpm < rpmLow || rpm > rpmHigh) {
            throw new IllegalArgumentException("Invalid rpm");
        }
        if (Math.abs(rpm - rpmLow) < 0.0000001) {
            return torqueLow;
        }
        if (rpm > rpmLow && rpm < rpmHigh) {
            return (torqueHigh + torqueLow) / 2;

        }
        if (Math.abs(rpm - rpmHigh) < 0.0000001) {
            return torqueHigh;
        }
        return -1;
    }

    public double getSFCByRPM(double rpm) {
        if (rpm < rpmLow || rpm > rpmHigh) {
            throw new IllegalArgumentException("Invalid rpm");
        }

        if (rpm >= rpmLow && rpm <= rpmHigh) {
            return this.sfc;

        }

        return -1;
    }

    public void setRpmLow(double rplow) {
        this.rpmLow = rplow;
    }

    public void setRpmHigh(double rphigh) {
        this.rpmHigh = rphigh;
    }

    public void setSFC(double sfc) {
        this.sfc = sfc;
    }

    public void setTorqueLow(double torqueLow) {
        this.torqueLow = torqueLow;
    }

    public void setTorqueHigh(double torqueHigh) {
        this.torqueHigh = torqueHigh;
    }

    /**
     * Validate the regime.
     *
     * @return True if regime is valid else returns false.
     */
    public boolean validateRegime() {

        if (torqueHigh <= 0) {
            throw new IllegalArgumentException("Torque high must be positive.");
        }

        if (torqueLow <= 0) {
            throw new IllegalArgumentException("Torque low must be positive.");
        }

        if (rpmLow < 0) {
            throw new IllegalArgumentException("RPM low must be positive.");
        }

        if (rpmHigh <= 0) {
            throw new IllegalArgumentException("RPM high must be positive.");
        }

        if (torqueLow > torqueHigh) {
            throw new IllegalArgumentException("Torque low should be less than torque high.");
        }

        if (rpmLow > rpmHigh) {
            throw new IllegalArgumentException("RPM low should be less than RPM high.");
        }

        if (sfc < 0) {
            throw new IllegalArgumentException("SFC must be positive.");
        }

        return true;
    }

    @Override
    public String toString() {
        return "Regime{" + "torque_low=" + torqueLow + ", torque_high=" + torqueHigh + ", rplow=" + rpmLow + ", rphigh=" + rpmHigh + ", SFC=" + sfc + '}';
    }

}
