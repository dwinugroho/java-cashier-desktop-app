package cashier.views;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import cashier.controllers.LevelSource;
import cashier.controllers.UserSource;
import cashier.helpers.Popup;
import cashier.models.Level;
import cashier.models.User;

public class UserManager extends javax.swing.JFrame {
	private DefaultTableModel userTableModel;
	private Admin parentWindow;
	private List<User> allUsers;
	private	User currentUser;

	// This is a bit hack-ish but I don't care lmao, I don't enjoy writing java
	private String currentLevel = "";

	/**
	 * Creates new form UserManagement
	 */
	public UserManager() {
		initComponents();
	}
	public UserManager(Admin parent, User user) {
		this.setLocationRelativeTo(null);
		initComponents();
		initTableModel();
		populateData();
		attachOnSelectionEvent();
		parentWindow = parent;
		currentUser = user;
	}

	/**
	 * Set the desired table model
	 */
	private void initTableModel() {
		String[] columns = new String[]{
			"ID", "Nama User", "Username", "Password", "Role"
		};
		userTableModel = new DefaultTableModel(columns, 0) {
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
		};
		foodTable.setModel(userTableModel);
		foodTable.getTableHeader().setReorderingAllowed(false); // prevent from table re-ordering
	}

	private void attachOnSelectionEvent() {
		foodTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent evt) {
				int selectedRow = foodTable.getSelectedRow();

				// we need to guard this here because when the row is cleared,
				// nothing gets selected
				if (selectedRow == -1) return;

				// set details for text fields
//				idField.setText(foodTable.getValueAt(selectedRow, 0).toString());
//				userField.setText(foodTable.getValueAt(selectedRow, 1).toString());
//				usernameField.setText(foodTable.getValueAt(selectedRow, 2).toString());
//				passwordField.setText(foodTable.getValueAt(selectedRow, 3).toString());
//				levelField.setText(foodTable.getValueAt(selectedRow, 4).toString());

				// change the current user
//				currentUser.setUserID(Integer.parseInt(foodTable.getValueAt(selectedRow, 0).toString()));
                                currentUser.setUserID((int) foodTable.getValueAt(selectedRow, 0));
				currentUser.setName(foodTable.getValueAt(selectedRow, 1).toString());
				currentUser.setUsername(foodTable.getValueAt(selectedRow, 2).toString());
				currentUser.setPassword(foodTable.getValueAt(selectedRow, 3).toString());
				currentLevel = foodTable.getValueAt(selectedRow, 4).toString();
			}
		});
	}

	public void populateData() {
		// always reset the table first before filling it
		foodTable.clearSelection();
		userTableModel.setRowCount(0);
		foodTable.revalidate();

		try {
			allUsers = UserSource.findAll();
			Level level = new Level();

			for (User user : allUsers) {
				level.setLevelID(user.getLevelID());
				Level foundLevel = new LevelSource(level).find();

				userTableModel.addRow(new Object[] {
					user.getUserID(),
					user.getName(),
					user.getUsername(),
					user.getPassword(),
					foundLevel.getLevelName(),
				});
			}
		} catch (SQLException ex) {
			// this conflicts with our Level class so we use the full path
			Logger.getLogger(UserManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
			JOptionPane.showMessageDialog(null, "Gagal menambahkan pengguna!");
			ex.printStackTrace();
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

        passwordField1 = new javax.swing.JTextField();
        windowTitle = new javax.swing.JLabel();
        deleteButton = new javax.swing.JButton();
        scrollPane = new javax.swing.JScrollPane();
        foodTable = new javax.swing.JTable();
        backButton = new javax.swing.JButton();
        logoutButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        addButton = new javax.swing.JButton();

        passwordField1.setEditable(false);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        windowTitle.setFont(new java.awt.Font("Inter", 1, 18)); // NOI18N
        windowTitle.setText("Manajemen User");

        deleteButton.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
        deleteButton.setText("Hapus User");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        scrollPane.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N

        foodTable.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        foodTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nama User", "Username", "Password", "Role"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollPane.setViewportView(foodTable);

        backButton.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        backButton.setText("Logout");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        logoutButton.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        logoutButton.setText("Kembali");
        logoutButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });

        editButton.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
        editButton.setText("Edit User");
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        addButton.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
        addButton.setText("Tambah User");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 516, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(logoutButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(windowTitle)
                        .addGap(81, 81, 81)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(deleteButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(editButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addButton, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                    .addComponent(backButton, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(14, 14, 14))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(windowTitle)
                    .addComponent(logoutButton)
                    .addComponent(backButton))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(318, 318, 318)
                        .addComponent(editButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(addButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(deleteButton))
                    .addComponent(scrollPane))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

	private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
		User user = new User();
		int selectedRow = foodTable.getSelectedRow();
		user.setUserID(Integer.parseInt(foodTable.getValueAt(selectedRow, 0).toString()));

		// initialise using the above user instance
		UserSource userData = new UserSource(user);

		try {
			userData.delete();
			JOptionPane.showMessageDialog(this, "Data berhasil dihapus!");
		} catch (SQLException ex) {
			// use full path, if not, it will conflict with the other one
			Logger.getLogger(UserManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
			JOptionPane.showMessageDialog(this, "Data gagal dihapus!");
			ex.printStackTrace();
		}

		// we need to refresh the table; hence this function call
		populateData();
	}//GEN-LAST:event_deleteButtonActionPerformed

	private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
		Popup.<Login>open(new Login(), "Login Aplikasi Kasir");
		this.dispose();
		parentWindow.dispose();
	}//GEN-LAST:event_backButtonActionPerformed

	private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
    Popup.<Admin>open(new Admin(currentUser), "Halaman Admin");
		this.dispose();
	}//GEN-LAST:event_logoutButtonActionPerformed

	private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
		Popup.<UserPopup>open(new UserPopup(this), "Tambah User");
	}//GEN-LAST:event_addButtonActionPerformed

	private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
		if (foodTable.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(this, "Tidak ada user yang dipilih!");
			return;
		}

		Popup.<UserPopup>open(new UserPopup(this, currentUser, currentLevel), "Edit User");
	}//GEN-LAST:event_editButtonActionPerformed

	/**
	 * @param args the command line arguments
	*/
	public static void main(String args[]) {
		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new UserManager().setVisible(true);
			}
		});
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton backButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton editButton;
    private javax.swing.JTable foodTable;
    private javax.swing.JButton logoutButton;
    private javax.swing.JTextField passwordField1;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JLabel windowTitle;
    // End of variables declaration//GEN-END:variables
}
