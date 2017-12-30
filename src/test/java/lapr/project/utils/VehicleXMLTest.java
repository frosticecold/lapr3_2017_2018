/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import java.io.File;
import java.io.FileNotFoundException;
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
        v3.setWheelSize(0.6);
        
        vehicleList.add(v1);
        vehicleList.add(v2);
        vehicleList.add(v3);

        int expResult = vehicleList.size();

        List<Vehicle> importedList = instance.importVehicles(file);
        int result = importedList.size();
        assertEquals(expResult, result);

        String expResult2 = v1.getName();
        String result2 = importedList.get(0).getName();
        assertEquals(expResult2, result2);

        expResult2 = v1.getDescription();
        result2 = importedList.get(0).getDescription();
        assertEquals(expResult2, result2);
        
        expResult2 = v2.getType();
        result2 = importedList.get(1).getType();
        assertEquals(expResult2, result2);
        
        expResult2 = v3.getMotorization();
        result2 = importedList.get(2).getMotorization();
        assertEquals(expResult2, result2);
        
        double expResult3 = v1.getFrontalArea();
        double result3 = importedList.get(0).getFrontalArea();
        assertEquals(expResult3, result3, 0.0);
        
        expResult3 = v2.getDragCoefficient();
        result3 = importedList.get(1).getDragCoefficient();
        assertEquals(expResult3, result3, 0.0);
        
        expResult3 = v3.getWheelSize();
        result3 = importedList.get(2).getWheelSize();
        assertEquals(expResult3, result3, 0.0);
    }

    /**
     * Test of getVehiclesList method, of class VehicleXML.
     */
    @Test
    public void testGetVehiclesList() throws FileNotFoundException, ImportException {
        System.out.println("getVehiclesList");
        File file = new File("TestSet02_Vehicles_v2.xml");
        VehicleXML instance = new VehicleXML();
        instance.importVehicles(file);
        List<Vehicle> vehicleList = new LinkedList<>();
        
        Vehicle v1 = new VehicleCombustion(900, 5500, 4, null, null, "Pick_up", "Pick-up test vehicle", "car", "diesel", 2, "combustion", 2400, 1200, 0.39, 0.8, 2.4, 0.015, null, null);
        Vehicle v2 = new VehicleCombustion(1000, 6500, 3.6, null, null, "Car", "Dummy test vehicle 01", "car", "gasoline", 1, "combustion", 1600, 420, 0.32, 0.6, 1.9, 0.013, null, null);
        Vehicle v3 = new VehicleElectric();
        
        vehicleList.add(v1);
        vehicleList.add(v2);
        vehicleList.add(v3);
        
        int expResult = vehicleList.size();
        int result = instance.getVehiclesList().size();
        assertEquals(expResult, result);
        
    }
}
