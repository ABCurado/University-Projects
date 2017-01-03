/**
 * Technical documentation regarding the work of the team member (1030472) Bruno
 * Silva during week4.
 *
 * <p>
 * <b>Scrum Master: no</b>
 *
 * <p>
 * <b>Area Leader: no</b>
 *
 * <h2>1. Notes</h2>
 *
 * <h2>2. Use Case/Feature: CRM02.1</h2>
 *
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-78">LPFOURDG-78</a>
 *
 * <b>Sub-tasks:</b><ul>
 * <li>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-383">LPFOURDG-334</a></li>
 * <li>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-384">LPFOURDG-335</a></li>
 * <li>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-385">LPFOURDG-336</a></li>
 * <li>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-386">LPFOURDG-337</a></li>
 * </ul>
 *
 * <h2>3. Requirement</h2>
 * There should be a new sidebar window to create, edit and remove addresses
 * associated with contacts (individuals or companies). Each address must
 * include: street, town, postal code, city and country. Each contact should
 * have to addresses: the main address and a secondary address. In the case of a
 * Portuguese address the postal code should be validated. It should be possible
 * to import and update the the list of valid Portuguese postal codes from an
 * external file (xml file or other format).
 *
 * <p>
 * <b>Use Case "Adress Edition":</b>
 * Using the sidebar "adress", the user should be able to create a main or
 * secundary adress associated to a contact.When the user creates adresses, he
 * had to introduce country, town, street and postal code. Also the User can
 * edit all the data of the created adresses or remove the adress. When an
 * adress is created with Portugal as country, the postal code has to be
 * validated by file import.
 *
 * <h2>4. Analysis</h2>
 *
 *
 * <h3>Create adress analysis</h3>
 * After last week where we had to implement Tasks, the same method is used here
 * with adresses, when the user choose to add or edit an adress to a contact,
 * AdressPanelManager is in charge to introduce the information,
 * AdressPanelSingle is in charge to show information created with AdressPanel
 * and add a new adress to a contact. AdressPanelSingle has two button, one to
 * edit and one to remove. Like it is asked postal code of portuguese city has
 * to be validated by importing an external file, so we have also a button to
 * import a file in the AdressPanel.
 * <h4>Create adress Sequence Diagram</h4>
 *
 * <img src="doc-files/crm02_01_design_create_adress.png" alt="image">
 *
 * <h2>6. Implementation</h2>
 *
 * <h2>7. Integration/Demonstration</h2>
 *
 *
 * <h2>8. Final Remarks</h2>
 *
 *
 * <h2>9. Work Log</h2>
 * <p>
 * <b>Monday</b>
 * <p>
 * Today
 * <p>
 *
 * <p>
 * Blocking:
 * <p>
 * 1. -nothing-
 * <p>
 * <b>Tuesday</b>
 * <p>
 * Today
 * <p>
 * 1. Started design and testing.
 * <p>
 * Blocking:
 * <p>
 * 1.
 * <p>
 * <b>Wednesday</b>
 * <p>
 * Today
 * <p>
 * 1.
 * <p>
 * Blocking:
 * <p>
 * 1.
 * <p>
 * <b>Thursday</b>
 * <p>
 * Today
 * <p>
 * 1.
 * <p>
 * Blocking:
 * <p>
 * 1.
 *
 * <h2>10. Self Assessment</h2>
 *
 * <h3>10.1. Design and Implementation:3</h3>
 *
 * <p>
 * <b>Evidences:</b>
 *
 * <h3>10.2. Teamwork: ...</h3>
 *
 * <h3>10.3. Technical Documentation: ...</h3>
 *
 * @author Bruno Silva <1030472@isep.ipp.pt>
 */
package csheets.worklog.n1030472.sprint4;

class _Dummy_ {
}
