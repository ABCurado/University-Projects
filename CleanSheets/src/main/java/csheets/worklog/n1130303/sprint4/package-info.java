/**
 * Technical documentation regarding the work of the team member (1130303) Rui Freitas during week4.
 *
 * <p>
 * <b>Scrum Master: no</b>
 *
 * <p>
 * <b>Area Leader: no</b>
 *
 * <h2>1. Notes</h2>
 *
 * <h2>2. Use Case/Feature: CRM05.3</h2>
 *
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-89">LPFOURDG-89</a>
 *
 * <h2>3. Requirement</h2>
 * The agenda window should now have a display area divided in 24 slots, one for
 * each hour of the day. Each of the slots should have a small text displaying
 * the hour of the day. The Events should be displayed in a size corresponding
 * to its duration and in the colour of the calendar. It should be possible to
 * select the calendars to display in the window. When double clicking in an
 * event its edit window should appear. It should be possible to switch between
 * this new view and a view that display the events of the day in a simple list
 * (like CRM05.2).
 *
 * <p>
 * <b>Use Case "Advanced Agenda Window":</b>
 * The user selects the extended view of the calendar and one or more calendars.
 * The system displays the events that match the contact, day and calendar(s)
 * selected in a hour separated UI. The user does a double click on an event.
 * The system shows an edit event window. The user edits event information. The
 * system update the event information.
 *
 * <h2>4. Analysis</h2>
 * <p>
 * Since it's the third iteration over this feature, my job was mostly to
 * understand how my colleagues implemented the previous functionalities. I
 * added a new type of events view and some more options on the main window. The
 * rest of code is mostly done. The hardest part is handle with Swing in order
 * to create the required UI.
 * </p>
 *
 * <h3>Analysis sequence diagram</h3>
 * <p>
 * <img src="doc-files/analysis_CRM_5_3.png" alt="image">
 * </p>
 *
 * <h2>5. Design</h2>
 *
 * <h3>Design sequence diagram</h3>
 * <p>
 * <img src="doc-files/design_CRM_5_3.png" alt="image">
 * <p>
 *
 * <h3>5.1. Functional Tests</h3>
 * Since this use case is mostly done with Swing, the only thing we need to test
 * is if the controller methods that load data from the persistence and working
 * correctly. Since this tests were already done, I didn't have to create new.
 *
 * <h3>5.2. UC Realization</h3>
 * I need to a new Events view UI and since the simple one and this new one
 * share behavior I implemented a strategy pattern. I need to make sure I
 * fulfill this sprint requirements but not damaging the previous state of this
 * UC. Business layer and logic layer don't have to be changed.
 *
 * <h3>5.4. Design Patterns and Best Practices</h3>
 * I extracted the JPanel that was displaying the events until now for a new
 * class <code>SimpleViewPanel</code>. Since the new extended view share
 * behavior with this part of the UI, I implemented a StrategyPattern
 * <code>AbstractCalendarViewPanel</code>. This approach avoids duplicated code
 * and makes things easier implementing the view change done by the user in the
 * main window.
 *
 * <h2>6. Implementation</h2>
 *
 * <p>
 * see:
 * <p>
 * <a href="../../../../csheets/ext/agenda/ui/package-summary.html">csheets.ext.agenda.ui</a><p>
 * <a href="../../../../csheets/ext/agenda/ui/CalendarView/package-summary.html">csheets.ext.agenda.ui.CalendarView</a><p>
 *
 * <h2>7. Integration/Demonstration</h2>
 * To demonstrate this iteration of the UC, the user should create more than an
 * event with more than one hour associated with different calendars.
 *
 * <h2>8. Final Remarks</h2>
 * Since most of the work was already done, I only need to work with UI
 * components. Swing doesn't make things easy and was on that part that I spend
 * most of the time.
 *
 * <h2>9. Work Log</h2>
 * <p>
 * <b>Monday</b>
 * <p>
 * Today
 * <p>
 * 1.	Analysis of the UC and started asking some explanations to the previous
 * colleague. Started design.
 * <p>
 * Blocking:
 * <p>
 * 1. -nothing-
 * <p>
 * <b>Tuesday</b>
 * <p>
 * Today
 * <p>
 * 1.	Started implementation.
 * <p>
 * Blocking:
 * <p>
 * 1.
 * <p>
 * <b>Wednesday</b>
 * <p>
 * Today
 * <p>
 * 1.	Continued implementation
 * <p>
 * Blocking:
 * <p>
 * 1.	Working with Swing.
 * <p>
 * <b>Thursday</b>
 * <p>
 * Today
 * <p>
 * 1. Finished the implementation. Presentation to the client.
 * <p>
 * Blocking:
 * <p>
 * 1.
 *
 * <h2>10. Self Assessment</h2>
 *
 * <h3>10.1. Design and Implementation:3</h3>
 * Overall was I good sprint in terms of work. Since the previous iteration was
 * done correctly my job was facilitated. The most problematic part was dealing
 * with Swing and creating (design) the new UI.
 * <p>
 * <b>Evidences:</b>
 *
 * <h3>10.2. Teamwork: ...</h3>
 * I think I have a good team spirit and mentality. I'm always looking for
 * colleagues with problems and offer my help.
 * <h3>10.3. Technical Documentation: ...</h3>
 *
 * @author Rui Freitas <1130303@isep.ipp.pt>
 */
package csheets.worklog.n1130303.sprint4;

class _Dummy_ {
}
