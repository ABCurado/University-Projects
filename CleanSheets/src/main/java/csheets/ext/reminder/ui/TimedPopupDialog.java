package csheets.ext.reminder.ui;

import csheets.domain.Reminder;
import csheets.ext.reminder.ReminderController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 *
 * @author Rui Freitas
 */
public class TimedPopupDialog extends javax.swing.JDialog {

	private static final int TIME_VISIBLE = 5000;
	private ReminderController theController;
	private Reminder reminder;
	private final Timer taskTimer;

	/**
	 * Creates new form TimedPopupDialog
	 *
	 * @param parent The parent frame.
	 * @param title The window title.
	 * @param controller The events controller.
	 * @param reminder reminder
	 */
	public TimedPopupDialog(java.awt.Frame parent, String title,
							ReminderController controller, Reminder reminder) {
		super(parent, true);
		initComponents();
		this.theController = controller;
		this.reminder = reminder;
		this.setTitle(title);
		this.jLabelDescription.setText(reminder.description());
		taskTimer = new Timer(TIME_VISIBLE, new ActionListener() {
							  @Override
							  public void actionPerformed(ActionEvent e) {
								  theController.alert(reminder, false);
								  close();
							  }
						  });

		taskTimer.start();
		setLocationRelativeTo(null);
		setModal(true);
		pack();
		setVisible(true);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabelDescription = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jButton1.setText("Ok");
        jButton1.setMaximumSize(new java.awt.Dimension(70, 30));
        jButton1.setMinimumSize(new java.awt.Dimension(70, 30));
        jButton1.setPreferredSize(new java.awt.Dimension(70, 30));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Snooze");
        jButton2.setMaximumSize(new java.awt.Dimension(70, 30));
        jButton2.setMinimumSize(new java.awt.Dimension(70, 30));
        jButton2.setPreferredSize(new java.awt.Dimension(70, 30));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabelDescription.setText("jLabel2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabelDescription)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
		theController.alert(reminder, false);
		close();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
		// TODO add your handling code here:
		theController.snoozeReminder(reminder);
		close();
    }//GEN-LAST:event_jButton2ActionPerformed

	private void close() {
		this.taskTimer.stop();
		dispose();
	}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabelDescription;
    // End of variables declaration//GEN-END:variables
}