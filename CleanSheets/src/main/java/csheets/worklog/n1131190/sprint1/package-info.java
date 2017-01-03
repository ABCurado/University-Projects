/**
 * Technical documentation regarding the work of the team member (1131190) João
 * Martins during week1.
 *
 * <b>Scrum Master: no</b>
 *
 * <p>
 * <b>Area Leader: no</b>
 *
 * <h2>1. Notes</h2>
 * <p>
 * This week work time was mostly spent on how the base application works.
 *
 * <h2>2. Use Case/Feature: CRM01.1</h2>
 *
 * Issue in Jira:
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-75">LPFOURDG-75</a>
 *
 * <h2>3. Requirement</h2>
 * Implement a local db using JPA (ORM) so the application user could create
 * <code>csheets.domain.Contact</code> and within each one be able to create
 * <code>csheets.domain.Event</code> on his <code>csheets.domain.Agenda</code>.
 * Besides the ability to create the above entities the user can remove or edit
 * each one.
 *
 * <p>
 * <b>Use Case "Contact Edition":</b> A sidebar window that provides
 * functionalities for creating, editing and removing contacts. Each contact
 * should have a first and last name and also a photograph. Each contact should
 * also have one agenda in which events related to the contact should be
 * displayed. For the moment, events have only a due date (i.e., timestamp) and
 * a textual description. It should be possible to create, edit and remove
 * events. The agenda may be displayed in a different sidebar. This sidebar
 * should display a list of all events: past, present and future. One of the
 * contacts should be the user of the session in the computer where Cleansheets
 * is running. If this user has events then, when their due date arrives,
 * Cleansheets should display a popup window notifying the user about the
 * events. This popup window should automatically disappear after a small time
 * interval (e.g., 5 seconds).
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
 * <p>
 * After understanding how extensions are created, we proceded to our use case
 * analysis. We decided that our sidebar will cover all the possible features
 * for the user (create/edit/remove contacts and events).
 *
 * We control the use case flow by enable or disable sidebar's components and
 * updating other components.
 *
 * The functional area of this use case requires the use of JPA (ORM). To
 * achieve this functionality we use the same framework used in UC EAPLI,
 * allowing the abstraction of persistence layer.
 *
 * <h2>5. Design</h2>
 *
 * <h3>5.1. Tests</h3>
 * Basically, from requirements and also analysis, we see that we have to make
 * sure that a contact object HAS to have a first name and a last name. So we
 * have created a test that represents this business rule.
 * <p>
 * see: <code>csheets.domain.ContactTest</code>
 * <p>
 * Agenda has no information at this point. From our view of the requirements
 * there is no business rule to apply on this concept.
 * <p>
 * Event MUST have a time and a description. So we have created a test that
 * represents this business rule.
 *
 * <p>
 * see: <code>csheets.domain.ContactTest</code>
 *
 * <h3>5.2. UC Realization</h3>
 * To realize this user story we will need to create a subclass of Extension. We
 * will also need to create a subclass of UIExtension. For the sidebar we need
 * to implement a JPanel. In the code of the extension
 * <code>csheets.ext.style</code> we can find examples that illustrate how to
 * implement these technical requirements. The following diagrams illustrate
 * core aspects of the design of the solution for this use case.
 *
 * <h3>Create Contact</h3>
 * <p>
 * <img src="doc-files/crm01_01_design_add_contact.png" alt="image">
 *
 * <h3>Edit Contact</h3>
 * <p>
 * <img src="doc-files/crm01_01_design_edit_contact.png" alt="image">
 *
 * <h3>Remove Contact</h3>
 * <p>
 * <img src="doc-files/crm01_01_design_remove_contact.png" alt="image">
 *
 * <h3>Create Event</h3>
 * <p>
 * <img src="doc-files/crm01_01_design_add_event.png" alt="image">
 *
 * <h3>Edit Event</h3>
 * <p>
 * <img src="doc-files/crm01_01_design_edit_event.png" alt="image">
 *
 * <h3>Remove Event</h3>
 * <p>
 * <img src="doc-files/crm01_01_design_remove_event.png" alt="image">
 *
 * <h3>Class Diagram of the feature</h3>
 * <p>
 * <img src="doc-files/crm01_01_design_class_diagram.png" alt="image">
 *
 * <h3>5.4. Design Patterns and Best Practices</h3>
 *
 * In this issue we used some design patterns: -Persistence layer as an
 * abstraction for the domain or application layer. -Entity, AggregateRoot and
 * value object DDD concepts.
 *
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
 * <p>
 * see:
 * <p>
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
 * <p>
 * 1. Meeting with our supervisor and decided who were the Aea Leaders as well
 * who was going to be Scrum Master.
 * <p>
 * Blocking:
 * <p>
 * 1. Nothing.
 * <p>
 * <b>Tuesday</b>
 * <p>
 * 1. Analysis of the UC, testing, designing and start the implementation
 * <p>
 * Blocking:
 * <p>
 * 1. Nothing.
 * <p>
 * <b>Wednesday</b>
 * <p>
 * 1. Implementation of the events
 * <p>
 * Blocking:
 * <p>
 *  * 1. Nothing.
 * <p>
 * <b>Thursday</b>
 * <p>
 * 1. Presentation to the client
 * <p>
 * Blocking:
 * <p>
 *  * 1. Nothing.
 *
 * <h2>10. Self Assessment</h2>
 *
 * -Insert here your self-assessment of the work during this sprint.-
 *
 * <h3>10.1. Design and Implementation:3</h3>
 *
 * 3- bom: os testes cobrem uma parte significativa das funcionalidades (ex:
 * mais de 50%) e apresentam código que para além de não ir contra a arquitetura
 * do cleansheets segue ainda as boas práticas da área técnica (ex:
 * sincronização, padrões de eapli, etc.)
 * <p>
 * <b>Evidences:</b>
 * <p>
 * - url of commit: ... - description: this commit is related to the
 * implementation of the design pattern ...-
 *
 * <h3>10.2. Teamwork: ...</h3>
 *
 * <h3>10.3. Technical Documentation: ...</h3>
 *
 * /**
 *
 * @author João Martins
 */
package csheets.worklog.n1131190.sprint1;

class _Dummy_ {
}
