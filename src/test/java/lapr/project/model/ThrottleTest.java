/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
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
public class ThrottleTest {

    public ThrottleTest() {
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
     * Test of getRegimeList method, of class Throttle.
     */
    @Test
    public void testGetRegimeList() {
        System.out.println("getRegimeList");
        Throttle instance = new Throttle();
        List<Regime> expResult = new ArrayList<>();
        List<Regime> result = instance.getRegimeList();
        assertEquals(expResult, result);

        instance = new Throttle(result);
        assertEquals(expResult, result);

    }

    /**
     * Test of setRegimeList method, of class Throttle.
     */
    @Test
    public void testSetRegimeList() {
        System.out.println("setRegimeList");
        List<Regime> m_regime_list = new ArrayList<>();
        List<Regime> result = new ArrayList<>();
        Throttle instance = new Throttle();
        instance.setRegimeList(m_regime_list);
        assertEquals(result, m_regime_list);
    }

    /**
     * Test of toString method, of class Throttle.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Throttle instance = new Throttle();
        String expResult = "Throttle{m_regime_list=[]}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }


}
