package csheets.support;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ThreadManagerTest {
    
    public ThreadManagerTest() {
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
     * Test of create method, of class ThreadManager.
     */
    @Test
    public void it_should_create_a_thread_and_store_it_and_delete_it() {
        final List<String> list = new ArrayList<>();
        
        Thread t1 = new Thread() {
            public void run() {
                synchronized (list) {
                    list.add("t1");
                }
            }
        };
        
        Thread t2 = new Thread() {
            public void run() {
                list.add("t1");
            }
        };
        
        ThreadManager.create("t1", t1);
        ThreadManager.create("t2", t2);
        
        ThreadManager.run("t1");
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        synchronized (list) {
            assertTrue("it should have 1 element", list.size() == 1);
        }
        
        ThreadManager.run("t2");
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        synchronized (list) {
            assertTrue("it should have 2 elements", list.size() == 2);
        }
        
        assertTrue("it should have the t1 key", ThreadManager.has("t1"));
        assertTrue("it should have the t1 thread", ThreadManager.get("t1").equals(t1));
        
        assertTrue("it should have the t2 key", ThreadManager.has("t2"));
        assertTrue("it should have the t2 thread", ThreadManager.get("t2").equals(t2));
        
        ThreadManager.destroy("t1");
        assertFalse("it should have deleted t1", ThreadManager.has("t1"));
        ThreadManager.destroy("t2");
        assertFalse("it should have deleted t2", ThreadManager.has("t2"));
    }
    
}
