/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.game.ui;

import csheets.CleanSheets;
import csheets.ext.Extension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

/**
 * This class implements the UI interface extension for the game extension. Ui
 * interface extension must extend the UIExtension abstract class.
 *
 * @author João Martins
 */
public class UIGameExtension extends UIExtension {

	/**
	 * SideBar to choose player and game to paly.
	 */
	private JComponent sideBar;

	/**
	 * The menu of the extension.
	 *
	 * @param extension extension
	 * @param uiController uiController
	 */
	public UIGameExtension(Extension extension, UIController uiController) {
		super(extension, uiController);
	}

	/**
	 * Returns an icon to display with the extension's name.
	 *
	 * @return an icon with style
	 */
	@Override
	public Icon getIcon() {
		return new ImageIcon(CleanSheets.class.
			getResource("ext/game/game.png"));
	}

	/**
	 * Returns a side bar that gives access to extension-specific functionality.
	 *
	 * @return a component, or null if the extension does not provide one
	 */
	@Override
	public JComponent getSideBar() {
		if (this.sideBar == null) {
			this.sideBar = new GamePanel(uiController);
		}
		return this.sideBar;
	}

}
