package csheets.ext.sort;

import csheets.core.Cell;
import csheets.core.Spreadsheet;
import csheets.core.Value;
import csheets.core.Workbook;
import csheets.core.formula.compiler.FormulaCompilationException;
import csheets.ui.ctrl.UIController;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Core 3.2 Tests
 */
public class SortControllerTest {

    private SortController controller;
    private Workbook wb;
    private Spreadsheet ss;
    private List<Value> list = new ArrayList<>();
    Value value1 = new Value(300);
    Value value2 = new Value("ola");
    Value value3 = new Value(Boolean.TRUE);
    Value value4 = new Value(6);
    Value value5 = new Value(4.30);
    Value value6 = new Value("adeus");
    Value value7 = new Value(Boolean.FALSE);

    public SortControllerTest() {
    }

    @Before
    public void setUp() {

        UIController uiController = UIController.getUIController();
        list.add(value1);
        list.add(value2);
        list.add(value3);
        list.add(value4);
        list.add(value5);
        list.add(value6);
        list.add(value7);
//		wb = new Workbook(3);
//		ss = wb.getSpreadsheet(1);
//		try {
//			ss.getCell(0, 0).setContent("a");
//			ss.getCell(0, 1).setContent("d");
//			ss.getCell(0, 2).setContent("f");
//			ss.getCell(0, 3).setContent("c");
//			ss.getCell(0, 4).setContent("b");
//			ss.getCell(0, 5).setContent("g");
//			ss.getCell(0, 6).setContent("e");
//		} catch (Exception e) {
//			//handling
//		}
        controller = new SortController(uiController);
    }

    /**
     * Test of order method, of class SortController.
     */
    @Test
    public void testAscendingOrder() {
        System.out.println("ascending");
        controller.setValueList(list);
        controller.order(0); //asc
        List<Value> expResult = new ArrayList<>();
        expResult.add(value5);
        expResult.add(value4);
        expResult.add(value1);
        expResult.add(value6);
        expResult.add(value2);
        expResult.add(value7);
        expResult.add(value3);
        assertEquals(expResult, list);
    }

    @Test
    public void testDescendingOrder() {
        System.out.println("descending");
        controller.setValueList(list);
        controller.order(1);
        List<Value> expResult = new ArrayList<>();
        expResult.add(value3);
        expResult.add(value7);
        expResult.add(value2);
        expResult.add(value6);
        expResult.add(value1);
        expResult.add(value4);
        expResult.add(value5);
        assertEquals(expResult, list);

    }

    @Test
    public void test_sort_range_of_cells_with_only_one_cell_selected() {
        Workbook wb = new Workbook(1);
        Spreadsheet ss = wb.getSpreadsheet(0);

        try {
            ss.getCell(0, 0).setContent("12");
        } catch (FormulaCompilationException ex) {
            Logger.getLogger(SortControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        Cell[][] cells = new Cell[][]{
            { ss.getCell(0, 0) },
        };
        
        Cell[][] sorted = controller.sortRangeOfCells(cells, 1, true);
        
        assertTrue(sorted.length == 1);
        assertTrue(sorted[0].length == 1);
        assertTrue(sorted[0][0].getContent().equals(cells[0][0].getContent()));
    }
    
    @Test
    public void test_sort_range_of_cells_with_muliple_columns_selected_and_one_line() {
        Workbook wb = new Workbook(1);
        Spreadsheet ss = wb.getSpreadsheet(0);

        try {
            ss.getCell(0, 0).setContent("12");
            ss.getCell(1, 0).setContent("13");
            ss.getCell(2, 0).setContent("14");
        } catch (FormulaCompilationException ex) {
            Logger.getLogger(SortControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        Cell[][] cells = new Cell[][] {
            { ss.getCell(0, 0), ss.getCell(1, 0), ss.getCell(2, 0) }
        };

        Cell[][] sorted = controller.sortRangeOfCells(cells, 1, true);

        assertTrue(sorted.length == 1);
        assertTrue(sorted[0].length == 3);
        
        for (int i = 0; i < 3; i++) {
            assertTrue(sorted[0][i].getContent().equals(cells[0][i].getContent()));
        }
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void test_invalid_column_is_handled()
    {
        controller.sortRangeOfCells(new Cell[][] {}, -2, true);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void test_handle_null_cells() {
        controller.sortRangeOfCells(null, 1, true);
    }

}
