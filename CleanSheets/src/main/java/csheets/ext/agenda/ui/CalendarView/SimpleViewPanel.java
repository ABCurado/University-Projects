/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.agenda.ui.CalendarView;

import csheets.domain.ContactCalendar;
import csheets.domain.Event;
import csheets.ext.agenda.ui.AgendaFrame;
import java.awt.GridLayout;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Rui Freitas 1130303@isep.ipp.pt
 */
public class SimpleViewPanel extends AbstractCalendarViewPanel {

	public SimpleViewPanel(AgendaFrame parent) {
		super(parent);
		setLayout(new GridLayout(1, 0));
	}

	@Override
	public void updateEvents() {
		clearEventList();
		Calendar currentDate = theParent.calendar();
		for (ContactCalendar cc : theParent.selectCalendars()) {
			List<Event> list = theParent.controller().
				updateEvents(currentDate, theParent.selectedContact(), cc);
			for (Event event : list) {
				add(new EventPanel(event));
				addGridRow();
			}
		}

		revalidate();
		repaint();
	}

	/**
	 * Deletes all information from event list.
	 */
	@Override
	protected void clearEventList() {
		removeAll();
		((GridLayout) getLayout()).setRows(1);
	}

	/**
	 * Layout specific: add's a row to the panel's layout (to prevent adding a
	 * new colummn).
	 */
	private void addGridRow() {
		GridLayout layout = (GridLayout) getLayout();
		layout.setRows(layout.getRows() + 1);
	}

}
