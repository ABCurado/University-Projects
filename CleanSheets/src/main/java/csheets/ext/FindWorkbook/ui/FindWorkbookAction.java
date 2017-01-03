/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.FindWorkbook.ui;

import csheets.ui.FileChooser;
import csheets.ui.ctrl.BaseAction;
import csheets.ui.ctrl.UIController;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Carlos Mateus
 */
public class FindWorkbookAction extends BaseAction {

	/**
	 * The user interface controller
	 */
	protected UIController uiController;

	/**
	 * Find workbook controller.
	 */
	private FindWorkbookController findWorkbookController;

	/**
	 * Creates a new action.
	 *
	 * @param uiController the user interface controller
	 * @param findWorkbookController find workbook controller
	 */
	public FindWorkbookAction(UIController uiController,
							  FindWorkbookController findWorkbookController) {
		this.uiController = uiController;
		this.findWorkbookController = findWorkbookController;
	}

	@Override
	protected String getName() {
		return "Find Workbook";
	}

	@Override
	protected void defineProperties() {
	}

	/**
	 *
	 * @param event event
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
//		FindWorkbookUI findWorkbook = new FindWorkbookUI(uiController, findWorkbookController);
//		findWorkbook.run();
//	}

		FileChooser chooser = new FileChooser(null, null);
		chooser.setFileSelectionMode(FileChooser.DIRECTORIES_ONLY);
		chooser.showDialog(null, null);
		findWorkbookController = new FindWorkbookController();
		List<File> lst = findWorkbookController.
			findWorkbook(chooser.getSelectedFile(), ".*\\.cls");

		if (lst.isEmpty()) {
			JOptionPane.
				showMessageDialog(null, "The directory don´t have workbooks files!",
								  "Workbooks files not Found", JOptionPane.INFORMATION_MESSAGE);
		} else {
			FindWorkbookResults window = new FindWorkbookResults(uiController);
			window.run();
			window.fillList(lst);
		}
	}
}
