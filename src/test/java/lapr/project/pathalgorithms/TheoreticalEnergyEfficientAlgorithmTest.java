/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.pathalgorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lapr.project.model.Accelerator;
import lapr.project.model.Gear;
import lapr.project.model.Gearbox;
import lapr.project.model.Junction;
import lapr.project.model.Project;
import lapr.project.model.Regime;
import lapr.project.model.Road;
import lapr.project.model.Section;
import lapr.project.model.Segment;
import lapr.project.model.Throttle;
import lapr.project.model.Vehicle;
import lapr.project.model.VehicleCombustion;
import lapr.project.networkanalysis.AlgorithmResults;
import lapr.project.utils.Session;
import lapr.project.utils.graphbase.Graph;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Car
 */
public class TheoreticalEnergyEfficientAlgorithmTest {
   Project project;
    private static final String FX_NETWORK = "TestSet02_Network_v2.xml";
    private static final String FX_VEHICLE = "TestSet02_Vehicles_v2.xml";

    public TheoreticalEnergyEfficientAlgorithmTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    Graph<Junction, Section> roadNetwork;

    Vehicle vh1;

    @Before
    public void setUp() {
        roadNetwork = new Graph<>(true);
        project = new Project("Project 1", "Test");
        project.setRoadNetwork(roadNetwork);
        Map<String, Double> mapRoadVelocityLimit = new HashMap<>();
        mapRoadVelocityLimit.put("HIGHWAY", 110.0);
        mapRoadVelocityLimit.put("ROAD", 80.0);

        Gear g1 = new Gear(1, 4.5);
        Gear g2 = new Gear(2, 3.5);
        Gear g3 = new Gear(3, 2.7);
        Gear g4 = new Gear(4, 1.6);
        Gear g5 = new Gear(5, 1.2);
        Gear g6 = new Gear(6, 0.9);
        List<Gear> list = new ArrayList<>();
        list.add(g1);
        list.add(g2);
        list.add(g3);
        list.add(g4);
        list.add(g5);
        list.add(g6);
        Gearbox gearbox1 = new Gearbox(list);

        Map<Integer, Throttle> throttleList = new LinkedHashMap<>();
        Throttle throttle = new Throttle();
        //Throttle 25 of Pickup in files Vehicles_v2.xml
        Regime reg1 = new Regime();

        reg1.setTorqueLow(115);
        reg1.setTorqueHigh(125);
        reg1.setRpmLow(900);
        reg1.setRpmHigh(1499);
        reg1.setSFC(500);
        throttle.getRegimeList().add(reg1);

        Regime reg2 = new Regime();
        reg2.setTorqueHigh(125);
        reg2.setTorqueLow(120);
        reg2.setRpmLow(1500);
        reg2.setRpmHigh(2499);
        reg2.setSFC(450);
        throttle.getRegimeList().add(reg2);

        Regime reg3 = new Regime();
        reg3.setTorqueHigh(120);
        reg3.setTorqueLow(105);
        reg3.setRpmLow(2500);
        reg3.setRpmHigh(3499);
        reg3.setSFC(520);
        throttle.getRegimeList().add(reg3);

        Regime reg4 = new Regime();
        reg4.setTorqueHigh(105);
        reg4.setTorqueLow(90);
        reg4.setRpmLow(3500);
        reg4.setRpmHigh(4499);
        reg4.setSFC(550);
        throttle.getRegimeList().add(reg4);

        Regime reg5 = new Regime();
        reg5.setTorqueHigh(90);
        reg5.setTorqueLow(80);
        reg5.setRpmLow(4500);
        reg5.setRpmHigh(5500);
        reg5.setSFC(650);
        throttle.getRegimeList().add(reg5);

        throttleList.put(25, throttle);

        Throttle throttle1 = new Throttle();
        Regime reg11 = new Regime();

        reg11.setTorqueLow(185);
        reg11.setTorqueHigh(195);
        reg11.setRpmLow(900);
        reg11.setRpmHigh(1499);
        reg11.setSFC(500);
        throttle1.getRegimeList().add(reg11);

        Regime reg22 = new Regime();
        reg22.setTorqueHigh(195);
        reg22.setTorqueLow(190);
        reg22.setRpmLow(1500);
        reg22.setRpmHigh(2499);
        reg22.setSFC(450);
        throttle1.getRegimeList().add(reg22);

        Regime reg33 = new Regime();
        reg33.setTorqueHigh(190);
        reg33.setTorqueLow(180);
        reg33.setRpmLow(2500);
        reg33.setRpmHigh(3499);
        reg33.setSFC(520);
        throttle1.getRegimeList().add(reg33);

        Regime reg44 = new Regime();
        reg44.setTorqueHigh(180);
        reg44.setTorqueLow(150);
        reg44.setRpmLow(3500);
        reg44.setRpmHigh(4499);
        reg44.setSFC(550);
        throttle1.getRegimeList().add(reg44);

        Regime reg55 = new Regime();
        reg55.setTorqueHigh(150);
        reg55.setTorqueLow(135);
        reg55.setRpmLow(4500);
        reg55.setRpmHigh(5500);
        reg55.setSFC(650);
        throttle1.getRegimeList().add(reg55);

        throttleList.put(50, throttle1);

        Throttle throttle2 = new Throttle();
        Regime reg111 = new Regime();

        reg111.setTorqueLow(305);
        reg111.setTorqueHigh(325);
        reg111.setRpmLow(900);
        reg111.setRpmHigh(1499);
        reg111.setSFC(500);
        throttle2.getRegimeList().add(reg111);

        Regime reg222 = new Regime();
        reg222.setTorqueHigh(325);
        reg222.setTorqueLow(315);
        reg222.setRpmLow(1500);
        reg222.setRpmHigh(2499);
        reg222.setSFC(450);
        throttle2.getRegimeList().add(reg222);

        Regime reg333 = new Regime();
        reg333.setTorqueHigh(315);
        reg333.setTorqueLow(290);
        reg333.setRpmLow(2500);
        reg333.setRpmHigh(3499);
        reg333.setSFC(520);
        throttle2.getRegimeList().add(reg333);

        Regime reg444 = new Regime();
        reg444.setTorqueHigh(290);
        reg444.setTorqueLow(220);
        reg444.setRpmLow(3500);
        reg444.setRpmHigh(4499);
        reg444.setSFC(550);
        throttle2.getRegimeList().add(reg444);

        Regime reg555 = new Regime();
        reg555.setTorqueHigh(220);
        reg555.setTorqueLow(205);
        reg555.setRpmLow(4500);
        reg555.setRpmHigh(5500);
        reg555.setSFC(650);
        throttle2.getRegimeList().add(reg555);

        throttleList.put(100, throttle2);

        Accelerator acc = new Accelerator();
        acc.setThrottleList(throttleList);

        vh1 = new VehicleCombustion(1000, 5000, 4, gearbox1, acc, "Pickup", "Super duper pickup", "Car", Vehicle.FUEL_GASOLINE, 1, "Combustion", 2400, 1200, 0.39, 0.8, 2.4, 0.015, mapRoadVelocityLimit, throttleList);

        Junction j1 = new Junction("n0");
        Junction j2 = new Junction("n1");
        Junction j3 = new Junction("n2");
        Junction j4 = new Junction("n3");
        Junction j5 = new Junction("n4");
        Junction j6 = new Junction("n5");
        Junction j7 = new Junction("n6");
        Junction j8 = new Junction("n7");

        List<Road> listRoad = new ArrayList<>();
        Road road1 = new Road("E01", "E01", "regular road");
        Road road2 = new Road("A01", "A1", "toll highway");
        road2.addTollFare(1, 0.15);
        road2.addTollFare(2, 0.25);
        road2.addTollFare(3, 0.35);
        Road road3 = new Road("A02", "A2", "gantry toll highway");
        Road road4 = new Road("E06", "E06", "regular road");
        Road road5 = new Road("N232", "N232", "regular road");
        listRoad.add(road1);
        listRoad.add(road2);
        listRoad.add(road3);
        listRoad.add(road4);
        listRoad.add(road5);
        project.setListRoads(listRoad);

        Section section1 = new Section();
        section1.setBeginJunction(j1);
        section1.setEndJunction(j3);
        section1.setRoadID("E01");
        section1.setTypology("regular road");
        section1.setDirection(Section.Direction.BIDIRECTIONAL);
        Segment segment = new Segment(1, 100, 200, 1.2, 20, 5, 90, 0);
        Segment segment1 = new Segment(2, 200, 150, 6.5, -10, 2, 90, 0);
        Segment segment2 = new Segment(3, 150, 350, 4, -10, 2.5, 90, 0);
        Segment segment3 = new Segment(4, 350, 150, 10, -60, 2.7, 90, 0);
        List<Segment> listSegment = new ArrayList<>();
        listSegment.add(segment);
        listSegment.add(segment1);
        listSegment.add(segment2);
        listSegment.add(segment3);
        section1.setSegmentList(listSegment);

        Section section2 = new Section();
        section2.setBeginJunction(j3);
        section2.setEndJunction(j4);
        section2.setRoadID("E01");
        section2.setTypology("regular road");
        section2.setDirection(Section.Direction.BIDIRECTIONAL);
        Segment segment4 = new Segment(1, 150, 250, 1.5, 120, 1.5, 90, 0);
        Segment segment5 = new Segment(2, 250, 450, 6.5, -170, 2, 90, 0);
        Segment segment6 = new Segment(3, 450, 250, 4, -80, 2.5, 90, 0);
        List<Segment> listSegment2 = new ArrayList<>();
        listSegment2.add(segment4);
        listSegment2.add(segment5);
        listSegment2.add(segment6);
        section2.setSegmentList(listSegment2);

        Section section3 = new Section();
        section3.setBeginJunction(j4);
        section3.setEndJunction(j5);
        section3.setRoadID("E01");
        section3.setTypology("regular road");
        section3.setDirection(Section.Direction.BIDIRECTIONAL);
        Segment segment7 = new Segment(1, 250, 350, 10.5, 40, 2.5, 90, 0);
        Segment segment8 = new Segment(2, 350, 550, 4, -120, 2, 90, 0);
        Segment segment9 = new Segment(3, 550, 350, 6, -10, 2, 90, 0);
        List<Segment> listSegment3 = new ArrayList<>();
        listSegment3.add(segment7);
        listSegment3.add(segment8);
        listSegment3.add(segment9);
        section3.setSegmentList(listSegment3);

        Session.setActiveProject(project);

        project.addVehicle(vh1);

        project.addRoad(road1);
        project.addRoad(road2);
        project.addRoad(road3);
        project.addRoad(road4);
        project.addRoad(road5);
        project.addJunction(j1);
        project.addJunction(j2);
        project.addJunction(j3);
        project.addJunction(j4);
        project.addJunction(j5);
        project.addJunction(j6);
        project.addJunction(j7);

        project.addSection(section1);
        project.addSection(section2);
        project.addSection(section3);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of efficientPath method, of class bestPathAlgorithm.
     */
    @Test
    public void testEfficientPath() {
        System.out.println("bestPath");
        Graph<Junction, Section> graph = project.getRoadNetwork();
        Junction vOrig = project.getJunction("n0");
        Junction vDest = project.getJunction("n3");
        PathAlgorithm alg = new TheoreticalEnergyEfficientAlgorithm();
        AlgorithmResults result = alg.bestPath(graph, vOrig, vDest, vh1, 0);
        assertTrue(result.getVehicle().equals(vh1));
        assertTrue(result.getJunctionPath().contains(vOrig));
        assertTrue(result.getJunctionPath().contains(vDest));
        assertTrue(result.getSectionPath().get(0).getRoadID().equals("E01"));
        assertEquals(result.getDistance(), 33.7d, 0.5);
        assertEquals(result.getTravelTime(), 1516.5, 1);
        assertEquals(result.getCost(), 0, 0.5);
        assertEquals(result.getEnergy(), (1.95 * Math.pow(10, 7)), (0.1 * Math.pow(10, 7)));
        assertEquals(result.getAlgorithmType(), "best Path");
    }

    @Test
    public void test2BestPath() {
        System.out.println("bestPath2");
        Graph<Junction, Section> graph = project.getRoadNetwork();
        Junction vOrig = project.getJunction("n0");
        Junction vDest = project.getJunction("n4");
        PathAlgorithm alg = new TheoreticalEnergyEfficientAlgorithm();
        AlgorithmResults result = alg.bestPath(graph, vOrig, vDest, vh1, 0);

        assertTrue(result.getVehicle().equals(vh1));
        assertTrue(result.getJunctionPath().contains(vOrig));
        assertTrue(result.getJunctionPath().contains(vDest));
        assertTrue(result.getSectionPath().get(0).getRoadID().equals("E01"));
        assertEquals(result.getDistance(), 54.2d, 0.5);
        assertEquals(result.getTravelTime(), 2439.0, 1);
        assertEquals(result.getCost(), 0, 0.5);
        assertEquals(result.getEnergy(), (2.34 * Math.pow(10, 7)), (0.1 * Math.pow(10, 7)));
        assertEquals(result.getAlgorithmType(), "best Path");
    }
}
