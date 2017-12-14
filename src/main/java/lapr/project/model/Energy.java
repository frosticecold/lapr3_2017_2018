package lapr.project.model;

import java.util.List;

public class Energy {
    
    private double m_min_rpm;
    private double m_max_rpm;
    private double m_final_drive_ratio;
    private Gearbox m_gears_list;
    private List<Throttle> m_throttle_list;

    public Energy(double m_min_rpm, double m_max_rpm, double m_final_drive_ratio, Gearbox m_gears_list, List<Throttle> m_throttle_list) {
        this.m_min_rpm = m_min_rpm;
        this.m_max_rpm = m_max_rpm;
        this.m_final_drive_ratio = m_final_drive_ratio;
        this.m_gears_list = m_gears_list;
        this.m_throttle_list = m_throttle_list;
    }

    public Energy() {
    }

    public Gearbox getGearList() {
        return m_gears_list;
    }

    public List<Throttle> getThrottleList() {
        return m_throttle_list;
    }
    
    public void setMinRpm(double m_min_rpm) {
        this.m_min_rpm = m_min_rpm;
    }

    public void setMaxRpm(double m_max_rpm) {
        this.m_max_rpm = m_max_rpm;
    }

    public void setFinalDriveRatio(double m_final_drive_ratio) {
        this.m_final_drive_ratio = m_final_drive_ratio;
    }

    public void setGearsList(Gearbox m_gears_list) {
        this.m_gears_list = m_gears_list;
    }

    public void setThrottleList(List<Throttle> m_throttle_list) {
        this.m_throttle_list = m_throttle_list;
    }

    @Override
    public String toString() {
        return "Energy{" + "m_min_rpm=" + m_min_rpm + ", m_max_rpm=" + m_max_rpm + ", m_final_drive_ratio=" + m_final_drive_ratio + ", m_gears_list=" + m_gears_list + ", m_throttle_list=" + m_throttle_list + '}';
    }
}
