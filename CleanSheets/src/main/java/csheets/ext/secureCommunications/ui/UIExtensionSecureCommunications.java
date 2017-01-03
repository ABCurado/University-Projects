package csheets.ext.secureCommunications.ui;

import csheets.ext.Extension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;
import javax.swing.Icon;
import javax.swing.JComponent;

/**
 * This class implements the UI interface extension for the secure
 * communications extension. A UI interface extension must extend the
 * UIExtension abstract class.
 *
 * @see UIExtension
 * @author Renato Machado
 */
public class UIExtensionSecureCommunications extends UIExtension {

	/**
	 * The icon to display with the extension's name
	 */
	private Icon icon;

	/**
	 * A side bar that provides editing of comments
	 */
	private JComponent sideBar;

	/**
	 * Controller.
	 */
	private SecureCommunicationsController shareController;

	/**
	 * The menu of the extension
	 *
	 * @param extension extension
	 * @param uiController ui controller
	 */
	public UIExtensionSecureCommunications(Extension extension,
										   UIController uiController) {
		super(extension, uiController);
	}

	/**
	 * Returns an icon to display with the extension's name.
	 *
	 * @return an icon with style
	 */
	public Icon getIcon() {
//		if (icon == null) {
//			icon = new ImageIcon(ShareExtension.class.getResource("res/img/share.png"));
//		}
//		return icon;
		return null;
	}

	/**
	 * Returns a side bar that gives access to extension-specific functionality.
	 *
	 * @return a component, or null if the extension does not provide one
	 */
	public JComponent getSideBar() {
		if (sideBar == null) {
			if (shareController == null) {
				shareController = new SecureCommunicationsController();
			}
			sideBar = new SecureCommunicationsUI(uiController, shareController);
		}
		return sideBar;
	}

}
