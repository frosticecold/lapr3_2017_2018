///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package lapr.project.model;
//
//import java.util.LinkedList;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import static org.junit.Assert.*;
//
///**
// *
// * @author Miguel Santos <1161386@isep.ipp.pt>
// */
//public class AlgorithmResultsTest {
//    
//    public AlgorithmResultsTest() {
//    }
//    
//    @BeforeClass
//    public static void setUpClass() {
//    }
//    
//    @AfterClass
//    public static void tearDownClass() {
//    }
//    
//    @Before
//    public void setUp() {
//    }
//    
//    @After
//    public void tearDown() {
//    }
//
//    /**
//     * Test of calculate method, of class AlgorithmResults.
//     */
//    @Test
//    public void testCalculate() {
//        System.out.println("calculate");
//        AlgorithmResults instance = null;
//        instance.calculate();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setCost method, of class AlgorithmResults.
//     */
//    @Test
//    public void testSetCost() {
//        System.out.println("setCost");
//        double cost = 0.0;
//        AlgorithmResults instance = null;
//        instance.setCost(cost);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setDistance method, of class AlgorithmResults.
//     */
//    @Test
//    public void testSetDistance() {
//        System.out.println("setDistance");
//        double distance = 0.0;
//        AlgorithmResults instance = null;
//        instance.setDistance(distance);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getProject method, of class AlgorithmResults.
//     */
//    @Test
//    public void testGetProject() {
//        System.out.println("getProject");
//        AlgorithmResults instance = null;
//        Project expResult = null;
//        Project result = instance.getProject();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getEnergy method, of class AlgorithmResults.
//     */
//    @Test
//    public void testGetEnergy() {
//        System.out.println("getEnergy");
//        AlgorithmResults instance = null;
//        double expResult = 0.0;
//        double result = instance.getEnergy();
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getTravelTime method, of class AlgorithmResults.
//     */
//    @Test
//    public void testGetTravelTime() {
//        System.out.println("getTravelTime");
//        AlgorithmResults instance = null;
//        double expResult = 0.0;
//        double result = instance.getTravelTime();
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getCost method, of class AlgorithmResults.
//     */
//    @Test
//    public void testGetCost() {
//        System.out.println("getCost");
//        AlgorithmResults instance = null;
//        double expResult = 0.0;
//        double result = instance.getCost();
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getDistance method, of class AlgorithmResults.
//     */
//    @Test
//    public void testGetDistance() {
//        System.out.println("getDistance");
//        AlgorithmResults instance = null;
//        double expResult = 0.0;
//        double result = instance.getDistance();
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getSectionPath method, of class AlgorithmResults.
//     */
//    @Test
//    public void testGetSectionPath() {
//        System.out.println("getSectionPath");
//        AlgorithmResults instance = null;
//        LinkedList<Section> expResult = null;
//        LinkedList<Section> result = instance.getSectionPath();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getJunctionPath method, of class AlgorithmResults.
//     */
//    @Test
//    public void testGetJunctionPath() {
//        System.out.println("getJunctionPath");
//        AlgorithmResults instance = null;
//        LinkedList<Junction> expResult = null;
//        LinkedList<Junction> result = instance.getJunctionPath();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getVehicle method, of class AlgorithmResults.
//     */
//    @Test
//    public void testGetVehicle() {
//        System.out.println("getVehicle");
//        AlgorithmResults instance = null;
//        Vehicle expResult = null;
//        Vehicle result = instance.getVehicle();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getVehicleLoad method, of class AlgorithmResults.
//     */
//    @Test
//    public void testGetVehicleLoad() {
//        System.out.println("getVehicleLoad");
//        AlgorithmResults instance = null;
//        double expResult = 0.0;
//        double result = instance.getVehicleLoad();
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getAlgorithmType method, of class AlgorithmResults.
//     */
//    @Test
//    public void testGetAlgorithmType() {
//        System.out.println("getAlgorithmType");
//        AlgorithmResults instance = null;
//        String expResult = "";
//        String result = instance.getAlgorithmType();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of hashCode method, of class AlgorithmResults.
//     */
//    @Test
//    public void testHashCode() {
//        System.out.println("hashCode");
//        AlgorithmResults instance = null;
//        int expResult = 0;
//        int result = instance.hashCode();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of equals method, of class AlgorithmResults.
//     */
//    @Test
//    public void testEquals() {
//        System.out.println("equals");
//        Object obj = null;
//        AlgorithmResults instance = null;
//        boolean expResult = false;
//        boolean result = instance.equals(obj);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of toString method, of class AlgorithmResults.
//     */
//    @Test
//    public void testToString() {
//        System.out.println("toString");
//        AlgorithmResults instance = null;
//        String expResult = "";
//        String result = instance.toString();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of toStringHTML method, of class AlgorithmResults.
//     */
//    @Test
//    public void testToStringHTML() {
//        System.out.println("toStringHTML");
//        AlgorithmResults instance = null;
//        String expResult = "";
//        String result = instance.toStringHTML();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    
//}
