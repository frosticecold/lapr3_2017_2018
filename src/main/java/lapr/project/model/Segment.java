package lapr.project.model;

import lapr.project.calculations.UnitConverstion;

public class Segment {

    private int m_segment_index;
    private double m_initial_height;
    private double m_final_height;
    private double m_length;
    private double m_wind_direction;
    private double m_wind_speed;
    private double m_maximum_velocity;
    private double m_minimum_velocity;

    public Segment() {
    }

    public Segment(int m_segment_index, double m_initial_height, double m_final_height, double m_length, double m_wind_direction, double m_wind_speed, double m_maximum_velocity, double m_minimum_velocity) {
        this.m_segment_index = m_segment_index;
        this.m_initial_height = m_initial_height;
        this.m_final_height = m_final_height;
        this.m_length = m_length;
        this.m_wind_direction = m_wind_direction;
        this.m_wind_speed = m_wind_speed;
        this.m_maximum_velocity = m_maximum_velocity;
        this.m_minimum_velocity = m_minimum_velocity;
    }

    public double getLength() {
        return getM_length();
    }

    /**
     * @return the m_segment_index
     */
    public int getM_segment_index() {
        return m_segment_index;
    }

    /**
     * @return the m_initial_height
     */
    public double getM_initial_height() {
        return m_initial_height;
    }

    /**
     * @return the m_length
     */
    public double getM_length() {
        return m_length;
    }

    /**
     * @return the m_wind_direction
     */
    public double getM_wind_direction() {
        return m_wind_direction;
    }

    /**
     * @return the m_wind_speed
     */
    public double getM_wind_speed() {
        return m_wind_speed;
    }

    /**
     * @return the m_maximum_velocity
     */
    public double getM_maximum_velocity() {
        return m_maximum_velocity;
    }

    /**
     * @return the m_minimum_velocity
     */
    public double getM_minimum_velocity() {
        return m_minimum_velocity;
    }

    /**
     * @param m_segment_index the m_segment_index to set
     */
    public void setM_segment_index(int m_segment_index) {
        this.m_segment_index = m_segment_index;
    }

    /**
     * @param m_initial_height the m_initial_height to set
     */
    public void setM_initial_height(double m_initial_height) {
        this.m_initial_height = m_initial_height;
    }

    /**
     * @param m_length the m_length to set
     */
    public void setM_length(double m_length) {
        this.m_length = m_length;
    }

    /**
     * @param m_wind_direction the m_wind_direction to set
     */
    public void setM_wind_direction(double m_wind_direction) {
        this.m_wind_direction = m_wind_direction;
    }

    /**
     * @param wind_speed the m_wind_speed to set
     */
    public void setWindSpeed(double wind_speed) {
        if (wind_speed < 0) {
            throw new IllegalArgumentException("Wind speed must be equal or greater than zero.");
        }
        this.m_wind_speed = wind_speed;
    }

    /**
     * @param m_maximum_velocity the m_maximum_velocity to set
     */
    public void setM_maximum_velocity(double m_maximum_velocity) {
        this.m_maximum_velocity = m_maximum_velocity;
    }

    /**
     * @param m_minimum_velocity the m_minimum_velocity to set
     */
    public void setM_minimum_velocity(double m_minimum_velocity) {
        this.m_minimum_velocity = m_minimum_velocity;
    }

    public void setM_final_height(double final_height) {
        this.m_final_height = final_height;
    }

    /**
     * M = Delta Y / Delta X
     *
     * @return
     */
    public double calculateSlope() {
        double deltaY = m_final_height - m_initial_height;
        double M = deltaY / UnitConverstion.convertKmToMeters(m_length);
        double angle = Math.asin(M);
        return angle;

    }

    public boolean validate() {
        if (m_segment_index <= 0 || m_initial_height < 0 || m_final_height < 0
                || m_length <= 0 || m_wind_direction < -180 || m_wind_direction > 180
                || m_wind_speed < 0 || m_maximum_velocity < 0 || m_minimum_velocity < 0) {
            throw new IllegalArgumentException("Segment is not valid.");
        }
        return true;
    }

    public Segment reverseSegment(int index) {
        Segment seg = new Segment();
        seg.m_segment_index = index;
        seg.m_initial_height = this.m_final_height;
        seg.m_final_height = this.m_initial_height;
        seg.m_length = this.m_length;
        if (this.m_wind_direction + 180 > 180) {
            seg.m_wind_direction = this.m_wind_direction - 180;
        } else {
            seg.m_wind_direction = this.m_wind_direction + 180;
        }
        seg.m_wind_speed = m_wind_speed;
        seg.m_maximum_velocity = m_maximum_velocity;
        seg.m_minimum_velocity = m_minimum_velocity;
        return seg;

    }
}
