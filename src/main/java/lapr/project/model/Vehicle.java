package lapr.project.model;

public class Vehicle {

    private String m_name;
    private String m_description;
    private String m_type;
    private String m_fuel;
    private int m_vehicle_class;
    private String m_motorization;
    private double m_mass;
    private double m_load;
    private double m_drag_coefficient;
    private double m_wheel_size;
    private double m_frontal_area;
    private double m_rcc;
    private Energy m_energy;

    public Vehicle() {

    }

    public Vehicle(String m_name, String m_description, String m_type, String m_fuel, int m_vehicle_class, String m_motorization, double m_mass, double m_load, double m_drag_coefficient, double m_wheel_size, double m_frontal_area, double m_rcc, Energy m_energy) {
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
        this.m_energy = m_energy;
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
        this.m_energy = v.m_energy;
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
    
    public Energy getEnergy() {
        return m_energy;
    }

    public void setEnergy(Energy m_energy) {
        this.m_energy = m_energy;
    }

    public void setRcc(double m_rcc) {
        this.m_rcc = m_rcc;
    }

    public void setWheelSize(double m_wheel_size) {
        this.m_wheel_size = m_wheel_size;
    }

    public void setFrontalArea(double m_frontal_area) {
        this.m_frontal_area = m_frontal_area;
    }

    public void setFuel(String m_fuel) {
        this.m_fuel = m_fuel;
    }

    public void setDragCoefficient(double m_drag_coefficient) {
        this.m_drag_coefficient = m_drag_coefficient;
    }

    public void setLoad(double m_load) {
        this.m_load = m_load;
    }

    public void setMass(double m_mass) {
        this.m_mass = m_mass;
    }

    public void setMotorization(String m_motorization) {
        this.m_motorization = m_motorization;
    }

    public void setName(String m_name) {
        this.m_name = m_name;
    }

    public void setDescription(String description) {
        this.m_description = description;
    }

    public void setVehicleClass(int m_vehicle_class) {
        this.m_vehicle_class = m_vehicle_class;
    }

    public void setType(String m_type) {
        this.m_type = m_type;
    }

    @Override
    public String toString() {
        return "Vehicle{" + "m_name=" + m_name + ", m_description=" + m_description + ", m_type=" + m_type + ", m_fuel=" + m_fuel + ", m_vehicle_class=" + m_vehicle_class + ", m_motorization=" + m_motorization + ", m_mass=" + m_mass + ", m_load=" + m_load + ", m_drag_coefficient=" + m_drag_coefficient + ", m_wheel_size=" + m_wheel_size + ", m_frontal_area=" + m_frontal_area + ", m_rcc=" + m_rcc + ", m_energy=" + m_energy + '}';
    }

}
