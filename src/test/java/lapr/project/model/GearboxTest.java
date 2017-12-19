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
public class GearboxTest {

    public GearboxTest() {
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
     * Test of addGear method, of class Gearbox.
     */
    @Test
    public void testAddGear() {
        System.out.println("addGear");
        Gear g1 = new Gear(1, 2.3);
        Gear g2 = new Gear(2, 3.3);
        Gear g3 = new Gear(3, 1.7);
        Gear g4 = new Gear(4, 0.65);
        Gear g5 = new Gear(5, 0.4);
        Gearbox instance = new Gearbox();
        assertTrue(instance.getGearList().size() == 0);
        instance.addGear(g1);
        assertTrue(instance.getGearList().size() == 1);
        instance.addGear(g2);
        assertTrue(instance.getGearList().size() == 2);
        instance.addGear(g3);
        assertTrue(instance.getGearList().size() == 3);
        instance.addGear(g4);
        assertTrue(instance.getGearList().size() == 4);
        instance.addGear(g5);
        assertTrue(instance.getGearList().size() == 5);

    }

    /**
     * Test of getGear method, of class Gearbox.
     */
    @Test
    public void testGetGear() {
        System.out.println("getGear");
        Gear g1 = new Gear(1, 2.3);
        Gear g2 = new Gear(2, 3.3);
        Gear g3 = new Gear(3, 1.7);
        Gear g4 = new Gear(4, 0.65);
        Gear g5 = new Gear(5, 0.4);
        Gearbox instance = new Gearbox();
        instance.addGear(g1);
        instance.addGear(g2);
        instance.addGear(g3);
        instance.addGear(g4);
        instance.addGear(g5);
        for (int i = 1; i <= instance.getGearList().size(); i++) {
            assertTrue(instance.getGear(i).getGearID() == i);
        }
        assertTrue(instance.getGear(1) == g1);
        assertTrue(instance.getGear(2) == g2);
        assertTrue(instance.getGear(3) == g3);
        assertTrue(instance.getGear(4) == g4);
        assertTrue(instance.getGear(5) == g5);
        assertNull(instance.getGear(0));
        assertNull(instance.getGear(7));
    }

    /**
     * Test of getGearList method, of class Gearbox.
     */
    @Test
    public void testGetGearList() {
        System.out.println("getGearList");
        Gear g1 = new Gear(1, 2.3);
        Gear g2 = new Gear(2, 3.3);
        Gear g3 = new Gear(3, 1.7);
        Gear g4 = new Gear(4, 0.65);
        Gear g5 = new Gear(5, 0.4);
        Gearbox instance = new Gearbox();
        List<Gear> expResult = new ArrayList<>();
        List<Gear> result = instance.getGearList();
        assertEquals(expResult, result);
        assertEquals(expResult.size(), result.size());
        
        instance.addGear(g1);
        instance.addGear(g2);
        instance.addGear(g3);
        instance.addGear(g4);
        instance.addGear(g5);
        expResult.add(g1);
        expResult.add(g2);
        expResult.add(g3);
        expResult.add(g4);
        expResult.add(g5);
        result = instance.getGearList();
        assertEquals(expResult, result);
        assertEquals(expResult.size(), result.size());
        
    }

    /**
     * Test of toString method, of class Gearbox.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Gear g1 = new Gear(1, 2.3);
        Gear g2 = new Gear(2, 3.3);
        Gear g3 = new Gear(3, 1.7);
        Gear g4 = new Gear(4, 0.65);
        Gear g5 = new Gear(5, 0.4);
        Gearbox instance = new Gearbox();
        instance.addGear(g1);
        instance.addGear(g2);
        instance.addGear(g3);
        instance.addGear(g4);
        instance.addGear(g5);
        
        String expResult = "Gearbox{m_gears=[Gear{m_gearID=1, m_ratio=2.3}, Gear{m_gearID=2, m_ratio=3.3}, Gear{m_gearID=3, m_ratio=1.7}, Gear{m_gearID=4, m_ratio=0.65}, Gear{m_gearID=5, m_ratio=0.4}]}";
        String result = instance.toString();
        assertEquals(expResult, result);
        
        
        expResult = "Gearbox{m_gears=[Gear{m_gearID=32, m_ratio=2.7}, Gear{m_gearID=2, m_ratio=3.3}, Gear{m_gearID=3, m_ratio=1.7}, Gear{m_gearID=4, m_ratio=0.65}, Gear{m_gearID=5, m_ratio=0.4}]}";
        result = instance.toString();
        assertNotEquals(expResult, result);
    }

}
