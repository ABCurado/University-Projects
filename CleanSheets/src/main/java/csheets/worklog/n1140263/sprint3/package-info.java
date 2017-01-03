/**
 * Technical documentation regarding the work of the team member (1140263) Joao Martins during week3
 *
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
 * - Problem discussion with Marcelo - Analysis and Design.
 *
 * <h2>2. Use Case/Feature: Core06.2Forms and variables</h2>
 *
 * Issue in Jira:
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-43">LPFOURDG-43</a>
 * <p>
 * Sub-Task in Jira:
 * <p>
 * Analysis:
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-236">LPFOURDG-236</a>
 * <p>
 * Design:
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-237">LPFOURDG-237</a>
 * <p>
 * Implementation:
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-238">LPFOURDG-238</a>
 * <p>
 * Tests:
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-239">LPFOURDG-239</a>
 *
 *
 * <h2>3. Requirement</h2>
 * In order for forms to become useful in formulas or macros it is necessary to
 * associate data with the contents of the visual widgets. The mechanism used
 * for that will be the binding of variables (macros or formulas variables) with
 * the contents of the visual widgets. One simple way to achieve this is by
 * using temporary variables (from macros and formulas). The matching between
 * widgets and variables should be done by associating the ones with the same
 * name. When displaying a form (in the context of a macro or a formula), if the
 * temporary variables with the same name of widgets exist, them they are used
 * to set the content of the widgets. For widgets for which no temporary
 * variables with the same name are found then new temporary variables should be
 * created. The user should be able to change the contents of edit boxes. When
 * closing the form window the contents of the temporary variables should be
 * updated from the contents of the corresponding visual widgets.
 *
 *
 * <h2>4. Analysis</h2>
 * Allow the user to create a form dynamically in it can insert or remove a line
 * with buttons or labels or textfields, which is composed of a name and
 * content. Use temporary variables ( p.e: ={_var:=30;FORM()} ). It is necessary
 * to associate data with the contents of the visual widgets. The user should be
 * able to change the contents of edit boxes. When closing the form window the
 * contents of the temporary variables should be updated from the contents of
 * the corresponding visual widgets. If the user writes "label"; "textfield";
 * "button" it will be able to create the written type. If the system does not
 * recognize them, return a message: "widget not found". If the user open the
 * FORM with a variable and write the name of the variable in the content, the
 * system must display the value of the created variable.
 *
 *
 * <h3>First "analysis" sequence diagram</h3>
 * <img src="doc-files/forms_editor_analysis" alt="SD">
 *
 * <h2>5. Design</h2>
 *
 * <h3>5.1. Functional Tests</h3>
 *
 * Test the creation of panel; textfields; button. Test the remove option of one
 * widget from the form. Testar a associacao do valor da variavel quando escrito
 * o nome da variavel no form (na criacao de um widget especifico).
 *
 * <h3>5.2. UC Realization</h3>
 * <img src="doc-files/forms_editor_2_design.png" alt="image">
 *
 * <h3>5.3. Classes</h3>
 *
 * Form; AskContent; ButtonPanel; ButtonWidget; FormEditor; LabelPanel;
 * LabelWidget; SingleLine; TextFieldPanel; TextFieldWidget; (Interface) Widget
 *
 * <h3>5.4. Design Patterns and Best Practices</h3>
 *
 * 1. Test Driven Design. 2. Layers - UI; Controller; Domain
 *
 *
 * <h2>6. Implementation</h2>
 *
 * Creation of the classes of the three types of widgets: the user interface
 * panel and the class with the "actions" - "NameType"Widget.java Interface:
 * Widget with the methods that the "NameType"Widget.java will use to each type.
 * "NameType"Widget.java - toString with the designation and the constructor
 * with the creation of the "NameType"Panel.java to add to the FormEditor
 *
 * <h2>7. Integration/Demonstration</h2>
 *
 * Nothing.
 *
 * <h2>8. Final Remarks</h2>
 *
 * The number of supported widget will increase. It is necessary to offer more
 * options widget options to the form (or this 3 types are enough?)
 *
 * <h2>9. Work Log</h2>
 *
 * -Insert here a log of you daily work. This is in essence the log of your
 * daily standup meetings.-
 * <p>
 * Example
 * <p>
 * <b>Monday</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * 1. Learn 6.1 Lang implementation.
 * <p>
 * Today
 * <p>
 * 1. Analysis. 2. Think about design.
 * <p>
 * Blocking:
 * <p>
 * 1. -nothing-
 * <p>
 * <b>Tuesday</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * 1. Analysis. 2. Think about design.
 * <p>
 * Today
 * <p>
 * 1. Design. 2.Tests 3.Implementation
 * <p>
 * Blocking:
 * <p>
 * 1. Nothing
 * <p>
 * <b>Wednesday</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * 1. Design. 2.Tests 3.Implementation
 * <p>
 * Today
 * <p>
 * 1. Tests. 2. Implementation.
 * <p>
 * Blocking:
 * <p>
 * 1. -nothing-
 *
 * <h2>10. Self Assessment</h2>
 *
 * -Insert here your self-assessment of the work during this sprint.-
 *
 * <h3>10.1. Design and Implementation:3</h3>
 *
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
 * @author joao martins
 */
package csheets.worklog.n1140263.sprint3;

/**
 * This class is only here so that javadoc includes the documentation about this
 * EMPTY package! Do not remove this class!
 *
 * @author joao martins
 */
class _Dummy_ {
}
