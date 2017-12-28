package lapr.project.model;

public class Regime {

    private double torque_low;
    private double torque_high;
    private double rplow;
    private double rphigh;
    private double SFC;

    public Regime(double torque_low, double torque_high, double rplow, double rphigh, double SFC) {
        this.torque_low = torque_low;
        this.torque_high = torque_high;
        this.rplow = rplow;
        this.rphigh = rphigh;
        this.SFC = SFC;
    }

    public Regime() {
        torque_low = 0.0;
        torque_high = 0.0;
        rplow = 0.0;
        rphigh = 0.0;
        SFC = 0.0;
    }

    public Regime(Regime reg) {
        this.torque_high = reg.torque_high;
        this.torque_low = reg.torque_low;
        this.rplow = reg.rplow;
        this.rphigh = reg.rphigh;
        this.SFC = reg.SFC;
    }

    public double getTorqueLow() {
        return torque_low;
    }

    public double getTorqueHigh() {
        return torque_high;
    }

    public void setTorqueLow(double torque_low) {
        this.torque_low = torque_low;
    }

    public void setTorqueHigh(double torque_high) {
        this.torque_high = torque_high;
    }

    public double getRpmLow() {
        return rplow;
    }

    public double getRpmHigh() {
        return rphigh;
    }

    public double getSFC() {
        return SFC;
    }

    public double getTorqueByRPM(double rpm) {
        if (rpm < rplow || rpm > rphigh) {
            throw new IllegalArgumentException("Invalid rpm");
        }
        if (Math.abs(rpm - rplow) < 0.0000001) {
            return torque_low;
        }
        if (rpm > rplow && rpm < rphigh) {
            return (torque_high + torque_low) / 2;

        }
        if (Math.abs(rpm - rphigh) < 0.0000001) {
            return torque_high;
        }
        return -1;
    }

    public double getSFCByRPM(double rpm) {
        if (rpm < rplow || rpm > rphigh) {
            throw new IllegalArgumentException("Invalid rpm");
        }

        if (rpm >= rplow && rpm <= rphigh) {
            return this.SFC;

        }

        return -1;
    }

    public void setRpmLow(double rplow) {
        this.rplow = rplow;
    }

    public void setRpmHigh(double rphigh) {
        this.rphigh = rphigh;
    }

    public void setSFC(double SFC) {
        this.SFC = SFC;
    }

    /**
     * Validate the regime.
     *
     * @return True if regime is valid else returns false.
     */
    public boolean validateRegime() {

        if (torque_high <= 0) {
            throw new IllegalArgumentException("Torque high must be positive.");
        }

        if (torque_low <= 0) {
            throw new IllegalArgumentException("Torque low must be positive.");
        }

        if (rplow < 0) {
            throw new IllegalArgumentException("RPM low must be positive.");
        }

        if (rphigh <= 0) {
            throw new IllegalArgumentException("RPM high must be positive.");
        }

        if (torque_low > torque_high) {
            throw new IllegalArgumentException("Torque low should be less than torque high.");
        }

        if (rplow > rphigh) {
            throw new IllegalArgumentException("RPM low should be less than RPM high.");
        }

        if (SFC < 0) {
            throw new IllegalArgumentException("SFC must be positive.");
        }

        return true;
    }

    @Override
    public String toString() {
        return "Regime{" + "torque_low=" + torque_low + ", torque_high=" + torque_high + ", rplow=" + rplow + ", rphigh=" + rphigh + ", SFC=" + SFC + '}';
    }

}
