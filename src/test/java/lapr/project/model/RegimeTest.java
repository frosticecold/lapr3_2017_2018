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
 * @author MarioDias
 */
public class RegimeTest {

    public RegimeTest() {
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

    @Test
    public void testConstructor() {
        System.out.println("constructorTest");
        Regime reg1 = new Regime();
        reg1.setRpmHigh(10000);
        reg1.setRpmLow(2000);
        reg1.setSFC(500);
        reg1.setTorqueHigh(200);
        reg1.setTorqueLow(100);

        Regime reg2 = new Regime(reg1);

        assertEquals(reg1.getRpmHigh(), reg2.getRpmHigh(), 0.05);
        assertEquals(reg1.getRpmLow(), reg2.getRpmLow(), 0.05);
        assertEquals(reg1.getSFC(), reg2.getSFC(), 0.05);
        assertEquals(reg1.getTorqueHigh(), reg2.getTorqueHigh(), 0.05);
        assertEquals(reg1.getTorqueLow(), reg2.getTorqueLow(), 0.05);

    }

    @Test
    public void testGetTorqueByRPM() {
        System.out.println("testGetTorqueByRPM");
        Regime reg1 = new Regime();
        reg1.setRpmHigh(10000);
        reg1.setRpmLow(2000);
        reg1.setSFC(500);
        reg1.setTorqueHigh(200);
        reg1.setTorqueLow(100);

        try {
            reg1.getTorqueByRPM(100);
        } catch (IllegalArgumentException ex) {
        }
        reg1.getTorqueByRPM(5000);
        try {
            reg1.getTorqueByRPM(400000);
        } catch (IllegalArgumentException ex) {
        }

        System.out.println("testGetSFCbyRPM");
        try {
            reg1.getSFCByRPM(100);
        } catch (IllegalArgumentException ex) {
        }
        reg1.getSFCByRPM(5000);
        try {
            reg1.getSFCByRPM(50000);
        } catch (IllegalArgumentException ex) {
        }

        reg1.getTorqueByRPM(2000);
        reg1.getTorqueByRPM(4000);
        reg1.getTorqueByRPM(10000);

        reg1.getSFCByRPM(2000);
        reg1.getSFCByRPM(4000);
        reg1.getSFCByRPM(10000);

    }

    @Test
    public void testValidate() {

        Regime reg1 = new Regime();
        try {
            reg1.validateRegime();
        } catch (IllegalArgumentException ex) {
//            System.out.println(ex.getMessage());
        }
        try {
            reg1.setRpmHigh(10000);
            reg1.validateRegime();
        } catch (IllegalArgumentException ex) {
//            System.out.println(ex.getMessage());
        }

        try {
            reg1.setRpmLow(2000);
            reg1.validateRegime();
        } catch (IllegalArgumentException ex) {
//            System.out.println(ex.getMessage());
        }

        try {
            reg1.setSFC(500);
            reg1.validateRegime();
        } catch (IllegalArgumentException ex) {
//            System.out.println(ex.getMessage());
        }

        try {
            reg1.setTorqueHigh(200);
            reg1.validateRegime();
        } catch (IllegalArgumentException ex) {
//            System.out.println(ex.getMessage());
        }
        try {
            reg1.setTorqueLow(100);
            reg1.validateRegime();
        } catch (IllegalArgumentException ex) {
//            System.out.println(ex.getMessage());
        }
        try {
            reg1.setRpmLow(1000000);
            reg1.validateRegime();
        } catch (IllegalArgumentException ex) {
//            System.out.println(ex.getMessage());
        }

        Regime reg2 = new Regime();
        reg2.setRpmLow(-1000);
        reg2.setRpmHigh(10000);
        reg2.setSFC(500);
        reg2.setTorqueHigh(200);
        reg2.setTorqueLow(100);
        try {
            reg2.validateRegime();
        } catch (IllegalArgumentException ex) {
//            System.out.println(ex.getMessage());
        }

        try {
            reg2.setRpmLow(1000000);
            reg2.validateRegime();
        } catch (IllegalArgumentException ex) {
//            System.out.println(ex.getMessage());
        }

        try {
            reg2.setRpmLow(1000);
            reg2.setRpmHigh(50);
            reg2.validateRegime();
        } catch (IllegalArgumentException ex) {
//            System.out.println(ex.getMessage());
        }

        try {
            reg2.setRpmHigh(50000);
            reg2.setTorqueLow(-100);
            reg2.validateRegime();
        } catch (IllegalArgumentException ex) {
//            System.out.println(ex.getMessage());
        }

        try {
            reg2.setTorqueLow(100);
            reg2.setTorqueHigh(-100);
            reg2.validateRegime();

        } catch (IllegalArgumentException ex) {
//            System.out.println(ex.getMessage());
        }

        try {
            reg2.setTorqueHigh(200);
            reg2.setSFC(-1);
            reg2.validateRegime();
        } catch (IllegalArgumentException ex) {
//            System.out.println(ex.getMessage());
        }

        try {

            reg2.setSFC(100);
            reg2.validateRegime();
        } catch (IllegalArgumentException ex) {
//            System.out.println(ex.getMessage());
        }

        reg2.setRpmLow(1000);
        reg2.setRpmHigh(-10000);
        reg2.setSFC(500);
        reg2.setTorqueHigh(200);
        reg2.setTorqueLow(100);
        try {
            reg2.validateRegime();
        } catch (IllegalArgumentException ex) {
        }
        reg2.setRpmLow(1000);
        reg2.setRpmHigh(10000);
        reg2.setSFC(500);
        reg2.setTorqueHigh(200);
        reg2.setTorqueLow(3100);
        try {
            reg2.validateRegime();
        } catch (IllegalArgumentException ex) {
        }

        reg2.setRpmLow(1000);
        reg2.setRpmHigh(-10000);
        reg2.setSFC(500);
        reg2.setTorqueHigh(200);
        reg2.setTorqueLow(3100);
        try {
            reg2.validateRegime();
        } catch (IllegalArgumentException ex) {
        }
    }

    /**
     * Test of setTorque method, of class Regime.
     */
    @Test
    public void testSetGetRegime() {
        System.out.println("setTorque");

        Regime instance = new Regime();
        assertTrue(0 == instance.getSFC());
        assertTrue(0 == instance.getRpmHigh());
        assertTrue(0 == instance.getRpmLow());
        assertTrue(0 == instance.getTorqueLow());
        assertTrue(0 == instance.getTorqueHigh());

        instance.setRpmLow(2000);
        assertFalse(0 == instance.getRpmLow());
        assertTrue(2000 == instance.getRpmLow());
        instance.setRpmHigh(5000);
        assertFalse(0 == instance.getRpmHigh());
        assertTrue(5000 == instance.getRpmHigh());
        instance.setSFC(650);
        assertFalse(0 == instance.getSFC());
        assertTrue(650 == instance.getSFC());
        instance.setTorqueLow(1900);
        assertFalse(0 == instance.getTorqueLow());
        assertTrue(1900 == instance.getTorqueLow());
        instance.setTorqueHigh(900);
        assertFalse(0 == instance.getTorqueHigh());
        assertTrue(900 == instance.getTorqueHigh());
    }

    /**
     * Test of illegalArgumentException in class Junction.
     */
    @Test(expected = IllegalArgumentException.class)
    public void IllegalArgumentExceptionTest() {
        System.out.println("validateAcceleratorPedal");
        Regime instance = new Regime(900.0, 1900.0, -2000, 5600, 560);
        instance.validateRegime();
    }

    /**
     * Test of illegalArgumentException in class Junction.
     */
    @Test(expected = IllegalArgumentException.class)
    public void IllegalArgumentExceptionTest2() {
        System.out.println("validateAcceleratorPedal");
        Regime instance = new Regime(900.0, 1900.0, -2000, 5600, 560);
        instance.validateRegime();
    }

    /**
     * Test of illegalArgumentException in class Junction.
     */
    @Test(expected = IllegalArgumentException.class)
    public void IllegalArgumentExceptionTest3() {
        System.out.println("validateAcceleratorPedal");
        Regime instance = new Regime(900.0, 1900.0, -2000, 5600, 560);
        instance.validateRegime();
    }

    /**
     * Test of illegalArgumentException in class Junction.
     */
    @Test(expected = IllegalArgumentException.class)
    public void IllegalArgumentExceptionTest4() {
        System.out.println("validateAcceleratorPedal");
        Regime instance = new Regime(900.0, 1900.0, -2000, 5600, 560);
        instance.validateRegime();
    }

    /**
     * Test of illegalArgumentException in class Junction.
     */
    @Test(expected = IllegalArgumentException.class)
    public void IllegalArgumentExceptionTest5() {
        System.out.println("validateAcceleratorPedal");
        Regime instance = new Regime(900.0, 1900.0, -2000, 5600, 560);
        instance.validateRegime();
    }

    /**
     * Test of toString method, of class Regime.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Regime instance = new Regime();
        String expResult = "Regime{torque_low=0.0, torque_high=0.0, rplow=0.0, rphigh=0.0, SFC=0.0}";
        String result = instance.toString();
        assertEquals(expResult, result);

        instance = new Regime(900, 1900, 2500, 3100, 250);
        expResult = "Regime{torque_low=900.0, torque_high=1900.0, rplow=2500.0, rphigh=3100.0, SFC=250.0}";
        result = instance.toString();
        assertEquals(expResult, result);

    }

}
