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
public class ImportDatabaseAction extends BaseAction {

	UIController uiController;

	public ImportDatabaseAction(UIController uiController) {
		this.uiController = uiController;
	}

	@Override
	protected String getName() {
		return "Import cells from a database";
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new ImportExportToDatabaseFrame(uiController, ImportExportToDatabaseFrame.ExportImportOption.IMPORT).
			setVisible(true);
	}

}
