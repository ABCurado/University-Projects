package csheets.ext.cellsSharing.ui;

import csheets.ui.ctrl.BaseAction;
import csheets.ui.ctrl.UIController;
import java.awt.event.ActionEvent;

class OptionsAction extends BaseAction {

    /**
     * The user interface controller
     */
    protected UIController uiController;
    
    /**
     * The share cells controller.
     */
    private final ShareCellsController shareController;

    /**
     * Creates a new action.
     *
     * @param uiController the user interface controller
     */
    public OptionsAction(UIController uiController, ShareCellsController shareController) {
        this.uiController = uiController;
        this.shareController = shareController;
    }

    @Override
    protected String getName() {
        return "Options";
    }

    @Override
    protected void defineProperties() {
    }

    /**
     * A simple action that presents a confirmation dialog. If the user confirms
     * then the contents of the cell A1 of the current sheet are set to the
     * string "Changed".
     *
     * @param event the event that was fired
     */
    public void actionPerformed(ActionEvent event) {
        OptionsUI options = new OptionsUI(uiController, shareController);
        options.run();
    }

}
