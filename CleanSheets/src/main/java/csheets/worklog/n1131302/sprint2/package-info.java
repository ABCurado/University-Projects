/**
 * Technical documentation regarding the work of the team member (1131302) Gabriel Sousa during week2.
 *
 * <b>Scrum Master: no</b>
 *
 * <p>
 * <b>Area Leader: No</b>
 *
 * <h2>1. Notes</h2>
 *
 *
 * <h2>2. Use Case/Feature: CRM06.1</h2>
 *
 * Issue in Jira:
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-90">LPFOURDG-90</a>
 *
 * <h2>3. Requirement</h2>
 * Implement a local db using JPA (ORM) so the application user could create
 * <code>csheets.domain.Reminder</code>.
 *
 * <p>
 * <b>Use Case "Reminders Edition":</b> A new window to create, edit and remove
 * reminders. Reminders are like events but with reminders the user is notified
 * when the due date of the reminder arrives. A reminder has a name, a
 * description and a timestamp (due date). Cleansheets should only allow valid
 * timestamps. The window should list all the existing reminders. When the due
 * date of a reminder is reached Cleansheets should automatically display an
 * alert to the user in a popup window. This popup window should display the
 * name, description and due date of the reminder. The window should have two
 * buttons. One button to close the window and the other button to remind again
 * the user in 5 minutes.
 *
 *
 * <h2>4. Analysis</h2>
 *
 *Analyzing the use case found that we can separate into three separate use cases, one to create a new reminder,
 * another to change existing reminders and finally one to remove reminders.
 * A reminder has a name, a description and warning date.
 * When the current date reaches the reminder date this triggers a new window to the user to inform. Then the user decides closes it or snooze in 5min.
 *
 *
 * <h2>5. Design</h2>
 *
 * <h3>5.1. Tests</h3>
 * <p>
 * Basically, from requirements and also analysis, we see that we have to make
 * sure that a contact object HAS to have a name and a description and the time
 * step can´t be before the actual date. So we have created a test that
 * represents this business rule.
 * </p>
 * see: <code>csheets.domain.ReminderTest</code>
 *
 * <p>
 * Agenda has no information at this point. From our view of the requirements
 * there is no business rule to apply on this concept.
 * </p>
 *
 * Event MUST have a time and a description. So we have created a test that
 * represents this business rule.
 *
 * <p>
 * see: <code>csheets.domain.ReminderTest</code>
 *
 * </p>
 *
 * <h3>5.2. UC Realization</h3>
 *
 * <h3>Create Reminder</h3>
 * <p>
 * <img src="doc-files/crm06_01_design_add_reminder.png" alt="image">
 * </p>
 * <h3>Edit Reminder</h3>
 * <p>
 * <img src="doc-files/crm06_01_design_edit_reminder.png" alt="image">
 * </p>
 * <h3>Remove Reminder</h3>
 * <p>
 * <img src="doc-files/crm06_01_design_remove_reminder.png" alt="image">
 * </p>
 * <h3>5.3. Classes</h3>
 *
 * TODO: class diagram!
 *
 * <h3>5.4. Design Patterns and Best Practices</h3>
 * <p>
 * In this issue we used some design patterns: -Persistence layer as an
 * abstraction for the domain or application layer. -Entity, AggregateRoot and
 * value object DDD concepts.
 * </p>
 *
 * <h2>6. Implementation</h2>
 *
 * <code>csheets.domain.Contact</code> <code>csheets.domain.Agenda</code>
 * <code>csheets.domain.Event</code>
 *
 * <p>
 * -Also refer all other artifacts that are related to the implementation and
 * where used in this issue. As far as possible you should use links to the
 * commits of your work-
 * </p>
 * see:
 * <a href="../../../../csheets/persistence/package-summary.html">csheets.ext.comments</a>
 *
 * <h2>7. Integration/Demonstration</h2>
 *
 * We are in the first week where the workflow of the project is a little bit
 * different from the rest of the weeks. Our functional area is very independent
 * from the others. The only that we had to talk with our work collegues was
 * related to the extensions part (Core functional area).
 *
 * <h2>8. Final Remarks</h2>
 *
 * <h2>9. Work Log</h2>
 *
 * <p>
 * <b>Monday</b>
 * </p>
 * 1. Analysis and design of the CRM6.1 to create, edit and remove reminders. 2.
 * Create the domain necessary in project.
 * <p>
 * Blocking:
 * </p>
 * 1. Nothing.
 * <p>
 * <b>Tuesday</b>
 * <p>
 * 1.Start Implemetation
 * <b>Evidences:</b>
 * </p>
 * <b>Wednesday:</b>
 * <p>
 * 1.finish the implemetation. 2.Tests
 * <b>Evidences:</b>
 * </p>
 * - url of commit: ... - description: this commit is related to the
 * implementation of the design pattern ...-
 *
 * <h3>10.2. Teamwork: ...</h3>
 *
 * <h3>10.3. Technical Documentation: ...</h3>
 *
 * /**
 *
 * @author Gabriel Sousa
 */
package csheets.worklog.n1131302.sprint2;

class _Dummy_ {
}
