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
    @Test(expected = IllegalArgumentException.class)
    public void IllegalArgumentExceptionTest() {
        int vehic_id = 0;
        double value = 2.3;
        Section instance = new Section();
        boolean result = instance.addToll(vehic_id, value);
        assertFalse(result);
        vehic_id = 1;
        value = -5.3;
        result = instance.addToll(vehic_id, value);
        assertFalse(result);
        vehic_id = -1;
        value = 5.3;
        result = instance.addToll(vehic_id, value);
        assertFalse(result);

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
     * Test of getID method, of class Section.
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
    }

    /**
     * Test of getKey method, of class Section.
     */
    @Test
    public void testGetKey() {
        System.out.println("getKey");
        Section instance = new Section();
        instance.setKey(1);
        int expResult = 1;
        int result = instance.getKey();
        assertEquals(expResult, result);

        instance.setKey(2);
        expResult = 2;
        result = instance.getKey();
        assertEquals(expResult, result);

        instance.setKey(1);
        expResult = 2;
        result = instance.getKey();
        assertNotEquals(expResult, result);

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

        Object obj = new Object();
        assertFalse(expResult.equals(obj));

        obj = null;
        assertFalse(expResult.equals(obj));

        result.setBeginJunction(new Junction("kJunction"));
        expResult.setBeginJunction(new Junction("jJunction"));
        assertFalse(expResult.equals(result));

        result.setEndJunction(new Junction("lJunction"));
        expResult.setEndJunction(new Junction("pJunction"));
        assertFalse(expResult.equals(result));

        result.setKey(1);
        expResult.setKey(2);
        assertFalse(expResult.equals(result));

        result.setRoadID("road 1");
        expResult.setRoadID("Road 5");
        assertFalse(expResult.equals(result));

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
    }
    
    @Test
    public void testToString() {
        
        Section instance = new Section();
        instance.setBeginJunction(new Junction("Begin junction"));
        instance.setEndJunction(new Junction("End junction"));
        instance.setDirection(Section.Direction.DIRECT);
        instance.setID(1);
        instance.setKey(1);
        instance.setRoadID("Road 1");
        instance.setTypology("Regular");
        List <Segment> segmentList = new ArrayList<>();
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
        
        assertEquals(expResult , result);
    }
    
}
