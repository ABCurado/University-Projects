/**
 * Technical documentation regarding the work of the team member (1130980)
 * Rafael Vilar during week2.
 *
 * * <p>
 * <b>-Note: this is a template/example of the individual documentation that
 * each team member must produce each week/sprint. Suggestions on how to build
 * this documentation will appear between '-' like this one. You should remove
 * these suggestions in your own technical documentation-</b>
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
 * This week work time was mostly spent on how the base application works.
 *
 * <h2>2. Use Case/Feature: CORE02.2</h2>
 *
 * Issue in Jira:
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-4">LPFOURDG-4</a>
 * Sub-Task in Jira:
 * <p>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-144">LPFOURDG-144</a></p>
 * <p>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-147">LPFOURDG-147</a></p>
 * <p>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-149">LPFOURDG-149</a></p>
 * <p>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-150">LPFOURDG-150</a></p>
 *
 * <h2>3. Requirement</h2>
 * This feature Tooltip and User Associated to Comment is my responsibility
 *
 * <p>
 * <b>Use Case "Tooltip and User Associated to Comment":</b> Cleansheets should
 * register the name of the user that creates comments and each cell should
 * support several comments. When the mouse pointer is hovering above a cell and
 * the cell has comments then these comments should be displayed in a form
 * similar to a "tooltip". The name of the author of each comment should also
 * appear in all displays of comments. Comments should be persisted with the
 * workbook.
 *
 *
 * <h2>4. Analysis</h2>
 * <p>
 * <img src="doc-files/core02_2_Analysis.png" alt="image">
 * </p>
 * <p>
 * In this use case it is intended that when you make a comment in a cell, it
 * must be captured and registered the user name logged on the machine /
 * computer. It is also intended that a cell may have several comments. When the
 * user puts the mouse on a cell should be displayed as a "tooltip", all
 * comments of the cell and their authors. Comments should be stored / persisted
 * in the workbook.</p>
 *
 * <b>CommentController Class:</b>
 * <p>
 * In this class we have a method (cellSellected) receiving the selected cell,
 * where checks are reviewed, and if you are going to seek their comment. There
 * is another method (SetComment) receiving a cell and a String, which checks
 * whether the comment is null or empty, and if it is empty holds, but adds the
 * new comment and records the change in their workbook</p>
 *
 * <b>CommentableCell Class:</b>
 * <p>
 * In this class we have a method (hasComment) that checks whether the cell is
 * discussed. There is another method (setUserComment) that takes a String,
 * which is made the allocation of the String variable userComment, and is
 * called then the fireCommentsChanged() method notifies all registered
 * listeners that the cell's comments changed. There is also the getUserComment
 * () method that returns the comment
 *
 * <b>In the analysis of this feature, we chose to register the user name logged
 * on the machine.</b>
 *
 *
 * <h3>Domain Model</h3>
 *
 *
 *
 * <h2>5. Design</h2>
 * <p>
 * <img src="doc-files/core02_2_Design.png" alt="image">
 * </p>
 * <h3>5.1. Functional Tests</h3>
 * <p>
 * see: <code>csheets.core.CommentableCellTest</code>
 * </p>
 *
 * <h3>5.2. UC Realization</h3>
 *
 * <p>
 * <b>Note:</b> It is very important that in the final version of this technical
 * documentation the elements depicted in these design diagrams exist in the
 * code!
 *
 *
 * <h3>5.4. Design Patterns and Best Practices</h3>
 *
 * -Describe new or existing design patterns used in the issue-
 * <p>
 * There is the pattern Value Object - Comment</p>
 * <p>
 * -You can also add other artifacts to document the design, for instance,
 * database models or updates to the domain model-
 * </p>
 * <h2>6. Implementation</h2>
 *
 * <p>
 * <code>csheets.ext.comments.ui.CommentController</code>
 * </p>
 * <p>
 * <code>csheets.ext.comments.ui.CommentsPanel</code>
 * </p>
 * <p>
 * <code>csheets.ext.comments.ui.CommentPanel</code>
 * </p>
 * <p>
 * <code>csheets.ext.comments.ui.UIExtensionComments</code>
 * </p>
 * <p>
 * <code>csheets.ext.comments.Comment</code>
 * </p>
 * <p>
 * <code>csheets.ext.comments.CommentableCell</code>
 * </p>
 *
 * <p>
 * -Also refer all other artifacts that are related to the implementation and
 * where used in this issue. As far as possible you should use links to the
 * commits of your work-
 * </p>
 * see:
 * <p>
 * <a href="../../../../csheets/ext/comments/package-summary.html">csheets.ext.comments</a>
 * </p>
 *
 * <h2>7. Integration/Demonstration</h2>
 *
 * We are in the first week where the workflow of the project is a little bit
 * different from the rest of the weeks. Our functional area is very independent
 * from the others. The only that we had to talk with our work collegues was
 * related to the extensions part (Core functional area).
 *
 * <h2>8. Final Remarks</h2>
 *
 * <h2>9. Work Log</h2>
 *
 * <p>
 * <b>Monday</b>
 * </p>
 * 1. Analysis and early use case design
 * <p>
 * Blocking:
 * </p>
 * 1. Nothing.
 * <p>
 * <b>Tuesday</b>
 * </p>
 * 1. Complete Design and Starts Implementaton
 * <p>
 * Blocking:
 * </p>
 * 1. Nothing.
 * <p>
 * <b>Wednesday</b>
 * </p>
 * 1. Implementation and Tests
 * <p>
 * Blocking:
 * </p>
 * 1. Nothing.
 *
 * <h2>10. Self Assessment</h2>
 *
 * -Insert here your self-assessment of the work during this sprint.-
 *
 * <h3>10.1. Design and Implementation:3</h3>
 *
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
 * /**
 * This class is only here so that javadoc includes the documentation about this
 * EMPTY package! Do not remove this class!
 *
 * @author Rafael Vilar
 */
package csheets.worklog.n1130980.sprint2;

class _Dummy_ {
}
