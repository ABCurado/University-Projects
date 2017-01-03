/**
 * Technical documentation regarding the work of the team member (1140260) Diogo
 * Leite during week2.
 *
 *
 * <b>Scrum Master: -(yes/no)- no</b>
 *
 *
 * <b>Area Leader: -(yes/no)- no</b>
 *
 * <h2>1. Notes</h2>
 *
 *
 * <h2>2. Use Case/Feature: LANG03.1</h2>
 *
 * Issue in Jira:
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-33">LPFOURDG-33</a>
 *
 *
 * <h2>3. Requirement</h2>
 * Update the "style" extension so that it can be used for the conditional
 * formatting of cells based on the result of the execution of formulas. For the
 * style of a cell to be conditional it must have an associated formula and two
 * formatting styles. One of the styles is applied when the formula evaluates to
 * true and the other when it evaluates to false. The editing of these settings
 * should be done in a sidebar window.
 *
 *
 * <h2>4. Analysis</h2>
 *
 *
 * <h3>Conditional Formatting</h3>
 * The user selects "Conditional Formatting" option . The system asks the user
 * wich formatting options he want if formula turn out to be true or false The
 * user selects the conditional formatting for both options. The system analyzes
 * the formula and decides which formatting should he aplly.
 *
 *
 * <h2>SSD</h2>
 * <p>
 * <img src="doc-files/class_analysis_lang03.1.png" alt="image">
 *
 * <h2>5. Design</h2>
 *
 *
 * <h3>5.3. Classes</h3>
 * <p>
 * <img src="doc-files/lang03_01_design.png" alt="image">
 *
 *
 * <h2>6. Implementation</h2>
 *
 * <code>csheets.ext.conditionalFormatting</code>
 * <code>csheets.ext.conditionalFormatting.ui</code>
 *
 *
 *
 * <h2>9. Work Log</h2>
 *
 *
 *
 *
 *
 *
 * <b>Friday</b>
 * <p>
 * Reading feature requirements and analysing formulas code in order to
 * understand how antlr and grammars are integratd in the project and how it is
 * related with Use Case
 *
 *
 * <b>Monday</b>
 * <p>
 * Finnishing anlysis and design and begging implementation *
 *
 *
 *
 *
 * <b>Tuesday</b>
 * <p>
 * Continue UI and Controller implementation
 * <p>
 * Blocking: How to work with formatting actions
 *
 *
 *
 *
 *
 * <b>Wednesday</b>
 * <p>
 * Finnish UI and Controller implementation
 *
 *
 *
 *
 *
 *
 * <h2>10. Self Assessment</h2>
 *
 * -
 *
 * <h3>10.1. Design and Implementation:3</h3>
 *
 *
 * <b>Evidences:</b>
 *
 *
 * <h3>10.2. Teamwork: ...</h3>
 *
 * <h3>10.3. Technical Documentation: ...</h3>
 *
 * @author Diogo Leite
 */
package csheets.worklog.n1140260.sprint2;

/**
 * This class is only here so that javadoc includes the documentation about this
 * EMPTY package! Do not remove this class!
 *
 * @author Diogo Leite
 */
class _Dummy_ {
}
