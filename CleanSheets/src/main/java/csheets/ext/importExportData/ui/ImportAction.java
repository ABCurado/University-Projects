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
 * @author Rui Bastos
 */
class ImportAction extends BaseAction {

	private UIController uiController;
	private ImportExportTextFileController controller;

	public ImportAction(UIController uiController,
						ImportExportTextFileController controller) {
		this.uiController = uiController;
		this.controller = controller;
	}

	@Override
	protected String getName() {
		return "Import text file";
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		new ImportDialog(null, true, uiController, controller).setVisible(true);
	}

}
