/**
 * Technical documentation regarding the work of the team member (1140443) Diogo Azevedo during week 3.
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
 * <h2>2. Use Case/Feature: LANG02.2</h2>
 *
 * Issue in Jira: http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-31
 * <p>
 * LPFOURDG-113 LANG02.2: Global Variables
 *
 * <h2>3. Requirements</h2>
 *The global variables that exist in the context of the workbook, are persisted with the workbook and their names must start with the "@" sign.
 *At its creation, the global variable must be reffered in a formula.The value must be set with the following assign operator ":=".
 * <h2>4. Analysis</h2>
 * It will be necessary change the grammar, with the creation of a new token to
 * identify Global variables (start with "@" followed by numbers or letters).
 * Futhermore is also necessary change the rule of grammar in the allocation so
 * that the left besides being a reference to a cell, allowing also be a
 * reference to a temporary variable. For the Java will have to be amended to
 * implement the cell with introduction of a list of variables, where each time
 * it is invoked the name of a variable is added or not on that list.
 * <h2>5. Design</h2>
 * * <img src="doc-files/globalVariables.png" alt="image">
 * <h3>5.1. Functional Tests</h3>
 *
 * <h3>5.2. UC Realization</h3>
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
 * 
 * <h2>6. Implementation</h2>
 * 
 * <code>csheets.core.formula.lang.VariableGlobalReference</code>
 * <code>csheets.core.formula.lang.Assign</code>
 * <code>csheets.core.formula.compiler.ExcelExpressionCompiler</code>
 * 
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
 * 1. -nothing-
 * <p>
 * Blocking:
 * </p>
 * 1. -nothing-
 * <p>
 * <b>Tuesday</b>
 * </p>
 * Yesterday I worked on:
 * <p>
 * 1. -nothing-
 * </p>
 * Today
 * <p>
 * Analysis and Requirements of Global Variables
 * </p>
 * Blocking:
 * <p>
 * 1. nothing
 * </p>
 * <b>Wednesday</b>
 * <p>
 * ...
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
package csheets.worklog.n1140443.sprint3;

/**
 * This class is only here so that javadoc includes the documentation about this
 * EMPTY package! Do not remove this class!
 *
 * @author alexandrebraganca
 */
class _Dummy_ {
}
