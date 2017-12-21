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
        String expResult = "Regime{m_torque_low=0.0, m_torque_high=0.0, m_rpm_low=0.0, m_rpm_high=0.0, m_SFC=0.0}";
        String result = instance.toString();
        assertEquals(expResult, result);

        instance = new Regime(900, 1900, 2500, 3100, 250);
        expResult = "Regime{m_torque_low=900.0, m_torque_high=1900.0, m_rpm_low=2500.0, m_rpm_high=3100.0, m_SFC=250.0}";
        result = instance.toString();
        assertEquals(expResult, result);

    }

}
