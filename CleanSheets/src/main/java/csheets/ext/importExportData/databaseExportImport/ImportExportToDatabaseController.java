/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.importExportData.databaseExportImport;

import csheets.core.Cell;
import csheets.core.formula.compiler.FormulaCompilationException;
import csheets.support.DatabaseConnector;
import csheets.ui.ctrl.UIController;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 *
 * @author AB-1140280
 */
public class ImportExportToDatabaseController {

    UIController uiController;
    DatabaseConnector connector;

    public ImportExportToDatabaseController(UIController uiController) {
        this.uiController = uiController;
         connector = DatabaseConnector.getInstance();
    }

    public void exportToDatabase(String driver, String connection,
            String username, String password,
            String tableName) throws SQLException, FormulaCompilationException {
        connector.setConnection(driver, connection, username, password);
        Cell[][] cells = getSelectedCells();
        String[] headers = getHeader(cells);
        String[][] content = getContent(cells);
        connector.createTable(tableName, headers);
        connector.saveData(tableName, content);
    }

    public void importFromDatabase(String driver, String connection,
            String username, String password,
            String tableName) throws SQLException, FormulaCompilationException {
        connector.setConnection(driver, connection, username, password);
        Cell[][] cells = getSelectedCells();
        ResultSet result = connector.getTableData(tableName);
        setCellContent(cells, result);
    }

    public void setCellContent(Cell[][] cells, ResultSet result) throws SQLException, FormulaCompilationException {
        ResultSetMetaData rsmd = result.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        for (int i = 0; i < cells[0].length; i++) {
            if (columnsNumber <= i) {
                break;
            }
            cells[0][i].setContent(rsmd.getColumnName(i + 1));
        }
        int j = 1;
        while (result.next()) {
            for (int i = 0; i <= cells.length; i++) {
                if (columnsNumber <= i) {
                    break;
                }
                String str = result.getString(i + 1);
                cells[j][i].setContent(str);
            }
            j++;
        }
    }

    public Cell[][] getSelectedCells() {
        Cell[][] cells = uiController.focusOwner.getSelectedCells();
        if (cells.length != 0 || cells[0].length != 0) {
            return cells;
        }
        cells = new Cell[24][24];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                cells[i][j] = uiController.getActiveSpreadsheet().getCell(i, j);
            }
        }
        return uiController.focusOwner.getSelectedCells();
    }

    public String[] getHeader(Cell[][] cells) {
        String[] headers = new String[cells[0].length];
        for (int i = 0; i < cells[0].length; i++) {
            headers[i] = cells[0][i].getContent();
            if (headers[i].equals("")) {
                headers[i] = "default_header_" + i;
            }
        }
        return headers;
    }

    public String[][] getContent(Cell[][] cells) {
        String[][] content = new String[cells.length - 1][cells[0].length];
        for (int i = 1; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                content[i - 1][j] = cells[i][j].getContent();
            }
        }
        return content;
    }

}
