/**
 * Technical documentation regarding the work of the team member (1140491) Rui
 * Bastos during week3.
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
 * -I spent most of the time working on my feature. In the last day of the
 * sprint(wednesday) i was helping my colleague Carlos Mateus in his use case.-
 *
 * <h2>2. Use Case/Feature: Core05.1</h2>
 *
 * Issue in Jira: -LPFOURDG-12-
 *
 * <h2>3. Requirement</h2>
 * The new extension should have a window to setup email. This window should be
 * used to setup the required configurations for email. For instance, the
 * account data and server to be used to send emails. All the configuration data
 * should be saved in a proper file (used to save global data). The window
 * should have a button to send a test email. This test email should get its
 * contents (destination, subject and body) from the contents of specific cells
 * (to be selected when the user select the test email button). The window
 * should display a preview of the email and the result of the test.
 *
 * <p>
 * <b>Use Case "Email Configuration":</b> The user logs with is mail, entering
 * is e-mail, password and server. The system saves the data on a properties
 * file. The user selects the test mail button, selecting the destination,
 * subject and body of the message from the selected cells . The system sends
 * the message and shows a preview to the user.
 *
 *
 * <h2>4. Analysis</h2>
 * A jDialog should be created to ask for the configuration data. Another
 * jDialog should be used to preview the e-mail. To complete this feature we
 * will need to understand how emails are sent and how we can adapte them in our
 * code.
 *
 * <h3>First "analysis" sequence diagram</h3>
 * The following diagram depicts a proposal for the realization of the
 * previously described use case. We call this diagram an "analysis" use case
 * realization because it functions like a draft that we can do during analysis
 * or early design in order to get a previous approach to the design. For that
 * reason we mark the elements of the diagram with the stereotype "analysis"
 * that states that the element is not a design element and, therefore, does not
 * exists as such in the code of the application (at least at the moment that
 * this diagram was created).
 * <p>
 * <img src="doc-files/email_configuration_extension_uc_realization.png" alt="image">
 *
 * <h3>Analysis of Core Technical Problem</h3>
 * It is possible to send email in java, using the javamail library. This
 * library allows us to communicate with the smtp protocol easily, almost
 * "abstracting" the problem. We will test this library to understand it better.
 * Therefore, we will start to implement tests for this use case.
 *
 * <h2>5. Design</h2>
 *
 * <h3>5.1. Functional Tests</h3>
 * Basically, from requirements and also analysis, we see that the core
 * functionality of this use case is to configure an email. We need to know if
 * the email reaches the destination. For that i will use mock-javamail and
 * green mail. It will be tested if the message is sent. As usual, in a test
 * driven development approach tests normally fail in the beginning. The idea is
 * that the tests will pass in the end.
 *
 * <h3>5.2. UC Realization</h3>
 * To realize this user story we will need to create a subclass of Extension. We
 * will also need to create a subclass of UIExtension. For the window we need to
 * implement a JDialog. The code of the extension will be in
 * <code>csheets.ext.email</code> package. The following diagrams illustrate
 * core aspects of the design of the solution for this use case.
 * <p>
 * <b>Note:</b> It is very important that in the final version of this technical
 * documentation the elements depicted in these design diagrams exist in the
 * code!
 *
 * <h3>Use Case Sequence Diagram</h3>
 * The following diagram shows the steps needed for this use case. We decided
 * that the creation of the Email class would make sense, making all the actions
 * related to emails a responsability to this class.
 * <p>
 * <img src="doc-files/email_configuration_extension_design.png" alt="image">
 *
 *
 * <h3>5.3. Classes</h3>
 *
 * This is a really small class diagram. The only class we created to this task
 * was Email, because Session and Properties already existed.
 * <p>
 * <img src="doc-files/email_configuration_extension_class_diagram.png" alt="image">
 *
 * <h3>5.4. Design Patterns and Best Practices</h3>
 *
 * -To resolve this use case, we're using patterns learned in the courses of
 * EAPLI and ESOFT, namely the GRASP and SOLID patterns-
 * <p>
 * -...-
 *
 * <h2>6. Implementation</h2>
 *
 * To resolve this issue, we decided to create the class Email, assigning all
 * the responsabilities of email functionalities to it.
 *
 * public Email(Session session) { this.session = session; } The class has one
 * attribute(Session), that contains the user authentication and the properties
 * of the email account.
 *
 * We added to methods to this class:
 *
 * public void connect(String server, String email, String password) public void
 * sendMessage(String to, String subject, String body)
 *
 * The method connect tries to authenticate to the email account. if not
 * successfull it throws an exception. The method sendMessage tries to send a
 * message to another email account. If this email is not sent, the method
 * throws an exception.
 *
 * <p>
 * see:
 * <p>
 * <a href="../../../../csheets/ext/email/package-summary.html">csheets.ext.email</a><p>
 * <a href="../../../../csheets/ext/email/ui/package-summary.html">csheets.ext.email.ui</a>
 *
 * <p>
 * Commit Evidences:</p>
 *
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/fbe15de453facc60bd0b7e78dc0830755bc013cc">Analysis</a></p>
 *
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/e0022d44168c7e9246e949aa18bf945685e6c962#chg-src/main/java/csheets/worklog/n1140491/sprint3/email_configuration_extension_class_diagram.puml">Design
 * commit 1</a></p>
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/e0022d44168c7e9246e949aa18bf945685e6c962#chg-src/main/java/csheets/worklog/n1140491/sprint3/email_configuration_extension_design.puml">Design
 * commit 2</a></p>
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/a5a84fb20c661307b36e92a8ce95d8cdff25c761">Tests</a></p>
 *
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/a6ec0a830a2170c64503703d8206c0f3214377e1">Implementation
 * commit 1</a></p>
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/6bcfcf9d8b2ab2dff19158071a4611091fd98dea">Implementation
 * commit 2</a></p>
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/81dcff57e323849e9379020a528136d161c73ebf">Implementation
 * coommit 3</a></p>
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/45bccc3dd48e13996c717027464e2dc6b9fd5992">Implementation
 * commit 4</a></p>
 *
 * <h2>7. Integration/Demonstration</h2>
 *
 * On this sprint i demonstrated my own feature individuall.
 *
 * <h2>8. Final Remarks</h2>
 *
 * <p>
 * I tried to make a jdialog with a loading bar while the message was being
 * sent. I tried deifferent approaches but i couldn't finish it. So this may be
 * something to do in the future.
 *
 *
 * <h2>9. Work Log</h2>
 *
 * <p>
 * <b>Saturday</b>
 * <p>
 * Understanding JavaMail library.
 * <p>
 * <b>Sunday</b>
 * <p>
 * Analisys, design and beggining of user interface implementation
 * <p>
 * <b>Monday</b>
 * <p>
 * Implementation of controller, more user interfaces and unit tests
 * <p>
 * Blocking:
 * <p>
 * 1. -nothing-
 * <p>
 * <b>Tuesday</b>
 * <p>
 * Checking if all the features are correct. Updated tests using mock javamail
 * and greenmail
 * <p>
 * Blocking:
 * <p>
 * 1. -nothing-
 * <p>
 * Wednesday
 * <p>
 * Helped my co-worker Carlos Mateus in his use case. Made final touches. 1. ...
 *
 * <h2>10. Self Assessment</h2>
 *
 * -I think i worked well during the week. I worked on it during the weekend
 * and, because of that, i was able to finish it earlier..-
 *
 * <h3>10.1. Design and Implementation:3</h3>
 * To resolve this use case, we're using patterns learned in the courses of
 * EAPLI and ESOFT, namely the GRASP and SOLID patterns. All the code is
 * following the design made.
 * <p>
 * <b>Evidences:</b>
 * <p>
 * -
 * https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/a6ec0a830a2170c64503703d8206c0f3214377e1
 * - description: this commit is related to the implementation of the design
 * pattern ...-
 *
 * <h3>10.2. Teamwork: ...</h3>
 * <p>
 * We worked well as a team. We were a 4 element team which made our team work
 * easier.
 *
 *
 * <h3>10.3. Technical Documentation: ...</h3>
 * This was my technical documentation on this sprint. I think that, comparing
 * with the last sprint, this part of the work is way better.
 *
 * @author Rui Bastos
 */
package csheets.worklog.n1140491.sprint3;

/**
 * This class is only here so that javadoc includes the documentation about this
 * EMPTY package! Do not remove this class!
 *
 * @author Rui Bastos
 */
class _Dummy_ {
}
