/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ui;

import csheets.ext.Extension;
import csheets.ext.Extension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author yur
 */
public class ExtensionsActivDeactivUI extends JFrame {

    public static final String TITLE = "Activate/Deactivate Extensions";

    private final Object[] tablesNames = {"Extension Name", "Enable"};

    private UIController controller;
    private JLabel lblTitle;
    private JScrollPane scrollPane;
    private JTable tableExtensions;
    private DefaultTableModel modelTableExtensions;
    private Panel panel;
    private JButton btnApply, btnCancel;

    public ExtensionsActivDeactivUI(UIController controller) {
        this.controller = controller;
        createUIObjects();
        configureUIObjects();
        positionUIObjects();
        fetchExtensions();
    }

    private void createUIObjects() {
        this.modelTableExtensions = new DefaultTableModel(0, tablesNames.length) {
            public Class<?> getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return String.class;
                    case 1:
                        return Boolean.class;
                    default:
                        return String.class;
                }
            }
        };
        this.lblTitle = new JLabel();
        this.tableExtensions = new JTable(this.modelTableExtensions) {
            public boolean isCellEditable(int row, int column) {
                if (column==1) return true;
                return false;
            };
        };
        this.scrollPane = new JScrollPane(tableExtensions);
        this.panel = new Panel();
        this.btnApply = new JButton();
        this.btnCancel = new JButton();
    }

    private void positionUIObjects() {
        Container pane = getContentPane();
        pane.add(lblTitle, BorderLayout.NORTH);
        pane.add(scrollPane, BorderLayout.CENTER);
        pane.add(panel, BorderLayout.SOUTH);
        pack();
    }

    private void configureUIObjects() {
        confFrame();
        confLblTitle();
        confScrollPane();
        confTable();
        confPanel();
        confBtnApply();
        confBtnCancel();
    }

    private void confFrame() {
        Dimension defaultSize = new Dimension(350, 420);
        setAlwaysOnTop(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setEnabled(true);
        setPreferredSize(defaultSize);
        setResizable(false);
        setSize(defaultSize);
        setTitle(TITLE);
        setVisible(true);
    }

    private void confLblTitle() {
        lblTitle.setFont(new Font(lblTitle.getFont().getName(), Font.BOLD, 16));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setText(TITLE);
    }

    private void confScrollPane() {
    }

    private void confTable() {
        modelTableExtensions.setColumnIdentifiers(tablesNames);
        tableExtensions.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        tableExtensions.setBackground(this.getBackground());
        tableExtensions.setDragEnabled(false);
    }

    private void confPanel() {
        panel.add(btnApply);
        panel.add(btnCancel);
    }

    private void confBtnApply() {
        btnApply.setText("Apply");
        btnApply.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                btnApplyAction();
            }
        });
    }

    private void confBtnCancel() {
        btnCancel.setText("Cancel");
        btnCancel.addActionListener(e -> this.dispose());
    }

    private void fetchExtensions() {
        for (UIExtension uIExtension : controller.getExtensions()) {
            addExtensionToModel(uIExtension.getExtension());
        }
    }

    private void addExtensionToModel(Extension extension) {
        if (extension != null) {
            modelTableExtensions.addRow(new Object[]{extension.getName(), extension.isEnabled()});
        }
    }

    private void btnApplyAction() {
        for (int i = 0; i < modelTableExtensions.getRowCount(); i++) {
            Object extensionName = modelTableExtensions.getValueAt(i, 0);
            Object extensionEnable = modelTableExtensions.getValueAt(i, 1);
            controller.changeExtensionState((String) extensionName, (boolean) extensionEnable);
        }
        this.dispose();
    }
}
