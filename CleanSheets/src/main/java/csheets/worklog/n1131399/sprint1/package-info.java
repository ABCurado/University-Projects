/**
 * Technical documentation regarding the work of the team member (1131399)
 * Marcelo Barroso during week1.
 *
 * <p>
 * <b>Scrum Master: yes</b>
 *
 * <p>
 * <b>Area Leader: yes</b>
 *
 * <h2>1. Notes</h2>
 *
 * <p>
 * On this sprint i spent most of the time analysing the project's architecture,
 * then i implemented and tested Assign operations but spent wednesday afternoon
 * helping my colleague Pedro Gomes implement InstructionBlock in order to be
 * able to advance to FOR() function implementation.
 *
 * <h2>2. Use Case/Feature: Lang01.1</h2>
 *
 * <p>
 * Issue in Jira:
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-27">LPFOURDG-27
 * Lang01.1- Block of Instructions</a>
 * My Sub-Task: Unallocated to be responsible for the main task.
 *
 * <h2>3.1 Requirement for InstructionBlock support</h2>
 * Add the possibility of writing blocks (or sequences) of instructions. A block
 * must be delimited by curly braces and its instructions must be separated by
 * ";". The instructions of a block are executed sequentially and the block
 * "result" is the result of the last statement of the block.
 *
 * <h2>3.2 Requirement for AssignmentOperation support</h2>
 * Add the possibility for assigning a value to a different cell than the one in
 * which the content is being edited.
 *
 * <p>
 * <b>Use Case 1 - "Assign a refrenced cell with a value":</b> The user selects
 * the cell where he/she wants to type an assignment operation, and then types
 * it. The system updates the referenced cell with the resulting value.
 *
 * <p>
 * <b>Use Case 2 - "Instruction block":</b> The user writes down a set of
 * instructions separated by a semi-colon and surrounded with bracket. The
 * system all the instructions returning the resulting value from the last
 * instruction.
 *
 * <h2>4.2 Analysis for AssignmentOperation support</h2>
 * Since an assignment differs from a BinaryOperation(Operand, Operator, Operand
 * ) aswell as from a UnaryOperation(Operator, operand), a new Operation must
 * emerge: AssignmentOperation(CellReference, Operator, Operand). This will
 * ensure the architecture will be ready for new assignment operations as such:
 * '*= += /= ...' The following class diagram shows how we intend to implement
 * this:
 * <p>
 * <img src="doc-files/class_analysis_lang01.1.png" alt="image">
 * </p>
 * This approach was then abandoned, as our team agreed with the Area Leader
 * when he advised us not to extend the architecture yet, in favour of a more
 * conservative approach. This would allow us to have a working solution in a
 * more timely manner.
 *
 *
 * <h3> First "analysis" sequence diagram UC1 - Instruction Block</h3>
 *
 * <p>
 * <img src="doc-files/lang01.1_Instructions_block_sd_analysis.png" alt="Analysis">
 *
 * <h2>5. Design</h2>
 *
 * <h3>5.1. Functional Tests</h3>
 * Basically, for the development and after analysis, we test if the assign for
 * a specidfic cell it works and a method to test if should recognize the
 * Expression as a InstructionBlock and assign the result of the last Expression
 * to result.
 *
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
 *
 * <p>
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
 * </p>
 *
 * <h2>9. Work Log</h2>
 *
 * <p>
 * <b>Monday</b>
 * </p>
 * <p>
 * Cloning of the project, its compilation and first analysis.
 * </p>
 * <p>
 * <b>Tuesday</b>
 * </p>
 * <p>
 * Development of scripts and thinking in design.
 * </p>
 * <p>
 * <b>Wednesday</b>
 * </p>
 * <p>
 * Integration of the notification system and adaptation of grammar.
 * </p>
 * <p>
 * <b>Thursday</b>
 * </p>
 * <p>
 * Fine corrections in the cycle is functional and presentation.
 * </p>
 * <p>
 * <b>Friday</b>
 * </p>
 * <p>
 * Reload Worklog, global planning and finalizing some other obligations of the
 * ScrumMaster as review and finish sprint to start a new one.
 * </p>
 *
 *
 * <h2>10. Self Assessment</h2>
 *
 * During this sprint, my work was mainly of analysis and study of the
 * application architecture.
 *
 * <h3>10.1. Design and Implementation:</h3>
 *
 * <p>
 * <b>Evidences: ...</b>
 * </p>
 *
 * <h3>10.2. Teamwork: ...</h3>
 *
 * <h3>10.3. Technical Documentation: ...</h3>
 *
 * @author Marcelo Barroso 1131399
 */
package csheets.worklog.n1131399.sprint1;

/**
 * This class is only here so that javadoc includes the documentation about this
 * EMPTY package! Do not remove this class!
 *
 * @author alexandrebraganca
 */
class _Dummy_ {
}
