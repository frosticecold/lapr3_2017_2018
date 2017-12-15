package lapr.project.ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import lapr.project.model.Project;
import lapr.project.model.RoadNetwork;
import lapr.project.model.Throttle;
import lapr.project.model.Vehicle;
import lapr.project.utils.ImportException;
import lapr.project.utils.NetworkXML;
import lapr.project.utils.VehicleXML;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException, FileNotFoundException, ImportException {
        Project project = new Project();

        VehicleXML xmlFile = new VehicleXML();
        File file = new File("TestSet01_Vehicles.xml");
        xmlFile.importVehicles(file);

        for (Vehicle vehicle : xmlFile.getVehiclesList()) {
            System.out.println(vehicle.getEnergy());
        }
        
//        NetworkXML xmlnetwork = new NetworkXML();
//        File file = new File("TestSet01_Network.xml");
//        RoadNetwork r = xmlnetwork.importNetwork(file);
//        
//        System.out.println(r);
    }

}


