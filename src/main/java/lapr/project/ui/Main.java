package lapr.project.ui;

import java.io.File;
import java.io.FileNotFoundException;
import lapr.project.model.Project;
import lapr.project.model.Vehicle;
import lapr.project.model.VehicleCombustion;
import lapr.project.utils.ExportHTML;
import lapr.project.utils.ImportException;
import lapr.project.utils.NetworkXML;
import lapr.project.utils.VehicleXML;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ImportException, FileNotFoundException {

        boolean DEBUG = true;
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Mockup(DEBUG).setVisible(true);
            }
        });

//        Project project = new Project();
//        
//        VehicleXML vXML = new VehicleXML();
//        File f = new File("TestSet02_Vehicles.xml");
//        vXML.importVehicles(f);
//        
//        for (Vehicle vehicle : vXML.getVehiclesList()) {
//            project.addVehicle(vehicle);
//        }
//        
//        NetworkXML netXML = new NetworkXML();
//        File f2 = new File("TestSet02_Network_corrigido.xml");
//        netXML.importNetwork(project, f2);
//        
//        ExportHTML export = new ExportHTML();
//        export.exportProject(project, "C:\\Users\\Miguel\\Documents\\ISEP\\2º Ano\\LAPR3\\test.html");
    }
}
