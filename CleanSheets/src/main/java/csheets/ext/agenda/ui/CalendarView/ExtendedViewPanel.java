/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.agenda.ui.CalendarView;

import csheets.domain.ContactCalendar;
import csheets.domain.Event;
import csheets.ext.agenda.ui.AgendaFrame;
import csheets.support.DateTime;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 *
 * @author Rui Freitas 1130303@isep.ipp.pt
 */
public class ExtendedViewPanel extends AbstractCalendarViewPanel {

	public ExtendedViewPanel(AgendaFrame parent) {
		super(parent);
		setLayout(new GridLayout(1, 0));
		initComponents();
	}

	@Override
	public void updateEvents() {
		boolean isHead = false;

		clearEventList();
		Calendar currentDate = theParent.calendar();
		for (ContactCalendar cc : theParent.selectCalendars()) {
			List<Event> list = theParent.controller().
				updateEvents(currentDate, theParent.selectedContact(), cc);
			for (int i = 0; i <= 23; i++) {
				for (Event event : list) {
					if (i >= DateTime.hour(event.startDate()) && i <= DateTime.
						hour(event.endDate())) {
						isHead = (i == DateTime.hour(event.startDate()));
						((HourPanel) getComponent(i)).
							addEvent(event, isHead);
					} else {
						((HourPanel) getComponent(i)).
							addEvent(null, isHead);
					}
				}
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
		for (int i = 0; i < getComponentCount(); i++) {
			((HourPanel) getComponent(i)).removeAllEvents();
		}
	}

	/**
	 * Layout specific: add's a row to the panel's layout (to prevent adding a
	 * new colummn).
	 */
	private void addGridRow() {
		GridLayout layout = (GridLayout) getLayout();
		layout.setRows(layout.getRows() + 1);
	}

	private void initComponents() {

		clearEventList();
		for (int i = 0; i <= 23; i++) {
			HourPanel tmp = new HourPanel(i, this);
			add(tmp);
			addGridRow();
		}
		revalidate();
		repaint();
	}

}

class HourPanel extends JPanel {

	private static final Dimension DEFAULT_DIMENSION = new Dimension(400, 50);
	private final ArrayList<Event> theEvents;
	private JPanel eventsPanel;
	private JPanel hourPanel;
	private final ExtendedViewPanel parent;

	public HourPanel(int hour, ExtendedViewPanel aThis) {
		initComponents(hour);
		parent = aThis;
		theEvents = new ArrayList<>();
	}

	private void initComponents(int hour) {
		setPreferredSize(DEFAULT_DIMENSION);
		setLayout(new BorderLayout());
		setBorder(new LineBorder(Color.GRAY, 1, true));

		eventsPanel = new JPanel(new GridLayout(1, 1, 1, 0));
		hourPanel = new JPanel();

		hourPanel.setPreferredSize(new Dimension(15, DEFAULT_DIMENSION.height));
		eventsPanel.
			setPreferredSize(DEFAULT_DIMENSION);

		hourPanel.add(new JLabel(String.valueOf(hour)));
		add(hourPanel, BorderLayout.WEST);
		add(eventsPanel, BorderLayout.EAST);

		revalidate();
		repaint();
	}

	protected void addEvent(Event theEvent, boolean isHead) {

		JPanel tmp = new JPanel();

		if (theEvent != null) {
			tmp.setBackground(theEvent.calendar().getColor());
			if (isHead) {
				tmp.add(new JLabel(theEvent.description()));
			}
		}

		tmp.setPreferredSize(DEFAULT_DIMENSION);

		theEvents.add(theEvent);
		eventsPanel.add(tmp);

		GridLayout layout = (GridLayout) eventsPanel.getLayout();
		layout.setColumns(layout.getColumns() + 1);

		tmp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2 && theEvent != null) {
					EditEventPanel event = new EditEventPanel(theEvent);
					int eventOption = JOptionPane.
						showConfirmDialog(HourPanel.this, event, "Edit Event", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
					if (eventOption == JOptionPane.OK_OPTION) {
						event.editEvent();
						parent.updateEvents();
					}
				}
			}
		});

		revalidate();
		repaint();

	}

	protected void removeAllEvents() {
		theEvents.clear();
		eventsPanel.removeAll();
		((GridLayout) eventsPanel.getLayout()).setColumns(1);
		revalidate();
		repaint();
	}
}
