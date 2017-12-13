package lapr.project.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import lapr.project.model.Vehicle;

public class VehicleXML implements FileFormat {

    private File file;
    private XMLStreamReader reader;
    private String elementContent;

    private List<Vehicle> vehiclesList;
    private Vehicle vehicle;

    public VehicleXML() {

    }

    public List<Vehicle> importVehicles(File file) throws FileNotFoundException, ImportException {
        if (file == null || !file.exists()) {
            throw new FileNotFoundException("Must be an existing file.");
        }
        this.file = file;

        try {
            this.importXML();
        } catch (XMLStreamException ex) {
            throw new ImportException(ex);
        }
        return this.vehiclesList;
    }

    private void importXML() throws FileNotFoundException, XMLStreamException {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        this.reader = factory.createXMLStreamReader(new FileInputStream(this.file));
        this.elementContent = null;
        while (this.reader.hasNext()) {
            int xmlEvent = this.reader.next();
            identifyXml(xmlEvent);
        }
    }

    private void identifyXml(int xmlEvent) throws XMLStreamException {
        switch (xmlEvent) {
            case XMLStreamConstants.START_ELEMENT: {
                switch (this.reader.getLocalName()) {
                    case "vehicle_list": {
                        this.vehiclesList = new ArrayList<>();
                        readVehicleElements();
                        break;
                    }
                }
            }
        }
    }

    private void readVehicleElements() throws XMLStreamException {
        while (this.reader.hasNext()) {
            int xmlEvent = this.reader.next();
            checkVehicleElements(xmlEvent);
        }
    }

    private void checkVehicleElements(int xmlEvent) {
        switch (xmlEvent) {
            case XMLStreamConstants.START_ELEMENT: {
                checkVehicleStartElement();
                break;
            }
            case XMLStreamConstants.CHARACTERS: {
                this.elementContent = getElementText();
                break;
            }
            case XMLStreamConstants.END_ELEMENT: {
                checkVehicleEndElement();
                if (this.elementContent != null) {
                    this.elementContent = null;
                }
                break;
            }
        }
    }

    private String getElementText() {
        return this.reader.getText().trim();
    }

    private void checkVehicleStartElement() {
        switch (reader.getLocalName()) {
            case "vehicle": {
                createVehicle();
                break;
            }
            
            case "energy":{
                createEnergy();
                break;
            }
            
//            case "velocity_limit_list":{
//                createVelocityList();
//                break;
//            }
        }
    }

    private void checkVehicleEndElement() {
        switch (reader.getLocalName()) {
            case "vehicle": {
                addVehicleToList();
                break;
            }

            case "type": {
                vehicle.setType(this.elementContent);
                break;
            }

            case "toll_class": {
                addTollClass();
                break;
            }

            case "motorization": {
                vehicle.setMotorization(this.elementContent);
                break;
            }

            case "fuel": {
                vehicle.setFuel(this.elementContent);
                break;
            }

            case "mass": {
                addMass();
                break;
            }

            case "load": {
                addLoad();
                break;
            }

            case "drag": {
                vehicle.setDragCoefficient(Double.parseDouble(this.elementContent));
                break;
            }

            case "frontal_area": {
                vehicle.setFrontalArea(Double.parseDouble(this.elementContent));
                break;
            }

            case "rcc": {
                vehicle.setRcc(Double.parseDouble(this.elementContent));
                break;
            }

            case "wheel_size": {
                vehicle.setWheelSize(Double.parseDouble(this.elementContent));
                break;
            }
        }
    }

    private void createVehicle() {
        String name = reader.getAttributeValue(null, "name");
        String description = reader.getAttributeValue(null, "description");
        this.vehicle = new Vehicle();
        this.vehicle.setName(name);
        this.vehicle.setDescription(description);
    }
    
    private void createEnergy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void addVehicleToList() {
        this.vehiclesList.add(this.vehicle);
        this.vehicle = null;
    }

    public List<Vehicle> getVehiclesList() {
        return vehiclesList;
    }

    private void addMass() {
        String[] split = this.elementContent.split("");
        this.elementContent = split[0];
        double mass = Double.parseDouble(elementContent);
        elementContent = split[1];
        mass = unityConversion(mass, elementContent);
        vehicle.setMass(mass);
    }

    private void addLoad() {
        String[] split = this.elementContent.split("");
        this.elementContent = split[0];
        double load = Double.parseDouble(elementContent);
        elementContent = split[1];
        load = unityConversion(load, elementContent);
        vehicle.setLoad(load);
    }

    private double unityConversion(double value, String unity) {
        unity.toLowerCase();
        switch (unity) {
            case "g": {
                value = value * 1000;
            }
        }
        return value;
    }

    private void addTollClass() {
        int toll_class = Integer.parseInt(elementContent);
        vehicle.setVehicleClass(toll_class);
    }

}
