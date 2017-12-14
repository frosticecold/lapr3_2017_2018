package lapr.project.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import lapr.project.model.Energy;
import lapr.project.model.Gear;
import lapr.project.model.Gearbox;
import lapr.project.model.Junction;
import lapr.project.model.Regime;
import lapr.project.model.Road;
import lapr.project.model.RoadNetwork;
import lapr.project.model.Section;
import lapr.project.model.Throttle;
import lapr.project.model.Vehicle;
import lapr.project.utils.graphbase.Graph;

public class VehicleXML implements FileFormat {

    private File file;
    private XMLStreamReader reader;
    private String elementContent;

    private List<Vehicle> vehiclesList;
    private List<Throttle> throttleList;
    private Vehicle vehicle;
    private Energy energy;
    private Throttle throttle;
    private Regime regime;
    private Gearbox gearbox;
    private Gear gear;

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

            case "energy": {
                createEnergy();
                break;
            }

            case "gear_list": {
                createGearbox();
                break;
            }

            case "gear": {
                createGear();
                break;
            }

            case "throttle_list": {
                createThrottleList();
                break;
            }

            case "throttle": {
                createThrottle();
                break;
            }

            case "regime": {
                createRegime();
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

            case "energy": {
                addEnergyToVehicle();
                break;
            }

            case "min_rpm": {
                vehicle.getEnergy().setMinRpm(Double.parseDouble(this.elementContent));
                break;
            }

            case "max_rpm": {
                vehicle.getEnergy().setMaxRpm(Double.parseDouble(this.elementContent));
                break;
            }

            case "final_drive_ratio": {
                vehicle.getEnergy().setFinalDriveRatio(Double.parseDouble(this.elementContent));
                break;
            }

            case "gears_list": {
                addGearboxToVehicle();
                break;
            }

            case "gear": {
                addGearToVehicle();
                break;
            }

            case "ratio": {
                gear.setRatio(Double.parseDouble(this.elementContent));
                break;
            }

            case "torque": {
                regime.setTorque(Double.parseDouble(this.elementContent));
                break;
            }

            case "rpm_low": {
                regime.setRpmLow(Double.parseDouble(this.elementContent));
                break;
            }

            case "rpm_high": {
                regime.setRpmHigh(Double.parseDouble(this.elementContent));
                break;
            }

            case "SFC": {
                regime.setSFC(Double.parseDouble(this.elementContent));
                break;
            }

            case "throttle": {
                addThrottleToVehicle();
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
        this.energy = new Energy();
        vehicle.setEnergy(energy);
    }

    private void createGearbox() {
        this.gearbox = new Gearbox();
        vehicle.getEnergy().setGearsList(gearbox);
    }

    private void createGear() {
        String read = reader.getAttributeValue(null, "id");
        int id = Integer.parseInt(read);
        this.gear = new Gear();
        gear.setGearID(id);
    }

    private void createThrottleList() {
        this.throttleList = new LinkedList<>();
        vehicle.getEnergy().setThrottleList(throttleList);
    }

    private void createThrottle() {
        String read = reader.getAttributeValue(null, "id");
        int id = Integer.parseInt(read);
        this.throttle = new Throttle();
        throttle.setThrottleId(id);
    }

    private void createRegime() {
        this.regime = new Regime();
        throttle.getRegimeList().add(regime);
    }

    private void addVehicleToList() {
        this.vehiclesList.add(this.vehicle);
        this.vehicle = null;
    }

    private void addEnergyToVehicle() {
        this.energy = null;
    }

    private void addGearboxToVehicle() {
        this.gearbox = null;
    }

    private void addGearToVehicle() {
        vehicle.getEnergy().getGearList().addGear(gear);
        this.gear = null;
    }

    private void addThrottleToVehicle() {
        vehicle.getEnergy().getThrottleList().add(throttle);
        this.throttle = null;
    }

    public List<Vehicle> getVehiclesList() {
        return vehiclesList;
    }

    private void addMass() {
        String[] split = this.elementContent.split("");
        this.elementContent = split[0];
        double mass = Double.parseDouble(elementContent);
        elementContent = split[1];
        mass = unitConversion(mass, elementContent);
        vehicle.setMass(mass);
    }

    private void addLoad() {
        String[] split = this.elementContent.split("");
        this.elementContent = split[0];
        double load = Double.parseDouble(elementContent);
        elementContent = split[1];
        load = unitConversion(load, elementContent);
        vehicle.setLoad(load);
    }

    private double unitConversion(double value, String unity) {
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

    @Override
    public RoadNetwork importNetwork(File file) throws FileNotFoundException, ImportException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
