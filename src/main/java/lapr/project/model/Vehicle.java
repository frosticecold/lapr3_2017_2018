package lapr.project.model;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

public abstract class Vehicle {

    //Car attributes
    /**
     * Name of the vehicle.
     */
    private String name;
    /**
     * Description of the vehicle.
     */
    private String description;

    /**
     * Type of the vehicle
     * <p>
     * Example: car
     *
     */
    private String type;

    /**
     * Fuel of the vehicle
     * <p>
     * Types of Fuel:
     * <p>
     * Diesel
     * <p>
     * Gasoline
     * <p>
     * Electric
     */
    private String fuel;

    /**
     * Vehicle Class
     * <p>
     * Example : 1,2,3,4
     */
    private int vehicleClass;

    /**
     * Motorization of Vehicle
     * <p>
     * Example :
     * <p>
     * Combustion
     * <p>
     * Electric
     */
    private String motorization;

    /**
     * Wheel size of the vehicle (meters)
     */
    private double wheelSize;

    //Car Physics
    /**
     * Mass of a vehicle in Kg
     */
    private double mass;

    /**
     * Load of the vehicle in Kg
     */
    private double load;

    /**
     * Drag Coefficient of the vehicle (no units)
     */
    private double dragCoefficient;

    /**
     * Frontal area of the vehicle in m^2
     */
    private double frontalArea;

    /**
     * Rolling resistance coefficient of the vehicle. (no units)
     */
    private double rollingResistanceCoefficient;

    /**
     * Map with the max velocity(KM/H) for specific road
     * <p>
     * Example: For the regular road, the car can travel at max 90 KM/h
     */
    private Map<String, Double> mapRoadVelocityLimit;

    //------------------------------------- THIS MAP IS DUPLICATE ---------------
    /**
     * Throttle list for the car, there are three types of throttles
     * <p>
     * Throttle Low - 25%
     * <p>
     * Throttle Medium = 50%
     * <p>
     * Throttle Max = 100%
     */
    private Map<Integer, Throttle> throttleList;

    /**
     * Value for the minimum throttle
     */
    public static final int THROTTLE_LOW = 25;
    /**
     * Value for the medium throttle
     */
    public static final int THROTTLE_MEDIUM = 50;
    /**
     * Value for the maximum throttle
     */
    public static final int THROTTLE_MAX = 100;

    /**
     * Value for the diesel fuel type
     */
    public static final String FUEL_DIESEL = "diesel";
    /**
     * Value for the gasoline fuel type
     */
    public static final String FUEL_GASOLINE = "gasoline";
    /**
     * Value for the electric fuel type
     */
    public static final String FUEL_ELETRIC = "electric";

    /**
     * Empty constructor
     */
    public Vehicle() {
        name = "";
        description = "";
        type = "";
        fuel = "";
        vehicleClass = 0;
        motorization = "";
        wheelSize = 0.0;
        mass = 0.0;
        load = 0.0;
        dragCoefficient = 0.0;
        frontalArea = 0.0;
        rollingResistanceCoefficient = 0.0;
        mapRoadVelocityLimit = new HashMap<>();
        throttleList = new LinkedHashMap<>();
    }

    /**
     * Full constructor for a vehicle
     *
     * @param nameOfVehicle Name of Vehicle
     * @param descriptionOfVehicle Description of the vehicle
     * @param typeOfVehicle Type of the vehicle
     * @param fuelOfVehicle Fuel type of the vehicle
     * @param vehicleClass Vehicle class
     * @param motorization Type of motor
     * @param mass Mass of the vehicle (kg)
     * @param load Load of the vehicle (kg)
     * @param dragCoefficient Drag coefficient for the vehicle (no units)
     * @param wheelSize wheel size of the vehicle (m)
     * @param frontalArea frontal area of the vehicle (m^2)
     * @param rcc Rolling resistance coefficient of the vehicle.
     * @param velocityLimits Map with the velocity limits of a road
     * @param throttleList Map with the throttle list
     */
    public Vehicle(String nameOfVehicle, String descriptionOfVehicle, String typeOfVehicle, String fuelOfVehicle, int vehicleClass, String motorization, double mass, double load, double dragCoefficient, double wheelSize, double frontalArea, double rcc, Map<String, Double> velocityLimits, Map<Integer, Throttle> throttleList) {
        this.name = nameOfVehicle;
        this.description = descriptionOfVehicle;
        this.type = typeOfVehicle;
        this.fuel = fuelOfVehicle;
        this.vehicleClass = vehicleClass;
        this.motorization = motorization;
        this.mass = mass;
        this.load = load;
        this.dragCoefficient = dragCoefficient;
        this.wheelSize = wheelSize;
        this.frontalArea = frontalArea;
        this.rollingResistanceCoefficient = rcc;
        this.mapRoadVelocityLimit = velocityLimits;
        this.throttleList = throttleList;
    }

    /**
     * Copy Constructor
     *
     * @param v Vehicle to copy
     */
    public Vehicle(Vehicle v) {
        this.name = v.name;
        this.description = v.description;
        this.type = v.type;
        this.fuel = v.fuel;
        this.vehicleClass = v.vehicleClass;
        this.motorization = v.motorization;
        this.mass = v.mass;
        this.load = v.load;
        this.dragCoefficient = v.dragCoefficient;
        this.wheelSize = v.wheelSize;
        this.frontalArea = v.frontalArea;
        this.rollingResistanceCoefficient = v.rollingResistanceCoefficient;
        this.mapRoadVelocityLimit = new HashMap<>();
        for (String road : v.mapRoadVelocityLimit.keySet()) {
            this.mapRoadVelocityLimit.put(road, v.mapRoadVelocityLimit.get(road));
        }
        this.throttleList = new LinkedHashMap<>();
        for (Integer i : v.throttleList.keySet()) {
            this.throttleList.put(i, new Throttle(throttleList.get(i)));
        }

    }

    /**
     * Returns the vehicle name
     *
     * @return String with the vehicle name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the map of throttles
     *
     * @return
     */
    public Map<Integer, Throttle> getThrottles() {
        return this.throttleList;

    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getFuel() {
        return fuel;
    }

    public String getMotorization() {
        return motorization;
    }

    public double getLoad() {
        return load;
    }

    public double getMass() {
        return mass;
    }

    public double getTotalWeight() {
        return load + mass;
    }

    public double getDragCoefficient() {
        return dragCoefficient;
    }

    public double getWheelSize() {
        return wheelSize;
    }

    public double getFrontalArea() {
        return frontalArea;
    }

    public int getVehicleClass() {
        return vehicleClass;
    }

    public double getTorqueAtThrottle(int throttle, double rpm) {
        double torque = -1;
        if (throttle == 25 || throttle == 50 || throttle == 100) {
            Accelerator a = this.getAccelerator();
            Throttle thr = a.getThrottleList().get(throttle);
            torque = thr.getTorqueByRPM(rpm);
        }
        return torque;

    }

    /**
     * Returns the car maximum velocity in KM/H fora given road If there is no
     * speed limit, it returns the maximum car speed
     *
     * @param road
     * @return
     */
    public double getRoadVelocityLimit(String road) {
        road = road.toUpperCase();
        for (String roadkey : mapRoadVelocityLimit.keySet()) {
            if (road.contains(roadkey)) {
                return mapRoadVelocityLimit.get(roadkey);
            }
        }
        return this.getMaximumEngineVelocity();
    }

    public double getRCC() {
        return rollingResistanceCoefficient;
    }

    /**
     * Abstract method to return the vehicles maximum velocity
     *
     * @return
     */
    public abstract double getMaximumEngineVelocity();

    /**
     * Returns the minimum car RPM
     *
     * @return
     */
    public abstract double getMinRpm();

    /**
     * Returns the maximum car RPM
     *
     * @return
     */
    public abstract double getMaxRpm();

    /**
     * Returns the final drive ratio of the car
     *
     * @return
     */
    public abstract double getFinalDriveRatio();

    /**
     * Returns the gearbox for the car
     *
     * @return
     */
    public abstract Gearbox getGearbox();

    /**
     * Returns the accelerator pedal for the car
     *
     * @return
     */
    public abstract Accelerator getAccelerator();

    /**
     * method that changes the minimum rpm for the vehicle
     *
     * @param minRPM
     */
    public abstract void setMinRPM(double minRPM);

    /**
     * method that changes the maximum rpm for the vehicle
     *
     * @param maxRPM
     */
    public abstract void setMaxRPM(double maxRPM);

    /**
     * method that changes the final drive ratio for the vheicle
     *
     * @param finalDriveRatio
     */
    public abstract void setFinalDriveRatio(double finalDriveRatio);

    /**
     * Change the gearbox for a vehicle
     *
     * @param gearbox
     */
    public abstract void setGearbox(Gearbox gearbox);

    /**
     * Change the accelerator pedal for a vehicle
     *
     * @param accelerator
     */
    public abstract void setAccelerator(Accelerator accelerator);

    /**
     * Method that changes the map of road velocity limits
     *
     * @param mapRoadVelocityLimit
     */
    public void setRoadVelocityLimit(Map<String, Double> mapRoadVelocityLimit) {
        if (mapRoadVelocityLimit == null) {
            throw new IllegalArgumentException("The road velocity limit map inserted cannot be null.");
        }
        this.mapRoadVelocityLimit = mapRoadVelocityLimit;
    }

    /**
     * Method that changes the vehicle Rolling resistance coefficient
     *
     * @param rcc
     */
    public void setRCC(double rcc) {
        if (rcc < 0) {
            throw new IllegalArgumentException("The rolling resistance coefficient of the vehicle must be positive.");
        }
        this.rollingResistanceCoefficient = rcc;
    }

    /**
     * Method that changes the wheel size of the vehicle(m)
     *
     * @param wheel_size
     */
    public void setWheelSize(double wheel_size) {
        if (wheel_size <= 0) {
            throw new IllegalArgumentException("The wheel size of the vehicle must be greater than 0");
        }
        this.wheelSize = wheel_size;
    }

    /**
     * Method that changes the frontal area of the vehicle (m^2)
     *
     * @param frontal_area
     */
    public void setFrontalArea(double frontal_area) {
        if (frontal_area <= 0) {
            throw new IllegalArgumentException("The frontal area of the vehicle must be greater than 0");
        }
        this.frontalArea = frontal_area;
    }

    /**
     * Method that changes the fuel type of a vehicle
     *
     * @param fuel
     */
    public void setFuel(String fuel) {
        if (fuel == null || fuel.trim().isEmpty()) {
            throw new IllegalArgumentException("The fuel of the vehicle cannot be empty.");
        }
        this.fuel = fuel;
    }

    /**
     * Method that changes the Drag Coefficient of a vehicle
     *
     * @param drag_coefficient
     */
    public void setDragCoefficient(double drag_coefficient) {
        if (drag_coefficient <= 0) {
            throw new IllegalArgumentException("The drag coefficient of the vehicle must be greater than 0");
        }
        this.dragCoefficient = drag_coefficient;
    }

    /**
     * Method that changes the load of a vehicle (Kg)
     *
     * @param load
     */
    public void setLoad(double load) {
        if (load < 0) {
            throw new IllegalArgumentException("The load of the vehicle must be positive");
        }
        this.load = load;
    }

    /**
     * Method that changes the mass of a vehicle (Kg)
     *
     * @param mass
     */
    public void setMass(double mass) {
        if (mass <= 0) {
            throw new IllegalArgumentException("The mass of the vehicle must be greater than 0");
        }
        this.mass = mass;
    }

    /**
     * Method that changes the motorization of a vehicle
     *
     * @param motorization
     */
    public void setMotorization(String motorization) {
        if (motorization == null || motorization.trim().isEmpty()) {
            throw new IllegalArgumentException("The motorization of the vehicle cannot be empty.");
        }
        this.motorization = motorization;
    }

    /**
     * Method that changes the name of a vehicle
     *
     * @param name
     */
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("The name of the vehicle cannot be empty.");
        }
        this.name = name;
    }

    /**
     * Method that changes the description of a vehicle
     *
     * @param description
     */
    public void setDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("The description of the vehicle cannot be empty.");
        }
        this.description = description;
    }

    /**
     * Method that changes the vehicles class
     *
     * @param vehicle_class
     */
    public void setVehicleClass(int vehicle_class) {
        if (vehicle_class < 0) {
            throw new IllegalArgumentException("The vehicle_class of the vehicle must positive");
        }
        this.vehicleClass = vehicle_class;
    }

    /**
     * Method that changes the type of vehicle
     *
     * @param type
     */
    public void setType(String type) {
        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException("The type of the vehicle cannot be empty.");
        }
        this.type = type;
    }

    /**
     * Method that changes the throttle list map
     *
     * @param throttleList
     */
    public void setThrottlesList(Map<Integer, Throttle> throttleList) {
        this.throttleList = throttleList;
    }

    @Override
    public String toString() {
        return name;
    }

    /**
     * Returns the textual description of the object in html format.
     *
     * @return Textual description of the object.
     */
    public String toStringHTML() {
        StringBuilder sb = new StringBuilder();

        sb.append("\t<li>Name: ").append(this.name).append("</li>\n");
        sb.append("\t<li>Description: ").append(this.description).append("</li>\n");
        sb.append("\t<li>Type: ").append(this.type).append("</li>\n");
        sb.append("\t<li>Fuel: ").append(this.fuel).append("</li>\n");
        sb.append("\t<li>Mass: ").append(this.mass).append(" Kg</li>\n");
        sb.append("\t<li>Load: ").append(this.load).append(" Kg</li>\n");
        sb.append("\t<li>Drag Coefficient: ").append(this.dragCoefficient).append("</li>\n");
        sb.append("\t<li>Frontal Area: ").append(this.frontalArea).append(" m</li>\n");
        sb.append("\t<li>RRC: ").append(this.rollingResistanceCoefficient).append("</li>\n");
        sb.append("\t<li>Wheel Size: ").append(this.wheelSize).append(" m</li>\n");

        if (!this.mapRoadVelocityLimit.isEmpty()) {
            sb.append("\t<li>Velocity Limits:<ul>\n");
            for (Entry<String, Double> entry : this.mapRoadVelocityLimit.entrySet()) {
                sb.append("\t\t<li>").append(entry.getKey()).append(": ")
                        .append(entry.getValue()).append(" m/s</li>\n");
            }
            sb.append("\t</ul></li>\n");
        }
        return sb.toString();
    }

    public abstract Vehicle copy();

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.description);
        hash = 97 * hash + Objects.hashCode(this.type);
        hash = 97 * hash + Objects.hashCode(this.fuel);
        hash = 97 * hash + this.vehicleClass;
        hash = 97 * hash + Objects.hashCode(this.motorization);
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.wheelSize) ^ (Double.doubleToLongBits(this.wheelSize) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.mass) ^ (Double.doubleToLongBits(this.mass) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.load) ^ (Double.doubleToLongBits(this.load) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.dragCoefficient) ^ (Double.doubleToLongBits(this.dragCoefficient) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.frontalArea) ^ (Double.doubleToLongBits(this.frontalArea) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.rollingResistanceCoefficient) ^ (Double.doubleToLongBits(this.rollingResistanceCoefficient) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vehicle other = (Vehicle) obj;
        if (this.vehicleClass != other.vehicleClass) {
            return false;
        }
        if (Double.compare(this.wheelSize, other.wheelSize) != 0) {
            return false;
        }
        if (Double.compare(this.mass, other.mass) != 0) {
            return false;
        }
        if (Double.compare(this.load, other.load) != 0) {
            return false;
        }
        if (Double.compare(this.dragCoefficient, other.dragCoefficient) != 0) {
            return false;
        }
        if (Double.compare(this.frontalArea, other.frontalArea) != 0) {
            return false;
        }
        if (Double.compare(this.rollingResistanceCoefficient, other.rollingResistanceCoefficient) != 0) {
            return false;
        }
        if (!this.name.equalsIgnoreCase(other.name)) {
            return false;
        }
        if (!this.description.equalsIgnoreCase(other.description)) {
            return false;
        }
        if (!this.type.equalsIgnoreCase(other.type)) {
            return false;
        }
        if (!this.fuel.equalsIgnoreCase(other.fuel)) {
            return false;
        }
        if (!this.motorization.equalsIgnoreCase(other.motorization)) {
            return false;
        }
        return true;
    }

}
