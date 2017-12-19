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
        assertTrue(0 == instance.getM_SFC());
        assertTrue(0 == instance.getM_rpm_high());
        assertTrue(0 == instance.getM_rpm_low());
        assertTrue(0 == instance.getM_torque());

        instance.setRpmLow(2000);
        assertFalse(0 == instance.getM_rpm_low());
        assertTrue(2000 == instance.getM_rpm_low());
        instance.setRpmHigh(5000);
        assertFalse(0 == instance.getM_rpm_high());
        assertTrue(5000 == instance.getM_rpm_high());
        instance.setSFC(650);
        assertFalse(0 == instance.getM_SFC());
        assertTrue(650 == instance.getM_SFC());
        instance.setTorque(1900);
        assertFalse(0 == instance.getM_torque());
        assertTrue(1900 == instance.getM_torque());
    }

    /**
     * Test of illegalArgumentException in class Junction.
     */
    @Test(expected = IllegalArgumentException.class)
    public void IllegalArgumentExceptionTest() {
        System.out.println("validateAcceleratorPedal");
        Regime instance = new Regime(1200.0, -2000, 5600, 560);
        instance.validateRegime();
    }

    /**
     * Test of illegalArgumentException in class Junction.
     */
    @Test(expected = IllegalArgumentException.class)
    public void IllegalArgumentExceptionTest2() {
        System.out.println("validateAcceleratorPedal");
        Regime instance = new Regime(-1200.0, 2000, 5600, 560);
        instance.validateRegime();
    }

    /**
     * Test of illegalArgumentException in class Junction.
     */
    @Test(expected = IllegalArgumentException.class)
    public void IllegalArgumentExceptionTest3() {
        System.out.println("validateAcceleratorPedal");
        Regime instance = new Regime(1200.0, 2000, -5600, 560);
        instance.validateRegime();
    }

    /**
     * Test of illegalArgumentException in class Junction.
     */
    @Test(expected = IllegalArgumentException.class)
    public void IllegalArgumentExceptionTest4() {
        System.out.println("validateAcceleratorPedal");
        Regime instance = new Regime(1200.0, 2000, 5600, -560);
        instance.validateRegime();
    }

    /**
     * Test of illegalArgumentException in class Junction.
     */
    @Test(expected = IllegalArgumentException.class)
    public void IllegalArgumentExceptionTest5() {
        System.out.println("validateAcceleratorPedal");
        Regime instance = new Regime(1200, 8000, 5600, 560);
        instance.validateRegime();
    }

    /**
     * Test of toString method, of class Regime.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Regime instance = new Regime();
        String expResult = "Regime{m_torque=0.0, m_rpm_low=0.0, m_rpm_high=0.0, m_SFC=0.0}";
        String result = instance.toString();
        assertEquals(expResult, result);

        instance = new Regime(1200, 2500, 3100, 250);
        expResult = "Regime{m_torque=1200.0, m_rpm_low=2500.0, m_rpm_high=3100.0, m_SFC=250.0}";
        result = instance.toString();
        assertEquals(expResult, result);

    }

}
