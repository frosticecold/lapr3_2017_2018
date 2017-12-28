package lapr.project.model;

import lapr.project.calculations.UnitConversion;

public class Segment {

    private int segmentIndex;
    private double initialHeight;
    private double finalHeight;
    private double length;
    private double windDirection;
    private double windSpeed;
    private double maximumVelocity;
    private double minimumVelocity;

    public Segment() {
    }

    public Segment(int segmentIndex, double initialHeight, double finalHeight, double length, double windDirection, double windSpeed, double maximumVelocity, double minimumVelocity) {
        this.segmentIndex = segmentIndex;
        this.initialHeight = initialHeight;
        this.finalHeight = finalHeight;
        this.length = length;
        this.windDirection = windDirection;
        this.windSpeed = windSpeed;
        this.maximumVelocity = maximumVelocity;
        this.minimumVelocity = minimumVelocity;
    }

    public Segment(Segment s) {
        this.segmentIndex = s.segmentIndex;
        this.initialHeight = s.initialHeight;
        this.finalHeight = s.finalHeight;
        this.length = s.length;
        this.windDirection = s.windDirection;
        this.windSpeed = s.windSpeed;
        this.maximumVelocity = s.maximumVelocity;
        this.minimumVelocity = s.minimumVelocity;
    }

    public double getLength() {
        return length;
    }

    /**
     * @return the m_segment_index
     */
    public int getSegmentIndex() {
        return segmentIndex;
    }

    /**
     * @return the m_initial_height
     */
    public double getInitialHeight() {
        return initialHeight;
    }

    public double getFinalHeight() {
        return finalHeight;
    }

    /**
     * @return the m_wind_direction
     */
    public double getWindDirection() {
        return windDirection;
    }

    /**
     * @return the m_wind_speed
     */
    public double getWindSpeed() {
        return windSpeed;
    }

    /**
     * @return the m_maximum_velocity
     */
    public double getMaximumVelocity() {
        return maximumVelocity;
    }

    /**
     * @return the m_minimum_velocity
     */
    public double getMinimumVelocity() {
        return minimumVelocity;
    }

    /**
     * @param segmentIndex the m_segment_index to set
     */
    public void setSegmentIndex(int segmentIndex) {
        this.segmentIndex = segmentIndex;
    }

    /**
     * @param initialHeight the m_initial_height to set
     */
    public void setInitialHeight(double initialHeight) {
        this.initialHeight = initialHeight;
    }

    /**
     *
     * @param finalHeight
     */
    public void setFinalHeight(double finalHeight) {
        this.finalHeight = finalHeight;
    }

    /**
     * @param length the m_length to set
     */
    public void setLength(double length) {
        this.length = length;
    }

    /**
     * @param windDirection the m_wind_direction to set
     */
    public void setWindDirection(double windDirection) {
        this.windDirection = windDirection;
    }

    /**
     * @param windSpeed the m_wind_speed to set
     */
    public void setWindSpeed(double windSpeed) {
        if (windSpeed < 0) {
            throw new IllegalArgumentException("Wind speed must be equal or greater than zero.");
        }
        this.windSpeed = windSpeed;
    }

    /**
     * @param maximumVelocity the m_maximum_velocity to set
     */
    public void setMaximumVelocity(double maximumVelocity) {
        this.maximumVelocity = maximumVelocity;
    }

    /**
     * @param minimumVelocity the m_minimum_velocity to set
     */
    public void setMinimumVelocity(double minimumVelocity) {
        this.minimumVelocity = minimumVelocity;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.segmentIndex;
        hash = 61 * hash + (int) (Double.doubleToLongBits(this.initialHeight) ^ (Double.doubleToLongBits(this.initialHeight) >>> 32));
        hash = 61 * hash + (int) (Double.doubleToLongBits(this.finalHeight) ^ (Double.doubleToLongBits(this.finalHeight) >>> 32));
        hash = 61 * hash + (int) (Double.doubleToLongBits(this.length) ^ (Double.doubleToLongBits(this.length) >>> 32));
        hash = 61 * hash + (int) (Double.doubleToLongBits(this.windDirection) ^ (Double.doubleToLongBits(this.windDirection) >>> 32));
        hash = 61 * hash + (int) (Double.doubleToLongBits(this.windSpeed) ^ (Double.doubleToLongBits(this.windSpeed) >>> 32));
        hash = 61 * hash + (int) (Double.doubleToLongBits(this.maximumVelocity) ^ (Double.doubleToLongBits(this.maximumVelocity) >>> 32));
        hash = 61 * hash + (int) (Double.doubleToLongBits(this.minimumVelocity) ^ (Double.doubleToLongBits(this.minimumVelocity) >>> 32));
        return hash;
    }

    /**
     * M = Delta Y / Delta X
     *
     * @return
     */
    public double calculateSlope() {
        double deltaY = finalHeight - initialHeight;
        double diagonal = deltaY / UnitConversion.convertKmToMeters(length);
        double angle = Math.toDegrees(Math.asin(diagonal));
        return angle;

    }

    public boolean validate() {
        if (segmentIndex <= 0 || initialHeight < 0 || finalHeight < 0) {
            throw new IllegalArgumentException("Segment is not valid.");
        }
        if (length <= 0 || windDirection < -180 || windDirection > 180) {

            throw new IllegalArgumentException("Segment is not valid.");
        }
        if (windSpeed < 0 || maximumVelocity < 0 || minimumVelocity < 0) {

            throw new IllegalArgumentException("Segment is not valid.");
        }
        return true;
    }

    public Segment reverseSegment(int index) {
        Segment seg = new Segment();
        seg.segmentIndex = index;
        seg.initialHeight = this.finalHeight;
        seg.finalHeight = this.initialHeight;
        seg.length = this.length;
        if (this.windDirection + 180 > 180) {
            seg.windDirection = this.windDirection - 180;
        } else {
            seg.windDirection = this.windDirection + 180;
        }
        seg.windSpeed = windSpeed;
        seg.maximumVelocity = maximumVelocity;
        seg.minimumVelocity = minimumVelocity;
        return seg;
    }

    @Override
    public String toString() {
        return "Segment{" + "m_segment_index=" + segmentIndex + ", m_initial_height=" + initialHeight + ", m_final_height=" + finalHeight + ", m_length=" + length + ", m_wind_direction=" + windDirection + ", m_wind_speed=" + windSpeed + ", m_maximum_velocity=" + maximumVelocity + ", m_minimum_velocity=" + minimumVelocity + '}';
    }

}
