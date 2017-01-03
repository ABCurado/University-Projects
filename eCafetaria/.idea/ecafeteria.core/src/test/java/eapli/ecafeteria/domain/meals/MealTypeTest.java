/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.meals;

import java.util.Calendar;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Diogo Leite <1140260@isep.ipp.pt>
 */
public class MealTypeTest {
    MealType instance;
    Calendar startDate;
    Calendar endDate;
    
    public MealTypeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        startDate = Calendar.getInstance();
        endDate = Calendar.getInstance();
        endDate.add(Calendar.DATE, +1);
        instance=new MealType(startDate,endDate,"Jantar");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of startDate method, of class MealType.
     */
    @Test
    public void testStartDate() {
        Calendar expResult = startDate;
        Calendar result = instance.startDate();
        assertEquals(expResult, result);

    }

    /**
     * Test of endDate method, of class MealType.
     */
    @Test
    public void testEndDate() {
        Calendar expResult = endDate;
        Calendar result = instance.endDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of designation method, of class MealType.
     */
    @Test
    public void testDesignation() {
        String expResult = "Jantar";
        String result = instance.designation();
        assertEquals(expResult, result);
    }
//
//    /**
//     * Test of hashCode method, of class MealType.
//     */
//    @Test
//    public void testHashCode() {
//        System.out.println("hashCode");
//        MealType instance = new MealType();
//        int expResult = 0;
//        int result = instance.hashCode();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of equals method, of class MealType.
//     */
//    @Test
//    public void testEquals() {
//        System.out.println("equals");
//        Object object = null;
//        MealType instance = new MealType();
//        boolean expResult = false;
//        boolean result = instance.equals(object);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of toString method, of class MealType.
//     */
//    @Test
//    public void testToString() {
//        System.out.println("toString");
//        MealType instance = new MealType();
//        String expResult = "";
//        String result = instance.toString();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of isActive method, of class MealType.
//     */
//    @Test
//    public void testIsActive() {
//        System.out.println("isActive");
//        MealType instance = new MealType();
//        boolean expResult = false;
//        boolean result = instance.isActive();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
