/**
 * Technical documentation regarding the work of the team member (1140423)
 * Renato Machado during week4.
 *
 * <p>
 * <b>Scrum Master: -(yes/no)- no</b>
 *
 * <b>Area Leader: -(yes/no)- no</b>
 * </p>
 *
 * <h2>1. Notes</h2>
 * 
 * <h2>2. Use Case/Feature: Lang07.3</h2>
 *
 * <p>
 * Issue in Jira:
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-47">LPFOURDG-47</a>
 * </p>
 * <p>
 * LPFOURDG-47
 *
 * Design and implement a mini API of the Cleansheets objects inside Beanshell.
 * The new API should provide access to objects like workbook, worksheet, cells,
 * macros, variables, etc. This API should prevent as far as possible access to
 * 'internal' classes of Cleansheets.
 *
 * </p>
 *
 * <h2>3. Requirement</h2>
 *
 * It should be possible to access Cleansheets objects inside Beanshell by providing a public API.
 *
 * <p>
 * <b>Use Case "Cleansheets API":</b>
 *
 * The user creates a new script and uses the API object from Cleansheets go gain access to Cleansheets objects in run time.
 *
 * <h2>4. Analysis</h2>
 *
 * <h3>Beanshell</h3>
 * <p>
 * BeanShell is a small, free, embeddable Java source interpreter with object
 * scripting language features, written in Java. BeanShell dynamically executes
 * standard Java syntax and extends it with common scripting conveniences such
 * as loose types, commands, and method closures like those in Perl and
 * JavaScript.
 * </p>
 * 
 * <h3>Cleansheets</h3>
 * <p>
 * The API should provide public methods to be able to retrieve:
 * </p>
 * 
 * <p>
 * Workbook
 * </p>
 * <p>
 * Worksheet
 * </p>
 * <p>
 * Cells
 * </p>
 * <p>
 * Macros
 * </p>
 * <p>
 * Variables
 * </p>
 * <p>
 * And a few other data types.
 * </p>
 * 
 * <p>
 * The API consists of being a small Facade object in which creates a layer of abstraction between Cleansheets classes and Beanshell. 
 * </p>
 * 
 * <p>
 * Since BeanShell executes an interpreter, all we need is to provide that interpreter an instance of the API in order to have access to it in run time.
 * </p>
 * 
 * <h4>Analysis diagram:</h4>
 *
 * <img src="doc-files/analysis.png" alt="Analysis">
 *
 * <h2>5. Design</h2>
 *  
 * <p>
 * API Class Diagram
 * </p>
 * 
 * <p>
 * <img src="doc-files/api_class_diagram.png" alt="API Class Diagram.">
 * </p>
 * 
 * <p>
 * Associating the API with the BeanShell.
 * </p>
 * 
 * <p>
 * Important: This diagram assumes that the interaction with the User was already made (check earlier feature increments), and therefore we are already on the run() method of BeanShell class.
 * </p>
 * <p>
 * <img src="doc-files/api_beanshell_connection.png" alt="Associating the API with the BeanShell.">
 * </p>
 * 
 * <h3>Tests:</h3>
 * 
 * <p>As for tests, we will just cover if BeanShells is capable of invoking the API, and do some small functional tests proving that the API is working as desired.</p>
 * 
 * <h2>6. Implementation</h2>
 *
 * <p>In regards to implementation, the API is pretty straight forward. It is a small Facade class that gathers information from other classes such as the UIController.</p>
 * <p>To allow the usage of the API through BeanShell scripts, we need to inject the API into the BeanShell interpreter. This way, the interpreter can understand when a user writes "api" and translate it into the CleanSheetsAPI object.</p>
 * 
 * <p>Since this feature increment only relates to BeanShell invoking the API, which uses internal methods of CleanSheets there is no need to test the API itself, nor if BeanShell can run scripts.</p>
 * <p>The reason for this is because those tests were already performed before the start of this feature increment, therefore it's important to test if the BeanShell interpreter is able to invoke the API, and do functional tests on the API functionality.</p>
 * 
 * <h3>Functional Tests</h3>
 * 
 * <p>Let's say we want to select the contents of all of the selected cells, sum them, and insert the result in the cell A1 on the active spreadsheet.</p>
 * 
 * <code>
 * import csheets.core.Cell;
 *
 * int result = 0;
 *
 * Cell[][] cells = api.getSelectedCells();
 *
 * for (i=0; i &lt; cells.length; i++) { for (j=0; j &lt; cells.length; j++) { result += cells[i][j].getValue().toNumber().intValue(); }}
 *
 * api.getCell(0, 0).setContent(String.valueOf(result));
 * </code>
 *
 * <p>Suppose we select 4 cells containing the values: 1, 2, 3, and 4. The end result should be 10 on the A1 cell.</p>
 * 
 * <p>After running the application and executing the given script, we successfully see that the end result is 10 on the A1 cell.</p>
 * 
 * <h2>7. Work Log</h2>
 *
 * <p>
 * 20/06/2016
 * </p>
 * <b>Monday</b>
 * <p>
 * Today
 * </p>
 * <p>
 * Started to work on the feature. Will try to get as much done as possible.
 * Will also fix some of the previous iterations on small aspects.
 * </p>
 * <p>
 * Blocking</p>
 * <p>
 * Nothing.</p>
 * 
 * <p>22/06/2016</p>
 * <b>Wednesday</b>
 * <p>
 * Today
 * </p>
 * <p>
 * About to finish my feature. Helped some colleagues.
 * </p>
 * <p>Blocking</p>
 * <p>Nothing.</p>
 * 
 * <h2>10. Self Assessment</h2>
 *
 * <p>
 * Outcome 3 ("Design and Implementation") - 3</p>
 * <p>
 * Outcome 5 ("Teamwork") - 3</p>
 * <p>
 * Outcome 6 ("Technical Documentation") - 3</p>
 *
 * <h3>10.1. Design and Implementation:</h3>
 *
 * <p>
 * <b>Evidences:</b>
 * </p>
 * 
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/changeset/c49f1aebee469894b18b580e434e582f82d2586c">Finished working on the analysis.</a>
 * </p>
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/changeset/9274d5e9d812aa01dd1631f01a5796224e65574b">Hotfix on last commit. (Did not commit the correct changes)</a>
 * </p>
 * 
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/changeset/07fb7ef5b0a1afa6a726d4803345ea8dfd045529">Finishing design. Still a few fixes to do.</a>
 * </p>
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/changeset/3ffefa55a29795b6a512f030418e5f321ad2e8e9">Finished design.</a>
 * </p>
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/changeset/44778b979a3c7359c18058fa53cf5d59ce70b658">Finished design.</a>
 * </p>
 * 
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/changeset/32078b9467446f790d194d7d6a4e1b71a182f13b">Fixed tests.</a>
 * </p>
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/changeset/61032575551072186bf3ce15464c054b08f96a46">Added and improved tests.</a>
 * </p>
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/0540b08f4390d6050b1ca77189f5ae0dc39bc670">Uncommented tests and added functional test to the worklog.</a>
 * </p>
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/235fa9a0f560a2c8834762e08097fb53bf52da45">Finished the skeleton of the API (and applied changes covered in the design).</a>
 * </p>
 * 
 * 
 * 
 * <h3>10.2. Teamwork: ...</h3>
 *
 * <h3>10.3. Technical Documentation: ...</h3>
 *
 * @author Renato Machado
 */
package csheets.worklog.n1140423.sprint4;

/**
 * This class is only here so that javadoc includes the documentation about this
 * EMPTY package! Do not remove this class!
 *
 * @author alexandrebraganca
 */
class _Dummy_ {
}
