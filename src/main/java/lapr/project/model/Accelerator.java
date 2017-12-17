package lapr.project.model;

import java.util.HashMap;
import java.util.Map;

public class Accelerator {

    private Map<Integer, Throttle> m_throttle_list;

    public Accelerator() {
        this.m_throttle_list = new HashMap<>();
    }

    public Map<Integer, Throttle> getThrottleList() {
        return m_throttle_list;
    }

    public void setThrottleList(Map<Integer, Throttle> m_throttle_list) {
        this.m_throttle_list = m_throttle_list;
    }

    /**
     * Validate the accelerator pedal.
     *
     * @return True if accelerator pedal is valid else returns false.
     */
    public boolean validateAcceleratorPedal() {

        if (this.m_throttle_list.entrySet().isEmpty()) {
            throw new IllegalArgumentException(
                    "The accelerator pedal must have at least one throttle.");
        }

        return true;
    }

    @Override
    public String toString() {
        return "Accelerator{" + "m_throttle_list=" + m_throttle_list + '}';
    }

}
