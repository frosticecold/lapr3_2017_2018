/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

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
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public class RoadTest {

    private static final String DO_NOT_CHANGE_ME = "do not change me";

    public RoadTest() {
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

        String id = "E01";
        String name = "E01";
        String typology = "Super Road";

        Road road1 = new Road(id, name, typology);

        assertEquals(road1.getRoadID(), id);
        assertEquals(road1.getName(), name);
        assertEquals(road1.getTypology(), typology);

        Road anotherRoad = new Road(road1);

        assertEquals(anotherRoad.getRoadID(), id);
        assertEquals(anotherRoad.getName(), name);
        assertEquals(anotherRoad.getTypology(), typology);

    }

    /**
     * Test of getRoadID method, of class Road.
     */
    @Test
    public void testGetSetRoadID() {
        System.out.println("getSetRoadID");
        Road instance = new Road();
        instance.setRoadID("do not change me");
        String expResult = "do not change me";
        String result = instance.getRoadID();
        assertEquals(expResult, result);

        try {
            instance.setRoadID("");
            result = instance.getRoadID();
            assertEquals("", result);
        } catch (IllegalArgumentException ex) {
            System.out.println("Invalid roadid");
        }
        try {
            instance.setRoadID(null);
            result = instance.getRoadID();
            assertEquals(null, result);
        } catch (IllegalArgumentException ex) {
            System.out.println("Invalid roadid");
        }
    }

    /**
     * Test of getName method, of class Road.
     */
    @Test
    public void testGetSetName() {
        System.out.println("getSetName");
        Road instance = new Road();
        instance.setName(DO_NOT_CHANGE_ME);
        String expResult = DO_NOT_CHANGE_ME;
        String result = instance.getName();
        assertEquals(expResult, result);

        try {
            instance.setName("");
            result = instance.getName();
            assertEquals("", result);
        } catch (IllegalArgumentException ex) {
            System.out.println("Invalid name");
        }
        try {
            instance.setName(null);
            result = instance.getName();
            assertEquals(null, result);
        } catch (IllegalArgumentException ex) {
            System.out.println("Invalid name");
        }
    }

    /**
     * Test of getTypology method, of class Road.
     */
    @Test
    public void testGetSetTypology() {
        System.out.println("getTypology");
        Road instance = new Road();
        instance.setTypology(DO_NOT_CHANGE_ME);
        String expResult = "do not change me";
        String result = instance.getTypology();
        assertEquals(expResult, result);

        try {
            instance.setTypology("");
            result = instance.getTypology();
            assertEquals("", result);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
        try {
            instance.setTypology(null);
            result = instance.getTypology();
            assertEquals(null, result);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Test of setTollFare method, of class Road.
     */
    @Test
    public void testSetTollFare() {
        System.out.println("setTollFare");
        Map<Integer, Double> map = null;
        Road instance = new Road();
        try {
            instance.setTollFare(map);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }

        map = new HashMap<>();
        map.put(1, 2.0);
        map.put(2, 2.5);
        map.put(3, 3.0);
        map.put(4, 3.5);
        map.put(5, 4.0);
        instance.setTollFare(map);

        try {
            map.get(0);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }

        Road anotherRoad = new Road(instance);

        try {

            assertEquals(anotherRoad.getTollValue(1), 2.0, 0.05);
            assertEquals(anotherRoad.getTollValue(2), 2.5, 0.05);
            assertEquals(anotherRoad.getTollValue(3), 3.0, 0.05);
            assertEquals(anotherRoad.getTollValue(4), 3.5, 0.05);
            assertEquals(anotherRoad.getTollValue(5), 4.0, 0.05);

        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
        try {

            assertEquals(anotherRoad.getTollValue(-1), 2.0, 0.05);

        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }

    }

    /**
     * Test of addTollFare method, of class Road.
     */
    @Test
    public void testAddTollFare() {
        System.out.println("addTollFare");
        int vehicle_id = 0;
        double toll_value = 0.0;
        Road instance = new Road();
        try {
            instance.addTollFare(vehicle_id, toll_value);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }

        vehicle_id = 1;

        try {
            instance.addTollFare(vehicle_id, toll_value);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }

        vehicle_id = 0;

        toll_value = 1.0;
        try {
            instance.addTollFare(vehicle_id, toll_value);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }

        toll_value = -1;

        try {
            instance.addTollFare(vehicle_id, toll_value);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }

        try {
            instance.getTollValue(0);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
        try {
            instance.getTollValue(-1);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }

        try {
            instance.addTollFare(-1, -1);
        } catch (IllegalArgumentException ex) {
        }

        instance.addTollFare(1, 2.5);
        assertEquals(2.5, instance.getTollValue(1), 0.05);

        instance.addTollFare(2, 3.0);
        assertEquals(3.0, instance.getTollValue(2), 0.05);

        instance.addTollFare(3, 3.5);
        assertEquals(3.5, instance.getTollValue(3), 0.05);

        instance.addTollFare(4, 4.0);
        assertEquals(4.0, instance.getTollValue(4), 0.05);

        assertEquals(-1, instance.getTollValue(5), 0.05);
    }

    /**
     * Test of validate method, of class Road.
     */
    @Test
    public void testValidate() {
        System.out.println("validate");
        Road road = new Road();
        boolean expResult = false;
        boolean result;
        try {
            result = road.validate();
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }

        road.setName(DO_NOT_CHANGE_ME);
        try {
            result = road.validate();
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
        road.setRoadID(DO_NOT_CHANGE_ME);
        try {
            result = road.validate();
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
        road.setTypology(DO_NOT_CHANGE_ME);
        try {
            result = road.validate();
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
        result = road.validate();

        expResult = true;
        assertEquals(expResult, result);
    }

    @Test
    public void testHashCode() {
        Road road = new Road(DO_NOT_CHANGE_ME, DO_NOT_CHANGE_ME, DO_NOT_CHANGE_ME);
        System.out.println(road.hashCode());
        int expResult = 1525428821;
        assertEquals(road.hashCode(), expResult);

    }

    @Test
    public void testEquals() {
        Road road = new Road(DO_NOT_CHANGE_ME, DO_NOT_CHANGE_ME, DO_NOT_CHANGE_ME);
        assertTrue(road.equals(road));

        assertFalse(road.equals(null));

        assertFalse(road.equals(new Object()));

        Road otherRoad = new Road("A", "B", "C");

        assertFalse(road.equals(otherRoad));

        otherRoad.setRoadID(DO_NOT_CHANGE_ME);

        assertFalse(road.equals(otherRoad));

        otherRoad.setName(DO_NOT_CHANGE_ME);

        assertFalse(road.equals(otherRoad));

        otherRoad.setName("ASD");
        otherRoad.setTypology(DO_NOT_CHANGE_ME);
        assertFalse(road.equals(otherRoad));

        otherRoad.setName(DO_NOT_CHANGE_ME);
        assertTrue(road.equals(otherRoad));
    }

    @Test
    public void testToStringHTML() {
        System.out.println("toStringHTML");
        Road road = new Road(DO_NOT_CHANGE_ME, DO_NOT_CHANGE_ME, DO_NOT_CHANGE_ME);
        String tohtml = road.toStringHTML();
        String expected = "\t<li>Road id: " + road.getRoadID() + "</li>\n"
                + "\t<li>Name: " + road.getName() + "</li>\n"
                + "\t<li>Typology: " + road.getTypology() + "</li>\n";
        assertEquals(expected, tohtml);

    }

}
