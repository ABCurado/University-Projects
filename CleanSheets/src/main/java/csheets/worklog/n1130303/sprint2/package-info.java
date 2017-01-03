/**
 * Technical documentation regarding the work of the team member (1130303) Rui
 * Freitas during week2.
 *
 * <b>Scrum Master: no</b>
 *
 * <p>
 * <b>Area Leader: yes</b>
 *
 * <h2>1. Notes</h2>
 *
 * <h2>2. Use Case/Feature: Lang01.2</h2>
 *
 * Issue in Jira:
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-28">LPFOURDG-28</a>
 *
 * <h2>3. Requirement</h2>
 *
 * Add a new gramar to Cleansheets. This new grammar should be able to make
 * simple ahritmetic operations with monetary values.
 *
 * Exchange rates must be set on a configuration file by the user in order to
 * make value conversions.
 *
 * <p>
 * <b>Use Case "Monetary Language":</b>
 * Add a new formulas language (currently Cleansheets only has the Excel
 * formulas that begin with the character '='). The new language should do only
 * calculations related to currencies. The character that begins the formula
 * should be '#'. The formula should only accept the addition, subtraction,
 * multiplication and division operators. Operands are monetary values in which
 * it is necessary to provide the currency (Ex: 10.21€, 1.32£ or 0.20$). All
 * expressions are required to be contained within brackets with the currency
 * prefix in which we want the result. Ex: '#euro {10.32$ + 12.89£ }' or
 * '#dollar{ 10.32$ + 12.89£ }' or '#pound{10.32$ + 12.89£ } '. For the user to
 * use this language instead of the 'regular' Excel language it should start the
 * formula by the character '#' instead of the '=' character. Cleansheets should
 * also provide a way for setting exchange rates (by means of a configuration).
 * The implementation should avoid the use of numbers in floating point
 * representation (e.g., float, double) in order to avoid precision problems.
 *
 *
 * <h2>4. Analysis</h2>
 *
 * As required a new grammar should be created/added to the application to
 * interpret the user input. In order to process this grammar a new expression
 * compiler also needs to be created.
 *
 * The first problem is that each operand has a currency and then the result of
 * the operation has to be converted to the given currency. (eg:
 * #dollar{12€+45$})
 *
 * My first task was learning how the application is integrated with a grammar
 * (ANTRL) and then understand how the interpreted result was used.
 *
 *
 * <h2>5. Design</h2>
 *
 * <h3>5.1. Tests</h3>
 * <p>
 * TODO How to test lang function area?
 *
 * <h3>5.2. UC Realization</h3>
 * <p>
 * To accomplish this user story I need to create a new grammar file with the
 * necessary grammar to interpret the user input.
 *
 * The second step is create a new <code>ExpressionCompiler</code> capable of
 * convert a given input to a expression using the above mentioned grammar.
 *
 * The third step is create the necessary operators and functions to handle the
 * processed values.
 *
 * Given this input example: #dollar{12€+12.44$}
 *
 * I defined that all values are converted to Euro currency. This way the
 * conversion process is much easier to implement.
 *
 * So in order to do that 3 <code>UnaryOperator</code> must exist:
 * <code>EuroOperator</code>, <code>DollarOperator</code> and
 * <code>PoundOperator</code>. This operators take the preciding value and
 * convert it to Euro currency. This is done through an utility class
 * <code>ExchangeRateConverter</code> using an exchanging rate defined in a
 * configuration file (cleansheets.properties).
 *
 * Value conversion done, 4 <code>BinaryOperator</code> must exist (each one to
 * every arithmetic operation): <code>MonetaryAdder</code>,
 * <code>MonetarySubtracter</code>, <code>MonetaryDivider</code> and
 * <code>MonetaryMultiplier</code>. There are already operators to do the
 * required operations but since I use a diferent type of value I had to create
 * this ones (see below). As operators are recognized by their identifiers (and
 * there are already operators to the required operations), I add "€" to the
 * operation symbol just before the selection of the operator (see
 * <code>MonetaryExpressionCompiler</code>).
 *
 * The finish operation is done by a <code>Function</code>. Since there are 3
 * currencies I have to create 3 new functions: <code>Dollar</code>,
 * <code>Euro</code> and <code>Pound</code>. This function just take one
 * argument that is the operation result (Euro currency) and convert it to the
 * necessary currency returning the result that is viewed in the cell.
 *
 * <p>
 * Notes: Configuration file with exchanging rates: cleansheets.properties. The
 * feature's requirements are explicit about the data type used. I used a
 * <code>Money</code> class used in EAPLI and made the necessary changes to the
 * <code>Value</code> class. This way there is no problem with floating point
 * data types.
 *
 *
 * To accomplish this user story we will need to create a subclass of Extension.
 * We will also need to create a subclass of UIExtension. For the sidebar we
 * need to implement a JPanel. In the code of the extension
 * <code>csheets.ext.style</code> we can find examples that illustrate how to
 * implement these technical requirements. The following diagrams illustrate
 * core aspects of the design of the solution for this use case.
 * </p>
 *
 * <h3>5.3. Classes</h3>
 *
 * TODO!!! Class diagram!
 *
 * <h3>5.4. Design Patterns and Best Practices</h3>
 * <p>
 * TODO
 * </p>
 *
 * <h2>6. Implementation</h2>
 *
 * <code>csheets.formula.compiler.MonetaryExpressionCompiler</code>
 * <code>csheets.formula.lang.monetary.Dollar</code>
 * <code>csheets.formula.lang.monetary.DollarOperator</code>
 * <code>csheets.formula.lang.monetary.Euro</code>
 * <code>csheets.formula.lang.monetary.EuroOperator</code>
 * <code>csheets.formula.lang.monetary.Pound</code>
 * <code>csheets.formula.lang.monetary.MonetaryAdder</code>
 * <code>csheets.formula.lang.monetary.MonetaryDivider</code>
 * <code>csheets.formula.lang.monetary.MonetaryMultiplier</code>
 * <code>csheets.formula.lang.monetary.MonetarySubtracter</code>
 *
 * <p>
 * -Also refer all other artifacts that are related to the implementation and
 * where used in this issue. As far as possible you should use links to the
 * commits of your work-
 * </p>
 * see:
 * <a href="../../../../csheets/core/formula/lang/monetary/package-summary.html">csheets.core.formula.lang.monetary</a>
 *
 * <h2>7. Integration/Demonstration</h2>
 *
 * Since the feature I implemented this sprint was about a whole new grammar and
 * new operators/functions I had no really comunication about it with others
 * colleagues.
 *
 * <h2>8. Final Remarks</h2>
 *
 * I will add a new "divide" method to <code>Money</code> class. Currently, this
 * operation is done by doing a regular "divider" mathematic operation wich
 * could lead to some rounding issues.
 * <h2>9. Work Log</h2>
 *
 * <p>
 * <b>Saturday</b>
 * </p>
 * 1. Analysis of the feature. Understanding how this funcional area works and
 * how ANTLR is incorporated into the application.
 * <p>
 * Blocking:
 * </p>
 * 1. Needed a new grammar or using the existing one? (Producted owner clarified
 * 1º option).
 * <p>
 * <b>Sunday</b>
 * <p>
 * 1. Design of the UC and started the implementation. New grammar created.
 * <p>
 * Blocking:
 * <p>
 * 1. Understanding how to use functions and operators.
 * <p>
 * <b>Monday</b>
 * <p>
 * 1. Implementation completed: created new operators and functions. Needs to be
 * tested!
 * <p>
 * Blocking:
 * <p>
 *  * 1. Nothing.
 * <p>
 * <b>Tuesday</b>
 * <p>
 * 1. Since I completed the implementation of my feature I started helping some
 * colleagues with some issues.
 * <p>
 * Blocking:
 * <p>
 *  * 1. Nothing.
 * <p>
 * <b>Wednesday</b>
 * <p>
 * 1. Since I completed the implementation of my feature I started helping some
 * colleagues with some issues.
 * <p>
 * Blocking:
 * <p>
 *  * 1. Nothing.
 * <p>
 * <b>Thursday</b>
 * <p>
 * 1. Client presentation.
 * <p>
 * Blocking:
 * <p>
 *  * 1. Nothing.
 *
 * <h2>10. Self Assessment</h2>
 *
 * This week went well. I ended up completing the implementation off my feature
 * early so as a teamworker I went to help how was needing the most.
 * <h3>10.1. Design and Implementation:3</h3>
 *
 * <p>
 * <b>Evidences:</b>
 * </p>
 *
 *
 * <h3>10.2. Teamwork: ...</h3>
 *
 * <h3>10.3. Technical Documentation: ...</h3>
 *
 * /**
 *
 * @author Rui Freitas
 */
package csheets.worklog.n1130303.sprint2;

class _Dummy_ {
}
