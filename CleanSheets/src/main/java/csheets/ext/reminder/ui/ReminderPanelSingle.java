package csheets.ext.reminder.ui;

import csheets.domain.Reminder;
import csheets.ext.reminder.ReminderController;
import javax.swing.JOptionPane;

/**
 *
 * @author Marcelo Barroso 1131399
 */
public class ReminderPanelSingle extends javax.swing.JPanel {

	private ReminderController controller;
	private Reminder reminder;

	/**
	 * Creates new form ReminderPanel
	 *
	 * @param controller controller of events
	 * @param reminder reminder
	 */
	public ReminderPanelSingle(ReminderController controller, Reminder reminder) {
		this.controller = controller;
		this.reminder = reminder;
		initComponents();
		init();
	}

	private void init() {
		this.labelName.setText(this.reminder.name());
		this.labelDescription.setText(this.reminder.description());
		//this.labelDate.setText(DateTime.
		//	format(this.reminder.timeOfReminder(), "YYYY/MM/dd hh:mm"));
		this.labelDate.setText(this.reminder.timeOfRemider());
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
        labelName = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        labelDescription = new javax.swing.JLabel();
        labelDate = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();

        setMaximumSize(new java.awt.Dimension(250, 65));
        setMinimumSize(new java.awt.Dimension(250, 65));
        setPreferredSize(new java.awt.Dimension(250, 65));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ReminderPanelSingle.this.mouseClicked(evt);
            }
        });
        setLayout(new java.awt.BorderLayout());

        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        labelName.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        labelName.setText("name");
        labelName.setAutoscrolls(true);
        labelName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelNameMouseClicked(evt);
            }
        });

        labelDescription.setFont(new java.awt.Font("Lucida Grande", 0, 11)); // NOI18N
        labelDescription.setText("description");

        labelDate.setFont(new java.awt.Font("Lucida Grande", 0, 11)); // NOI18N
        labelDate.setText("date");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/csheets/res/img/delete.gif"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/csheets/res/img/edit.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jSeparator2)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(labelDate, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(labelDescription)
                        .addComponent(labelName, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(170, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelDate, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(3, 3, 3)
                    .addComponent(labelName, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(labelDescription)
                    .addContainerGap(65, Short.MAX_VALUE)))
        );

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void mouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mouseClicked

    }//GEN-LAST:event_mouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
		ManageReminders reminder = new ManageReminders(this.controller, this.reminder);
		int eventOption = JOptionPane.
			showConfirmDialog(null, reminder, "Edit Reminder", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		if (eventOption == JOptionPane.OK_OPTION) {
			reminder.createReminder();
		}
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

		int op = JOptionPane.
			showConfirmDialog(this, "Do you want to remove " + this.labelDescription.
							  getText() + " " + "?", "Remove Reminder", JOptionPane.OK_CANCEL_OPTION);

		if (JOptionPane.OK_OPTION == op) {
			this.controller.removeReminder(this.reminder);
		}
    }//GEN-LAST:event_jButton1ActionPerformed

    private void labelNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelNameMouseClicked

    }//GEN-LAST:event_labelNameMouseClicked

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
		if (evt.getClickCount() == 2) {
			new ReminderView(reminder);
		}
    }//GEN-LAST:event_jPanel1MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel labelDate;
    private javax.swing.JLabel labelDescription;
    private javax.swing.JLabel labelName;
    // End of variables declaration//GEN-END:variables
}
