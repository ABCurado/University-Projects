/**
 * Technical documentation regarding the work of the team member (1131399)
 * Marcelo Barroso during week2.
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
 * <h2>2. Use Case/Feature: CRM01.2</h2>
 *
 *
 * Issue in Jira:
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-76">LPFOURDG-76
 * CRM01.2- Company Contact</a>
 *
 *
 *
 * <h2>3 Requirement</h2>
 * A contact may also be a company. If a contact is a company then it has a name
 * (no first and no last name). A person contact may now be related to a company
 * contact. A person contact may have also a profession. The profession should
 * be selected from a list. The list of professions should be loaded (and/or
 * updated) from a external xml file or an existing configuration file of
 * Cleansheets. The window for company contacts should display all the person
 * contacts that are related to it. The company window should also have an
 * agenda. The agenda of a company should be read only and display all the
 * events of the individual contacts that are related to it.
 *
 *
 *
 * <h2>4. Analysis</h2>
 * Since contacts will be supported in a new extension to cleansheets we need to
 * study how extensions are loaded by cleansheets and how they work. The first
 * sequence diagram in the section
 * <a href="../../../../overview-summary.html#arranque_da_aplicacao">Application
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
 * for the user (create/edit/remove contacts and events).
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
 * <h3>Create Contact</h3>
 *
 *
 *
 * <img src="doc-files/crm01_02_design_add_contact.png" alt="image">
 *
 *
 *
 * <h3>Edit Contact</h3>
 *
 *
 *
 * <img src="doc-files/crm01_02_design_edit_contact.png" alt="image">
 *
 *
 *
 * <h3>Remove Contact</h3>
 *
 *
 *
 * <img src="doc-files/crm01_02_design_remove_contact.png" alt="image">
 *
 *
 *
 * <h3>Diagram Class</h3>
 *
 *
 *
 * <img src="doc-files/crm01_02_design_diagram_class.png" alt="image">
 *
 *
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
 *
 *
 *
 * -In this section present your views regarding alternatives, extra work and
 * future work on the issue.-
 *
 *
 *
 * As an extra this use case also implements a small cell visual decorator if
 * the cell has a comment. This "feature" is not documented in this page.
 *
 *
 * <h2>9. Work Log</h2>
 *
 *
 * <b>Monday</b>
 *
 *
 * Cloning of the project, its compilation and first analysis.
 *
 *
 * <b>Tuesday</b>
 *
 *
 * Development of scripts and thinking in design.
 *
 *
 * <b>Wednesday</b>
 *
 *
 * Integration of the notification system and adaptation of grammar.
 *
 *
 * <b>Thursday</b>
 *
 *
 * Fine corrections in the cycle is functional and presentation.
 *
 *
 * <b>Friday</b>
 *
 *
 * Reload Worklog, global planning and finalizing some other obligations of the
 * ScrumMaster as review and finish sprint to start a new one.
 *
 *
 *
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
 * @author Marcelo Barroso 1131399
 */
package csheets.worklog.n1131399.sprint2;

/**
 * This class is only here so that javadoc includes the documentation about this
 * EMPTY package! Do not remove this class!
 *
 * @author alexandrebraganca
 */
class _Dummy_ {
}
