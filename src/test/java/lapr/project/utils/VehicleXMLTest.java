/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import lapr.project.model.Accelerator;
import lapr.project.model.Gearbox;
import lapr.project.model.Junction;
import lapr.project.model.Road;
import lapr.project.model.Section;
import lapr.project.model.Vehicle;
import lapr.project.model.VehicleCombustion;
import lapr.project.model.VehicleElectric;
import lapr.project.utils.graphbase.Graph;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Miguel Santos <1161386@isep.ipp.pt>
 */
public class VehicleXMLTest {
    
    public VehicleXMLTest() {
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
     * Test of importVehicles method, of class VehicleXML.
     */
    @Test
    public void testImportVehicles() throws Exception {
        System.out.println("importVehicles");
        File file = new File("TestSet02_Vehicles_v2.xml");
        VehicleXML instance = new VehicleXML();
        List<Vehicle> vehicleList = new LinkedList<>();
        
        Vehicle v1 = new VehicleCombustion(900, 5500, 4, null, null, "Pick_up", "Pick-up test vehicle", "car", "diesel", 2, "combustion", 2400, 1200, 0.39, 0.8, 2.4, 0.015, null, null);
        Vehicle v2 = new VehicleCombustion(1000, 6500, 3.6, null, null, "Car", "Dummy test vehicle 01", "car", "gasoline", 1, "combustion", 1600, 420, 0.32, 0.6, 1.9, 0.013, null, null);
      //Vehicle v3 = new VehicleElectric(0, 12500, 10.6, null, null, "ElectricDummy", "Electric Dummy teste vehicle", "car", "electric", 1, "electric", 1400, 420, 0.31, 0.6, 1.8, 0.01, null, null);
        Vehicle v3 = new VehicleElectric();
        
        vehicleList.add(v1);
        vehicleList.add(v2);
        vehicleList.add(v3);
        
        int expResult = vehicleList.size();
        
        List<Vehicle> importedList = instance.importVehicles(file);
        int result = importedList.size();
        assertEquals(expResult, result);
    }

//    /**
//     * Test of getVehiclesList method, of class VehicleXML.
//     */
//    @Test
//    public void testGetVehiclesList() {
//        System.out.println("getVehiclesList");
//        VehicleXML instance = new VehicleXML();
//        List<Vehicle> expResult = null;
//        List<Vehicle> result = instance.getVehiclesList();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of importNetwork method, of class VehicleXML.
//     */
//    @Test
//    public void testImportNetwork() throws Exception {
//        System.out.println("importNetwork");
//        File file = null;
//        VehicleXML instance = new VehicleXML();
//        Pair<Graph<Junction, Section>, List<Road>> expResult = null;
//        Pair<Graph<Junction, Section>, List<Road>> result = instance.importNetwork(file);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
