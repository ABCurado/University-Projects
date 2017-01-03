package csheets.ext.networkExplorer.ui;

import csheets.ext.events.EventsExtension;
import csheets.ext.networkExplorer.NetworkExplorerController;
import csheets.ext.networkExplorer.domain.AppInfo;
import csheets.ext.networkExplorer.domain.ExtensionInfo;
import csheets.ext.networkExplorer.domain.SpreadSheetInfo;
import csheets.ext.networkExplorer.domain.WorkbookInfo;
import csheets.notification.Notification;
import csheets.ui.ctrl.UIController;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author Martins
 */
public class NetworkExplorerPanel extends javax.swing.JPanel implements Observer {

	private final NetworkExplorerController controller;

	private final UIController uiController;
	/**
	 * Hostname
	 */
	private String host;

	/**
	 * Creates new form NetworkExplorerPanel
	 *
	 * @param uiController The user interface controller.
	 *
	 */
	public NetworkExplorerPanel(UIController uiController) {
		this.uiController = uiController;
		this.controller = new NetworkExplorerController();
		this.setName(EventsExtension.NAME);
		this.initComponents();
		this.update(null, null);
		Notification.exploreInformer().addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof Map) {
			Map<String, String> data = (Map) arg;
			if (data.get("reference").equals("AppInfo")) {
				this.controller.
					appInfo(data.get("ip") + ":" + data.get("port"), data.
							get("info"));
			}
		}

		//cleans the tree
		this.jTree.setModel(null);

		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("root");
		DefaultTreeModel treeModel = new DefaultTreeModel(rootNode);
		this.jTree.setModel(treeModel);

		//puts extensions in JTREE
		for (AppInfo appInfo : controller.appInfoList()) {
			DefaultMutableTreeNode treeRoot = (DefaultMutableTreeNode) this.jTree.
				getModel().getRoot();
			DefaultMutableTreeNode root = new DefaultMutableTreeNode(appInfo.
				getName());
			treeRoot.add(root);
			DefaultMutableTreeNode workbookTitle = new DefaultMutableTreeNode("Workbooks");
			root.add(workbookTitle);
			//puts workbooks in JTREE
			for (WorkbookInfo wrk : appInfo.getWorkbooks()) {
				DefaultMutableTreeNode workbookName = new DefaultMutableTreeNode(wrk.
					getName());
				workbookTitle.add(workbookName);
				for (SpreadSheetInfo spreadsheet : wrk.getSheetsMap()) {
					DefaultMutableTreeNode spreadName = new DefaultMutableTreeNode(spreadsheet.
						getName());
					workbookName.add(spreadName);
				}
			}

			DefaultMutableTreeNode extensionTitle = new DefaultMutableTreeNode("Extensions");
			root.add(extensionTitle);
			//puts extensions in JTREE
			for (ExtensionInfo ext : appInfo.getExtensions()) {
				DefaultMutableTreeNode extName = new DefaultMutableTreeNode(ext.
					getName());
				extensionTitle.add(extName);
				DefaultMutableTreeNode extActive = new DefaultMutableTreeNode(ext.
					getName());
				DefaultMutableTreeNode extVersion = new DefaultMutableTreeNode(ext.
					getVersion());
				DefaultMutableTreeNode extDescription = new DefaultMutableTreeNode(ext.
					getDescription());
				extName.add(extActive);
				extName.add(extVersion);
				extName.add(extDescription);

			}
		}
		this.revalidate();
		this.repaint();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTree = new javax.swing.JTree();
        jLabel1 = new javax.swing.JLabel();

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        jTree.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jScrollPane1.setViewportView(jTree);

        jLabel1.setText("Network Explorer");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 227, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTree jTree;
    // End of variables declaration//GEN-END:variables
}