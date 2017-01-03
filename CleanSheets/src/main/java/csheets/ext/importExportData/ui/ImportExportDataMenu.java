package csheets.ext.importExportData.ui;

import csheets.ext.importExportData.ImportExportDataExtension;
import csheets.ext.importExportData.databaseExportImport.ExportDatabaseAction;
import csheets.ext.importExportData.databaseExportImport.ImportDatabaseAction;
import csheets.ui.ctrl.UIController;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JMenu;

/**
 * Represents the UI extension menu of the share extension.
 *
 * @author Rui Bastos
 */
public class ImportExportDataMenu extends JMenu {

	/**
	 * Creates a new share menu. This constructor creates and adds the menu
	 * options. A menu option is an action (in this case
	 *
	 *
	 * @param uiController the user interface controller
	 * @param controller The controller.
	 */
	public ImportExportDataMenu(UIController uiController,
								ImportExportTextFileController controller) {
		super("Import/Export Data");
		setMnemonic(KeyEvent.VK_E);

		// Adds font actions
		add(new ImportAction(uiController, controller)).
			setIcon(new ImageIcon(ImportExportDataExtension.class.
				getResource("res/img/import_txt.png")));
		add(new ExportAction(uiController, controller)).
			setIcon(new ImageIcon(ImportExportDataExtension.class.
				getResource("res/img/export_txt.png")));
		add(new ImportDatabaseAction(uiController)).
			setIcon(new ImageIcon(ImportExportDataExtension.class.
				getResource("res/img/importDatabase.png")));
		add(new ExportDatabaseAction(uiController)).
			setIcon(new ImageIcon(ImportExportDataExtension.class.
				getResource("res/img/exportDatabase.png")));
		add(new DisableEnableUpdateAction(uiController, controller)).
			setIcon(new ImageIcon(ImportExportDataExtension.class.
				getResource("res/img/link.png")));
	}
}
