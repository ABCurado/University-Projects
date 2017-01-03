package csheets.ext.sort.ui;

import com.sun.glass.events.KeyEvent;
import csheets.CleanSheets;
import csheets.ui.ctrl.UIController;
import javax.swing.ImageIcon;
import javax.swing.JMenu;

/**
 * Represents the UI extension Menu of Sort Extension
 */
public class SortMenu extends JMenu {

    public SortMenu(UIController uiController) {
        super("Sort");

        setMnemonic(KeyEvent.VK_E);
        this.setIcon(new ImageIcon(CleanSheets.class.
                getResource("ext/sort/sort_icon_2.png")));

        add(new SortAction(uiController));
        add(new RangedSortAction(uiController));
        add(new AutomaticSortAction(uiController));
    }

}
