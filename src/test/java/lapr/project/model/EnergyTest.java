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
public class EnergyTest {

    public EnergyTest() {
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
     * Test of getMinRpm method, of class Energy.
     */
    @Test
    public void testGetMinRpm() {
        System.out.println("getMinRpm");
        Gearbox gearBox = new Gearbox();
        Gear gear1 = new Gear(1, 3.3);
        Gear gear2 = new Gear(2, 2.3);
        Gear gear3 = new Gear(3, 1.2);
        Gear gear4 = new Gear(4, 0.6);
        Gear gear5 = new Gear(5, 0.4);
        gearBox.addGear(gear1);
        gearBox.addGear(gear2);
        gearBox.addGear(gear3);
        gearBox.addGear(gear4);
        gearBox.addGear(gear5);
        double minRpm = 3500;
        double maxRpm = 6000;
        double finalDriverRatio = 3.5;
        ArrayList<Regime> m_regime_list = new ArrayList<>();
        Regime r1 = new Regime(115, 900, 1499, 500);
        Regime r2 = new Regime(125, 1500, 2499, 450);
        Regime r3 = new Regime(120, 2500, 3499, 520);
        Regime r4 = new Regime(105, 3500, 4499, 550);
        Regime r5 = new Regime(90, 4500, 5500, 650);
        m_regime_list.add(r1);
        m_regime_list.add(r2);
        m_regime_list.add(r3);
        m_regime_list.add(r4);
        m_regime_list.add(r5);
        Throttle t1 = new Throttle(m_regime_list);
        ArrayList<Throttle> throttleList = new ArrayList<>();
        throttleList.add(t1);

        Energy instance = new Energy(minRpm, maxRpm, finalDriverRatio, gearBox, throttleList);
        double expResult = minRpm;
        double result = instance.getMinRpm();
        assertEquals(expResult, result, 0.0001);
    }

    /**
     * Test of getMaxRpm method, of class Energy.
     */
    @Test
    public void testGetMaxRpm() {
       Gearbox gearBox = new Gearbox();
        Gear gear1 = new Gear(1, 3.3);
        Gear gear2 = new Gear(2, 2.3);
        Gear gear3 = new Gear(3, 1.2);
        Gear gear4 = new Gear(4, 0.6);
        Gear gear5 = new Gear(5, 0.4);
        gearBox.addGear(gear1);
        gearBox.addGear(gear2);
        gearBox.addGear(gear3);
        gearBox.addGear(gear4);
        gearBox.addGear(gear5);
        double minRpm = 3500;
        double maxRpm = 6000;
        double finalDriverRatio = 3.5;
        ArrayList<Regime> m_regime_list = new ArrayList<>();
        Regime r1 = new Regime(115, 900, 1499, 500);
        Regime r2 = new Regime(125, 1500, 2499, 450);
        Regime r3 = new Regime(120, 2500, 3499, 520);
        Regime r4 = new Regime(105, 3500, 4499, 550);
        Regime r5 = new Regime(90, 4500, 5500, 650);
        m_regime_list.add(r1);
        m_regime_list.add(r2);
        m_regime_list.add(r3);
        m_regime_list.add(r4);
        m_regime_list.add(r5);
        Throttle t1 = new Throttle(m_regime_list);
        ArrayList<Throttle> throttleList = new ArrayList<>();
        throttleList.add(t1);

        Energy instance = new Energy(minRpm, maxRpm, finalDriverRatio, gearBox, throttleList);
        double expResult = maxRpm;
        double result = instance.getMaxRpm();
        assertEquals(expResult, result, 0.0001);
    }

    /**
     * Test of getFinalDriveRatio method, of class Energy.
     */
    @Test
    public void testGetFinalDriveRatio() {
       Gearbox gearBox = new Gearbox();
        Gear gear1 = new Gear(1, 3.3);
        Gear gear2 = new Gear(2, 2.3);
        Gear gear3 = new Gear(3, 1.2);
        Gear gear4 = new Gear(4, 0.6);
        Gear gear5 = new Gear(5, 0.4);
        gearBox.addGear(gear1);
        gearBox.addGear(gear2);
        gearBox.addGear(gear3);
        gearBox.addGear(gear4);
        gearBox.addGear(gear5);
        double minRpm = 3500;
        double maxRpm = 6000;
        double finalDriverRatio = 3.5;
        ArrayList<Regime> m_regime_list = new ArrayList<>();
        Regime r1 = new Regime(115, 900, 1499, 500);
        Regime r2 = new Regime(125, 1500, 2499, 450);
        Regime r3 = new Regime(120, 2500, 3499, 520);
        Regime r4 = new Regime(105, 3500, 4499, 550);
        Regime r5 = new Regime(90, 4500, 5500, 650);
        m_regime_list.add(r1);
        m_regime_list.add(r2);
        m_regime_list.add(r3);
        m_regime_list.add(r4);
        m_regime_list.add(r5);
        Throttle t1 = new Throttle(m_regime_list);
        ArrayList<Throttle> throttleList = new ArrayList<>();
        throttleList.add(t1);

        Energy instance = new Energy(minRpm, maxRpm, finalDriverRatio, gearBox, throttleList);
        double expResult = finalDriverRatio;
        double result = instance.getFinalDriveRatio();
        assertEquals(expResult, result, 0.0001);
    }

    /**
     * Test of getGearList method, of class Energy.
     */
    @Test
    public void testGetGearList() {
       Gearbox gearBox = new Gearbox();
        Gear gear1 = new Gear(1, 3.3);
        Gear gear2 = new Gear(2, 2.3);
        Gear gear3 = new Gear(3, 1.2);
        Gear gear4 = new Gear(4, 0.6);
        Gear gear5 = new Gear(5, 0.4);
        gearBox.addGear(gear1);
        gearBox.addGear(gear2);
        gearBox.addGear(gear3);
        gearBox.addGear(gear4);
        gearBox.addGear(gear5);
        double minRpm = 3500;
        double maxRpm = 6000;
        double finalDriverRatio = 3.5;
        ArrayList<Regime> m_regime_list = new ArrayList<>();
        Regime r1 = new Regime(115, 900, 1499, 500);
        Regime r2 = new Regime(125, 1500, 2499, 450);
        Regime r3 = new Regime(120, 2500, 3499, 520);
        Regime r4 = new Regime(105, 3500, 4499, 550);
        Regime r5 = new Regime(90, 4500, 5500, 650);
        m_regime_list.add(r1);
        m_regime_list.add(r2);
        m_regime_list.add(r3);
        m_regime_list.add(r4);
        m_regime_list.add(r5);
        Throttle t1 = new Throttle(m_regime_list);
        ArrayList<Throttle> throttleList = new ArrayList<>();
        throttleList.add(t1);

        Energy instance = new Energy(minRpm, maxRpm, finalDriverRatio, gearBox, throttleList);
        Gearbox expResult = gearBox;
        Gearbox result = instance.getGearList();
        assertEquals(expResult, result);
    }

    /**
     * Test of getThrottleList method, of class Energy.
     */
    @Test
    public void testGetThrottleList() {
        Gearbox gearBox = new Gearbox();
        Gear gear1 = new Gear(1, 3.3);
        Gear gear2 = new Gear(2, 2.3);
        Gear gear3 = new Gear(3, 1.2);
        Gear gear4 = new Gear(4, 0.6);
        Gear gear5 = new Gear(5, 0.4);
        gearBox.addGear(gear1);
        gearBox.addGear(gear2);
        gearBox.addGear(gear3);
        gearBox.addGear(gear4);
        gearBox.addGear(gear5);
        double minRpm = 3500;
        double maxRpm = 6000;
        double finalDriverRatio = 3.5;
        ArrayList<Regime> m_regime_list = new ArrayList<>();
        Regime r1 = new Regime(115, 900, 1499, 500);
        Regime r2 = new Regime(125, 1500, 2499, 450);
        Regime r3 = new Regime(120, 2500, 3499, 520);
        Regime r4 = new Regime(105, 3500, 4499, 550);
        Regime r5 = new Regime(90, 4500, 5500, 650);
        m_regime_list.add(r1);
        m_regime_list.add(r2);
        m_regime_list.add(r3);
        m_regime_list.add(r4);
        m_regime_list.add(r5);
        Throttle t1 = new Throttle(m_regime_list);
        ArrayList<Throttle> throttleList = new ArrayList<>();
        throttleList.add(t1);

        Energy instance = new Energy(minRpm, maxRpm, finalDriverRatio, gearBox, throttleList);
        ArrayList<Throttle>  expResult = throttleList;
        ArrayList<Throttle> result = (ArrayList<Throttle>) instance.getThrottleList();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMinRpm method, of class Energy.
     */
    @Test
    public void testSetMinRpm() {
        System.out.println("setMinRpm");
        Gearbox gearBox = new Gearbox();
        Gear gear1 = new Gear(1, 3.3);
        Gear gear2 = new Gear(2, 2.3);
        Gear gear3 = new Gear(3, 1.2);
        Gear gear4 = new Gear(4, 0.6);
        Gear gear5 = new Gear(5, 0.4);
        gearBox.addGear(gear1);
        gearBox.addGear(gear2);
        gearBox.addGear(gear3);
        gearBox.addGear(gear4);
        gearBox.addGear(gear5);
        double minRpm = 3500;
        double maxRpm = 6000;
        double finalDriverRatio = 3.5;
        ArrayList<Regime> m_regime_list = new ArrayList<>();
        Regime r1 = new Regime(115, 900, 1499, 500);
        Regime r2 = new Regime(125, 1500, 2499, 450);
        Regime r3 = new Regime(120, 2500, 3499, 520);
        Regime r4 = new Regime(105, 3500, 4499, 550);
        Regime r5 = new Regime(90, 4500, 5500, 650);
        m_regime_list.add(r1);
        m_regime_list.add(r2);
        m_regime_list.add(r3);
        m_regime_list.add(r4);
        m_regime_list.add(r5);
        Throttle t1 = new Throttle(m_regime_list);
        ArrayList<Throttle> throttleList = new ArrayList<>();
        throttleList.add(t1);

        Energy instance = new Energy();
        instance.setMinRpm(minRpm);
        double expResult = minRpm;
        double result = instance.getMinRpm();
        assertEquals(expResult, result, 0.0001);
        
        instance.setMaxRpm(maxRpm);
        expResult = maxRpm;
        result = instance.getMaxRpm();
        assertEquals(expResult, result, 0.0001);
    
        instance.setFinalDriveRatio(finalDriverRatio);
        expResult = finalDriverRatio;
        result = instance.getFinalDriveRatio();
        assertEquals(expResult, result, 0.0001);
    
        instance.setGearsList(gearBox);
        assertEquals(gearBox,instance.getGearList());

        instance.setThrottleList(throttleList);
        assertEquals(throttleList,instance.getThrottleList());
        
        instance.setGearsList(null);
        assertNull(instance.getGearList());

        instance.setThrottleList(null);
        assertNull(instance.getThrottleList());
    }
    
     @Test
    public void testToString() {
          Gearbox gearBox = new Gearbox();
        Gear gear1 = new Gear(1, 3.3);
        Gear gear2 = new Gear(2, 2.3);
        Gear gear3 = new Gear(3, 1.2);
        Gear gear4 = new Gear(4, 0.6);
        Gear gear5 = new Gear(5, 0.4);
        gearBox.addGear(gear1);
        gearBox.addGear(gear2);
        gearBox.addGear(gear3);
        gearBox.addGear(gear4);
        gearBox.addGear(gear5);
        double minRpm = 3500;
        double maxRpm = 6000;
        double finalDriverRatio = 3.5;
        ArrayList<Regime> m_regime_list = new ArrayList<>();
        Regime r1 = new Regime(115, 900, 1499, 500);
        Regime r2 = new Regime(125, 1500, 2499, 450);
        Regime r3 = new Regime(120, 2500, 3499, 520);
        Regime r4 = new Regime(105, 3500, 4499, 550);
        Regime r5 = new Regime(90, 4500, 5500, 650);
        m_regime_list.add(r1);
        m_regime_list.add(r2);
        m_regime_list.add(r3);
        m_regime_list.add(r4);
        m_regime_list.add(r5);
        Throttle t1 = new Throttle(m_regime_list);
        ArrayList<Throttle> throttleList = new ArrayList<>();
        throttleList.add(t1);

        Energy instance = new Energy(minRpm, maxRpm, finalDriverRatio, gearBox, throttleList);
        
        String expResult = "Energy{m_min_rpm=3500.0, m_max_rpm=6000.0, m_final_drive_ratio=3.5, m_gears_list=Gearbox{m_gears=[Gear{m_gearID=1, m_ratio=3.3}, Gear{m_gearID=2, m_ratio=2.3}, Gear{m_gearID=3, m_ratio=1.2}, Gear{m_gearID=4, m_ratio=0.6}, Gear{m_gearID=5, m_ratio=0.4}]}, m_throttle_list=[Throttle{m_regime_list=[Regime{m_torque=115.0, m_rpm_low=900.0, m_rpm_high=1499.0, m_SFC=500.0}, Regime{m_torque=125.0, m_rpm_low=1500.0, m_rpm_high=2499.0, m_SFC=450.0}, Regime{m_torque=120.0, m_rpm_low=2500.0, m_rpm_high=3499.0, m_SFC=520.0}, Regime{m_torque=105.0, m_rpm_low=3500.0, m_rpm_high=4499.0, m_SFC=550.0}, Regime{m_torque=90.0, m_rpm_low=4500.0, m_rpm_high=5500.0, m_SFC=650.0}]}]}";
        String  result = instance.toString();
        assertEquals(expResult , result );
        
        instance = new  Energy();
        
        expResult = "Energy{m_min_rpm=0.0, m_max_rpm=0.0, m_final_drive_ratio=0.0, m_gears_list=null, m_throttle_list=null}";
        result = instance.toString();
        assertEquals(expResult , result );
    }
}
