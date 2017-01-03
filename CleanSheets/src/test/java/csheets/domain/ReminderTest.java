/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.domain;

import java.text.DateFormat;
import java.util.Calendar;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Gabriel
 */
public class ReminderTest {
    private Reminder reminder;
    private Calendar date;
    
    public ReminderTest() {
        this.date=Calendar.getInstance();
        this.reminder=new Reminder("lembrete", "Teste",this.date , false);
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
     * Test of name method, of class Reminder.
     */
    @Test
    public void testName() {
        System.out.println("name");
        String expResult = "lembrete";
        String result = this.reminder.name();
        assertEquals(expResult, result);
    }

    /**
     * Test of description method, of class Reminder.
     */
    @Test
    public void testDescription() {
        System.out.println("description");
        String expResult = "Teste";
        String result = this.reminder.description();
        assertEquals(expResult, result);
    }

    /**
     * Test of timeOfReminder method, of class Reminder.
     */
    @Test
    public void testTimeOfReminder() {
        System.out.println("timeOfReminder");
        Calendar expResult = this.date;
        Calendar result = this.reminder.timeOfReminder();
        assertEquals(expResult, result);
    }

    /**
     * Test of alert method, of class Reminder.
     */
    @Test
    public void testAlert_0args() {
        System.out.println("alert");
        boolean expResult = false;
        boolean result = this.reminder.alert();
        assertEquals(expResult, result);
    }

    /**
     * Test of defineAlert method, of class Reminder.
     */
    @Test
    public void testDefineAlert() {
        System.out.println("defineAlert");
        boolean alert = false;
        this.reminder.defineAlert(alert);
        boolean expResult = this.reminder.alert();
        assertEquals(expResult, alert);
    }

    /**
     * Test of defineReminder method, of class Reminder.
     */
    @Test
    public void testDefineReminder() {
        System.out.println("defineReminder");
        String name = "novoNme";
        String description = "novaDesc";
        boolean alert = false;
        this.reminder.defineReminder(name, description, this.date, alert);
        String nomeEsperado = this.reminder.name();
        assertEquals(nomeEsperado, name);
        String dscEsperado = this.reminder.description();
        assertEquals(dscEsperado, description);
    }

    /**
     * Test of add method, of class Reminder.
     */
    @Test
    public void testAdd() {
        this.date.add(DateFormat.MINUTE_FIELD, 5);
        System.out.println("add");
        int field = 0;
        int amount = 5;
        this.reminder.add(DateFormat.MINUTE_FIELD, amount);
        Calendar expResult = this.date;
        Calendar result = this.reminder.timeOfReminder();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Reminder.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Reminder instance = new Reminder();
        instance.defineReminder("aaaa", "desc2", this.date, false);
        boolean expResult = false;
        boolean result = this.reminder.equals(instance);
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Reminder.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        int expResult = this.reminder.hashCode();
        int result = this.reminder.hashCode();
        assertEquals(expResult, result);
    }
    
}
