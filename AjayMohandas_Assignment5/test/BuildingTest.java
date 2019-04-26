/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Welcome
 */
public class BuildingTest {

    private Building snellLibrary;
    private String snell;
    private String sn;

    @Before
    public void setUp() throws Exception {
        snell = "Snell Engineering Center";
        sn = "SN";
        snellLibrary = new Building(54, sn, "Center", -71.0910495, 42.3382885, true, snell);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getName() {
        assertEquals(sn, snellLibrary.getName());
    }

    @Test
    public void getPosition() {
        assertEquals(new Position_Spherical(42.3382885, -71.0910495), snellLibrary.getPosition());
    }

    @Test
    public void toStringTest() {
        assertEquals(snell, snellLibrary.toString());
    }

}