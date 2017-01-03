/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.notes_and_lists.ui;

import csheets.domain.Contact;
import csheets.domain.List;
import csheets.domain.Note;
import csheets.ext.notes_and_lists.NotesListsController;
import csheets.ext.notes_and_lists.NotesListsExtension;
import csheets.framework.persistence.repositories.DataIntegrityViolationException;
import csheets.notification.Notification;
import csheets.ui.DefaulListModel;
import csheets.ui.ctrl.UIController;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rui Bento
 * @author Diogo Azevedo
 * @author João Martins
 */
public class NotesListsPanels extends javax.swing.JPanel implements Observer {

	private NotesListsController controller;

	/**
	 * Default receive list
	 */
	private DefaultListModel NoteListModel = new DefaulListModel();
	/**
	 * Default receive list
	 */
	private DefaultListModel VersionListModel = new DefaulListModel();

	private DefaultComboBoxModel<Contact> contactModel = new DefaultComboBoxModel();
	private DefaultListModel<Note> contactNoteModel = new DefaultListModel();
	private DefaultListModel<List> contactListModel = new DefaultListModel();
	private DefaultListModel<Integer> listVersionModel = new DefaultListModel();
	private DefaultTableModel listDataModel = new DefaultTableModel();

	/**
	 * Creates new form EventsPanel
	 *
	 * @param uiController The user interface controller.
	 */
	public NotesListsPanels(UIController uiController) {
		this.controller = new NotesListsController(uiController);
		this.setName(NotesListsExtension.NAME);
		initComponents();
		createActions();
		fetchContacts();
		this.lstNoteVersions.setEnabled(false);
		this.btnNoteEdit.setEnabled(false);
		this.btnNoteDelete.setEnabled(false);
		cbListContact.setSelectedIndex(-1);
		Notification.noteInformer().addObserver(this);
		Notification.contactInformer().addObserver(this);
		this.update(null, null);
	}

	private void defineTableModel() {
		listDataModel = new DefaultTableModel() {
			public Class<?> getColumnClass(int column) {
				switch (column) {
					case 0:
						return Boolean.class;
					case 1:
						return String.class;
					default:
						return String.class;
				}
			}
		};
		listDataModel.setColumnIdentifiers(new Object[]{"Done", "Text"});
		tableListData.setModel(listDataModel);
		int width = 50;
		tableListData.getColumnModel().getColumn(0).setMinWidth(width);
		tableListData.getColumnModel().getColumn(0).setMaxWidth(width);
	}

	private void createActions() {
		addContactsComboBoxAction();
		addListsAction();
		addListVersionsAction();
		addEditAction();
	}

	private void fetchContacts() {
		for (Contact contact : controller.getContacts()) {
			contactModel.addElement(contact);
		}
		contactListModel.clear();
	}

	private void addContactsComboBoxAction() {
		cbListContact.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					contactListModel.clear();
					for (List list : controller.getContactLists(e.getItem())) {
						contactListModel.addElement(list);
					}
					panelContactList.setVisible(true);
					panelCreateList.setVisible(true);
					panelListVersion.setVisible(false);
					panelListData.setVisible(false);
					panelListActions.setVisible(false);
				}
			}
		});
	}

	private void addListsAction() {
		lstContactLists.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				List selectedValue = lstContactLists.getSelectedValue();
				if (selectedValue != null) {
					panelListVersion.setVisible(true);
					panelListData.setVisible(true);
					panelListActions.setVisible(true);
					listVersionModel.clear();
					for (List version : controller.
						getListVersions(selectedValue).values()) {
						listVersionModel.addElement(version.getVersionNumber());
					}
					if (checkboxEditList.isSelected()) {
						btnApply.setText("Edit");
						showListEdit(controller.getVersion(selectedValue, -1));
					} else {
						hideListEdit(controller.getVersion(selectedValue, -1));
					}
				}
			}
		});
	}

	private void addListVersionsAction() {
		lstListVersions.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				List selectedList = lstContactLists.getSelectedValue();
				if (checkboxEditList.isSelected() && selectedList != null) {
					Integer versionNum = lstListVersions.getSelectedValue();
					if (versionNum != null) {
						showListEdit(controller.
							getVersion(selectedList, versionNum));
					} else {
						showListEdit(controller.getVersion(selectedList, -1));
					}
				}
			}
		});
	}

	private void addEditAction() {
		checkboxEditList.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (!btnApply.getText().equals("Create") && lstContactLists.
					getSelectedValue() != null) {
					if (checkboxEditList.isSelected()) {
						if (!lstListVersions.isSelectionEmpty()) {
							showListEdit(controller.getVersion(lstContactLists.
								getSelectedValue(), lstListVersions.
															   getSelectedValue()));
						} else {
							showListEdit(controller.getVersion(lstContactLists.
								getSelectedValue(), -1));
						}
						btnApply.setText("Edit");
					} else {
						if (!lstListVersions.isSelectionEmpty()) {
							hideListEdit(controller.getVersion(lstContactLists.
								getSelectedValue(), lstListVersions.
															   getSelectedValue()));
						} else {
							hideListEdit(controller.getVersion(lstContactLists.
								getSelectedValue(), -1));
						}
						btnApply.setText("Apply");
					}
				}
			}
		});
	}

	private void showListEdit(List l) {
		checkboxEditList.setSelected(true);
		textAreadListData.setEnabled(true);
		textAreadListData.setVisible(true);
		tableListData.setVisible(false);
		tableListData.setEnabled(false);
		textAreadListData.setText(l.getTitle() + "\n" + l.getText());
	}

	private void hideListEdit(List l) {
		checkboxEditList.setSelected(false);
		textAreadListData.setVisible(false);
		textAreadListData.setEnabled(false);
		tableListData.setEnabled(true);
		tableListData.setVisible(true);
		btnApply.setText("Apply");
		defineTableModel();
		for (List.ListLine line : l.getLines()) {
			listDataModel.addRow(new Object[]{line.getCheck(), line.getText()});
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof Contact) {
			contactModel.addElement((Contact) arg);
			cbListContact.setSelectedIndex(-1);
			cbNoteContact.setSelectedIndex(-1);
			contactListModel.clear();
			this.cbNoteContact.removeAllItems();
			for (Contact c : controller.showContacts()) {
				cbNoteContact.addItem(c);
			}
		} else if (arg instanceof Note) {
			this.NoteListModel.removeAllElements();
			if (cbNoteContact.getSelectedItem() != null) {
				Contact c = (Contact) cbNoteContact.getSelectedItem();
				for (Note note : controller.notesByContact(c)) {
					this.NoteListModel.addElement(note);
				}
				lstContactNotes.setModel(NoteListModel);
			}
			this.VersionListModel.removeAllElements();
			if (lstContactNotes.getSelectedValue() != null) {
				Note n = (Note) this.NoteListModel.
					getElementAt(this.lstContactNotes.getSelectedIndex());
				for (Note note : n.versionByNote()) {
					this.VersionListModel.addElement(note);
				}
				lstNoteVersions.setModel(VersionListModel);
			}
			refreshUI();
		} else if (arg == null) {
			this.cbNoteContact.removeAllItems();
			contactModel.removeAllElements();
			for (Contact c : controller.showContacts()) {
				cbNoteContact.addItem(c);
				contactModel.addElement(c);
			}
			cbListContact.setSelectedIndex(-1);
			contactListModel.clear();
			//NOTES
			this.NoteListModel.removeAllElements();
			if (cbNoteContact.getSelectedItem() != null) {
				Contact c = (Contact) cbNoteContact.getSelectedItem();
				for (Note note : controller.notesByContact(c)) {
					this.NoteListModel.addElement(note);
				}
				lstContactNotes.setModel(NoteListModel);
			}
		}
		refreshUI();
	}

	private void refreshUI() {
		lstContactNotes.revalidate();
		lstContactNotes.repaint();
		lstNoteVersions.revalidate();
		lstNoteVersions.repaint();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jScrollPane8 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        tabPane = new javax.swing.JTabbedPane();
        panelNotes = new javax.swing.JPanel();
        panelNoteContact = new javax.swing.JPanel();
        lblNoteContact = new javax.swing.JLabel();
        cbNoteContact = new javax.swing.JComboBox<>();
        panelNoteInfo = new javax.swing.JPanel();
        panelContactNoteList = new javax.swing.JPanel();
        lblNoteContactNotes = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstContactNotes = new javax.swing.JList<>();
        panelNoteVersionList = new javax.swing.JPanel();
        lblNoteVersion = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstNoteVersions = new javax.swing.JList<>();
        panelNoteData = new javax.swing.JPanel();
        lblNote = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        textAreaNote = new javax.swing.JTextArea();
        panelNoteActions = new javax.swing.JPanel();
        btnNoteCreate = new javax.swing.JButton();
        btnNoteEdit = new javax.swing.JButton();
        btnNoteDelete = new javax.swing.JButton();
        panelLists = new javax.swing.JPanel();
        panelListContact = new javax.swing.JPanel();
        lblListContact = new javax.swing.JLabel();
        cbListContact = new javax.swing.JComboBox<>();
        panelCreateList = new javax.swing.JPanel();
        btnCreateNewList = new javax.swing.JButton();
        panelListInfo = new javax.swing.JPanel();
        panelContactList = new javax.swing.JPanel();
        lblContactLists = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        lstContactLists = new javax.swing.JList<>();
        panelListVersion = new javax.swing.JPanel();
        lblListVersions = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        lstListVersions = new javax.swing.JList<>();
        panelListData = new javax.swing.JPanel();
        lblList = new javax.swing.JLabel();
        checkboxEditList = new javax.swing.JCheckBox();
        jScrollPane6 = new javax.swing.JScrollPane();
        tableListData = new javax.swing.JTable() {
            public boolean isCellEditable(int row, int column) {
                switch(column) {
                    case 0: return true;
                    case 1: return false;
                    default: return false;
                }
            };
        };
        jScrollPane7 = new javax.swing.JScrollPane();
        textAreadListData = new javax.swing.JTextArea();
        panelListActions = new javax.swing.JPanel();
        btnApply = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();

        jMenu1.setText("jMenu1");

        jScrollPane8.setViewportView(jEditorPane1);

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        lblNoteContact.setText("Contact:");

        cbNoteContact.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbNoteContactItemStateChanged(evt);
            }
        });
        cbNoteContact.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cbNoteContactKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout panelNoteContactLayout = new javax.swing.GroupLayout(panelNoteContact);
        panelNoteContact.setLayout(panelNoteContactLayout);
        panelNoteContactLayout.setHorizontalGroup(
            panelNoteContactLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNoteContactLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNoteContact)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbNoteContact, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelNoteContactLayout.setVerticalGroup(
            panelNoteContactLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNoteContactLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblNoteContact)
                .addComponent(cbNoteContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        lblNoteContactNotes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNoteContactNotes.setText("Contact Notes");

        lstContactNotes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstContactNotesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(lstContactNotes);

        javax.swing.GroupLayout panelContactNoteListLayout = new javax.swing.GroupLayout(panelContactNoteList);
        panelContactNoteList.setLayout(panelContactNoteListLayout);
        panelContactNoteListLayout.setHorizontalGroup(
            panelContactNoteListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
            .addComponent(lblNoteContactNotes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelContactNoteListLayout.setVerticalGroup(
            panelContactNoteListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContactNoteListLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNoteContactNotes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE))
        );

        lblNoteVersion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNoteVersion.setText("Note Versions");

        jScrollPane1.setViewportView(lstNoteVersions);

        javax.swing.GroupLayout panelNoteVersionListLayout = new javax.swing.GroupLayout(panelNoteVersionList);
        panelNoteVersionList.setLayout(panelNoteVersionListLayout);
        panelNoteVersionListLayout.setHorizontalGroup(
            panelNoteVersionListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(lblNoteVersion, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
        );
        panelNoteVersionListLayout.setVerticalGroup(
            panelNoteVersionListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNoteVersionListLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNoteVersion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1))
        );

        javax.swing.GroupLayout panelNoteInfoLayout = new javax.swing.GroupLayout(panelNoteInfo);
        panelNoteInfo.setLayout(panelNoteInfoLayout);
        panelNoteInfoLayout.setHorizontalGroup(
            panelNoteInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNoteInfoLayout.createSequentialGroup()
                .addComponent(panelContactNoteList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelNoteVersionList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelNoteInfoLayout.setVerticalGroup(
            panelNoteInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNoteInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelNoteInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelContactNoteList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelNoteVersionList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        lblNote.setText("Note");

        textAreaNote.setColumns(20);
        textAreaNote.setRows(5);
        jScrollPane3.setViewportView(textAreaNote);

        javax.swing.GroupLayout panelNoteDataLayout = new javax.swing.GroupLayout(panelNoteData);
        panelNoteData.setLayout(panelNoteDataLayout);
        panelNoteDataLayout.setHorizontalGroup(
            panelNoteDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNote, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane3)
        );
        panelNoteDataLayout.setVerticalGroup(
            panelNoteDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNoteDataLayout.createSequentialGroup()
                .addComponent(lblNote)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE))
        );

        btnNoteCreate.setText("Create");
        btnNoteCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNoteCreateActionPerformed(evt);
            }
        });

        btnNoteEdit.setText("Edit");
        btnNoteEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNoteEditActionPerformed(evt);
            }
        });

        btnNoteDelete.setText("Delete");
        btnNoteDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNoteDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelNoteActionsLayout = new javax.swing.GroupLayout(panelNoteActions);
        panelNoteActions.setLayout(panelNoteActionsLayout);
        panelNoteActionsLayout.setHorizontalGroup(
            panelNoteActionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNoteActionsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnNoteCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNoteEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNoteDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelNoteActionsLayout.setVerticalGroup(
            panelNoteActionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNoteActionsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelNoteActionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNoteCreate)
                    .addComponent(btnNoteEdit)
                    .addComponent(btnNoteDelete))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelNotesLayout = new javax.swing.GroupLayout(panelNotes);
        panelNotes.setLayout(panelNotesLayout);
        panelNotesLayout.setHorizontalGroup(
            panelNotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNotesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelNotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelNoteContact, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelNotesLayout.createSequentialGroup()
                        .addGroup(panelNotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(panelNoteActions, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelNoteData, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelNoteInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        panelNotesLayout.setVerticalGroup(
            panelNotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNotesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelNoteContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelNoteInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelNoteData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelNoteActions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tabPane.addTab("Notes", panelNotes);

        lblListContact.setText("Contact:");

        cbListContact.setModel(contactModel);
        cbListContact.setSelectedItem(null);
        cbListContact.setSelectedIndex(-1);

        javax.swing.GroupLayout panelListContactLayout = new javax.swing.GroupLayout(panelListContact);
        panelListContact.setLayout(panelListContactLayout);
        panelListContactLayout.setHorizontalGroup(
            panelListContactLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelListContactLayout.createSequentialGroup()
                .addComponent(lblListContact)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbListContact, 0, 289, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelListContactLayout.setVerticalGroup(
            panelListContactLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelListContactLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblListContact)
                .addComponent(cbListContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        btnCreateNewList.setText("Create New List");
        btnCreateNewList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateNewListActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelCreateListLayout = new javax.swing.GroupLayout(panelCreateList);
        panelCreateList.setLayout(panelCreateListLayout);
        panelCreateListLayout.setHorizontalGroup(
            panelCreateListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnCreateNewList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelCreateListLayout.setVerticalGroup(
            panelCreateListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnCreateNewList)
        );

        panelListInfo.setPreferredSize(new java.awt.Dimension(239, 200));

        panelContactList.setPreferredSize(new java.awt.Dimension(93, 139));
        panelContactList.setVisible(false);

        lblContactLists.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblContactLists.setText("Contact Lists");

        lstContactLists.setModel(contactListModel);
        lstContactLists.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane4.setViewportView(lstContactLists);

        javax.swing.GroupLayout panelContactListLayout = new javax.swing.GroupLayout(panelContactList);
        panelContactList.setLayout(panelContactListLayout);
        panelContactListLayout.setHorizontalGroup(
            panelContactListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblContactLists, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
        );
        panelContactListLayout.setVerticalGroup(
            panelContactListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContactListLayout.createSequentialGroup()
                .addComponent(lblContactLists)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4))
        );

        panelListVersion.setPreferredSize(new java.awt.Dimension(120, 139));

        lblListVersions.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblListVersions.setText("List Versions");

        lstListVersions.setModel(listVersionModel);
        lstListVersions.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane5.setViewportView(lstListVersions);

        javax.swing.GroupLayout panelListVersionLayout = new javax.swing.GroupLayout(panelListVersion);
        panelListVersion.setLayout(panelListVersionLayout);
        panelListVersionLayout.setHorizontalGroup(
            panelListVersionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblListVersions, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        panelListVersionLayout.setVerticalGroup(
            panelListVersionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelListVersionLayout.createSequentialGroup()
                .addComponent(lblListVersions)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelListInfoLayout = new javax.swing.GroupLayout(panelListInfo);
        panelListInfo.setLayout(panelListInfoLayout);
        panelListInfoLayout.setHorizontalGroup(
            panelListInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelListInfoLayout.createSequentialGroup()
                .addComponent(panelContactList, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelListVersion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelListInfoLayout.setVerticalGroup(
            panelListInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelListVersion, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
            .addComponent(panelContactList, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
        );

        panelListData.setPreferredSize(new java.awt.Dimension(202, 188));

        lblList.setText("List");

        checkboxEditList.setText("Enable Edit");

        tableListData.setModel(listDataModel);
        jScrollPane6.setViewportView(tableListData);

        textAreadListData.setSize(panelListData.getWidth(), tableListData.getHeight());
        textAreadListData.setColumns(10);
        textAreadListData.setRows(5);
        jScrollPane7.setViewportView(textAreadListData);

        javax.swing.GroupLayout panelListDataLayout = new javax.swing.GroupLayout(panelListData);
        panelListData.setLayout(panelListDataLayout);
        panelListDataLayout.setHorizontalGroup(
            panelListDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelListDataLayout.createSequentialGroup()
                .addComponent(lblList, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 158, Short.MAX_VALUE)
                .addComponent(checkboxEditList))
            .addGroup(panelListDataLayout.createSequentialGroup()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );
        panelListDataLayout.setVerticalGroup(
            panelListDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelListDataLayout.createSequentialGroup()
                .addGroup(panelListDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblList)
                    .addComponent(checkboxEditList))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelListDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)))
        );

        btnApply.setText("Apply");
        btnApply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApplyActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelListActionsLayout = new javax.swing.GroupLayout(panelListActions);
        panelListActions.setLayout(panelListActionsLayout);
        panelListActionsLayout.setHorizontalGroup(
            panelListActionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelListActionsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnApply, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelListActionsLayout.setVerticalGroup(
            panelListActionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelListActionsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelListActionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnApply)
                    .addComponent(btnDelete))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelListsLayout = new javax.swing.GroupLayout(panelLists);
        panelLists.setLayout(panelListsLayout);
        panelListsLayout.setHorizontalGroup(
            panelListsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelListsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelListsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelListActions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelListContact, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelListsLayout.createSequentialGroup()
                        .addGroup(panelListsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(panelCreateList, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelListData, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
                            .addComponent(panelListInfo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        panelListsLayout.setVerticalGroup(
            panelListsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelListsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelListContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelCreateList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelListInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelListData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelListActions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tabPane.addTab("Lists", panelLists);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(tabPane)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabPane)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lstContactNotesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstContactNotesMouseClicked
		//VERSIONS
		this.VersionListModel.removeAllElements();
		if (lstContactNotes.getSelectedValue() != null) {
			Note n = (Note) this.NoteListModel.
				getElementAt(this.lstContactNotes.getSelectedIndex());
			this.textAreaNote.setText(n.getInfo());
			for (Note note : n.versionByNote()) {
				this.VersionListModel.addElement(note);
			}
			lstNoteVersions.setModel(VersionListModel);
		}
		this.btnNoteEdit.setEnabled(true);
		this.btnNoteDelete.setEnabled(true);
		refreshUI();
    }//GEN-LAST:event_lstContactNotesMouseClicked

    private void btnNoteDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNoteDeleteActionPerformed
		controller.deleteNote((Note) this.NoteListModel.
			getElementAt(this.lstContactNotes.getSelectedIndex()));
		this.btnNoteDelete.setEnabled(false);
		this.btnNoteEdit.setEnabled(false);
    }//GEN-LAST:event_btnNoteDeleteActionPerformed

    private void btnNoteEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNoteEditActionPerformed
		controller.editNote(textAreaNote.getText(), (Note) this.NoteListModel.
							getElementAt(this.lstContactNotes.getSelectedIndex()));
		this.btnNoteEdit.setEnabled(false);
		this.btnDelete.setEnabled(false);
    }//GEN-LAST:event_btnNoteEditActionPerformed

    private void btnNoteCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNoteCreateActionPerformed
		Contact contact = (Contact) cbNoteContact.getSelectedItem();
		controller.createNote(textAreaNote.getText(), contact);
		this.textAreaNote.setText("");
		this.btnNoteEdit.setEnabled(false);
		this.btnDelete.setEnabled(false);
    }//GEN-LAST:event_btnNoteCreateActionPerformed

    private void btnCreateNewListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateNewListActionPerformed
		if (cbListContact.getSelectedItem() == null) {
			JOptionPane.
				showMessageDialog(panelLists, "No contact selected", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		panelListVersion.setVisible(false);
		panelListData.setVisible(true);
		panelListActions.setVisible(true);
		textAreadListData.setEnabled(true);
		textAreadListData.setText("");
		textAreadListData.setVisible(true);
		tableListData.setVisible(false);
		tableListData.setEnabled(false);
		checkboxEditList.setSelected(true);
		btnApply.setText("Create");
    }//GEN-LAST:event_btnCreateNewListActionPerformed

    private void btnApplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApplyActionPerformed
		switch (evt.getActionCommand()) {
			case "Create": {
				List newList;
				try {
					newList = controller.createList(cbListContact.
						getSelectedItem(), textAreadListData.getText());
				} catch (DataIntegrityViolationException ex) {
					JOptionPane.
						showMessageDialog(panelLists, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					break;
				} catch (ClassCastException ex) {
					JOptionPane.
						showMessageDialog(panelLists, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					break;
				}
				if (newList == null) {
					JOptionPane.
						showMessageDialog(panelLists, "Couldn't create new list.\nError saving the list.", "Error", JOptionPane.ERROR_MESSAGE);
					break;
				}
				contactListModel.addElement(newList);
				lstContactLists.setSelectedValue(newList, true);
				hideListEdit(newList);
				break;
			}
			case "Edit": {
				List list;
				try {
					list = controller.editList(lstContactLists.
						getSelectedValue(), textAreadListData.getText());
				} catch (DataIntegrityViolationException ex) {
					JOptionPane.
						showMessageDialog(panelLists, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					break;
				}
				if (list == null) {
					JOptionPane.
						showMessageDialog(panelLists, "Couldn't edit list.\nError saving the list.", "Error", JOptionPane.ERROR_MESSAGE);
					break;
				}
				contactListModel.removeElement(lstContactLists.
					getSelectedValue());
				contactListModel.addElement(list);
				lstContactLists.setSelectedValue(list, true);
				hideListEdit(list);
				break;
			}
			case "Apply": {
				String title;
				String message;
				int icon;
				Object data[][] = new Object[listDataModel.getRowCount()][listDataModel.
					getColumnCount()];
				for (int i = 0; i < listDataModel.getRowCount(); i++) {
					for (int j = 0; j < listDataModel.getColumnCount(); j++) {
						data[i][j] = listDataModel.getValueAt(i, j);
					}
				}
				if (controller.
					applyList(lstContactLists.getSelectedValue(), data) != null) {
					title = "Success";
					message = "Your changes were saved with success.";
					icon = JOptionPane.PLAIN_MESSAGE;
				} else {
					title = "Error";
					message = "Something was wrong when saving the info.";
					icon = JOptionPane.ERROR_MESSAGE;
				}
				JOptionPane.showMessageDialog(panelLists, message, title, icon);
				break;
			}
		}
    }//GEN-LAST:event_btnApplyActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
		List l = lstContactLists.getSelectedValue();
		if (lstContactLists.getSelectedValue() != null) {
			String title = "Delete List";
			String message = "Are you sure you want to delete that list ?";
			int reply = JOptionPane.
				showConfirmDialog(panelLists, message, title, JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION) {
				List list = controller.deleteList(l);
				contactListModel.removeElement(l);
				panelContactList.setVisible(true);
				panelCreateList.setVisible(true);
				panelListVersion.setVisible(false);
				panelListData.setVisible(false);
				panelListActions.setVisible(false);
			}
		} else {
			String title = "Error";
			String message = "There is no list selected ...";
			JOptionPane.
				showMessageDialog(panelLists, message, title, JOptionPane.ERROR_MESSAGE);
		}
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
		int panelWidth = panelListData.getWidth();
		int smallGap = 6;
		textAreadListData.setSize(((panelWidth - 6) / 2), textAreadListData.
								  getHeight());
		tableListData.setSize(((panelWidth - 6) / 2), tableListData.getHeight());
    }//GEN-LAST:event_formComponentResized

    private void cbNoteContactKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbNoteContactKeyReleased
    }//GEN-LAST:event_cbNoteContactKeyReleased

    private void cbNoteContactItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbNoteContactItemStateChanged
		this.NoteListModel.removeAllElements();
		if (cbNoteContact.getSelectedItem() != null) {
			Contact c = (Contact) cbNoteContact.getSelectedItem();
			for (Note note : controller.notesByContact(c)) {
				this.NoteListModel.addElement(note);
			}
			lstContactNotes.setModel(NoteListModel);
			this.textAreaNote.setText("");
			refreshUI();
		}
    }//GEN-LAST:event_cbNoteContactItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApply;
    private javax.swing.JButton btnCreateNewList;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnNoteCreate;
    private javax.swing.JButton btnNoteDelete;
    private javax.swing.JButton btnNoteEdit;
    private javax.swing.JComboBox<Contact> cbListContact;
    private javax.swing.JComboBox<Contact> cbNoteContact;
    private javax.swing.JCheckBox checkboxEditList;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JLabel lblContactLists;
    private javax.swing.JLabel lblList;
    private javax.swing.JLabel lblListContact;
    private javax.swing.JLabel lblListVersions;
    private javax.swing.JLabel lblNote;
    private javax.swing.JLabel lblNoteContact;
    private javax.swing.JLabel lblNoteContactNotes;
    private javax.swing.JLabel lblNoteVersion;
    private javax.swing.JList<List> lstContactLists;
    private javax.swing.JList<String> lstContactNotes;
    private javax.swing.JList<Integer> lstListVersions;
    private javax.swing.JList<String> lstNoteVersions;
    private javax.swing.JPanel panelContactList;
    private javax.swing.JPanel panelContactNoteList;
    private javax.swing.JPanel panelCreateList;
    private javax.swing.JPanel panelListActions;
    private javax.swing.JPanel panelListContact;
    private javax.swing.JPanel panelListData;
    private javax.swing.JPanel panelListInfo;
    private javax.swing.JPanel panelListVersion;
    private javax.swing.JPanel panelLists;
    private javax.swing.JPanel panelNoteActions;
    private javax.swing.JPanel panelNoteContact;
    private javax.swing.JPanel panelNoteData;
    private javax.swing.JPanel panelNoteInfo;
    private javax.swing.JPanel panelNoteVersionList;
    private javax.swing.JPanel panelNotes;
    private javax.swing.JTabbedPane tabPane;
    private javax.swing.JTable tableListData;
    private javax.swing.JTextArea textAreaNote;
    private javax.swing.JTextArea textAreadListData;
    // End of variables declaration//GEN-END:variables
}
