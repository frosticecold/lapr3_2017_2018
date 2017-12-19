/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    /**
     * Test of addSection method, of class Road.
     */
    @Test
    public void testAddSection() {
        System.out.println("addSection");
        Section s = new Section();
        Road instance = new Road();
        boolean expResult = true;
        boolean result = instance.addSection(s);
        assertEquals(expResult, result);
        
        expResult = false;
        instance.addSection(s);
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
     * Test of getListOfSections method, of class Road.
     */
    @Test
    public void testGetListOfSections() {
        System.out.println("getListOfSections");
        Road instance = new Road();
        assertTrue(instance.getListOfSections().isEmpty());
        
        Section s1 = new Section();
        s1.setBeginJunction(new Junction("a"));
        s1.setEndJunction(new Junction("b"));
        s1.setDirection(Section.Direction.DIRECT);
        s1.setRoadID("A01");
        Section s2 = new Section();
        s2.setBeginJunction(new Junction("c"));
        s2.setEndJunction(new Junction("d"));
        s2.setDirection(Section.Direction.REVERSE);
        s2.setRoadID("A03");
        
        instance.addSection(s1);
        instance.addSection(s1);
        instance.addSection(s2);
        instance.addSection(s2);
        
        assertTrue(instance.getListOfSections().size() == 2);
        
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
     * Test of setListOfSections method, of class Road.
     */
    @Test
    public void testSetListOfSections() {
        System.out.println("setListOfSections");
        List<Section> m_listOfSections = null;
        Road instance = new Road();
        try {
            instance.setListOfSections(m_listOfSections);
            assertEquals(m_listOfSections, instance.getListOfSections());
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
        
        m_listOfSections = new ArrayList<>();
        Section s1 = new Section();
        Section s2 = new Section();
        Section s3 = new Section();
        Section s4 = new Section();
        Section s5 = new Section();
        m_listOfSections.add(s1);
        m_listOfSections.add(s2);
        m_listOfSections.add(s3);
        m_listOfSections.add(s4);
        m_listOfSections.add(s5);
        
        instance.setListOfSections(m_listOfSections);
        assertEquals(m_listOfSections, instance.getListOfSections());
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
        map.put(2, 2.0);
        map.put(3, 2.0);
        map.put(4, 2.0);
        map.put(5, 2.0);
        instance.setTollFare(map);
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
        
        instance.addTollFare(1, 2.5);
        assertEquals(2.5, instance.getTollValue(1), 0.05);
        
        instance.addTollFare(2, 3.0);
        assertEquals(3.0, instance.getTollValue(2), 0.05);
        
        instance.addTollFare(3, 3.5);
        assertEquals(3.5, instance.getTollValue(3), 0.05);
        
        instance.addTollFare(4, 4.0);
        assertEquals(4.0, instance.getTollValue(4), 0.05);
        
        assertEquals(-1,instance.getTollValue(5),0.05);
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
    
}
