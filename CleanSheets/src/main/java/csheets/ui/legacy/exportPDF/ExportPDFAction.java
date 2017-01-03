package csheets.ui.legacy.exportPDF;

import csheets.ui.ctrl.UIController;
import csheets.ui.legacy.exportPDF.ui.ExportPDFPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;

/**
 *
 * @author Diogo Leite
 */
public class ExportPDFAction extends AbstractAction {

	private UIController controller;

	/**
	 * Creates Export PDF action.
	 *
	 * @param controller controller
	 */
	public ExportPDFAction(UIController controller) {
		// Configures action
		this.controller = controller;
		String name = "Export PDF";
		putValue(NAME, name);
		putValue(SHORT_DESCRIPTION, name);
		putValue(ACTION_COMMAND_KEY, name);
		putValue(MNEMONIC_KEY, KeyEvent.VK_P);
	}

	/**
	 *
	 * Creates actionPerformed to call ExportPDFPanel
	 *
	 * @param ae event
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		new ExportPDFPanel(this.controller).show();
	}
}
