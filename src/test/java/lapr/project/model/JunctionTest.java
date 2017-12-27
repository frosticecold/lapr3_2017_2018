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
public class JunctionTest {

    public JunctionTest() {
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
        System.out.println("Constructor");

        Junction j1 = new Junction("A");
        Junction otherJunction = new Junction(j1);

        assertEquals(j1.getName(), otherJunction.getName());
    }

    /**
     * Test of getName method, of class Junction.
     */
    @Test
    public void testGetID() {
        System.out.println("getID");
        Junction instance = new Junction("New Junction");
        String expResult = "New Junction";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of illegalArgumentException in class Junction.
     */
    @Test(expected = IllegalArgumentException.class)
    public void IllegalArgumentExceptionTest() {
        System.out.println("validateAcceleratorPedal");
        String test = null;
        Junction instance = new Junction(test);
        instance.validate();
    }

    /**
     * Test of illegalArgumentException in class Junction.
     */
    @Test(expected = IllegalArgumentException.class)
    public void IllegalArgumentExceptionTest2() {
        System.out.println("validateAcceleratorPedal");
        Junction instance = new Junction("");
        instance.validate();
    }

    /**
     * Test of hashCode method, of class Junction.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Junction instance = new Junction("code");
        int expResult = 3059446;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        expResult = Integer.MAX_VALUE;
        assertNotEquals(expResult, result);

    }

    /**
     * Test of equals method, of class Junction.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        Junction instance = new Junction("Junction");
        boolean result = instance.equals(obj);
        assertFalse(result);

        assertTrue(instance.equals(instance));
        assertFalse(instance.equals(new Junction("Next")));
        assertFalse(instance.equals(new Gear()));
        assertTrue(instance.equals(new Junction("Junction")));

    }

    /**
     * Test of validate method, of class Junction.
     */
    @Test
    public void testValidate() {
        System.out.println("validateJunctionTest");
        Junction instance = new Junction("Test");
        boolean expResult = true;
        boolean result = instance.validate();
        assertEquals(expResult, result);
        instance = new Junction("Test12");
        result = instance.validate();
        assertEquals(expResult, result);
    }

    @Test
    public void testToStringHTML() {
        System.out.println("toStringHTML");
        Junction instance = new Junction("Test");
        String output = instance.toStringHTML();
        String expected = "\t<li>ID: " + instance.getName() + "</li>\n";
        assertEquals(output, expected);
    }
}
