/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
     * Test of copy constructor
     */
    @Test
    public void testCopyConstructor() {
        System.out.println("copyConstructor");
        Accelerator acc_a = new Accelerator();
        ArrayList<Regime> regime_list = new ArrayList<>();
        Regime r1 = new Regime(115, 500, 900, 1499, 500);
        Regime r2 = new Regime(125, 600, 1500, 2499, 450);
        Regime r3 = new Regime(120, 550, 2500, 3499, 520);
        Regime r4 = new Regime(105, 400, 3500, 4499, 550);
        Regime r5 = new Regime(90, 300, 4500, 5500, 650);
        regime_list.add(r1);
        regime_list.add(r2);
        regime_list.add(r3);
        regime_list.add(r4);
        regime_list.add(r5);
        Throttle t1 = new Throttle(regime_list);
        LinkedHashMap<Integer,Throttle> map = new LinkedHashMap<>();
        map.put(25, t1);
        acc_a.setThrottleList(map);
        
        Accelerator ac_b = new Accelerator(acc_a);
        
        Throttle t25 = ac_b.getThrottleList().get(25);
        assertEquals(t1.getRegimeList().get(0).getRpmLow(),t25.getRegimeList().get(0).getRpmLow(),0.05);
        assertEquals(t1.getRegimeList().get(1).getRpmLow(),t25.getRegimeList().get(1).getRpmLow(),0.05);
        assertEquals(t1.getRegimeList().get(2).getRpmLow(),t25.getRegimeList().get(2).getRpmLow(),0.05);
        assertEquals(t1.getRegimeList().get(3).getRpmLow(),t25.getRegimeList().get(3).getRpmLow(),0.05);
    }

    /**
     * Test of getThrottleList method, of class Accelerator.
     */
    @Test
    public void testGetThrottleList() {
        System.out.println("getThrottleList");
        ArrayList<Regime> regime_list = new ArrayList<>();
        Regime r1 = new Regime(115, 500, 900, 1499, 500);
        Regime r2 = new Regime(125, 600, 1500, 2499, 450);
        Regime r3 = new Regime(120, 550, 2500, 3499, 520);
        Regime r4 = new Regime(105, 400, 3500, 4499, 550);
        Regime r5 = new Regime(90, 300, 4500, 5500, 650);
        regime_list.add(r1);
        regime_list.add(r2);
        regime_list.add(r3);
        regime_list.add(r4);
        regime_list.add(r5);
        Throttle t1 = new Throttle(regime_list);
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
        ArrayList<Regime> regime_list = new ArrayList<>();
        Regime r1 = new Regime(115, 500, 900, 1499, 500);
        Regime r2 = new Regime(125, 600, 1500, 2499, 450);
        Regime r3 = new Regime(120, 550, 2500, 3499, 520);
        Regime r4 = new Regime(105, 400, 3500, 4499, 550);
        Regime r5 = new Regime(90, 300, 4500, 5500, 650);
        regime_list.add(r1);
        regime_list.add(r2);
        regime_list.add(r3);
        regime_list.add(r4);
        regime_list.add(r5);
        Throttle t1 = new Throttle(regime_list);
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

        ArrayList<Regime> regime_list = new ArrayList<>();
        Regime r1 = new Regime(115, 500, 900, 1499, 500);
        Regime r2 = new Regime(125, 600, 1500, 2499, 450);
        Regime r3 = new Regime(120, 550, 2500, 3499, 520);
        Regime r4 = new Regime(105, 400, 3500, 4499, 550);
        Regime r5 = new Regime(90, 300, 4500, 5500, 650);
        regime_list.add(r1);
        regime_list.add(r2);
        regime_list.add(r3);
        regime_list.add(r4);
        regime_list.add(r5);
        Throttle t1 = new Throttle(regime_list);
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
        ArrayList<Regime> regime_list = new ArrayList<>();
        Regime r1 = new Regime(115, 500, 900, 1499, 500);
        Regime r2 = new Regime(125, 600, 1500, 2499, 450);
        Regime r3 = new Regime(120, 550, 2500, 3499, 520);
        Regime r4 = new Regime(105, 400, 3500, 4499, 550);
        Regime r5 = new Regime(90, 300, 4500, 5500, 650);
        regime_list.add(r1);
        regime_list.add(r2);
        regime_list.add(r3);
        regime_list.add(r4);
        regime_list.add(r5);
        Throttle t1 = new Throttle(regime_list);
        Map<Integer, Throttle> mapThrottle = new HashMap<>();
        int throttleId = 25;
        mapThrottle.put(throttleId, t1);

        Accelerator instance = new Accelerator();
        instance.setThrottleList(mapThrottle);
        String result = instance.toString();
        String expResult = "Accelerator{throttle_list={25=Throttle{regime_list=[Regime{torque_low=115.0, torque_high=500.0, rplow=900.0, rphigh=1499.0, SFC=500.0}, Regime{torque_low=125.0, torque_high=600.0, rplow=1500.0, rphigh=2499.0, SFC=450.0}, Regime{torque_low=120.0, torque_high=550.0, rplow=2500.0, rphigh=3499.0, SFC=520.0}, Regime{torque_low=105.0, torque_high=400.0, rplow=3500.0, rphigh=4499.0, SFC=550.0}, Regime{torque_low=90.0, torque_high=300.0, rplow=4500.0, rphigh=5500.0, SFC=650.0}]}}}";
        assertEquals(expResult, result);
        instance = new Accelerator();
        expResult = "Accelerator{throttle_list={}}";
        result = instance.toString();
        assertEquals(expResult, result);

    }

}
