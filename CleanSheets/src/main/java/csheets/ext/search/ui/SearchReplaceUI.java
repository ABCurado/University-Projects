package csheets.ext.search.ui;

import csheets.core.Spreadsheet;
import csheets.core.Value;
import csheets.ext.search.SearchController;
import csheets.ext.search.SearchExtension;
import csheets.framework.search.SearchResultDTO;
import csheets.notification.Notification;
import csheets.ui.ctrl.UIController;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.regex.PatternSyntaxException;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author José Barros
 */
public class SearchReplaceUI extends javax.swing.JFrame implements Observer {

	/**
	 * The SearchController
	 */
	private final SearchController searchController = new SearchController();

	/**
	 * Type of object to search
	 */
	private Map<String, Value.Type> types = new HashMap<>();

	/**
	 * Formula of cell content
	 */
	private boolean formulas = false;

	/**
	 * Comments of cell
	 */
	private boolean comments = false;

	/**
	 * Panel with search tools
	 */
	private SearchReplaceToolsPanel searchTools;

	/**
	 * The UIController
	 */
	private final UIController uiController;

	/**
	 * The Results list Model
	 */
	private final DefaultListModel resultsModel = new DefaultListModel();

	/**
	 * Helper flag: If true, search box will be cleared otherwise it wont
	 */
	private boolean firstClick = true;

	/**
	 * Replace flag: If true replace all matches in once time
	 */
	private boolean replaceAll = false;

	/**
	 * Creates new form SearchReplaceUI
	 *
	 * @param controller UI controller
	 */
	public SearchReplaceUI(UIController controller) {
		searchTools = new SearchReplaceToolsPanel(this);
		this.setLocationRelativeTo(null);
		setName(SearchExtension.NAME);
		this.uiController = controller;
		initComponents();
		Notification.eventInformer().addObserver(this);
	}

	public void setAdvancedSearch(Map<String, Value.Type> types,
								  boolean formulas, boolean comments) {
		this.types = types;
		this.formulas = formulas;
		this.comments = comments;
	}

	private void performSearch() {

		String searchstring = jSearchTextField.getText();
		String replacestring = txtReplace.getText();

		try {

			if (!searchstring.equals("") && (!replacestring.equals("") && !replacestring.
				equals("Type a text to replace..."))) {

				List<SearchResultDTO> results = searchController.
					searchWorkBook(uiController.workbooks(), searchstring, types,
								   formulas, comments);
				int found = results.size();

				if (found > 0) {

					results.stream().
						forEach((result) -> {

							Spreadsheet spread = this.searchController.
								getSpreadSheetObject(result.getSpreadSheet());

							if (replaceAll) {
								this.searchController.
									replace(result, replacestring, spread);
							} else {
								SearchResultPanel panel = new SearchResultPanel(this, true, uiController, result, replacestring, searchController);
							}
						});

				} else {
					JOptionPane.
						showMessageDialog(this, "No results.", "Search Result", JOptionPane.INFORMATION_MESSAGE);
					dispose();
				}
				dispose();

			} else {
				JOptionPane.
					showMessageDialog(this, "Missing fields.", "Error", JOptionPane.ERROR_MESSAGE);
			}

		} catch (PatternSyntaxException ex) {
			JOptionPane.
				showMessageDialog(this, "Invalid pattern syntax!", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jSearchTextField = new javax.swing.JTextField();
        searchLabel = new javax.swing.JLabel();
        jSearchToolsButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtReplace = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Search and Replace", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        searchLabel.setText("Search:");

        jSearchToolsButton.setText("Search Tools");
        jSearchToolsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSearchToolsButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Replace:");

        searchButton.setText("SEARCH AND REPLACE");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        btnCancel.setText("CANCEL");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(searchButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(searchLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jSearchTextField)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jSearchToolsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtReplace, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchLabel)
                    .addComponent(jSearchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSearchToolsButton))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtReplace, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel)
                    .addComponent(searchButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtReplace.setText("Type new value for the replace...");

        txtReplace.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                if (firstClick) {
                    txtReplace.setText("");
                    firstClick = false;
                }
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jSearchToolsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSearchToolsButtonActionPerformed
		searchTools.setVisible(true);
		searchTools.setLocationRelativeTo(this);
    }//GEN-LAST:event_jSearchToolsButtonActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
		dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
		performSearch();
    }//GEN-LAST:event_searchButtonActionPerformed

	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof String) {
			if (((String) arg).equals("REPLACE ALL")) {
				this.replaceAll = true;
			}
		}
	}

	public void run() {
		this.setVisible(true);
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jSearchTextField;
    private javax.swing.JButton jSearchToolsButton;
    private javax.swing.JButton searchButton;
    private javax.swing.JLabel searchLabel;
    private javax.swing.JTextField txtReplace;
    // End of variables declaration//GEN-END:variables
}
