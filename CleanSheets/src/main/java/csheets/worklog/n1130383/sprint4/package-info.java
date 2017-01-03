/**
 * Technical documentation regarding the work of the team member (1130383) Pedro
 * Gomes during week4.
 *
 * <p>
 * <b>Scrum Master:</b>no.
 *
 * <p>
 * <b>Area Leader:</b>no.
 *
 * <h2>1. Notes</h2>
 *
 * <p>
 * During this week I’ve spent a significant amount of time with this feature.
 * Although it wasn’t hard, it was definitely extensive to perform all the
 * required analysis and design for both Use Cases. The feature was implemented
 * with the necessary requirements and some additional functionalities.
 * Alongside this implementation I’ve paired up with my colleague Rúben Teixeira
 * – 1140780 to solve difficulties from my part as from his.</p>
 *
 * <h2>2. Use Case/Feature: Lang02.3</h2>
 *
 * <b>Issue in Jira:</b>
 * <p>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-32">LPFOURDG-32</a></p>
 * <b>Sub-tasks:</b><ul>
 * <li>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-334">LPFOURDG-334</a></li>
 * <li>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-335">LPFOURDG-335</a></li>
 * <li>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-336">LPFOURDG-336</a></li>
 * <li>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-337">LPFOURDG-337</a></li>
 * </ul>
 *
 * <h2>3. Requirement</h2>
 * <p>
 * Add support for array variables (temporary and global). Any variable can be
 * an array. When accessing a variable only by its name it should be inferred
 * the position 1 of the array. To explicitly access a position in a array
 * variable the position index should be specified inside brackets (following
 * the name of the variable). For example, the formula
 * <code>=@abc[2]:=123</code> will set the position 2 of the global variable
 * '@abc' to the value 123. Each position of an array can be of a different
 * type. For instance it should be possible to have an array with numeric and
 * alphanumeric values. There should also be a window in the sidebar to display
 * and edit the value of global variables. The values that appear in this window
 * should be automatically updated when the variables are updated.</p>
 *
 * <p>
 * <b>Use Case: Array Variables Support</b>: Both temporal and global variables
 * should now support an array of values. All values from one variable should be
 * accessed by its position on the array. Both variable types should be
 * initialized inside a opened Workbook. The Assign operations assigns a value
 * to one specific position of the Array. <code>=@global[2]:=3</code> - This
 * will assign the value 3 into the second position of the global (@) variable
 * '@global'. The token '_' is used for local (temporal) variables.</p>
 *
 * <p>
 * <b>Use Case: Edit global Variables Side Bar</b>: Cleansheets application
 * should have a side bar linked to the workbook in the current workspace. The
 * side bar should provide access to its Global variables and allow the editing
 * of its values in the used positions.</p>
 *
 *
 * <h2>4. Analysis</h2>
 * <p>
 * For the Analysis of this feature it’s important to perform and read both
 * analysis of the previous features that led to this one. First one being
 * <b>Support for Temporal Variables</b> and second being <b>Support for Global
 * Variables</b>. It’s known that these tokens '_' and '@' allow to initialize
 * these different Variables. But how does the process work?</p>
 *
 * <p>
 * By analyzing the current class <code>ExcelExpressionCompiler</code>, when the
 * method compile() is running and after the grammar interpretation, the
 * Expression <code>=@global:=2</code> its recognized as a <b>Binary
 * Operation</b>. The Parse Tree recognizes the node as an Assign Operator
 * (':=') and the Childs that descend from this node are both '@global', as Left
 * Operant, and '2' as Right Operant.</p>
 *
 * <p>
 * The operation then proceeds to recursively detect the reference belonging to
 * each Child. In this case, '@global' is recognized as a
 * <code>FormulaLexer.VARG_REF</code> and the value '2' as a
 * <code>FormulaLexer.NUMBER</code>. This excerpt of Code is important to
 * understand how to implement the feature:
 * <pre>
 * {@code
 *		if (node.getChildCount() == 0) {
 *		try {
 *			switch (node.getType()) {
 *				case FormulaLexer.CELL_REF:
 *					return new CellReference(cell.getSpreadsheet(), node.getText());
 *				case FormulaLexer.VART_REF:
 *					return new VariableLocalReference(cell, node.getText());
 *				case FormulaLexer.VARG_REF:
 *					return new VariableGlobalReference(cell, node.getText());
 *				case FormulaLexer.NUMBER:
 *					return new Literal(Value.parseNumericValue(node.getText()));
 *				case FormulaLexer.STRING:
 *					return new Literal(Value.
 *					parseValue(node.getText(), Value.Type.BOOLEAN, Value.Type.DATE));
 *				}
 *			} catch (ParseException e) {
 *			throw new FormulaCompilationException(e);
 *			}
 *		}
 * }
 * </pre>
 * <p>
 * For this feature, the main goal it’s to detect both variables as a
 * <code>FormulaLexer.ARRAY_REF</code> when, in the Left Operant, there are
 * Straight Brackets '[]' and one or more digits inside [{0,1,2,3,4,5,6,7,8,9}].
 * This requires adding code to <code>FormulaLexer.java</code>.</p>
 * <p>
 * Update on this Subject:</p>
 * <p>
 * It wasn't necessary to take this approach since both variables reference are
 * now inherited from <code>VariableArrayReference</code> as seen in the Class
 * Diagram of this Use Case. The class <code>ExcelExpressionCompiler</code>
 * still differs one local variable from a global variable as said before. The
 * way it was implemented, it's guaranteed the increase of cohesion.</p>
 *
 * <p>
 * It’s also necessary to edit the grammar rules, <code>Formula.g</code> and add
 * new tokens: <ul>
 * <li>
 * Left Straight Bracket - [;</li>
 * <li>
 * Right Straight Bracket - ];</li>
 * </ul>
 * Both under the Miscellaneous operators.
 * <p>
 * The new rules to add would be similar to this:<ul>
 * <li>
 * VART_REF (LSBRA DIGIT+ RSBRA)?; - Temporal Variable followed by
 * [*digit*]</li>
 * <li>
 * VARG_REF (LSBRA DIGIT+ RSBRA)? – Global Variable followed by [*digit*]</li>
 * </ul>
 *
 * <p>
 * By performing this changes it should be possible to retrieve the correct
 * reference and work with the expresiion - '@global[2]'.</p>
 * <p>
 * From the previous Features two classes are important
 * <code>VariableLocalReference</code> and <code>VariableGlobalReference</code>.
 * Both classes create a reference from where(Cell) the variable is initialized.
 * An object of <code>VariableLocalReference</code> is linked to a Cell, with no
 * Serialization, which means that, at close-up, the variables and their
 * correspondent Value would not be saved, also, a Local Variable is only known
 * inside that Cell, without the possibility to use it outside of it. However,
 * an object of <code>VariableGlobalReference</code> is linked to a Workbook and
 * saved with it. For as long as that Workbook prevails the variable and its
 * correspondent values should also exist.
 * <p>
 * A possible Solution would be to add at both this classes an ArrayList of
 * Variables to store the assign Values. This would led to a change in both
 * Classes <code>Workbook.java</code> and <code>CellImpl.java</code> that are
 * currently using an HashMap(String,Value) to assign to a variable (String) one
 * and only one Value (Value).</p>
 *
 * <h3>First Analysis Diagram</h3>
 *
 * <p>
 * <img src="http://i.imgur.com/ZZ2FQlV.jpg" alt="Class Diagram Analysis"></p>
 *
 * <h2>5. Design</h2>
 *
 * <h3>Class Diagram of Use Case - Array Variable Support.</h3>
 * <p>
 * <img src="http://i.imgur.com/y319gjS.jpg" alt="Class Diagram"></p>
 *
 * <h3>Class Diagram of Use Case - Edit Variable SideBar.</h3>
 * <p>
 * <img src="doc-files/lang02.3_class_diagram.png" alt="Class Diagram"></p>
 *
 * <h3>Sequence Diagram of Use Case - Edit Variable SideBar.</h3>
 * <p>
 * <img src="doc-files/lang02.3_sequence_diagram.png" alt="Sequence Diagram"></p>
 *
 * <h3>Functional Tests</h3>
 * <p>
 * Tests will be performed to test the updated Grammar to check if it recognizes
 * all tokens and rules associated with Array Reference. All
 * <code>evaluate()</code> methods should also be tested for their specific
 * behaviour.</p>
 * <p>
 * Grammar works as expected and is now identifying the new tokens and
 * rules.</p>
 *
 * <p>
 * Tests were added for the <code>AssignTest</code> to test if the value
 * assigned to a specific position of the Array works as expected.The tests
 * performed in this Class also allow to test if the
 * <code>ExcelExpressionCompiler</code> returns the <code>Reference</code>
 * required to interpret whether if it is a <code>VariableLocalReference</code>
 * or <code>VariableGlobalReference</code>.</p>
 *
 * <p>
 * Workbook Test Class also got updated. It's now testing methods regarding the
 * variables update and variables duplication.</p>
 *
 * <p>
 * Finally Tests were added to the Controller Extension to test the parse method
 * and it's also working as expected.</p>
 *
 *
 *
 *
 * <h3>UC Realization</h3>
 * <p>
 * This feature was divided into two Use Case due to the extensive amount of
 * work required. The separation provided a better Analysis to set the paths on
 * how to implement.</p>
 *
 * <h3>Classes</h3>
 * <p>
 * The list above has sorted information to help understand what classes were
 * added, what classes were updated and what operations they provide:<ul>
 * <li><b>Formula.g</b>:Added two new tokens and two new rules so the
 * application is able to recognize an VariableArray, as explained above in the
 * analysis.<ul>
 * <li>LSBRA: '[' ;</li>
 * <li>RSBRA: ']' ;</li>
 * <li>VART_REF : UNDR ( LETTER | DIGIT )+ (LSBRA DIGIT+ RSBRA)?;</li>
 * <li>VARG_REF : ARRB ( LETTER | DIGIT )+ (LSBRA DIGIT+ RSBRA)?;</li></ul>
 *
 * <li><b>VaribleGlobalReference</b>: Updated. This class now extends
 * VariableArrayReference since all Variables must be an array. This
 * implementation is better described in the Class Diagram provided above.</li>
 *
 * <li><b>VaribleLocalReference</b>: Updated. This class now extends
 * VariableArrayReference as well.</li>
 *
 * <li><b>VariableArrayReference</b>: Created. This class provides the necessary
 * operations for the creation of a VariableArray.</li>
 *
 * <li><b>CellImpl</b>: Updated. This class was updated according to the design,
 * it uses the concept of VariableArray and saves its variables in a ArrayList.
 * The methods implemented operate directly with this ArrayList whether to add
 * new values to one specific variable, add a new variable or search for a
 * specific variable of value.</li>
 *
 * <li><b>Workbook</b>: Updated. This class got similar updates as CellImpl
 * except for some bonus methods to work alongside the sidebar required to
 * implement.</li>
 *
 * <li><b>Assign</b>: Updated. Among the most important Class to look at when
 * analysing this feature. The Assign is responsible for creating a new
 * variable, assign it the given value and add it to the Workbook, in case of a
 * global variable, or to a Cell, in case of local variable.</li>
 *
 * <li><b>VariableArray</b>: Created. A new concept for variable was
 * implemented. Both Cell and Workbook work with objects from this Class. For
 * this use case its all.</li></ul>
 *
 * <p>
 * The Classes listed below where added for Use Case 2 of this Feature<ul>
 * <li><b>WorkbookGlobalVariableUI.</b>Extends UIExtension.</li>
 * <li><b>WorkbookGlobalVariableExtension.</b> Class read by
 * "Extensions.props".</li>
 * <li><b>WorkbookGlobalVariableController.</b> SideBar Controller.</li>
 * <li><b>WorkbookGlobalVariablePanel</b>: Provides the main interaction with
 * the user.</li>
 * <li><b>WorkbookGlobalVariableEditDialog:</b>Provides Edit Value of variable
 * interaction with the user.</li>
 * <li><b>WorkbookGlobalVariableAddDialog.</b>Provides Add Value of variable
 * interaction with the user.</li></ul>
 *
 *
 * <h3>Design Patterns and Best Practices</h3>
 * <p>
 * Singleton Pattern implemented by ExtensionManager.</p>
 * <p>
 * Low Cowpling - High Cohesion.</p>
 * <p>
 * Observer Pattern - This implementation was required to automatically update
 * the Global Variables List once another Workbbook is opened to the current
 * Workspace.</p>
 *
 * <h2>6. Implementation</h2>
 * <p>
 * The Analysis and Design provide the big picture on what and how the
 * implementation was proceeded.</p>
 *
 * <h3> The Side Bar </h3>
 * <p>
 * This feature side bar provides the major interaction with the user and access
 * to its functionalities.The user is able to not only initialize global
 * variables from the Spreadsheet but also from the side bar. It was implemented
 * along side 2 others Dialog Windows:</p>
 * <p>
 * <b>WorkbookGlobalVariablePanel</b>: Has 4 components associated with it: 2
 * jButton and 2 jList.<ul>
 * <li><b>"Refresh" button</b>: This button allows to refresh all variables and
 * its values from the Workbook opened. Only required when a value or variable
 * is updated on the Spreadsheet itself. If the changes occurs on the side bar
 * side, the Workbook is updated automatically by reevaluating all cells from
 * it.</li>
 * <li><b>"Add new Variable" button</b> - This button allows to initialize new
 * variables from the side bar as well as update the current values in any
 * specific position by providing the name of the existing value.</li>
 * <li><b>Varible List</b> - (DefaultListModel). This list contains the global
 * variables of a Workbook. It's associated with a mouse interaction
 * (MouseEvent). By pressing one time the correspondent values and positions are
 * presented to the user. This lead us to the second list:</li>
 * <li><b>Values List</b> - (DefaultListModel). This list also has implemented a
 * mouse interaction (MouseEvent). By pressing twice on a value a Dialog Window
 * appears where the user can update it. When updating through the side Bar, all
 * cell dependencies are automatically updated.</li>
 * </ul>
 *
 * <p>
 * <b>WorkbookGlobalVariableEditDialog </b>: Has the essential functions to
 * retrieve the new value data.</p>
 * <p>
 * <b>WorkbookGlobalVariableAddDialog </b>: Has the essential functions to
 * retrieve the new variable data.</p>
 *
 * <h3>Final Version of the Side Bar Panel</h3>
 *
 * <p>
 * <img src="http://i.imgur.com/IelReTR.png" alt="Side Bar"></p>
 * <p>
 * <img src="http://i.imgur.com/gGDquRf.png" alt="Edit Window"></p>
 * <p>
 * <img src="http://i.imgur.com/DUHEeUL.png" alt="Add new Variable Window"></p>
 *
 *
 * <h2>7. Integration/Demonstration</h2>
 * <p>
 * During this week I focused more on my individual feature. Also did pair
 * programming with my colleague Rúben Teixeira in his feature as well in mine
 * when needed.</p>
 *
 * <h2>Work Evidences</h2>
 * <p>
 * <ul>
 * <li>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/610d2a844e6f8eb2b426505c55d431e42be7b5be">First
 * Analysis and Formula.g File update.</a></li>
 * <li>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/df3ac738e560927f6f8146c258c569262ef94fe5">Second
 * Analysis, First spets into Design and Implementation.</a></li>
 * <li>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/4e40ff5137f6bb96a3a1514bb1c2c43608a59339">First
 * Tests Solutions accompanied by Implementation according to the
 * Design.</a></li>
 * <li>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/e2d4849c0e57e4bb8fb15608b93511b171a8ca3e">Started
 * and mostly finished Implementation of this Feature Side Bar</a></li>
 * <li>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/1b363d0899b523d78bf187b4ae251e695a57e599">Fundamental
 * Updates to Design; new Tests Solutions and Feature deployment (fully
 * available). </a></li>
 * <li>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/b36c013dad1cb26ba646f67b62b5287352420c89">Final
 * Testing</a></li>
 * </ul>
 *
 * <h2>8. Final Remarks</h2>
 * <p>
 * The feature was implemented will all necessary requirements and a bonus one,
 * the ability to add a new global variable from the side bar.</p>
 * <p>
 * TDD approach was used alongside the implementation due to this feature
 * approach. All tests made cover 100% of the code outside UI.</p>
 *
 * <h2>9. Work Log</h2>
 * <p>
 * <b>Sunday</b>
 * <p>
 * Today:
 * <p>
 * 1. Started Analysis.
 * <p>
 * 2. Updated Formula.g file according to new Tokens and Expressions.
 * <p>
 * Blocking:
 * <p>
 * 1. nothing.
 *
 * <p>
 * <b>Monday</b>
 * <p>
 * Yesteday:
 * <p>
 * 1. Analysis developed as expected.
 * <p>
 * Today:
 * <p>
 * 1. First Design Solutions for first Use Case of this feature.
 * <p>
 * 2. Test Solutions for first Use Case.
 * <p>
 * 3. Implementation of the first Use Case.
 * <p>
 * 4. Reunion with Product Owner.</p>
 * <p>
 * 5. Reunion with Manager.</p>
 * <p>
 * Blocking:</p>
 * <p>
 * 1. nothing.</p>
 *
 * <p>
 * <b>Tuesday</b>
 * <p>
 * Yesteday:
 * <p>
 * 1. All Tasks from yesterday were done as expected.
 * <p>
 * Today:
 * <p>
 * 1. Updated Diagrams for first Use Case.
 * <p>
 * 2. Started Design - Class placement for Second Use Case of this Feature.
 * <p>
 * 3. Reunion with Supervisor.
 * <p>
 * Blocking:
 * <p>
 * 1. nothing.
 *
 * <p>
 * <b>Wednesday</b>
 * <p>
 * Yesteday:
 * <p>
 * 1. All Tasks from yesterday were done as expected.
 * <p>
 * Today:
 * <p>
 * 1. Finished Implementation from second Use Case from this feature.
 * <p>
 * 2. Finished Design from second Use Case.
 * <p>
 * 3. Performed Tests.
 * <p>
 * 4. Reunion with Supervisor.
 * <p>
 * Blocking:
 * <p>
 * 1. nothing.
 *
 * <p>
 * <b>Thursday</b>
 * <p>
 * Yesteday:
 * <p>
 * 1. All Tasks from yesterday were done as expected.
 * <p>
 * Today:
 * <p>
 * 1. Worklog and Javadoc Updates.
 * <p>
 * 2. Demonstration.
 * <p>
 * Blocking:
 * <p>
 * 1. nothing.
 *
 * <h2>10. Self Assessment</h2>
 *
 * <h3>Design and Implementation:</h3>
 * <p>
 * 4</p>
 *
 * <h3>Teamwork:</h3>
 * <p>
 * 4</p>
 *
 * <h3>Technical Documentation:</h3>
 * <p>
 * 4</p>
 *
 * @author Pedro Gomes 1130383@isep.ipp.pt
 */
package csheets.worklog.n1130383.sprint4;

/**
 * This class is only here so that javadoc includes the documentation about this
 * EMPTY package! Do not remove this class!
 *
 * @author Pedro Gomes 1130383@isep.ipp.pt
 */
class _Dummy_ {
}
