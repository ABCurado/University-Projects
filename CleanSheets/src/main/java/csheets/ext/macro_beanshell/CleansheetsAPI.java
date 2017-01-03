package csheets.ext.macro_beanshell;

import csheets.core.Address;
import csheets.core.Cell;
import csheets.core.Spreadsheet;
import csheets.core.Value;
import csheets.core.Workbook;
import csheets.core.formula.VariableArray;
import csheets.ui.FormEditor.ui.FormE;
import csheets.ui.ctrl.UIController;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.Stack;

/**
 * This class is a simple Facade object that provides a straight forward API to Cleansheets objects.
 * 
 * @author Renato Machado
 */
public class CleansheetsAPI {
    
    /**
     * User Interface Controller.
     */
    private final UIController uiController;
    
    public CleansheetsAPI(UIController uiController)
    {
        this.uiController = uiController;
    }
    
    /**
     * Adds a new spreadsheet to the active workbook.
     */
    public void addSpreadsheet() {
        this.uiController.getActiveWorkbook().addSpreadsheet();
    }

    /**
     * Adds a new spreadsheet to the active workbook.
     *
     * @param content Content to be added to the cells.
     */
    public void addSpreadsheet(String[][] content) {
        this.uiController.getActiveWorkbook().addSpreadsheet(content);
    }

    /**
     * Gets a spreadsheet from the active workbook.
     *
     * @param index Spreadsheet index (starts at 0).
     * @return Spreadsheet.
     */
    public Spreadsheet getSpreadsheet(int index) {
        return this.uiController.getActiveWorkbook().getSpreadsheet(index);
    }

    /**
     * Counts the number of spreadsheets on the active workbook.
     *
     * @return Number of spreadsheets.
     */
    public int getSpreadsheetCount() {
        return this.uiController.getActiveWorkbook().getSpreadsheetCount();
    }

    /**
     * Removes a given spreadsheet from the active workbook.
     *
     * @param spreadsheet Spreadsheet.
     */
    public void removeSpreadsheet(Spreadsheet spreadsheet) {
        this.uiController.getActiveWorkbook().removeSpreadsheet(spreadsheet);
    }

    /**
     * Gets all of the spreadsheets from the active workbook.
     *
     * @return Spreadsheets.
     */
    public Iterator<Spreadsheet> spreadsheets() {
        return this.uiController.getActiveWorkbook().iterator();
    }

    /**
     * Adds a script to the active workbook.
     *
     * @param code Script
     */
    public void addScript(Code code) {
        this.uiController.getActiveWorkbook().addScript(code);
    }

    /**
     * Gets all of the scripts from the active workbook.
     *
     * @return Scripts
     */
    public List<Code> getScripts() {
        return this.uiController.getActiveWorkbook().getScripts();
    }

    /**
     * Gets a script from the active workbook.
     *
     * @param name Script name
     * @return Script
     */
    public Code getScript(String name) {
        return this.uiController.getActiveWorkbook().getScript(name);
    }

    /**
     * Clears all the scripts from the active workbook.
     */
    public void clearScripts() {
        this.uiController.getActiveWorkbook().clearScripts();
    }

    /**
     * Adds a variable to the active workbook.
     *
     * @param variable Variable
     */
    public void addVariable(VariableArray variable) {
        this.uiController.getActiveWorkbook().addVariable(variable);
    }

    /**
     * Gets all variables from the active workbook.
     *
     * @return All variables
     */
    public List<VariableArray> getAllVariables() {
        return this.uiController.getActiveWorkbook().getAllVariables();
    }

    /**
     * Gets a variable from the active workbook.
     *
     * @param name Variable Name
     * @return Variable
     */
    public VariableArray getVariable(String name) {
        return this.uiController.getActiveWorkbook().getVariable(name);
    }

    /**
     * Get a variable value.
     *
     * @param name Variable Name.
     * @param position Position.
     * @return Variable value.
     */
    public Value getVariableValue(String name, int position) {
        return this.uiController.getActiveWorkbook().getVariableValue(name, position);
    }

    /**
     * Updates a variable's value.
     *
     * @param variable Variable Name.
     * @param value New Value.
     * @param position Position.
     */
    public void updateValue(String variable, Value value, int position) {
        this.uiController.getActiveWorkbook().updateValue(variable, value, position);
    }

    /**
     * Clears all variables from the active workbook.
     */
    public void clearVariables() {
        this.uiController.getActiveWorkbook().clearVariables();
    }

    /**
     * Gets the parent file name.
     *
     * @return File name.
     */
    public String getParentFileName() {
        return this.uiController.getActiveWorkbook().getParentFileName();
    }

    /**
     * Sets the parent file name.
     *
     * @param name File name.
     */
    public void setParentFileName(String name) {
        this.uiController.getActiveWorkbook().setParentFileName(name);
    }

    /**
     * Gets a form from the active workbook.
     *
     * @param name Form name.
     * @return Form.
     */
    public FormE getForm(String name) {
        return this.uiController.getActiveWorkbook().getForm(name);
    }

    /**
     * Clears all the forms from the active workbook.
     */
    public void clearForms() {
        this.uiController.getActiveWorkbook().clearForms();
    }

    /**
     * Gets the active workbook.
     *
     * @return Active workbook.
     */
    public Workbook workbook() {
        return this.uiController.getActiveWorkbook();
    }

    /**
     * Gets a cell from the active spreadsheet.
     *
     * @param address Cell address.
     * @return Cell.
     */
    public Cell getCell(Address address) {
        return this.uiController.getActiveSpreadsheet().getCell(address);
    }

    /**
     * Gets a cell from the active spreadsheet.
     *
     * @param column Column index.
     * @param row Row index.
     * @return Cell.
     */
    public Cell getCell(int column, int row) {
        return this.uiController.getActiveSpreadsheet().getCell(column, row);
    }

    /**
     * Returns a set of Cells between the addresses of the active spreadsheet.
     *
     * @param ad1 Address 1.
     * @param ad2 Address 2.
     * @return Set of Cells.
     */
    public SortedSet<Cell> getCells(Address ad1, Address ad2) {
        return this.uiController.getActiveSpreadsheet().getCells(ad1, ad2);
    }

    /**
     * Gets all the cells of the active spreadsheet.
     *
     * @return Cells.
     */
    public SortedSet<Cell> getCells() {
        return this.uiController.getActiveSpreadsheet().getCells();
    }

    /**
     * Gets all of the cells of a given column from the active spreadsheet.
     *
     * @param index Column index.
     * @return Cells.
     */
    public Cell[] getColumn(int index) {
        return this.uiController.getActiveSpreadsheet().getColumn(index);
    }

    /**
     * Counts the number of columns in the active spreadsheet.
     *
     * @return Number of columns in the active spreadsheet.
     */
    public int getColumnCount() {
        return this.uiController.getActiveSpreadsheet().getColumnCount();
    }

    /**
     * Gets all of the cells of a given row from the active spreadsheet.
     *
     * @param index Row index.
     * @return Cells.
     */
    public Cell[] getRow(int index) {
        return this.uiController.getActiveSpreadsheet().getRow(index);
    }

    /**
     * Counts the number of rows in the active spreadsheet.
     *
     * @return Number of rows in the active spreadsheet.
     */
    public int getRowCount() {
        return this.uiController.getActiveSpreadsheet().getRowCount();
    }

    /**
     * Gets the title of the active spreadsheet.
     *
     * @return Title of the active spreadsheet.
     */
    public String getTitle() {
        return this.uiController.getActiveSpreadsheet().getTitle();
    }

    /**
     * Sets the title of the active spreadsheet.
     *
     * @param title Title of the active spreadsheet.
     */
    public void setTitle(String title) {
        this.uiController.getActiveSpreadsheet().setTitle(title);
    }

    /**
     * Clears the content of all selected cells.
     */
    public void clearSelectedCells() {
        this.uiController.focusOwner.clearSelectedCells();
    }

    /**
     * Gets the selected cell.
     *
     * @return Selected cell.
     */
    public Cell getSelectedCell() {
        return this.uiController.focusOwner.getSelectedCell();
    }

    /**
     * Gets the selected cells.
     *
     * @return Selected cells.
     */
    public Cell[][] getSelectedCells() {
        return this.uiController.focusOwner.getSelectedCells();
    }
    
    /**
     * Gets the selected column index.
     *
     * @return Column index.
     */
    public int getSelectedColumn() {
        return this.uiController.focusOwner.getSelectedColumn();
    }

    /**
     * Gets the number of cells in the selected column.
     *
     * @return Number of cells in the selected column.
     */
    public int getSelectedColumnCount() {
        return this.uiController.focusOwner.getSelectedColumnCount();
    }

    /**
     * Gets the selected columns indexes.
     *
     * @return Selected columns indexes.
     */
    public int[] getSelectedColumns() {
        return this.uiController.focusOwner.getSelectedColumns();
    }

    /**
     * Gets the selected row index.
     *
     * @return Selected row index.
     */
    public int getSelectedRow() {
        return this.uiController.focusOwner.getSelectedRow();
    }

    /**
     * Gets the number of cells in the selected row.
     *
     * @return Number of cells in the selected row.
     */
    public int getSelectedRowCount() {
        return this.uiController.focusOwner.getSelectedRowCount();
    }

    /**
     * Gets the selected rows indexes.
     *
     * @return Selected rows indexes.
     */
    public int[] getSelectedRows() {
        return this.uiController.focusOwner.getSelectedRows();
    }
    
    /**
     * Gets all of the workbooks currently open in Cleansheets.
     *
     * @return All Workbooks open in Cleansheets.
     */
    public Stack<Workbook> workbooks() {
        return this.uiController.workbooks();
    }
    
    /**
     * Gets all of the Macros in the active workbook.
     * 
     * @return Macros.
     */
    public List<Code> getMacros()
    {
        List<Code> macros = new ArrayList<>();
        
        for (Code script : this.getScripts()) {
            if (script.getType().equals("Macro")) {
                macros.add(script);
            }
        }
        
        return macros;
    }
    
    /**
     * Gets all of the BeanShell scripts in the active workbook.
     * 
     * @return BeanShell Scripts.
     */
    public List<Code> getBeanShellScripts()
    {
        List<Code> beanshell = new ArrayList<>();

        for (Code script : this.getScripts()) {
            if (script.getType().equals("BeanShell")) {
                beanshell.add(script);
            }
        }

        return beanshell;
    }
    
    /**
     * Gets a BeanShell script given its name.
     *
     * @param name Name of the script.
     * @return Macro, or throws an exception.
     */
    public Code getBeanShellScript(String name) 
    {
        for (Code script : this.getBeanShellScripts()) {
            if (script.getName().equals(name)) {
                return script;
            }
        }
        
        throw new IllegalArgumentException("No BeanShell script was found with the given name.");
    }
    
    /**
     * Gets a macro given its name.
     * 
     * @param name Name of the macro.
     * @return Macro, or throws an exception.
     */
    public Code getMacro(String name) {
        for (Code script : this.getMacros()) {
            if (script.getName().equals(name)) {
                return script;
            }
        }

        throw new IllegalArgumentException("No macro was found with the given name.");
    }
}
