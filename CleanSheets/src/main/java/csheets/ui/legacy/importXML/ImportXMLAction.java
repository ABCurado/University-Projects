/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ui.legacy.importXML;

import csheets.ui.ctrl.UIController;
import csheets.ui.legacy.importXML.ui.ImportXMLPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import static javax.swing.Action.ACTION_COMMAND_KEY;
import static javax.swing.Action.MNEMONIC_KEY;
import static javax.swing.Action.SHORT_DESCRIPTION;

/**
 *
 * @author scarl
 */
public class ImportXMLAction extends AbstractAction {

	private UIController controller;

	/**
	 * Creates Export XML action.
	 *
	 * @param controller controller
	 */
	public ImportXMLAction(UIController controller) {
		// Configures action
		this.controller = controller;
		String name = "Import XML";
		putValue(NAME, name);
		putValue(SHORT_DESCRIPTION, name);
		putValue(ACTION_COMMAND_KEY, name);
		putValue(MNEMONIC_KEY, KeyEvent.VK_F);
	}

	/**
	 *
	 * Creates actionPerformed to call ExportXMLPanel
	 *
	 * @param e e
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		new ImportXMLPanel(this.controller).show();
	}

}
