package lapr.project.model;

import java.util.HashMap;
import java.util.Map;

public class Accelerator {

    private Map<Integer, Throttle> throttleList;

    public Accelerator() {
        this.throttleList = new HashMap<>();
    }

    public Accelerator(Accelerator acc) {
        this.throttleList = new HashMap<>();
        for (Integer i : acc.throttleList.keySet()) {
            this.throttleList.put(i, acc.throttleList.get(i));
        }
    }

    public Map<Integer, Throttle> getThrottleList() {
        return throttleList;
    }

    public void setThrottleList(Map<Integer, Throttle> m_throttle_list) {
        this.throttleList = m_throttle_list;
    }

    /**
     * Validate the accelerator pedal.
     *
     * @return True if accelerator pedal is valid else returns false.
     */
    public boolean validateAcceleratorPedal() {

        if (this.throttleList.entrySet().isEmpty()) {
            throw new IllegalArgumentException(
                    "The accelerator pedal must have at least one throttle.");
        }

        return true;
    }

    @Override
    public String toString() {
        return "Accelerator{" + "m_throttle_list=" + throttleList + '}';
    }
}
