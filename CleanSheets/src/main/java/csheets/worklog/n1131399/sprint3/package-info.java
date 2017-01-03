/**
 * Technical documentation regarding the work of the team member (1131399)
 * Marcelo Barroso during week3.
 *
 * <p>
 * <b>Scrum Master: no</b>
 *
 * <p>
 * <b>Area Leader: no</b>
 *
 * <h2>1. Notes</h2>
 *
 * <p>
 * This new fucnionalidade is a continuation of what was developed in the first
 * week, will create our loop functions, it is important to know well the system
 * functions and atribução values ​​should be working perfectly.
 *
 * <h2>2. Use Case/Feature: Lang01.3</h2>
 *
 * <p>
 * Issue in Jira:
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-29">LPFOURDG-27
 * Lang01.3- Eval and While Loops</a>
 *
 * <h2>3 Requirements</h2>
 * Add the 'Eval' function. This function has a single parameter that is a
 * string. When executed, this function will 'compile' the formula contained in
 * the only parameter and execute the resulting expression. The result of 'Eval'
 * is the result of the execution of the compiled expression. For example, if we
 * write the following formula '=“ 2 + 3 “' we get the string “2 + 3“ in the
 * cell. However, if we write the formula '= eval (“ 2 + 3 “)' the value
 * obtained in the cell is '5'. Add the following two loop functions: 'DoWhile'
 * and 'WhileDo'. The 'DoWhile' executes the first expression in loop while the
 * second expression evaluates to true. In each iteration of the loop the the
 * first expression is the first to be evaluated. The 'WhileDo' executes the
 * second expression in loop while the first evaluates to true. In each
 * iteration of the loop the the first expression is the first to be evaluated.
 * Example: '= @Counter:=1; WhileDo(Eval( “A“&@Counter)> 0;
 * {C1:=C1+Eval(“B“&@Counter); @Counter:=@Counter+1 } ) }'. In this example, the
 * cell C1 will get the sum of all the values of column B in that the
 * corresponding values in column A are greater than zero.
 *
 * <h2>4 Analysis</h2>
 * First you must create the Eval function, the user will enter an expression
 * and this will be interpreted as a formula to be exacutada, the result will be
 * the result of the expression. Then you need to enter the other loop
 * functions, as was done with the FOR the first week, these will consist of a
 * Do While and Do While as developers know it. In an initial naalise I do not
 * see many problems to solve what is required since been developing the
 * Lang01.1 the first week and I'm comfortable with it.
 *
 * <h2>5. Design</h2>
 * You need to create three new classes in csheets.core.formula.lang packget to
 * represent the three new functions. The first is the Eval basically it gets a
 * variable of type Value.Type.TEXT that contain the expression to run as a
 * formula, implementation will be done using the FormulaCompiler.getInstance ()
 * method. Compile (Cell cell, String source ); and the value is obtained to
 * evaluate () method instantiates the Formula class. The DoWhile and WhileDo
 * functions will be made just as it was made For function, what changes will be
 * the order of the cycle, but the proposal is to set the first parameter as the
 * expression to be executed and the second as the expression to be checked for
 * continuing the cycle.
 *
 * <h3>6. Tests</h3>
 *
 * <h2>7. Implementation</h2>
 *
 * <h2>8. Integration/Demonstration</h2>
 *
 * <h2>9. Final Remarks</h2>
 *
 * <h2>10. Work Log</h2>
 *
 * <p>
 * <b>Monday</b>
 * </p>
 * <p>
 * ...
 * </p>
 * <p>
 * <b>Tuesday</b>
 * </p>
 * <p>
 * ...
 * </p>
 * <p>
 * <b>Wednesday</b>
 * </p>
 * <p>
 * ...
 * </p>
 * <p>
 * <b>Thursday</b>
 * </p>
 * <p>
 * ...
 * </p>
 * <p>
 * <b>Friday</b>
 * </p>
 * <p>
 * ...
 * </p>
 *
 *
 * <h2>11. Self Assessment</h2>
 *
 * During this sprint, my work was mainly of analysis and study of the
 * application architecture.
 *
 * <h3>11.1. Design and Implementation:</h3>
 * <b>Evidences: ...</b>
 *
 * <h3>11.2. Teamwork: ...</h3>
 * ...
 *
 * <h3>11.3. Technical Documentation: ...</h3>
 * ...
 *
 * @author Marcelo Barroso 1131399
 */
package csheets.worklog.n1131399.sprint3;

/**
 * This class is only here so that javadoc includes the documentation about this
 * EMPTY package! Do not remove this class!
 *
 * @author alexandrebraganca
 */
class _Dummy_ {
}
