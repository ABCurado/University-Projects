package csheets.ext.sort;

import csheets.ext.Extension;
import csheets.ext.sort.ui.UISort;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;

/**
 * Sort Extension
 */
public class SortExtension extends Extension {

	/**
	 * Extension name - required.
	 */
	private static final String NAME = "Sort";

	/**
	 * Creates a new SortExtension.
	 */
	public SortExtension() {
		super(NAME);

	}

	/**
	 * Returns the User Interface Extension of the SortExtension.
	 *
	 * @return UIExtension
	 */
	@Override
	public UIExtension getUIExtension(UIController uiController) {
		return new UISort(this, uiController);
	}
}
