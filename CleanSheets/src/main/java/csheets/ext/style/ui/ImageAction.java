/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.style.ui;

import csheets.core.Cell;
import csheets.ext.style.StylableCell;
import csheets.ext.style.StyleExtension;
import csheets.ui.ctrl.FocusOwnerAction;
import csheets.ui.ctrl.UIController;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

/**
 *
 * @author scarl
 */
public class ImageAction extends FocusOwnerAction {

    /**
     * The user interface controller
     */
    protected UIController uiController;

    public ImageAction(UIController uiController) {
        this.uiController = uiController;
    }

    @Override
    protected String getName() {
        return "Image...";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (focusOwner == null) {
            return;
        }
        // Changes the border of each selected cell
        Cell cell = focusOwner.getSelectedCell();

        StylableCell stylableCell = (StylableCell) cell.
                getExtension(StyleExtension.NAME);
        stylableCell.setImage(new ImageIcon(StyleExtension.class.getResource("res/img/seta-para-baixo.gif")));

        uiController.setWorkbookModified(focusOwner.getSpreadsheet().getWorkbook());
        focusOwner.repaint();
    }
}
