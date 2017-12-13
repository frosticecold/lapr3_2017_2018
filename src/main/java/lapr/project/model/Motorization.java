package lapr.project.model;

public class Motorization {

    private String type;
    private int torque;

    /**
     *
     * @param name
     */
    public Motorization(String type) {
        this.type = type;
    }

    public String getMotorType() {
        return type;
    }

    public int getTorque() {
        return torque;
    }

}
