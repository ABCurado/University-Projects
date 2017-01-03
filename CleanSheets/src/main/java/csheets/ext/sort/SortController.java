package csheets.ext.sort;

import csheets.core.Cell;
import csheets.core.Value;
import csheets.core.formula.compiler.FormulaCompilationException;
import csheets.ui.ctrl.UIController;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Sort Controller.
 */
public class SortController {

    /**
     * User Interface controller.
     */
    private UIController uiController;

    /**
     * Sorting List.
     */
    private List<Value> valueList = new ArrayList<>();

    /**
     * Column index.
     */
    private int index;

    public SortController(UIController controller) {
        this.uiController = controller;
    }

    /**
     * Set Column Index.
     *
     * @param index index
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * Assign column values to valueList.
     */
    public void updateValueList() {
        for (Cell c : this.uiController.getActiveSpreadsheet().
                getColumn(this.index)) {
            if (!c.getValue().toString().equals("")) {
                valueList.add(c.getValue());
            }
        }
    }

    /**
     * Set a new List.
     *
     * @param newList List.
     */
    public void setValueList(List<Value> newList) {
        this.valueList = newList;
    }

    /**
     * Value List Sorting.
     *
     * @param order order
     */
    public void order(int order) {
        if (order == 0) {
            Collections.sort(valueList);
        } else {
            Collections.sort(valueList);
            Collections.reverse(valueList);
        }
    }

    /**
     * Updates the working column.
     */
    public void updateColumn() {
        int j = 0;
        for (Cell c : this.uiController.getActiveSpreadsheet().
                getColumn(this.index)) {
            if (!c.getValue().toString().equalsIgnoreCase("")) {
                c.clear();
                try {
                    c.setContent(valueList.get(j).toString());
                } catch (FormulaCompilationException ex) {
                    Logger.getLogger(SortController.class.getName()).
                            log(Level.SEVERE, null, ex);
                }
                j++;
            }
        }
        valueList.clear();
    }

    /**
     * Gets the selected columns in the spreadsheet.
     * 
     * @return Selected columns.
     */
    public int[] getSelectedColumns() {
        return this.uiController.focusOwner.getSelectedColumns();
    }
    
    /**
     * Sorts the given cells in ascending or descending order based on their value type.
     * 
     * @param cells Cells to be sorted.
     * @param column Column to sort.
     * @param order The sort order.
     * @return Sorted cells.
     */
    public Cell[][] sortRangeOfCells(Cell[][] cells, int column, boolean order) {
        if (cells == null) {
            throw new IllegalArgumentException("The selected cells cannot be set to null.");
        }
        
        if (column < 0) {
            throw new IllegalArgumentException("A column can not have an index below 0.");
        }
        
        // If there is only one column with just one line, we don't have
        // anything to sort.
        if (cells.length == 1) {
            if (cells[0].length == 1) {
                return cells;
            }
        }
        
        // If there are multiple columns selected with only one line, then
        // we don't have anything to sort.
        if (cells[0].length == 1) {
            return cells;
        }
        
        return new SortService().sortRangeOfCells(cells, column, order);
    }
}
