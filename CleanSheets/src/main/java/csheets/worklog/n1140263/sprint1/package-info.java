/**
 * Technical documentation regarding the work of the team member (1140263) Joao Martins during week1
 *
 * <p>
 * <b>Scrum Master: no</b>
 *
 * <p>
 * <b>Area Leader: no</b>
 *
 * <h2>1. Notes</h2>
 *
 * -Notes about the week's work.-
 * <p>
 * On this sprint i spent most of the time analysing the project's architecture,
 * then i help Ruben Santos in analysis and i did part of the design of the
 * funcionality. In the last day i implement one feature with Marcelo Barroso
 * (pair programming).
 *
 *
 * <h2>2. Use Case/Feature: Lang01.1</h2>
 *
 * Issue in Jira:
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-27">LPFOURDG-27</a>
 * <p>
 * Sub-Task in Jira:
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-103">LPFOURDG-103</a>
 *
 *
 * <h2>3. Requirement for InstructionBlock</h2>
 * Add the possibility of writing blocks (or sequences) of instructions. A block
 * must be delimited by curly braces and its instructions must be separated by
 * ";". The instructions of a block are executed sequentially and the block
 * "result" is the result of the last statement of the block.
 *
 * <h2>3.2 Requirement for AssignmentOperation support</h2>
 * Add a comment to this line Add the possibility for assigning a value to a
 * different cell than the one in which the content is being edited.
 *
 * <p>
 * <b>Use Case 1 - "Assign a refrenced cell with a value":</b> The user selects
 * Add a comment to this line the cell where he/she wants to type an assignment
 * operation, and then types it. The system updates the referenced cell with the
 * resulting value.
 *
 * <p>
 * <b>Use Case 2 - "Instruction block":</b> The user writes down a set of
 * instructions separated by a semi-colon and surrounded with bracket. The
 * system all the instructions returning the resulting value from the last
 * instruction.
 *
 *
 * <h2>4. Analysis</h2>
 *
 * For the development of use case "Lang01.1 - Instructions Block", it´s
 * necessary analyze all classes of the project of packages formula. We conlude
 * that is necessary add new tokens "{", "}", ":=", "FOR" and define new rules
 * of grammatics for recognize intructions block and function for. We conclude
 * that is necessary create some extra classes. We have to crate Class "For",
 * and "Atrribuation" are functions. Also we have create a new Operation to
 * resolve n expressions and validates in function "convert" of class
 * "ExcelExpressionCompiler".
 *
 * <h2>5. Design</h2>
 *
 * <h3>5.1. Functional Tests</h3>
 * Basically, for the development and after analysis, we test if the assign for
 * a specidfic cell it works and a method to test if should recognize the
 * Expression as a InstructionBlock and assign the result of the last Expression
 * to result.
 *
 * <h3>5.2. UC Realization</h3>
 * To realize this user story we will need to create Class "For" (function), and
 * "Atribution" (operator). It will be necessary to define a better grammar to
 * accept new tokens, and a "for" loop with multiple operations blocks. Also we
 * have create a new Operation to resolve n expressions and validates in
 * function "convert" of class "ExcelExpressionCompiler".
 *
 * <b>Assign Operation Sequence Diagram</b>:
 * <img src="doc-files/lang01.1_design_assign_operator.png" alt="SD">
 *
 * <h3>5.3. Classes</h3>
 *
 *
 * <h3>5.4. Design Patterns and Best Practices</h3>
 * <p>
 * Implemented Patterns: Low Coupling - High Cohesion.
 *
 * <h2>6. Implementation</h2>
 *
 * <p>
 * <b>Created Classes</b>: For, Assign, Instruction Block, Ternary Operation,
 * Ternary Operator.
 * <p>
 *
 * <b>Updated Classes/Files</b>: language.props, Formula.g,
 * ExcelExpressionCompiler.
 *
 * <h2>7. Integration/Demonstration</h2>
 *
 * -In this section document your contribution and efforts to the integration of
 * your work with the work of the other elements of the team and also your work
 * regarding the demonstration (i.e., tests, updating of scripts, etc.)-
 *
 * <h2>8. Final Remarks</h2>
 *
 * -In this section present your views regarding alternatives, extra work and
 * future work on the issue.-
 * <p>
 * As an extra this use case also implements a small cell visual decorator if
 * the cell has a comment. This "feature" is not documented in this page.
 *
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
 * 1. Configure the IDE to start working. Cloning of the projec. Read javadoc.
 * Understand the project.
 * <p>
 * Today
 * <p>
 * 1. ........
 * <p>
 * Blocking:
 * <p>
 * 1. -nothing-
 * <p>
 * <b>Tuesday</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * 1. Configure the IDE to start working. 2. Clone project. 3. Read javadoc. 4.
 * Understand the project.
 * <p>
 * Today
 * <p>
 * 1. Understand the problem/analysis to begin the implementation.
 * <p>
 * Blocking:
 * <p>
 * <b>Wednesday</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * 1. Configure the IDE to start working (done). 2. Cloning of the projec(done).
 * 3. Read javadoc. 4. Understand the project.
 * <p>
 * Today
 * <p>
 * 1. Help Design and decide analysis. 2. Design - Assign feature. 3. Learn/read
 * the class ExcelExpressionCompiler.java to add the assign functionality. 4.
 * Assign a value to a cell. - Pair Programming (Marcelo).
 * <p>
 * Blocking:
 * <p>
 * 1. Deep modifications analysis / design.
 *
 * <h2>10. Self Assessment</h2>
 *
 * -Insert here your self-assessment of the work during this sprint.-
 *
 * <h3>10.1. Design and Implementation:</h3>
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
 * @author joao martins
 */
package csheets.worklog.n1140263.sprint1;

/**
 * This class is only here so that javadoc includes the documentation about this
 * EMPTY package! Do not remove this class!
 *
 * @author alexandrebraganca
 */
class _Dummy_ {
}
