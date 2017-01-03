package csheets.ext.distributedWorkbookSearch;

import csheets.ext.Extension;
import csheets.ext.distributedWorkbookSearch.ui.UIExtensionNetworkWorkbookSearch;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;

/**
 * An extension to support workbook search in another instance. An extension
 * must extend the Extension abstract class. The class that implements the
 * Extension is the "bootstrap" of the extension.
 *
 * @see Extension
 * @author José Barros
 */
public class NetworkWorkbookSearchExtension extends Extension {

	/**
	 * The name of extension
	 */
	public static final String NAME = "Network Workbook Search";

	/**
	 * Creates a new workbook extension.
	 */
	public NetworkWorkbookSearchExtension() {
		super(NAME);
	}

	/**
	 * Returns the user interface extension of this extension
	 *
	 * @param uiController the user interface controller
	 * @return a user interface extension, or null if none is provided
	 */
	@Override
	public UIExtension getUIExtension(UIController uiController) {
		return new UIExtensionNetworkWorkbookSearch(this, uiController);
	}
}
