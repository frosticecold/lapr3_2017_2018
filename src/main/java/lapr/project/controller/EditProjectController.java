package lapr.project.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.model.Junction;
import lapr.project.model.Project;
import lapr.project.model.Road;
import lapr.project.model.Section;
import lapr.project.model.Vehicle;
import lapr.project.utils.ImportException;
import lapr.project.utils.NetworkXML;
import lapr.project.utils.Pair;
import lapr.project.utils.Session;
import lapr.project.utils.VehicleXML;
import lapr.project.utils.graphbase.Graph;

public class EditProjectController {

    private Project project;
    private List<Vehicle> newVehiclesList;
    private Graph<Junction, Section> newRoadNetwork;
    private List<Road> newRoadList;

    public EditProjectController() {
        project = Session.getActiveProject();
        newVehiclesList = new ArrayList<>();
    }

    public void editNewProject(String name, String description) {
        project.setName(name);
        project.setDescription(description);
        addVehicles();
    }

    private void addVehicles() {

//        List<Vehicle> listVehicles = new LinkedList<>();
//
//        for (Vehicle vehicle : newVehiclesList) {
//            for (Vehicle vehicle2 : project.getListVehicles().getVehicleList()) {
//                String vehicleName = vehicle.getName();
//                if (vehicleName.equalsIgnoreCase(vehicle2.getName())) {
//                    try {
//                        if (!vehicle.equals(vehicle2)) {
//                            vehicle.setVehicleCounter(vehicle.getVehicleCounter() + 1);
//                            vehicle.setName(vehicleName + vehicle.getVehicleCounter());
//                            listVehicles.add(vehicle);
//                            System.out.println(vehicle);
//                        }
//                    } catch (NumberFormatException ex) {
//                        throw new IllegalArgumentException("Counter is not a number");
//                    }
//                }
//            }
//        }
//
//        for (Vehicle vehicle : listVehicles) {
//            project.getListVehicles().getVehicleList().add(vehicle);
//        }

        for (Vehicle v : newVehiclesList) {
            project.addVehicle(v);
        }
    }

    public String getActiveProjectDescription(){
        return project.getDescription();
    }
    
    public String getActiveProjectName() {
        return project.getName();
    }

    public Project getActiveProject() {
        return project;
    }

    public void addVehicles(File file) throws FileNotFoundException, ImportException {
        VehicleXML vxml = new VehicleXML();
        List<Vehicle> importVehicles = vxml.importVehicles(file);

        if (!importVehicles.isEmpty()) {
            for (Vehicle v : importVehicles) {
                newVehiclesList.add(v);
            }
        } else {
            throw new ImportException();
        }
    }

    public void addRoadNetwork(File file) throws FileNotFoundException, ImportException {
        try {
            NetworkXML xml = new NetworkXML();
            Pair<Graph<Junction, Section>, List<Road>> pair;
            pair = xml.importNetwork(file);
            newRoadNetwork = pair.getFirstElement();
            newRoadList = pair.getSecondElement();
        } catch (ImportException ex) {
            Logger.getLogger(EditProjectController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Vehicle> getNewVehiclesList() {
        return newVehiclesList;
    }

    public Graph<Junction, Section> getNewRoadNetwork() {
        return newRoadNetwork;
    }

    public List<Road> getNewRoadList() {
        return newRoadList;
    }

}
