/**
 * Technical documentation regarding the work of the team member (1131302) Gabriel Sousa during week1
 *
 * <p>
 * <b>Scrum Master: no</b>
 * </p>
 *
 * <p>
 * <b>Area Leader: no</b>
 * </p>
 * <h2>1. Notes</h2>
 * <p>
 * -Notes about the week's work.-
 * </p>
 *
 * <h2>2. Use Case/Feature: Lang01.1</h2>
 * <p>
 * Issue in Jira: http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-101
 * LPFOURDG-27 Lang01.1- Block of Instructions
 *
 * </p>
 *
 * <h2>3. Requirement</h2>
 * Add the possibility of writing blocks (or sequences) of instructions. A block
 * must be delimited by curly braces and its instructions must be separated by
 * ";". The instructions of a block are executed sequentially and the block
 * + * "result" is the result of the last statement of the block.
 *
 * <p>
 * <b> ###TODO### Use Case "Enter Comment on Cell":</b> The user selects the
 * cell where he/she wants to enter a comment. The system displays the current
 * comment of that cell. The user enter the text of the comment (or alters the
 * existing one). The system saves the comment of the cell.
 * </p>
 *
 * <h2>4.  Analysis</h2>
 * <p>
 * Since comments on cells will be supported in a new extension to cleansheets
 * we need to study how extensions are loaded by cleansheets and how they work.
 * The first sequence diagram in the section
 * <a href="../../../../overview-summary.html#arranque_da_aplicacao">Application
 * Startup</a> tells us that extensions must be subclasses of the Extension
 * abstract class and need to be registered in special files. The Extension
 * class has a method called getUIExtension that should be implemented and
 * return an instance of a class that is a subclass of UIExtension. In this
 * subclass of UIExtension there is a method (getSideBar) that returns the
 * sidebar for the extension. A sidebar is a JPanel.
 * </p>
 *
 * <h3>First "analysis" sequence diagram ####TODO!####</h3>
 * <p>
 * The following diagram depicts a proposal for the realization of the
 * previously described use case. We call this diagram an "analysis" use case
 * realization because it functions like a draft that we can do during analysis
 * or early design in order to get a previous approach to the design. For that
 * reason we mark the elements of the diagram with the stereotype "analysis"
 * that states that the element is not a design element and, therefore, does not
 * exists as such in the code of the application (at least at the moment that
 * this diagram was created).
 * </p>
 * <p>
 * <img src="doc-files/comments_extension_uc_realization1.png" alt="image">
 * </p>
 * <p>
 * From the previous diagram we see that we need to add a new "attribute" to a
 * cell: "comment". Therefore, at this point, we need to study how to add this
 * new attribute to the class/interface "cell". This is the core technical
 * problem regarding this issue.
 * </p>
 * 
 * <h3>Analysis of Core Technical Problem</h3>
 * <p>
 * We can see a class diagram of the domain model of the application
 * <a href="../../../../overview-summary.html#modelo_de_dominio">here</a>
 * From the domain model we see that there is a Cell interface. This defines the
 * interface of the cells. We also see that there is a class CellImpl that must
 * implement the Cell interface. If we open the {@link csheets.core.Cell} code
 * we see that the interface is defined as:
 * <code>public interface Cell extends Comparable &lt;Cell&gt;, Extensible&lt;Cell&gt;, Serializable</code>.
 * Because of the <code>Extensible</code> it seams that a cell can be extended.
 * If we further investigate the hierarchy of {@link csheets.core.Cell} we see
 * that it has a subclass {@link csheets.ext.CellExtension} which has a subclass
 * {@link csheets.ext.style.StylableCell}.
 * {@link csheets.ext.style.StylableCell} seems to be an example of how to
 * extend cells. Therefore, we will assume that it is possible to extend cells
 * and start to implement tests for this use case.
 * </p>
 * 
 * <p>
 * The <a href="http://en.wikipedia.org/wiki/Delegation_pattern">delegation
 * design pattern</a> is used in the cell extension mechanism of cleansheets.
 * The following class diagram depicts the relations between classes in the
 * "Cell" hierarchy.
 * </p>
 * <p>
 * <img src="doc-files/core02_01_analysis_cell_delegate.png" alt="image">
 *
 * </p>
 * <p>
 * One important aspect is how extensions are dynamically created and returned.
 * The <code>Extensible</code> interface has only one method,
 * <code>getExtension</code>. Any class, to be extensible, must return a
 * specific extension by its name. The default (and base) implementation for the
 * <code>Cell</code> interface, the class <code>CellImpl</code>, implements the
 * method in the following manner:
 * </p>
 * <pre>
 * {@code
 * 	public Cell getExtension(String name) {
 *		// Looks for an existing cell extension
 *		CellExtension extension = extensions.get(name);
 *		if (extension == null) {
 *			// Creates a new cell extension
 *			Extension x = ExtensionManager.getInstance().getExtension(name);
 *			if (x != null) {
 *				extension = x.extend(this);
 *				if (extension != null)
 *					extensions.put(name, extension);
 *			}
 *		}
 *		return extension;
 *	}
 * }
 * </pre> 
 * 
 * <p>
 * As we can see from the code, if we are requesting a extension that is
 * not already present in the cell, it is applied at the moment and then
 * returned. The extension class (that implements the <code>Extension</code>
 * interface) what will do is to create a new instance of its cell extension
 * class (this will be the <b>delegator</b> in the pattern). The constructor
 * receives the instance of the cell to extend (the <b>delegate</b> in the
 * pattern). For instance, <code>StylableCell</code> (the delegator) will
 * delegate to <code>CellImpl</code> all the method invocations regarding
 * methods of the <code>Cell</code> interface. Obviously, methods specific to
 * <code>StylableCell</code> must be implemented by it. Therefore, to implement
 * a cell that can have a associated comment we need to implement a class
 * similar to <code>StylableCell</code>.
 * </p>
 * <h2>5. Design</h2>
 *
 * <h3>5.1. Functional Tests</h3>
 * 
 * <p>
 * Basically, from requirements and also analysis, we see that the core
 * functionality of this use case is to be able to add an attribute to cells to
 * be used to store a comment/text. We need to be able to set and get its value.
 * Following this approach we can start by coding a unit test that uses a
 * subclass of <code>CellExtension</code> with a new attribute for user comments
 * with the corresponding method accessors (set and get). A simple test can be
 * to set this attribute with a simple string and to verify if the get method
 * returns the same string. As usual, in a test driven development approach
 * tests normally fail in the beginning. The idea is that the tests will pass in
 * the end.
 * </p>
 * see: <code>csheets.ext.comments.CommentableCellTest</code>
 *
 * <h3>5.2. UC Realization ####TODO!####</h3>
 * <p>
 * To realize this user story we will need to create a subclass of Extension. We
 * will also need to create a subclass of UIExtension. For the sidebar we need
 * to implement a JPanel. In the code of the extension
 * <code>csheets.ext.style</code> we can find examples that illustrate how to
 * implement these technical requirements. The following diagrams illustrate
 * core aspects of the design of the solution for this use case.
 * </p>
 * <p>
 * <b>Note:</b> It is very important that in the final version of this technical
 * documentation the elements depicted in these design diagrams exist in the
 * code!
 * </p>
 * 
 * <h3>Extension Setup</h3>
 * The following diagram shows the setup of the "comments" extension when
 * cleansheets is run.
 * <p>
 * <img src="doc-files/core02_01_design.png" alt="image">
 * </p>
 *
 * <h3>User Selects a Cell</h3>
 * The following diagram illustrates what happens when the user selects a cell.
 * The idea is that when this happens the extension must display in the sidebar
 * the comment of that cell (if it exists).
 * <p>
 * <img src="doc-files/core02_01_design2.png" alt="image">
 * </p>
 * 
 * <h3>User Updates the Comment of a Cell</h3>
 * The following diagram illustrates what happens when the user updates the text
 * of the comment of the current cell. To be noticed that this diagram does not
 * depict the actual selection of a cell (that is illustrated in the previous
 * diagram).
 * <p>
 * <img src="doc-files/core02_01_design3.png" alt="image">
 * </p>
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
 * <h2>6. Implementation</h2>
 *
 * -Reference the code elements that where updated or added-
 * <p>
 * -Also refer all other artifacts that are related to the implementation and
 * where used in this issue. As far as possible you should use links to the
 * commits of your work-
 * </p>
 * see:
 * <p>
 * <a href="../../../../csheets/ext/comments/package-summary.html">csheets.ext.comments</a>
 * </p>
 * <p>
 * <a href="../../../../csheets/ext/comments/ui/package-summary.html">csheets.ext.comments.ui</a>
 * </p>
 *
 * <h2>7. Integration/Demonstration</h2>
 *
 * -In this section document your contribution and efforts to the integration of
 * your work with the work of the other elements of the team and also your work
 * regarding the demonstration (i.e., tests, updating of scripts, etc.)-
 *
 * <h2>8. Final Remarks</h2>
 * <p>
 * -In this section present your views regarding alternatives, extra work and
 * future work on the issue.-
 * </p>
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
 * </p>
 * <b>Monday</b>
 * <p>
 * Yesterday I worked on:
 * </p>
 * 1. Configure the IDE to start working.
 * <p>
 * Today
 * </p>
 * 1. Generate JavaDoc and read him.
 * 2.Debub the project.
 * <p>
 * Blocking:
 * </p>
 * 1. -nothing-
 * <p>
 * <b>Tuesday</b>
 * </p>
 * Yesterday I worked on:
 * <p>
 * 1. Configure the IDE to start working. Cloning of the projec. Read javadoc. Understand the project.
 * </p>
 * Today
 * <p>
 * 1. Understand the problem/analysis to begin the implementation.
 * </p>
 * Blocking:
 * <p>
 * 1. ...
 * </p>
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
 * @author Joao Martins
 */
package csheets.worklog.n1131302.sprint1;

/**
 * This class is only here so that javadoc includes the documentation about this
 * EMPTY package! Do not remove this class!
 *
 * @author alexandrebraganca
 */
class _Dummy_ {
}
