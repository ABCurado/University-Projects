/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.sort.ui;

import csheets.CleanSheets;
import csheets.core.Cell;
import csheets.core.Spreadsheet;
import csheets.core.formula.compiler.FormulaCompilationException;
import csheets.ext.sort.AutomaticSortService;
import csheets.ext.sort.SortController;
import csheets.ext.style.StylableCell;
import csheets.ext.style.StyleExtension;
import csheets.ui.ctrl.BaseAction;
import csheets.ui.ctrl.SelectionEvent;
import csheets.ui.ctrl.SelectionListener;
import csheets.ui.ctrl.UIController;
import csheets.ui.sheet.SpreadsheetTable;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.Action.MNEMONIC_KEY;
import static javax.swing.Action.SMALL_ICON;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author scarl
 */
public class AutomaticSortAction extends BaseAction {

    /**
     * User Interface Controller
     */
    protected UIController uiController;
    /**
     * Range of Cells
     */
    private Cell[][] selectedCells;

    public AutomaticSortAction(UIController uiController) {
        this.uiController = uiController;
    }

    /**
     * @return name extension.
     */
    @Override
    protected String getName() {
        return "Automatic sort";
    }

    @Override
    protected void defineProperties() {
        putValue(MNEMONIC_KEY, KeyEvent.VK_F);
        putValue(SMALL_ICON, new ImageIcon(CleanSheets.class.
                getResource("ext/sort/sort_icon_2.png")));
        setEnabled(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        selectedCells = uiController.focusOwner.getSelectedCells();
        //put background color diferent
        for (int i = 0; i < selectedCells.length; i++) {
            for (int j = 0; j < selectedCells[0].length; j++) {
                StylableCell cell = (StylableCell) selectedCells[i][j].getExtension(StyleExtension.NAME);
                cell.setBackgroundColor(Color.yellow);
            }
        }
        AutomaticSortService autoService = new AutomaticSortService(uiController, selectedCells);
        uiController.addSelectionListener(autoService);
        uiController.focusOwner.addMouseListener(autoService);
        uiController.focusOwner.revalidate();
        uiController.focusOwner.repaint();
    }

}
