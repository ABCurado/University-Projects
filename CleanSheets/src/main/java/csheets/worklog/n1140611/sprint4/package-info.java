/**
 * Technical documentation regarding the work of the team member (1140611)
 * Carlos Santos during week4.
 *
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
 * <h2>2. Use Case/Feature: Core03.3</h2>
 * Issue in Jira:
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-8">LPFOURDG-8</a>
 * <p>
 * -Include the identification and description of the feature-
 *
 * <h2>3. Requirement</h2>
 * Automatically sort a range of cells.
 * <p>
 * <b>Use Case "Auto-Sorting" :</b> Automatically sort a range of cells. After
 * defining a range of cells like in the previous FI Cleansheets should
 * automatically maintain the order as the user updates the contents of the
 * range. Visual marks should be displayed for the range of cells as well as
 * visual indicators for the sorting column and sorting order. The user should
 * be able to change the sorting column by clicking on a new column. Clicking on
 * the sorting column should change the sorting order.
 *
 * <h2>4. Analysis</h2>
 * <h3>Auto-Sorting</h3>
 * The user selects a range of cells. When a user updates the content of the
 * range of cells sould maintain the order.
 *
 * <h3>First "analysis" sequence diagram</h3>
 *
 * <h4>Auto-Sorting</h4>
 *
 * <h4>Analysis of Core Technical Problem</h4>
 * The core of sort automatically is to maintain the order after changes.
 * <p>
 * <img src="doc-files/core03.3_analysis.png" alt="image">
 *
 * <h2>5. Design</h2>
 *
 * <h3>5.1. Functional Tests</h3>
 * Basically, from requirements and also analysis, we see that the core
 * functionality of this use case is to change order by clicking in a column,
 * and change order by clicking in same column and change order.
 * <p>
 * see: <code>TestPackages.csheets.ext.sort</code>
 *
 * <h3>5.2. UC Realization</h3>
 * The following diagrams illustrate core aspects of the design of the solution
 * for this use case.
 * <p>
 * <b>Note:</b> It is very important that in the final version of this technical
 * documentation the elements depicted in these design diagrams exist in the
 * code!
 *
 * <h3>Auto-Sorting</h3>
 * The following diagram shows how import data from xml format
 * <p>
 * <img src="doc-files/core03.3_design.png" alt="image">
 *
 * <h3>5.3. Classes</h3>
 * <p>
 * <b>Class Diagram</b>
 * <p>
 * Global Class Diagram
 * <p>
 * <img src="doc-files/core03.3_classDiagram.png" alt="image">
 * <p>
 * -Document the implementation with class diagrams illustrating the new and the
 * modified classes-
 *
 * <h3>5.4. Design Patterns and Best Practices</h3>
 *
 * -Describe new or existing design patterns used in the issue-
 *
 *
 * <h2>6. Implementation</h2>
 *
 * -Reference the code elements that where updated or added-
 * <p>
 * -Also refer all other artifacts that are related to the implementation and
 * where used in this issue. As far as possible you should use links to the
 * commits of your work-
 * <p>
 * see:
 * <p>
 * <a href="../../../../csheets/ext/sort/package-summary.html">csheets.ext.sort</a>
 *
 *
 * <h2>7. Integration/Demonstration</h2>
 *
 * -In this section document your contribution and efforts to the integration of
 * your work with the work of the other elements of the team and also your work
 * regarding the demonstration (i.e., tests, updating of scripts, etc.)-
 *
 * <p>
 * Test of auto-sorting are presented on the next package
 * <a href="../../../TestPackages/csheets/ext/sort/package-summary.html">TestPackages.csheets.ext.sortL</a>
 *
 * <h2>8. Final Remarks</h2>
 *
 * -In this section present your views regarding alternatives, extra work and
 * future work on the issue.-
 *
 *
 *
 *
 * <h2>9. Work Log</h2>
 *
 * -Insert here a log of you daily work. This is in essence the log of your
 * daily standup meetings.-
 * <p>
 * Example
 * <p>
 * <b>Friday</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * 1. sprint review
 * <p>
 * Today
 * <p>
 * 1. read the feature
 * <p>
 * Blocking:
 * <p>
 * 1. -nothing-
 * <p>
 * <b>Saturday</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * 1. read the feature
 * <p>
 * Today
 * <p>
 * 1. nothing
 * <p>
 * Blocking:
 * <p>
 * 1.-nothing-
 * <p>
 * <b>Sunday</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * 1. nothing
 * <p>
 * Today
 * <p>
 * 1. nothing
 * <p>
 * Blocking:
 * <p>
 * 1. -nothing to report at moment-
 * <p>
 * <b>Monday</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * 1. nothing
 * <p>
 * Today
 * <p>
 * 1. Start analysis of feature core03.3 auto-sorting
 * <p>
 * Blocking:
 * <p>
 * 1. don't understand what i have to do
 * <p>
 * <b>Tuesday</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * 1. Start analysis of feature core03.3 auto-sorting
 * <p>
 * Today
 * <p>
 * 1. finish analysis and start design of core03.3 auto-sorting and start
 * implementation and test implementation
 * <p>
 * Blocking:
 * <p>
 * 1. clickEvent in a cell
 * <p>
 * <b>Wednesday</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * 1. finish analysis and start design of core03.3 auto-sorting and start
 * implementation and test implementation
 * <p>
 * Today
 * <p>
 * 1. finish implementation and test implementation of core03.3 auto-sorting and
 * helped on ipc08.1
 * <p>
 * Blocking:
 * <p>
 * 1. -nothing-
 * <p>
 * <b>Thursday</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * 1. finish implementation and test implementation of core03.3 auto-sorting and
 * helped on ipc08.1
 * <p>
 * Today
 * <p>
 * 1. update worklog and heldped expand automaticaly jtree in lang04.3 and prepare apresentation
 * <p>
 * Blocking:
 * <p>
 * 1.-nothing-
 * <h2>10. Self Assessment</h2>
 *
 * -Insert here your self-assessment of the work during this sprint.-
 *
 * <h3>10.1. Design and Implementation:3</h3>
 *
 * 3- good: the implmentation follows all requirements.
 * <p>
 * <b>Evidences:</b>
 * <p>
 * - url of commit:
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/b4e7a0a9b9b0c67ddf1278dac8c68b107649b928">commit
 * a632501</a>- description: this commit is related to  testsimplementation
 * 
 * - url of commit:
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/e8c690d19b1e113cafa1815c067d8505a3223233">commit
 * e8c690d</a>- description: this commit is related to the implementation
 *
 * <p>
 * The option to test was to test at first moment the import of data. After i
 * test for each type of import if the cells are changed after import
 *
 *
 * <h3>10.2. Teamwork: ...</h3>
 *
 * <h3>10.3. Technical Documentation: ...</h3>
 *
 * @author Carlos Santos
 */
package csheets.worklog.n1140611.sprint4;

/**
 * This class is only here so that javadoc includes the documentation about this
 * EMPTY package! Do not remove this class!
 *
 * @author Carlos Santos
 */
class _Dummy_ {
}
