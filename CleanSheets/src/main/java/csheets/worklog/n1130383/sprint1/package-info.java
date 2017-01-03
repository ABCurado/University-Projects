/**
 * Technical documentation regarding the work of the team member (1130383) Pedro
 * Gomes during week1.
 *
 * <p>
 * <b>Scrum Master:</b> no.
 * <p>
 * <b>Area Leader:</b> no.
 *
 * <h2>1. Notes</h2>
 *
 * <p>
 * This first week was intended to understand the Project behaviour as well as
 * the mechanisms it uses to perform the already implemented functionality.
 * Besides the required intensive comprehesion and analysis, the team was
 * divided into groups to work on a Functional Increment. In ours team case, the
 * first requirement was Lang01.1 - Instructions Block.</p>
 * <p>
 * The first step was to perform further analysis regarding gramatical behaviour
 * and how the application could deal with the input data. Next the Design,
 * where possible Test solutions were designed and third the implementation.
 * We've mostly coded using pair programming where two or more members were
 * working on the same terminal exchange ideas on how to implement the
 * functional requirement. All features were implemented as expected:
 * Instructions Block where shown Value should be the Value of the last
 * operation in the block; Assign functionality by assigning a Value to a
 * specific Cell and the For Function.</p>
 *
 * <h2>2. Use Case/Feature: Lang01.1</h2>
 *
 * Issue in Jira:
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-27">LPFOURDG-27</a>
 * <p>
 * Sub-Task in Jira:
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-102">LPFOURDG-102</a>
 *
 *
 * <h2>3. Requirement</h2>
 * <p>
 * Add the possibility of writing blocks (or sequences) of instructions. A block
 * must be delimited by curly braces and its instructions must be separated by
 * ";". The instructions of a block are executed sequentially and the block
 * "result" is the result of the last statement of the block. For example, the
 * formula "= {1+2; sum (A1:A10), B3 + 4 }" must result in the sequential
 * execution of all expressions and the result is the value of the expression
 * "B3 + 4". Add the assign operator (its symbol is ":="). This operator assigns
 * to its left the result of the right expression. At the moment the left of the
 * assign operator can only be a cell reference. The FOR loop should also be
 * implemented based on instruction blocks, where the first Expression concerns
 * the "starting point" of the For Loop and the second Expression the "limiter"
 * where the For Loop should end.</p>
 *
 * <p>
 * <b>Use Case 1 - "Assign a refrenced cell with a value":</b> The user selects
 * the cell where he/she wants to type an assignment operation, and then types
 * it. The system updates the referenced cell with the resulting value.</p>
 *
 * <p>
 * <b>Use Case 2 - "Instruction block":</b> The user writes down a set of
 * instructions separated by a semi-colon and surrounded with bracket. The
 * system all the instructions returning the resulting value from the last
 * instruction.</p>
 *
 * <h2>4. Analysis</h2>
 * <p>
 * For the development of Use Case "Lang01.1 - Instructions Block" it's required
 * to analyze all classes of the project of all Formula Packages. We conluded
 * that is necessary to add new tokens "{", "}", ":=", "FOR" and define new
 * grammatical rules to recognize these tokens and their specific behaviour. It
 * is also necessary to create some extra classes such as class For (implements
 * Function), and Assign (implements Operator). In For Loop, the first
 * expression is the initialization, the second term is the boundary condition,
 * and the third is based on Instruction Blocks. The Class
 * ExcelExpressionCompiler was fundamental to the understanding of the behaviour
 * on how to proceed. We will implement specific code to recognize the new
 * Tokens. This class implements methods that extract from the grammar a
 * Parser(FormulaParser) and a Lexer(FormulaLexer). Both provide meaning for the
 * tokens read from the input source (String). A Tree-shape formula (CommonTree)
 * is created containing the tokens which will be used for obtaining the correct
 * Cell final Value.</p>
 *
 * <h3>Assign Analysis:</h3>
 * <p>
 * Since an assignment differs from a BinaryOperation(Operand, Operator, Operand
 * ) as well as from a UnaryOperation(Operator, operand), a new Operation must
 * emerge: AssignmentOperation(CellReference, Operator, Operand). This will
 * ensure the architecture will be ready for new assignment operations such as:
 * '*= += /= ...' The following class diagram shows how we intend to implement
 * this:</p>
 * <img src="doc-files/class_analysis_lang01.1.png" alt="image">
 * <p>
 * This approach was then abandoned as our team agreed with the Area Leader when
 * he advised us not to extend the architecture yet, in favour of a more
 * conservative approach. This would allow us to have a working solution in a
 * more timely manner.</p>
 *
 * <h3>Instruction Block Analysis:</h3>
 * <p>
 * <img src="doc-files/lang01.1_design_assign_operator.png" alt="Analysis"></p>
 *
 * <h2>5.Design</h2>
 *
 * <h3>5.1. Functional Tests</h3>
 * <p>
 * For the development and after analysis, we test if the assign a value for a
 * specific cell works properly and test the recognition of the
 * InstructionsBlock as an Expression as well as the result of the last
 * Expression.</p>
 *
 * <h3>5.2. UC Realization</h3>
 * <p>
 * To complete this User Story we will create specific Classes to handle the new
 * "Grammatical behaviour". The Class For (Function), and Assign (Operator) as
 * well as the InstructionBlock (Expressions). It will be necessary to define a
 * new grammar rules to accept new tokens, and a "for" loop with multiple
 * Expressions (itself an Instructions Block).</p>
 *
 * <b>Assign Operation Sequence Diagram</b>:
 * <img src="doc-files/lang01.1_design_assign_operator.png" alt="SD">
 *
 * <h3>5.3. Classes</h3>
 * <p>
 * Class Diagram.</p>
 *
 * <h3>5.4. Design Patterns and Best Practices</h3>
 * <p>
 * Implemented Patterns that promote Low Coupling - High Cohesion.</p>
 *
 *
 * <h2>6. Implementation</h2>
 * <p>
 * <b>Created Classes</b>: For, Assign, Instruction Block, Ternary Operation,
 * Ternary Operator.</p>
 * <p>
 * <b>Updated Classes/Files</b>: language.props, Formula.g,
 * ExcelExpressionCompiler.</p>
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/de7585f726fba1a7909a1c5db109f4880f12a6eb">Class
 * Creation Commit</a></p>
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/e4e09359bc60855dac83cff747312d396003c3a0">Class
 * Creation and IntructionBlock Implementation Commit as well as possible Test
 * Solutions.</a></p>
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/7ecc655f858b81307defce06a350354542b73cb7">InstructionBlock
 * Update Commit</a></p>
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/72d863fee9327bcc8c6f124a273fdbd30f2461bf">InstructionBlock
 * Update as well as implementation ExcelExpressionCompiler</a></p>
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/44435553e11c99d77430d5fc02b9fb58035b3736">For
 * Implementation Commit</a></p>
 *
 * <h2>7. Integration/Demonstration</h2>
 * <p>
 * Analysis, Testing and Implementation.</p>
 *
 * <h2>8. Final Remarks</h2>
 *
 * <h2>9. Work Log</h2>
 *
 * <p>
 * <b>Monday</b>
 * <p>
 * Today:
 * <p>
 * - Cloning of the project, its compilation, first analysis and reading
 * Javadoc.
 * <p>
 * Blocking:
 * <p>
 * - nothing
 *
 * <p>
 * <b>Tuesday</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * - Cloning of the project, its compilation and first analysis and reading
 * Javadoc.
 * <p>
 * Today:
 * <p>
 * - Started my own worklog. Started analysis of Lang Functional Area and Design
 * on possible Test solutions.
 * <p>
 * Blocking:
 * <p>
 * - nothing
 *
 *
 * <p>
 * <b>Wednesday</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * - Started my own worklog. Started analysis of Lang Functional Area and Design
 * on possible Test solutions.</p>
 * <p>
 * Today:</p>
 * <p>
 * - Implementation of InstructionBlock Class and For Class as well as increased
 * functionalitys on ExcelExpressionCompiler Class and Testing.
 * </p>
 * <p>
 * Blocking:
 * <p>
 * - nothing
 *
 * <p>
 * <b>Thursday</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * - Implementation of InstructionBlock Class and For Class as well as increased
 * functionalitys on ExcelExpressionCompiler Class and Testing.</p>
 * <p>
 * Today:</p>
 * <p>
 * - Sprint Demonstration; Reunion with the Team to define (for the sprint nº2):
 * further requirements that should and will be implemented, Scrum Master and
 * Area Leader.</p>
 * <p>
 * Blocking:
 * <p>
 * - nothing
 *
 *
 *
 * <p>
 * <b>Friday</b>
 * <p>
 * Yesterday I worked on:</p>
 * <p>
 * - Sprint Demonstration; Reunion with the Team to define (for the sprint nº2):
 * further requirements that should and will be implemented, Scrum Master and
 * Area Leader.</p>
 * <p>
 * Today:</p>
 * <p>
 * - Reload Worklog; Prepare and Start the new Sprint, by creating it Tasks and
 * Sub-Tasks.</p>
 * <p>
 * Blocking:
 * <p>
 * - nothing
 *
 * <h2>10. Self Assessment</h2>
 *
 * <h3>10.1. Design and Implementation:</h3>
 * <p>
 * 3- Good: The code implemented follow the already existing patterns promoting
 * Low Couple High Cohesion.</p>
 * <b>Evidences:</b>
 * <p>
 * Links provided above.</p>
 *
 * <h3>10.2. Teamwork</h3>
 * <p>
 * 3- Good.</p>
 *
 * <h3>10.3. Technical Documentation</h3>
 *
 * @author Pedro Gomes 1130383@isep.ipp.pt
 */
package csheets.worklog.n1130383.sprint1;

/**
 * This class is only here so that javadoc includes the documentation about this
 * EMPTY package! Do not remove this class!
 *
 * @author Pedro Gomes 1130383@isep.ipp.pt
 */
class _Dummy_ {
}
