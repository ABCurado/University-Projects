/**
 * Technical documentation regarding the work of the team member (1131190) João
 * Martins during week2.
 *
 * <p>
 * <b>Scrum Master: no</b>
 *
 * <p>
 * <b>Area Leader: no</b>
 *
 * <h2>1. Notes</h2>
 *
 *
 *
 * <h2>2. Use Case/Feature: LANG02.1</h2>
 *
 * Issue in Jira:
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-30">LPFOURDG-75</a>
 *
 * <h2>3. Requirement</h2>
 * Add support for temporary variables. The name of temporary variables must
 * start with the "_" sign. When a variable is referred in a formula for the
 * first time it is created. To set the value of a variable it must be used on
 * the left of the assign operator (":="). Temporary variables are variables
 * that only exist in the context of the execution of a formula. Therefore, it
 * is possible for several formulas to use tem- porary variables with the same
 * name and they will be different instances.
 *
 * <h2>4. Analysis</h2>
 * It will be necessary change the grammar, with the creation of a new token to
 * identify temporary variables (start with "_" followed by numbers or letters).
 * Futhermore is also necessary change the rule of grammar in the allocation so
 * that the left besides being a reference to a cell, allowing also be a
 * reference to a temporary variable. For the Java will have to be amended to
 * implement the cell with introduction of a list of variables, where each time
 * it is invoked the name of a variable is added or not on that list.
 *
 *
 * <h2>5. Design</h2>
 *
 * <img src="doc-files/lang02_01_design_temporary_variables.png" alt="image">
 *
 * <h3>5.3. Classes</h3>
 *
 * <h2>9. Work Log</h2>
 *
 * <b>Saturday</b>
 * <p>
 * Reading feature requirements and analysing formulas code in order to
 * understand how antlr and grammars are integratd in the project
 * <p>
 *
 * <b>Sunday</b>
 * <p>
 * Analysis and designing of the UC
 * <p>
 *
 * <b>Monday</b>
 * <p>
 * Implementation of the UC
 * <p>
 *
 * <b>Tuesday</b>
 * <p>
 * Tests in the implementation and close issue
 *
 * <p>
 * Helping in the UC LANG 08.1(export XML)
 * <p>
 *
 * <b>Wednesday</b>
 * <p>
 * Helping in the UC CRM05.1(calendar edition)
 *
 * <p>
 * Helping in the UC CRM06.1(reminders edition)
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
 * @author João Martins
 */
package csheets.worklog.n1131190.sprint2;

/**
 * This class is only here so that javadoc includes the documentation about this
 * EMPTY package! Do not remove this class!
 *
 * @author João Martins
 */
class _Dummy_ {
}
