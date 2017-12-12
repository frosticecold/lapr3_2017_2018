package lapr.project.model;

public enum VehicleType {
    TRUCK("TRUCK"),
    TRACTOR("TRACKTOR"),
    SEMI_TRAILER("SEMI_TRAILER"),
    CAR("CAR"),
    MOTORCYCLE("MOTORCYCLE");

    private String m_name;

    /**
     *
     * @param name
     */
    VehicleType(String name) {
        m_name = name;
    }

}
