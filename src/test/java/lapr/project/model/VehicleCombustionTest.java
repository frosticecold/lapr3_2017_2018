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
            instance.setMaxRPM(500);
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

    /**
     * Test of copy method, of class VehicleCombustion.
     */
    @Test
    public void testCopy() {
        System.out.println("copy");
        Map<String, Double> mapRoadVelocityLimit = new HashMap<>();
        mapRoadVelocityLimit.put("HIGHWAY", 110.0);
        mapRoadVelocityLimit.put("ROAD", 80.0);

        Gear g1 = new Gear(1, 4.5);
        Gear g2 = new Gear(2, 3.5);
        Gear g3 = new Gear(3, 2.7);
        Gear g4 = new Gear(4, 1.6);
        Gear g5 = new Gear(5, 1.2);
        Gear g6 = new Gear(6, 0.9);
        List<Gear> list = new ArrayList<>();
        list.add(g1);
        list.add(g2);
        list.add(g3);
        list.add(g4);
        list.add(g5);
        list.add(g6);
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

        Throttle throttle1 = new Throttle();
        Regime reg11 = new Regime();

        reg11.setTorqueLow(185);
        reg11.setTorqueHigh(195);
        reg11.setRpmLow(900);
        reg11.setRpmHigh(1499);
        reg11.setSFC(500);
        throttle1.getRegimeList().add(reg11);

        Regime reg22 = new Regime();
        reg22.setTorqueHigh(195);
        reg22.setTorqueLow(190);
        reg22.setRpmLow(1500);
        reg22.setRpmHigh(2499);
        reg22.setSFC(450);
        throttle1.getRegimeList().add(reg22);

        Regime reg33 = new Regime();
        reg33.setTorqueHigh(190);
        reg33.setTorqueLow(180);
        reg33.setRpmLow(2500);
        reg33.setRpmHigh(3499);
        reg33.setSFC(520);
        throttle1.getRegimeList().add(reg33);

        Regime reg44 = new Regime();
        reg44.setTorqueHigh(180);
        reg44.setTorqueLow(150);
        reg44.setRpmLow(3500);
        reg44.setRpmHigh(4499);
        reg44.setSFC(550);
        throttle1.getRegimeList().add(reg44);

        Regime reg55 = new Regime();
        reg55.setTorqueHigh(150);
        reg55.setTorqueLow(135);
        reg55.setRpmLow(4500);
        reg55.setRpmHigh(5500);
        reg55.setSFC(650);
        throttle1.getRegimeList().add(reg55);

        throttleList.put(50, throttle1);

        Throttle throttle2 = new Throttle();
        Regime reg111 = new Regime();

        reg111.setTorqueLow(305);
        reg111.setTorqueHigh(325);
        reg111.setRpmLow(900);
        reg111.setRpmHigh(1499);
        reg111.setSFC(500);
        throttle2.getRegimeList().add(reg111);

        Regime reg222 = new Regime();
        reg222.setTorqueHigh(325);
        reg222.setTorqueLow(315);
        reg222.setRpmLow(1500);
        reg222.setRpmHigh(2499);
        reg222.setSFC(450);
        throttle2.getRegimeList().add(reg222);

        Regime reg333 = new Regime();
        reg333.setTorqueHigh(315);
        reg333.setTorqueLow(290);
        reg333.setRpmLow(2500);
        reg333.setRpmHigh(3499);
        reg333.setSFC(520);
        throttle2.getRegimeList().add(reg333);

        Regime reg444 = new Regime();
        reg444.setTorqueHigh(290);
        reg444.setTorqueLow(220);
        reg444.setRpmLow(3500);
        reg444.setRpmHigh(4499);
        reg444.setSFC(550);
        throttle2.getRegimeList().add(reg444);

        Regime reg555 = new Regime();
        reg555.setTorqueHigh(220);
        reg555.setTorqueLow(205);
        reg555.setRpmLow(4500);
        reg555.setRpmHigh(5500);
        reg555.setSFC(650);
        throttle2.getRegimeList().add(reg555);

        throttleList.put(100, throttle2);

        Accelerator acc = new Accelerator();
        acc.setThrottleList(throttleList);

        Vehicle vh1 = new VehicleCombustion(1000, 5000, 4, gearbox1, acc, "Pickup", "Super duper pickup", "Car", Vehicle.FUEL_GASOLINE, 1, "Combustion", 2400, 1200, 0.39, 0.8, 2.4, 0.015, mapRoadVelocityLimit, throttleList);
        assertTrue(vh1.equals(vh1.copy()));

    }
}
