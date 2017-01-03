package csheets.ext.search.ui;

import csheets.core.Spreadsheet;
import csheets.ext.search.SearchController;
import csheets.framework.search.SearchResultDTO;
import csheets.notification.Notification;
import csheets.ui.ctrl.UIController;

/**
 *
 * @author José Barros
 */
public class SearchResultPanel extends javax.swing.JDialog {

	/**
	 * The UIController
	 */
	private final UIController uiController;

	/**
	 * UI parent
	 */
	private final SearchReplaceUI panel;

	/**
	 * Search result
	 */
	private final SearchResultDTO result;

	/**
	 * New string for cell content
	 */
	private final String replacestring;

	/**
	 * The SearchController
	 */
	private final SearchController controller;

	/**
	 * Creates new form SearchResultPanel
	 *
	 * @param parent ui parent
	 * @param modal modal
	 * @param uiController UI controller
	 * @param result search result
	 * @param replacestring new string
	 * @param controller Search controller
	 */
	public SearchResultPanel(SearchReplaceUI parent, boolean modal,
							 UIController uiController, SearchResultDTO result,
							 String replacestring, SearchController controller) {
		super(parent, modal);
		this.uiController = uiController;
		this.controller = controller;
		this.panel = parent;
		this.result = result;
		this.replacestring = replacestring;
		this.setLocationRelativeTo(null);
		initComponents();
		initFields();
		this.setVisible(true);
	}

	private void initFields() {
		this.labelValueResult.setText(result.getValue());
		this.labelInResult.setText(result.getCell());
		this.labelReplaceTo.setText(replacestring);
		this.labelSpread.setText(result.getSpreadSheet());
	}

	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        labelValue = new javax.swing.JLabel();
        labelValueResult = new javax.swing.JLabel();
        labelIn = new javax.swing.JLabel();
        labelInResult = new javax.swing.JLabel();
        labelReplace = new javax.swing.JLabel();
        labelReplaceTo = new javax.swing.JLabel();
        jCheckBoxReplace = new javax.swing.JCheckBox();
        btnReplace = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        labelSpread = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Search Result", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        labelValue.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelValue.setText("Value:");

        labelValueResult.setText("info");

        labelIn.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelIn.setText("Found in:");

        labelInResult.setText("info2");

        labelReplace.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelReplace.setText("Value after replace:");

        labelReplaceTo.setText("info");

        jCheckBoxReplace.setText("Replace All");
        jCheckBoxReplace.setFocusable(false);

        btnReplace.setText("REPLACE");
        btnReplace.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReplaceActionPerformed(evt);
            }
        });

        btnNext.setText(">>");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        labelSpread.setText("info1");

        jLabel1.setText(">>");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jCheckBoxReplace)
                        .addGap(18, 18, 18)
                        .addComponent(btnReplace)
                        .addGap(18, 18, 18)
                        .addComponent(btnNext, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(labelIn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelValue, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelReplace, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelReplaceTo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(labelValueResult, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(labelSpread)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelInResult, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelValue)
                    .addComponent(labelValueResult))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelIn)
                    .addComponent(labelInResult)
                    .addComponent(labelSpread)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelReplace)
                    .addComponent(labelReplaceTo))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxReplace)
                    .addComponent(btnReplace)
                    .addComponent(btnNext))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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

    private void btnReplaceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReplaceActionPerformed

		Spreadsheet spread = this.controller.getSpreadSheetObject(result.
			getSpreadSheet());

		this.controller.replace(result, replacestring, spread);

		if (jCheckBoxReplace.isSelected()) {
			Notification.eventInformer().notifyChange("REPLACE ALL");
		}

		dispose();
    }//GEN-LAST:event_btnReplaceActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
		dispose();
    }//GEN-LAST:event_btnNextActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnReplace;
    private javax.swing.JCheckBox jCheckBoxReplace;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelIn;
    private javax.swing.JLabel labelInResult;
    private javax.swing.JLabel labelReplace;
    private javax.swing.JLabel labelReplaceTo;
    private javax.swing.JLabel labelSpread;
    private javax.swing.JLabel labelValue;
    private javax.swing.JLabel labelValueResult;
    // End of variables declaration//GEN-END:variables
}
