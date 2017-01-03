/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.importExportData.databaseExportImport;

import csheets.CleanSheets;
import csheets.core.Cell;
import csheets.core.Spreadsheet;
import csheets.core.Workbook;
import csheets.core.formula.compiler.FormulaCompilationException;
import csheets.ui.ctrl.UIController;
import csheets.ui.sheet.SpreadsheetTable;
import static org.junit.Assert.assertArrayEquals;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * Most important tests on Controller are get headers and get content from selected 
 * cells, other methods requires selected cells and focus owner which need UI components and 
 * break jenkins build, if I have time will revisit tests and get 100% coverage on controller
 * @author ab
 */
public class ImportExportToDatabaseControllerTest {

    CleanSheets app;
    UIController uiController;
    ImportExportToDatabaseController controller;
    Spreadsheet s;
    String tableName = "ImportExportToDatabaseControllerTest";
    String driver = "org.h2.Driver";
    String name = "jdbc:h2:./db/csheets";
    String username = "";
    String password = "";

    public ImportExportToDatabaseControllerTest() {
        uiController = UIController.getUIController();
        controller = new ImportExportToDatabaseController(uiController);
        Workbook wb = new Workbook(2);
        String[][] content = new String[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                content[i][j] = "";
            }
        }
        wb.addSpreadsheet(content);
        s = wb.getSpreadsheet(wb.getSpreadsheetCount() - 1);
    }

    /**
     * Test of getHeader method, of class ImportExportToDatabaseController.
     */
    @Test
    public void testGetHeader() throws FormulaCompilationException {
        String[] expectedResult = new String[4];
        expectedResult[0] = "header1";
        expectedResult[1] = "header2";
        expectedResult[2] = "header3";
        expectedResult[3] = "header4";
        Cell[][] cells = new Cell[2][4];
        cells[0][0] = s.getCell(0, 0);
        cells[0][0].setContent(expectedResult[0]);
        cells[0][1] = s.getCell(0, 1);
        cells[0][1].setContent(expectedResult[1]);
        cells[0][2] = s.getCell(0, 2);
        cells[0][2].setContent(expectedResult[2]);
        cells[0][3] = s.getCell(0, 3);
        cells[0][3].setContent(expectedResult[3]);
        assertArrayEquals(expectedResult, controller.getHeader(cells));
    }

    /**
     * Test of getContent method, of class ImportExportToDatabaseController.
     */
    @Test
    public void testGetContent() throws FormulaCompilationException {
        String[][] headers = new String[1][2];
        headers[0][0] = "header1";
        headers[0][1] = "header2";
        String[][] expectedResult = new String[2][2];
        expectedResult[0][0] = "content1";
        expectedResult[0][1] = "content2";
        expectedResult[1][0] = "content3";
        expectedResult[1][1] = "content4";
        Cell[][] cells = new Cell[3][2];
        cells[0][0] = s.getCell(0, 0);
        cells[0][0].setContent(headers[0][0]);
        cells[0][1] = s.getCell(0, 1);
        cells[0][1].setContent(headers[0][1]);
        cells[1][0] = s.getCell(1, 0);
        cells[1][0].setContent(expectedResult[0][0]);
        cells[1][1] = s.getCell(1, 1);
        cells[1][1].setContent(expectedResult[0][1]);
        cells[2][0] = s.getCell(2, 0);
        cells[2][0].setContent(expectedResult[1][0]);
        cells[2][1] = s.getCell(2, 1);
        cells[2][1].setContent(expectedResult[1][1]);
        assertArrayEquals(expectedResult, controller.getContent(cells));
    }

}
