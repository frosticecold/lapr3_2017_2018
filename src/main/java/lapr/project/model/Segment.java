package lapr.project.model;

public class Segment {

    private int m_segment_index;
    private float m_initial_height;
    private float m_slope;
    private float m_length;
    private float m_wind_direction;
    private float m_wind_speed;
    private float m_maximum_velocity;
    private float m_minimum_velocity;
    private Toll m_gantry_toll;

    public Segment(int m_segment_index, float m_initial_height, float m_slope, float m_length, float m_wind_direction, float m_wind_speed, float m_maximum_velocity, float m_minimum_velocity, Toll m_gantry_toll) {
        this.m_segment_index = m_segment_index;
        this.m_initial_height = m_initial_height;
        this.m_slope = m_slope;
        this.m_length = m_length;
        this.m_wind_direction = m_wind_direction;
        this.m_wind_speed = m_wind_speed;
        this.m_maximum_velocity = m_maximum_velocity;
        this.m_minimum_velocity = m_minimum_velocity;
        this.m_gantry_toll = m_gantry_toll;
    }

    public float getLength() {
        return m_length;
    }

}
