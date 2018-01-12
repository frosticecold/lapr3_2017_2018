package lapr.project.model;

import java.util.Map;

public class VehicleCombustion extends Vehicle {

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
     * Creates an instance of an electric vehicle.
     */
    public VehicleCombustion() {
        super();
        this.minRPM = 0;
        this.maxRPM = 0;
        this.finalDriveRatio = 0.0;
        this.gearbox = new Gearbox();
        this.acceleratorPedal = new Accelerator();
    }

    public VehicleCombustion(VehicleCombustion vehicle) {
        super(vehicle);
        this.minRPM = vehicle.minRPM;
        this.maxRPM = vehicle.maxRPM;
        this.finalDriveRatio = vehicle.finalDriveRatio;
        this.gearbox = new Gearbox(vehicle.gearbox);
        this.acceleratorPedal = new Accelerator(vehicle.acceleratorPedal);
    }

    public VehicleCombustion(double minRPM, double maxRPM, double finalDriveRatio, Gearbox gearbox, Accelerator acceleratorPedal, String nameOfVehicle, String descriptionOfVehicle, String typeOfVehicle, String fuelOfVehicle, int vehicleClass, String motorization, double mass, double load, double dragCoefficient, double wheelSize, double frontalArea, double rcc, Map<String, Double> velocityLimits, Map<Integer, Throttle> throttleList) {
        super(nameOfVehicle, descriptionOfVehicle, typeOfVehicle, fuelOfVehicle, vehicleClass, motorization, mass, load, dragCoefficient, wheelSize, frontalArea, rcc, velocityLimits, throttleList);
        this.minRPM = minRPM;
        this.maxRPM = maxRPM;
        this.finalDriveRatio = finalDriveRatio;
        this.gearbox = gearbox;
        this.acceleratorPedal = acceleratorPedal;
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
     * Returns the accelerator of the vehicle.
     *
     * @return The accelerator list of the vehicle.
     */
    @Override
    public Accelerator getAccelerator() {
        return this.acceleratorPedal;
    }

    /**
     * Sets the minimum RPM of the vehicle's engine.
     *
     * @param minRPM The new minimum RPM of the vehicle's engine.
     */
    @Override
    public void setMinRPM(double minRPM) {
        if (minRPM < 0) {
            throw new IllegalArgumentException("The mimimum rounds per minute "
                    + "of the vehicle should be positive.");
        }

        this.minRPM = minRPM;
    }

    /**
     * Sets the maximum RPM of the vehicle's engine.
     *
     * @param maxRPM The new maximum RPM of the vehicle's engine.
     */
    @Override
    public void setMaxRPM(double maxRPM) {
        if (maxRPM <= 0) {
            throw new IllegalArgumentException("The maximum rounds per minute "
                    + "of the vehicle should be positive.");
        }

        this.maxRPM = maxRPM;
    }

    /**
     * Sets the final drive of the vehicle.
     *
     * @param finalDriveRatio The new final drive of the vehicle.
     */
    @Override
    public void setFinalDriveRatio(double finalDriveRatio) {
        if (finalDriveRatio <= 0) {
            throw new IllegalArgumentException("The final drive of the vehicle "
                    + "should be positive.");
        }

        this.finalDriveRatio = finalDriveRatio;
    }

    @Override
    public void setGearbox(Gearbox gearbox) {
        if (gearbox == null) {
            throw new IllegalArgumentException("The vehicle should have a gearbox. ");
        }

        this.gearbox = gearbox;
    }

    public void setAccelerator(Accelerator acceleratorPedal) {
        if (acceleratorPedal == null) {
            throw new IllegalArgumentException("The vehicle should have a gearbox. ");
        }

        this.acceleratorPedal = acceleratorPedal;
    }

    /**
     * Returns the textual description of the object in the following format:
     * super.toString() RPM Minimum: 1000 RPM Maximum: 5500 Final Drive: 2.6
     * m_gearbox.toString() acceleratorPedal.toString()
     *
     * @return Textual description of the object.
     */
    @Override
    public String toString() {

        return super.toString();
    }

    /**
     * Validate the combustion vehicle.
     *
     * @return True if vehicle combustion is valid else returns false.
     */
    public boolean validateCombustionVehicle() {

        if (this.minRPM <= 0) {
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
        return true;
    }

    /**
     * Returns the maximum velocity in KM/H
     *
     * @return
     */
    @Override
    public double getMaximumEngineVelocity() {
        double velocity = (Math.PI * this.getWheelSize() * maxRPM) / (60 * finalDriveRatio * this.getGearbox().getLowestGearRatio());

        return velocity * 3.6;
    }

    @Override
    public Vehicle copy() {
        return new VehicleCombustion(this);
    }
}
