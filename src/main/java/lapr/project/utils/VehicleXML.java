package lapr.project.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import lapr.project.model.VehicleCombustion;
import lapr.project.model.VehicleElectric;
import lapr.project.model.Gear;
import lapr.project.model.Gearbox;
import lapr.project.model.Junction;
import lapr.project.model.Project;
import lapr.project.model.Regime;
import lapr.project.model.Road;
import lapr.project.model.Section;
import lapr.project.model.Throttle;
import lapr.project.model.Vehicle;
import lapr.project.utils.graphbase.Graph;

public class VehicleXML implements FileFormat {

    private static final String VEHICLE_TAG = "vehicle";
    private static final String ELETRIC_VEHICLE_TAG = "electric";
    private static final String COMBUSTION_VEHICLE_TAG = "combustion";
    private static final String ENERGY_TAG = "energy";
    private static final String GEARBOX_TAG = "gear_list";
    private static final String GEAR_TAG = "gear";
    private static final String THROTTLE_LIST_TAG = "throttle_list";
    private static final String THROTTLE_TAG = "throttle";
    private static final String REGIME_TAG = "regime";
    private static final String TYPE_TAG = "type";
    private static final String TOLL_CLASS_TAG = "toll_class";
    private static final String MOTORIZATION_TAG = "motorization";
    private static final String FUEL_TAG = "fuel";
    private static final String MASS_TAG = "mass";
    private static final String LOAD_TAG = "load";
    private static final String DRAG_TAG = "drag";
    private static final String FRONTAL_AREA_TAG = "frontal_area";
    private static final String RRC_TAG = "rrc";
    private static final String WHEEL_SIZE_TAG = "wheel_size";
    private static final String MIN_RPM_TAG = "min_rpm";
    private static final String MAX_RPM_TAG = "max_rpm";
    private static final String FINAL_DRIVE_RATIO_TAG = "final_drive_ratio";
    private static final String RATIO_TAG = "ratio";
    private static final String TORQUE_LOW_TAG = "torque_low";
    private static final String TORQUE_HIGH_TAG = "torque_high";
    private static final String RPM_LOW_TAG = "rpm_low";
    private static final String RPM_HIGH_TAG = "rpm_high";
    private static final String SFC_TAG = "SFC";
    private static final String VELOCITY_LIMIT_LIST_TAG = "velocity_limit_list";
    private static final String VELOCITY_LIMIT_TAG = "velocity_limit";
    private static final String SEGMENT_TYPE_TAG = "segment_type";
    private static final String LIMIT_TAG = "limit";

    private File file;
    private XMLStreamReader reader;
    private String elementContent;

    private List<Vehicle> vehiclesList;
    private Map<Integer, Throttle> throttleList;
    private Vehicle vehicle;
    private Throttle throttle;
    private Regime regime;
    private Gearbox gearbox;
    private Gear gear;
    private int throttleID;
    private String name;
    private String description;
    private String type;
    private int tollClass;
    private Map<String, Double> velocityLimitList;
    private String segmentType;
    private Double velocityLimit;

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
                    default:
                        break;
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
            default:
                break;
        }
    }

    private String getElementText() {
        return this.reader.getText().trim();
    }

    private void checkVehicleStartElement() {
        switch (reader.getLocalName()) {
            case VEHICLE_TAG: {
                saveNameAndDescription();
                break;
            }

            case ENERGY_TAG: {
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

            case VELOCITY_LIMIT_LIST_TAG: {
                this.velocityLimitList = new LinkedHashMap<>();
                break;
            }
            default:
                break;
        }
    }

    private void checkVehicleEndElement() {
        switch (reader.getLocalName()) {
            case VEHICLE_TAG: {
                addVehicleToList();
                break;
            }

            case TYPE_TAG: {
                type = this.elementContent;
                break;
            }

            case TOLL_CLASS_TAG: {
                addTollClass();
                break;
            }

            case MOTORIZATION_TAG: {
                createVehicle();
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

            case RRC_TAG: {
                vehicle.setRCC(Double.parseDouble(this.elementContent));
                break;
            }

            case WHEEL_SIZE_TAG: {
                vehicle.setWheelSize(Double.parseDouble(this.elementContent));
                break;
            }

            case ENERGY_TAG: {
                break;
            }

            case MIN_RPM_TAG: {
                vehicle.setMinRPM(Double.parseDouble(this.elementContent));
                break;
            }

            case MAX_RPM_TAG: {
                vehicle.setMaxRPM(Double.parseDouble(this.elementContent));
                break;
            }

            case FINAL_DRIVE_RATIO_TAG: {
                vehicle.setFinalDriveRatio(Double.parseDouble(this.elementContent));
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

            case TORQUE_LOW_TAG: {
                regime.setTorqueLow(Double.parseDouble(this.elementContent));
                break;
            }

            case TORQUE_HIGH_TAG: {
                regime.setTorqueHigh(Double.parseDouble(this.elementContent));
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

            case THROTTLE_LIST_TAG: {
                vehicle.setThrottlesList(throttleList);
                break;
            }
            case THROTTLE_TAG: {
                addThrottleToVehicle();
                break;
            }

            case SEGMENT_TYPE_TAG: {
                segmentType = this.elementContent;
                segmentType = segmentType.toUpperCase();
                break;
            }

            case LIMIT_TAG: {
                velocityLimit = Double.parseDouble(this.elementContent);
                break;
            }

            case VELOCITY_LIMIT_TAG: {
                velocityLimitList.put(segmentType, velocityLimit);
                break;
            }

            case VELOCITY_LIMIT_LIST_TAG: {
                vehicle.setRoadVelocityLimit(velocityLimitList);
                break;
            }
        }
    }

    private void saveNameAndDescription() {
        name = reader.getAttributeValue(null, "name");
        description = reader.getAttributeValue(null, "description");
    }

    private void createVehicle() {
        switch (this.elementContent) {
            case ELETRIC_VEHICLE_TAG: {
                this.vehicle = new VehicleElectric();
                break;
            }
            case COMBUSTION_VEHICLE_TAG: {
                this.vehicle = new VehicleCombustion();
                break;
            }
            default:
                break;
        }
        this.vehicle.setName(name);
        this.vehicle.setDescription(description);
        this.vehicle.setType(type);
        this.vehicle.setVehicleClass(tollClass);
    }

    private void createGearbox() {
        this.gearbox = new Gearbox();
        vehicle.setGearbox(gearbox);
    }

    private void createGear() {
        String read = reader.getAttributeValue(null, "id");
        int id = Integer.parseInt(read);
        this.gear = new Gear();
        gear.setGearID(id);
    }

    private void createThrottleList() {
        this.throttleList = new LinkedHashMap<>();
        vehicle.getAccelerator().setThrottleList(throttleList);
    }

    private void createThrottle() {
        String read = reader.getAttributeValue(null, "id");
        int id = Integer.parseInt(read);
        this.throttle = new Throttle();
        throttleID = id;
    }

    private void createRegime() {
        this.regime = new Regime();
        throttle.getRegimeList().add(regime);
    }

    private void addVehicleToList() {
        this.vehiclesList.add(this.vehicle);
        this.vehicle = null;
    }

    private void addGearboxToVehicle() {
        this.gearbox = null;
    }

    private void addGearToVehicle() {
        vehicle.getGearbox().addGear(gear);
        this.gear = null;
    }

    private void addThrottleToVehicle() {
        throttleList.put(throttleID, throttle);
        this.throttle = null;
    }

    public List<Vehicle> getVehiclesList() {
        return vehiclesList;
    }

    private void addMass() {
        String[] split = this.elementContent.split(" ");
        this.elementContent = split[0];
        double mass = Double.parseDouble(elementContent);
        elementContent = split[1];
        mass = unitConversion(mass, elementContent);
        vehicle.setMass(mass);
    }

    private void addLoad() {
        String[] split = this.elementContent.split(" ");
        this.elementContent = split[0];
        double load = Double.parseDouble(elementContent);
        elementContent = split[1];
        load = unitConversion(load, elementContent);
        vehicle.setLoad(load);
    }

    private double unitConversion(double value, String unity) {
        String temp = unity.toLowerCase();
        switch (temp) {
            case "g": {
                value = value * 1000;
            }
        }
        return value;
    }

    private void addTollClass() {
        int toll_class = Integer.parseInt(elementContent);
        tollClass = toll_class;
    }

    @Override
    public Pair<Graph<Junction, Section>, List<Road>>  importNetwork(File file) throws FileNotFoundException, ImportException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
