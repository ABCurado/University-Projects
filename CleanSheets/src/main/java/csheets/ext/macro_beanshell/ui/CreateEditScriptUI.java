package csheets.ext.macro_beanshell.ui;

import csheets.ext.macro_beanshell.BeanShell;
import csheets.ext.macro_beanshell.Code;
import csheets.ext.macro_beanshell.Macro;
import csheets.ext.macro_beanshell.MacroBeanShellController;
import csheets.ext.macro_beanshell.MacroBeanShellExtension;
import csheets.ui.ctrl.UIController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

/**
 *
 * @author José Barros
 */
public class CreateEditScriptUI extends javax.swing.JFrame {

	private final MacroBeanShellController controller;
	private final ScriptManagerUI managerUI;
	private final UIController uiController;
	private String script_type;
	private boolean sync;

	/**
	 * Creates new form CreateScriptUI
	 *
	 * @param ui Principal
	 * @param uiController UI controller
	 * @param script name
	 */
	public CreateEditScriptUI(ScriptManagerUI ui, UIController uiController,
							  String script) {
		this.controller = new MacroBeanShellController(uiController);
		this.uiController = uiController;
		this.managerUI = ui;
		setName(MacroBeanShellExtension.NAME);
		this.setLocationRelativeTo(null);
		initComponents();
		createJRadioButtons();
		setRadioGroupButtonsAction();
		setRadioGroup2ButtonsAction();
		setFields(script);
	}

	private void createJRadioButtons() {
		createJRadioConfig(radioBtnMacro, Macro.NAME);
		createJRadioConfig(radioBtnBeanShell, BeanShell.NAME);
		createJRadioConfig(jRadioSync, "Sync");
		createJRadioConfig(jRadioAsync, "aSync");
	}

	private void createJRadioConfig(JRadioButton r, String name) {
		r.setText(name);
		r.setActionCommand(name);
	}

	private void setRadioGroupButtonsAction() {
		Enumeration radioGroup = buttonGroup1.getElements();
		while (radioGroup.hasMoreElements()) {
			JRadioButton radioBtn = (JRadioButton) radioGroup.nextElement();
			radioBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					setScriptType(e.getActionCommand());
				}
			});
		}
	}

	private void setRadioGroup2ButtonsAction() {
		Enumeration radioGroup = buttonGroup2.getElements();
		while (radioGroup.hasMoreElements()) {
			JRadioButton radioBtn = (JRadioButton) radioGroup.nextElement();
			radioBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					setExecType(e.getActionCommand());
				}
			});
		}
	}

	private void setFields(String script) {
		Code code;
		if (script != null) {
			code = uiController.getActiveWorkbook().getScript(script);

			txtName.setText(code.getName());
			this.script_type = code.getType();

			if (script_type.equals(BeanShell.NAME)) {
				radioBtnBeanShell.setSelected(true);
			} else {
				radioBtnMacro.setSelected(true);
			}
			txtAreaCode.setText(code.getContent());
			this.sync = code.isSynchronous();

			if (sync) {
				jRadioSync.setSelected(true);
			} else {
				jRadioAsync.setSelected(true);
			}
		}
	}

	public void setScriptType(String type) {
		this.script_type = type;
	}

	public void setExecType(String exec) {
		if (exec.equals("Sync")) {
			this.sync = true;
		} else {
			this.sync = false;
		}
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPane1 = new java.awt.ScrollPane();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaCode = new javax.swing.JTextArea();
        btnSave = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        codeLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        execLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        radioBtnMacro = new javax.swing.JRadioButton();
        radioBtnBeanShell = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        jRadioSync = new javax.swing.JRadioButton();
        jRadioAsync = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "New Script", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        txtAreaCode.setColumns(20);
        txtAreaCode.setRows(5);
        jScrollPane1.setViewportView(txtAreaCode);

        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/csheets/res/img/save.png"))); // NOI18N
        btnSave.setToolTipText("Saves a script");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/csheets/res/img/clear.png"))); // NOI18N
        btnClear.setText("Clear");
        btnClear.setFocusable(false);
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        codeLabel.setText("Code:");

        nameLabel.setText("Name:");

        execLabel.setText("Execution:");

        buttonGroup1.add(radioBtnMacro);
        radioBtnMacro.setText("Macro");

        buttonGroup1.add(radioBtnBeanShell);
        radioBtnBeanShell.setText("BeanShell Script");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(radioBtnMacro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radioBtnBeanShell)
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioBtnMacro)
                    .addComponent(radioBtnBeanShell)))
        );

        buttonGroup2.add(jRadioSync);
        jRadioSync.setText("Synchronous");

        buttonGroup2.add(jRadioAsync);
        jRadioAsync.setText("Asynchronous");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jRadioSync)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioAsync)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioSync)
                    .addComponent(jRadioAsync))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSave, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnClear))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(nameLabel)
                                .addGap(18, 18, 18)
                                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(execLabel)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 241, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(codeLabel)
                        .addGap(22, 22, 22)
                        .addComponent(jScrollPane1)))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLabel)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(codeLabel)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(execLabel))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed

		String name = txtName.getText();
		String content = txtAreaCode.getText();

		if (!name.equals("") && !content.equals("") && buttonGroup1.
			getSelection() != null && buttonGroup2.getSelection() != null) {

			controller.saveScript(name, script_type, content, sync);
			managerUI.displayScripts();
			txtAreaCode.setText(null);
			this.dispose();
		} else {
			JOptionPane.
				showMessageDialog(this, "You didn't create any script.", "Error", JOptionPane.ERROR_MESSAGE);
		}
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
		txtAreaCode.setText(null);
		txtName.setText(null);
		buttonGroup1.clearSelection();
		buttonGroup2.clearSelection();
    }//GEN-LAST:event_btnClearActionPerformed

	public void run() {
		this.setVisible(true);
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnSave;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel codeLabel;
    private javax.swing.JLabel execLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRadioAsync;
    private javax.swing.JRadioButton jRadioSync;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JRadioButton radioBtnBeanShell;
    private javax.swing.JRadioButton radioBtnMacro;
    private java.awt.ScrollPane scrollPane1;
    private javax.swing.JTextArea txtAreaCode;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables

}