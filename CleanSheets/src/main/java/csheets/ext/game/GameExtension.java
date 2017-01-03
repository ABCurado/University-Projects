/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.game;

import csheets.ext.Extension;
import csheets.ext.game.ui.UIGameExtension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;

/**
 * An extension to support the game feature. An extension must extend the
 * Extension abstract class. The class that implements the Extension is the
 * "bootstrap" of the extension.
 *
 * @see Extension
 * @author João Martins
 */
public class GameExtension extends Extension {

	/**
	 * Definition of the name extension.
	 */
	public static final String NAME = "Play Game";

	/**
	 * Creates a new Game Extension - "Play Game"
	 */
	public GameExtension() {
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
		return new UIGameExtension(this, uiController);
	}

}
