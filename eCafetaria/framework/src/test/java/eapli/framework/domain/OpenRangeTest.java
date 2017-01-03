/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.framework.domain;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Paulo Gandra Sousa
 */
public class OpenRangeTest extends AbstractRangeTest {

    public OpenRangeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        System.out.println("openRange");
        instance = Range.openRange(START, END);
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

    @Test
    public void ensureStartIsNotInRange() {
        System.out.println("ensureStartIsNotInRange");
        Long target = new Long(START_VALUE);
        boolean result = instance.includes(target);
        assertFalse("start cannot be part of an open range", result);
    }

    @Test
    public void ensureEndIsNotInRange() {
        System.out.println("ensureEndIsNotInRange");
        Long target = new Long(END_VALUE);
        boolean result = instance.includes(target);
        assertFalse("end cannot be part of an open range", result);
    }
}
