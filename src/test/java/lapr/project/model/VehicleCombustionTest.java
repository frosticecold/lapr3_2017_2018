/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

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

//    /**
//     * Test of getMinRpm method, of class VehicleCombustion.
//     */
//    @Test
//    public void testGetMinRpm() {
//        System.out.println("getMinRpm");
//        VehicleCombustion instance = new VehicleCombustion();
//        double expResult = 0.0;
//        double result = instance.getMinRpm();
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getMaxRpm method, of class VehicleCombustion.
//     */
//    @Test
//    public void testGetMaxRpm() {
//        System.out.println("getMaxRpm");
//        VehicleCombustion instance = new VehicleCombustion();
//        double expResult = 0.0;
//        double result = instance.getMaxRpm();
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getFinalDriveRatio method, of class VehicleCombustion.
//     */
//    @Test
//    public void testGetFinalDriveRatio() {
//        System.out.println("getFinalDriveRatio");
//        VehicleCombustion instance = new VehicleCombustion();
//        double expResult = 0.0;
//        double result = instance.getFinalDriveRatio();
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getGearbox method, of class VehicleCombustion.
//     */
//    @Test
//    public void testGetGearbox() {
//        System.out.println("getGearbox");
//        VehicleCombustion instance = new VehicleCombustion();
//        Gearbox expResult = null;
//        Gearbox result = instance.getGearbox();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getAccelerator method, of class VehicleCombustion.
//     */
//    @Test
//    public void testGetAccelerator() {
//        System.out.println("getAccelerator");
//        VehicleCombustion instance = new VehicleCombustion();
//        Accelerator expResult = null;
//        Accelerator result = instance.getAccelerator();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setMinRPM method, of class VehicleCombustion.
//     */
//    @Test
//    public void testSetMinRPM() {
//        System.out.println("setMinRPM");
//        double minRPM = 0.0;
//        VehicleCombustion instance = new VehicleCombustion();
//        instance.setMinRPM(minRPM);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setMaxRPM method, of class VehicleCombustion.
//     */
//    @Test
//    public void testSetMaxRPM() {
//        System.out.println("setMaxRPM");
//        double maxRPM = 0.0;
//        VehicleCombustion instance = new VehicleCombustion();
//        instance.setMaxRPM(maxRPM);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setFinalDriveRatio method, of class VehicleCombustion.
//     */
//    @Test
//    public void testSetFinalDriveRatio() {
//        System.out.println("setFinalDriveRatio");
//        double finalDriveRatio = 0.0;
//        VehicleCombustion instance = new VehicleCombustion();
//        instance.setFinalDriveRatio(finalDriveRatio);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setGearbox method, of class VehicleCombustion.
//     */
//    @Test
//    public void testSetGearbox() {
//        System.out.println("setGearbox");
//        Gearbox gearbox = null;
//        VehicleCombustion instance = new VehicleCombustion();
//        instance.setGearbox(gearbox);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setAccelerator method, of class VehicleCombustion.
//     */
//    @Test
//    public void testSetAccelerator() {
//        System.out.println("setAccelerator");
//        Accelerator acceleratorPedal = null;
//        VehicleCombustion instance = new VehicleCombustion();
//        instance.setAccelerator(acceleratorPedal);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of toString method, of class VehicleCombustion.
//     */
//    @Test
//    public void testToString() {
//        System.out.println("toString");
//        VehicleCombustion instance = new VehicleCombustion();
//        String expResult = "";
//        String result = instance.toString();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of validateCombustionVehicle method, of class VehicleCombustion.
//     */
//    @Test
//    public void testValidateCombustionVehicle() {
//        System.out.println("validateCombustionVehicle");
//        VehicleCombustion instance = new VehicleCombustion();
//        boolean expResult = false;
//        boolean result = instance.validateCombustionVehicle();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
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
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
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
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
