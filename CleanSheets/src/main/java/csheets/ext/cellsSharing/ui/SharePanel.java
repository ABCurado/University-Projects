/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.cellsSharing.ui;

import csheets.core.Cell;
import csheets.core.CellListener;
import csheets.core.formula.compiler.FormulaCompilationException;
import csheets.ext.cellsSharing.ShareExtension;
import csheets.notification.Notification;
import csheets.support.Task;
import csheets.support.TaskManager;
import csheets.ui.DefaulListModel;
import csheets.ui.ctrl.SelectionEvent;
import csheets.ui.ctrl.SelectionListener;
import csheets.ui.ctrl.UIController;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 * A panel for share a cell
 *
 */
public class SharePanel extends javax.swing.JPanel implements CellListener, SelectionListener, Observer {

	private final UIController uiController;

	/**
	 * The assertion controller
	 */
	private ShareCellsController controller;

	/**
	 * Default instance list
	 */
	private DefaultListModel instanceListModel;

	/**
	 * Default receive list
	 */
	private DefaultListModel receiveListModel;

	/**
	 * Received elements.
	 */
	private List<Map<String, String>> receivedElements;

	/**
	 * Hostname
	 */
	private String host;

	/**
	 * Cell selected
	 */
	private Cell[][] cells;

	/**
	 * Task Manager
	 */
	private final TaskManager manager = new TaskManager();

	/**
	 * Creates new form SharePanel
	 *
	 * @param uiController a ui controller
	 * @param controller Share cells controller.
	 */
	public SharePanel(UIController uiController, ShareCellsController controller) {
		this.uiController = uiController;

		setName(ShareExtension.NAME);

		// Create default lists
		instanceListModel = new DefaultListModel();
		receiveListModel = new DefaulListModel();
		receivedElements = new ArrayList<>();
		//TODO

		initComponents();

		uiController.addSelectionListener(this);

		instancesList.setModel(instanceListModel);
		receiveList.setModel(receiveListModel);

		// @IMPROVEMENT: Needs to get the timer from the configuration.
		// Maybe get it through a configuration file?
		final int defaultSeconds = 3;
		final int defaultPort = 20000;

		this.controller = controller;
		this.controller.startUdpService(this, defaultSeconds);
		this.controller.startTcpService(this, defaultPort);
		Notification.cellInformer().addObserver(this);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        instancesList = new javax.swing.JList<>();
        sendButton = new javax.swing.JButton();
        connectButton = new javax.swing.JButton();
        disconnectButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        receiveList = new javax.swing.JList<>();
        receiveButton = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Instances", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13))); // NOI18N

        instancesList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                instancesListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(instancesList);

        sendButton.setText("Send");
        sendButton.setEnabled(false);
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });

        connectButton.setText("Connect");
        connectButton.setEnabled(false);
        connectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectButtonActionPerformed(evt);
            }
        });

        disconnectButton.setText("Disconnect");
        disconnectButton.setEnabled(false);
        disconnectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                disconnectButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(connectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(disconnectButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(connectButton)
                    .addComponent(sendButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(disconnectButton)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cells Received", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13))); // NOI18N

        receiveList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                receiveListValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(receiveList);

        receiveButton.setText("Receive");
        receiveButton.setEnabled(false);
        receiveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                receiveButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(receiveButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                .addGap(11, 11, 11)
                .addComponent(receiveButton)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonActionPerformed

		String cell = "", value = "";

		int line = cells.length;
		int column = cells[0].length;

		if ((line > 2 && column == 1) || (line == 1 && column > 2) || (line > 1 && column > 1)) {
			cell = cells[0][0].getAddress() + ":" + cells[cells.length - 1][cells[0].length - 1].
				getAddress();
			value = "[\"" + cells[0][0].getValue() + "\", ..., \"" + cells[cells.length - 1][cells[0].length - 1].
				getValue() + "\"]";
		} else if ((line == 2 && column == 1) || (line == 1 && column == 2)) {
			cell = cells[0][0].getAddress() + ":" + cells[cells.length - 1][cells[0].length - 1].
				getAddress();
			value = "[\"" + cells[0][0].getValue() + "\", \"" + cells[cells.length - 1][cells[0].length - 1].
				getValue() + "\"]";
		} else {
			// The cells only have one value. A single cell.
			cell = cells[0][0].getAddress().toString();
			value = "[\"" + cells[0][0].getValue() + "\"]";
		}

		int reply = JOptionPane.
			showConfirmDialog(this, "::. Send information .::\n"
							  + "Host: " + host
							  + "\nCell: " + cell
							  + "\nValue: " + value);

		if (reply == JOptionPane.YES_OPTION) {
			controller.sendCells(host, cells);
		} else if (reply == JOptionPane.NO_OPTION) {
			// option 2
		}
    }//GEN-LAST:event_sendButtonActionPerformed

    private void instancesListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_instancesListValueChanged
		if (evt.getValueIsAdjusting() == false) {
			if (instancesList.getSelectedIndex() == -1) {
				//No selection.
				sendButton.setEnabled(true);
				connectButton.setEnabled(true);
			} else {
				//Selection.
				cells = uiController.focusOwner.getSelectedCells();

				if (cells.length <= 0) {
					return;
				}
				sendButton.setEnabled(true);
				if (!disconnectButton.isEnabled()) {
					connectButton.setEnabled(true);
				}
				host = instancesList.getSelectedValue();
			}
		}
    }//GEN-LAST:event_instancesListValueChanged

	public void updateInstanceList(List<String> addresses) {
		for (String address : addresses) {
			if (!instanceListModel.contains(address)) {
				instanceListModel.addElement(address);

				manager.after(20).once(new Task() {
					public void fire() {
						instanceListModel.removeElement(address);
						instancesList.setModel(instanceListModel);
					}
				});
			}
		}

		instancesList.setModel(instanceListModel);
		repaint();
	}

	private String getAddress(int column, int row) {
		String columnStr;
		int tempColumn = column;
		for (columnStr = ""; tempColumn >= 0; tempColumn = tempColumn
			/ ('Z' - 'A' + 1) - 1) {
			columnStr = (char) ((char) (tempColumn % ('Z'
				- 'A' + 1)) + 'A') + columnStr;
		}

		return columnStr + (row + 1);
	}

	private String getValue(String data) {
		String[] keys = data.split(";");

		if (keys.length == 1) {
			return "\"\"";
		}

		return "\"" + keys[1] + "\"";
	}

	public void updateReceiveList(Map<String, String> cells) {
		int index = 0, size = cells.size() - 1;
		String firstAddress = "";
		String firstValue = "";
		String secondAddress = ":";
		String secondValue = ", ..., ";

		if (cells.size() == 1) {
			for (Map.Entry<String, String> entry : cells.entrySet()) {
				String[] key = entry.getKey().split(":");
				firstAddress += this.
					getAddress(Integer.parseInt(key[0]), Integer.
							   parseInt(key[1]));
				firstValue += this.getValue(entry.getValue());
			}

			secondAddress = "";
			secondValue = "";
		} else if (cells.size() == 2) {
			secondValue = ", ";

			for (Map.Entry<String, String> entry : cells.entrySet()) {
				String[] key = entry.getKey().split(":");

				if (index == 0) {
					firstAddress += this.
						getAddress(Integer.parseInt(key[0]), Integer.
								   parseInt(key[1]));
					firstValue += this.getValue(entry.getValue());
				}

				if (index == 1) {
					secondAddress += this.
						getAddress(Integer.parseInt(key[0]), Integer.
								   parseInt(key[1]));
					secondValue += this.getValue(entry.getValue());
				}

				index++;
			}

		} else {
			// The number of cells received are above two.
			for (Map.Entry<String, String> entry : cells.entrySet()) {
				if (index == 0) {
					String[] key = entry.getKey().split(":");
					firstAddress += this.
						getAddress(Integer.parseInt(key[0]), Integer.
								   parseInt(key[1]));
					firstValue += this.getValue(entry.getValue());
				}

				if (index == size) {
					String[] key = entry.getKey().split(":");
					secondAddress += this.
						getAddress(Integer.parseInt(key[0]), Integer.
								   parseInt(key[1]));
					secondValue += this.getValue(entry.getValue());
				}

				index++;
			}
		}

		String element = firstAddress + secondAddress + " => [" + firstValue + secondValue + "]";

		receiveListModel.addElement(element);
		receivedElements.add(receiveListModel.size() - 1, cells);

		manager.after(30).once(new Task() {
			public void fire() {
				receiveListModel.removeElement(element);
				receiveList.setModel(receiveListModel);
				receivedElements.remove(cells);
			}
		});

		receiveList.setModel(receiveListModel);
		repaint();
	}

    private void receiveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_receiveButtonActionPerformed

		int reply = JOptionPane.
			showConfirmDialog(this, "::. Receive information .::\n"
							  + "You want to receive these cells?");

		if (reply == JOptionPane.YES_OPTION) {
			try {
				controller.updateCells(uiController, receivedElements.
									   get(receiveList.getSelectedIndex()));
			} catch (FormulaCompilationException ex) {
				System.out.println("Error!");
			}
		} else if (reply == JOptionPane.NO_OPTION) {
			// nothing to do
		}
    }//GEN-LAST:event_receiveButtonActionPerformed

    private void receiveListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_receiveListValueChanged

		if (evt.getValueIsAdjusting() == false) {
			if (receiveList.getSelectedIndex() == -1) {
				//No selection.
				receiveButton.setEnabled(false);
			} else {
				//Selection.
				cells = uiController.focusOwner.getSelectedCells();

				if (cells.length <= 0) {
					return;
				}
				receiveButton.setEnabled(true);
				host = receiveList.getSelectedValue();
			}
		}
    }//GEN-LAST:event_receiveListValueChanged

    private void connectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectButtonActionPerformed
		int reply = JOptionPane.
			showConfirmDialog(this, "::. Connect to instance .::\n"
							  + "Do you want to connect to this instance?");

		if (reply == JOptionPane.YES_OPTION) {
			this.controller.setContinuousTarget(host);
			this.uiController.getActiveSpreadsheet().addCellListener(this);
			disconnectButton.setEnabled(true);
			connectButton.setEnabled(false);
		} else if (reply == JOptionPane.NO_OPTION) {
			// nothing to do
		}
    }//GEN-LAST:event_connectButtonActionPerformed

    private void disconnectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_disconnectButtonActionPerformed
		int reply = JOptionPane.
			showConfirmDialog(this, "::. Disconnect from instance .::\n"
							  + "Are you sure you want to disconnect?");

		if (reply == JOptionPane.YES_OPTION) {
			this.uiController.getActiveSpreadsheet().removeCellListener(this);
			this.controller.stopConnection();
			disconnectButton.setEnabled(false);
		} else if (reply == JOptionPane.NO_OPTION) {
			// nothing to do
		}
    }//GEN-LAST:event_disconnectButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton connectButton;
    private javax.swing.JButton disconnectButton;
    private javax.swing.JList<String> instancesList;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton receiveButton;
    private javax.swing.JList<String> receiveList;
    private javax.swing.JButton sendButton;
    // End of variables declaration//GEN-END:variables

	@Override
	public void selectionChanged(SelectionEvent event) {
		//
	}

	@Override
	public void update(Observable o, Object object) {
		if (object instanceof Cell) {
			Cell cell = (Cell) object;
			this.controller.continuousSending(cell);
		} else if (object instanceof Map) {
			Map<String, String> mapCells = (Map<String, String>) object;

			Map.Entry<String, String> entry = mapCells.entrySet().iterator().
				next();

			if (entry.getKey().split("@")[0].equals("Continuous")) {

				String address = entry.getKey().split("@")[1];
				String data = entry.getValue();

				mapCells.put(address, data);
				mapCells.remove(entry.getKey());

				try {
					controller.updateCells(uiController, mapCells);
				} catch (FormulaCompilationException ex) {
					Logger.getLogger(SharePanel.class.getName()).
						log(Level.SEVERE, null, ex);
				}
				return;
			}

			updateReceiveList(mapCells);
		}
		if (object instanceof List) {
			List<String> addresses = (List<String>) object;
			updateInstanceList(addresses);
		}
	}

	public boolean askForConfirmation(String hostname) {
		int reply = JOptionPane.
			showConfirmDialog(this, "::. Receive information .::\n"
							  + "An instance (" + hostname + ") wants to establish a connection with you.\nDo you wish to receive its cells?");

		return !(reply == JOptionPane.NO_OPTION || reply == JOptionPane.CANCEL_OPTION);
	}

	public void notifyDisconnection(String hostname) {
		JOptionPane.
			showMessageDialog(this, "An instance (" + hostname + ") has disconnected from you.");
	}

	@Override
	public void valueChanged(Cell cell) {
		this.controller.continuousSending(cell);
	}

	@Override
	public void contentChanged(Cell cell) {
		// To be developed in IPC01.3
	}

	@Override
	public void dependentsChanged(Cell cell) {
		// To be developed in IPC01.3
	}

	@Override
	public void cellCleared(Cell cell) {
		// Do nothing for now
	}

	@Override
	public void cellCopied(Cell cell, Cell source) {
		// Do nothing for now
	}
}
