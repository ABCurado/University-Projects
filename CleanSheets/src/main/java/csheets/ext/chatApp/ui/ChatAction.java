package csheets.ext.chatApp.ui;

import csheets.ext.chatApp.application.ChatAppController;
import csheets.ui.ctrl.BaseAction;
import csheets.ui.ctrl.UIController;
import java.awt.event.ActionEvent;

class ChatAction extends BaseAction {

	/**
	 * The user interface controller
	 */
	protected UIController uiController;

	/**
	 * The share cells controller.
	 */
	private final ChatAppController chatAppController;

	/**
	 * Creates a new action.
	 *
	 * @param uiController the user interface controller
	 */
	public ChatAction(UIController uiController,
					  ChatAppController chatAppController) {
		this.uiController = uiController;
		this.chatAppController = chatAppController;
	}

	@Override
	protected String getName() {
		return "Connect";
	}

	@Override
	protected void defineProperties() {
	}

	/**
	 * A simple action that presents a confirmation dialog. If the user confirms
	 * then the contents of the cell A1 of the current sheet are set to the
	 * string "Changed".
	 *
	 * @param event the event that was fired
	 */
	public void actionPerformed(ActionEvent event) {
		ChatUI.instance(chatAppController);
	}

}
