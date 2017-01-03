/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.importExportData.ui;

import csheets.ui.ctrl.BaseAction;
import csheets.ui.ctrl.UIController;
import java.awt.event.ActionEvent;

/**
 *
 * @author Hicham Abahri 1141042@isep.ipp.pt
 */
public class DisableEnableUpdateAction extends BaseAction {

    private UIController uiController;
    private ImportExportTextFileController controller;

    public DisableEnableUpdateAction(UIController uiController, ImportExportTextFileController controller) {
        this.uiController = uiController;
        this.controller = controller;
    }

    @Override
    protected String getName() {
        return "Enable or Disabel automatically update";
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        new DisableEnableUpdate(null, enabled, uiController, controller).setVisible(true);
    }

}
