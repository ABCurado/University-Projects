/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.importExportData.databaseExportImport;

import csheets.ui.ctrl.BaseAction;
import csheets.ui.ctrl.UIController;
import java.awt.event.ActionEvent;

/**
 *
 * @author AB
 */
public class ExportDatabaseAction extends BaseAction {

	UIController uiController;

	public ExportDatabaseAction(UIController uiController) {
		this.uiController = uiController;
	}

	@Override
	protected String getName() {
		return "Export cells to a database";
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new ImportExportToDatabaseFrame(uiController, ImportExportToDatabaseFrame.ExportImportOption.EXPORT).
			setVisible(true);
	}

}
