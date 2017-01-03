/**
 * Technical documentation regarding the work of the team member (1130980)
 * Rafael Vilar during week4.
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
 * This week will be developed the Advanced Forms. </p>
 * <p>
 * - Problem discussion with Rui Bastos - Design and Implementation.</p>
 *
 * <h2>2. Use Case/Feature: IPC07.2</h2>
 *
 * Issue in Jira:
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-44">LPFOURDG-44</a>
 * <p>
 * Sub-Task in Jira:</p>
 * <p>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-359">Analysis -
 * LPFOURDG-359</a></p>
 * <p>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-360">Design -
 * LPFOURDG-360</a></p>
 * <p>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-361">Implementation
 * - LPFOURDG-361</a></p>
 * <p>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-362">Tests -
 * LPFOURDG-362</a></p>
 *
 * <h2>3. Requirement</h2>
 * This feature Advanced Forms is my responsibility.
 *
 * <p>
 * <b>Use Case "Advanced Forms":</b>
 * <p>
 * It should now be possible to create multiple forms for each workbook. Forms
 * should also be persistent with the workbook. To distinguish Forms each one
 * should have a unique name (within its workbook). The function (in macros and
 * formulas) that displays the forms should now have one parameter that is used
 * to pass the name of the form to display (since there can be several forms for
 * each workbook). When displaying a form it should be possible specify if it
 * should be readonly (i.e., it will display the value of the variables but does
 * not allow any update) or writable (in this case the form should allow for the
 * user to modify the values that are displayed). Writable forms should have a
 * new "Update" button. When the user clicks in the update button the form
 * closes and the current values of the widgets update the corresponding
 * variables. If the user closes the form window by any other means the
 * variables should not be updated. It should be also possible to specify the
 * "mode" of the form window (when invoking the display of a form). Two modes
 * are allowed: modal and modeless. A modal form window is a window that will
 * block the macro or formula that call it until the user closes the form
 * window. The macro or formula will only resume execution when the form is
 * closed. A modeless for window is a window that will no block the calling
 * macro or formula, i.e., the macro or formula will continue its execution in
 * parallel with the display of the form. Modeless forms do not return anything.
 * Model forms should return the name of the button that was used to close the
 * window.
 * </p>
 *
 * <h2>4. Analysis</h2>
 * <p>
 * <img src="doc-files/LANG06_3_Analysis_2.png" alt="image">
 * </p>
 * <p>
 * In this feature, the user initiates the Cleansheets application, which will
 * create several forms for the same workbook.
 * </p>
 * <p>
 * To create a new form will have to write the following in a cell:
 * <h3>=FORM("nameForm";TRUE())</h3><br>
 * <h3>=FORM("nameForm";FALSE())</h3>
 * <p>
 * This TRUE() or FALSE() is an option from Modal form or not Modal</p>
 * <p>
 * The form name is passed as parameter, and a check is made to see if there is
 * some form of the same name, and if it exists, show the existent form.
 * </p>
 * When we show a form should be possible to define whether it is read-only or
 * write.
 * <p>
 * To change the text of any textfield form , being editable, changes , and to
 * save click update
 * </p>
 *
 * <h2>5. Design</h2>
 * <p>
 * <img src="doc-files/lang06_3_Design_addWidgets.png" alt="image">
 * </p>
 * <p>
 * <img src="doc-files/lang06_3_Design.png" alt="image">
 * </p>
 * <h3>5.1. Functional Tests</h3>
 *
 * Test the creation of panel; textfields; button, labels. Test the remove
 * option of one widget from the form. Test the persistence forms on workbook.
 * Test the readonly or writable form. Test the Modal or not Modal form.
 *
 *
 * <h3>5.2. UC Realization</h3>
 *
 * <h3>5.3. Classes</h3>
 *
 * Form; AskContent; ButtonPanel; ButtonWidget; FormEditor; LabelPanel;
 * LabelWidget; SingleLine; TextFieldPanel; TextFieldWidget; (Interface) Widget;
 * FormE; Workbook;
 *
 *
 * <h3>5.4. Design Patterns and Best Practices</h3>
 *
 * Observer Pattern - is used for removing widgets from the form
 *
 * <h2>6. Implementation</h2>
 *
 * <p>
 * <code>csheets.ui.FormEditor.ui.AskContent</code></p>
 * <p>
 * <code>csheets.ui.FormEditor.ui.ButtonPanel</code></p>
 * <p>
 * <code>csheets.ui.FormEditor.ui.ButtonWidget</code></p>
 * <p>
 * <code>csheets.ui.FormEditor.ui.FormE</code></p>
 * <p>
 * <code>csheets.ui.FormEditor.ui.FormEditor</code></p>
 * <p>
 * <code>csheets.ui.FormEditor.ui.LabelPanel</code></p>
 * <p>
 * <code>csheets.ui.FormEditor.ui.LabelWidget</code></p>
 * <p>
 * <code>csheets.ui.FormEditor.ui.SingleLine</code></p>
 * <p>
 * <code>csheets.ui.FormEditor.ui.TextFieldPanel</code></p>
 * <p>
 * <code>csheets.ui.FormEditor.ui.TextFieldWidget</code></p>
 * <p>
 * <code>csheets.ui.FormEditor.ui.Widget</code></p>
 * <p>
 * <code>csheets.core.Workbook</code></p>
 *
 * <p>
 * -Also refer all other artifacts that are related to the implementation and
 * where used in this issue. As far as possible you should use links to the
 * commits of your work-
 * </p>
 * see:
 * <p>
 * <a href="../../../../csheets/ui/FormEditor/ui/package-summary.html">csheets.ui.FormEditor.ui</a>
 * </p>
 *
 * <h2>7. Integration/Demonstration</h2>
 *
 * This week my feature was to implement the Advanced Forms. This feature
 * belongs to LANG06.3 area and need to persist Forms on Workbook
 *
 * <h2>8. Final Remarks</h2>
 *
 * <h2>9. Work Log</h2>
 *
 * <p>
 * <b>Monday</b>
 * </p>
 * 1. Analysis use case
 * <p>
 * Blocking:
 * </p>
 * 1. Nothing.
 * <p>
 * <b>Tuesday</b>
 * </p>
 * 1. Design and Starts Implementaton
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
 *
 * <h3>Step 1</h3><br><br>
 * <img src="http://imgur.com/oDMW9Yf.png" alt="image1">
 *
 * <h3>Step 2</h3><br><br>
 * <img src="http://imgur.com/nMLqP6e.png" alt="image2">
 *
 * <h3>Step 3</h3><br><br>
 * <img src="http://imgur.com/fzrgLbH.png" alt="image3">
 *
 * <h3>Step 4</h3><br><br>
 * <img src="http://imgur.com/PJ8mUsY.png" alt="image4">
 *
 * <h3>Step 5</h3><br><br>
 * <img src="http://imgur.com/t7KziyR.png" alt="image5">
 *
 * <h3>Step 6</h3><br><br>
 * <img src="http://imgur.com/A8wANfA.png" alt="image6">
 *
 * <h3>Step 7</h3><br><br>
 * <img src="http://imgur.com/8UBcfX7.png" alt="image7">
 *
 * <h3>Step 8</h3><br><br>
 * <img src="http://imgur.com/yAbNGAT.png" alt="image8">
 *
 * <h3>Step 9</h3><br><br>
 * <img src="http://imgur.com/ZlLUmLy.png" alt="image9">
 *
 * <h3>Step 10</h3><br><br>
 * <img src="http://imgur.com/qvTLloA.png" alt="image10">
 *
 * <h3>Step 11</h3><br><br>
 * <img src="http://imgur.com/hnymioM.png" alt="image11">
 *
 * <h3>Step 12</h3><br><br>
 * <img src="http://imgur.com/gDM7yuA.png" alt="image12">
 *
 * <h3>Step 13</h3><br><br>
 * <img src="http://imgur.com/2WtTU8y.png" alt="image13">
 *
 * <h3>Step 14</h3><br><br>
 * <img src="http://imgur.com/xHwwKvn.png" alt="image14">
 *
 * <h3>Step 15</h3><br><br>
 * <img src="http://imgur.com/1uxJ7Sv.png" alt="image15">
 *
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
package csheets.worklog.n1130980.sprint4;

class _Dummy_ {
}
