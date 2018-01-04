/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.pathalgorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.model.Junction;
import lapr.project.model.Project;
import lapr.project.model.Road;
import lapr.project.model.Section;
import lapr.project.model.Vehicle;
import lapr.project.networkanalysis.AlgorithmResults;
import lapr.project.utils.ImportException;
import lapr.project.utils.NetworkXML;
import lapr.project.utils.Pair;
import lapr.project.utils.Session;
import lapr.project.utils.VehicleXML;
import lapr.project.utils.graphbase.Graph;
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
public class FastestPathAlgorithmTest {

    Project project;
    private static final String FX_NETWORK = "TestSet02_Network_v2.xml";
    private static final String FX_VEHICLE = "TestSet02_Vehicles_v2.xml";

    public FastestPathAlgorithmTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        try {
            NetworkXML nxml = new NetworkXML();
            Pair<Graph<Junction, Section>, List<Road>> importNetwork = nxml.importNetwork(new File(FX_NETWORK));
            VehicleXML vxml = new VehicleXML();
            List<Vehicle> importVehicles = vxml.importVehicles(new File(FX_VEHICLE));
            project = new Project(importNetwork.getFirstElement(), importVehicles, importNetwork.getSecondElement());
            Session.setActiveProject(project);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FastestPathAlgorithmTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ImportException ex) {
            Logger.getLogger(FastestPathAlgorithmTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of shortestPath method, of class FastestPathAlgorithm.
     */
    @Test
    public void testFastestPath() {
        System.out.println("shortestPath");
        Graph<Junction, Section> graph = project.getRoadNetwork();
        Junction vOrig = project.getJunction("n0");
        Junction vDest = project.getJunction("n1");
        Vehicle vehicle = project.getListVehicles().getVehicleList().get(0);
        PathAlgorithm alg = new FastestPathAlgorithm();
        AlgorithmResults result = alg.bestPath(graph, vOrig, vDest, vehicle, 0);

        assertTrue(result.getVehicle().equals(vehicle));
        assertTrue(result.getJunctionPath().contains(vOrig));
        assertTrue(result.getJunctionPath().contains(vDest));
        assertTrue(result.getSectionPath().get(0).getRoadID().equals("A01"));
        assertEquals(result.getDistance(), 29.2d, 0.5);
        assertEquals(result.getTravelTime(), 955, 1);
        assertEquals(result.getCost(), 7.3, 0.5);
        assertEquals(result.getEnergy(), (2.9 * Math.pow(10, 7)), (0.1 * Math.pow(10, 7)));
        assertEquals(result.getAlgorithmType(), "Fastest Path");

    }

}
