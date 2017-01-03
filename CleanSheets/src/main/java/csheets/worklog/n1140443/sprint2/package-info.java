/**
 * Technical documentation regarding the work of the team member (1140443) Diogo Azevedo during week2.
 *
 * <p>
 * <b>-Note: this is a template/example of the individual documentation that
 * each team member must produce each week/sprint. Suggestions on how to build
 * this documentation will appear between '-' like this one. You should remove
 * these suggestions in your own technical documentation-</b>
 * <p>
 * <b>Scrum Master: -(yes/no)- no</b>
 *
 * <p>
 * <b>Area Leader: -(yes/no)- no</b>
 *
 * <h2>1. Notes</h2>
 *
 * -Notes about the week's work.-
 * <p>
 * -In this section you should register important notes regarding your work
 * during the week. For instance, if you spend significant time helping a
 * colleague or if you work in more than a feature.-
 *
 * <h2>2. Use Case/Feature: CRM04.1</h2>
 *
 * Issue in Jira: http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-84
 * <p>
 * LPFOURDG-113 CRM04.1: Notes Edition
 *
 * <h2>3. Requirements</h2>
 * Creating, editing and deleting the text notes associated with contacts. There
 * should be a sidebar to list the textual notes of a contact. Cleansheets
 * should maintain the history of modifications for each text note. When a text
 * note is selected Cleansheets should display its history.
 *
 *
 * <h2>4. Analysis</h2>
 *
 * <b>Use Case "Notes Edition-create note":</b> The User initiates the Notes
 * Edition use case.The System displays all contacts.The user selects a
 * contact,writes the note and chooses to create it.The system creates the note
 * and adds it to the repository, and informs the sucess.
 * <b>Use Case "Notes Edition-edit note":</b> The User initiates the Notes
 * Edition use case.The System displays all contacts.The user selects a
 * contact,a note and chooses edits it.The system edits the note and saves it in
 * the repository, and informs the sucess.
 * <b>Use Case "Notes Edition-celete note":</b> The User initiates the Notes
 * Edition use case.The System displays all contacts.The user selects a
 * contact,a note and chooses to delete it.The system deletes the note from the
 * repository, and informs the sucess.
 * <h2>5. Design</h2>
 *
 * <h3>5.1. Functional Tests</h3>
 *
 *
 * <h3>5.2. UC Realization</h3>
 * <h3>Create Note</h3>
 * <p>
 * <img src="doc-files/notesCreate.png" alt="image">
 * </p>
 * <h3>Edit Note</h3>
 * <p>
 * <img src="doc-files/notesEdit.png" alt="image">
 * </p>
 * <h3>Remove Note</h3>
 * <p>
 * <img src="doc-files/notesDelete.png" alt="image">
 * </p>
 *
 * <h3>5.3. Classes</h3>
 *
 * -Document the implementation with class diagrams illustrating the new and the
 * modified classes-
 *
 * <h3>5.4. Design Patterns and Best Practices</h3>
 *
 * -Describe new or existing design patterns used in the issue-
 * <p>
 * -You can also add other artifacts to document the design, for instance,
 * database models or updates to the domain model-
 * </p>
 * <h2>6. Implementation</h2>
 *
 * <code>csheets.domain.Note</code>
 * <code>csheets.ext.notes.NotesController</code>
 * <code>csheets.ext.notes.ui.NotesPanel</code>
 * <p>
 * -Also refer all other artifacts that are related to the implementation and
 * where used in this issue. As far as possible you should use links to the
 * commits of your work-
 * </p>
 * see:
 * <p>
 * <a href="../../../../csheets/ext/comments/package-summary.html">csheets.ext.comments</a>
 * </p>
 * <p>
 * <a href="../../../../csheets/ext/comments/ui/package-summary.html">csheets.ext.comments.ui</a>
 * </p>
 * <h2>7. Integration/Demonstration</h2>
 * Has we enter the second week of the project, we start to develop features for
 * the contacts.
 *
 * <h2>8. Final Remarks</h2>
 *
 * <h2>9. Work Log</h2>
 *
 * <p>
 * Example
 * </p>
 * <b>Monday</b>
 * <p>
 * Yesterday I worked on:
 * </p>
 * 1. -nothing-
 * <p>
 * Today
 * </p>
 * 1. Analysis and Requirements of the Notes Edition
 * <p>
 * Blocking:
 * </p>
 * 1. -nothing-
 * <p>
 * <b>Tuesday</b>
 * </p>
 * Yesterday I worked on:
 * <p>
 * 1. Analysis and Requirements of the Notes Edition
 * </p>
 * Today
 * <p>
 * 1. Design and implementation of the Notes Edition
 * </p>
 * Blocking:
 * <p>
 * 1. nothing
 * </p>
 * <b>Wednesday</b>
 * <p>
 * Implementation of the Notes and work and changes in the worklog
 * <p>
 * 1. Nothing.
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
 * </p>
 * - url of commit: ... - description: this commit is related to the
 * implementation of the design pattern ...-
 *
 * <h3>10.2. Teamwork: ...</h3>
 *
 * <h3>10.3. Technical Documentation: ...</h3>
 *
 * @author Diogo Azevedo
 */
package csheets.worklog.n1140443.sprint2;

/**
 * This class is only here so that javadoc includes the documentation about this
 * EMPTY package! Do not remove this class!
 *
 * @author alexandrebraganca
 */
class _Dummy_ {
}
