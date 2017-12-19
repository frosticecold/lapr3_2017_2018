/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.HashMap;
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
public class AcceleratorTest {

    public AcceleratorTest() {
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
     * Test of getThrottleList method, of class Accelerator.
     */
    @Test
    public void testGetThrottleList() {
        System.out.println("getThrottleList");
        ArrayList<Regime> m_regime_list = new ArrayList<>();
        Regime r1 = new Regime(115, 900, 1499, 500);
        Regime r2 = new Regime(125, 1500, 2499, 450);
        Regime r3 = new Regime(120, 2500, 3499, 520);
        Regime r4 = new Regime(105, 3500, 4499, 550);
        Regime r5 = new Regime(90, 4500, 5500, 650);
        m_regime_list.add(r1);
        m_regime_list.add(r2);
        m_regime_list.add(r3);
        m_regime_list.add(r4);
        m_regime_list.add(r5);
        Throttle t1 = new Throttle(m_regime_list);
        Map<Integer, Throttle> expResult = new HashMap<>();
        int throttleId = 25;
        expResult.put(throttleId, t1);

        Accelerator instance = new Accelerator();
        instance.setThrottleList(expResult);
        Map<Integer, Throttle> result = instance.getThrottleList();
        assertTrue(result == expResult);

        instance.setThrottleList(null);
        result = instance.getThrottleList();
        assertNull(result);

    }

    /**
     * Test of setThrottleList method, of class Accelerator.
     */
    @Test
    public void testSetThrottleList() {
        System.out.println("setThrottleList");
        ArrayList<Regime> m_regime_list = new ArrayList<>();
        Regime r1 = new Regime(115, 900, 1499, 500);
        Regime r2 = new Regime(125, 1500, 2499, 450);
        Regime r3 = new Regime(120, 2500, 3499, 520);
        Regime r4 = new Regime(105, 3500, 4499, 550);
        Regime r5 = new Regime(90, 4500, 5500, 650);
        m_regime_list.add(r1);
        m_regime_list.add(r2);
        m_regime_list.add(r3);
        m_regime_list.add(r4);
        m_regime_list.add(r5);
        Throttle t1 = new Throttle(m_regime_list);
        Map<Integer, Throttle> expResult = new HashMap<>();
        int throttleId = 25;
        expResult.put(throttleId, t1);

        Accelerator instance = new Accelerator();
        instance.setThrottleList(expResult);
        Map<Integer, Throttle> result = instance.getThrottleList();
        assertTrue(result == expResult);

        instance.setThrottleList(null);
        result = instance.getThrottleList();
        assertNull(result);
    }

    /**
     * Test of validateAcceleratorPedal method, of class Accelerator.
     */
    @Test
    public void testValidateAcceleratorPedal() {
        System.out.println("validateAcceleratorPedal");

        ArrayList<Regime> m_regime_list = new ArrayList<>();
        Regime r1 = new Regime(115, 900, 1499, 500);
        Regime r2 = new Regime(125, 1500, 2499, 450);
        Regime r3 = new Regime(120, 2500, 3499, 520);
        Regime r4 = new Regime(105, 3500, 4499, 550);
        Regime r5 = new Regime(90, 4500, 5500, 650);
        m_regime_list.add(r1);
        m_regime_list.add(r2);
        m_regime_list.add(r3);
        m_regime_list.add(r4);
        m_regime_list.add(r5);
        Throttle t1 = new Throttle(m_regime_list);
        Map<Integer, Throttle> mapThrottle = new HashMap<>();
        int throttleId = 25;
        mapThrottle.put(throttleId, t1);

        Accelerator instance = new Accelerator();
        instance.setThrottleList(mapThrottle);
        boolean expResult = true;
        boolean result = instance.validateAcceleratorPedal();
        assertEquals(expResult, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void outOfBounds() {
        System.out.println("validateAcceleratorPedal");
        Accelerator instance = new Accelerator();
        instance.validateAcceleratorPedal();
    }

    /**
     * Test of toString method, of class Accelerator.
     */
    @Test
    public void testToString() {
        System.out.println("AcceleratortoStringTest");
        ArrayList<Regime> m_regime_list = new ArrayList<>();
        Regime r1 = new Regime(115, 900, 1499, 500);
        Regime r2 = new Regime(125, 1500, 2499, 450);
        Regime r3 = new Regime(120, 2500, 3499, 520);
        Regime r4 = new Regime(105, 3500, 4499, 550);
        Regime r5 = new Regime(90, 4500, 5500, 650);
        m_regime_list.add(r1);
        m_regime_list.add(r2);
        m_regime_list.add(r3);
        m_regime_list.add(r4);
        m_regime_list.add(r5);
        Throttle t1 = new Throttle(m_regime_list);
        Map<Integer, Throttle> mapThrottle = new HashMap<>();
        int throttleId = 25;
        mapThrottle.put(throttleId, t1);

        Accelerator instance = new Accelerator();
        instance.setThrottleList(mapThrottle);
        String expResult = "Accelerator{m_throttle_list={25=Throttle{m_regime_list=[Regime{m_torque=115.0, m_rpm_low=900.0, m_rpm_high=1499.0, m_SFC=500.0}, Regime{m_torque=125.0, m_rpm_low=1500.0, m_rpm_high=2499.0, m_SFC=450.0}, Regime{m_torque=120.0, m_rpm_low=2500.0, m_rpm_high=3499.0, m_SFC=520.0}, Regime{m_torque=105.0, m_rpm_low=3500.0, m_rpm_high=4499.0, m_SFC=550.0}, Regime{m_torque=90.0, m_rpm_low=4500.0, m_rpm_high=5500.0, m_SFC=650.0}]}}}";
        instance = new Accelerator();
        expResult = "Accelerator{m_throttle_list={}}";
        String result = instance.toString();
        assertEquals(expResult, result);

    }

    /**
     * Test of toString method, of class Accelerator.
     */
    @Test
    public void testCopy() {
        System.out.println("AcceleratortoStringTest");
        ArrayList<Regime> m_regime_list = new ArrayList<>();
        Regime r1 = new Regime(115, 900, 1499, 500);
        Regime r2 = new Regime(125, 1500, 2499, 450);
        Regime r3 = new Regime(120, 2500, 3499, 520);
        Regime r4 = new Regime(105, 3500, 4499, 550);
        Regime r5 = new Regime(90, 4500, 5500, 650);
        m_regime_list.add(r1);
        m_regime_list.add(r2);
        m_regime_list.add(r3);
        m_regime_list.add(r4);
        m_regime_list.add(r5);
        Throttle t1 = new Throttle(m_regime_list);
        Map<Integer, Throttle> mapThrottle = new HashMap<>();
        int throttleId = 25;
        mapThrottle.put(throttleId, t1);

        Accelerator expResult = new Accelerator();
        expResult.setThrottleList(mapThrottle);
        
        
        Accelerator result = expResult.copy();
        assertTrue(result.toString().equals(expResult.toString()));
        
        expResult.setThrottleList(new HashMap<>());
        assertNotEquals(result,expResult);
        
    }

}
