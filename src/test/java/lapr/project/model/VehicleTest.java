///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package lapr.project.model;
//
//import java.util.HashMap;
//import java.util.LinkedHashMap;
//import java.util.Map;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import static org.junit.Assert.*;
//
///**
// *
// * @author Raúl Correia <1090657@isep.ipp.pt>
// */
//public class VehicleTest {
//
//    Vehicle vh1;
//
//    public VehicleTest() {
//    }
//
//    @BeforeClass
//    public static void setUpClass() {
//    }
//
//    @AfterClass
//    public static void tearDownClass() {
//    }
//
//    @Before
//    public void setUp() {
//        Map<String, Double> mapRoadVelocityLimit = new HashMap<>();
//        mapRoadVelocityLimit.put("Highway", 110d);
//        mapRoadVelocityLimit.put("Road", 80d);
//        Map<Integer, Throttle> throttleList = new LinkedHashMap<>();
//        vh1 = new VehicleCombustion(1000, 5000, 4, new Gearbox(), new Accelerator(), "Pickup", "Super duper pickup", "Car", Vehicle.FUEL_GASOLINE, 1, "Combustion", 2400, 1200, 0.39, 0.8, 2.4, 0.015, mapRoadVelocityLimit, throttleList);
//    }
//
//    @After
//    public void tearDown() {
//    }
//
//    /**
//     * Test of getName method, of class Vehicle.
//     */
//    @Test
//    public void testGetName() {
//        System.out.println("getName");
//        Vehicle instance = new VehicleImpl();
//        String expResult = "";
//        String result = instance.getName();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getThrottles method, of class Vehicle.
//     */
//    @Test
//    public void testGetThrottles() {
//        System.out.println("getThrottles");
//        Vehicle instance = new VehicleImpl();
//        Map<Integer, Throttle> expResult = null;
//        Map<Integer, Throttle> result = instance.getThrottles();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getType method, of class Vehicle.
//     */
//    @Test
//    public void testGetType() {
//        System.out.println("getType");
//        Vehicle instance = new VehicleImpl();
//        String expResult = "";
//        String result = instance.getType();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getDescription method, of class Vehicle.
//     */
//    @Test
//    public void testGetDescription() {
//        System.out.println("getDescription");
//        Vehicle instance = new VehicleImpl();
//        String expResult = "";
//        String result = instance.getDescription();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getFuel method, of class Vehicle.
//     */
//    @Test
//    public void testGetFuel() {
//        System.out.println("getFuel");
//        Vehicle instance = new VehicleImpl();
//        String expResult = "";
//        String result = instance.getFuel();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getMotorization method, of class Vehicle.
//     */
//    @Test
//    public void testGetMotorization() {
//        System.out.println("getMotorization");
//        Vehicle instance = new VehicleImpl();
//        String expResult = "";
//        String result = instance.getMotorization();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getLoad method, of class Vehicle.
//     */
//    @Test
//    public void testGetLoad() {
//        System.out.println("getLoad");
//        Vehicle instance = new VehicleImpl();
//        double expResult = 0.0;
//        double result = instance.getLoad();
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getMass method, of class Vehicle.
//     */
//    @Test
//    public void testGetMass() {
//        System.out.println("getMass");
//        Vehicle instance = new VehicleImpl();
//        double expResult = 0.0;
//        double result = instance.getMass();
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getTotalWeight method, of class Vehicle.
//     */
//    @Test
//    public void testGetTotalWeight() {
//        System.out.println("getTotalWeight");
//        Vehicle instance = new VehicleImpl();
//        double expResult = 0.0;
//        double result = instance.getTotalWeight();
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getDragCoefficient method, of class Vehicle.
//     */
//    @Test
//    public void testGetDragCoefficient() {
//        System.out.println("getDragCoefficient");
//        Vehicle instance = new VehicleImpl();
//        double expResult = 0.0;
//        double result = instance.getDragCoefficient();
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getWheelSize method, of class Vehicle.
//     */
//    @Test
//    public void testGetWheelSize() {
//        System.out.println("getWheelSize");
//        Vehicle instance = new VehicleImpl();
//        double expResult = 0.0;
//        double result = instance.getWheelSize();
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getFrontalArea method, of class Vehicle.
//     */
//    @Test
//    public void testGetFrontalArea() {
//        System.out.println("getFrontalArea");
//        Vehicle instance = new VehicleImpl();
//        double expResult = 0.0;
//        double result = instance.getFrontalArea();
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getVehicleClass method, of class Vehicle.
//     */
//    @Test
//    public void testGetVehicleClass() {
//        System.out.println("getVehicleClass");
//        Vehicle instance = new VehicleImpl();
//        int expResult = 0;
//        int result = instance.getVehicleClass();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getTorqueAtThrottle method, of class Vehicle.
//     */
//    @Test
//    public void testGetTorqueAtThrottle() {
//        System.out.println("getTorqueAtThrottle");
//        int throttle = 0;
//        double rpm = 0.0;
//        Vehicle instance = new VehicleImpl();
//        double expResult = 0.0;
//        double result = instance.getTorqueAtThrottle(throttle, rpm);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getRoadVelocityLimit method, of class Vehicle.
//     */
//    @Test
//    public void testGetRoadVelocityLimit() {
//        System.out.println("getRoadVelocityLimit");
//        String road = "";
//        Vehicle instance = new VehicleImpl();
//        double expResult = 0.0;
//        double result = instance.getRoadVelocityLimit(road);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getRCC method, of class Vehicle.
//     */
//    @Test
//    public void testGetRCC() {
//        System.out.println("getRCC");
//        Vehicle instance = new VehicleImpl();
//        double expResult = 0.0;
//        double result = instance.getRCC();
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getMaximumEngineVelocity method, of class Vehicle.
//     */
//    @Test
//    public void testGetMaximumEngineVelocity() {
//        System.out.println("getMaximumEngineVelocity");
//        Vehicle instance = new VehicleImpl();
//        double expResult = 0.0;
//        double result = instance.getMaximumEngineVelocity();
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getMinRpm method, of class Vehicle.
//     */
//    @Test
//    public void testGetMinRpm() {
//        System.out.println("getMinRpm");
//        Vehicle instance = new VehicleImpl();
//        double expResult = 0.0;
//        double result = instance.getMinRpm();
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getMaxRpm method, of class Vehicle.
//     */
//    @Test
//    public void testGetMaxRpm() {
//        System.out.println("getMaxRpm");
//        Vehicle instance = new VehicleImpl();
//        double expResult = 0.0;
//        double result = instance.getMaxRpm();
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getFinalDriveRatio method, of class Vehicle.
//     */
//    @Test
//    public void testGetFinalDriveRatio() {
//        System.out.println("getFinalDriveRatio");
//        Vehicle instance = new VehicleImpl();
//        double expResult = 0.0;
//        double result = instance.getFinalDriveRatio();
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getGearbox method, of class Vehicle.
//     */
//    @Test
//    public void testGetGearbox() {
//        System.out.println("getGearbox");
//        Vehicle instance = new VehicleImpl();
//        Gearbox expResult = null;
//        Gearbox result = instance.getGearbox();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getAccelerator method, of class Vehicle.
//     */
//    @Test
//    public void testGetAccelerator() {
//        System.out.println("getAccelerator");
//        Vehicle instance = new VehicleImpl();
//        Accelerator expResult = null;
//        Accelerator result = instance.getAccelerator();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setMinRPM method, of class Vehicle.
//     */
//    @Test
//    public void testSetMinRPM() {
//        System.out.println("setMinRPM");
//        double minRPM = 0.0;
//        Vehicle instance = new VehicleImpl();
//        instance.setMinRPM(minRPM);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setMaxRPM method, of class Vehicle.
//     */
//    @Test
//    public void testSetMaxRPM() {
//        System.out.println("setMaxRPM");
//        double maxRPM = 0.0;
//        Vehicle instance = new VehicleImpl();
//        instance.setMaxRPM(maxRPM);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setFinalDriveRatio method, of class Vehicle.
//     */
//    @Test
//    public void testSetFinalDriveRatio() {
//        System.out.println("setFinalDriveRatio");
//        double finalDriveRatio = 0.0;
//        Vehicle instance = new VehicleImpl();
//        instance.setFinalDriveRatio(finalDriveRatio);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setGearbox method, of class Vehicle.
//     */
//    @Test
//    public void testSetGearbox() {
//        System.out.println("setGearbox");
//        Gearbox gearbox = null;
//        Vehicle instance = new VehicleImpl();
//        instance.setGearbox(gearbox);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setAccelerator method, of class Vehicle.
//     */
//    @Test
//    public void testSetAccelerator() {
//        System.out.println("setAccelerator");
//        Accelerator accelerator = null;
//        Vehicle instance = new VehicleImpl();
//        instance.setAccelerator(accelerator);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setRoadVelocityLimit method, of class Vehicle.
//     */
//    @Test
//    public void testSetRoadVelocityLimit() {
//        System.out.println("setRoadVelocityLimit");
//        Map<String, Double> mapRoadVelocityLimit = null;
//        Vehicle instance = new VehicleImpl();
//        instance.setRoadVelocityLimit(mapRoadVelocityLimit);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setRCC method, of class Vehicle.
//     */
//    @Test
//    public void testSetRCC() {
//        System.out.println("setRCC");
//        double rcc = 0.0;
//        Vehicle instance = new VehicleImpl();
//        instance.setRCC(rcc);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setWheelSize method, of class Vehicle.
//     */
//    @Test
//    public void testSetWheelSize() {
//        System.out.println("setWheelSize");
//        double wheel_size = 0.0;
//        Vehicle instance = new VehicleImpl();
//        instance.setWheelSize(wheel_size);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setFrontalArea method, of class Vehicle.
//     */
//    @Test
//    public void testSetFrontalArea() {
//        System.out.println("setFrontalArea");
//        double frontal_area = 0.0;
//        Vehicle instance = new VehicleImpl();
//        instance.setFrontalArea(frontal_area);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setFuel method, of class Vehicle.
//     */
//    @Test
//    public void testSetFuel() {
//        System.out.println("setFuel");
//        String fuel = "";
//        Vehicle instance = new VehicleImpl();
//        instance.setFuel(fuel);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setDragCoefficient method, of class Vehicle.
//     */
//    @Test
//    public void testSetDragCoefficient() {
//        System.out.println("setDragCoefficient");
//        double drag_coefficient = 0.0;
//        Vehicle instance = new VehicleImpl();
//        instance.setDragCoefficient(drag_coefficient);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setLoad method, of class Vehicle.
//     */
//    @Test
//    public void testSetLoad() {
//        System.out.println("setLoad");
//        double load = 0.0;
//        Vehicle instance = new VehicleImpl();
//        instance.setLoad(load);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setMass method, of class Vehicle.
//     */
//    @Test
//    public void testSetMass() {
//        System.out.println("setMass");
//        double mass = 0.0;
//        Vehicle instance = new VehicleImpl();
//        instance.setMass(mass);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setMotorization method, of class Vehicle.
//     */
//    @Test
//    public void testSetMotorization() {
//        System.out.println("setMotorization");
//        String motorization = "";
//        Vehicle instance = new VehicleImpl();
//        instance.setMotorization(motorization);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setName method, of class Vehicle.
//     */
//    @Test
//    public void testSetName() {
//        System.out.println("setName");
//        String name = "";
//        Vehicle instance = new VehicleImpl();
//        instance.setName(name);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setDescription method, of class Vehicle.
//     */
//    @Test
//    public void testSetDescription() {
//        System.out.println("setDescription");
//        String description = "";
//        Vehicle instance = new VehicleImpl();
//        instance.setDescription(description);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setVehicleClass method, of class Vehicle.
//     */
//    @Test
//    public void testSetVehicleClass() {
//        System.out.println("setVehicleClass");
//        int vehicle_class = 0;
//        Vehicle instance = new VehicleImpl();
//        instance.setVehicleClass(vehicle_class);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setType method, of class Vehicle.
//     */
//    @Test
//    public void testSetType() {
//        System.out.println("setType");
//        String type = "";
//        Vehicle instance = new VehicleImpl();
//        instance.setType(type);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setThrottlesList method, of class Vehicle.
//     */
//    @Test
//    public void testSetThrottlesList() {
//        System.out.println("setThrottlesList");
//        Map<Integer, Throttle> throttleList = null;
//        Vehicle instance = new VehicleImpl();
//        instance.setThrottlesList(throttleList);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of toString method, of class Vehicle.
//     */
//    @Test
//    public void testToString() {
//        System.out.println("toString");
//        Vehicle instance = new VehicleImpl();
//        String expResult = "";
//        String result = instance.toString();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of toStringHTML method, of class Vehicle.
//     */
//    @Test
//    public void testToStringHTML() {
//        System.out.println("toStringHTML");
//        Vehicle instance = new VehicleImpl();
//        String expResult = "";
//        String result = instance.toStringHTML();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of copy method, of class Vehicle.
//     */
//    @Test
//    public void testCopy() {
//        System.out.println("copy");
//        Vehicle instance = new VehicleImpl();
//        Vehicle expResult = null;
//        Vehicle result = instance.copy();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of hashCode method, of class Vehicle.
//     */
//    @Test
//    public void testHashCode() {
//        System.out.println("hashCode");
//        Vehicle instance = new VehicleImpl();
//        int expResult = 0;
//        int result = instance.hashCode();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of equals method, of class Vehicle.
//     */
//    @Test
//    public void testEquals() {
//        System.out.println("equals");
//        Object obj = null;
//        Vehicle instance = new VehicleImpl();
//        boolean expResult = false;
//        boolean result = instance.equals(obj);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    public class VehicleImpl extends Vehicle {
//
//        public double getMaximumEngineVelocity() {
//            return 0.0;
//        }
//
//        public double getMinRpm() {
//            return 0.0;
//        }
//
//        public double getMaxRpm() {
//            return 0.0;
//        }
//
//        public double getFinalDriveRatio() {
//            return 0.0;
//        }
//
//        public Gearbox getGearbox() {
//            return null;
//        }
//
//        public Accelerator getAccelerator() {
//            return null;
//        }
//
//        public void setMinRPM(double minRPM) {
//        }
//
//        public void setMaxRPM(double maxRPM) {
//        }
//
//        public void setFinalDriveRatio(double finalDriveRatio) {
//        }
//
//        public void setGearbox(Gearbox gearbox) {
//        }
//
//        public void setAccelerator(Accelerator accelerator) {
//        }
//
//        public Vehicle copy() {
//            return null;
//        }
//    }
//
//}
