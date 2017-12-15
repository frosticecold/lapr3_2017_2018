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
import lapr.project.model.Regime;
import lapr.project.model.RoadNetwork;
import lapr.project.model.Throttle;
import lapr.project.model.Vehicle;

public class VehicleXML implements FileFormat {

    private final String VEHICLE_TAG = "vehicle";
    private final String ENERGY_TAG = "energy";
    private final String GEARBOX_TAG = "gear_list";
    private final String GEAR_TAG = "gear";
    private final String THROTTLE_LIST_TAG = "throttle_list";
    private final String THROTTLE_TAG = "throttle";
    private final String REGIME_TAG = "regime";
    private final String TYPE_TAG = "type";
    private final String TOLL_CLASS_TAG = "toll_class";
    private final String MOTORIZATION_TAG = "motorization";
    private final String FUEL_TAG = "fuel";
    private final String MASS_TAG = "mass";
    private final String LOAD_TAG = "load";
    private final String DRAG_TAG = "drag";
    private final String FRONTAL_AREA_TAG = "frontal_area";
    private final String RCC_TAG = "rcc";
    private final String WHEEL_SIZE_TAG = "wheel_size";
    private final String MIN_RPM_TAG = "min_rpm";
    private final String MAX_RPM_TAG = "max_rpm";
    private final String FINAL_DRIVE_RATIO_TAG = "final_drive_ratio";
    private final String RATIO_TAG = "ratio";
    private final String TORQUE_TAG = "torque";
    private final String RPM_LOW_TAG = "rpm_low";
    private final String RPM_HIGH_TAG = "rpm_high";
    private final String SFC_TAG = "SFC";

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
            case VEHICLE_TAG: {
                createVehicle();
                break;
            }

            case ENERGY_TAG: {
                createEnergy();
                break;
            }

            case GEARBOX_TAG: {
                createGearbox();
                break;
            }

            case GEAR_TAG: {
                createGear();
                break;
            }

            case THROTTLE_LIST_TAG: {
                createThrottleList();
                break;
            }

            case THROTTLE_TAG: {
                createThrottle();
                break;
            }

            case REGIME_TAG: {
                createRegime();
                break;
            }
        }
    }

    private void checkVehicleEndElement() {
        switch (reader.getLocalName()) {
            case VEHICLE_TAG: {
                addVehicleToList();
                break;
            }

            case TYPE_TAG: {
                vehicle.setType(this.elementContent);
                break;
            }

            case TOLL_CLASS_TAG: {
                addTollClass();
                break;
            }

            case MOTORIZATION_TAG: {
                vehicle.setMotorization(this.elementContent);
                break;
            }

            case FUEL_TAG: {
                vehicle.setFuel(this.elementContent);
                break;
            }

            case MASS_TAG: {
                addMass();
                break;
            }

            case LOAD_TAG: {
                addLoad();
                break;
            }

            case DRAG_TAG: {
                vehicle.setDragCoefficient(Double.parseDouble(this.elementContent));
                break;
            }

            case FRONTAL_AREA_TAG: {
                vehicle.setFrontalArea(Double.parseDouble(this.elementContent));
                break;
            }

            case RCC_TAG: {
                vehicle.setRcc(Double.parseDouble(this.elementContent));
                break;
            }

            case WHEEL_SIZE_TAG: {
                vehicle.setWheelSize(Double.parseDouble(this.elementContent));
                break;
            }

            case ENERGY_TAG: {
                addEnergyToVehicle();
                break;
            }

            case MIN_RPM_TAG: {
                vehicle.getEnergy().setMinRpm(Double.parseDouble(this.elementContent));
                break;
            }

            case MAX_RPM_TAG: {
                vehicle.getEnergy().setMaxRpm(Double.parseDouble(this.elementContent));
                break;
            }

            case FINAL_DRIVE_RATIO_TAG: {
                vehicle.getEnergy().setFinalDriveRatio(Double.parseDouble(this.elementContent));
                break;
            }

            case GEARBOX_TAG: {
                addGearboxToVehicle();
                break;
            }

            case GEAR_TAG: {
                addGearToVehicle();
                break;
            }

            case RATIO_TAG: {
                gear.setRatio(Double.parseDouble(this.elementContent));
                break;
            }

            case TORQUE_TAG: {
                regime.setTorque(Double.parseDouble(this.elementContent));
                break;
            }

            case RPM_LOW_TAG: {
                regime.setRpmLow(Double.parseDouble(this.elementContent));
                break;
            }

            case RPM_HIGH_TAG: {
                regime.setRpmHigh(Double.parseDouble(this.elementContent));
                break;
            }

            case SFC_TAG: {
                regime.setSFC(Double.parseDouble(this.elementContent));
                break;
            }

            case THROTTLE_TAG: {
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
