/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.sort;

import csheets.core.formula.compiler.FormulaCompilationException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author scarl
 */
public class AutomaticSortServiceTest {

    /*
    UIController uiController = new UIController(new CleanSheets());
    Workbook wb;
    Spreadsheet ss;
    Cell[][] rangeOfCells;
    int[] columns = {1, 2};
    int[] rows = {0, 1, 2};
     */
    public AutomaticSortServiceTest() {

    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws FormulaCompilationException {
        /*
        wb = new Workbook(1);
        ss = wb.getSpreadsheet(0);
        SpreadsheetTable sst = new SpreadsheetTable(ss, uiController);
        uiController.focusOwner = sst;
        uiController.setActiveSpreadsheet(ss);
        uiController.setActiveCell(ss.getCell(1, 0));

        ss.getCell(1, 0).setContent("Rafa");
        ss.getCell(1, 1).setContent("Joana");
        ss.getCell(1, 2).setContent("Ana");

        ss.getCell(2, 0).setContent("15");
        ss.getCell(2, 1).setContent("11");
        ss.getCell(2, 2).setContent("30");

        rangeOfCells = new Cell[][]{
            {ss.getCell(1, 0), ss.getCell(2, 0)},
            {ss.getCell(1, 1), ss.getCell(2, 1)},
            {ss.getCell(1, 2), ss.getCell(2, 2)},};
         */

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of sortByColumn method, of class AutomaticSortService.
     */
    /* @Test
    public void testSortByColumn() {
        
        System.out.println("sortByColumn");
        int column = 1;
        boolean order = false;
        AutomaticSortService instance = new AutomaticSortService(uiController, rangeOfCells);
        instance.sortByColumn(column, order);
        assertEquals("the first cell should be equal to Rafa", ss.getCell(1, 0).getValue().toString(), "Rafa");
        assertEquals("the first cell should be equal to 15", ss.getCell(2, 0).getValue().toString(), "15");
        assertEquals("the second cell should be equal to Joana", ss.getCell(1, 1).getValue().toString(), "Joana");
        assertEquals("the first cell should be equal to 11", ss.getCell(2, 1).getValue().toString(), "11");
        assertEquals("the second cell should be equal to Ana", ss.getCell(1, 2).getValue().toString(), "Ana");
        assertEquals("the first cell should be equal to 30", ss.getCell(2, 2).getValue().toString(), "30");
		 
		
	}
     */
    /**
     * Test of sameColumn method, of class AutomaticSortService.
     */
    /*
    @Test
    public void testSameColumn() {
        System.out.println("sameColumn");
        Cell cell = ss.getCell(1, 0);
        AutomaticSortService instance = new AutomaticSortService(uiController, rangeOfCells, columns, rows, ss);
        instance.sameColumn(cell);
        assertEquals("the first cell should be equal to Ana", ss.getCell(1, 0).getValue().toString(), "Ana");
        assertEquals("the first cell should be equal to 30", ss.getCell(2, 0).getValue().toString(), "30");
        assertEquals("the second cell should be equal to Joana", ss.getCell(1, 1).getValue().toString(), "Joana");
        assertEquals("the first cell should be equal to 11", ss.getCell(2, 1).getValue().toString(), "11");
        assertEquals("the second cell should be equal to Rafa", ss.getCell(1, 2).getValue().toString(), "Rafa");
        assertEquals("the first cell should be equal to 15", ss.getCell(2, 2).getValue().toString(), "15");
    }
     */
    /**
     * Test of diferentColumn method, of class AutomaticSortService.
     */
    /*
    @Test
    public void testDiferentColumn() {
        System.out.println("diferentColumn");
        Cell cell = ss.getCell(2, 0);
        AutomaticSortService instance = new AutomaticSortService(uiController, rangeOfCells, columns, rows, ss);
        instance.diferentColumn(cell);
        assertEquals("the first cell should be equal to Joana", ss.getCell(1, 0).getValue().toString(), "Joana");
        assertEquals("the first cell should be equal to 30", ss.getCell(2, 0).getValue().toString(), "11");
        assertEquals("the second cell should be equal to Rafa", ss.getCell(1, 1).getValue().toString(), "Rafa");
        assertEquals("the first cell should be equal to 15", ss.getCell(2, 1).getValue().toString(), "15");
        assertEquals("the second cell should be equal to Ana", ss.getCell(1, 2).getValue().toString(), "Ana");
        assertEquals("the first cell should be equal to 15", ss.getCell(2, 2).getValue().toString(), "30");
    }
     */
}
