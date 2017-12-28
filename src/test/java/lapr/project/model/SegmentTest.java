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
public class SegmentTest {

    public SegmentTest() {
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
        Segment instance = new Segment(1, 10, 20, 15, 0, 20, 20, 80);
        Segment otherSegment = new Segment(instance);

        assertEquals(instance.calculateSlope(), otherSegment.calculateSlope(), 0.05);
        assertEquals(instance.getSegmentIndex(), otherSegment.getSegmentIndex());
        assertEquals(instance.getInitialHeight(), otherSegment.getInitialHeight(), 0.05);
        assertEquals(instance.getFinalHeight(), otherSegment.getFinalHeight(), 0.05);
        assertEquals(instance.getLength(), otherSegment.getLength(), 0.05);
        assertEquals(instance.getWindDirection(), otherSegment.getWindDirection(), 0.05);
        assertEquals(instance.getWindSpeed(), otherSegment.getWindSpeed(), 0.05);
        assertEquals(instance.getMinimumVelocity(), otherSegment.getMinimumVelocity(), 0.05);
        assertEquals(instance.getMaximumVelocity(), otherSegment.getMaximumVelocity(), 0.05);

    }

    /**
     * Test of getLength method, of class Segment.
     */
    @Test
    public void testGetSet() {
        System.out.println("getLength");
        Segment instance = new Segment(1, 10, 10, 15, 0, 20, 100, 80);
        double result = instance.getLength();
        assertEquals(15, result, 0.0);
        instance.setLength(12);
        double expResult = 12.0;
        result = instance.getLength();
        assertEquals(expResult, result, 0.0);

        int result2 = instance.getSegmentIndex();
        assertEquals(1, result2);
        instance.setSegmentIndex(3);
        result2 = instance.getSegmentIndex();
        assertEquals(3, result2);

        result = instance.getInitialHeight();
        assertEquals(10, result, 0.0);
        instance.setInitialHeight(12);
        result = instance.getInitialHeight();
        assertEquals(12, result, 0.0);

        result = instance.getFinalHeight();
        assertEquals(10, result, 0.0);
        instance.setFinalHeight(80);
        result = instance.getFinalHeight();
        assertEquals(80, result, 0.0);

        result = instance.getWindDirection();
        assertEquals(0, result, 0.0);
        instance.setWindDirection(15);
        result = instance.getWindDirection();
        assertEquals(15, result, 0.0);

        result = instance.getMaximumVelocity();
        assertEquals(100, result, 0.0);
        instance.setMaximumVelocity(150);
        result = instance.getMaximumVelocity();
        assertEquals(150, result, 0.0);

        result = instance.getMinimumVelocity();
        assertEquals(80, result, 0.0);
        instance.setMinimumVelocity(95);
        result = instance.getMinimumVelocity();
        assertEquals(95, result, 0.0);

        result = instance.getWindSpeed();
        assertEquals(20, result, 0);
        instance.setWindSpeed(10);
        result = instance.getWindSpeed();
        assertEquals(10, result, 0);
    }

    /**
     * Test of calculateSlope method, of class Segment.
     */
    @Test
    public void testCalculateSlope() {
        System.out.println("calculateSlope");
        Segment instance = new Segment(1, 0, 45, 0.090, 0, 20, 100, 80);
        double expResult = 30.0;
        double result = instance.calculateSlope();
        assertEquals(expResult, result, 0.00000000005);
    }

    /**
     * Test of validate method, of class Segment.
     */
    @Test(expected = IllegalArgumentException.class)
    public void IllegalArgumentExceptionTest() {
        System.out.println("validate");
        Segment instance = new Segment();
        instance.setFinalHeight(-1);
        boolean result = instance.validate();
        boolean expResult = false;
        assertEquals(expResult, result);
        instance.setInitialHeight(-2);
        result = instance.validate();
        assertEquals(expResult, result);
        instance.setLength(-5);
        result = instance.validate();
        assertEquals(expResult, result);
        instance.setMaximumVelocity(-45);
        result = instance.validate();
        assertEquals(expResult, result);

        instance.setMinimumVelocity(-98);
        result = instance.validate();
        assertEquals(expResult, result);

        instance.setSegmentIndex(0);
        result = instance.validate();
        assertEquals(expResult, result);
        instance.setSegmentIndex(-8);
        result = instance.validate();
        assertEquals(expResult, result);

        instance.setWindDirection(190);
        result = instance.validate();
        assertEquals(expResult, result);
        instance.setWindDirection(-190);
        result = instance.validate();
        assertEquals(expResult, result);

        instance.setWindSpeed(-8);
        result = instance.validate();
        assertEquals(expResult, result);
    }

    /**
     * Test of reverseSegment method, of class Segment.
     */
    @Test
    public void testReverseSegment() {
        System.out.println("reverseSegment");
        int index = 0;
        Segment instance = new Segment();
        instance.setFinalHeight(12);
        instance.setInitialHeight(0);
        instance.setLength(1);
        instance.setMaximumVelocity(120);
        instance.setMinimumVelocity(100);
        instance.setSegmentIndex(0);
        instance.setWindDirection(45);
        instance.setWindSpeed(10);
        Segment expResult = new Segment(0, 12.0, 0.0, 1.0, -135.0, 10.0, 120.0, 100.0);
        Segment result = instance.reverseSegment(index);
        assertEquals(expResult.toString(), result.toString());
    }

    @Test
    public void testToString() {
        Segment instance = new Segment(0, 12.0, 0.0, 1.0, -135.0, 10.0, 120.0, 100.0);
        String result = instance.toString();
        String expResult = "Segment{m_segment_index=0, m_initial_height=12.0, m_final_height=0.0, m_length=1.0, m_wind_direction=-135.0, m_wind_speed=10.0, m_maximum_velocity=120.0, m_minimum_velocity=100.0}";
        assertEquals(result, expResult);
    }

    @Test
    public void testHashcode() {
        Segment instance = new Segment(0, 12.0, 0.0, 1.0, -135.0, 10.0, 120.0, 100.0);
        assertEquals(-1399408281, instance.hashCode());
    }

}
