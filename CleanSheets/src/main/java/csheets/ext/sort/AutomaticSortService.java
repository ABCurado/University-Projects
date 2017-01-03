/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.sort;

import csheets.core.Cell;
import csheets.core.Spreadsheet;
import csheets.core.formula.compiler.FormulaCompilationException;
import csheets.ext.sort.ui.RangedSortDialog;
import csheets.ext.style.StylableCell;
import csheets.ext.style.StyleExtension;
import csheets.ui.ctrl.SelectionEvent;
import csheets.ui.ctrl.SelectionListener;
import csheets.ui.ctrl.UIController;
import csheets.ui.sheet.SpreadsheetTable;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author scarl
 */
public class AutomaticSortService implements SelectionListener, MouseListener {

    protected UIController uiController;

    private final SortController controller;

    private Cell[][] selectedCells;

    int[] columns;

    int[] rows;

    boolean orders[];

    int column;

    Spreadsheet ss;

    public AutomaticSortService(UIController uiController, Cell[][] rangeOfCells) {
        this.uiController = uiController;
        this.controller = new SortController(uiController);
        this.selectedCells = rangeOfCells;
        this.columns = uiController.focusOwner.getSelectedColumns();
        this.rows = uiController.focusOwner.getSelectedRows();
        ss = uiController.getActiveSpreadsheet();
        column = columns[0];
        orders = new boolean[columns.length];
    }

    public AutomaticSortService(UIController uiController, Cell[][] rangeOfCells, int[] columns, int[] rows,Spreadsheet ss) {
        this.uiController = uiController;
        this.controller = new SortController(uiController);
        this.selectedCells = rangeOfCells;
        this.columns = columns;
        this.rows = rows;
        this.ss = ss;
        column = columns[0];
        orders = new boolean[columns.length];
    }

    @Override
    public void selectionChanged(SelectionEvent event) {
        if (event.getCell().getAddress().getColumn() >= columns[0] && event.getCell().getAddress().getColumn() <= columns[columns.length - 1]) {
            if (column != event.getCell().getAddress().getColumn()) {
                diferentColumn(event.getCell());
            }
            if (column == event.getCell().getAddress().getColumn()) {
                sameColumn(event.getCell());
            }
        } else {
            JOptionPane.showMessageDialog(uiController.getFrame(), "Select a column with a visual indicator", "Column Selection", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (column == ((SpreadsheetTable) e.getSource()).getSelectedCell().getAddress().getColumn()) {
            if (ss.getCell(column, rows[0]) == ((SpreadsheetTable) e.getSource()).getSelectedCell()) {
                sameColumn(((SpreadsheetTable) e.getSource()).getSelectedCell());
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //do nothing
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //do nothing
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //do nothing
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //do nothing
    }

    public void sortByColumn(int column, boolean order) {
        Cell[][] cells = this.controller.sortRangeOfCells(selectedCells, column, order);
        String[][] sorted = new String[cells.length][cells[0].length];

        // Brute-force copy.
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                sorted[i][j] = cells[i][j].getContent();
            }
        }
        for (int i = 0; i < columns.length; i++) {
            for (int j = 0; j < rows.length; j++) {
                try {
                    ss.getCell(columns[i], rows[j]).setContent(sorted[j][i]);
                } catch (FormulaCompilationException ex) {
                    Logger.getLogger(RangedSortDialog.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        uiController.focusOwner.revalidate();
        uiController.focusOwner.repaint();
    }

    public void sameColumn(Cell cell) {
        int atualColumn = column - columns[0];
        if (orders[atualColumn] == true) {
            orders[atualColumn] = false;
            StylableCell prevstylableCell = (StylableCell) ss.getCell(cell.getAddress().getColumn(), rows[0]).getExtension(StyleExtension.NAME);
            prevstylableCell.setImage(new ImageIcon());

            StylableCell stylableCell = (StylableCell) ss.getCell(cell.getAddress().getColumn(), rows[0]).
                    getExtension(StyleExtension.NAME);
            stylableCell.setImage(new ImageIcon(StyleExtension.class.getResource("res/img/seta-para-cima.gif")));
            sortByColumn(column, orders[atualColumn]);
        } else {
            orders[atualColumn] = true;
            StylableCell prevstylableCell = (StylableCell) cell.getExtension(StyleExtension.NAME);
            prevstylableCell.setImage(new ImageIcon());

            StylableCell stylableCell = (StylableCell) ss.getCell(cell.getAddress().getColumn(), rows[0]).
                    getExtension(StyleExtension.NAME);
            stylableCell.setImage(new ImageIcon(StyleExtension.class.getResource("res/img/seta-para-baixo.gif")));
            sortByColumn(column, orders[atualColumn]);
        }
    }

    public void diferentColumn(Cell cell) {
        int atualColumn = column - columns[0];
        orders[atualColumn] = true;
        StylableCell prevstylableCell = (StylableCell) ss.getCell(column, rows[0]).getExtension(StyleExtension.NAME);
        prevstylableCell.setImage(new ImageIcon());

        column = cell.getAddress().getColumn();
        StylableCell stylableCell = (StylableCell) ss.getCell(cell.getAddress().getColumn(), rows[0]).
                getExtension(StyleExtension.NAME);
        stylableCell.setImage(new ImageIcon(StyleExtension.class.getResource("res/img/seta-para-baixo.gif")));

        sortByColumn(column, orders[atualColumn]);
    }
}
