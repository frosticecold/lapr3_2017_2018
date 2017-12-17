package lapr.project.ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.model.Project;
import lapr.project.model.Vehicle;
import lapr.project.utils.ImportException;
import lapr.project.utils.NetworkXML;
import lapr.project.utils.VehicleXML;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        
        String s= "1.5 km";
        Double d = Double.parseDouble(s.split(" ")[0]);
        Project p = new Project();
        NetworkXML xml = new NetworkXML();
        File file = new File("TestSet02_Network_corrigido.xml");
        try {
            xml.importNetwork(p, file);
        } catch (FileNotFoundException ex) {
            System.out.println("Ficheiro não encontrado");
        } catch (ImportException ex) {
            System.out.println("Erro importação");
        }

//        /*Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Mockup().setVisible(true);
//            }
//        });
    }

}
