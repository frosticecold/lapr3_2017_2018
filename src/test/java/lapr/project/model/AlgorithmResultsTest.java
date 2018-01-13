/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;
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
public class AlgorithmResultsTest {

    Project project;
    AlgorithmResults instance;
    LinkedList<Junction> junctionsList;
    LinkedList<Section> fastestPath;
    Vehicle vehicle;

    public AlgorithmResultsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

        project = new Project("TestProject", "Best Test Ever!");
        junctionsList = new LinkedList<>();
        Junction j1 = new Junction("j1");
        Junction j2 = new Junction("j2");
        Junction j3 = new Junction("j3");
        Junction j4 = new Junction("j4");
        Junction j5 = new Junction("j5");
        Junction j6 = new Junction("j6");

        Graph<Junction, Section> roadNetwork = new Graph<>(true);
        project.setRoadNetwork(roadNetwork);

        junctionsList.add(j1);
        junctionsList.add(j2);
        junctionsList.add(j3);
        junctionsList.add(j4);
        junctionsList.add(j5);
        project.addJunction(j1);
        project.addJunction(j2);
        project.addJunction(j3);
        project.addJunction(j4);
        project.addJunction(j5);
        project.addJunction(j6);

        fastestPath = new LinkedList<>();
        Section s1 = new Section();
        Section s2 = new Section();
        Section s3 = new Section();
        s1.setRoadID("1");
        s2.setRoadID("1");
        s3.setRoadID("2");
        s1.setBeginJunction(j1);
        s1.setEndJunction(j3);
        s2.setBeginJunction(j2);
        s2.setEndJunction(j3);
        s3.setBeginJunction(j4);
        s3.setEndJunction(j5);

        Road r1 = new Road("1", "E01", "toll highway");
        r1.addTollFare(1, 5);
        r1.addTollFare(2, 5);
        Road r2 = new Road("2", "E02", "gantry toll highway");
        r2.addTollFare(1, 4);
        r2.addTollFare(2, 4);

        fastestPath.add(s1);
        fastestPath.add(s2);
        fastestPath.add(s3);

        project.addRoad(r1);
        project.addRoad(r2);

        project.addSection(s1);
        project.addSection(s2);
        project.addSection(s3);

        vehicle = new VehicleCombustion();
        vehicle.setName("Pick_up");
        vehicle.setType("car");
        vehicle.setVehicleClass(2);
        vehicle.setMaxLoad(2000);

        project.addVehicle(vehicle);

        double[] results = {800, 2000000};

        instance = new AlgorithmResults(project, junctionsList, fastestPath, vehicle, results, "Fastest Path");

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of calculate method, of class AlgorithmResults.
     */
    @Test
    public void testCalculate() {
        System.out.println("calculate");
        instance.calculate();
        double expResult = 14.0;
        double result = instance.getCost();
        assertNotEquals(expResult, result, 0.0);

    }

    /**
     * Test of setCost method, of class AlgorithmResults.
     */
    @Test
    public void testSetCost() {
        System.out.println("setCost");
        double cost = 20.0;
        instance.setCost(cost);
        double expResult = 20.0;
        double result = instance.getCost();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setDistance method, of class AlgorithmResults.
     */
    @Test
    public void testSetDistance() {
        System.out.println("setDistance");
        double distance = 50.0;
        instance.setDistance(distance);
        double expResult = 50.0;
        double result = instance.getDistance();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getProject method, of class AlgorithmResults.
     */
    @Test
    public void testGetProject() {
        System.out.println("getProject");
        Project expResult = new Project("TestProject", "Best Test Ever!");
        Graph<Junction, Section> roadNetwork = new Graph<>(true);
        expResult.setRoadNetwork(roadNetwork);

        Junction j1 = new Junction("j1");
        Junction j2 = new Junction("j2");
        Junction j3 = new Junction("j3");
        Junction j4 = new Junction("j4");
        Junction j5 = new Junction("j5");
        Junction j6 = new Junction("j6");

        Section s1 = new Section();
        Section s2 = new Section();
        Section s3 = new Section();
        s1.setRoadID("1");
        s2.setRoadID("1");
        s3.setRoadID("2");
        s1.setBeginJunction(j1);
        s1.setEndJunction(j3);
        s2.setBeginJunction(j2);
        s2.setEndJunction(j3);
        s3.setBeginJunction(j4);
        s3.setEndJunction(j5);

        expResult.addJunction(j1);
        expResult.addJunction(j2);
        expResult.addJunction(j3);
        expResult.addJunction(j4);
        expResult.addJunction(j5);
        expResult.addJunction(j6);

        Road r1 = new Road("1", "E01", "toll highway");
        r1.addTollFare(1, 5);
        r1.addTollFare(2, 5);
        Road r2 = new Road("2", "E02", "gantry toll highway");
        r2.addTollFare(1, 4);
        r2.addTollFare(2, 4);

        expResult.addRoad(r1);
        expResult.addRoad(r2);

        expResult.addSection(s1);
        expResult.addSection(s2);
        expResult.addSection(s3);

        expResult.addVehicle(vehicle);
        Project result = instance.getProject();
        assertNotEquals(expResult, result);

    }

    /**
     * Test of getEnergy method, of class AlgorithmResults.
     */
    @Test
    public void testGetEnergy() {
        System.out.println("getEnergy");
        double expResult = 2000000.0;
        double result = instance.getEnergy();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of getTravelTime method, of class AlgorithmResults.
     */
    @Test
    public void testGetTravelTime() {
        System.out.println("getTravelTime");
        double expResult = 0.0;
        double result = instance.getTravelTime();
        assertNotEquals(expResult, result, 0.0);

    }

    /**
     * Test of getCost method, of class AlgorithmResults.
     */
    @Test
    public void testGetCost() {
        System.out.println("getCost");
        instance.setCost(20.0);
        double expResult = 20.0;
        double result = instance.getCost();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of getDistance method, of class AlgorithmResults.
     */
    @Test
    public void testGetDistance() {
        System.out.println("getDistance");
        instance.setDistance(20.0);
        double expResult = 20.0;
        double result = instance.getDistance();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of getSectionPath method, of class AlgorithmResults.
     */
    @Test
    public void testGetSectionPath() {
        System.out.println("getSectionPath");
        LinkedList<Section> expResult = fastestPath;
        LinkedList<Section> result = instance.getSectionPath();
        assertEquals(expResult, result);

    }

    /**
     * Test of getJunctionPath method, of class AlgorithmResults.
     */
    @Test
    public void testGetJunctionPath() {
        System.out.println("getJunctionPath");
        LinkedList<Junction> expResult = junctionsList;
        LinkedList<Junction> result = instance.getJunctionPath();
        assertEquals(expResult, result);

    }

    /**
     * Test of getVehicle method, of class AlgorithmResults.
     */
    @Test
    public void testGetVehicle() {
        System.out.println("getVehicle");
        Vehicle expResult = vehicle;
        Vehicle result = instance.getVehicle();
        assertEquals(expResult, result);

    }

    /**
     * Test of getVehicleLoad method, of class AlgorithmResults.
     */
    @Test
    public void testGetVehicleLoad() {
        System.out.println("getVehicleLoad");

        double expResult = vehicle.getCurrentLoad();
        double result = instance.getVehicleLoad();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of getAlgorithmType method, of class AlgorithmResults.
     */
    @Test
    public void testGetAlgorithmType() {
        System.out.println("getAlgorithmType");
        String expResult = "Fastest Path";
        String result = instance.getAlgorithmType();
        assertEquals(expResult, result);

    }

    /**
     * Test of hashCode method, of class AlgorithmResults.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        double[] results = {800, 2000000};
        AlgorithmResults expected = new AlgorithmResults(project, junctionsList, fastestPath, vehicle, results, "Fastest Path");
        int expResult = expected.hashCode();
        int result = instance.hashCode();
        assertEquals(expResult, result);

    }

    /**
     * Test of equals method, of class AlgorithmResults.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        double[] results = {800, 2000000};
        AlgorithmResults expected = new AlgorithmResults(project, junctionsList, fastestPath, vehicle, results, "Fastest Path");
        Object obj = expected;
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);

    }

    /**
     * Test of toString method, of class AlgorithmResults.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String expResult = "Project: TestProject\n"
                + "\n"
                + "Algorithm: Fastest Path\n"
                + "Vehicle: Pick_up\n"
                + "Vehicle total weight: 0.0 kg\n"
                + "\n"
                + "Path:\n"
                + "1 Junction j1 Junction j3\n"
                + "1 Junction j2 Junction j3\n"
                + "2 Junction j4 Junction j5\n"
                + "\n"
                + "Distance:0.0 km\n"
                + "Travel time:00:13:20 h\n"
                + "Cost:0 €\n"
                + "Energy:2,00 MJ";
        String result = instance.toString();
        assertEquals(expResult, result);

    }

    /**
     * Test of toStringHTML method, of class AlgorithmResults.
     */
    @Test
    public void testToStringHTML() {
        System.out.println("toStringHTML");
        StringBuilder sb = new StringBuilder();

        StringBuilder path = new StringBuilder();
        int i = 1;
        for (Section section : fastestPath) {
            path.append(section.toStringHTML() + "\n").append("Section " + i + ":");
            i++;
        }

        //sb.append("<h1>Fastest Path Results</h1>");
        sb.append("<table>\n");
        sb.append("\t<tr><th>Vehicle</th><th>Vehicle</th><th>Travel Time</th><th>Consumed Energy</th><th>Cost</th><th>Consumption</th></tr>\n");

        sb.append("<tr>"
                + "<td>").append(instance.getAlgorithmType()).append("</td>"
                + "<td>").append(this.vehicle.getName()).append("</td>"
                + "<td>").append(UnitConversion.convertSecondstoHoursMinSec(instance.getTravelTime())).append(" h</td>"
                + "<td>").append(String.format("%.2f", UnitConversion.convertJoulesToMegaJoules(instance.getEnergy()))).append(" MJ</td>"
                + "<td>").append(new DecimalFormat("#.##").format(instance.getCost())).append(" €</td>"
                + "<td>").append(new DecimalFormat("#.##").format(UnitConversion.convertJoulesToLitres(vehicle.getFuel(), instance.getEnergy()))).append(" liters/100km</td>");
        sb.append("</tr>\n");
        sb.append("</table>\n");
        sb.append("<h2> </h2>");
        sb.append("<table>\n");
        sb.append("\t<tr><th>Path</th></tr>\n");
        sb.append("<tr>"
                + "<td>").append(path).append("</td>");
        sb.append("</tr>\n");
        sb.append("</table>");

        String expResult = sb.toString();
        String result = instance.toStringHTML();
        assertEquals(expResult, result);

    }

}
