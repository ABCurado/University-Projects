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
public class ListTest {
    
    private String title;
    private String text;
    private Contact contact;
    
    
    public ListTest() {
        title = "Lista de compras";
        text = "Arroz\nLaranja\nBatatas";
        contact = new PersonContact("Pedro", "Perna Alta", null, null, new byte[10]);
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
     * Test of getTitle method, of class List.
     */
    @Test
    public void testGetTitle() {
        System.out.println("getTitle");
        List instance = new List(title, text, contact);
        String expResult = title;
        String result = instance.getTitle();
        assertEquals(expResult, result);
    }

    /**
     * Test of getText method, of class List.
     */
    @Test
    public void testGetText() {
        System.out.println("getText");
        List instance = new List(title, text, contact);
        String expResult = text;
        String result = instance.getText();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLines method, of class List.
     */
    @Test
    public void testGetLines() {
        System.out.println("getLines");
        List instance = new List(title, text, contact);
        String expResult[] = text.split("\n");
        java.util.List<List.ListLine> result = instance.getLines();
        assertEquals(expResult.length, result.size());
        for (int i = 0; i < expResult.length; i++) {
            assertEquals(expResult[i], result.get(i).getText());
        }
    }

    /**
     * Test of changeState method, of class List.
     */
    @Test
    public void testChangeState() {
        System.out.println("changeState");
        List instance = new List(title, text, contact);
        boolean check = true;
        for (List.ListLine line : instance.getLines()) {
            assertEquals(line.getCheck(), false);
            instance.changeState(line.getText(), check);
            assertEquals(line.getCheck(), check);
            check = !check;
        }
    }

    /**
     * Test of getVersionNumber method, of class List.
     */
    @Test
    public void testGetVersionNumber() {
        System.out.println("getVersionNumber");
        List instance = new List(title, text, contact);
        int expResult = 1;
        int result = instance.getVersionNumber();
        assertEquals(expResult, result);
    }

    /**
     * Test of newVersion method, of class List.
     */
    @Test
    public void testNewVersion() {
        System.out.println("newVersion");
        String title = "New List";
        String text = "Awesome\nDo\nThings";
        int newVersionNum = 2;
        List instance = new List(this.title, this.text, contact);
        List result = instance.newVersion(title, text);
        assertEquals(result.getTitle(), title);
        assertEquals(result.getText(), text);
        assertEquals(result.getVersionNumber(), newVersionNum);
        assertEquals(result.isLatestVersion(), true);
    }

    /**
     * Test of delete method, of class List.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        List instance = new List(this.title, this.text, contact);
        instance.delete();
        assertEquals(instance.isDeleted(), true);
    }

    /**
     * Test of isLatestVersion method, of class List.
     */
    @Test
    public void testIsLatestVersion() {
        System.out.println("isLatestVersion");
        List instance = new List(this.title, this.text, contact);
        boolean expResult = true;
        boolean result = instance.isLatestVersion();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of isLatestVersion method, of class List.
     */
    @Test
    public void testIsNotLatestVersion() {
        System.out.println("isNotLatestVersion");
        List instance = new List(this.title, this.text, contact);
        String title = "New List";
        String text = "Awesome\nDo\nThings";
        List newList = instance.newVersion(title, text);
        boolean expResult = false;
        boolean result = instance.isLatestVersion();
        assertEquals(expResult, result);
    }

    /**
     * Test of sameNotation method, of class List.
     */
    @Test
    public void testSameNotation() {
        System.out.println("sameNotation");
        List instance = new List(this.title, this.text, contact);
        String title = "New List";
        String text = "Awesome\nDo\nThings";
        List newList = instance.newVersion(title, text);
        boolean expResult = true;
        boolean result = instance.sameNotation(newList);
        assertEquals(expResult, result);
    }

    /**
     * Test of sameNotation method, of class List.
     */
    @Test
    public void testNotSameNotation() {
        System.out.println("notSameNotation");
        List instance = new List(this.title, this.text, contact);
        List newList = new List(this.title, this.text, contact);
        boolean expResult = false;
        boolean result = instance.sameNotation(newList);
        assertEquals(expResult, result);
    }
    
}
