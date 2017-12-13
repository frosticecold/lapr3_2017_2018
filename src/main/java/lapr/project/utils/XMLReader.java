package lapr.project.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import lapr.project.model.Junction;
import lapr.project.model.Project;
import lapr.project.model.Vehicle;
import lapr.project.model.VehicleType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XMLReader {

    private String vehiclesFile = "";
    private String roadNetworkFile = "";

    /**
     *
     * @param fileXML
     */
    public XMLReader(String vehiclesFile, String roadNetworkFile) {
        if (testFilepath(vehiclesFile) || testFilepath(roadNetworkFile)) {
            this.vehiclesFile = vehiclesFile;
            this.roadNetworkFile = roadNetworkFile;
        }
    }

    public Project readValuesFromXML(Project project) throws ParseException {
        try {
            File vehicleFile = new File(vehiclesFile);

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            InputSource inputSource = new InputSource();
            inputSource.setCharacterStream(new FileReader(vehicleFile));

//            File networkFile = new File(roadNetworkFile);
//            
//            DocumentBuilderFactory documentBuilderFactory2 = DocumentBuilderFactory.newInstance();
//            DocumentBuilder documentBuilder2 = documentBuilderFactory2.newDocumentBuilder();
//            
//            InputSource inputSource2 = new InputSource();
//            inputSource.setCharacterStream(new FileReader(networkFile));
            Document document = documentBuilder.parse(inputSource);
//            Document document2 = documentBuilder2.parse(inputSource2);

            NodeList vehiclesList = document.getElementsByTagName("vehicle_list");
            insertVehicles(project, vehiclesList);

//            NodeList roadNetworkList = document2.getElementsByTagName("Network");
        } catch (FileNotFoundException e) {
            Logger.getLogger(XMLReader.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "Ficheiro não encontrado");
        } catch (IOException | ParserConfigurationException | SAXException e) {
            Logger.getLogger(XMLReader.class.getName()).log(Level.SEVERE, null, e);
        }
        return project;
    }

    private void insertVehicles(Project project, NodeList vehiclesList) throws ParseException {
        for (int i = 0; i < vehiclesList.getLength(); i++) {          
            Vehicle vehicle = new Vehicle("");
            LinkedList<Vehicle> m_list_vehicles = new LinkedList<>();
            Element title = (Element) vehiclesList.item(i);
            String vehicleType = title.getElementsByTagName("type").item(0).getTextContent();
            
            System.out.println(vehicleType);
            
            vehicle.setType(vehicleType);
            project.setM_list_vehicles(m_list_vehicles);
            project.getM_list_vehicles().add(vehicle);
        }
    }

    private void insertJunctions(Project project) throws ParseException {

    }

    /**
     *
     * @param filepath
     * @return
     */
    public boolean testFilepath(String filepath) {
        File file = new File(filepath);
        if (!file.exists() || (file.exists() && file.isDirectory())) {
            System.out.println("File not found. Using default file");
            return false;
        }
        return true;
    }

}
