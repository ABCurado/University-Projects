/**
 * Technical documentation regarding the work of the team member (1141042) Hicham Abahri week2.
 *
 * <p>
 * <b>Scrum Master: -(yes/no)- no</b>
 *
 * <b>Area Leader: -(yes/no)- no</b>
 * </p>
 *
 * <h2>1. Notes</h2>
 *
 * Helped my colleagues throughout the sprint in regards to Volt and
 * network/thread related questions.
 *
 * <h2>2. Use Case/Feature: LPFOURDG-42 </h2>
 *
 * <p>
 * Issue in Jira:
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-42">LPFOURDG-42</a>
 * </p>
 * <p>
 * LPFOURDG-42 Cleansheets should have a new menu option to launch a window for
 * editing a form. A Form is a window that is designed by the end user and is
 * used for interacting with the user (input and output). The new window should
 * support the creation and testing of a Form. Forms should be very simple. A
 * Form should be composed of rows, each row can be empty or have one or two
 * visual widgets. The supported visual widgets are: button (to invoke actions);
 * edit box (to enter data) and static text box (to display data). It should be
 * possible to set the core properties of these widgets (like the text to
 * display in a static text box, for instance). In the edit form window it
 * should be possible to: add a new row; remove an existing row; edit an
 * existing row; 'play' the form and close the edit form window. The 'play'
 * button is for testing the appearance of a form during its design (see example
 * in the manual). At the moment it is only required to support a single Form
 * for each workbook. It is not required to persist the Form. Macros and
 * formulas should have a new function that can be used to display the form of
 * the current workbook. Forms should have an icon or button to close de form.
 * When the form is closed the function (in macros or formulas) who call it
 * returns.
 *
 * </p>
 *
 * <h2>3. Requirement</h2>
 *
 * allow the user to create a form dynamically in it can insert or remove a
 * line, which is composed of a name and content.
 *
 *
 * <h2>4. Analysis</h2>
 *
 * <h3>Send a Secure Message</h3>
 * <p>
 * User: Starts the process. 
 * System: Opens the window with options add or remove a line. 
 * System: Presents the option to add remove a line.
 * User: Chooses an option. 
 * System: ask the name of the label and the textbox description 
 * User: Answer. 
 * System: Adds a new line.
 * User: If the chosen option is to remove. 
 * System: Removes one line in the form.
 * </p>
 * 
 * <h4>Analysis diagram:</h4>
 * 
 * <img src="doc-files/forms_editor_analysis.png" alt="image">
 *
 *
 * This feature is already supported, and was built on the Feature
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-42">LPFOURDG-42</a>.
 *
 * <h2>5. Design</h2>
 * 
 * <img src="doc-files/forms_editor_design.png" alt="image">
 *
 * <h2>5.1. Functional Tests</h3>
 * <p>
 * Tests were performed for Class AskContent, FormEdir and Single Line to check if the search works as
 * intended and for SearchResultAssembler to ensure the proper information was
 * being gathered and displayed.</p>
 *
 * <h2>8. Final Remarks</h2>
 *
 * <h2>9. Work Log</h2>
 * <p>
 * 06/06/2016</p>
 * <p>
 * Monday</p>
 * <p> Start analysis and design  </p>
 * <p>
 * Today</p>
  * Blocking</p>
 * <p>
 * Nothing</p> *
 * <p>
 * 07/06/2016</p>
 * <p>
 * Tuesday</p>
 * <p>
 * Today</p>
 * <p>
 * start implementation </p>
 * <p>
 * Blocking</p>
 * <p>
 * Nothing</p>
 *
 * <p>
 * 08/06/2016</p>
 * <p>
 * Wednesday</p>
 * <p>
 * Yesterday</p>
 * <p>
 * Implemented the static class and made some small improvements to it.</p>
 * <p>
 * Today</p>
 * <p>
 * Will finish my use case by implementing the User Interface and the associated
 * controller following the design. Test my use case and update the overall
 * documentation.</p>
 * <p>
 * Blocking</p>
 * <p>
 * Nothing</p>
 *
 * <h2>10. Self Assessment</h2>
 *
 * <p>
 * Outcome 3 ("Design and Implementation") -4</p>
 * <p>
 * Outcome 5 ("Teamwork") - 3</p>
 * <p>
 * Outcome 6 ("Technical Documentation") - 4</p>
 *
 * <h3>10.1. Design and Implementation:3</h3>
 *
 *<p>The implementation of the use case was a simple form that allows the user to 
 * add or remove a line consisting of a Label and a TextFiel before being added, 
 * it is necessary that the user enters the name and content,
 * then the line will be added to the form with the data entered.
 * </p>
 * <p>
 * <b>Evidences:</b>
 * </p>
 *
 * <h3>10.2. Teamwork: ...</h3>
 *
 * <h3>10.3. Technical Documentation: ...</h3>
 *
 * @author Hicham Abahri
 */
package csheets.worklog.n1141042.sprint2;

/**
 * This class is only here so that javadoc includes the documentation about this
 * EMPTY package! Do not remove this class!
 *
 * @author alexandrebraganca
 */
class _Dummy_ {
}
