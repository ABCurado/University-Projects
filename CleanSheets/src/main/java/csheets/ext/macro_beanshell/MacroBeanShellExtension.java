package csheets.ext.macro_beanshell;

import csheets.ext.Extension;
import csheets.ext.macro_beanshell.ui.UIExtensionMacroBeanShell;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;

/**
 * @see Extension
 * @author Rui Bento
 */
public class MacroBeanShellExtension extends Extension {

	/**
	 * The name of the extension
	 */
	public static final String NAME = "Macro/BeanShell";

	/**
	 * Creates a new MacroBeanShellExtension extension.
	 */
	public MacroBeanShellExtension() {
		super(NAME);
	}

	/**
	 * Returns the user interface extension of this extension (an instance of
	 * the class
	 * {@link csheets.ext.macro_beanshell.ui.UIExtensionMacroBeanShell}).
	 *
	 * @param uiController the user interface controller
	 * @return a user interface extension, or null if none is provided
	 */
	@Override
	public UIExtension getUIExtension(UIController uiController) {
		return new UIExtensionMacroBeanShell(this, uiController);
	}
}
