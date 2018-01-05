/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public class VehicleTest {

    Vehicle vh1;

    public VehicleTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        Map<String, Double> mapRoadVelocityLimit = new HashMap<>();
        mapRoadVelocityLimit.put("HIGHWAY", 110.0);
        mapRoadVelocityLimit.put("ROAD", 80.0);

        Gear g1 = new Gear(1, 2.3);
        Gear g2 = new Gear(2, 3.3);
        Gear g3 = new Gear(3, 1.7);
        Gear g4 = new Gear(4, 0.65);
        Gear g5 = new Gear(5, 0.4);
        List<Gear> list = new ArrayList<>();
        list.add(g1);
        list.add(g2);
        list.add(g3);
        list.add(g4);
        list.add(g5);
        Gearbox gearbox1 = new Gearbox(list);

        Map<Integer, Throttle> throttleList = new LinkedHashMap<>();
        Throttle throttle = new Throttle();
        //Throttle 25 of Pickup in files Vehicles_v2.xml
        Regime reg1 = new Regime();

        reg1.setTorqueLow(115);
        reg1.setTorqueHigh(125);
        reg1.setRpmLow(900);
        reg1.setRpmHigh(1499);
        reg1.setSFC(500);
        throttle.getRegimeList().add(reg1);

        Regime reg2 = new Regime();
        reg2.setTorqueHigh(125);
        reg2.setTorqueLow(120);
        reg2.setRpmLow(1500);
        reg2.setRpmHigh(2499);
        reg2.setSFC(450);
        throttle.getRegimeList().add(reg2);

        Regime reg3 = new Regime();
        reg3.setTorqueHigh(120);
        reg3.setTorqueLow(105);
        reg3.setRpmLow(2500);
        reg3.setRpmHigh(3499);
        reg3.setSFC(520);
        throttle.getRegimeList().add(reg3);

        Regime reg4 = new Regime();
        reg4.setTorqueHigh(105);
        reg4.setTorqueLow(90);
        reg4.setRpmLow(3500);
        reg4.setRpmHigh(4499);
        reg4.setSFC(550);
        throttle.getRegimeList().add(reg4);

        Regime reg5 = new Regime();
        reg5.setTorqueHigh(90);
        reg5.setTorqueLow(80);
        reg5.setRpmLow(4500);
        reg5.setRpmHigh(5500);
        reg5.setSFC(650);
        throttle.getRegimeList().add(reg5);

        throttleList.put(25, throttle);

        Accelerator acc = new Accelerator();
        acc.setThrottleList(throttleList);

        vh1 = new VehicleCombustion(1000, 5000, 4, gearbox1, acc, "Pickup", "Super duper pickup", "Car", Vehicle.FUEL_GASOLINE, 1, "Combustion", 2400, 1200, 0.39, 0.8, 2.4, 0.015, mapRoadVelocityLimit, throttleList);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getName method, of class Vehicle.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        String expResult = "Pickup";
        String result = vh1.getName();
        assertEquals(expResult, result);

        try {
            vh1.setName(null);
        } catch (IllegalArgumentException t) {
            System.out.println("Error inserting name, name is null");
        }
        try {
            vh1.setName("");
        } catch (IllegalArgumentException t) {
            System.out.println("Error inserting name, name is null");
        }
    }

    /**
     * Test of getThrottles method, of class Vehicle.
     */
    @Test
    public void testGetThrottles() {
        System.out.println("getThrottles");
        Map<Integer, Throttle> expResult = new LinkedHashMap<>();
        Throttle throttle = new Throttle();
        //Throttle 25 of Pickup in files Vehicles_v2.xml
        Regime reg1 = new Regime();

        reg1.setTorqueLow(115);
        reg1.setTorqueHigh(125);
        reg1.setRpmLow(900);
        reg1.setRpmHigh(1499);
        reg1.setSFC(500);
        throttle.getRegimeList().add(reg1);

        Regime reg2 = new Regime();
        reg2.setTorqueHigh(125);
        reg2.setTorqueLow(120);
        reg2.setRpmLow(1500);
        reg2.setRpmHigh(2499);
        reg2.setSFC(450);
        throttle.getRegimeList().add(reg2);

        Regime reg3 = new Regime();
        reg3.setTorqueHigh(120);
        reg3.setTorqueLow(105);
        reg3.setRpmLow(2500);
        reg3.setRpmHigh(3499);
        reg3.setSFC(520);
        throttle.getRegimeList().add(reg3);

        Regime reg4 = new Regime();
        reg4.setTorqueHigh(105);
        reg4.setTorqueLow(90);
        reg4.setRpmLow(3500);
        reg4.setRpmHigh(4499);
        reg4.setSFC(550);
        throttle.getRegimeList().add(reg4);

        Regime reg5 = new Regime();
        reg5.setTorqueHigh(90);
        reg5.setTorqueLow(80);
        reg5.setRpmLow(4500);
        reg5.setRpmHigh(5500);
        reg5.setSFC(650);
        throttle.getRegimeList().add(reg5);

        expResult.put(25, throttle);
        Map<Integer, Throttle> result = vh1.getThrottles();

        assertEquals(expResult.size(), result.size());
        for (int i = 0; i < expResult.get(25).getRegimeList().size(); i++) {
            assertEquals(expResult.get(25).getRegimeList().get(i).toString(), result.get(25).getRegimeList().get(i).toString());
        }
    }

    /**
     * Test of getType method, of class Vehicle.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        String expResult = "Car";
        String result = vh1.getType();
        assertEquals(expResult, result);

        try {
            vh1.setType(null);
        } catch (IllegalArgumentException t) {
            System.out.println("Error inserting type");
        }
        try {
            vh1.setType("");
        } catch (IllegalArgumentException t) {
            System.out.println("Error inserting type");
        }
    }

    /**
     * Test of getDescription method, of class Vehicle.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        String expResult = "Super duper pickup";
        String result = vh1.getDescription();
        assertEquals(expResult, result);
        try {
            vh1.setDescription("");
        } catch (IllegalArgumentException t) {
            System.out.println("Error inserting type");
        }
        try {
            vh1.setDescription(null);
        } catch (IllegalArgumentException t) {
            System.out.println("Error inserting type");
        }
    }

    /**
     * Test of getFuel method, of class Vehicle.
     */
    @Test
    public void testGetFuel() {
        System.out.println("getFuel");
        String expResult = Vehicle.FUEL_GASOLINE;
        String result = vh1.getFuel();
        assertEquals(expResult, result);

        try {
            vh1.setFuel(null);
        } catch (IllegalArgumentException t) {
            System.out.println("Error inserting type");
        }
        try {
            vh1.setFuel("");
        } catch (IllegalArgumentException t) {
            System.out.println("Error inserting type");
        }
    }

    /**
     * Test of getMotorization method, of class Vehicle.
     */
    @Test
    public void testGetMotorization() {
        System.out.println("getMotorization");
        String expResult = "Combustion";
        String result = vh1.getMotorization();
        assertEquals(expResult, result);
        try {
            vh1.setMotorization("");
        } catch (IllegalArgumentException t) {
            System.out.println("Error inserting type");
        }
        try {
            vh1.setMotorization(null);
        } catch (IllegalArgumentException t) {
            System.out.println("Error inserting type");
        }
    }

    /**
     * Test of getLoad method, of class Vehicle.
     */
    @Test
    public void testGetLoad() {
        System.out.println("getLoad");
        double expResult = 1200.0;
        double result = vh1.getMaxLoad();
        assertEquals(expResult, result, 0.0);

        try {
            vh1.setMaxLoad(-1);
        } catch (IllegalArgumentException t) {
            System.out.println("Error inserting Max Load");
        }
    }

    /**
     * Test of getMass method, of class Vehicle.
     */
    @Test
    public void testGetMass() {
        System.out.println("getMass");
        double expResult = 2400.0;
        double result = vh1.getMass();
        assertEquals(expResult, result, 0.0);

        try {
            vh1.setMass(0);
        } catch (IllegalArgumentException t) {
            System.out.println("Error inserting Mass");
        }
        try {
            vh1.setMass(-1);
        } catch (IllegalArgumentException t) {
            System.out.println("Error inserting Mass");
        }
    }

    /**
     * Test of getTotalWeight method, of class Vehicle.
     */
    @Test
    public void testGetTotalWeight() {
        System.out.println("getTotalWeight");
        double expResult = 2400.0;
        double result = vh1.getTotalWeight();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getDragCoefficient method, of class Vehicle.
     */
    @Test
    public void testGetDragCoefficient() {
        System.out.println("getDragCoefficient");
        double expResult = 0.39;
        double result = vh1.getDragCoefficient();
        assertEquals(expResult, result, 0.0);

        try {
            vh1.setDragCoefficient(0);
        } catch (IllegalArgumentException t) {
            System.out.println("Error inserting Drag Coeficient");
        }
        try {
            vh1.setDragCoefficient(-1);
        } catch (IllegalArgumentException t) {
            System.out.println("Error inserting Drag Coeficient");
        }
    }

    /**
     * Test of getWheelSize method, of class Vehicle.
     */
    @Test
    public void testGetWheelSize() {
        System.out.println("getWheelSize");
        double expResult = 0.8;
        double result = vh1.getWheelSize();
        assertEquals(expResult, result, 0.0);

        try {
            vh1.setWheelSize(0);
        } catch (IllegalArgumentException t) {
            System.out.println("Error inserting Wheel size");
        }
        try {
            vh1.setWheelSize(-1);
        } catch (IllegalArgumentException t) {
            System.out.println("Error inserting Wheel size");
        }
    }

    /**
     * Test of getFrontalArea method, of class Vehicle.
     */
    @Test
    public void testGetFrontalArea() {
        System.out.println("getFrontalArea");
        double expResult = 2.4;
        double result = vh1.getFrontalArea();
        assertEquals(expResult, result, 0.0);

        try {
            vh1.setFrontalArea(0);
        } catch (IllegalArgumentException t) {
            System.out.println("Error inserting Frontal area");
        }
        try {
            vh1.setFrontalArea(-1);
        } catch (IllegalArgumentException t) {
            System.out.println("Error inserting Frontal Area");
        }
    }

    /**
     * Test of getVehicleClass method, of class Vehicle.
     */
    @Test
    public void testGetVehicleClass() {
        System.out.println("getVehicleClass");
        int expResult = 1;
        int result = vh1.getVehicleClass();
        assertEquals(expResult, result);

        try {
            vh1.setVehicleClass(-1);
        } catch (IllegalArgumentException t) {
            System.out.println("Error inserting Class");
        }
    }

    /**
     * Test of getTorqueAtThrottle method, of class Vehicle.
     */
    @Test
    public void testGetTorqueAtThrottle() {
        System.out.println("getTorqueAtThrottle");
        int throttle = 25;
        double rpm = 2000.0;
        double expResult = 122.5;
        double result = vh1.getTorqueAtThrottle(throttle, rpm);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getRoadVelocityLimit method, of class Vehicle.
     */
    @Test
    public void testGetRoadVelocityLimit() {
        System.out.println("getRoadVelocityLimit");
        String road = "Toll Highway";
        double expResult = 110.0;
        double result = vh1.getMaximumPermitedVelocity(road);
        assertEquals(expResult, result, 0.005);
        road = "Regular Road";
        expResult = 80.0;
        result = vh1.getMaximumPermitedVelocity(road);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getRCC method, of class Vehicle.
     */
    @Test
    public void testGetRCC() {
        System.out.println("getRCC");
        double expResult = 0.015;
        double result = vh1.getRCC();
        assertEquals(expResult, result, 0.0);

        try {
            vh1.setRCC(-1);
        } catch (IllegalArgumentException t) {
            System.out.println("Error inserting RCC");
        }

    }

    /**
     * Test of getMaximumEngineVelocity method, of class Vehicle.
     */
    @Test
    public void testGetMaximumEngineVelocity() {
        System.out.println("getMaximumEngineVelocity");
        double expResult = 471.0;
        double result = vh1.getMaximumEngineVelocity();
        assertEquals(expResult, result, 2.5);
    }

    /**
     * Test of getMinRpm method, of class Vehicle.
     */
    @Test
    public void testGetMinRpm() {
        System.out.println("getMinRpm");
        double expResult = 1000.0;
        double result = vh1.getMinRpm();
        assertEquals(expResult, result, 0.0);

        try {
            vh1.setMinRPM(-1);
        } catch (IllegalArgumentException t) {
            System.out.println("Error inserting Min RPM");
        }
    }

    /**
     * Test of getMaxRpm method, of class Vehicle.
     */
    @Test
    public void testGetMaxRpm() {
        System.out.println("getMaxRpm");
        double expResult = 5000.0;
        double result = vh1.getMaxRpm();
        assertEquals(expResult, result, 0.0);

        try {
            vh1.setMaxRPM(0);
        } catch (IllegalArgumentException t) {
            System.out.println("Error inserting Max RPM");
        }
        try {
            vh1.setMaxRPM(-1);
        } catch (IllegalArgumentException t) {
            System.out.println("Error inserting Max RPM");
        }
    }

    /**
     * Test of getFinalDriveRatio method, of class Vehicle.
     */
    @Test
    public void testGetFinalDriveRatio() {
        System.out.println("getFinalDriveRatio");
        double expResult = 4.0;
        double result = vh1.getFinalDriveRatio();
        assertEquals(expResult, result, 0.0);

        try {
            vh1.setFinalDriveRatio(0);
        } catch (IllegalArgumentException t) {
            System.out.println("Error inserting Final drive ratio");
        }
        try {
            vh1.setFinalDriveRatio(-1);
        } catch (IllegalArgumentException t) {
            System.out.println("Error inserting Final drive ratio");
        }
    }

    /**
     * Test of getGearbox method, of class Vehicle.
     */
    @Test
    public void testGetGearbox() {
        System.out.println("getGearbox");

        Gear g1 = new Gear(1, 2.3);
        Gear g2 = new Gear(2, 3.3);
        Gear g3 = new Gear(3, 1.7);
        Gear g4 = new Gear(4, 0.65);
        Gear g5 = new Gear(5, 0.4);
        List<Gear> list = new ArrayList<>();
        list.add(g1);
        list.add(g2);
        list.add(g3);
        list.add(g4);
        list.add(g5);
        Gearbox expResult = new Gearbox(list);
        Gearbox result = vh1.getGearbox();
        assertEquals(expResult.toString(), result.toString());
        assertEquals(expResult.getNumberOfGears(), result.getNumberOfGears());
        assertEquals(expResult.getLowestGear(), result.getLowestGear(), 0.5);

        try {
            vh1.setGearbox(null);
        } catch (IllegalArgumentException t) {
            System.out.println("Error inserting Gearbox");
        }

    }

    /**
     * Test of getAccelerator method, of class Vehicle.
     */
    @Test
    public void testGetAccelerator() {
        System.out.println("getAccelerator");
        Map<Integer, Throttle> throttleList = new LinkedHashMap<>();
        Throttle throttle = new Throttle();
        //Throttle 25 of Pickup in files Vehicles_v2.xml
        Regime reg1 = new Regime();

        reg1.setTorqueLow(115);
        reg1.setTorqueHigh(125);
        reg1.setRpmLow(900);
        reg1.setRpmHigh(1499);
        reg1.setSFC(500);
        throttle.getRegimeList().add(reg1);

        Regime reg2 = new Regime();
        reg2.setTorqueHigh(125);
        reg2.setTorqueLow(120);
        reg2.setRpmLow(1500);
        reg2.setRpmHigh(2499);
        reg2.setSFC(450);
        throttle.getRegimeList().add(reg2);

        Regime reg3 = new Regime();
        reg3.setTorqueHigh(120);
        reg3.setTorqueLow(105);
        reg3.setRpmLow(2500);
        reg3.setRpmHigh(3499);
        reg3.setSFC(520);
        throttle.getRegimeList().add(reg3);

        Regime reg4 = new Regime();
        reg4.setTorqueHigh(105);
        reg4.setTorqueLow(90);
        reg4.setRpmLow(3500);
        reg4.setRpmHigh(4499);
        reg4.setSFC(550);
        throttle.getRegimeList().add(reg4);

        Regime reg5 = new Regime();
        reg5.setTorqueHigh(90);
        reg5.setTorqueLow(80);
        reg5.setRpmLow(4500);
        reg5.setRpmHigh(5500);
        reg5.setSFC(650);
        throttle.getRegimeList().add(reg5);

        throttleList.put(25, throttle);

        Accelerator expResult = new Accelerator();
        expResult.setThrottleList(throttleList);
        Accelerator result = vh1.getAccelerator();
        assertTrue(expResult.toString().equalsIgnoreCase(result.toString()));

        try {
            vh1.setAccelerator(null);
        } catch (IllegalArgumentException t) {
            System.out.println("Error inserting Accelerator");

        }
        vh1.setAccelerator(new Accelerator());
        
        assertEquals(new Accelerator().toString(), vh1.getAccelerator().toString());
    }

    /*
     * Test of toString method, of class Vehicle.
     */
    @Test
    public void testToString() {
        System.out.println("toString");

        String expResult = "Pickup";
        String result = vh1.toString();
        assertEquals(expResult, result);

    }

    /**
     * Test of hashCode method, of class Vehicle.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        int expResult = 412610890;
        int result = vh1.hashCode();
        assertEquals(expResult, result);
    }
}
