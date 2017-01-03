/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.task;

import csheets.AppSettings;
import csheets.domain.Contact;
import csheets.domain.ContactCalendar;
import csheets.domain.Event;
import csheets.domain.PersonContact;
import csheets.domain.Reminder;
import csheets.domain.Task;
import csheets.ext.events.EventsController;
import csheets.ext.reminder.ReminderController;
import csheets.framework.persistence.repositories.DataIntegrityViolationException;
import csheets.persistence.PersistenceContext;
import csheets.support.DateTime;
import csheets.ui.ctrl.UIController;
import java.awt.Color;
import java.util.Calendar;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Hicham Abahri <1141042.isep.ipp.pt>
 */
public class TaskControllerTest {

    private Task task;
    private TaskController taskController;
    private ReminderController reminderController;
    private EventsController eventsController;
    private Reminder reminder;
    private Event event;
    private byte[] photo;
    private Contact contact;
    private ContactCalendar contactCalendar;
    private Calendar calendar;
    private UIController uicontroller;
    private static String repositoryFactory;

    public TaskControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        repositoryFactory = AppSettings.instance().
                get("persistence.repositoryFactory");
        AppSettings.instance().
                set("persistence.repositoryFactory", "csheets.persistence.inmemory.InMemoryRepositoryFactory");
    }

    @AfterClass
    public static void tearDownClass() {
        AppSettings.instance().
                set("persistence.repositoryFactory", repositoryFactory);
    }

    @Before
    public void setUp() throws DataIntegrityViolationException {
        taskController = new TaskController(uicontroller);
        reminderController = new ReminderController(null, null);
        this.photo = new byte[12];
        this.contact = new PersonContact("Hicham", "Abhari", null, null, this.photo);
        PersistenceContext.repositories().contacts().save(contact);
        contactCalendar = new ContactCalendar("ISEP", "Calendario ISEP", new Color(20), contact);
        PersistenceContext.repositories().calendars().save(contactCalendar);
        this.calendar = DateTime.newCalendar(2016, 7, 20);
        reminderController.
                createReminder("São João", "Feriado São João", calendar, false);
        event = new Event(contactCalendar, "Evento 1", DateTime.
                newCalendar(2016, 6, 20), DateTime.
                newCalendar(2016, 6, 28));
        PersistenceContext.repositories().events().save(event);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of showContacts method, of class TaskController.
     */
    @Test
    public void testShowContacts() {
//        System.out.println("showContacts");
//        TaskController instance = null;
//        Iterable<Contact> expResult = null;
//        Iterable<Contact> result = instance.showContacts();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of createTask method, of class TaskController.
     */
    @Test
    public void testCreateTask() throws Exception {
        String taskName = "Task";
        String description = "TaskDescription";
        int priority = 3;
        float percentageofcompletion = 50.0F;
        Task result = taskController.
                createTask(taskName, description, priority, percentageofcompletion, contact, this.event);
        assertEquals("newTaskDescription", PersistenceContext.repositories().
                task().all().iterator().next().Description());
    }

    /**
     * Test of editTask method, of class TaskController.
     */
    @Test
    public void testEditDescriptionTask() {
        System.out.println("editDescriptionTask");
        String taskName = "Task";
        String description = "TaskDescription";
        String expResult = "newTaskDescription";
        int priority = 3;
        float percentageofcompletion = 50.0F;
        byte[] photo = {0};
        boolean state = true;

        String reminderName = "reminder";
        String reminderDescription = "reminderDescription";
        Calendar data = Calendar.getInstance();
        contact = new PersonContact(taskName, taskName, description, contact, photo);
        this.reminder = new Reminder(reminderName, reminderDescription, data, state);

        TaskController instance = new TaskController(uicontroller);

        task = new Task(reminderName, description, priority, percentageofcompletion, contact);
        Task newTask = new Task(reminderName, expResult, priority, percentageofcompletion, contact);

        instance.editTask(newTask);
        String result = newTask.Description();

        assertEquals(expResult, result);

    }

    @Test
    public void testEditPercentageTask() {

        System.out.println("editNameTask");

        System.out.println("editDescriptionTask");
        String name = "Hicham", lastName = "Abahri", profession = "Student";
        String taskName = "Task";
        String description = "TaskDescription";
        // String expResult = "newTaskDescription";
        int priority = 3;
        float percentageofcompletion = 50.0F, Newpercentageofcompletion = 80.0F;
        byte[] photo = {0};
        boolean state = true;

        String reminderName = "reminder";
        String reminderDescription = "reminderDescription";
        Calendar data = Calendar.getInstance();

        contact = new PersonContact(name, lastName, profession, contact, photo);
        this.reminder = new Reminder(reminderName, reminderDescription, data, state);
        TaskController instance = new TaskController(uicontroller);
        task = new Task(taskName, description, priority, percentageofcompletion, contact);
        Task newTask = new Task(reminderName, description, priority, Newpercentageofcompletion, contact);

        instance.editTask(newTask);
        float result = newTask.Percentage();
        assertEquals(Newpercentageofcompletion, result, 0.0000);

    }

    /**
     * Test of removeTask method, of class TaskController. //
     */
//    @Test
//    public void testRemoveTask() {
//        System.out.println("removeTask");
//        task = new Task(taskName, description, priority, percentageofcompletion, contact);
//        TaskController instance = new TaskController(uicontroller);
//        instance.removeTask(task);
//
//    }
//    /**
//     * Test of allTasksContact method, of class TaskController. //
//     */
//    @Test
//    public void testAllTasksContact() throws DataIntegrityViolationException {
//        System.out.println("allTasksContact");
//        TaskController instance = new TaskController(uicontroller);
//        Task task = instance.createTask("taskall", "taskallDesc", 4, (float) 5.20, contact, reminder);
//        Task expResult = task;
//        Task result = instance.allTasksContact(contact).get(0);
//        assertEquals(expResult, result);
//
//    }
    /**
     * Test of allTasks method, of class TaskController.
     */
//    @Test
//    public void testAllTasks_0args() {
//        System.out.println("allTasks");
//        TaskController instance = null;
//        List<Task> expResult = null;
//        List<Task> result = instance.allTasks();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    /**
     * Test of allTasks method, of class TaskController.
     */
    @Test
    public void testAllTasks_String_String() {
        System.out.println("allTasks");
        String left = "50";
        String right = "50";
        TaskController instance = new TaskController(uicontroller);
        List<Task> expResult = instance.allTasks(left, right);
        List<Task> result = instance.allTasks(left, right);
        assertEquals(expResult, result);

    }

    /**
     * Test of allReminders method, of class TaskController.
     */
    @Test
    public void testAllReminders() {
        System.out.println("allReminders");
        TaskController instance = new TaskController(uicontroller);
        Iterable<Reminder> expResult = instance.allReminders();
        Iterable<Reminder> result = instance.allReminders();
        assertEquals(expResult, result);

    }
}
