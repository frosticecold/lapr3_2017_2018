/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
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
 * @author MarioDias
 */
public class PhysicsCalculusTest {

    public PhysicsCalculusTest() {
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
     * Test of forceActingOnVehicle method, of class PhysicsCalculus.
     */
    @Test
    public void testForceActingOnVehicle() {
        System.out.println("forceActingOnVehicle");
        double motorForce = 15000.0;
        double rollingResistance = 5000.0;
        double airDrag = 5000.0;
        double expResult = 5000.0;
        double result = PhysicsCalculus.forceActingOnVehicle(motorForce, rollingResistance, airDrag);
        assertEquals(expResult, result, 0.5);
        motorForce = 10000.0;
        rollingResistance = 5000.0;
        airDrag = 5000.0;
        expResult = 0.0;
        result = PhysicsCalculus.forceActingOnVehicle(motorForce, rollingResistance, airDrag);
        assertEquals(expResult, result, 0.5);
    }

    /**
     * Test of forceActingOnVehicleOnSlope method, of class PhysicsCalculus.
     */
    @Test
    public void testForceActingOnVehicleOnSlope() {
        System.out.println("forceActingOnVehicleOnSlope");
        double motorForce = 20000.0;
        double rollingResistance = 200.0;
        double gravitationalForce = 1300.0;
        double airDrag = 1000.0;
        double expResult = 17500.0;
        double result = PhysicsCalculus.forceActingOnVehicleOnSlope(motorForce, rollingResistance, gravitationalForce, airDrag);
        assertEquals(expResult, result, 0.5);
        motorForce = 2500.0;
        rollingResistance = 200.0;
        gravitationalForce = 1300.0;
        airDrag = 1000.0;
        expResult = 0.0;
        result = PhysicsCalculus.forceActingOnVehicleOnSlope(motorForce, rollingResistance, gravitationalForce, airDrag);
        assertEquals(expResult, result, 0.5);
    }

    /**
     * Test of motorForceCalculation method, of class PhysicsCalculus.
     */
    @Test
    public void testMotorForceCalculation() {
        System.out.println("motorForceCalculation");
        double torqueFunction = 500.0;
        double finalDriveRatio = 4.0;
        double gearRatio = 2.7;
        double wheelSize = 0.9;
        double expResult = 12000.0;
        double result = PhysicsCalculus.motorForceCalculation(torqueFunction, finalDriveRatio, gearRatio, wheelSize);
        assertEquals(expResult, result, 0.0);
        torqueFunction = 600.0;
        finalDriveRatio = 2.1;
        gearRatio = 0.9;
        wheelSize = 0.8;
        expResult = 2835.0;
        result = PhysicsCalculus.motorForceCalculation(torqueFunction, finalDriveRatio, gearRatio, wheelSize);
        assertEquals(expResult, result, 0.5);
        torqueFunction = -600.0;
        finalDriveRatio = 2.1;
        gearRatio = 0.9;
        wheelSize = 0.8;
        expResult = -1.0;
        result = PhysicsCalculus.motorForceCalculation(torqueFunction, finalDriveRatio, gearRatio, wheelSize);
        assertEquals(expResult, result, 0.0);
        torqueFunction = 600.0;
        finalDriveRatio = -1.1;
        gearRatio = 0.9;
        wheelSize = 0.8;
        expResult = -1.0;
        result = PhysicsCalculus.motorForceCalculation(torqueFunction, finalDriveRatio, gearRatio, wheelSize);
        assertEquals(expResult, result, 0.0);
        torqueFunction = 600.0;
        finalDriveRatio = 4.0;
        gearRatio = -0.7;
        wheelSize = 0.8;
        expResult = -1.0;
        result = PhysicsCalculus.motorForceCalculation(torqueFunction, finalDriveRatio, gearRatio, wheelSize);
        assertEquals(expResult, result, 0.0);
        torqueFunction = 600.0;
        finalDriveRatio = 4.0;
        gearRatio = 0.7;
        wheelSize = -1.1;
        expResult = -1.0;
        result = PhysicsCalculus.motorForceCalculation(torqueFunction, finalDriveRatio, gearRatio, wheelSize);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of rollingResistanceCalculation method, of class PhysicsCalculus.
     */
    @Test
    public void testRollingResistanceCalculation() {
        System.out.println("rollingResistanceCalculation");
        double rollingResistanceCoeficient = 2.6;
        double mass = 2500.0;
        double expResult = 63743.225;
        double result = PhysicsCalculus.rollingResistanceCalculation(rollingResistanceCoeficient, mass);
        assertEquals(expResult, result, 1.5);
        rollingResistanceCoeficient = 3.25;
        mass = 5000.0;
        expResult = 159358.0625;
        result = PhysicsCalculus.rollingResistanceCalculation(rollingResistanceCoeficient, mass);
        assertEquals(expResult, result, 1.5);
        rollingResistanceCoeficient = -1.25;
        mass = 5000.0;
        expResult = -1;
        result = PhysicsCalculus.rollingResistanceCalculation(rollingResistanceCoeficient, mass);
        assertEquals(expResult, result, 1.5);
        rollingResistanceCoeficient = 1.25;
        mass = -2000.0;
        expResult = -1;
        result = PhysicsCalculus.rollingResistanceCalculation(rollingResistanceCoeficient, mass);
        assertEquals(expResult, result, 1.5);

    }

    /**
     * Test of rollingResistanceCalculationSlope method, of class
     * PhysicsCalculus.
     */
    @Test
    public void testRollingResistanceCalculationSlope() {
        System.out.println("rollingResistanceCalculationSlope");
        double rollingResistanceCoeficient = 2.0;
        double mass = 1600.0;
        double angle = 15.0;
        double expResult = 30311.99;
        double result = PhysicsCalculus.rollingResistanceCalculationSlope(rollingResistanceCoeficient, mass, angle);
        assertEquals(expResult, result, 1.5);
        rollingResistanceCoeficient = -1.0;
        mass = 1600.0;
        angle = 15.0;
        expResult = -1;
        result = PhysicsCalculus.rollingResistanceCalculationSlope(rollingResistanceCoeficient, mass, angle);
        assertEquals(expResult, result, 1.5);
        rollingResistanceCoeficient = 1.9;
        mass = -1200.0;
        angle = 15.0;
        expResult = -1;
        result = PhysicsCalculus.rollingResistanceCalculationSlope(rollingResistanceCoeficient, mass, angle);
        assertEquals(expResult, result, 1.5);
        rollingResistanceCoeficient = 1.9;
        mass = 1200.0;
        angle = -5.0;
        expResult = -1;
        result = PhysicsCalculus.rollingResistanceCalculationSlope(rollingResistanceCoeficient, mass, angle);
        assertEquals(expResult, result, 1.5);
    }

    /**
     * Test of gravitationalForceCalculation method, of class PhysicsCalculus.
     */
    @Test
    public void testGravitationalForceCalculation() {
        System.out.println("gravitationalForceCalculation");
        double mass = 1300.0;
        double angle = 10.0;
        double expResult = 2213.7789;
        double result = PhysicsCalculus.gravitationalForceCalculation(mass, angle);
        assertEquals(expResult, result, 1.5);
        mass = 2600.0;
        angle = 15.0;
        expResult = 6599.184251;
        result = PhysicsCalculus.gravitationalForceCalculation(mass, angle);
        assertEquals(expResult, result, 1.5);
        mass = -600.0;
        angle = 15.0;
        expResult = -1;
        result = PhysicsCalculus.gravitationalForceCalculation(mass, angle);
        assertEquals(expResult, result, 1.5);
        mass = 1500.0;
        angle = -9.0;
        expResult = -1;
        result = PhysicsCalculus.gravitationalForceCalculation(mass, angle);
        assertEquals(expResult, result, 1.5);
    }

    /**
     * Test of airDragCalculation method, of class PhysicsCalculus.
     */
    @Test
    public void testAirDragCalculation() {
        System.out.println("airDragCalculation");
        double velocityRelativeAir = 15;
        double airDragCoeficient = 0.7;
        double frontalArea = 1.7;
        double expResult = 163.996875;
        double result = PhysicsCalculus.airDragCalculation(velocityRelativeAir, airDragCoeficient, frontalArea);
        assertEquals(expResult, result, 1.5);

        velocityRelativeAir = 12.3;
        airDragCoeficient = 1.2;
        frontalArea = 2.3;
        expResult = 255.755745;
        result = PhysicsCalculus.airDragCalculation(velocityRelativeAir, airDragCoeficient, frontalArea);
        assertEquals(expResult, result, 1.5);

        velocityRelativeAir = -1.3;
        airDragCoeficient = 1.2;
        frontalArea = 2.3;
        expResult = -1;
        result = PhysicsCalculus.airDragCalculation(velocityRelativeAir, airDragCoeficient, frontalArea);
        assertEquals(expResult, result, 1.5);

        velocityRelativeAir = 11.3;
        airDragCoeficient = -0.2;
        frontalArea = 2.3;
        expResult = -1;
        result = PhysicsCalculus.airDragCalculation(velocityRelativeAir, airDragCoeficient, frontalArea);
        assertEquals(expResult, result, 1.5);

        velocityRelativeAir = 11.3;
        airDragCoeficient = 0.2;
        frontalArea = -3.3;
        expResult = -1;
        result = PhysicsCalculus.airDragCalculation(velocityRelativeAir, airDragCoeficient, frontalArea);
        assertEquals(expResult, result, 1.5);
    }

    /**
     * Test of velocityRelativeAirCalculation method, of class PhysicsCalculus.
     */
    @Test
    public void testVelocityRelativeAirCalculation() {
        System.out.println("velocityRelativeAirCalculation");
        double rpm = 2500.0;
        double finalDriveRatio = 3.5;
        double kGear = 2.7;
        double tireRatio = 0.9;
        double windSpeed = 27.0;
        double expResult = 32.43;
        double result = PhysicsCalculus.velocityRelativeAirCalculation(rpm, finalDriveRatio, kGear, tireRatio, windSpeed);
        assertEquals(expResult, result, 1.5);

        rpm = 5655.0;
        finalDriveRatio = 2.74;
        kGear = 1.4;
        tireRatio = 1.25;
        windSpeed = 32.0;
        expResult = 201.86;
        result = PhysicsCalculus.velocityRelativeAirCalculation(rpm, finalDriveRatio, kGear, tireRatio, windSpeed);
        assertEquals(expResult, result, 1.5);

        rpm = -655.0;
        finalDriveRatio = 2.74;
        kGear = 1.4;
        tireRatio = 1.25;
        windSpeed = 32.0;
        expResult = -1;
        result = PhysicsCalculus.velocityRelativeAirCalculation(rpm, finalDriveRatio, kGear, tireRatio, windSpeed);
        assertEquals(expResult, result, 1.5);

        rpm = 655.0;
        finalDriveRatio = -2.74;
        kGear = 1.4;
        tireRatio = 1.25;
        windSpeed = 32.0;
        expResult = -1;
        result = PhysicsCalculus.velocityRelativeAirCalculation(rpm, finalDriveRatio, kGear, tireRatio, windSpeed);
        assertEquals(expResult, result, 1.5);

        rpm = 655.0;
        finalDriveRatio = 2.74;
        kGear = -1.4;
        tireRatio = 1.25;
        windSpeed = 32.0;
        expResult = -1;
        result = PhysicsCalculus.velocityRelativeAirCalculation(rpm, finalDriveRatio, kGear, tireRatio, windSpeed);
        assertEquals(expResult, result, 1.5);

        rpm = 655.0;
        finalDriveRatio = 2.74;
        kGear = 1.4;
        tireRatio = -1.25;
        windSpeed = 32.0;
        expResult = -1;
        result = PhysicsCalculus.velocityRelativeAirCalculation(rpm, finalDriveRatio, kGear, tireRatio, windSpeed);
        assertEquals(expResult, result, 1.5);

        rpm = 655.0;
        finalDriveRatio = 2.74;
        kGear = 1.4;
        tireRatio = 1.25;
        windSpeed = -32.0;
        expResult = -1;
        result = PhysicsCalculus.velocityRelativeAirCalculation(rpm, finalDriveRatio, kGear, tireRatio, windSpeed);
        assertEquals(expResult, result, 1.5);
    }

    /**
     * Test of velocityRelativeGroundCalculation method, of class
     * PhysicsCalculus.
     */
    @Test
    public void testVelocityRelativeGroundCalculation() {
        System.out.println("velocityRelativeGroundCalculation");
        double rpm = 2100.0;
        double finalDriveRatio = 4.0;
        double kGear = 2.9;
        double tireRatio = 1.8;
        double expResult = 122.847;
        double result = PhysicsCalculus.velocityRelativeGroundCalculation(rpm, finalDriveRatio, kGear, tireRatio);
        assertEquals(expResult, result, 1.5);

        rpm = 1265.0;
        finalDriveRatio = 3.2;
        kGear = 3.73;
        tireRatio = 1.25;
        expResult = 49.942;
        result = PhysicsCalculus.velocityRelativeGroundCalculation(rpm, finalDriveRatio, kGear, tireRatio);
        assertEquals(expResult, result, 1.5);

        rpm = -1265.0;
        finalDriveRatio = 3.2;
        kGear = 3.73;
        tireRatio = 1.25;
        expResult = -1;
        result = PhysicsCalculus.velocityRelativeGroundCalculation(rpm, finalDriveRatio, kGear, tireRatio);
        assertEquals(expResult, result, 1.5);

        rpm = 1265.0;
        finalDriveRatio = -3.2;
        kGear = 3.73;
        tireRatio = 1.25;
        expResult = -1;
        result = PhysicsCalculus.velocityRelativeGroundCalculation(rpm, finalDriveRatio, kGear, tireRatio);
        assertEquals(expResult, result, 1.5);

        rpm = 1265.0;
        finalDriveRatio = 3.2;
        kGear = -3.73;
        tireRatio = 1.25;
        expResult = -1;
        result = PhysicsCalculus.velocityRelativeGroundCalculation(rpm, finalDriveRatio, kGear, tireRatio);
        assertEquals(expResult, result, 1.5);

        rpm = 1265.0;
        finalDriveRatio = 3.2;
        kGear = 3.73;
        tireRatio = -1.25;
        expResult = -1;
        result = PhysicsCalculus.velocityRelativeGroundCalculation(rpm, finalDriveRatio, kGear, tireRatio);
        assertEquals(expResult, result, 1.5);
    }

    /**
     * Test of calculateRPMandGearRatio method, of class PhysicsCalculus.
     */
    @Test
    public void testCalculateRPMandGearRatio() {
        System.out.println("calculateRPMandGearRatio");
        Vehicle vh1 = null;
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

        reg1.setTorqueLow(125);
        reg1.setTorqueHigh(115);
        reg1.setRpmLow(900);
        reg1.setRpmHigh(1499);
        reg1.setSFC(500);
        throttle.getRegimeList().add(reg1);

        Regime reg2 = new Regime();
        reg2.setTorqueHigh(120);
        reg2.setTorqueLow(125);
        reg2.setRpmLow(1500);
        reg2.setRpmHigh(2499);
        reg2.setSFC(450);
        throttle.getRegimeList().add(reg2);

        Regime reg3 = new Regime();
        reg3.setTorqueHigh(105);
        reg3.setTorqueLow(120);
        reg3.setRpmLow(2500);
        reg3.setRpmHigh(3499);
        reg3.setSFC(520);
        throttle.getRegimeList().add(reg3);

        Regime reg4 = new Regime();
        reg4.setTorqueHigh(90);
        reg4.setTorqueLow(105);
        reg4.setRpmLow(3500);
        reg4.setRpmHigh(4499);
        reg4.setSFC(550);
        throttle.getRegimeList().add(reg4);

        Regime reg5 = new Regime();
        reg5.setTorqueHigh(80);
        reg5.setTorqueLow(90);
        reg5.setRpmLow(4500);
        reg5.setRpmHigh(5500);
        reg5.setSFC(650);
        throttle.getRegimeList().add(reg5);

        throttleList.put(25, throttle);

        Throttle throttle1 = new Throttle();
        Regime reg11 = new Regime();

        reg11.setTorqueLow(195);
        reg11.setTorqueHigh(185);
        reg11.setRpmLow(900);
        reg11.setRpmHigh(1499);
        reg11.setSFC(500);
        throttle1.getRegimeList().add(reg11);

        Regime reg22 = new Regime();
        reg22.setTorqueHigh(190);
        reg22.setTorqueLow(195);
        reg22.setRpmLow(1500);
        reg22.setRpmHigh(2499);
        reg22.setSFC(450);
        throttle1.getRegimeList().add(reg22);

        Regime reg33 = new Regime();
        reg33.setTorqueHigh(180);
        reg33.setTorqueLow(190);
        reg33.setRpmLow(2500);
        reg33.setRpmHigh(3499);
        reg33.setSFC(520);
        throttle1.getRegimeList().add(reg33);

        Regime reg44 = new Regime();
        reg44.setTorqueHigh(150);
        reg44.setTorqueLow(190);
        reg44.setRpmLow(3500);
        reg44.setRpmHigh(4499);
        reg44.setSFC(550);
        throttle1.getRegimeList().add(reg44);

        Regime reg55 = new Regime();
        reg55.setTorqueHigh(135);
        reg55.setTorqueLow(150);
        reg55.setRpmLow(4500);
        reg55.setRpmHigh(5500);
        reg55.setSFC(650);
        throttle1.getRegimeList().add(reg55);

        throttleList.put(50, throttle1);

        Throttle throttle2 = new Throttle();
        Regime reg111 = new Regime();

        reg111.setTorqueLow(325);
        reg111.setTorqueHigh(305);
        reg111.setRpmLow(900);
        reg111.setRpmHigh(1499);
        reg111.setSFC(500);
        throttle2.getRegimeList().add(reg111);

        Regime reg222 = new Regime();
        reg222.setTorqueHigh(315);
        reg222.setTorqueLow(325);
        reg222.setRpmLow(1500);
        reg222.setRpmHigh(2499);
        reg222.setSFC(450);
        throttle2.getRegimeList().add(reg222);

        Regime reg333 = new Regime();
        reg333.setTorqueHigh(290);
        reg333.setTorqueLow(315);
        reg333.setRpmLow(2500);
        reg333.setRpmHigh(3499);
        reg333.setSFC(520);
        throttle2.getRegimeList().add(reg333);

        Regime reg444 = new Regime();
        reg444.setTorqueHigh(220);
        reg444.setTorqueLow(290);
        reg444.setRpmLow(3500);
        reg444.setRpmHigh(4499);
        reg444.setSFC(550);
        throttle2.getRegimeList().add(reg444);

        Regime reg555 = new Regime();
        reg555.setTorqueHigh(205);
        reg555.setTorqueLow(220);
        reg555.setRpmLow(4500);
        reg555.setRpmHigh(5500);
        reg555.setSFC(650);
        throttle2.getRegimeList().add(reg555);

        throttleList.put(100, throttle2);

        Accelerator acc = new Accelerator();
        acc.setThrottleList(throttleList);
        Map<String, Double> mapRoadVelocityLimit = new HashMap<>();
        mapRoadVelocityLimit.put("HIGHWAY", 110.0);
        mapRoadVelocityLimit.put("ROAD", 80.0);

        vh1 = new VehicleCombustion(1000, 5000, 4, gearbox1, acc, "Pickup", "Super duper pickup", "Car", Vehicle.FUEL_GASOLINE, 1, "Combustion", 2400, 1200, 0.39, 0.8, 2.4, 0.015, mapRoadVelocityLimit, throttleList);

        double velocity = 22.2;
        double[] expResult = {1907.94, 0.9};
        double[] result = PhysicsCalculus.calculateRPMandGearRatio(vh1, velocity);
        assertEquals(expResult[0], result[0], 1.5);
        assertEquals(expResult[1], result[1], 1.5);

        velocity = 17.2;
        expResult[0] = 1478.23;
        expResult[1] = 0.9;
        result = PhysicsCalculus.calculateRPMandGearRatio(vh1, velocity);
        assertEquals(expResult[0], result[0], 1.5);
        assertEquals(expResult[1], result[1], 1.5);

        velocity = 3.2;
        expResult[0] = 1069.52;
        expResult[1] = 4.5;
        result = PhysicsCalculus.calculateRPMandGearRatio(vh1, velocity);
        assertEquals(expResult[0], result[0], 1.5);
        assertEquals(expResult[1], result[1], 1.5);

        velocity = 2.2;
        result = PhysicsCalculus.calculateRPMandGearRatio(vh1, velocity);
        assertEquals(result[0], Double.POSITIVE_INFINITY, 0.5);
        assertEquals(result[1], 0, 0);
    }

    /**
     * Test of calcVelocityBasedOnRPMandGear method, of class PhysicsCalculus.
     */
    @Test
    public void testCalcVelocityBasedOnRPMandGear() {
        System.out.println("calcVelocityBasedOnRPMandGear");
        Vehicle vh1 = null;
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

        reg1.setTorqueLow(125);
        reg1.setTorqueHigh(115);
        reg1.setRpmLow(900);
        reg1.setRpmHigh(1499);
        reg1.setSFC(500);
        throttle.getRegimeList().add(reg1);

        Regime reg2 = new Regime();
        reg2.setTorqueHigh(120);
        reg2.setTorqueLow(125);
        reg2.setRpmLow(1500);
        reg2.setRpmHigh(2499);
        reg2.setSFC(450);
        throttle.getRegimeList().add(reg2);

        Regime reg3 = new Regime();
        reg3.setTorqueHigh(105);
        reg3.setTorqueLow(120);
        reg3.setRpmLow(2500);
        reg3.setRpmHigh(3499);
        reg3.setSFC(520);
        throttle.getRegimeList().add(reg3);

        Regime reg4 = new Regime();
        reg4.setTorqueHigh(90);
        reg4.setTorqueLow(105);
        reg4.setRpmLow(3500);
        reg4.setRpmHigh(4499);
        reg4.setSFC(550);
        throttle.getRegimeList().add(reg4);

        Regime reg5 = new Regime();
        reg5.setTorqueHigh(80);
        reg5.setTorqueLow(90);
        reg5.setRpmLow(4500);
        reg5.setRpmHigh(5500);
        reg5.setSFC(650);
        throttle.getRegimeList().add(reg5);

        throttleList.put(25, throttle);

        Throttle throttle1 = new Throttle();
        Regime reg11 = new Regime();

        reg11.setTorqueLow(195);
        reg11.setTorqueHigh(185);
        reg11.setRpmLow(900);
        reg11.setRpmHigh(1499);
        reg11.setSFC(500);
        throttle1.getRegimeList().add(reg11);

        Regime reg22 = new Regime();
        reg22.setTorqueHigh(190);
        reg22.setTorqueLow(195);
        reg22.setRpmLow(1500);
        reg22.setRpmHigh(2499);
        reg22.setSFC(450);
        throttle1.getRegimeList().add(reg22);

        Regime reg33 = new Regime();
        reg33.setTorqueHigh(180);
        reg33.setTorqueLow(190);
        reg33.setRpmLow(2500);
        reg33.setRpmHigh(3499);
        reg33.setSFC(520);
        throttle1.getRegimeList().add(reg33);

        Regime reg44 = new Regime();
        reg44.setTorqueHigh(150);
        reg44.setTorqueLow(190);
        reg44.setRpmLow(3500);
        reg44.setRpmHigh(4499);
        reg44.setSFC(550);
        throttle1.getRegimeList().add(reg44);

        Regime reg55 = new Regime();
        reg55.setTorqueHigh(135);
        reg55.setTorqueLow(150);
        reg55.setRpmLow(4500);
        reg55.setRpmHigh(5500);
        reg55.setSFC(650);
        throttle1.getRegimeList().add(reg55);

        throttleList.put(50, throttle1);

        Throttle throttle2 = new Throttle();
        Regime reg111 = new Regime();

        reg111.setTorqueLow(325);
        reg111.setTorqueHigh(305);
        reg111.setRpmLow(900);
        reg111.setRpmHigh(1499);
        reg111.setSFC(500);
        throttle2.getRegimeList().add(reg111);

        Regime reg222 = new Regime();
        reg222.setTorqueHigh(315);
        reg222.setTorqueLow(325);
        reg222.setRpmLow(1500);
        reg222.setRpmHigh(2499);
        reg222.setSFC(450);
        throttle2.getRegimeList().add(reg222);

        Regime reg333 = new Regime();
        reg333.setTorqueHigh(290);
        reg333.setTorqueLow(315);
        reg333.setRpmLow(2500);
        reg333.setRpmHigh(3499);
        reg333.setSFC(520);
        throttle2.getRegimeList().add(reg333);

        Regime reg444 = new Regime();
        reg444.setTorqueHigh(220);
        reg444.setTorqueLow(290);
        reg444.setRpmLow(3500);
        reg444.setRpmHigh(4499);
        reg444.setSFC(550);
        throttle2.getRegimeList().add(reg444);

        Regime reg555 = new Regime();
        reg555.setTorqueHigh(205);
        reg555.setTorqueLow(220);
        reg555.setRpmLow(4500);
        reg555.setRpmHigh(5500);
        reg555.setSFC(650);
        throttle2.getRegimeList().add(reg555);

        throttleList.put(100, throttle2);

        Accelerator acc = new Accelerator();
        acc.setThrottleList(throttleList);
        Map<String, Double> mapRoadVelocityLimit = new HashMap<>();
        mapRoadVelocityLimit.put("HIGHWAY", 110.0);
        mapRoadVelocityLimit.put("ROAD", 80.0);

        vh1 = new VehicleCombustion(1000, 5000, 4, gearbox1, acc, "Pickup", "Super duper pickup", "Car", Vehicle.FUEL_GASOLINE, 1, "Combustion", 2400, 1200, 0.39, 0.8, 2.4, 0.015, mapRoadVelocityLimit, throttleList);

        double rpm = 1478.23;
        double gear_ratio = 0.9;
        double expResult = 17.2;
        double result = PhysicsCalculus.calcVelocityBasedOnRPMandGear(vh1, rpm, gear_ratio);
        assertEquals(expResult, result, 1.5);

        rpm = 1907.99;
        gear_ratio = 0.9;
        expResult = 22.2;
        result = PhysicsCalculus.calcVelocityBasedOnRPMandGear(vh1, rpm, gear_ratio);
        assertEquals(expResult, result, 0.5);

        rpm = 1069.52;
        gear_ratio = 4.5;
        expResult = 2.5;
        result = PhysicsCalculus.calcVelocityBasedOnRPMandGear(vh1, rpm, gear_ratio);
        assertEquals(expResult, result, 0.5);

        rpm = 1000.0;
        gear_ratio = 4.5;
        expResult = 2.3;
        result = PhysicsCalculus.calcVelocityBasedOnRPMandGear(vh1, rpm, gear_ratio);
        assertEquals(expResult, result, 0.5);

    }

    /**
     * Test of calcVelocityHighestGearLowestThrotlle method, of class
     * PhysicsCalculus.
     */
    @Test
    public void testCalcVelocityHighestGearLowestThrotlle() {
        System.out.println("calcVelocityHighestGearLowestThrotlle");

        Vehicle vh1 = null;
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

        reg1.setTorqueLow(125);
        reg1.setTorqueHigh(115);
        reg1.setRpmLow(900);
        reg1.setRpmHigh(1499);
        reg1.setSFC(500);
        throttle.getRegimeList().add(reg1);

        Regime reg2 = new Regime();
        reg2.setTorqueHigh(120);
        reg2.setTorqueLow(125);
        reg2.setRpmLow(1500);
        reg2.setRpmHigh(2499);
        reg2.setSFC(450);
        throttle.getRegimeList().add(reg2);

        Regime reg3 = new Regime();
        reg3.setTorqueHigh(105);
        reg3.setTorqueLow(120);
        reg3.setRpmLow(2500);
        reg3.setRpmHigh(3499);
        reg3.setSFC(520);
        throttle.getRegimeList().add(reg3);

        Regime reg4 = new Regime();
        reg4.setTorqueHigh(90);
        reg4.setTorqueLow(105);
        reg4.setRpmLow(3500);
        reg4.setRpmHigh(4499);
        reg4.setSFC(550);
        throttle.getRegimeList().add(reg4);

        Regime reg5 = new Regime();
        reg5.setTorqueHigh(80);
        reg5.setTorqueLow(90);
        reg5.setRpmLow(4500);
        reg5.setRpmHigh(5500);
        reg5.setSFC(650);
        throttle.getRegimeList().add(reg5);

        throttleList.put(25, throttle);

        Throttle throttle1 = new Throttle();
        Regime reg11 = new Regime();

        reg11.setTorqueLow(195);
        reg11.setTorqueHigh(185);
        reg11.setRpmLow(900);
        reg11.setRpmHigh(1499);
        reg11.setSFC(500);
        throttle1.getRegimeList().add(reg11);

        Regime reg22 = new Regime();
        reg22.setTorqueHigh(190);
        reg22.setTorqueLow(195);
        reg22.setRpmLow(1500);
        reg22.setRpmHigh(2499);
        reg22.setSFC(450);
        throttle1.getRegimeList().add(reg22);

        Regime reg33 = new Regime();
        reg33.setTorqueHigh(180);
        reg33.setTorqueLow(190);
        reg33.setRpmLow(2500);
        reg33.setRpmHigh(3499);
        reg33.setSFC(520);
        throttle1.getRegimeList().add(reg33);

        Regime reg44 = new Regime();
        reg44.setTorqueHigh(150);
        reg44.setTorqueLow(190);
        reg44.setRpmLow(3500);
        reg44.setRpmHigh(4499);
        reg44.setSFC(550);
        throttle1.getRegimeList().add(reg44);

        Regime reg55 = new Regime();
        reg55.setTorqueHigh(135);
        reg55.setTorqueLow(150);
        reg55.setRpmLow(4500);
        reg55.setRpmHigh(5500);
        reg55.setSFC(650);
        throttle1.getRegimeList().add(reg55);

        throttleList.put(50, throttle1);

        Throttle throttle2 = new Throttle();
        Regime reg111 = new Regime();

        reg111.setTorqueLow(325);
        reg111.setTorqueHigh(305);
        reg111.setRpmLow(900);
        reg111.setRpmHigh(1499);
        reg111.setSFC(500);
        throttle2.getRegimeList().add(reg111);

        Regime reg222 = new Regime();
        reg222.setTorqueHigh(315);
        reg222.setTorqueLow(325);
        reg222.setRpmLow(1500);
        reg222.setRpmHigh(2499);
        reg222.setSFC(450);
        throttle2.getRegimeList().add(reg222);

        Regime reg333 = new Regime();
        reg333.setTorqueHigh(290);
        reg333.setTorqueLow(315);
        reg333.setRpmLow(2500);
        reg333.setRpmHigh(3499);
        reg333.setSFC(520);
        throttle2.getRegimeList().add(reg333);

        Regime reg444 = new Regime();
        reg444.setTorqueHigh(220);
        reg444.setTorqueLow(290);
        reg444.setRpmLow(3500);
        reg444.setRpmHigh(4499);
        reg444.setSFC(550);
        throttle2.getRegimeList().add(reg444);

        Regime reg555 = new Regime();
        reg555.setTorqueHigh(205);
        reg555.setTorqueLow(220);
        reg555.setRpmLow(4500);
        reg555.setRpmHigh(5500);
        reg555.setSFC(650);
        throttle2.getRegimeList().add(reg555);

        throttleList.put(100, throttle2);

        Accelerator acc = new Accelerator();
        acc.setThrottleList(throttleList);
        Map<String, Double> mapRoadVelocityLimit = new HashMap<>();
        mapRoadVelocityLimit.put("HIGHWAY", 110.0);
        mapRoadVelocityLimit.put("ROAD", 80.0);

        vh1 = new VehicleCombustion(1000, 5000, 4, gearbox1, acc, "Pickup", "Super duper pickup", "Car", Vehicle.FUEL_GASOLINE, 1, "Combustion", 2400, 1200, 0.39, 0.8, 2.4, 0.015, mapRoadVelocityLimit, throttleList);

        double[] expResult = {17.2, 104.4};
        double[] result = PhysicsCalculus.calcVelocityHighestGearLowestThrotlle(vh1);
        assertEquals(expResult[0], result[0], 0.5);
    }

    /**
     * Test of calcEnginePower method, of class PhysicsCalculus.
     */
    @Test
    public void testCalcEnginePower() {
        System.out.println("calcEnginePower");
        double torque = 190.0;
        double rpm = 2600.0;
        double expResult = 51731.56;
        double result = PhysicsCalculus.calcEnginePower(torque, rpm);
        assertEquals(expResult, result, 0.5);

        torque = 320.0;
        rpm = 3500;
        expResult = 117286.1257;
        result = PhysicsCalculus.calcEnginePower(torque, rpm);
        assertEquals(expResult, result, 0.5);

        torque = 0.0;
        rpm = 1500;
        expResult = -1.0;
        result = PhysicsCalculus.calcEnginePower(torque, rpm);
        assertEquals(expResult, result, 0.0);

        torque = -5000.0;
        rpm = 1500;
        expResult = -1.0;
        result = PhysicsCalculus.calcEnginePower(torque, rpm);
        assertEquals(expResult, result, 0.0);

        torque = 50.0;
        rpm = 0;
        expResult = -1.0;
        result = PhysicsCalculus.calcEnginePower(torque, rpm);
        assertEquals(expResult, result, 0.0);

        torque = 50.0;
        rpm = -1000;
        expResult = -1.0;
        result = PhysicsCalculus.calcEnginePower(torque, rpm);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of calcMaximumVelocity method, of class PhysicsCalculus.
     */
    @Test
    public void testCalcMaximumVelocity() {
        System.out.println("calcMaximumVelocity");

        Vehicle vh1 = null;
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

        reg1.setTorqueLow(125);
        reg1.setTorqueHigh(115);
        reg1.setRpmLow(900);
        reg1.setRpmHigh(1499);
        reg1.setSFC(500);
        throttle.getRegimeList().add(reg1);

        Regime reg2 = new Regime();
        reg2.setTorqueHigh(120);
        reg2.setTorqueLow(125);
        reg2.setRpmLow(1500);
        reg2.setRpmHigh(2499);
        reg2.setSFC(450);
        throttle.getRegimeList().add(reg2);

        Regime reg3 = new Regime();
        reg3.setTorqueHigh(105);
        reg3.setTorqueLow(120);
        reg3.setRpmLow(2500);
        reg3.setRpmHigh(3499);
        reg3.setSFC(520);
        throttle.getRegimeList().add(reg3);

        Regime reg4 = new Regime();
        reg4.setTorqueHigh(90);
        reg4.setTorqueLow(105);
        reg4.setRpmLow(3500);
        reg4.setRpmHigh(4499);
        reg4.setSFC(550);
        throttle.getRegimeList().add(reg4);

        Regime reg5 = new Regime();
        reg5.setTorqueHigh(80);
        reg5.setTorqueLow(90);
        reg5.setRpmLow(4500);
        reg5.setRpmHigh(5500);
        reg5.setSFC(650);
        throttle.getRegimeList().add(reg5);

        throttleList.put(25, throttle);

        Throttle throttle1 = new Throttle();
        Regime reg11 = new Regime();

        reg11.setTorqueLow(195);
        reg11.setTorqueHigh(185);
        reg11.setRpmLow(900);
        reg11.setRpmHigh(1499);
        reg11.setSFC(500);
        throttle1.getRegimeList().add(reg11);

        Regime reg22 = new Regime();
        reg22.setTorqueHigh(190);
        reg22.setTorqueLow(195);
        reg22.setRpmLow(1500);
        reg22.setRpmHigh(2499);
        reg22.setSFC(450);
        throttle1.getRegimeList().add(reg22);

        Regime reg33 = new Regime();
        reg33.setTorqueHigh(180);
        reg33.setTorqueLow(190);
        reg33.setRpmLow(2500);
        reg33.setRpmHigh(3499);
        reg33.setSFC(520);
        throttle1.getRegimeList().add(reg33);

        Regime reg44 = new Regime();
        reg44.setTorqueHigh(150);
        reg44.setTorqueLow(190);
        reg44.setRpmLow(3500);
        reg44.setRpmHigh(4499);
        reg44.setSFC(550);
        throttle1.getRegimeList().add(reg44);

        Regime reg55 = new Regime();
        reg55.setTorqueHigh(135);
        reg55.setTorqueLow(150);
        reg55.setRpmLow(4500);
        reg55.setRpmHigh(5500);
        reg55.setSFC(650);
        throttle1.getRegimeList().add(reg55);

        throttleList.put(50, throttle1);

        Throttle throttle2 = new Throttle();
        Regime reg111 = new Regime();

        reg111.setTorqueLow(325);
        reg111.setTorqueHigh(305);
        reg111.setRpmLow(900);
        reg111.setRpmHigh(1499);
        reg111.setSFC(500);
        throttle2.getRegimeList().add(reg111);

        Regime reg222 = new Regime();
        reg222.setTorqueHigh(315);
        reg222.setTorqueLow(325);
        reg222.setRpmLow(1500);
        reg222.setRpmHigh(2499);
        reg222.setSFC(450);
        throttle2.getRegimeList().add(reg222);

        Regime reg333 = new Regime();
        reg333.setTorqueHigh(290);
        reg333.setTorqueLow(315);
        reg333.setRpmLow(2500);
        reg333.setRpmHigh(3499);
        reg333.setSFC(520);
        throttle2.getRegimeList().add(reg333);

        Regime reg444 = new Regime();
        reg444.setTorqueHigh(220);
        reg444.setTorqueLow(290);
        reg444.setRpmLow(3500);
        reg444.setRpmHigh(4499);
        reg444.setSFC(550);
        throttle2.getRegimeList().add(reg444);

        Regime reg555 = new Regime();
        reg555.setTorqueHigh(205);
        reg555.setTorqueLow(220);
        reg555.setRpmLow(4500);
        reg555.setRpmHigh(5500);
        reg555.setSFC(650);
        throttle2.getRegimeList().add(reg555);

        throttleList.put(100, throttle2);

        Accelerator acc = new Accelerator();
        acc.setThrottleList(throttleList);
        Map<String, Double> mapRoadVelocityLimit = new HashMap<>();
        mapRoadVelocityLimit.put("HIGHWAY", 90.0);
        mapRoadVelocityLimit.put("ROAD", 60.0);

        vh1 = new VehicleCombustion(1000, 5000, 4, gearbox1, acc, "Pickup", "Super duper pickup", "Car", Vehicle.FUEL_GASOLINE, 1, "Combustion", 2400, 1200, 0.39, 0.8, 2.4, 0.015, mapRoadVelocityLimit, throttleList);

        Segment segment1 = new Segment(1, 100, 110, 10, 0, 10, 120, 0);
        Segment segment2 = new Segment(2, 110, 110, 10, 0, 10, 120, 0);
        Segment segment3 = new Segment(3, 110, 120, 10, 0, 10, 120, 0);
        Segment segment4 = new Segment(4, 120, 100, 10, 15, 5, 90, 0);
        Segment segment5 = new Segment(5, 100, 100, 10, 0, 10, 120, 0);

        List<Segment> listSegment = new LinkedList<>();
        listSegment.add(segment1);
        listSegment.add(segment2);
        listSegment.add(segment3);
        listSegment.add(segment4);
        listSegment.add(segment5);

        Section section = new Section();
        section.setBeginJunction(new Junction("N0"));
        section.setEndJunction(new Junction("N1"));
        section.setDirection(Section.Direction.DIRECT);
        section.setRoadID("A01");
        section.setSectionID(1);
        section.setSegmentList(listSegment);
        section.setTypology("Toll Highway");

        Section section2 = new Section();
        section2.setBeginJunction(new Junction("N0"));
        section2.setEndJunction(new Junction("N1"));
        section2.setDirection(Section.Direction.DIRECT);
        section2.setRoadID("F01");
        section2.setSectionID(1);
        section2.setSegmentList(listSegment);
        section2.setTypology("Regular Road");
        double expResult = 15.0;
        double result = PhysicsCalculus.calcMaximumVelocity(segment1, vh1, section);
        assertEquals(expResult, result, 0.5);

        expResult = 11.8;
        result = PhysicsCalculus.calcMaximumVelocity(segment4, vh1, section2);
        assertEquals(expResult, result, 0.5);

        expResult = 6.6;
        result = PhysicsCalculus.calcMaximumVelocity(segment2, vh1, section2);
        assertEquals(expResult, result, 0.5);
    }

    /**
     * Test of calcForceInSegment method, of class PhysicsCalculus.
     */
    @Test
    public void testCalcForceInSegment() {
        System.out.println("calcForceInSegment");

        Vehicle vh1 = null;
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

        reg1.setTorqueLow(125);
        reg1.setTorqueHigh(115);
        reg1.setRpmLow(900);
        reg1.setRpmHigh(1499);
        reg1.setSFC(500);
        throttle.getRegimeList().add(reg1);

        Regime reg2 = new Regime();
        reg2.setTorqueHigh(120);
        reg2.setTorqueLow(125);
        reg2.setRpmLow(1500);
        reg2.setRpmHigh(2499);
        reg2.setSFC(450);
        throttle.getRegimeList().add(reg2);

        Regime reg3 = new Regime();
        reg3.setTorqueHigh(105);
        reg3.setTorqueLow(120);
        reg3.setRpmLow(2500);
        reg3.setRpmHigh(3499);
        reg3.setSFC(520);
        throttle.getRegimeList().add(reg3);

        Regime reg4 = new Regime();
        reg4.setTorqueHigh(90);
        reg4.setTorqueLow(105);
        reg4.setRpmLow(3500);
        reg4.setRpmHigh(4499);
        reg4.setSFC(550);
        throttle.getRegimeList().add(reg4);

        Regime reg5 = new Regime();
        reg5.setTorqueHigh(80);
        reg5.setTorqueLow(90);
        reg5.setRpmLow(4500);
        reg5.setRpmHigh(5500);
        reg5.setSFC(650);
        throttle.getRegimeList().add(reg5);

        throttleList.put(25, throttle);

        Throttle throttle1 = new Throttle();
        Regime reg11 = new Regime();

        reg11.setTorqueLow(195);
        reg11.setTorqueHigh(185);
        reg11.setRpmLow(900);
        reg11.setRpmHigh(1499);
        reg11.setSFC(500);
        throttle1.getRegimeList().add(reg11);

        Regime reg22 = new Regime();
        reg22.setTorqueHigh(190);
        reg22.setTorqueLow(195);
        reg22.setRpmLow(1500);
        reg22.setRpmHigh(2499);
        reg22.setSFC(450);
        throttle1.getRegimeList().add(reg22);

        Regime reg33 = new Regime();
        reg33.setTorqueHigh(180);
        reg33.setTorqueLow(190);
        reg33.setRpmLow(2500);
        reg33.setRpmHigh(3499);
        reg33.setSFC(520);
        throttle1.getRegimeList().add(reg33);

        Regime reg44 = new Regime();
        reg44.setTorqueHigh(150);
        reg44.setTorqueLow(190);
        reg44.setRpmLow(3500);
        reg44.setRpmHigh(4499);
        reg44.setSFC(550);
        throttle1.getRegimeList().add(reg44);

        Regime reg55 = new Regime();
        reg55.setTorqueHigh(135);
        reg55.setTorqueLow(150);
        reg55.setRpmLow(4500);
        reg55.setRpmHigh(5500);
        reg55.setSFC(650);
        throttle1.getRegimeList().add(reg55);

        throttleList.put(50, throttle1);

        Throttle throttle2 = new Throttle();
        Regime reg111 = new Regime();

        reg111.setTorqueLow(325);
        reg111.setTorqueHigh(305);
        reg111.setRpmLow(900);
        reg111.setRpmHigh(1499);
        reg111.setSFC(500);
        throttle2.getRegimeList().add(reg111);

        Regime reg222 = new Regime();
        reg222.setTorqueHigh(315);
        reg222.setTorqueLow(325);
        reg222.setRpmLow(1500);
        reg222.setRpmHigh(2499);
        reg222.setSFC(450);
        throttle2.getRegimeList().add(reg222);

        Regime reg333 = new Regime();
        reg333.setTorqueHigh(290);
        reg333.setTorqueLow(315);
        reg333.setRpmLow(2500);
        reg333.setRpmHigh(3499);
        reg333.setSFC(520);
        throttle2.getRegimeList().add(reg333);

        Regime reg444 = new Regime();
        reg444.setTorqueHigh(220);
        reg444.setTorqueLow(290);
        reg444.setRpmLow(3500);
        reg444.setRpmHigh(4499);
        reg444.setSFC(550);
        throttle2.getRegimeList().add(reg444);

        Regime reg555 = new Regime();
        reg555.setTorqueHigh(205);
        reg555.setTorqueLow(220);
        reg555.setRpmLow(4500);
        reg555.setRpmHigh(5500);
        reg555.setSFC(650);
        throttle2.getRegimeList().add(reg555);

        throttleList.put(100, throttle2);

        Accelerator acc = new Accelerator();
        acc.setThrottleList(throttleList);
        Map<String, Double> mapRoadVelocityLimit = new HashMap<>();
        mapRoadVelocityLimit.put("HIGHWAY", 90.0);
        mapRoadVelocityLimit.put("ROAD", 60.0);

        vh1 = new VehicleCombustion(900, 5500, 4, gearbox1, acc, "Pickup", "Super duper pickup", "Car", Vehicle.FUEL_GASOLINE, 1, "Combustion", 2400, 1200, 0.39, 0.8, 2.4, 0.015, mapRoadVelocityLimit, throttleList);

        Segment segment1 = new Segment(1, 100, 110, 10, 0, 10, 120, 0);
        Segment segment2 = new Segment(2, 110, 110, 8, 0, 10, 120, 0);
        Segment segment3 = new Segment(3, 110, 120, 6, 0, 10, 120, 0);
        Segment segment4 = new Segment(4, 120, 110, 1.9, 15, 5, 90, 0);
        Segment segment5 = new Segment(5, 110, 110, 2.4, 0, 10, 120, 0);

        List<Segment> listSegment = new LinkedList<>();
        listSegment.add(segment1);
        listSegment.add(segment2);
        listSegment.add(segment3);
        listSegment.add(segment4);
        listSegment.add(segment5);

        Section section = new Section();
        section.setBeginJunction(new Junction("N0"));
        section.setEndJunction(new Junction("N1"));
        section.setDirection(Section.Direction.DIRECT);
        section.setRoadID("A01");
        section.setSectionID(1);
        section.setSegmentList(listSegment);
        section.setTypology("Toll Highway");

        Section section2 = new Section();
        section2.setBeginJunction(new Junction("N0"));
        section2.setEndJunction(new Junction("N1"));
        section2.setDirection(Section.Direction.DIRECT);
        section2.setRoadID("F01");
        section2.setSectionID(1);
        section2.setSegmentList(listSegment);
        section2.setTypology("Regular Road");

        double expResult = 505.56;
        double result = PhysicsCalculus.calcForceInSegment(segment1, vh1, section);
        assertEquals(expResult, result, 0.5);

        expResult = 482.1;
        result = PhysicsCalculus.calcForceInSegment(segment2, vh1, section);
        assertEquals(expResult, result, 0.5);

        expResult = 521.3;
        result = PhysicsCalculus.calcForceInSegment(segment3, vh1, section);
        assertEquals(expResult, result, 0.5);

        expResult = 108.4;
        result = PhysicsCalculus.calcForceInSegment(segment4, vh1, section);
        assertEquals(expResult, result, 0.5);

        expResult = 482.0;
        result = PhysicsCalculus.calcForceInSegment(segment5, vh1, section);
        assertEquals(expResult, result, 0.5);
    }

    /**
     * Test of calcTimeBasedOnVelocityAndAcceleration method, of class
     * PhysicsCalculus.
     */
    @Test
    public void testCalcTimeBasedOnVelocityAndAcceleration() {
        System.out.println("calcTimeBasedOnVelocityAndAcceleration");
        double desiredVelocity = 22.2;
        double initialVelocity = 2.2;
        double acceleration = 1.0;
        double expResult = 20.0;
        double result = PhysicsCalculus.calcTimeBasedOnVelocityAndAcceleration(desiredVelocity, initialVelocity, acceleration);
        System.out.println(result);
        assertEquals(expResult, result, 0.5);

        desiredVelocity = 33.3;
        initialVelocity = 10.2;
        acceleration = 1.0;
        expResult = 23.1;
        result = PhysicsCalculus.calcTimeBasedOnVelocityAndAcceleration(desiredVelocity, initialVelocity, acceleration);
        System.out.println(result);
        assertEquals(expResult, result, 0.5);

        desiredVelocity = 50.3;
        initialVelocity = 16.2;
        acceleration = 1.2;
        expResult = 28.42;
        result = PhysicsCalculus.calcTimeBasedOnVelocityAndAcceleration(desiredVelocity, initialVelocity, acceleration);
        System.out.println(result);
        assertEquals(expResult, result, 0.5);

        desiredVelocity = 51.75;
        initialVelocity = 44.85;
        acceleration = 0.5;
        expResult = 13.8;
        result = PhysicsCalculus.calcTimeBasedOnVelocityAndAcceleration(desiredVelocity, initialVelocity, acceleration);
        System.out.println(result);
        assertEquals(expResult, result, 0.5);
    }

    /**
     * Test of calcTimeBasedOnDistanceAndAcceleration method, of class
     * PhysicsCalculus.
     */
    @Test
    public void testCalcTimeBasedOnDistanceAndAcceleration() {
        System.out.println("calcTimeBasedOnDistanceAndAcceleration");
        double initialVelocity = 12.3;
        double distance = 50.62;
        double acceleration = 1.0;
        double expResult = 8.75;
        double result = PhysicsCalculus.calcTimeBasedOnDistanceAndAcceleration(initialVelocity, distance, acceleration);
        assertEquals(expResult, result, 0.5);

        initialVelocity = 12.3;
        distance = 32;
        acceleration = 0.65;
        expResult = 7.78;
        result = PhysicsCalculus.calcTimeBasedOnDistanceAndAcceleration(initialVelocity, distance, acceleration);
        assertEquals(expResult, result, 0.5);
    }

    /**
     * Test of calcDistance method, of class PhysicsCalculus.
     */
    @Test
    public void testCalcDistance() {
        System.out.println("calcDistance");
        double initialvelocity = 10.0;
        double acceleration = 1.0;
        double time = 50;
        double expResult = 1750.0;
        double result = PhysicsCalculus.calcDistance(initialvelocity, acceleration, time);
        assertEquals(expResult, result, 0.5);

        initialvelocity = 20.0;
        acceleration = 0.5;
        time = 13;
        expResult = 302.25;
        result = PhysicsCalculus.calcDistance(initialvelocity, acceleration, time);
        assertEquals(expResult, result, 0.5);
    }

    /**
     * Test of calcVelocityBasedOnInitialVelocityAccelerationTime method, of
     * class PhysicsCalculus.
     */
    @Test
    public void testCalcVelocityBasedOnInitialVelocityAccelerationTime() {
        System.out.println("calcVelocityBasedOnInitialVelocityAccelerationTime");
        double initialVelocity = 12.5;
        double acceleration = 1.0;
        double time = 23;
        double expResult = 35.5;
        double result = PhysicsCalculus.calcVelocityBasedOnInitialVelocityAccelerationTime(initialVelocity, acceleration, time);
        assertEquals(expResult, result, 0.5);

        initialVelocity = 30;
        acceleration = 0.5;
        time = 50;
        expResult = 55.0;
        result = PhysicsCalculus.calcVelocityBasedOnInitialVelocityAccelerationTime(initialVelocity, acceleration, time);
        assertEquals(expResult, result, 0.5);

        initialVelocity = 30;
        acceleration = 1.6;
        time = 62;
        expResult = 129.2;
        result = PhysicsCalculus.calcVelocityBasedOnInitialVelocityAccelerationTime(initialVelocity, acceleration, time);
        assertEquals(expResult, result, 0.5);
    }

    /**
     * Test of calcRPMBasedOnVelocityGear method, of class PhysicsCalculus.
     */
    @Test
    public void testCalcRPMBasedOnVelocityGear() {
        System.out.println("calcRPMBasedOnVelocityGear");
        double velocity = 30.0;
        double gearRatio = 2.5;
        double finalDriveRatio = 4.0;
        double wheelsize = 0.8;
        double expResult = 7161.98;
        double result = PhysicsCalculus.calcRPMBasedOnVelocityGear(velocity, gearRatio, finalDriveRatio, wheelsize);
        assertEquals(expResult, result, 0.5);

        velocity = 15.0;
        gearRatio = 0.9;
        finalDriveRatio = 2.0;
        wheelsize = 0.9;
        expResult = 572.96;
        result = PhysicsCalculus.calcRPMBasedOnVelocityGear(velocity, gearRatio, finalDriveRatio, wheelsize);
        assertEquals(expResult, result, 0.5);

        velocity = 15.0;
        gearRatio = 1.6;
        finalDriveRatio = 3.2;
        wheelsize = 1.2;
        expResult = 1222.30;
        result = PhysicsCalculus.calcRPMBasedOnVelocityGear(velocity, gearRatio, finalDriveRatio, wheelsize);
        assertEquals(expResult, result, 0.5);
    }

    /**
     * Test of calcIdealMotorForceBasedAcceleration method, of class
     * PhysicsCalculus.
     */
    @Test
    public void testCalcIdealMotorForceBasedAcceleration() {
        System.out.println("calcIdealMotorForceBasedAcceleration");

        Vehicle vh1 = null;
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

        reg1.setTorqueLow(125);
        reg1.setTorqueHigh(115);
        reg1.setRpmLow(900);
        reg1.setRpmHigh(1499);
        reg1.setSFC(500);
        throttle.getRegimeList().add(reg1);

        Regime reg2 = new Regime();
        reg2.setTorqueHigh(120);
        reg2.setTorqueLow(125);
        reg2.setRpmLow(1500);
        reg2.setRpmHigh(2499);
        reg2.setSFC(450);
        throttle.getRegimeList().add(reg2);

        Regime reg3 = new Regime();
        reg3.setTorqueHigh(105);
        reg3.setTorqueLow(120);
        reg3.setRpmLow(2500);
        reg3.setRpmHigh(3499);
        reg3.setSFC(520);
        throttle.getRegimeList().add(reg3);

        Regime reg4 = new Regime();
        reg4.setTorqueHigh(90);
        reg4.setTorqueLow(105);
        reg4.setRpmLow(3500);
        reg4.setRpmHigh(4499);
        reg4.setSFC(550);
        throttle.getRegimeList().add(reg4);

        Regime reg5 = new Regime();
        reg5.setTorqueHigh(80);
        reg5.setTorqueLow(90);
        reg5.setRpmLow(4500);
        reg5.setRpmHigh(5500);
        reg5.setSFC(650);
        throttle.getRegimeList().add(reg5);

        throttleList.put(25, throttle);

        Throttle throttle1 = new Throttle();
        Regime reg11 = new Regime();

        reg11.setTorqueLow(195);
        reg11.setTorqueHigh(185);
        reg11.setRpmLow(900);
        reg11.setRpmHigh(1499);
        reg11.setSFC(500);
        throttle1.getRegimeList().add(reg11);

        Regime reg22 = new Regime();
        reg22.setTorqueHigh(190);
        reg22.setTorqueLow(195);
        reg22.setRpmLow(1500);
        reg22.setRpmHigh(2499);
        reg22.setSFC(450);
        throttle1.getRegimeList().add(reg22);

        Regime reg33 = new Regime();
        reg33.setTorqueHigh(180);
        reg33.setTorqueLow(190);
        reg33.setRpmLow(2500);
        reg33.setRpmHigh(3499);
        reg33.setSFC(520);
        throttle1.getRegimeList().add(reg33);

        Regime reg44 = new Regime();
        reg44.setTorqueHigh(150);
        reg44.setTorqueLow(190);
        reg44.setRpmLow(3500);
        reg44.setRpmHigh(4499);
        reg44.setSFC(550);
        throttle1.getRegimeList().add(reg44);

        Regime reg55 = new Regime();
        reg55.setTorqueHigh(135);
        reg55.setTorqueLow(150);
        reg55.setRpmLow(4500);
        reg55.setRpmHigh(5500);
        reg55.setSFC(650);
        throttle1.getRegimeList().add(reg55);

        throttleList.put(50, throttle1);

        Throttle throttle2 = new Throttle();
        Regime reg111 = new Regime();

        reg111.setTorqueLow(325);
        reg111.setTorqueHigh(305);
        reg111.setRpmLow(900);
        reg111.setRpmHigh(1499);
        reg111.setSFC(500);
        throttle2.getRegimeList().add(reg111);

        Regime reg222 = new Regime();
        reg222.setTorqueHigh(315);
        reg222.setTorqueLow(325);
        reg222.setRpmLow(1500);
        reg222.setRpmHigh(2499);
        reg222.setSFC(450);
        throttle2.getRegimeList().add(reg222);

        Regime reg333 = new Regime();
        reg333.setTorqueHigh(290);
        reg333.setTorqueLow(315);
        reg333.setRpmLow(2500);
        reg333.setRpmHigh(3499);
        reg333.setSFC(520);
        throttle2.getRegimeList().add(reg333);

        Regime reg444 = new Regime();
        reg444.setTorqueHigh(220);
        reg444.setTorqueLow(290);
        reg444.setRpmLow(3500);
        reg444.setRpmHigh(4499);
        reg444.setSFC(550);
        throttle2.getRegimeList().add(reg444);

        Regime reg555 = new Regime();
        reg555.setTorqueHigh(205);
        reg555.setTorqueLow(220);
        reg555.setRpmLow(4500);
        reg555.setRpmHigh(5500);
        reg555.setSFC(650);
        throttle2.getRegimeList().add(reg555);

        throttleList.put(100, throttle2);

        Accelerator acc = new Accelerator();
        acc.setThrottleList(throttleList);
        Map<String, Double> mapRoadVelocityLimit = new HashMap<>();
        mapRoadVelocityLimit.put("HIGHWAY", 90.0);
        mapRoadVelocityLimit.put("ROAD", 60.0);

        vh1 = new VehicleCombustion(900, 5500, 4, gearbox1, acc, "Pickup", "Super duper pickup", "Car", Vehicle.FUEL_GASOLINE, 1, "Combustion", 2400, 1200, 0.39, 0.8, 2.4, 0.015, mapRoadVelocityLimit, throttleList);

        Segment segment1 = new Segment(1, 100, 110, 10, 0, 10, 120, 0);
        Segment segment2 = new Segment(2, 110, 110, 8, 0, 10, 120, 0);
        Segment segment3 = new Segment(3, 110, 120, 6, 0, 10, 120, 0);
        Segment segment4 = new Segment(4, 120, 110, 1.9, 15, 5, 90, 0);
        Segment segment5 = new Segment(5, 110, 110, 2.4, 0, 10, 120, 0);

        List<Segment> listSegment = new LinkedList<>();
        listSegment.add(segment1);
        listSegment.add(segment2);
        listSegment.add(segment3);
        listSegment.add(segment4);
        listSegment.add(segment5);

        Section section = new Section();
        section.setBeginJunction(new Junction("N0"));
        section.setEndJunction(new Junction("N1"));
        section.setDirection(Section.Direction.DIRECT);
        section.setRoadID("A01");
        section.setSectionID(1);
        section.setSegmentList(listSegment);
        section.setTypology("Toll Highway");

        Section section2 = new Section();
        section2.setBeginJunction(new Junction("N0"));
        section2.setEndJunction(new Junction("N1"));
        section2.setDirection(Section.Direction.DIRECT);
        section2.setRoadID("F01");
        section2.setSectionID(1);
        section2.setSegmentList(listSegment);
        section2.setTypology("Regular Road");

        double acceleration = 1.0;
        double velocity = 22.2;
        double[] expResult = new double[5];
        expResult[0] = 5.0;
        expResult[1] = 2544.0;
        expResult[2] = 313.89;
        expResult[3] = 520.0;
        expResult[4] = 4.0;
        double[] result = PhysicsCalculus.calcIdealMotorForceBasedAcceleration(section, segment1, vh1, acceleration, velocity);
        assertEquals(expResult[0], result[0], 0.5);
        assertEquals(expResult[1], result[1], 0.5);
        assertEquals(expResult[2], result[2], 0.5);
        assertEquals(expResult[3], result[3], 0.5);
        assertEquals(expResult[4], result[4], 0.5);

        acceleration = 1.2;
        velocity = 17.2;

        expResult[0] = 5.0;
        expResult[1] = 1971.0;
        expResult[2] = 320.29;
        expResult[3] = 450.0;
        expResult[4] = 4.0;
        result = PhysicsCalculus.calcIdealMotorForceBasedAcceleration(section, segment1, vh1, acceleration, velocity);
        assertEquals(expResult[0], result[0], 0.5);
        assertEquals(expResult[1], result[1], 0.5);
        assertEquals(expResult[2], result[2], 0.5);
        assertEquals(expResult[3], result[3], 0.5);
        assertEquals(expResult[4], result[4], 0.5);

        acceleration = 0.5;
        velocity = 33.3;

        expResult[0] = 6.0;
        expResult[1] = 2862.0;
        expResult[2] = 305.94;
        expResult[3] = 520.0;
        expResult[4] = 4.0;
        result = PhysicsCalculus.calcIdealMotorForceBasedAcceleration(section, segment1, vh1, acceleration, velocity);
        assertEquals(expResult[0], result[0], 0.5);
        assertEquals(expResult[1], result[1], 0.5);
        assertEquals(expResult[2], result[2], 0.5);
        assertEquals(expResult[3], result[3], 0.5);
        assertEquals(expResult[4], result[4], 0.5);
    }

    /**
     * Test of calcTimeBasedOnInitialVelocityDesiredVelocityAndAcceleration
     * method, of class PhysicsCalculus.
     */
    @Test
    public void testCalcTimeBasedOnInitialVelocityDesiredVelocityAndAcceleration() {
        System.out.println("calcTimeBasedOnInitialVelocityDesiredVelocityAndAcceleration");
        double initialVelocity = 21.3;
        double desiredVelocity = 33.3;
        double acceleration = 1.0;
        double expResult = 12.0;
        double result = PhysicsCalculus.calcTimeBasedOnInitialVelocityDesiredVelocityAndAcceleration(initialVelocity, desiredVelocity, acceleration);
        assertEquals(expResult, result, 0.3);

        initialVelocity = 15.3;
        desiredVelocity = 49.41;
        acceleration = 0.65;
        expResult = 52.47;
        result = PhysicsCalculus.calcTimeBasedOnInitialVelocityDesiredVelocityAndAcceleration(initialVelocity, desiredVelocity, acceleration);
        assertEquals(expResult, result, 0.3);

        initialVelocity = 2.3;
        desiredVelocity = 55.5;
        acceleration = 1.3;
        expResult = 41.0;
        result = PhysicsCalculus.calcTimeBasedOnInitialVelocityDesiredVelocityAndAcceleration(initialVelocity, desiredVelocity, acceleration);
        assertEquals(expResult, result, 0.3);

        initialVelocity = 0;
        desiredVelocity = 35.0;
        acceleration = 2;
        expResult = 17.5;
        result = PhysicsCalculus.calcTimeBasedOnInitialVelocityDesiredVelocityAndAcceleration(initialVelocity, desiredVelocity, acceleration);
        assertEquals(expResult, result, 0.3);
    }

    /**
     * Test of calcDistanceBasedOnInitialVelocityDesiredVelocityAcceleration
     * method, of class PhysicsCalculus.
     */
    @Test
    public void testCalcDistanceBasedOnInitialVelocityDesiredVelocityAcceleration() {
        System.out.println("calcDistanceBasedOnInitialVelocityDesiredVelocityAcceleration");
        double initialVelocity = 10.0;
        double desiredVelocity = 36.0;
        double acceleration = 2.3;
        double expResult = 260.0;
        double result = PhysicsCalculus.calcDistanceBasedOnInitialVelocityDesiredVelocityAcceleration(initialVelocity, desiredVelocity, acceleration);
        assertEquals(expResult, result, 0.5);

        initialVelocity = 17.3;
        desiredVelocity = 55.6;
        acceleration = 1.3;
        expResult = 1073.87;
        result = PhysicsCalculus.calcDistanceBasedOnInitialVelocityDesiredVelocityAcceleration(initialVelocity, desiredVelocity, acceleration);
        assertEquals(expResult, result, 0.5);

        initialVelocity = 10.0;
        desiredVelocity = 11.0;
        acceleration = 1.0;
        expResult = 10.5;
        result = PhysicsCalculus.calcDistanceBasedOnInitialVelocityDesiredVelocityAcceleration(initialVelocity, desiredVelocity, acceleration);
        assertEquals(expResult, result, 0.5);
    }

    /**
     * Test of calcFuelComsumption method, of class PhysicsCalculus.
     */
    @Test
    public void testCalcFuelComsumption() {
        System.out.println("calcFuelComsumption");
        double sfc = 520.0;
        double motorPower = 15623.785;
        double time = 3625.0;
        double expResult = 8180.78;
        double result = PhysicsCalculus.calcFuelComsumption(sfc, motorPower, time);
        assertEquals(expResult, result, 0.5);

        sfc = 350.0;
        motorPower = 19347.325;
        time = 1552.0;
        expResult = 2919.29;
        result = PhysicsCalculus.calcFuelComsumption(sfc, motorPower, time);
        assertEquals(expResult, result, 0.5);

        sfc = 420.0;
        motorPower = 25689.525;
        time = 2985.0;
        expResult = 8946.37;
        result = PhysicsCalculus.calcFuelComsumption(sfc, motorPower, time);
        assertEquals(expResult, result, 0.5);
    }

    /**
     * Test of calcEnergySpentPerGramOfFuel method, of class PhysicsCalculus.
     */
    @Test
    public void testCalcEnergySpentPerGramOfFuel() {
        System.out.println("calcEnergySpentPerGramOfFuel");
        Vehicle vh1 = null;
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

        reg1.setTorqueLow(125);
        reg1.setTorqueHigh(115);
        reg1.setRpmLow(900);
        reg1.setRpmHigh(1499);
        reg1.setSFC(500);
        throttle.getRegimeList().add(reg1);

        Regime reg2 = new Regime();
        reg2.setTorqueHigh(120);
        reg2.setTorqueLow(125);
        reg2.setRpmLow(1500);
        reg2.setRpmHigh(2499);
        reg2.setSFC(450);
        throttle.getRegimeList().add(reg2);

        Regime reg3 = new Regime();
        reg3.setTorqueHigh(105);
        reg3.setTorqueLow(120);
        reg3.setRpmLow(2500);
        reg3.setRpmHigh(3499);
        reg3.setSFC(520);
        throttle.getRegimeList().add(reg3);

        Regime reg4 = new Regime();
        reg4.setTorqueHigh(90);
        reg4.setTorqueLow(105);
        reg4.setRpmLow(3500);
        reg4.setRpmHigh(4499);
        reg4.setSFC(550);
        throttle.getRegimeList().add(reg4);

        Regime reg5 = new Regime();
        reg5.setTorqueHigh(80);
        reg5.setTorqueLow(90);
        reg5.setRpmLow(4500);
        reg5.setRpmHigh(5500);
        reg5.setSFC(650);
        throttle.getRegimeList().add(reg5);

        throttleList.put(25, throttle);

        Throttle throttle1 = new Throttle();
        Regime reg11 = new Regime();

        reg11.setTorqueLow(195);
        reg11.setTorqueHigh(185);
        reg11.setRpmLow(900);
        reg11.setRpmHigh(1499);
        reg11.setSFC(500);
        throttle1.getRegimeList().add(reg11);

        Regime reg22 = new Regime();
        reg22.setTorqueHigh(190);
        reg22.setTorqueLow(195);
        reg22.setRpmLow(1500);
        reg22.setRpmHigh(2499);
        reg22.setSFC(450);
        throttle1.getRegimeList().add(reg22);

        Regime reg33 = new Regime();
        reg33.setTorqueHigh(180);
        reg33.setTorqueLow(190);
        reg33.setRpmLow(2500);
        reg33.setRpmHigh(3499);
        reg33.setSFC(520);
        throttle1.getRegimeList().add(reg33);

        Regime reg44 = new Regime();
        reg44.setTorqueHigh(150);
        reg44.setTorqueLow(190);
        reg44.setRpmLow(3500);
        reg44.setRpmHigh(4499);
        reg44.setSFC(550);
        throttle1.getRegimeList().add(reg44);

        Regime reg55 = new Regime();
        reg55.setTorqueHigh(135);
        reg55.setTorqueLow(150);
        reg55.setRpmLow(4500);
        reg55.setRpmHigh(5500);
        reg55.setSFC(650);
        throttle1.getRegimeList().add(reg55);

        throttleList.put(50, throttle1);

        Throttle throttle2 = new Throttle();
        Regime reg111 = new Regime();

        reg111.setTorqueLow(325);
        reg111.setTorqueHigh(305);
        reg111.setRpmLow(900);
        reg111.setRpmHigh(1499);
        reg111.setSFC(500);
        throttle2.getRegimeList().add(reg111);

        Regime reg222 = new Regime();
        reg222.setTorqueHigh(315);
        reg222.setTorqueLow(325);
        reg222.setRpmLow(1500);
        reg222.setRpmHigh(2499);
        reg222.setSFC(450);
        throttle2.getRegimeList().add(reg222);

        Regime reg333 = new Regime();
        reg333.setTorqueHigh(290);
        reg333.setTorqueLow(315);
        reg333.setRpmLow(2500);
        reg333.setRpmHigh(3499);
        reg333.setSFC(520);
        throttle2.getRegimeList().add(reg333);

        Regime reg444 = new Regime();
        reg444.setTorqueHigh(220);
        reg444.setTorqueLow(290);
        reg444.setRpmLow(3500);
        reg444.setRpmHigh(4499);
        reg444.setSFC(550);
        throttle2.getRegimeList().add(reg444);

        Regime reg555 = new Regime();
        reg555.setTorqueHigh(205);
        reg555.setTorqueLow(220);
        reg555.setRpmLow(4500);
        reg555.setRpmHigh(5500);
        reg555.setSFC(650);
        throttle2.getRegimeList().add(reg555);

        throttleList.put(100, throttle2);

        Accelerator acc = new Accelerator();
        acc.setThrottleList(throttleList);
        Map<String, Double> mapRoadVelocityLimit = new HashMap<>();
        mapRoadVelocityLimit.put("HIGHWAY", 90.0);
        mapRoadVelocityLimit.put("ROAD", 60.0);

        vh1 = new VehicleCombustion(900, 5500, 4, gearbox1, acc, "Pickup", "Super duper pickup", "Car", Vehicle.FUEL_GASOLINE, 1, "Combustion", 2400, 1200, 0.39, 0.8, 2.4, 0.015, mapRoadVelocityLimit, throttleList);

        double energy = 2.7 * Math.pow(10, 8);
        double expResult = 1.19 * Math.pow(10, 13);
        double result = PhysicsCalculus.calcEnergySpentPerGramOfFuel(vh1, energy);
        assertEquals(expResult, result, 1.0 * Math.pow(10, 13));

        energy = 1.2 * Math.pow(10, 8);
        expResult = 5.32 * Math.pow(10, 12);
        result = PhysicsCalculus.calcEnergySpentPerGramOfFuel(vh1, energy);
        assertEquals(expResult, result, 0.1 * Math.pow(10, 12));
    }
}
