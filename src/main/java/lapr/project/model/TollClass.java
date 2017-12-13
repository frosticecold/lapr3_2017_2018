package lapr.project.model;

public class TollClass {

    private final int m_id_class;
    private final double m_toll_fare;

    public TollClass(int id_class, double toll_fare) {
        m_id_class = id_class;
        m_toll_fare = toll_fare;
    }

    public int getIdClass() {
        return m_id_class;
    }

    public double getTollFare() {
        return m_toll_fare;
    }
}
