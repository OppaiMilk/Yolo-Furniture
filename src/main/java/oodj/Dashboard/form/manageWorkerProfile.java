package oodj.Dashboard.form;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import oodj.Dashboard.swing.TableCustom;

public class manageWorkerProfile extends javax.swing.JPanel {
    private DefaultTableModel tblmodel;
    private ArrayList<Object[]> filteredData;

    public manageWorkerProfile() {
        initComponents();
        setOpaque(false);
        TableCustom.apply(jScrollPane1, TableCustom.TableType.DEFAULT);

        tblmodel = (DefaultTableModel) tableDetails.getModel();
        filteredData = new ArrayList<>();
        readWorkerDetails("");
    }

    private void readWorkerDetails(String searchQuery) {
        tblmodel.setRowCount(0);
        filteredData.clear();
        try (BufferedReader br = new BufferedReader(new FileReader("staff.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");
                if (matchesSearchQuery(data, searchQuery)) {
                    tblmodel.addRow(data);
                    filteredData.add(data);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file");
        }
    }

    private boolean matchesSearchQuery(String[] data, String searchQuery) {
        // Check if the customer name or room ID matches the search query
        if (data.length >= 7) {
            String staffName = data[0];
            String staffRole = data[6];
            return staffName.toLowerCase().contains(searchQuery.toLowerCase())
                    || staffRole.toLowerCase().contains(searchQuery.toLowerCase());
        } else {
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new oodj.Dashboard.swing.RoundPanel();
        jLabel2 = new javax.swing.JLabel();
        tfSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        tfName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tfEmail = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        pfPassword = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();
        tfPhone = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        tfSecQuestion = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        tfSecAnswer = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        tfRole = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        comboStatus = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableDetails = new javax.swing.JTable();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();

        roundPanel1.setBackground(new java.awt.Color(245, 240, 228));

        jLabel2.setFont(new java.awt.Font("Segoe UI Historic", 1, 24)); // NOI18N
        jLabel2.setText("Enter Worker Details");

        tfSearch.setFont(new java.awt.Font("Segoe UI Historic", 1, 24)); // NOI18N

        btnSearch.setBackground(new java.awt.Color(102, 66, 41));
        btnSearch.setFont(new java.awt.Font("Segoe UI Historic", 1, 24)); // NOI18N
        btnSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI Historic", 1, 18)); // NOI18N
        jLabel3.setText("Name");

        tfName.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI Historic", 1, 18)); // NOI18N
        jLabel4.setText("Email");

        tfEmail.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI Historic", 1, 18)); // NOI18N
        jLabel5.setText("Password");

        pfPassword.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI Historic", 1, 18)); // NOI18N
        jLabel6.setText("Phone Number");

        tfPhone.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Segoe UI Historic", 1, 18)); // NOI18N
        jLabel7.setText("Security Question");

        tfSecQuestion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Segoe UI Historic", 1, 18)); // NOI18N
        jLabel8.setText("Security Answer");

        tfSecAnswer.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Segoe UI Historic", 1, 18)); // NOI18N
        jLabel9.setText("Role");

        tfRole.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Segoe UI Historic", 1, 18)); // NOI18N
        jLabel10.setText("Status");

        comboStatus.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        comboStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Approved", "Processing" }));
        comboStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboStatusActionPerformed(evt);
            }
        });

        tableDetails.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N
        tableDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Email", "Password", "Phone No.", "Security Question", "Security Ans", "Role", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableDetails.setRowHeight(40);
        tableDetails.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDetailsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableDetails);

        btnEdit.setBackground(new java.awt.Color(102, 66, 41));
        btnEdit.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        btnEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(102, 66, 41));
        btnDelete.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(btnSearch))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(comboStatus, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(tfName, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(tfEmail, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(pfPassword, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(tfPhone, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(tfSecAnswer, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(tfSecQuestion, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(tfRole, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addGap(136, 136, 136)
                                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(100, 100, 100)
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 734, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(21, 21, 21))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(tfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch))
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEdit)
                            .addComponent(btnDelete)))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfSecQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfSecAnswer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9)
                        .addGap(3, 3, 3)
                        .addComponent(tfRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        String searchText = tfSearch.getText();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>((DefaultTableModel) tableDetails.getModel());
        tableDetails.setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText)); // Case-insensitive search
    }//GEN-LAST:event_btnSearchActionPerformed

    private void comboStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboStatusActionPerformed
        String selectedStatus = (String) comboStatus.getSelectedItem();
        // Check if the selected status is "Approved"
        if ("Approved".equals(selectedStatus)) {
            // Disable the combo box
            comboStatus.setEnabled(false);
        } else {
            // Enable the combo box for other statuses
            comboStatus.setEnabled(true);
        }
    }//GEN-LAST:event_comboStatusActionPerformed

    private void tableDetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDetailsMouseClicked
        //When row is selected, data will set to textfield and wait for edit
        int selectedRow = tableDetails.getSelectedRow();
        if (selectedRow != -1) { // Check if a row is selected
            Object[] rowData = filteredData.get(selectedRow);
            String tblName = getValueAsString(rowData, 0);
            String tblEmail = getValueAsString(rowData, 1);
            String tblPassword = getValueAsString(rowData, 2);
            String tblPhone = getValueAsString(rowData, 3);
            String tblQuestion = getValueAsString(rowData, 4);
            String tblAnswer = getValueAsString(rowData, 5);
            String tblRole = getValueAsString(rowData, 6);
            String tblStatus = getValueAsString(rowData, 7);

            if (tblName != null && tblEmail != null && tblPassword != null && tblPhone != null
                && tblQuestion != null && tblAnswer != null && tblRole != null && tblStatus != null) {
                tfName.setText(tblName);
                tfEmail.setText(tblEmail);
                pfPassword.setText(tblPassword);
                tfPhone.setText(tblPhone);
                tfSecQuestion.setText(tblQuestion);
                tfSecAnswer.setText(tblAnswer);
                tfRole.setText(tblRole);
                comboStatus.setSelectedItem(tblStatus);
                
            } else {
                JOptionPane.showMessageDialog(this, "Please select a valid data");
            }
        }
    }//GEN-LAST:event_tableDetailsMouseClicked

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        DefaultTableModel tblModel = (DefaultTableModel) tableDetails.getModel();

        if (tableDetails.getSelectedRowCount() == 1) {
            int selectedRow = tableDetails.getSelectedRow();
            Object[] rowData = filteredData.get(selectedRow);

            // Get the values from the text fields
            String tblName = tfName.getText();
            String tblEmail = tfEmail.getText();
            String tblPassword = String.valueOf(pfPassword.getPassword());
            String tblPhone = tfPhone.getText();
            String tblQuestion = tfSecQuestion.getText();
            String tblAnswer = tfSecAnswer.getText();
            String tblRole = tfRole.getText();
            String tblStatus = (String) comboStatus.getSelectedItem();

            // Update the values in the table model
            tblModel.setValueAt(tblName, selectedRow, 0);
            tblModel.setValueAt(tblEmail, selectedRow, 1);
            tblModel.setValueAt(tblPassword, selectedRow, 2);
            tblModel.setValueAt(tblPhone, selectedRow, 3);
            tblModel.setValueAt(tblQuestion, selectedRow, 4);
            tblModel.setValueAt(tblAnswer, selectedRow, 5);
            tblModel.setValueAt(tblRole, selectedRow, 6);
            tblModel.setValueAt(tblStatus, selectedRow, 7);

            // Update the values in the filtered data list
            rowData[0] = tblName;
            rowData[1] = tblEmail;
            rowData[2] = tblPassword;
            rowData[3] = tblPhone;
            rowData[4] = tblQuestion;
            rowData[5] = tblAnswer; // Convert LocalDate to String
            rowData[6] = tblRole;
            rowData[7] = tblStatus;

            // Update the booking.txt file
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("staff.txt"))) {
                for (Object[] data : filteredData) {
                    String line = String.join(";", Arrays.copyOf(data, data.length, String[].class));
                    bw.write(line);
                    bw.newLine();
                }
                JOptionPane.showMessageDialog(this, "Update successful");
                tfName.setText("");
                tfEmail.setText("");
                pfPassword.setText("");
                tfPhone.setText("");
                tfSecQuestion.setText("");
                tfSecAnswer.setText("");
                tfRole.setText("");
                comboStatus.setSelectedIndex(-1);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error updating data");
            }

            // Refresh the tableDetails
            tblModel.setRowCount(0); // Clear the table model
            for (Object[] data : filteredData) {
                tblModel.addRow(data); // Add the updated data
            }

        } else {
            if (tableDetails.getRowCount() == 0) {
                // If table is empty
                JOptionPane.showMessageDialog(this, "Table is empty");
            } else {
                // If row is not selected or multiple rows are selected
                JOptionPane.showMessageDialog(this, "Please select a single row for update");
            }
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        DefaultTableModel tblModel = (DefaultTableModel) tableDetails.getModel();
        int selectedRow = tableDetails.getSelectedRow();
        if (selectedRow != -1) { // Check if a row is selected
            int confirmationResult = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete the staff?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if (confirmationResult == JOptionPane.YES_OPTION) {
                int modelRow = tableDetails.convertRowIndexToModel(selectedRow);

                // Remove row from table model
                tblModel.removeRow(modelRow);

                // Remove row from filtered data list
                filteredData.remove(modelRow);

                // Update the booking.txt file
                try (BufferedWriter bw = new BufferedWriter(new FileWriter("staff.txt"))) {
                    for (Object[] data : filteredData) {
                        String line = String.join(";", Arrays.copyOf(data, data.length, String[].class));
                        bw.write(line);
                        bw.newLine();
                    }
                    JOptionPane.showMessageDialog(this, "Delete successful");
                    // Clear the text fields
                    tfName.setText("");
                    tfEmail.setText("");
                    pfPassword.setText("");
                    tfPhone.setText("");
                    tfSecQuestion.setText("");
                    tfSecAnswer.setText("");
                    tfRole.setText("");
                    comboStatus.setSelectedIndex(-1);
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(this, "Error deleting data");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Delete canceled");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to delete");
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private String getValueAsString(Object[] data, int column) {
        return (column >= 0 && column < data.length) ? data[column].toString() : null;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox<String> comboStatus;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPasswordField pfPassword;
    private oodj.Dashboard.swing.RoundPanel roundPanel1;
    private javax.swing.JTable tableDetails;
    private javax.swing.JTextField tfEmail;
    private javax.swing.JTextField tfName;
    private javax.swing.JTextField tfPhone;
    private javax.swing.JTextField tfRole;
    private javax.swing.JTextField tfSearch;
    private javax.swing.JTextField tfSecAnswer;
    private javax.swing.JTextField tfSecQuestion;
    // End of variables declaration//GEN-END:variables
}
