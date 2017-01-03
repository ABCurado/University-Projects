/**
 * Technical documentation regarding the work of the team member (1030472) Bruno
 * Silva during week3.
 *
 *
 * <b>Scrum Master: yes</b>
 *
 *
 * <b>Area Leader: yes</b>
 *
 *
 * <h2>1. Notes</h2>
 *
 *
 * <h2>2. Use Case/Feature: CRM06.2</h2>
 *
 *
 * Issue in Jira:
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-91">LPFOURDG-91
 * CRM06.2- Task Edition</a>
 *
 *
 *
 * <h2>3 Requirement</h2>
 * It should be possible to create, edit and remove tasks. A task has a name, a
 * description and is associated with a contact. A task also has a priority
 * level (1 to 5) and a percentage of completion. Cleansheets should have a
 * sidebar window to display tasks. A double click in a task should open a
 * window to edit the task. In the sidebar it should be possible to sort and
 * filter the tasks using expressions based on its fields. For instance, it
 * should be possible to only display tasks that are not completed.
 *
 *
 *
 * <h2>4. Analysis</h2>
 * Since tasks will be supported in a new extension to cleansheets, we need to
 * study how extensions are loaded by cleansheets and how they work. extensions
 * must be a subclass of the Extension abstract class and need to be registered
 * in special files. The Extension class has a method called getUIExtension that
 * should be implemented and return an instance of a class that is a subclass of
 * UIExtension. In this subclass of UIExtension there is a method (getSideBar)
 * that returns the sidebar for the extension. A sidebar is a JPanel.
 *
 * After understanding how extensions are created, we proceded to our use case
 * analysis. We decided that our sidebar will cover all the possible features
 * for the user (create/edit/remove tasks). To create a Task, we made a sequence
 * diagram:
 * <img src="doc-files/crm01_02_design_edit_contact.png" alt="image">
 * To edit a task, the same panel is used as to create: taskmanager, user insert
 * the new information and the task will be updated. To remove a task
 * TaskPanelSingle will execute remove(). TaskController is in charge to execute
 * create/edit and remove of the UI comands The functional area of this use case
 * requires the use of JPA (ORM), allowing the abstraction of persistence layer.
 * Observer pattern is implemented in order of updating information of the task
 * repository. Resume: When user click in "add task" or "edit task" we will see
 * if the task already has info, if it has information, it will edit and update,
 * if not it will create the new task.
 * <h2>5. Design</h2>
 *
 *
 * <h3>Create Task</h3>
 *
 *
 *
 * <img src="doc-files/crm06_02_design_create_task.png" alt="image">
 *
 *
 *
 *
 * <h3>Diagram Class</h3>
 *
 * <h2>7. Integration/Demonstration</h2>
 *
 *
 *
 * -In this section document your contribution and efforts to the integration of
 * your work with the work of the other elements of the team and also your work
 * regarding the demonstration (i.e., tests, updating of scripts, etc.)-
 *
 *
 *
 * <h2>8. Final Remarks</h2>
 * The final parts of the use case was not completely implemented, but it was
 * asked to be able to sort or filter not completed tasks, and to be able to
 * filter we allow the user to choose one or two percentage he wants to filter:
 * example (NUMBER) <= percentage <= (NUMBER)
 *
 *
 *
 * <h2>9. Work Log</h2>
 *
 *
 * <b>Monday</b>
 *
 *
 *
 * <b>Tuesday</b>
 *
 *
 * Development of analise and design.
 *
 *
 * <b>Wednesday</b>
 *
 *
 * Implementation
 *
 *
 * <b>Thursday</b>
 *
 *
 * Fine corrections swing ui. Filter percentage tasks
 *
 * <b>Friday</b>
 *
 *
 *
 * @author Bruno Silva 1030472
 */
package csheets.worklog.n1030472.sprint3;

/**
 * This class is only here so that javadoc includes the documentation about this
 * EMPTY package! Do not remove this class!
 *
 * @author alexandrebraganca
 */
class _Dummy_ {
}
