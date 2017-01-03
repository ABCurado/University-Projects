/**
 * Technical documentation regarding the work of the team member (1140329) 
 * Rafael Rocha during week4. 
 * 
 * <p>
 * <b>Scrum Master: -(yes/no)- no</b>
 * 
 * <p>
 * <b>Area Leader: -(yes/no)- no</b>
 * 
 * <h2>1. Notes</h2>
 * 
 * <p>
 * -In this section you should register important notes regarding your work during the week.
 * For instance, if you spend significant time helping a colleague or if you work in more than a feature.-
 *
 * <h2>2. Use Case/Feature: Lang04.3</h2>
 * Issue in Jira: <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-38">LPFOURDG-38: Insert Formula Advanced Wizard</a>
 * <p>
 * Sub-Tasks in Jira:  <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-338">LPFOURDG-338: Analysis</a>; 
 *                     <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-339">LPFOURDG-339: Design</a>; 
 *                     <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-340">LPFOURDG-340: Tests</a>; 
 *                     <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-341">LPFOURDG-341: Implementation</a>
 * <p>
 * 
 * LPFOURDG-38
 * 
 * The wizard should now have an edit box where the formula is gradually 
 * constructed. The user should be able to edit the formula text freely. 
 * The functions or operators (and the values of its parameters/operands)
 * selected from the list should now be inserted in the position of the cursor
 * in the new editbox. The wizard should continue to have an area to display 
 * the evaluation of the formula (that should be produced dynamically, 
 * as the user edits the formula). The wizard should also have a new window 
 * that should display the structure of the formula expression like an abstract
 * syntax tree (i.e., the structure resulting from the formula compilation). 
 * When the user clicks a tree element its respective text in the edit box 
 * should appear highlighted.
 * 
 * <h2>3. Requirement</h2>
 * The wizard now has to let the user insert (in the cursor's position) 
 * as many functions as he wants and let him edit them all as he sees fit. 
 * At the same time, the wizard has to dynamically show the formula's result or,
 * if the formula has syntax errors, an evaluation of the formula.
 * The wizard also has to show a representation of the formula expression in 
 * an abstract syntax tree. If the user selects on of the tree's elements, its
 * respective text in the edit box should be highlighted.
 * 
 * <p>
 * <b>Use Case "Insert Formula Advanced Wizard":</b> The user can now select 
 * multiple functions. The system inserts these functions in the cursor's 
 * position in the edit box. The user can freely edit the formula. As the user
 * constructs the formula, the system dynamically shows the formula's result or,
 * if the formula has syntax errors, an evaluation of the formula. The user can
 * select to see the formula expression in a abstract syntax tree. If the user 
 * selects one of the elements in that tree, the system highlights the 
 * respective text in the edit box.
 *  
 * <h2>4. Analysis</h2>
 * 
 * <b>WizardFrame Class:</b>
 * <p>
 * A window that shows all the available functions and a description of each 
 * (if the user selects the help button), the syntax of each function, and a 
 * edit box where the user can construct a formula.</p>
 *
 * <b>WizardController Class:</b>
 * <p>
 * This class deals with the formula execution, the update of the active cell's
 * formula, and the construction of the abstact syntax tree representation of 
 * the built formula.</p>
 *
 * <b>Language Class:</b>
 * <p>
 * This class consists of a factory for creating certain types of language 
 * elements. It returns all the suported formula functions in the system.</p>
 * 
 * <b>FormulaCompiler Class:</b>
 * <p>
 * This class consists of a compiler that generates formulas from strings.</p>
 * 
 * <b>Formula Class:</b>
 * <p>
 * This class represents a formula and returns an evaluation of its formula 
 * expression.</p>
 * 
 * <b>ExcelExpressionCompiler Class:</b>
 * <p>
 * This class consists of a compiler that generates formulas from strings. In
 * this case, the method compileTree() will be useful to get a tree 
 * representation of a given formula.</p>
 * 
 * <b>MonetaryExpressionCompiler Class:</b>
 * <p>
 * This class consists of A compiler that generates Monetary-style formulas
 * from strings. Like the ExcelExpressionCompiler Class, the method 
 * compileTree() will be useful to get a tree representation of a given formula.
 * </p>
 * 
 * <h3>First "analysis" sequence diagram</h3>
 * The following diagram depicts a proposal for the realization of the 
 * previously described use case. We call this diagram an "analysis" use case
 * realization because it functions like a draft that we can do during analysis
 * or early design in order to get a previous approach to the design.
 * For that reason we mark the elements of the diagram with the stereotype
 * "analysis" that states that the element is not a design element and, 
 * therefore, does not exists as such in the code of the application
 * (at least at the moment that this diagram was created).
 * 
 * <h4>Insert Formula Advanced Wizard proposal analysis</h4>
 * <p>
 * <img src="doc-files/lang04_03_analysis.png" alt="image"> 
 * <p>
 * 
 * From the previous diagram we see that we need to add new functionalities to 
 * the UI: insertion of multiple functions and dynamically show the formula's 
 * result. We also need to implement a new window that shows an abstract syntax
 * tree representation of the formula expression. 
 * These are the core technical problems regarding this issue.
 * 
 * <h2>5. Design</h2>
 *
 * <h3>5.1. Functional Tests</h3>
 *
 * <h3>5.2. UC Realization</h3>
 * To realize this user story we will need to implement 
 * The following diagrams illustrate core aspects of the design of the solution for this use case.
 * <h3>User starts the Formula Wizard (Insert Formula Advanced Wizard)</h3>
 * The following diagram shows how the wizard constructs a formula and its 
 * respective abstract syntax tree.
 * <p>
 * <img src="doc-files/lang04_03_design.png" alt="image">
 * 
 * <h3>5.3. Classes</h3>
 * <p>
 * <b>Class Diagram</b>
 * <img src="doc-files/lang04_03_class_diagram.png" alt="image">
 * 
 * <h3>5.4. Design Patterns and Best Practices</h3>
 * 
 * 
 * <h2>6. Implementation</h2>
 * 
 * Updated the WizardFrame, WizardController, WizardButton, and Frame 
 * (changed the button's position in the formula bar). Also updated 
 * ExcelExpressionCompiler and MonetaryExpressionCompiler:
 * 
 * <p>https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/4ba4029f88a4b869378dee4ce508e0d3ccf01913</p>
 * <p>https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/018a4216173641a92b5886f87370c6b6dfbd0165</p>
 * <p>https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/df45722bfab3a038b466405917430c3b1a2e8348</p>
 * 
 * <h2>7. Integration/Demonstration</h2>
 * 
 * Updated some tests and added some more:
 * <p>https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/7c5517cfe8e5cd059e098e71013bc2002daf9a44</p>
 * <p>https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/e11d593100bba323b7f3b947a9ad5761aa81b130</p>
 * 
 * <h2>8. Final Remarks</h2>
 * 
 * The method fillTree in WizardTreeFrame is a bit sloppy and could be better
 * implemented, since it is easy to get lost in the code logic.
 * 
 * <h2>9. Work Log</h2> 
 * <b>Friday</b>
 * <p>
 * Today
 * <p>
 * 1. Started analysis of Lang04.3
 * <p>
 * 2. Started design of Lang04.3
 * <p>
 * 3. Studied the program's code to help with the analysis and design.
 * <p>
 * Blocking:
 * <p>
 * 1. I don't know the best approach to construct an abstract syntax tree. 
 * <p>
 * <b>Saturday</b>
 * <p>
 * Yesterday I worked on: 
 * <p>
 * 1. Started analysis of Lang04.3
 * <p>
 * 2. Started design of Lang04.3
 * <p>
 * 3. Studied the program's code to help with the analysis and design.
 * <p>
 * Today
 * <p>
 * 1. Finishing analysis of Lang04.3
 * <p>
 * 2. Working on design of Lang04.3
 * <p>
 * 3. Working on the implementation of Lang04.3
 * <p>
 * Blocking:
 * <p>
 * 1. I don't know the best approach to construct an abstract syntax tree. 
 * <p>
 * <b>Sunday</b>
 * <p>
 * Yesterday I worked on: 
 * <p>
 * 1. Finishing analysis of Lang04.3
 * <p>
 * 2. Working on design of Lang04.3
 * <p>
 * 3. Working on the implementation of Lang04.3
 * <p>
 * Today
 * <p>
 * 1. Planning out tests for the use case.
 * <p>
 * Blocking:
 * <p>
 * 1. I don't know the best approach to construct an abstract syntax tree. 
 * <p>
 * <b>Monday</b>
 * <p>
 * Yesterday I worked on: 
 * <p>
 * 1. Planning out tests for the use case.
 * <p>
 * Today
 * <p>
 * 1. Working on design of Lang04.3
 * <p>
 * 2. Working on the implementation of Lang04.3
 * <p>
 * Blocking:
 * <p>
 * 1. -nothing-
 * <p>
 * <b>Tuesday</b>
 * <p>
 * Yesterday I worked on: 
 * <p>
 * 1. Working on design of Lang04.3
 * <p>
 * 2. Working on the implementation of Lang04.3
 * <p>
 * Today
 * <p>
 * 1. Helping other colleagues on their use cases.
 * <p>
 * 2. Finishing the implementation of Lang04.3
 * <p>
 * Blocking:
 * <p>
 * 1. -nothing-
 * <p>
 * <b>Wednesday</b>
 * <p>
 * Yesterday I worked on: 
 * <p>
 * 1. Helping other colleagues on their use cases.
 * <p>
 * 2. Finishing the implementation of Lang04.3
 * <p>
 * Today
 * <p>
 * 1. Finishing tests
 * <p>
 * 2. Updating the implementation of Lang04.3
 * <p>
 * 3. Helping other colleagues with their use cases (for example, IPC07.3).
 * <p>
 * Blocking:
 * <p>
 * 1. -nothing-
 * <p>
 * <b>Thursday</b>
 * <p>
 * Yesterday I worked on: 
 * <p>
 * 1. Finishing tests
 * <p>
 * 2. Updating the implementation of Lang04.3
 * <p>
 * 3. Helping other colleagues with their use cases (for example, IPC07.3).
 * <p>
 * Today
 * <p>
 * 1. Fixing some UI issues.
 * <p>
 * Blocking:
 * <p>
 * 1. -nothing-
 * 
 * <h2>10. Self Assessment</h2> 
 * 
 * <h3>10.1. Design and Implementation: 4</h3>
 * 
 * <b>Evidences:</b>
 * <p>
 * url of commit:  - description: this commit is related to the implementation of the design pattern -
 * 
 * <p>https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/4ba4029f88a4b869378dee4ce508e0d3ccf01913 :
 *  - description: this commit is related to most of the development of the use
 * case (Analysis, Design, Implementation) -</p>
 * <p>https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/018a4216173641a92b5886f87370c6b6dfbd0165 :
 *  - description: this commit is related to the construction of the tree representation
 * of a formula expression -</p>
 * <p>https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/df45722bfab3a038b466405917430c3b1a2e8348 :
 *  - description: this commit is related to the feature of highlighting the corresponding text
 * of a selected element</p>
 * 
 * <h3>10.2. Teamwork: </h3>
 * 
 * Throughout this week I've helped out a lot of my colleagues on the 
 * implementation of their Use Cases, since I had already finished most of my 
 * Use Case on the past weekend.
 * 
 * <h3>10.3. Technical Documentation: ...</h3>
 * 
 * @author Rafael Rocha
 */

package csheets.worklog.n1140329.sprint4;

/**
 * This class is only here so that javadoc includes the documentation about this EMPTY package! Do not remove this class!
 * 
 * @author RafaelRocha
 */
class _Dummy_ {}

