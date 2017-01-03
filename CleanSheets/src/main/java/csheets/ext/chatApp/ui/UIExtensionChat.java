/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.chatApp.ui;

import csheets.ext.chatApp.application.ChatAppController;
import csheets.ext.Extension;
import csheets.ext.chatApp.ChatAppExtension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JMenu;

/**
 * This class implements the UI interface extension for the comments extension.
 * A UI interface extension must extend the UIExtension abstract class.
 *
 * @see UIExtension
 * @author José Barros
 */
public class UIExtensionChat extends UIExtension {

	/**
	 * The icon to display with the extension's name
	 */
	private Icon icon;
	/**
	 * The menu of the extension
	 */
	private ChatMenu menu;
	/**
	 * A side bar that provides editing of comments
	 */
	private JComponent sideBar;

	/**
	 * Controller.
	 */
	private ChatAppController chatAppController;

	/**
	 * The menu of the extension
	 *
	 * @param extension extension
	 * @param uiController ui controller
	 */
	public UIExtensionChat(Extension extension, UIController uiController) {
		super(extension, uiController);
	}

	/**
	 * Returns an instance of a class that implements JMenu.
	 *
	 * @see ChatMenu
	 * @return a JMenu component
	 */
	@Override
	public JMenu getMenu() {
		if (menu == null) {
			if (chatAppController == null) {
				chatAppController = new ChatAppController();
			}
			menu = new ChatMenu(uiController, chatAppController);
		}
		return menu;
	}

	/**
	 * Returns an icon to display with the extension's name.
	 *
	 * @return an icon with a tree
	 */
	@Override
	public Icon getIcon() {
		if (icon == null) {
			icon = new ImageIcon(
				ChatAppExtension.class.getResource("res/img/logo.gif"));
		}
		return icon;
	}

	/**
	 * Returns a side bar that gives access to extension-specific functionality.
	 *
	 * @return a component, or null if the extension does not provide one
	 */
	@Override
	public JComponent getSideBar() {
		if (sideBar == null) {
			if (chatAppController == null) {
				chatAppController = new ChatAppController();
			}
			sideBar = new ChatApplicationPanel(uiController, chatAppController);
		}
		return sideBar;
	}
}
