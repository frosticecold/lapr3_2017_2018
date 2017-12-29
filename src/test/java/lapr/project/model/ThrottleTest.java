/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
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
public class ThrottleTest {

    //Throttle 25 of Pickup in files Vehicles_v2.xml
    Throttle throttle;

    public ThrottleTest() {

    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        throttle = new Throttle();
        //Throttle 25 of Pickup in files Vehicles_v2.xml
        Regime reg1 = new Regime();

        reg1.setTorqueLow(115);
        reg1.setTorqueHigh(125);
        reg1.setRpmLow(900);
        reg1.setRpmHigh(1499);
        reg1.setSFC(500);
        throttle.getRegimeList().add(reg1);

        Regime reg2 = new Regime();
        reg2.setTorqueLow(125);
        reg2.setTorqueHigh(120);
        reg2.setRpmLow(1500);
        reg2.setRpmHigh(2499);
        reg2.setSFC(450);
        throttle.getRegimeList().add(reg2);

        Regime reg3 = new Regime();
        reg3.setTorqueLow(120);
        reg3.setTorqueHigh(105);
        reg3.setRpmLow(2500);
        reg3.setRpmHigh(3499);
        reg3.setSFC(520);
        throttle.getRegimeList().add(reg3);

        Regime reg4 = new Regime();
        reg4.setTorqueLow(105);
        reg4.setTorqueHigh(90);
        reg4.setRpmLow(3500);
        reg4.setRpmHigh(4499);
        reg4.setSFC(550);
        throttle.getRegimeList().add(reg4);

        Regime reg5 = new Regime();
        reg5.setTorqueLow(90);
        reg5.setTorqueHigh(80);
        reg5.setRpmLow(4500);
        reg5.setRpmHigh(5500);
        reg5.setSFC(650);
        throttle.getRegimeList().add(reg5);

    }

    @After
    public void tearDown() {
    }

    @Test
    public void testConstructor() {
        System.out.println("constructorTest");

        Throttle instance = new Throttle(throttle);
        assertEquals(instance.getSFCByRPM(1000),throttle.getSFCByRPM(1000),0.05);
        assertEquals(instance.getSFCByRPM(3000),throttle.getSFCByRPM(3000),0.05);
        assertEquals(instance.getSFCByRPM(4000),throttle.getSFCByRPM(4000),0.05);
        assertEquals(instance.getSFCByRPM(5000),throttle.getSFCByRPM(5000),0.05);
        
    }

    /**
     * Test of getRegimeList method, of class Throttle.
     */
    @Test
    public void testGetRegimeList() {
        System.out.println("getRegimeList");
        Throttle instance = new Throttle();
        List<Regime> expResult = new ArrayList<>();
        List<Regime> result = instance.getRegimeList();
        assertEquals(expResult, result);

        instance = new Throttle(result);
        assertEquals(expResult, result);

    }

    @Test
    public void testGetTorqueByRPMandSFCbyRPM() {
        System.out.println("testTorquebyRPMandSFCbyRPM");

        assertEquals(throttle.getTorqueByRPM(500), -1, 0.05);
        assertEquals(throttle.getTorqueByRPM(900), 115, 0.05);
        assertEquals(throttle.getTorqueByRPM(1200), 120, 0.05);
        assertEquals(throttle.getTorqueByRPM(1499), 125, 0.5);

        assertEquals(throttle.getTorqueByRPM(1500), 125, 05);
        assertEquals(throttle.getTorqueByRPM(2000), 122.5, 0.05);
        assertEquals(throttle.getTorqueByRPM(2499), 120, 05);

        assertEquals(throttle.getTorqueByRPM(2500), 120, 0.05);
        assertEquals(throttle.getTorqueByRPM(3000), 112.5, 0.05);
        assertEquals(throttle.getTorqueByRPM(3499), 105, 0.05);

        assertEquals(throttle.getTorqueByRPM(100000), -1, 0.05);
        
        
        assertEquals(throttle.getSFCByRPM(500), -1, 0.05);
        assertEquals(throttle.getSFCByRPM(900), 500, 0.05);
        assertEquals(throttle.getSFCByRPM(1200), 500, 0.05);
        assertEquals(throttle.getSFCByRPM(1499), 500, 0.5);

        assertEquals(throttle.getSFCByRPM(1500), 450, 05);
        assertEquals(throttle.getSFCByRPM(2000), 450, 0.05);
        assertEquals(throttle.getSFCByRPM(2499), 450, 05);

        assertEquals(throttle.getSFCByRPM(2500), 520, 0.05);
        assertEquals(throttle.getSFCByRPM(3000), 520, 0.05);
        assertEquals(throttle.getSFCByRPM(3499), 520, 0.05);

        assertEquals(throttle.getSFCByRPM(100000), -1, 0.05);
    }

    /**
     * Test of setRegimeList method, of class Throttle.
     */
    @Test
    public void testSetRegimeList() {
        System.out.println("setRegimeList");
        List<Regime> regime_list = new ArrayList<>();
        List<Regime> result = new ArrayList<>();
        Throttle instance = new Throttle();
        instance.setRegimeList(regime_list);
        assertEquals(result, regime_list);
    }

    /**
     * Test of toString method, of class Throttle.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Throttle instance = new Throttle();
        String expResult = "Throttle{regime_list=[]}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

}
