/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.calculations;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.model.Project;
import lapr.project.model.Vehicle;
import lapr.project.utils.ImportException;
import lapr.project.utils.VehicleXML;
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
     * Test of forceActingOnVehicleOnSlope method, of class PhysicsCalculus.
     */
    @Test
    public void testForceActingOnVehicle() {
        System.out.println("ForceActingOnVehicle");
        double motorForce = 2070;
        double rollingResistance = 197.871;
        double gravitationalForce = 593.613;
        double airDrag = 315.110;
        double expResult = 963.406;
        double result = PhysicsCalculus.forceActingOnVehicleOnSlope(motorForce, rollingResistance, gravitationalForce, airDrag);

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
        double expResult = 2070;
        double result = PhysicsCalculus.motorForceCalculation(torqueFunction, finalDriveRatio, gearRatio, tireRatio);
        assertEquals(expResult, result, 0.0001);
        torqueFunction = -230.000;
        finalDriveRatio = 3.6;
        gearRatio = 0.75;
        tireRatio = 0.3;
        result = PhysicsCalculus.motorForceCalculation(torqueFunction, finalDriveRatio, gearRatio, tireRatio);
        assertTrue("Expected -1", result == -1);
        torqueFunction = 230.000;
        finalDriveRatio = -3.6;
        gearRatio = 0.75;
        tireRatio = 0.3;
       
        result = PhysicsCalculus.motorForceCalculation(torqueFunction, finalDriveRatio, gearRatio, tireRatio);
        assertTrue("Expected -1", result == -1);
        torqueFunction = 230.000;
        finalDriveRatio = 3.6;
        gearRatio = -0.75;
        tireRatio = 0.3;
        
        result = PhysicsCalculus.motorForceCalculation(torqueFunction, finalDriveRatio, gearRatio, tireRatio);
        assertTrue("Expected -1", result == -1);
        torqueFunction = 230.000;
        finalDriveRatio = 3.6;
        gearRatio = 0.75;
        tireRatio = -0.3;
        
        result = PhysicsCalculus.motorForceCalculation(torqueFunction, finalDriveRatio, gearRatio, tireRatio);
        assertTrue("Expected -1", result == -1);
    }

    /**
     * Test of rollingResistanceCalculationSlope method, of class PhysicsCalculus.
     */
    @Test
    public void testRollingResistanceCalculation() {
        System.out.println("rollingResistanceCalculation");
        double rollingResistanceCoeficient = 0.01;
        double mass = 2020.0;
        double angle = 0.030;
        angle= Math.toRadians(angle);
        double expResult = 198.00519750272485;
        double result = PhysicsCalculus.rollingResistanceCalculationSlope(rollingResistanceCoeficient, mass, angle);
        assertEquals(expResult, result, 0.5);
        rollingResistanceCoeficient = -0.01;
        mass = 2020.0;
        angle = 0.030;
        angle= Math.toRadians(angle);
        result = PhysicsCalculus.rollingResistanceCalculationSlope(rollingResistanceCoeficient, mass, angle);
        assertTrue("Expected -1", result == -1);
        rollingResistanceCoeficient = 0.01;
        mass = -2020.0;
        angle = 0.030;
        angle= Math.toRadians(angle);
        result = PhysicsCalculus.rollingResistanceCalculationSlope(rollingResistanceCoeficient, mass, angle);
        assertTrue("Expected -1", result == -1);
        rollingResistanceCoeficient = 0.01;
        mass = 2020.0;
        angle = -0.030;
        angle= Math.toRadians(angle);
        result = PhysicsCalculus.rollingResistanceCalculationSlope(rollingResistanceCoeficient, mass, angle);
        assertTrue("Expected -1", result == -1);
    }

    /**
     * Test of gravitationalForceCalculation method, of class PhysicsCalculus.
     */
    @Test
    public void testGravitationalForceCalculation() {
        System.out.println("gravitationalForceCalculation");
        double mass = 2020.0;
        double angle = 0.030;
        
        double expResult = 594.1938613629898;
        double result = PhysicsCalculus.gravitationalForceCalculation(mass, angle);
        assertEquals(expResult, result, 0.0001);
        mass = -2020.0;
        angle = 0.030;
        result = PhysicsCalculus.gravitationalForceCalculation(mass, angle);
        assertTrue("Expected -1", result == -1);
        mass = 2020.0;
        angle = -0.030;
        result = PhysicsCalculus.gravitationalForceCalculation(mass, angle);
        assertTrue("Expected -1", result == -1);
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
        double expResult = 315.1136785804;
        double result = PhysicsCalculus.airDragCalculation(velocityRelativeAir, airDragCoeficient, frontalArea);
        assertEquals(expResult, result, 0.0000000000001);
        velocityRelativeAir = -29.089;
        airDragCoeficient = 0.320;
        frontalArea = 1.9;
        result = PhysicsCalculus.airDragCalculation(velocityRelativeAir, airDragCoeficient, frontalArea);
        assertTrue("Expected -1", result == -1);
        velocityRelativeAir = 29.089;
        airDragCoeficient = -0.320;
        frontalArea = 1.9;
        result = PhysicsCalculus.airDragCalculation(velocityRelativeAir, airDragCoeficient, frontalArea);
        assertTrue("Expected -1", result == -1);
        velocityRelativeAir = 29.089;
        airDragCoeficient = 0.320;
        frontalArea = -2;
        result = PhysicsCalculus.airDragCalculation(velocityRelativeAir, airDragCoeficient, frontalArea);
        assertTrue("Expected -1", result == -1);
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

        double expResult = 29.08882086657216;
        double result = PhysicsCalculus.velocityRelativeAirCalculation(rpm, finalDriveRatio, kGear, tireRatio, windSpeed);
        assertEquals(expResult, result, 0.0001);
        rpm = 2500;
        finalDriveRatio = 3.6;
        kGear = 0.75;
        tireRatio = 0.3;
        windSpeed = 25.0;

        expResult = 36.033265311016600;
        result = PhysicsCalculus.velocityRelativeAirCalculation(rpm, finalDriveRatio, kGear, tireRatio, windSpeed);
        assertEquals(expResult, result, 0.0001);

        rpm = -2500;
        finalDriveRatio = 3.6;
        kGear = 0.75;
        tireRatio = 0.3;
        windSpeed = 25.0;
        result = PhysicsCalculus.velocityRelativeAirCalculation(rpm, finalDriveRatio, kGear, tireRatio, windSpeed);
        assertTrue("Expected -1", result == -1);
        rpm = 2500;
        finalDriveRatio = -3.6;
        kGear = 0.75;
        tireRatio = 0.3;
        windSpeed = 25.0;
        result = PhysicsCalculus.velocityRelativeAirCalculation(rpm, finalDriveRatio, kGear, tireRatio, windSpeed);
        assertTrue("Expected -1", result == -1);
        rpm = 2500;
        finalDriveRatio = 3.6;
        kGear = -0.75;
        tireRatio = 0.3;
        windSpeed = 25.0;
        result = PhysicsCalculus.velocityRelativeAirCalculation(rpm, finalDriveRatio, kGear, tireRatio, windSpeed);
        assertTrue("Expected -1", result == -1);
        rpm = 2500;
        finalDriveRatio = 3.6;
        kGear = 0.75;
        tireRatio = -0.3;
        windSpeed = 25.0;
        result = PhysicsCalculus.velocityRelativeAirCalculation(rpm, finalDriveRatio, kGear, tireRatio, windSpeed);
        assertTrue("Expected -1", result == -1);
        rpm = 2500;
        finalDriveRatio = 3.6;
        kGear = 0.75;
        tireRatio = 0.3;
        windSpeed = -50.0;
        result = PhysicsCalculus.velocityRelativeAirCalculation(rpm, finalDriveRatio, kGear, tireRatio, windSpeed);
        assertTrue("Expected -1", result == -1);
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

        double expResult = 104.71975511965978;
        double result = PhysicsCalculus.velocityRelativeGroundCalculation(rpm, finalDriveRatio, kGear, tireRatio);
        assertEquals(expResult, result, 0.0001);

        rpm = -2500;
        finalDriveRatio = 3.6;
        kGear = 0.75;
        tireRatio = 0.3;
        result = PhysicsCalculus.velocityRelativeGroundCalculation(rpm, finalDriveRatio, kGear, tireRatio);
        assertTrue("Expected -1", result == -1);
        rpm = 2500;
        finalDriveRatio = -3.6;
        kGear = 0.75;
        tireRatio = 0.3;
        result = PhysicsCalculus.velocityRelativeGroundCalculation(rpm, finalDriveRatio, kGear, tireRatio);
        assertTrue("Expected -1", result == -1);
        rpm = 2500;
        finalDriveRatio = 3.6;
        kGear = -0.75;
        tireRatio = 0.3;
        result = PhysicsCalculus.velocityRelativeGroundCalculation(rpm, finalDriveRatio, kGear, tireRatio);
        assertTrue("Expected -1", result == -1);
        rpm = 2500;
        finalDriveRatio = 3.6;
        kGear = 0.75;
        tireRatio = -0.3;
        result = PhysicsCalculus.velocityRelativeGroundCalculation(rpm, finalDriveRatio, kGear, tireRatio);
        assertTrue("Expected -1", result == -1);
    }

    @Test
    public void testCalculateRPM() {
    }
}
