/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.search;

import csheets.ext.Extension;
import csheets.ext.search.ui.UISearch;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;

/**
 *
 * @author Rúben Teixeira 1140780@isep.ipp.pt
 */
public class SearchExtension extends Extension {

	/**
	 * Extension name - required.
	 */
	public static final String NAME = "Search";

	/**
	 * Creates a new SearchExtension.
	 */
	public SearchExtension() {
		super(NAME);
	}

	/**
	 * Returns the User Interface Extension, the SearchExtension.
	 *
	 * @return UIExtension
	 */
	@Override
	public UIExtension getUIExtension(UIController uiController) {
		return new UISearch(this, uiController);
	}
}
