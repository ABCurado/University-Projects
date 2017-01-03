package csheets.ext.chatApp.ui;

import csheets.ext.chatApp.application.ChatAppController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 *
 * @author Carlos Santos
 */
public class TimedPopupMessageDialog extends javax.swing.JDialog {

	private static final int TIME_VISIBLE = 5000;
	private ChatAppController theController;
	private String message;
	private final Timer taskTimer;

	/**
	 * Creates new form TimedPopupDialog
	 *
	 * @param parent The parent frame.
	 * @param message The message to display.
	 */
	public TimedPopupMessageDialog(java.awt.Frame parent, String message) {
		super(parent, true);
		this.message = message;

		setTitle("CleanSheets");
		taskTimer = new Timer(TIME_VISIBLE, new ActionListener() {
							  @Override
							  public void actionPerformed(ActionEvent e) {
								  close();
							  }
						  });

		taskTimer.start();

		this.setLocationRelativeTo(parent);
		setModal(true);
		initComponents();
		lblMessage.setText(message);
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

        lblAlert = new javax.swing.JLabel();
        lblMessage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        lblAlert.setFont(new java.awt.Font("Cantarell", 0, 14)); // NOI18N
        lblAlert.setForeground(new java.awt.Color(255, 0, 9));
        lblAlert.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAlert.setText("RECEIVED A MESSAGE!");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAlert)
                    .addComponent(lblMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAlert, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

	private void close() {
		this.taskTimer.stop();
		dispose();
	}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblAlert;
    private javax.swing.JLabel lblMessage;
    // End of variables declaration//GEN-END:variables
}
