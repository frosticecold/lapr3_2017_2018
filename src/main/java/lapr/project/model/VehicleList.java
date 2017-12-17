package lapr.project.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public class VehicleList {

    /**
     * List of vehicles.
     */
    private List<Vehicle> m_list_of_vehicles;

    /**
     * List of vehicle names already on the list for id generation.
     */
    private Map<String, Integer> m_map_of_vehicle_names;

    /**
     * Creates a list of vehicles with an empty list.
     */
    public VehicleList() {
        this.m_list_of_vehicles = new ArrayList<>();
        this.m_map_of_vehicle_names = new LinkedHashMap<>();
    }

    /**
     * Creates a clone of the target vehicleList
     *
     * @param source The target vehicle list to clone.
     */
    public VehicleList(VehicleList source) {
        m_list_of_vehicles = new ArrayList<>();
        Collections.copy(source.m_list_of_vehicles, m_list_of_vehicles);
    }

    /**
     * Returns the number of vehicles in the list.
     *
     * @return Number of vehicles in the list.
     */
    public int size() {
        return this.m_list_of_vehicles.size();
    }

    /**
     * Add a vehicle to the vehicles list.
     *
     * @param vehicle Vehicle to be added.
     * @return True if the vehicle was added , otherwise returns false.
     */
    public boolean addVehicle(Vehicle vehicle) {
        if (!this.m_list_of_vehicles.contains(vehicle)) {
            while (this.m_map_of_vehicle_names.containsKey(vehicle.getName())) {
                int vehicleCount = this.m_map_of_vehicle_names.get(vehicle.getName());

                vehicle.setName(vehicle.getName() + " " + ++vehicleCount);
            }

            this.m_map_of_vehicle_names.put(vehicle.getName(), 1);
            return this.m_list_of_vehicles.add(vehicle);
        }

        return false;
    }

    /**
     * Remove the vehicle received by parameter vehicles list
     *
     * @param vehicle Vehicle to be removed.
     * @return True if the vehicle was added , otherwise returns false.
     */
    public boolean removeVehicle(Vehicle vehicle) {
        return this.m_list_of_vehicles.remove(vehicle);
    }

    /**
     * Returns an iterator to allow running through the list of vehicles.
     *
     * @return Iterator.
     */
    public Iterator<Vehicle> iterator() {
        return this.m_list_of_vehicles.iterator();
    }

    /**
     * Returns a list containing the names of all the vehicles.
     *
     * @return (List&lt;String&gt;) The list with the name.
     */
    public List<String> getVehicleList() {
        List<String> vehicleList = new ArrayList();

        for (Vehicle vehicle : this.m_list_of_vehicles) {
            vehicleList.add(vehicle.getName());
        }

        return vehicleList;
    }

    /**
     * Returns the textual description of the object in the following format:
     *
     * List of vehicles: foreach vehicle { vehicle.toString }
     *
     * @return Textual description of the object.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("List of Vehicles:\n");
        for (Vehicle vehicle : this.m_list_of_vehicles) {
            sb.append(vehicle);
        }

        return sb.toString();
    }

    /**
     * Validate the vehicle list.
     *
     * @return True if vehicle list is valid else returns false.
     */
    public boolean validateVehicleList() {

        if (this.m_list_of_vehicles.isEmpty()) {
            throw new IllegalArgumentException("The project must be at least one vehicle.");
        }

        return true;
    }

}
