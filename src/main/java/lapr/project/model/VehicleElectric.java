package lapr.project.model;

public class VehicleElectric extends Vehicle {

    /**
     * Minimum RPM of the engine of the vehicle.
     */
    private double minRPM;

    /**
     * Maximum RPM of the engine of the vehicle.
     */
    private double maxRPM;

    /**
     * Final drive ratio of the vehicle.
     */
    private double finalDriveRatio;

    /**
     * Gear box of the vehicle.
     */
    private Gearbox gearbox;

    /**
     * List of possible throtlles of the vehicle.
     */
    private Accelerator acceleratorPedal;
    /**
     * The energy regeneration ratio when braking.
     */
    private double energyRegenerationRatio;

    /**
     * Creates an instance of an electric vehicle.
     */
    public VehicleElectric() {
        super();
        this.minRPM = 0;
        this.maxRPM = 0;
        this.finalDriveRatio = 0.0;
        this.gearbox = new Gearbox();
        this.acceleratorPedal = new Accelerator();
        this.energyRegenerationRatio = 0;

    }

    public VehicleElectric(VehicleElectric vehicle) {
        super(vehicle);
        this.minRPM = vehicle.minRPM;
        this.maxRPM = vehicle.maxRPM;
        this.finalDriveRatio = vehicle.finalDriveRatio;
        this.gearbox = new Gearbox(vehicle.gearbox);
        this.acceleratorPedal = new Accelerator(vehicle.acceleratorPedal);
        this.energyRegenerationRatio = vehicle.energyRegenerationRatio;
    }

    /**
     * Returns the RPM minimum of the vehicle.
     *
     * @return The RPM minimum of the vehicle.
     */
    @Override
    public double getMinRpm() {
        return this.minRPM;
    }

    /**
     * Returns the RPM maximum of the vehicle.
     *
     * @return The RPM maximum of the vehicle.
     */
    @Override
    public double getMaxRpm() {
        return this.maxRPM;
    }

    /**
     * Returns the final drive ratio
     *
     * @return Final Drive Ratio
     */
    @Override
    public double getFinalDriveRatio() {
        return this.finalDriveRatio;
    }

    /**
     * Returns the Gearbox of the vehicle.
     *
     * @return The Gearbox of the vehicle.
     */
    @Override
    public Gearbox getGearbox() {
        return this.gearbox;
    }

    /**
     * Returns the accelerator pedal of the vehicle.
     *
     * @return The accelerator pedal list of the vehicle.
     */
    @Override
    public Accelerator getAccelerator() {
        return this.acceleratorPedal;
    }

    /**
     * Returns the maximum velocity in km/h
     *
     * @return
     */
    @Override
    public double getMaximumEngineVelocity() {
        double velocity = (Math.PI * this.getWheelSize() * maxRPM) / (60 * finalDriveRatio * this.getGearbox().getLowestGearRatio());
        return velocity * 3.6;
    }

    /**
     * Returns the energy regeneration ratio of this electric vehicle.
     *
     * @return (double) The energy regeneration ratio.
     */
    public double getEnergyRegenerationRatio() {
        return this.energyRegenerationRatio;
    }

    /**
     * Sets the minimum RPM of the vehicle's engine.
     *
     * @param m_min_rpm The new minimum RPM of the vehicle's engine.
     */
    @Override
    public void setMinRPM(double m_min_rpm) {
        if (m_min_rpm < 0) {
            throw new IllegalArgumentException("The mimimum rounds per minute "
                    + "of the vehicle should be positive.");
        }

        this.minRPM = m_min_rpm;
    }

    /**
     * Sets the maximum RPM of the vehicle's engine.
     *
     * @param m_max_rpm The new maximum RPM of the vehicle's engine.
     */
    @Override
    public void setMaxRPM(double m_max_rpm) {
        if (m_max_rpm <= 0) {
            throw new IllegalArgumentException("The maximum rounds per minute "
                    + "of the vehicle should be positive.");
        }

        this.maxRPM = m_max_rpm;
    }

    /**
     * Sets the final drive ratio of the vehicle.
     *
     * @param m_final_drive_ratio The new final drive ratio of the vehicle.
     */
    @Override
    public void setFinalDriveRatio(double m_final_drive_ratio) {
        if (m_final_drive_ratio <= 0) {
            throw new IllegalArgumentException("The final drive of the vehicle "
                    + "should be positive.");
        }

        this.finalDriveRatio = m_final_drive_ratio;
    }

    @Override
    public void setGearbox(Gearbox m_gearbox) {
        if (m_gearbox == null) {
            throw new IllegalArgumentException("The vehicle should have a gearbox. ");
        }

        this.gearbox = m_gearbox;
    }

    public void setAccelerator(Accelerator m_accelerator) {
        if (m_accelerator == null) {
            throw new IllegalArgumentException("The vehicle should have a gearbox. ");
        }

        this.acceleratorPedal = m_accelerator;
    }

    /**
     * Sets the energy regeneration ratio of this vehicle.
     * <p>
     * The ratio must lie between 0 and 1.
     *
     * @param m_energy_regeneration_ratio (double) The new value for the energy
     * regeneration ratio.
     */
    public void setEnergyRegenerationRatio(double m_energy_regeneration_ratio) {
        if (m_energy_regeneration_ratio < 0 || m_energy_regeneration_ratio > 1) {
            throw new IllegalArgumentException("Energy regeneration ratio must lie between 0 and 1!");
        }
        this.energyRegenerationRatio = m_energy_regeneration_ratio;
    }

    /**
     * Returns the textual description of the object in the following format:
     * super.toString() RPM Minimum: 1000 RPM Maximum: 5500 Final Drive: 2.6
     * Energy Regeneration Ratio: 0.9 m_gearbox.toString()
     * acceleratorPedal.toString()
     *
     * @return Textual description of the object.
     */
    @Override
    public String toString() {
//        StringBuilder sb = new StringBuilder();
//
//        sb.append(super.toString());
//        sb.append("\tRPM Minimum: ").append(this.m_min_rpm).append("\n");
//        sb.append("\tRPM Maximum: ").append(this.m_max_rpm).append("\n");
//        sb.append("\tFinal Drive: ").append(this.m_final_drive_ratio).append(" m\n");
//        sb.append("\tEnergy Regeneration Ratio: ").append(this.m_energy_regeneration_ratio).append(" \n");
//        sb.append(this.m_gearbox.toString());
//        sb.append(this.m_accelerator.toString());
//
//        return sb.toString();

        return super.toString();
    }

    /**
     * Validate the electric vehicle.
     *
     * @return True if vehicle electric is valid else returns false.
     */
    public boolean validateElectricVehicle() {

        if (this.minRPM < 0) {
            throw new IllegalArgumentException("The mimimum rounds per minute "
                    + "of the vehicle should be positive.");
        }

        if (this.maxRPM <= 0) {
            throw new IllegalArgumentException("The maximum rounds per minute "
                    + "of the vehicle should be positive.");
        }

        if (this.minRPM > maxRPM) {
            throw new IllegalArgumentException("RPM low should be less than RPM high.");
        }

        if (this.finalDriveRatio <= 0) {
            throw new IllegalArgumentException("The final drive of the vehicle "
                    + "should be positive.");
        }
        if (this.energyRegenerationRatio < 0 || this.energyRegenerationRatio > 1) {
            throw new IllegalArgumentException("The energy regeneration ratio "
                    + "of the vehicle should be ]0-1[.");
        }

        return true;
    }

    @Override
    public Vehicle copy() {
        return new VehicleElectric(this);
    }

}
