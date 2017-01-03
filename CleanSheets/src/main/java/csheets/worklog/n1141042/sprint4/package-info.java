/**
 * Technical documentation regarding the work of the team member 1141042.
 * Hicham Abahri during week4.
 *
 *
 * <b>Scrum Master: no</b>
 *
 *
 * <b>Area Leader: yes</b>
 *
 *
 * <h2>1. Notes</h2>
 *
 *
 * <h2>2. Use Case/Feature: CRM6.3</h2>
 *
 *
 * Issue in Jira:
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-92">LPFOURDG-92
 * CRM01.2- Company Contact</a>
 *
 *
 *
 * <h2>3 Requirement</h2>
 * Tasks may now have due dates. For tasks with due dates it should be possible to create reminders (one or
 * more). The reminders should have a due date that is earlier than the due date of the task. It should also
 * be possible to create an event in a calendar of the contact to mark the task deadline. For events, tasks,
 * contacts and reminders that are related the user interface should also provide visual information of that
 * relationships and it should be possible to "move" between linked elements (e.g., it should be possible to
 * open a task window from an event that is associated with a task).
 *
 *
 *
 * <h2>4. Analysis</h2>
 * A task will have an end date.
 * The reminder must have end date before the end date of the task.
 * Create an event in the calendar of the contact to dial the deadline for a task.
 * Contact trough has access to events, the events have to show the tasks, and each task has to show their reminder.
 * <a href="../../../../overview-summary.html#arranque_da_aplicacao">
 * Application
 * Startup</a> tells us that extensions must be a subclass of the Extension
 * abstract class and need to be registered in special files. The Extension
 * class has a method called getUIExtension that should be implemented and
 * return an instance of a class that is a subclass of UIExtension. In this
 * subclass of UIExtension there is a method (getSideBar) that returns the
 * sidebar for the extension. A sidebar is a JPanel.
 *
 *
 *
 * After understanding how extensions are created, we proceded to our use case
 * analysis. We decided that our sidebar will cover all the possible features
 * for the user (create/edit/remove tasks).
 *
 *
 * We control the use case flow by enable or disable sidebar's components and
 * updating other components.
 *
 *
 * The functional area of this use case requires the use of JPA (ORM). To
 * achieve this functionality we use the same framework used in UC EAPLI,
 * allowing the abstraction of persistence layer.
 *
 *
 *
 * <h2>5. Design</h2>
 *
 *
 *
 * To realize this user story we will need to create a subclass of Extension. We
 * will also need to create a subclass of UIExtension. For the sidebar we need
 * to implement a JPanel. In the code of the extension
 * <code>csheets.ext.style</code> we can find examples that illustrate how to
 * implement these technical requirements. The following diagrams illustrate
 * core aspects of the design of the solution for this use case.
 *
 *
 *
 * <h3>Create,Edit and Remove Task</h3>
 *
 * <img src="doc-files/crm06_03_design_create_task.png" alt="image">

 * <h2>7 Integration Demonstration</h2>
 * <h2>7.1 Integration </h2>
 *
 * My job this week was a continuation of previous work of my colleagues.
 * I was able to integrate and understand the operation of the use case,
 * in a simple way. I made some changes in their code. 
 *
  <h2>7.2  Demonstration</h2>
  * <img src="doc-files/pri.png" alt="image">
  * <p>activate the extension of the tasks.</p>
  * <img src="doc-files/seg.png" alt="image">
  * <p>before starting to create a user tasknecessarily have to create a contact 
  * a calendar a reminder, and finally an event.</p>
  * <img src="doc-files/ter.png" alt="image">
  * <p>after filling in the data (name, description and set the priority and percentage),
  * the task may or may not be associated with the end date of an event or a reminder, 
  * a date set by the user, or create without deadline.</p>
  * <img src="doc-files/qua.png" alt="image">
  * <p>after creation one task, the user has the possibility to edit or delete a created task.</p> 
  * 
 * <h2>8. Final Remarks</h2>
 * This use case, it is not difficult to accomplish, but at first was confused understand the requisites.
 * After a conversation with Professor Miguel Losa, I concluded that I realized the evil requisites, 
 * and explained to me what we need to do, and soon started implementation.
 *
 * <h2>9. Work Log</h2>
 *
 * <b>Monday</b>
 * <p>
 * Yesterday I worked on:
 * </p>
 * 1. -nothing-
 * <p>
 * Today
 * </p>
 * 1. Analysis and Design of the Network Explorer
 * <p>
 * Blocking:
 * </p>
 * 1. -nothing-
 * <p>
 * <b>Tuesday</b>
 * </p>
 * Yesterday I worked on:
 * <p>
 * 1. Analysis and Design of the Network Explorer
 * </p>
 * Today
 * <p>
 * 1. implementation, and controller 
 * </p>
 * Blocking:
 * <p>
 * 1. nothing
 * </p>
 * <p>
 * <b>Wednesday</b>
 * </p>
 * Yesterday I worked on:
 * <p>
 * 1. Implementation continuation
 * </p>
 * Today
 * <p>
 * 1. finishing the implementation and testing
 * </p>
 * Blocking:
 * <p>
 * 1. nothing
 * </p>
 * <h2>10. Self Assessment</h2>
 *
 * During this sprint, my work was mainly of analysis and study of the
 * application architecture.
 *
 * <h3>10.1. Design and Implementation:</h3>
 *
 *
 * <b>Evidences: ...</b>
 *
 *
 * <h3>10.2. Teamwork: ...</h3>
 *
 * <h3>10.3. Technical Documentation: ...</h3>
 *
 * @author Hicham Abahri 1141042
 */
package csheets.worklog.n1141042.sprint4;

/**
 * This class is only here so that javadoc includes the documentation about this
 * EMPTY package! Do not remove this class!
 *
 * @author alexandrebraganca
 */
class _Dummy_ {
}
