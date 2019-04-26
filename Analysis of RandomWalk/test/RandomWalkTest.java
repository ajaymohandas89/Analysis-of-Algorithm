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
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import psa_randomwalk.RandomWalk;

public class RandomWalkTest {

    @Test
    public void testMove0() {
        RandomWalk rw = new RandomWalk();
        PrivateMethodTester pmt = new PrivateMethodTester(rw);
        pmt.invokePrivate("move", 1, 0);
        assertEquals(1.0, rw.distance(), 1.0E-7);
    }

    /**
     */
    @Test
    public void testMove1() {
        RandomWalk rw = new RandomWalk();
        PrivateMethodTester pmt = new PrivateMethodTester(rw);
        pmt.invokePrivate("move", 1, 0);
        assertEquals(1.0, rw.distance(), 1.0E-7);
        pmt.invokePrivate("move", 1, 0);
        assertEquals(2.0, rw.distance(), 1.0E-7);
        pmt.invokePrivate("move", -1, 0);
        assertEquals(1.0, rw.distance(), 1.0E-7);
        pmt.invokePrivate("move", -1, 0);
        assertEquals(0.0, rw.distance(), 1.0E-7);
    }

    /**
     */
    @Test
    public void testMove2() {
        RandomWalk rw = new RandomWalk();
        PrivateMethodTester pmt = new PrivateMethodTester(rw);
        pmt.invokePrivate("move", 0, 1);
        assertEquals(1.0, rw.distance(), 1.0E-7);
        pmt.invokePrivate("move", 0, 1);
        assertEquals(2.0, rw.distance(), 1.0E-7);
        pmt.invokePrivate("move", 0, -1);
        assertEquals(1.0, rw.distance(), 1.0E-7);
        pmt.invokePrivate("move", 0, -1);
        assertEquals(0.0, rw.distance(), 1.0E-7);
    }

    /**
     */
    @Test
    public void testMove3() {
        RandomWalk rw = new RandomWalk();
        double root2 = Math.sqrt(2);
        PrivateMethodTester pmt = new PrivateMethodTester(rw);
        pmt.invokePrivate("move", 1, 1);
        assertEquals(root2, rw.distance(), 1.0E-7);
        pmt.invokePrivate("move", 1, 1);
        assertEquals(2 * root2, rw.distance(), 1.0E-7);
        pmt.invokePrivate("move", 0, -2);
        assertEquals(2.0, rw.distance(), 1.0E-7);
        pmt.invokePrivate("move", -2, 0);
        assertEquals(0.0, rw.distance(), 1.0E-7);
    }

    /**
     */
    @Test
    public void testRandomWalk() {
        for (int i = 0; i < 5000; i++)
            assertEquals(10, RandomWalk.randomWalkMulti(100, 100), 4);
    }

    @Test
    public void testRandomWalk2() {
        for (int i = 0; i < 5000; i++)
            assertNotSame(0, RandomWalk.randomWalkMulti(1, 1));
    }
}