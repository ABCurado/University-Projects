package csheets.ext.macro_beanshell.ui;

import csheets.CleanSheets;
import csheets.ui.ctrl.BaseAction;
import csheets.ui.ctrl.UIController;
import java.awt.event.ActionEvent;
import static javax.swing.Action.SMALL_ICON;
import javax.swing.ImageIcon;

/**
 *
 * @author José Barros
 */
public class ScriptManagerAction extends BaseAction {

	/**
	 * The user interface controller
	 */
	private UIController uiController;

	/**
	 * Creates a new action.
	 *
	 * @param uiController the user interface controller
	 */
	public ScriptManagerAction(UIController uiController) {
		this.uiController = uiController;
	}

	@Override
	protected String getName() {
		return "Scripts Manager";
	}

	protected void defineProperties() {
		putValue(SMALL_ICON, new ImageIcon(CleanSheets.class.
				 getResource("ext/macro_beanshell/script_small.png")));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new ScriptManagerUI(uiController).run();
	}

}
