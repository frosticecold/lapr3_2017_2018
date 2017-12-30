/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import java.io.File;
import java.util.List;
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
        
    }
    
}
