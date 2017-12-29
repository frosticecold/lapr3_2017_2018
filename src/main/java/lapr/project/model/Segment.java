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
        this.setSegmentIndex(segmentIndex);
        this.setInitialHeight(initialHeight);
        this.setFinalHeight(finalHeight);
        this.setLength(length);
        this.setWindDirection(windDirection);
        this.setWindSpeed(windSpeed);
        this.setMaximumVelocity(maximumVelocity);
        this.setMinimumVelocity(minimumVelocity);
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
        if (segmentIndex <= 0) {
            throw new IllegalArgumentException("Segment index must be greater than zero.");
        }
        this.segmentIndex = segmentIndex;
    }

    /**
     * @param initialHeight the m_initial_height to set
     */
    public void setInitialHeight(double initialHeight) {
        if (initialHeight < 0) {
            throw new IllegalArgumentException("Initial height must be equal or greater than zero");
        }
        this.initialHeight = initialHeight;
    }

    /**
     *
     * @param finalHeight
     */
    public void setFinalHeight(double finalHeight) {
        if (finalHeight < 0) {
            throw new IllegalArgumentException("Final height must be equal or greater than zero");
        }
        this.finalHeight = finalHeight;
    }

    /**
     * @param length the m_length to set
     */
    public void setLength(double length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be equal or greater than zero.");
        }
        this.length = length;
    }

    /**
     * @param windDirection the m_wind_direction to set
     */
    public void setWindDirection(double windDirection) {
        if (windDirection <= -180 || windDirection >= 180) {
            throw new IllegalArgumentException("Invalid windDirection");
        }
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
        if (maximumVelocity < 0) {
            throw new IllegalArgumentException("Maximum velocity must be greater than zero.");
        }
        this.maximumVelocity = maximumVelocity;
    }

    /**
     * @param minimumVelocity the m_minimum_velocity to set
     */
    public void setMinimumVelocity(double minimumVelocity) {
        if (minimumVelocity < 0) {
            throw new IllegalArgumentException("Minimum velocity must be greater than zero.");
        }
        this.minimumVelocity = minimumVelocity;
    }

    /**
     * M = Delta Y / Delta X
     *
     * @return
     */
    public double calculateSlope() {
        double deltaY = finalHeight - initialHeight;
        double diagonal = deltaY / UnitConversion.convertKmToMeters(length);
        return Math.toDegrees(Math.asin(diagonal));

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
