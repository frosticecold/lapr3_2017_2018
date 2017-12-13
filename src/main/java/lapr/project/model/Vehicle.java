package lapr.project.model;

import lapr.project.model.TollClass;

public class Vehicle {

    private VehicleType m_vehicle_type;
    private TollClass m_toll_class;
    private Motorization m_motorization;
    private float m_mass;
    private float m_load;
    private float m_drag_coefficient;
    private float m_rolling_resistance_coefficient;
    private float m_wheel_size;
    private String name;
    private String description;
    private String type;

    public Vehicle(String type) {
        this.type = type;
    }

    public Vehicle() {
    }
    
    

    public void energy() {
        throw new UnsupportedOperationException();
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Vehicle{" + "type=" + type + '}';
    }

  
}
