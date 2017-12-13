package lapr.project.ui;

import java.text.ParseException;
import lapr.project.model.Project;
import lapr.project.model.Vehicle;
import lapr.project.utils.XMLReader;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        Project project = new Project();
        
        XMLReader xmlFile = new XMLReader("TestSet01_Vehicles.xml", null);
        project = xmlFile.readValuesFromXML(project);
        
        for (Vehicle vehicle : project.getM_list_vehicles()) {
            System.out.println(vehicle);
        }
    }
    
}
