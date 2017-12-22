package lapr.project.model;

import lapr.project.calculations.UnitConversion;

public class Segment implements Cloneable {

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

    public Segment(Segment s) {
        this.m_segment_index = s.m_segment_index;
        this.m_initial_height = s.m_initial_height;
        this.m_final_height = s.m_final_height;
        this.m_length = s.m_length;
        this.m_wind_direction = s.m_wind_direction;
        this.m_wind_speed = s.m_wind_speed;
        this.m_maximum_velocity = s.m_maximum_velocity;
        this.m_minimum_velocity = s.m_minimum_velocity;
    }

    public double getLength() {
        return m_length;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.m_segment_index;
        hash = 61 * hash + (int) (Double.doubleToLongBits(this.m_initial_height) ^ (Double.doubleToLongBits(this.m_initial_height) >>> 32));
        hash = 61 * hash + (int) (Double.doubleToLongBits(this.m_final_height) ^ (Double.doubleToLongBits(this.m_final_height) >>> 32));
        hash = 61 * hash + (int) (Double.doubleToLongBits(this.m_length) ^ (Double.doubleToLongBits(this.m_length) >>> 32));
        hash = 61 * hash + (int) (Double.doubleToLongBits(this.m_wind_direction) ^ (Double.doubleToLongBits(this.m_wind_direction) >>> 32));
        hash = 61 * hash + (int) (Double.doubleToLongBits(this.m_wind_speed) ^ (Double.doubleToLongBits(this.m_wind_speed) >>> 32));
        hash = 61 * hash + (int) (Double.doubleToLongBits(this.m_maximum_velocity) ^ (Double.doubleToLongBits(this.m_maximum_velocity) >>> 32));
        hash = 61 * hash + (int) (Double.doubleToLongBits(this.m_minimum_velocity) ^ (Double.doubleToLongBits(this.m_minimum_velocity) >>> 32));
        return hash;
    }

    /**
     * @return the m_segment_index
     */
    public int getSegmentIndex() {
        return m_segment_index;
    }

    /**
     * @return the m_initial_height
     */
    public double getInitialHeight() {
        return m_initial_height;
    }

    public double getFinalHeight() {
        return m_final_height;
    }

    /**
     * @return the m_wind_direction
     */
    public double getWindDirection() {
        return m_wind_direction;
    }

    /**
     * @return the m_wind_speed
     */
    public double getWindSpeed() {
        return m_wind_speed;
    }

    /**
     * @return the m_maximum_velocity
     */
    public double getMaximumVelocity() {
        return m_maximum_velocity;
    }

    /**
     * @return the m_minimum_velocity
     */
    public double getMinimumVelocity() {
        return m_minimum_velocity;
    }

    /**
     * @param segment_index the m_segment_index to set
     */
    public void setSegmentIndex(int segment_index) {
        this.m_segment_index = segment_index;
    }

    /**
     * @param initial_height the m_initial_height to set
     */
    public void setInitialHeight(double initial_height) {
        this.m_initial_height = initial_height;
    }

    /**
     *
     * @param final_height
     */
    public void setFinalHeight(double final_height) {
        this.m_final_height = final_height;
    }

    /**
     * @param length the m_length to set
     */
    public void setLength(double length) {
        this.m_length = length;
    }

    /**
     * @param wind_direction the m_wind_direction to set
     */
    public void setWindDirection(double wind_direction) {
        this.m_wind_direction = wind_direction;
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
    public void setMaximumVelocity(double m_maximum_velocity) {
        this.m_maximum_velocity = m_maximum_velocity;
    }

    /**
     * @param m_minimum_velocity the m_minimum_velocity to set
     */
    public void setMinimumVelocity(double m_minimum_velocity) {
        this.m_minimum_velocity = m_minimum_velocity;
    }

    /**
     * M = Delta Y / Delta X
     *
     * @return
     */
    public double calculateSlope() {
        double deltaY = m_final_height - m_initial_height;
        double M = deltaY / UnitConversion.convertKmToMeters(m_length);
        double angle = Math.toDegrees(Math.asin(M));
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

    @Override
    public String toString() {
        return "Segment{" + "m_segment_index=" + m_segment_index + ", m_initial_height=" + m_initial_height + ", m_final_height=" + m_final_height + ", m_length=" + m_length + ", m_wind_direction=" + m_wind_direction + ", m_wind_speed=" + m_wind_speed + ", m_maximum_velocity=" + m_maximum_velocity + ", m_minimum_velocity=" + m_minimum_velocity + '}';
    }

    public Object copy() throws CloneNotSupportedException {
        return super.clone();
    }

}
