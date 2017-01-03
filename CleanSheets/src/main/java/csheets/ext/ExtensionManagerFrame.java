/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Pedro Gomes 1130383@isep.ipp.pt
 */
public class ExtensionManagerFrame extends JFrame {

	private static final String TITLE = "Load Extensions";
	private static final int locationX = 500;
	private static final int locationY = 100;
	private static final int height = 445;
	private static final int width = 800;

	private final Object[] extensionNames = {"Extension Name", "Version", "Description", "Enable"};

	private List<String> extensions = new ArrayList<>();
	private List<String> toLoadExtensions = new ArrayList<>();

	private boolean flag = false;
	private CountDownLatch signal;
	private JLabel lblTitle;
	private JScrollPane scrollPane;
	private JTable tableExtensions;
	private DefaultTableModel modelTableExtensions;
	private Panel panel;
	private JButton btnApply;

	public ExtensionManagerFrame(List<String> extensionList,
								 CountDownLatch s) {
		extensions = extensionList;
		signal = s;
		flag = false;
		createUIObjects();
		configureUIObjects();
		positionUIObjects();
		fetchExtensions();
	}

	private void createUIObjects() {
		this.modelTableExtensions = new DefaultTableModel(0, extensionNames.length) {
			public Class<?> getColumnClass(int column) {
				switch (column) {
					case 0:
						return String.class;
					case 1:
						return String.class;
					case 2:
						return String.class;
					case 3:
						return Boolean.class;
					default:
						return String.class;
				}
			}
		};
		this.lblTitle = new JLabel();
		this.tableExtensions = new JTable(this.modelTableExtensions) {
			public boolean isCellEditable(int row, int column) {
				if (column == 3) {
					return true;
				}
				return false;
			}
		};
		this.scrollPane = new JScrollPane(tableExtensions);
		this.panel = new Panel();
		this.btnApply = new JButton();
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
	}

	private void confFrame() {
		Dimension size = new Dimension(width, height);
		setEnabled(true);
		setVisible(true);
		setLocation(locationX, locationY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(size);
		setResizable(false);
		setTitle(TITLE);
	}

	private void confLblTitle() {
		lblTitle.setFont(new Font(lblTitle.getFont().getName(), Font.BOLD, 16));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setText(TITLE);
	}

	private void confScrollPane() {
	}

	private void confTable() {
		modelTableExtensions.setColumnIdentifiers(extensionNames);
		tableExtensions.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		tableExtensions.setBackground(this.getBackground());
		tableExtensions.setDragEnabled(false);
	}

	private void confPanel() {
		panel.add(btnApply);
	}

	private void confBtnApply() {
		btnApply.setText("Apply");
		btnApply.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				btnApplyAction();
			}
		});
	}

	private void fetchExtensions() {
		String[] a = new String[4];
		for (String s : extensions) {
			a = s.split(";");
			String[] b = a[0].split("\\.");
			String t = (b[3] + " - " + a[1] + " - " + a[2]);
			addExtensionToModel(b[3], a[1], a[2]);
		}
	}

	private void addExtensionToModel(String extension, String version,
									 String description) {
		if (extension != null) {
			modelTableExtensions.
				addRow(new Object[]{extension, version, description, true});
		}
	}

	private void btnApplyAction() {
		String[] a = new String[4];
		Boolean c;
		for (int i = 0; i < extensions.size(); i++) {
			Object extensionEnable = modelTableExtensions.getValueAt(i, 3);
			c = (Boolean) extensionEnable;
			if (c == false && (i == 3 || i == 19)) {//Force
				String[] b = extensions.get(i).split(";");
				toLoadExtensions.add(b[0]);
			} else if (c == true) {
				a = extensions.get(i).split(";");
				toLoadExtensions.add(a[0]);
			}
		}
		this.signal.countDown();
		this.dispose();
	}

	public List<String> getExtension() {
		return this.toLoadExtensions;
	}

	public synchronized boolean keep() {
		return this.flag;
	}
}
