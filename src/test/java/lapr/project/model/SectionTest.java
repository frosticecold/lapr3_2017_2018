/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
 * @author MarioDias
 */
public class SectionTest {
    
    public SectionTest() {
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
        System.out.println("constructor");
        Section s1 = new Section();
        Junction j1 = new Junction("n0");
        Junction j2 = new Junction("n1");
        s1.setBeginJunction(j1);
        s1.setEndJunction(j2);
        s1.setRoadID("E01");
        s1.setID(1);
        s1.setTypology("Regular Road");
        s1.setDirection(Section.Direction.BIDIRECTIONAL);
        
        Segment seg1 = new Segment(1, 100, 200, 10, 30, 5, 100, 50);
        Segment seg2 = new Segment(1, 100, 200, 10, -30, 5, 100, 50);
        s1.getSequenceOfSegments().add(seg1);
        s1.getSequenceOfSegments().add(seg2);
        
        s1.addToll(1, 2.5);
        s1.addToll(2, 3);
        s1.addToll(3, 3.5);
        s1.addToll(4, 4);
        
        Section s2 = new Section(s1);
        assertEquals(s1.getBeginningJunction(), s2.getBeginningJunction());
        assertEquals(s1.getEndingJunction(), s2.getEndingJunction());
        assertEquals(s1.getRoadID(), s2.getRoadID());
        assertEquals(s1.getID(), s2.getID());
        assertEquals(s1.getTypology(), s2.getTypology());
    }

    /**
     * Test of addToll method, of class Section.
     */
    @Test
    public void testAddToll() {
        System.out.println("addToll");
        int vehic_id = 2;
        double value = 2.3;
        Section instance = new Section();
        boolean result = instance.addToll(vehic_id, value);
        assertTrue(result);
        vehic_id = 1;
        value = 5.3;
        result = instance.addToll(vehic_id, value);
        assertTrue(result);
    }

    /**
     * Test of illegalArgumentException in class Section.
     */
    @Test
    public void IllegalArgumentExceptionTest() {
        int vehic_id = 0;
        double value = 2.3;
        Section instance = new Section();
        boolean result = false;
        try {
            instance.addToll(vehic_id, value);
        } catch (IllegalArgumentException ex) {
        }
        
        try {
            vehic_id = 1;
            value = -5.3;
            result = instance.addToll(vehic_id, value);
        } catch (IllegalArgumentException ex) {
        }
        try {
            vehic_id = -1;
            value = 5.3;
            result = instance.addToll(vehic_id, value);
        } catch (IllegalArgumentException ex) {
        }
        assertFalse(result);
        
        try {
            vehic_id = 0;
            value = 0;
            result = instance.addToll(vehic_id, value);
        } catch (IllegalArgumentException ex) {
        }
        assertFalse(result);
        
        try {
            value = instance.getTollValue(0);
        } catch (IllegalArgumentException ex) {
        }
        try {
            value = instance.getTollValue(-1);
        } catch (IllegalArgumentException ex) {
        }
        try {
            instance.setTypology(null);
        } catch (IllegalArgumentException ex) {
        }
        try {
            instance.setTypology("");
        } catch (IllegalArgumentException ex) {
        }
    }

    /**
     * Test of getToll method, of class Section.
     */
    @Test
    public void testGetToll() {
        System.out.println("getToll");
        int vehic_id = 1;
        double value = 2.3;
        Section instance = new Section();
        Map<Integer, Double> expResult = new HashMap<>();
        Map<Integer, Double> result = instance.getToll();
        assertEquals(expResult, result);
        instance.addToll(vehic_id, value);
        expResult.put(vehic_id, value);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getBeginningJunction method, of class Section.
     */
    @Test
    public void testGetBeginningJunction() {
        System.out.println("getBeginningJunction");
        Section instance = new Section();
        Junction expResult = new Junction("JunctionTest");
        Junction j = new Junction("JunctionTest");
        instance.setBeginJunction(j);
        Junction result = instance.getBeginningJunction();
        assertEquals(expResult, result);
        expResult = new Junction("JunctionTest");
        j = new Junction("JunctionTest12");
        instance.setBeginJunction(j);
        result = instance.getBeginningJunction();
        assertNotEquals(expResult, result);
        
    }

    /**
     * Test of getEndingJunction method, of class Section.
     */
    @Test
    public void testGetEndingJunction() {
        System.out.println("getEndingJunction");
        Section instance = new Section();
        Junction expResult = new Junction("JunctionTest2");
        Junction j = new Junction("JunctionTest2");
        instance.setEndJunction(j);
        Junction result = instance.getEndingJunction();
        assertEquals(expResult, result);
        
        expResult = new Junction("JunctionTest2");
        j = new Junction("JunctionTest2123");
        instance.setEndJunction(j);
        result = instance.getEndingJunction();
        assertNotEquals(expResult, result);
        
    }

    /**
     * Test of getDirection method, of class Section.
     */
    @Test
    public void testGetDirection() {
        System.out.println("getDirection");
        Section instance = new Section();
        Section.Direction result = instance.getDirection();
        assertEquals(Section.Direction.DIRECT, result);
    }

    /**
     * Test of getName method, of class Section.
     */
    @Test
    public void testGetID() {
        System.out.println("getID");
        Section instance = new Section();
        int expResult = 1;
        instance.setID(1);
        int result = instance.getID();
        assertEquals(expResult, result);
        
        expResult = 3;
        instance.setID(3);
        result = instance.getID();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSequenceOfSegments method, of class Section.
     */
    @Test
    public void testGetSequenceOfSegments() {
        System.out.println("getSequenceOfSegments");
        Section instance = new Section();
        Segment s1 = new Segment(1, 10, 10, 15, 0, 20, 100, 80);
        Segment s2 = new Segment(2, 10, 10, 15, 0, 20, 100, 80);
        Segment s3 = new Segment(3, 10, 10, 15, 0, 20, 100, 80);
        Segment s4 = new Segment(4, 10, 10, 15, 0, 20, 100, 80);
        
        List<Segment> expResult = new ArrayList<>();
        expResult.add(s1);
        expResult.add(s2);
        expResult.add(s3);
        expResult.add(s4);
        
        List<Segment> segmentList = new ArrayList<>();
        segmentList.add(s1);
        segmentList.add(s2);
        segmentList.add(s3);
        segmentList.add(s4);
        instance.setSegmentList(segmentList);
        List<Segment> result = instance.getSequenceOfSegments();
        assertEquals(expResult, result);
        
        instance.setSegmentList(null);
        result = instance.getSequenceOfSegments();
        assertNull(result);
    }

    /**
     * Test of getSectionLength method, of class Section.
     */
    @Test
    public void testGetSectionLength() {
        System.out.println("getSectionLength");
        Section instance = new Section();
        Segment s1 = new Segment(1, 10, 10, 15, 0, 20, 100, 80);
        Segment s2 = new Segment(2, 10, 10, 15, 0, 20, 100, 80);
        Segment s3 = new Segment(3, 10, 10, 15, 0, 20, 100, 80);
        Segment s4 = new Segment(4, 10, 10, 15, 0, 20, 100, 80);
        List<Segment> segmentList = new ArrayList<>();
        segmentList.add(s1);
        segmentList.add(s2);
        segmentList.add(s3);
        segmentList.add(s4);
        instance.setSegmentList(segmentList);
        double expResult = 60.0;
        double result = instance.getSectionLength();
        assertEquals(expResult, result, 0.0005);
    }

    /**
     * Test of getRoadID method, of class Section.
     */
    @Test
    public void testGetRoadID() {
        System.out.println("getRoadID");
        Section instance = new Section();
        instance.setRoadID("12");
        String expResult = "12";
        String result = instance.getRoadID();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTypology method, of class Section.
     */
    @Test
    public void testGetTypology() {
        System.out.println("getTypology");
        Section instance = new Section();
        instance.setTypology("Regular road");
        String expResult = "Regular road";
        String result = instance.getTypology();
        assertEquals(expResult, result);
        
        instance.setTypology("Highway");
        expResult = "Highway";
        result = instance.getTypology();
        assertEquals(expResult, result);
        
        instance.setTypology("Normal road");
        expResult = "Highway";
        result = instance.getTypology();
        assertNotEquals(expResult, result);
        
        try {
            instance.setDirection(null);
        } catch (IllegalArgumentException ex) {
        }
    }

    /**
     * Test of getTollValue method, of class Section.
     */
    @Test
    public void testGetTollValue() {
        System.out.println("getTollValue");
        int toll_key = 1;
        Section instance = new Section();
        instance.addToll(1, 1.23);
        instance.addToll(2, 0.95);
        instance.addToll(3, 2.95);
        double expResult = 1.23;
        double result = instance.getTollValue(toll_key);
        assertEquals(expResult, result, 0.0005);
        toll_key = 2;
        instance.addToll(1, 1.23);
        instance.addToll(2, 0.95);
        instance.addToll(3, 2.95);
        expResult = 0.95;
        result = instance.getTollValue(toll_key);
        assertEquals(expResult, result, 0.0005);
        toll_key = 3;
        instance.addToll(1, 1.23);
        instance.addToll(2, 0.95);
        instance.addToll(3, 2.95);
        expResult = 2.95;
        result = instance.getTollValue(toll_key);
        assertEquals(expResult, result, 0.0005);
        
        result = instance.getTollValue(6);
        assertEquals(result, -1, 0.05);
    }

    /**
     * Test of equals method, of class Section.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        
        Section result = new Section();
        Section expResult = new Section();
        assertTrue(expResult.equals(result));
        
        assertTrue(result.equals(result));
        
        Object obj = new Object();
        assertFalse(expResult.equals(obj));
        
        obj = null;
        assertFalse(expResult.equals(obj));
        
        result.setBeginJunction(new Junction("kJunction"));
        expResult.setBeginJunction(new Junction("jJunction"));
        assertFalse(expResult.equals(result));
        result.setBeginJunction(new Junction("ASD"));
        expResult.setBeginJunction(new Junction("CVB"));
        assertFalse(result.equals(expResult));
        
        result.setEndJunction(new Junction("lJunction"));
        expResult.setEndJunction(new Junction("pJunction"));
        assertFalse(expResult.equals(result));
        result.setEndJunction(new Junction("ERT"));
        expResult.setEndJunction(new Junction("FGH"));
        assertFalse(result.equals(expResult));
        
        result.setBeginJunction(expResult.getBeginningJunction());
        result.setEndJunction(expResult.getEndingJunction());
        
        result.setRoadID("road 1");
        expResult.setRoadID("Road 5");
        assertFalse(expResult.equals(result));
        
        result.setRoadID(expResult.getRoadID());
        
        List<Segment> segmentList = new ArrayList<>();
        Segment s1 = new Segment(1, 10, 10, 15, 0, 20, 100, 80);
        Segment s2 = new Segment(2, 10, 10, 15, 0, 20, 100, 80);
        Segment s3 = new Segment(3, 10, 10, 15, 0, 20, 100, 80);
        Segment s4 = new Segment(4, 10, 10, 15, 0, 20, 100, 80);
        segmentList.add(s1);
        segmentList.add(s2);
        segmentList.add(s3);
        segmentList.add(s4);
        result.setSegmentList(segmentList);
        List<Segment> segmentList2 = new ArrayList<>();
        expResult.setSegmentList(segmentList2);
        segmentList2.add(s4);
        expResult.setSegmentList(segmentList2);
        assertFalse(expResult.equals(result));
        
        result.setTypology("Highway");
        expResult.setTypology("Regular");
        assertFalse(expResult.equals(result));
        result.setTypology("Test1");
        expResult.setTypology("Test2");
        assertFalse(result.equals(expResult));
        
        result.setTypology(expResult.getTypology());
        result.setDirection(Section.Direction.DIRECT);
        expResult.setDirection(Section.Direction.DIRECT);
        assertFalse(result.equals(expResult));
        expResult.setSegmentList(segmentList);
        assertEquals(expResult, result);
        
        result.setDirection(Section.Direction.BIDIRECTIONAL);
        assertNotEquals(expResult, result);
        
        result.setDirection(Section.Direction.DIRECT);
        result.setEndJunction(new Junction("Top"));
        assertNotEquals(expResult, result);
    }
    
    @Test
    public void testToString() {
        System.out.println("TestToString");
        Section instance = new Section();
        instance.setBeginJunction(new Junction("Begin junction"));
        instance.setEndJunction(new Junction("End junction"));
        instance.setDirection(Section.Direction.DIRECT);
        instance.setID(1);
        instance.setRoadID("Road 1");
        instance.setTypology("Regular");
        List<Segment> segmentList = new ArrayList<>();
        Segment s1 = new Segment(1, 10, 10, 15, 0, 20, 100, 80);
        Segment s2 = new Segment(2, 10, 10, 15, 0, 20, 100, 80);
        Segment s3 = new Segment(3, 10, 10, 15, 0, 20, 100, 80);
        Segment s4 = new Segment(4, 10, 10, 15, 0, 20, 100, 80);
        segmentList.add(s1);
        segmentList.add(s2);
        segmentList.add(s3);
        segmentList.add(s4);
        instance.setSegmentList(segmentList);
        
        String expResult = "Road 1 Junction Begin junction Junction End junction";
        String result = instance.toString();
        
        assertEquals(expResult, result);
    }
    
    @Test
    public void testValidate() {
        Section instance = new Section();
        try {
            instance.validate();
        } catch (IllegalArgumentException ex) {
        }
        instance.setBeginJunction(new Junction("Begin junction"));
        try {
            instance.validate();
        } catch (IllegalArgumentException ex) {
        }
        instance.setEndJunction(new Junction("End junction"));
        try {
            instance.validate();
        } catch (IllegalArgumentException ex) {
        }
        instance.setDirection(Section.Direction.DIRECT);
        try {
            instance.validate();
        } catch (IllegalArgumentException ex) {
        }
        instance.setID(1);
        try {
            instance.validate();
        } catch (IllegalArgumentException ex) {
        }
        instance.setRoadID("");
        try {
            instance.validate();
        } catch (IllegalArgumentException ex) {
        }
        instance.setRoadID("Road 1");
        try {
            instance.validate();
        } catch (IllegalArgumentException ex) {
        }
        instance.setTypology("");
        try {
            instance.validate();
        } catch (IllegalArgumentException ex) {
        }
        instance.setTypology("Regular");
        try {
            instance.validate();
        } catch (IllegalArgumentException ex) {
        }
        List<Segment> segmentList = new ArrayList<>();
        Segment s1 = new Segment(1, 10, 10, 15, 0, 20, 100, 80);
        Segment s2 = new Segment(2, 10, 10, 15, 0, 20, 100, 80);
        Segment s3 = new Segment(3, 10, 10, 15, 0, 20, 100, 80);
        Segment s4 = new Segment(4, 10, 10, 15, 0, 20, 100, 80);
        segmentList.add(s1);
        segmentList.add(s2);
        segmentList.add(s3);
        segmentList.add(s4);
        instance.setSegmentList(segmentList);
        boolean validate = false;
        try {
            validate = instance.validate();
        } catch (IllegalArgumentException ex) {
        }
        assertTrue(validate);
    }
    
    @Test
    public void testReverseSegment() {
        System.out.println("testReverseSegment");
        Section instance = new Section();
        instance.setBeginJunction(new Junction("Begin junction"));
        instance.setEndJunction(new Junction("End junction"));
        instance.setDirection(Section.Direction.DIRECT);
        instance.setID(1);
        instance.setRoadID("Road 1");
        instance.setTypology("Regular");
        List<Segment> segmentList = new ArrayList<>();
        Segment s1 = new Segment(1, 10, 100, 250, 0, 20, 100, 80);
        Segment s2 = new Segment(2, 10, 10, 15, 0, 20, 100, 80);
        Segment s3 = new Segment(3, 10, 10, 15, 0, 20, 100, 80);
        Segment s4 = new Segment(4, 10, 10, 15, 0, 20, 100, 80);
        segmentList.add(s1);
        segmentList.add(s2);
        segmentList.add(s3);
        segmentList.add(s4);
        instance.setSegmentList(segmentList);
        
        Section reverse = instance.reverseSection();
        assertEquals(s1.getFinalHeight(), reverse.getSequenceOfSegments().get(3).getInitialHeight(), 0.05);
    }
    
    @Test
    public void toStringHTML() {
        Section instance = new Section();
        instance.setBeginJunction(new Junction("Begin junction"));
        instance.setEndJunction(new Junction("End junction"));
        instance.setDirection(Section.Direction.DIRECT);
        instance.setID(1);
        instance.setRoadID("Road 1");
        instance.setTypology("Regular");
        List<Segment> segmentList = new ArrayList<>();
        Segment s1 = new Segment(1, 10, 100, 250, 0, 20, 100, 80);
        Segment s2 = new Segment(2, 10, 10, 15, 0, 20, 100, 80);
        Segment s3 = new Segment(3, 10, 10, 15, 0, 20, 100, 80);
        Segment s4 = new Segment(4, 10, 10, 15, 0, 20, 100, 80);
        segmentList.add(s1);
        segmentList.add(s2);
        segmentList.add(s3);
        segmentList.add(s4);
        instance.setSegmentList(segmentList);
        
        String tohtml = instance.toStringHTML();
        assertNotNull(tohtml);
        
    }
    
}
