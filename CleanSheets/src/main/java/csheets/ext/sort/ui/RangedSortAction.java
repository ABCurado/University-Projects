package csheets.ext.sort.ui;

import csheets.CleanSheets;
import csheets.ui.ctrl.BaseAction;
import csheets.ui.ctrl.UIController;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import static javax.swing.Action.MNEMONIC_KEY;
import static javax.swing.Action.SMALL_ICON;
import javax.swing.ImageIcon;

/**
 * Range Sort Extension Action that interacts with the Active SpreadSheet.
 */
public class RangedSortAction extends BaseAction {

    /**
     * User Interface Controller.
     */
    protected UIController uiController;

    public RangedSortAction(UIController uiController) {
        this.uiController = uiController;
    }

    /**
     * @return name extension.
     */
    @Override
    protected String getName() {
        return "Sort range of selected columns";
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
        new RangedSortDialog(this.getName(), uiController).setVisible(true);
    }
}
