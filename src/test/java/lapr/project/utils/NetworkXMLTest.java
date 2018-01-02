/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import lapr.project.model.Junction;
import lapr.project.model.Road;
import lapr.project.model.Section;
import lapr.project.model.Vehicle;
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
public class NetworkXMLTest {
    
    public NetworkXMLTest() {
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
     * Test of importNetwork method, of class NetworkXML.
     */
    @Test
    public void testImportNetwork() throws Exception {
        System.out.println("importNetwork");
        File file = new File("TestSet02_Network_v2.xml");
        NetworkXML instance = new NetworkXML();
        
        Graph<Junction, Section> graph = new Graph<>(true);
        
        Junction j0 = new Junction("n0");
        Junction j1 = new Junction("n1");
        Junction j2 = new Junction("n2");
        Junction j3 = new Junction("n3");
        Junction j4 = new Junction("n4");
        Junction j5 = new Junction("n5");
        Junction j7 = new Junction("n7");
        
        graph.insertVertex(j0);
        graph.insertVertex(j1);
        graph.insertVertex(j2);
        graph.insertVertex(j3);
        graph.insertVertex(j4);
        graph.insertVertex(j5);
        graph.insertVertex(j7);
        
        int expResult = graph.numVertices();
        int result = instance.importNetwork(file).getFirstElement().numVertices();
        assertEquals(expResult, result);
        
        ArrayList<Road> expResult2 = new ArrayList<>();
        
        Road r1 = new Road("E01", "E01", "regular road");
        Road r2 = new Road("A01", "A1", "toll highway");
        Road r3 = new Road("A02", "A2", "gantry toll highway");
        Road r4 = new Road("E06", "E06", "regular road");
        Road r5 = new Road("N232", "N232", "regular road");
        
        expResult2.add(r1);
        expResult2.add(r2);
        expResult2.add(r3);
        expResult2.add(r4);
        expResult2.add(r5);
        
        List<Road> result2 = instance.importNetwork(file).getSecondElement();
        
        assertEquals(expResult2, result2);
        
        Map<Integer, Double> tollFare = new LinkedHashMap<>();
        
        tollFare.put(1, 0.15);
        tollFare.put(2, 0.25);
        tollFare.put(3, 0.35);
        
        expResult2.get(1).setTollFare(tollFare);
        
//        assertNotEquals(expResult2, result2);
        
        expResult = expResult2.size();
        result = result2.size();
        
        assertEquals(expResult, result);
    }
    
}
