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
public class GearTest {

    public GearTest() {
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
     * Test of getGearID method, of class Gear.
     */
    @Test
    public void testGetSet() {
        System.out.println("getGearID");
        int id = 1;
        double ratio = 0.95;
        Gear instance = new Gear(id, ratio);
        int expResult = id;
        int result = instance.getGearID();
        assertEquals(expResult, result);

        instance = new Gear();
        instance.setGearID(0);
        result = instance.getGearID();
        assertEquals(0, result);

        instance.setGearID(5);
        result = instance.getGearID();
        assertEquals(5, result);

        instance.setGearID(3);
        result = instance.getGearID();
        assertNotEquals(5, result);

        instance.setRatio(1.6);
        double ratioResult = instance.getRatio();
        assertEquals(1.6, ratioResult, 0.00005);

        instance.setRatio(2.9);
        ratioResult = instance.getRatio();
        assertEquals(2.9, ratioResult, 0.00005);

        instance.setRatio(2.9);
        ratioResult = instance.getRatio();
        assertNotEquals(5.2, ratioResult, 0.00005);

        Gear anotherGear = new Gear(instance);
        assertEquals(instance.getGearID(), anotherGear.getGearID());
        assertEquals(instance.getRatio(), anotherGear.getRatio(), 0.05);

    }

    @Test
    public void testToString() {
        System.out.println("toStringGear");
        int id = 1;
        double ratio = 0.95;
        Gear instance = new Gear(id, ratio);
        String expResult = "Gear{m_gearID=1, m_ratio=0.95}";
        String result = instance.toString();
        assertEquals(expResult, result);
        instance = new Gear(0, 0);
        expResult = "Gear{m_gearID=0, m_ratio=0.0}";
        result = instance.toString();
        assertEquals(expResult, result);
    }

}
