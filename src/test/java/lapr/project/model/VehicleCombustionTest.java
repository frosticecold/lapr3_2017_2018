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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Miguel Santos <1161386@isep.ipp.pt>
 */
public class VehicleCombustionTest {

    public VehicleCombustionTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getMinRpm method, of class VehicleCombustion.
     */
    @Test
    public void testGetMinRpm() {
        System.out.println("getMinRpm");
        VehicleCombustion instance = new VehicleCombustion();
        double expResult = 1200.0;
        instance.setMinRPM(1200.0);
        double result = instance.getMinRpm();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of getMaxRpm method, of class VehicleCombustion.
     */
    @Test
    public void testGetMaxRpm() {
        System.out.println("getMaxRpm");
        VehicleCombustion instance = new VehicleCombustion();
        double expResult = 5000.0;
        instance.setMaxRPM(5000);
        double result = instance.getMaxRpm();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of getFinalDriveRatio method, of class VehicleCombustion.
     */
    @Test
    public void testGetFinalDriveRatio() {
        System.out.println("getFinalDriveRatio");
        VehicleCombustion instance = new VehicleCombustion();
        double expResult = 0.25;
        instance.setFinalDriveRatio(0.25);
        double result = instance.getFinalDriveRatio();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of getGearbox method, of class VehicleCombustion.
     */
    @Test
    public void testGetGearbox() {
        System.out.println("getGearbox");
        VehicleCombustion instance = new VehicleCombustion();
        Gearbox expResult = new Gearbox();
        instance.setGearbox(new Gearbox());
        Gearbox result = instance.getGearbox();
        assertEquals(expResult.toString(), result.toString());

    }

    /**
     * Test of getAccelerator method, of class VehicleCombustion.
     */
    @Test
    public void testGetAccelerator() {
        System.out.println("getAccelerator");
        VehicleCombustion instance = new VehicleCombustion();
        Accelerator expResult = new Accelerator();
        instance.setAccelerator(new Accelerator());
        Accelerator result = instance.getAccelerator();
        assertEquals(expResult.toString(), result.toString());

    }

    /**
     * Test of toString method, of class VehicleCombustion.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        VehicleCombustion instance = new VehicleCombustion();
        instance.setName("Ferrari");
        String expResult = "Ferrari";
        String result = instance.toString();
        assertEquals(expResult, result);

    }

    /**
     * Test of validateCombustionVehicle method, of class VehicleCombustion.
     */
    @Test
    public void testValidateCombustionVehicle() {
        System.out.println("validateCombustionVehicle");
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

        VehicleCombustion instance = new VehicleCombustion(-1000, -1, 0, gearbox1, acc, "Pickup", "Super duper pickup", "Car", Vehicle.FUEL_GASOLINE, 1, "Combustion", 2400, 1200, 0.39, 0.8, 2.4, 0.015, mapRoadVelocityLimit, throttleList);

        try {
            boolean result = instance.validateCombustionVehicle();
        } catch (IllegalArgumentException e) {
            System.out.println("Error");
        }
        try {
            instance.setMinRPM(1000);
            boolean result = instance.validateCombustionVehicle();
        } catch (IllegalArgumentException e) {
            System.out.println("Error");
        }
        try {
            instance.setMaxRPM(5000);
            boolean result = instance.validateCombustionVehicle();
        } catch (IllegalArgumentException e) {
            System.out.println("Error");
        }
        try {
            instance.setFinalDriveRatio(-8);
            boolean result = instance.validateCombustionVehicle();
        } catch (IllegalArgumentException e) {
            System.out.println("Error");
        }
        VehicleCombustion valid = new VehicleCombustion(1000, 10000, 3.2, gearbox1, acc, "Pickup", "Super duper pickup", "Car", Vehicle.FUEL_GASOLINE, 1, "Combustion", 2400, 1200, 0.39, 0.8, 2.4, 0.015, mapRoadVelocityLimit, throttleList);

        
        assertTrue(valid.validateCombustionVehicle());
    }

//    /**
//     * Test of getMaximumEngineVelocity method, of class VehicleCombustion.
//     */
//    @Test
//    public void testGetMaximumEngineVelocity() {
//        System.out.println("getMaximumEngineVelocity");
//        VehicleCombustion instance = new VehicleCombustion();
//        double expResult = 0.0;
//        double result = instance.getMaximumEngineVelocity();
//        assertEquals(expResult, result, 0.0);
//
//    }
//
//    /**
//     * Test of copy method, of class VehicleCombustion.
//     */
//    @Test
//    public void testCopy() {
//        System.out.println("copy");
//        VehicleCombustion instance = new VehicleCombustion();
//        Vehicle expResult = null;
//        Vehicle result = instance.copy();
//        assertEquals(expResult, result);
//
//    }
}
