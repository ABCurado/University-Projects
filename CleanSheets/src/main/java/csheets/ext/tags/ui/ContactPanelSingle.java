package csheets.ext.tags.ui;

import csheets.domain.CompanyContact;
import csheets.domain.Contact;
import csheets.domain.PersonContact;
import csheets.ext.contacts.ContactsController;
import csheets.ext.contacts.ui.CompanyView;
import csheets.ext.contacts.ui.PersonView;
import csheets.ext.tags.TagController;
import csheets.ui.ctrl.UIController;
import java.awt.Image;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;

/**
 *
 * @author Marcelo Barroso 1131399
 */
public class ContactPanelSingle extends javax.swing.JPanel implements Observer {

	private Contact contact;
	private TagController controller;
	private ContactsController contactsController;

	/**
	 * Creates new form ContactPanel3
	 *
	 * @param controller controller of events
	 * @param contact contact
	 */
	public ContactPanelSingle(TagController controller, Contact contact) {
		this.controller = controller;
		this.contact = contact;
		this.initComponents();
		this.update(null, null);
	}

	@Override
	public void update(Observable o, Object arg) {
		if (this.contact != null) {
			this.labelName.setText(this.contact.toString());
			if (this.contact instanceof CompanyContact) {
				this.jLabelType.setText("Company");
			} else {
				this.jLabelType.setText("Person");
			}
			try {
				this.jLabelPhoto.setIcon(new ImageIcon(this.controller.
					contactPhoto(contact).getScaledInstance(this.jLabelPhoto.
					getWidth(), this.jLabelPhoto.getHeight(), Image.SCALE_SMOOTH)));
			} catch (Exception ex) {
				System.out.println(ex);
			}
		}
	}

	public void contactView() {

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
        jLabelType = new javax.swing.JLabel();
        jButtonEdit = new javax.swing.JButton();
        jLabelPhoto = new javax.swing.JLabel();
        jButtonEdit1 = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(250, 65));
        setMinimumSize(new java.awt.Dimension(250, 65));
        setPreferredSize(new java.awt.Dimension(250, 65));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ContactPanelSingle.this.mouseClicked(evt);
            }
        });
        setLayout(new java.awt.BorderLayout());

        jPanel1.setMaximumSize(new java.awt.Dimension(265, 65));
        jPanel1.setMinimumSize(new java.awt.Dimension(265, 65));
        jPanel1.setPreferredSize(new java.awt.Dimension(265, 65));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        labelName.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        labelName.setText("Name");
        labelName.setAutoscrolls(true);
        labelName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelNameMouseClicked(evt);
            }
        });

        jLabelType.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        jLabelType.setText("type");
        jLabelType.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelTypeMouseClicked(evt);
            }
        });

        jButtonEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/csheets/res/img/new.png"))); // NOI18N
        jButtonEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditActionPerformed(evt);
            }
        });

        jLabelPhoto.setBackground(new java.awt.Color(204, 204, 204));
        jLabelPhoto.setMaximumSize(new java.awt.Dimension(40, 40));
        jLabelPhoto.setMinimumSize(new java.awt.Dimension(40, 40));
        jLabelPhoto.setPreferredSize(new java.awt.Dimension(40, 40));
        jLabelPhoto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelPhotoMouseClicked(evt);
            }
        });

        jButtonEdit1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/csheets/res/img/edit.png"))); // NOI18N
        jButtonEdit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEdit1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelName, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabelType, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonEdit, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(jButtonEdit1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelName)
                            .addComponent(jButtonEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelType)
                            .addComponent(jButtonEdit1))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void mouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mouseClicked
		this.contactView();
    }//GEN-LAST:event_mouseClicked

    private void jButtonEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditActionPerformed
		new TagManager(UIController.getUIController().getFrame(), this.controller, this.contact).
			setVisible(true);
    }//GEN-LAST:event_jButtonEditActionPerformed

    private void labelNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelNameMouseClicked
		if (this.contact instanceof CompanyContact) {
			new CompanyView(contactsController, this.contact).setVisible(true);
		} else if (this.contact instanceof PersonContact) {
			new PersonView(contactsController, contact).setVisible(true);
		}
    }//GEN-LAST:event_labelNameMouseClicked

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
		if (this.contact instanceof CompanyContact) {
			new CompanyView(contactsController, this.contact).setVisible(true);
		} else if (this.contact instanceof PersonContact) {
			new PersonView(contactsController, contact).setVisible(true);
		}
    }//GEN-LAST:event_jPanel1MouseClicked

    private void jLabelPhotoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelPhotoMouseClicked
		if (this.contact instanceof CompanyContact) {
			new CompanyView(contactsController, this.contact).setVisible(true);
		} else if (this.contact instanceof PersonContact) {
			new PersonView(contactsController, contact).setVisible(true);
		}
    }//GEN-LAST:event_jLabelPhotoMouseClicked

    private void jLabelTypeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelTypeMouseClicked
		if (this.contact instanceof CompanyContact) {
			new CompanyView(contactsController, this.contact).setVisible(true);
		} else if (this.contact instanceof PersonContact) {
			new PersonView(contactsController, contact).setVisible(true);
		}
    }//GEN-LAST:event_jLabelTypeMouseClicked

    private void jButtonEdit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEdit1ActionPerformed
		new ContactManager(UIController.getUIController().getFrame(), this.controller, this.contact).
			setVisible(true);
    }//GEN-LAST:event_jButtonEdit1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonEdit;
    private javax.swing.JButton jButtonEdit1;
    private javax.swing.JLabel jLabelPhoto;
    private javax.swing.JLabel jLabelType;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel labelName;
    // End of variables declaration//GEN-END:variables
}
