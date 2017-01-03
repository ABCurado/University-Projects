/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.shareFiles;

import csheets.ext.Extension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;

/**
 *
 * @author João Martins
 */
public class ShareFilesExtension extends Extension {

	/**
	 * Extension name - required.
	 */
	public static final String NAME = "Share Files";

	/**
	 * Creates a new SearchExtension.
	 */
	public ShareFilesExtension() {
		super(NAME);
	}

	/**
	 * Returns the User Interface Extension, the SearchExtension.
	 *
	 * @return UIExtension
	 */
	@Override
	public UIExtension getUIExtension(UIController uiController) {
		return new UIExtensionShareFiles(this, uiController);
	}
}
