/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.calculations;

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
        System.out.println("ForceActingOnVehicle");
        PhysicsCalculus instance = new PhysicsCalculus();
        double motorForce = 2070;
        double rollingResistance = 197.871;
        double gravitationalForce = 593.613;
        double airDrag = 315.110;
        double expResult = 963.406;
        double result = instance.forceActingOnVehicle(motorForce, rollingResistance, gravitationalForce, airDrag);

        assertEquals("Exp result is 963,406 N", expResult, result, 0.001);
    }

    /**
     * Test of motorForceCalculation method, of class PhysicsCalculus.
     */
    @Test
    public void testMotorForceCalculation() {
        System.out.println("motorForceCalculation");
        double torqueFunction = 230.000;
        double finalDriveRatio = 3.6;
        double gearRatio = 0.75;
        double tireRatio = 0.3;
        PhysicsCalculus instance = new PhysicsCalculus();
        double expResult = 2070;
        double result = instance.motorForceCalculation(torqueFunction, finalDriveRatio, gearRatio, tireRatio);
        result = (result * 100.00) / 100.00;
        assertEquals(expResult, result, 0.0001);
    }

    /**
     * Test of rollingResistanceCalculation method, of class PhysicsCalculus.
     */
    @Test
    public void testRollingResistanceCalculation() {
        System.out.println("rollingResistanceCalculation");
        double rollingResistanceCoeficient = 0.01;
        double mass = 2020.0;
        double angle = 0.030;
        PhysicsCalculus instance = new PhysicsCalculus();
        double expResult = 198.00519750272485;
        double result = instance.rollingResistanceCalculation(rollingResistanceCoeficient, mass, angle);
        assertEquals(expResult, result, 0.0001);
    }

    /**
     * Test of gravitationalForceCalculation method, of class PhysicsCalculus.
     */
    @Test
    public void testGravitationalForceCalculation() {
        System.out.println("gravitationalForceCalculation");
        double mass = 2020.0;
        double angle = 0.030;
        PhysicsCalculus instance = new PhysicsCalculus();
        double expResult = 594.1938613629898;
        double result = instance.gravitationalForceCalculation(mass, angle);
        assertEquals(expResult, result, 0.0001);
    }

    /**
     * Test of airDragCalculation method, of class PhysicsCalculus.
     */
    @Test
    public void testAirDragCalculation() {
        System.out.println("airDragCalculation");
        double velocityRelativeAir = 29.089;
        double airDragCoeficient = 0.320;
        double frontalArea = 1.9;
        double airDensity = 1.225;
        PhysicsCalculus instance = new PhysicsCalculus();
        double expResult = 315.1136785804;
        double result = instance.airDragCalculation(velocityRelativeAir, airDragCoeficient, frontalArea, airDensity);
        assertEquals(expResult, result, 0.0000000000001);

    }

    /**
     * Test of velocityRelativeAirCalculation method, of class PhysicsCalculus.
     */
    @Test
    public void testVelocityRelativeAirCalculation() {
        System.out.println("velocityRelativeAirCalculation");
        double rpm = 2500;
        double finalDriveRatio = 3.6;
        double kGear = 0.75;
        double tireRatio = 0.3;
        double windSpeed = 0.0;
        PhysicsCalculus instance = new PhysicsCalculus();
        double expResult = 29.08882086657216;
        double result = instance.velocityRelativeAirCalculation(rpm, finalDriveRatio, kGear, tireRatio, windSpeed);
        assertEquals(expResult, result, 0.0001);
        rpm = 2500;
        finalDriveRatio = 3.6;
        kGear = 0.75;
        tireRatio = 0.3;
        windSpeed = 25.0;
        instance = new PhysicsCalculus();
        expResult = 36.033265311016600;
        result = instance.velocityRelativeAirCalculation(rpm, finalDriveRatio, kGear, tireRatio, windSpeed);
        assertEquals(expResult, result, 0.0001);
    }

    /**
     * Test of velocityRelativeGroundCalculation method, of class
     * PhysicsCalculus.
     */
    @Test
    public void testVelocityRelativeGroundCalculation() {
        System.out.println("velocityRelativeGroundCalculation");
        double rpm = 2500;
        double finalDriveRatio = 3.6;
        double kGear = 0.75;
        double tireRatio = 0.3;
        PhysicsCalculus instance = new PhysicsCalculus();
        double expResult = 104.71975511965978;
        double result = instance.velocityRelativeGroundCalculation(rpm, finalDriveRatio, kGear, tireRatio);
        assertEquals(expResult, result, 0.0001);
    }

}
