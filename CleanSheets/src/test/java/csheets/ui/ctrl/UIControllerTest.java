/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ui.ctrl;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author Diogo Azevedo
 */
public class UIControllerTest {

	public UIControllerTest() {
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
//
//    /**
//     * Test of getActiveWorkbook method, of class UIController.
//     */
//    @Test
//    public void testGetActiveWorkbook() {
//        System.out.println("getActiveWorkbook");
//        UIController instance = null;
//        Workbook expResult = null;
//        Workbook result = instance.getActiveWorkbook();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setActiveWorkbook method, of class UIController.
//     */
//    @Test
//    public void testSetActiveWorkbook() {
//        System.out.println("setActiveWorkbook");
//        Workbook workbook = null;
//        UIController instance = null;
//        instance.setActiveWorkbook(workbook);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getActiveSpreadsheet method, of class UIController.
//     */
//    @Test
//    public void testGetActiveSpreadsheet() {
//        System.out.println("getActiveSpreadsheet");
//        UIController instance = null;
//        Spreadsheet expResult = null;
//        Spreadsheet result = instance.getActiveSpreadsheet();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setActiveSpreadsheet method, of class UIController.
//     */
//    @Test
//    public void testSetActiveSpreadsheet() {
//        System.out.println("setActiveSpreadsheet");
//        Spreadsheet spreadsheet = null;
//        UIController instance = null;
//        instance.setActiveSpreadsheet(spreadsheet);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getActiveCell method, of class UIController.
//     */
//    @Test
//    public void testGetActiveCell() {
//        System.out.println("getActiveCell");
//        UIController instance = null;
//        Cell expResult = null;
//        Cell result = instance.getActiveCell();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setActiveCell method, of class UIController.
//     */
//    @Test
//    public void testSetActiveCell() {
//        System.out.println("setActiveCell");
//        Cell cell = null;
//        UIController instance = null;
//        instance.setActiveCell(cell);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of isActiveWorkbookModified method, of class UIController.
//     */
//    @Test
//    public void testIsActiveWorkbookModified() {
//        System.out.println("isActiveWorkbookModified");
//        UIController instance = null;
//        boolean expResult = false;
//        boolean result = instance.isActiveWorkbookModified();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of isWorkbookModified method, of class UIController.
//     */
//    @Test
//    public void testIsWorkbookModified() {
//        System.out.println("isWorkbookModified");
//        Workbook workbook = null;
//        UIController instance = null;
//        boolean expResult = false;
//        boolean result = instance.isWorkbookModified(workbook);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setWorkbookModified method, of class UIController.
//     */
//    @Test
//    public void testSetWorkbookModified() {
//        System.out.println("setWorkbookModified");
//        Workbook workbook = null;
//        UIController instance = null;
//        instance.setWorkbookModified(workbook);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getCellTransferHandler method, of class UIController.
//     */
//    @Test
//    public void testGetCellTransferHandler() {
//        System.out.println("getCellTransferHandler");
//        UIController instance = null;
//        TransferHandler expResult = null;
//        TransferHandler result = instance.getCellTransferHandler();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getUserProperties method, of class UIController.
//     */
//    @Test
//    public void testGetUserProperties() {
//        System.out.println("getUserProperties");
//        UIController instance = null;
//        Properties expResult = null;
//        Properties result = instance.getUserProperties();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getExtensions method, of class UIController.
//     */
//    @Test
//    public void testGetExtensions() {
//        System.out.println("getExtensions");
//        UIController instance = null;
//        UIExtension[] expResult = null;
//        UIExtension[] result = instance.getExtensions();
//        assertArrayEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of changeExtensionState method, of class UIController.
//     */
//    @Test
//    public void testChangeExtensionState() {
//        System.out.println("changeExtensionState");
//        String ext = "";
//        boolean action = false;
//        UIController instance = null;
//        instance.changeExtensionState(ext, action);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of workbookCreated method, of class UIController.
//     */
//    @Test
//    public void testWorkbookCreated() {
//        System.out.println("workbookCreated");
//        SpreadsheetAppEvent event = null;
//        UIController instance = null;
//        instance.workbookCreated(event);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of workbookLoaded method, of class UIController.
//     */
//    @Test
//    public void testWorkbookLoaded() {
//        System.out.println("workbookLoaded");
//        SpreadsheetAppEvent event = null;
//        UIController instance = null;
//        instance.workbookLoaded(event);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of workbookUnloaded method, of class UIController.
//     */
//    @Test
//    public void testWorkbookUnloaded() {
//        System.out.println("workbookUnloaded");
//        SpreadsheetAppEvent event = null;
//        UIController instance = null;
//        instance.workbookUnloaded(event);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of workbookSaved method, of class UIController.
//     */
//    @Test
//    public void testWorkbookSaved() {
//        System.out.println("workbookSaved");
//        SpreadsheetAppEvent event = null;
//        UIController instance = null;
//        instance.workbookSaved(event);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of addSelectionListener method, of class UIController.
//     */
//    @Test
//    public void testAddSelectionListener() {
//        System.out.println("addSelectionListener");
//        SelectionListener listener = null;
//        UIController instance = null;
//        instance.addSelectionListener(listener);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of removeSelectionListener method, of class UIController.
//     */
//    @Test
//    public void testRemoveSelectionListener() {
//        System.out.println("removeSelectionListener");
//        SelectionListener listener = null;
//        UIController instance = null;
//        instance.removeSelectionListener(listener);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of addEditListener method, of class UIController.
//     */
//    @Test
//    public void testAddEditListener() {
//        System.out.println("addEditListener");
//        EditListener listener = null;
//        UIController instance = null;
//        instance.addEditListener(listener);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of removeEditListener method, of class UIController.
//     */
//    @Test
//    public void testRemoveEditListener() {
//        System.out.println("removeEditListener");
//        EditListener listener = null;
//        UIController instance = null;
//        instance.removeEditListener(listener);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    
}
