package csheets.ext.networkExplorer;

import csheets.ext.networkExplorer.ui.UIExtensionNetworkExplorer;
import csheets.ext.events.*;
import csheets.ext.Extension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;

/**
 * An extension to support comments on cells. An extension must extend the
 * Extension abstract class. The class that implements the Extension is the
 * "bootstrap" of the extension.
 *
 * @see Extension
 * @author Alexandre Braganca
 * @author Einar Pehrson
 */
public class NetworkExplorerExtension extends Extension {

	/**
	 * The name of the extension
	 */
	public static final String NAME = "Network Explorer";

	/**
	 * Creates a new Example extension.
	 */
	public NetworkExplorerExtension() {
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
		return new UIExtensionNetworkExplorer(this, uiController);
	}
}
