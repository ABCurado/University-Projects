/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Rui Bento
 */
public class VersionControlTest {
    
    public VersionControlTest() {
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
     * Test of isLastVersion method, of class VersionControl.
     */
    @Test
    public void testIsNotLastVersion() {
        System.out.println("isNotLastVersion");
        VersionControl instance = new VersionControl();
        int createVersions = 10;
        int result = 0;
        for (int i = 0; i < createVersions; i++) {
            result = instance.addVersion();
        }
        result--;
        assertEquals(instance.isLastVersion(result), false);
        result++;result++;
        assertEquals(instance.isLastVersion(result), false);
    }
    
    /**
     * Test of isLastVersion method, of class VersionControl.
     */
    @Test
    public void testIsLastVersion() {
        System.out.println("isLastVersion");
        VersionControl instance = new VersionControl();
        int createVersions = 10;
        int result = 0;
        for (int i = 0; i < createVersions; i++) {
            result = instance.addVersion();
        }
        assertEquals(instance.isLastVersion(result), true);
    }

    /**
     * Test of addVersion method, of class VersionControl.
     */
    @Test
    public void testAddVersion() {
        System.out.println("addVersion");
        VersionControl instance = new VersionControl();
        int createVersions = 10;
        int result = 0;
        for (int i = 0; i < createVersions; i++) {
            result = instance.addVersion();
        }
        assertEquals(createVersions, result);
    }

    /**
     * Test of delete method, of class VersionControl.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        VersionControl instance = new VersionControl();
        instance.delete();
        assertEquals(instance.isDeleted(), true);
    }
    
}
