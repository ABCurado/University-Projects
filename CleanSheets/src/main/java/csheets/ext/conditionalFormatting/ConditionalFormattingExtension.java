/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.conditionalFormatting;

import csheets.ext.conditionalFormatting.ui.UIExtensionCondicionalFormatting;
import csheets.ext.Extension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;

/**
 *
 * @author Diogo Leite
 */
public class ConditionalFormattingExtension extends Extension {

	/**
	 * The name of the extension
	 */
	public static final String NAME = "Conditional Formatting";

	/**
	 * Creates a new assertion extension.
	 */
	public ConditionalFormattingExtension() {
		super(NAME);
	}

	/**
	 * Returns a user interface extension for dependency trees.
	 *
	 * @param uiController the user interface controller
	 * @return a user interface extension for dependency trees
	 */
	@Override
	public UIExtension getUIExtension(UIController uiController) {
		return new UIExtensionCondicionalFormatting(this, uiController);
	}

}
