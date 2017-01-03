package csheets.ext.agenda.ui;

import csheets.CleanSheets;
import csheets.ui.ctrl.BaseAction;
import csheets.ui.ctrl.UIController;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

/**
 * An action of the agenda extension to interact with the spreadsheet.
 *
 * @author AB-1140280
 */
public class AgendaAction extends BaseAction {

	/**
	 * The user interface controller
	 */
	protected UIController uiController;

	/**
	 * Creates a new action.
	 *
	 * @param uiController the user interface controller
	 */
	public AgendaAction(UIController uiController) {
		this.uiController = uiController;
	}

	@Override
	protected String getName() {
		return "Agenda to show events of a contact.";
	}

	@Override
	protected void defineProperties() {
		putValue(SMALL_ICON, new ImageIcon(CleanSheets.class.
				 getResource("ext/agenda/calendar_select_days.png")));
	}

	/**
	 * A simple action that presents a agenda frame. Which allows the user to
	 * see all available events of a contact
	 *
	 * @param event the event that was fired
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		new AgendaFrame(uiController, this.uiController.getFrame()).
			setVisible(true);;
	}
}
