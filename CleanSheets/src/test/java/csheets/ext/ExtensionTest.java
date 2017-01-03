/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Diogo Azevedo
 */
public class ExtensionTest {

    Extension extTrue;
    Extension extFalse;

    public ExtensionTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        extTrue = new Extension("extensaoT") {
        };
        extFalse = new Extension("extensaoF", false) {
        };
    }

    @After
    public void tearDown() {
    }



    /**
     * Test of isEnabled method, of class Extension.
     */
    @Test
    public void testIsEnabled() {
        System.out.println("isEnabled");
        boolean expResult = true;
        boolean result = extTrue.isEnabled();
        assertEquals(expResult, result);
    }

    /**
     * Test of enable method, of class Extension.
     */
    @Test
    public void testEnable() {
        System.out.println("enable");
        extFalse.enable();
        assertEquals(true, extTrue.isEnabled());
    }

    /**
     * Test of disable method, of class Extension.
     */
    @Test
    public void testDisable() {
        System.out.println("disable");
        extTrue.disable();
        assertEquals(false, extTrue.isEnabled());
    }


}
