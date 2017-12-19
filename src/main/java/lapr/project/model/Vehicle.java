package lapr.project.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public abstract class Vehicle {

    //Car attributes
    /**
     * Name of the vehicle.
     */
    private String m_name;
    /**
     * Description of the vehicle.
     */
    private String m_description;

    /**
     * Type of the vehicle
     */
    private String m_type;

    /**
     * Fuel of the vehicle
     */
    private String m_fuel;

    /**
     * Vehicle Class
     */
    private int m_vehicle_class;

    /**
     * Motorization of Vehicle
     */
    private String m_motorization;

    /**
     * Wheel size of the vehicle
     */
    private double m_wheel_size;

    //Car Physics
    /**
     * Mass of a vehicle
     */
    private double m_mass;

    /**
     * Load of the vehicle
     */
    private double m_load;

    /**
     * Drag Coefficient of the vehicle
     */
    private double m_drag_coefficient;

    /**
     * Frontal area of the vehicle
     */
    private double m_frontal_area;

    /**
     * Rolling resistance coefficient of the vehicle.
     */
    private double m_rcc;

    private Map<String, Double> m_road_velocity_limit;

    public Vehicle() {
        m_name = "";
        m_description = "";
        m_type = "";
        m_fuel = "";
        m_vehicle_class = 0;
        m_motorization = "";
        m_wheel_size = 0.0;
        m_mass = 0.0;
        m_load = 0.0;
        m_drag_coefficient = 0.0;
        m_frontal_area = 0.0;
        m_rcc = 0.0;
        m_road_velocity_limit = new HashMap<>();
    }

    public Vehicle(String m_name, String m_description, String m_type, String m_fuel, int m_vehicle_class, String m_motorization, double m_mass, double m_load, double m_drag_coefficient, double m_wheel_size, double m_frontal_area, double m_rcc, Map<String, Double> velocityLimits) {
        this.m_name = m_name;
        this.m_description = m_description;
        this.m_type = m_type;
        this.m_fuel = m_fuel;
        this.m_vehicle_class = m_vehicle_class;
        this.m_motorization = m_motorization;
        this.m_mass = m_mass;
        this.m_load = m_load;
        this.m_drag_coefficient = m_drag_coefficient;
        this.m_wheel_size = m_wheel_size;
        this.m_frontal_area = m_frontal_area;
        this.m_rcc = m_rcc;
        this.m_road_velocity_limit = velocityLimits;
    }

    public Vehicle(Vehicle v) {
        this.m_name = v.m_name;
        this.m_description = v.m_description;
        this.m_type = v.m_type;
        this.m_fuel = v.m_fuel;
        this.m_vehicle_class = v.m_vehicle_class;
        this.m_motorization = v.m_motorization;
        this.m_mass = v.m_mass;
        this.m_load = v.m_load;
        this.m_drag_coefficient = v.m_drag_coefficient;
        this.m_wheel_size = v.m_wheel_size;
        this.m_frontal_area = v.m_frontal_area;
        this.m_rcc = v.m_rcc;
        this.m_road_velocity_limit = v.m_road_velocity_limit;
    }

    public String getName() {
        return m_name;
    }

    public abstract double getMaximumVelocity();

    public abstract double getMinRpm();

    public abstract void setMinRpm(double m_min_rpm);

    public abstract double getMaxRpm();

    public abstract void setMaxRpm(double m_max_rpm);

    public abstract double getFinalDriveRatio();

    public abstract void setFinalDriveRatio(double m_final_drive_ratio);

    public abstract Gearbox getGearbox();

    public abstract void setGearbox(Gearbox m_gearbox);

    public abstract Accelerator getAccelerator();

    public abstract void setAccelerator(Accelerator m_accelerator);

    public double getRoadVelocityLimit(String road) {
        if (m_road_velocity_limit.containsKey(road)) {
            return m_road_velocity_limit.get(road);
        } else {
            return this.getMaximumVelocity();
        }

    }

    public void setRoadVelocityLimit(Map<String, Double> m_road_velocity_limit) {
        if (m_road_velocity_limit == null) {
            throw new IllegalArgumentException("The road velocity limit map inserted cannot be null.");
        }
        this.m_road_velocity_limit = m_road_velocity_limit;
    }

    public double getMass() {
        return m_mass;
    }

    public double getDragCoefficient() {
        return m_drag_coefficient;
    }

    public double getWheelSize() {
        return m_wheel_size;
    }

    public double getFrontalArea() {
        return m_frontal_area;
    }

    public int getVehicleClass() {
        return m_vehicle_class;
    }

    public void setRcc(double rcc) {
        if (rcc < 0) {
            throw new IllegalArgumentException("The rolling resistance coefficient of the vehicle must be positive.");
        }
        this.m_rcc = rcc;
    }

    public void setWheelSize(double wheel_size) {
        if (wheel_size <= 0) {
            throw new IllegalArgumentException("The wheel size of the vehicle must be greater than 0");
        }
        this.m_wheel_size = wheel_size;
    }

    public void setFrontalArea(double frontal_area) {
        if (frontal_area <= 0) {
            throw new IllegalArgumentException("The frontal area of the vehicle must be greater than 0");
        }
        this.m_frontal_area = frontal_area;
    }

    public void setFuel(String fuel) {
        if (fuel == null || fuel.trim().isEmpty()) {
            throw new IllegalArgumentException("The fuel of the vehicle cannot be empty.");
        }
        this.m_fuel = fuel;
    }

    public void setDragCoefficient(double drag_coefficient) {
        if (drag_coefficient <= 0) {
            throw new IllegalArgumentException("The drag coefficient of the vehicle must be greater than 0");
        }
        this.m_drag_coefficient = drag_coefficient;
    }

    public void setLoad(double load) {
        if (load < 0) {
            throw new IllegalArgumentException("The load of the vehicle must be positive");
        }
        this.m_load = load;
    }

    public void setMass(double mass) {
        if (mass <= 0) {
            throw new IllegalArgumentException("The mass of the vehicle must be greater than 0");
        }
        this.m_mass = mass;
    }

    public void setMotorization(String motorization) {
        if (motorization == null || motorization.trim().isEmpty()) {
            throw new IllegalArgumentException("The motorization of the vehicle cannot be empty.");
        }
        this.m_motorization = motorization;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("The name of the vehicle cannot be empty.");
        }
        this.m_name = name;
    }

    public void setDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("The description of the vehicle cannot be empty.");
        }
        this.m_description = description;
    }

    public void setVehicleClass(int vehicle_class) {
        if (vehicle_class < 0) {
            throw new IllegalArgumentException("The vehicle_class of the vehicle must positive");
        }
        this.m_vehicle_class = vehicle_class;
    }

    public void setType(String type) {
        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException("The type of the vehicle cannot be empty.");
        }
        this.m_type = type;
    }

    @Override
    public String toString() {
        return "Vehicle{" + "m_name=" + m_name + ", m_description=" + m_description + ", m_type=" + m_type + ", m_fuel=" + m_fuel + ", m_vehicle_class=" + m_vehicle_class + ", m_motorization=" + m_motorization + ", m_wheel_size=" + m_wheel_size + ", m_mass=" + m_mass + ", m_load=" + m_load + ", m_drag_coefficient=" + m_drag_coefficient + ", m_frontal_area=" + m_frontal_area + ", m_rcc=" + m_rcc + ", m_road_velocity_limit=" + m_road_velocity_limit + '}';
    }

    /**
     * Returns the textual description of the object in html format.
     *
     * @return Textual description of the object.
     */
    public String toStringHTML() {
        StringBuilder sb = new StringBuilder();

        sb.append("\t<li>Name: ").append(this.m_name).append("</li>\n");
        sb.append("\t<li>Description: ").append(this.m_description).append("</li>\n");
        sb.append("\t<li>Type: ").append(this.m_type).append("</li>\n");
        sb.append("\t<li>Fuel: ").append(this.m_fuel).append("</li>\n");
        sb.append("\t<li>Mass: ").append(this.m_mass).append(" Kg</li>\n");
        sb.append("\t<li>Load: ").append(this.m_load).append(" Kg</li>\n");
        sb.append("\t<li>Drag Coefficient: ").append(this.m_drag_coefficient).append("</li>\n");
        sb.append("\t<li>Frontal Area: ").append(this.m_frontal_area).append(" m</li>\n");
        sb.append("\t<li>RRC: ").append(this.m_rcc).append("</li>\n");
        sb.append("\t<li>Wheel Size: ").append(this.m_wheel_size).append(" m</li>\n");

        if (!this.m_road_velocity_limit.isEmpty()) {
            sb.append("\t<li>Velocity Limits:<ul>\n");
            for (Entry<String, Double> entry : this.m_road_velocity_limit.entrySet()) {
                sb.append("\t\t<li>").append(entry.getKey()).append(": ")
                        .append(entry.getValue()).append(" m/s</li>\n");
            }
            sb.append("\t</ul></li>\n");
        }
        return sb.toString();
    }

}
