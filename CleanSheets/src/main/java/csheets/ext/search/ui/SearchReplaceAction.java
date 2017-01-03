package csheets.ext.search.ui;

import csheets.ui.ctrl.BaseAction;
import csheets.ui.ctrl.UIController;
import java.awt.event.ActionEvent;

/**
 *
 * @author José Barros
 */
class SearchReplaceAction extends BaseAction {

	/**
	 * The user interface controller
	 */
	private final UIController uiController;

	public SearchReplaceAction(UIController uiController) {
		this.uiController = uiController;
	}

	@Override
	protected String getName() {
		return "Search and Replace";
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		new SearchReplaceUI(uiController).run();
	}

}
