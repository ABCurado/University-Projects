package csheets.ext.sort;

import csheets.core.Cell;
import csheets.core.Spreadsheet;
import csheets.core.Workbook;
import csheets.core.formula.compiler.FormulaCompilationException;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Renato Machado
 */
public class SortServiceTest {

    public SortServiceTest() {
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
     * Test of sortRangeOfCells method, of class SortService.
     *
     * @throws csheets.core.formula.compiler.FormulaCompilationException
     */
    @Test
    public void testSortRangeOfCellsInAscendingAndDescendingOrder() throws FormulaCompilationException {
        Workbook wb = new Workbook(1);
        Spreadsheet ss = wb.getSpreadsheet(0);

        ss.getCell(1, 0).setContent("12");
        ss.getCell(1, 1).setContent("14");

        ss.getCell(2, 0).setContent("15");
        ss.getCell(2, 1).setContent("11");

        Cell[][] cells = new Cell[][]{
            {ss.getCell(1, 0), ss.getCell(2, 0)},
            {ss.getCell(1, 1), ss.getCell(2, 1)},
        };

        SortService service = new SortService();

        // Sorting in descending order.
        Cell[][] sorted = service.sortRangeOfCells(cells, 1, false);

        assertEquals("the first cell should be equal to 14", sorted[0][0].getValue().toString(), "14");
        assertEquals("the lines of the first column were swapped, so the first cell of the second column should equal to the value of the swapped line",
                sorted[0][1].getValue().toString(), "11");
        
        // Sorting in ascending order.
        sorted = service.sortRangeOfCells(cells, 1, true);
        
        assertEquals("the first cell should be equal to 12", sorted[0][0].getValue().toString(), "12");
        assertEquals("Since the selected column was not sorted, the cells should remain equal",
                sorted[0][1].getValue().toString(), "15");
        
        // Sorting the second column in ascending order.
        sorted = service.sortRangeOfCells(cells, 2, true);
        
        assertEquals("the first cell should be equal to 14 because 11 was swapped with 15.", sorted[0][0].getValue().toString(), "14");
        assertEquals("Since this is ascending order, 15 will swap with 11.",
                sorted[0][1].getValue().toString(), "11");
    }
    

}
