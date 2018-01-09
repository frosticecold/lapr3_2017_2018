/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
public class VehicleListTest {

    private Vehicle vh1;
    private Vehicle vh2;
    private Vehicle vh3;

    public VehicleListTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        vh1 = new VehicleCombustion(1000, 5000, 4, new Gearbox(), new Accelerator(), "Pickup", "Super duper pickup", "Car", Vehicle.FUEL_GASOLINE, 1, "Combustion", 2400, 1200, 0.39, 0.8, 2.4, 0.015, new HashMap<>(), new HashMap<>());
        vh2 = new VehicleCombustion((VehicleCombustion) vh1);
        vh2.setMaxRPM(6000);
        vh3 = new VehicleCombustion((VehicleCombustion) vh1);
        vh3.setName("OtherPickup");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testConstructor() {

        VehicleList list = new VehicleList();
        vh2.setWheelSize(0.75);
        list.addVehicle(vh1);
        list.addVehicle(vh2);
        list.addVehicle(vh3);

        VehicleList otherList = new VehicleList(list);
        assertEquals(list.getVehicleList().get(0), otherList.getVehicleList().get(0));
        assertEquals(list.getVehicleList().get(1), otherList.getVehicleList().get(1));
        assertEquals(list.getVehicleList().get(2), otherList.getVehicleList().get(2));

    }

    /**
     * Test of size method, of class VehicleList.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        VehicleList instance = new VehicleList();
        int expResult = 2;
        instance.addVehicle(vh1);
        vh2.setWheelSize(0.75);
        instance.addVehicle(vh2);
        int result = instance.size();
        assertEquals(expResult, result);
    }

    /**
     * Test of addVehicle method, of class VehicleList.
     */
    @Test
    public void testAddVehicle() {
        System.out.println("addVehicle");
        VehicleList instance = new VehicleList();

        boolean expResult = true;
        boolean result = instance.addVehicle(vh1);
        assertEquals(expResult, result);

        expResult = false;
        result = instance.addVehicle(vh1);
        assertEquals(expResult, result);

        vh2.setWheelSize(0.75);
        expResult = true;
        result = instance.addVehicle(vh2);
        assertEquals(expResult, result);

        assertEquals(vh2.getName(), vh1.getName() + 2);

    }

    /**
     * Test of removeVehicle method, of class VehicleList.
     */
    @Test
    public void testRemoveVehicle() {
        System.out.println("removeVehicle");
        VehicleList instance = new VehicleList();
        instance.addVehicle(vh1);
        boolean expResult = true;
        boolean result = instance.removeVehicle(vh1);
        assertEquals(expResult, result);

        expResult = false;
        result = instance.removeVehicle(vh1);
        assertEquals(expResult, result);
    }

    /**
     * Test of iterator method, of class VehicleList.
     */
    @Test
    public void testIterator() {
        System.out.println("iterator");
        VehicleList instance = new VehicleList();
        instance.addVehicle(vh1);
        instance.addVehicle(vh3);
        Iterator<Vehicle> expResult = instance.getVehicleList().iterator();
        assertEquals(expResult.next(), vh1);
        assertEquals(expResult.next(), vh3);
        assertFalse(expResult.hasNext());

    }

    /**
     * Test of getVehicleNameList method, of class VehicleList.
     */
    @Test
    public void testGetVehicleNameList() {
        System.out.println("getVehicleNameList");
        VehicleList instance = new VehicleList();
        instance.addVehicle(vh1);
        vh2.setWheelSize(0.75);
        instance.addVehicle(vh2);
        instance.addVehicle(vh3);
        List<String> expResult = new ArrayList<>();
        expResult.add("Pickup");
        expResult.add("Pickup2");
        expResult.add("OtherPickup");
        List<String> result = instance.getVehicleNameList();
        assertEquals(expResult, result);
    }

    /**
     * Test of getVehicleList method, of class VehicleList.
     */
    @Test
    public void testGetVehicleList() {
        System.out.println("getVehicleList");
        VehicleList instance = new VehicleList();
        instance.addVehicle(vh1);
        instance.addVehicle(vh3);
        List<Vehicle> expResult = new ArrayList<Vehicle>();
        expResult.add(vh1);
        expResult.add(vh3);
        List<Vehicle> result = instance.getVehicleList();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class VehicleList.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        VehicleList instance = new VehicleList();
        String expResult = "List of Vehicles:\n";
        String result = instance.toString();
        assertEquals(expResult, result);

        instance.addVehicle(vh1);
        expResult += "Pickup";
        result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of validateVehicleList method, of class VehicleList.
     */
    @Test
    public void testValidateVehicleList() {
        System.out.println("validateVehicleList");
        VehicleList instance = new VehicleList();
        boolean expResult = true;
        boolean result = false;
        try {
            instance.validateVehicleList();
        } catch (IllegalArgumentException ex) {
        }

        instance.addVehicle(vh1);
        result = instance.validateVehicleList();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetVehicleByName(){
         System.out.println("getVehicleByName");
         String name="Pickup";
         VehicleList instance = new VehicleList();
         instance.addVehicle(vh1);
         instance.addVehicle(vh2);
         Vehicle expResult=vh1;
         Vehicle result;
        result = instance.getVehicleByName(name);
        assertEquals(expResult,result);
    }

}
