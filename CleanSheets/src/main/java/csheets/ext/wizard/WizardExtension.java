package csheets.ext.wizard;

import csheets.ext.Extension;
import csheets.ext.wizard.ui.UIExtensionWizard;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;

/**
 * @author AB-1140280
 */
public class WizardExtension extends Extension {

	/**
	 * The name of the extension
	 */
	public static final String NAME = "Wizard";

	/**
	 * Creates a new Example extension.
	 */
	public WizardExtension() {
		super(NAME);
	}

	/**
	 * Returns the user interface extension of this extension (an instance of
	 * the class {@link  csheets.ext.simple.ui.UIExtensionExample}). In this
	 * extension example we are only extending the user interface.
	 *
	 * @param uiController the user interface controller
	 * @return a user interface extension, or null if none is provided
	 */
        
        @Override
	public UIExtension getUIExtension(UIController uiController) {
		return new UIExtensionWizard(this, uiController);
	}
}
