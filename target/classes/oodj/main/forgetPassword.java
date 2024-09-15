package oodj.main;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

public class forgetPassword extends javax.swing.JFrame {

    private String[] getEmailFromStaff(String email) {
        try (BufferedReader br = new BufferedReader(new FileReader("staff.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] staffDetails = line.split(";");
                if (staffDetails.length >= 3 && staffDetails[1].equals(email)) {
                    return staffDetails; // Return the name associated with the email
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; // Email not found
    }

    public forgetPassword() {
        initComponents();
        this.getContentPane().setBackground(Color.WHITE);
        // Lock the password fields and save button
        pfNewPass.setEnabled(false);
        pfConfirmPass.setEnabled(false);
        btnSave.setEnabled(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        labelEmail = new javax.swing.JLabel();
        btnSearchUserName = new javax.swing.JButton();
        tfEmail = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        labelConfirmPass = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        pfConfirmPass = new javax.swing.JPasswordField();
        pfNewPass = new javax.swing.JPasswordField();
        labelNewPass = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        tfSecurityQues = new javax.swing.JTextField();
        labelSecurityQues = new javax.swing.JLabel();
        labelAns = new javax.swing.JLabel();
        tfAnswer = new javax.swing.JTextField();
        btnCheck = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(214, 190, 150));

        jLabel5.setBackground(new java.awt.Color(214, 190, 150));
        jLabel5.setForeground(new java.awt.Color(214, 190, 150));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/oodj/Dashboard/icon/forgotPassword.png"))); // NOI18N
        jLabel5.setText("jLabel5");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(227, 211, 185));

        labelEmail.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        labelEmail.setText("Email:");

        btnSearchUserName.setBackground(new java.awt.Color(102, 66, 41));
        btnSearchUserName.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        btnSearchUserName.setForeground(new java.awt.Color(255, 255, 255));
        btnSearchUserName.setText("Search");
        btnSearchUserName.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSearchUserName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchUserNameActionPerformed(evt);
            }
        });

        tfEmail.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tfEmail.setPreferredSize(new java.awt.Dimension(60, 28));

        jLabel2.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 66, 41));
        jLabel2.setText("Step 1: Search your email");

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 66, 41));
        jLabel1.setText("FORGET PASSWORD");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(labelEmail)
                        .addGap(24, 24, 24)
                        .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(btnSearchUserName)))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel1)
                .addGap(17, 17, 17)
                .addComponent(jLabel2)
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelEmail)
                    .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearchUserName)))
        );

        jPanel4.setBackground(new java.awt.Color(227, 211, 185));

        labelConfirmPass.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        labelConfirmPass.setText("Confirm Password:");

        btnSave.setBackground(new java.awt.Color(102, 66, 41));
        btnSave.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setText("Save");
        btnSave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnBack.setBackground(new java.awt.Color(102, 66, 41));
        btnBack.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        btnBack.setForeground(new java.awt.Color(255, 255, 255));
        btnBack.setText("Back");
        btnBack.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        pfConfirmPass.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        pfNewPass.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        labelNewPass.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        labelNewPass.setText("New Password: ");

        jLabel4.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 66, 41));
        jLabel4.setText("Step 3: Enter new password and save");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelConfirmPass)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(labelNewPass)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(pfNewPass)
                                    .addComponent(pfConfirmPass)
                                    .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnBack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(122, 122, 122))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pfNewPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelNewPass))
                .addGap(23, 23, 23)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pfConfirmPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelConfirmPass))
                .addGap(18, 18, 18)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBack)
                .addGap(12, 12, 12))
        );

        jPanel3.setBackground(new java.awt.Color(227, 211, 185));

        tfSecurityQues.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        labelSecurityQues.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        labelSecurityQues.setText("Security Questions:");

        labelAns.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        labelAns.setText("Answer:");

        tfAnswer.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        btnCheck.setBackground(new java.awt.Color(102, 66, 41));
        btnCheck.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        btnCheck.setForeground(new java.awt.Color(255, 255, 255));
        btnCheck.setText("Check");
        btnCheck.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 66, 41));
        jLabel3.setText("Step 2: Enter answer for security question");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelSecurityQues)
                            .addComponent(labelAns))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(tfAnswer, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                            .addComponent(tfSecurityQues, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(18, 18, 18)
                        .addComponent(btnCheck))
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfSecurityQues, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSecurityQues))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelAns)
                    .addComponent(tfAnswer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCheck))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchUserNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchUserNameActionPerformed
        String email = tfEmail.getText();

        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your email.");
            return;
        }

        String[] staffDetails = getEmailFromStaff(email);
        if (staffDetails != null) {
            // User found
            JOptionPane.showMessageDialog(this, "User found: " + staffDetails[1]);
            // Show the security question
            tfSecurityQues.setText(staffDetails[4]);
            tfAnswer.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(this, "User not found. Please enter a valid email.");
        }
    }//GEN-LAST:event_btnSearchUserNameActionPerformed

    private void btnCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckActionPerformed
        String email = tfEmail.getText();
        String enteredAnswer = new String(tfAnswer.getText());

        // Get the staff details
        String[] staffDetails = getEmailFromStaff(email);
        if (staffDetails != null) {
            // Verify the entered answer
            if (staffDetails.length >= 5 && staffDetails[5].equals(enteredAnswer)) {
                JOptionPane.showMessageDialog(this, "Correct Answer");
                // Answers match, enable password fields and save button
                pfNewPass.setEnabled(true);
                pfConfirmPass.setEnabled(true);
                btnSave.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(this, "Incorrect security answer. Please try again.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "User not found. Please enter a valid email.");
        }
    }//GEN-LAST:event_btnCheckActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // Retrieve entered password and confirm password
        String newPassword = new String(pfNewPass.getPassword());
        String confirmPassword = new String(pfConfirmPass.getPassword());

        // Check if passwords match
        if (!newPassword.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match. Please re-enter.");
            return;
        }

        // Get the email
        String email = tfEmail.getText();
        String enteredAnswer = new String(tfAnswer.getText());

        // Update password in staff.txt
        String[] staffDetails = getEmailFromStaff(email);
        if (staffDetails != null) {
            // Verify the entered answer
            if (staffDetails.length >= 8 && staffDetails[5].equals(enteredAnswer)) {
                // Update password in staffDetails
                staffDetails[2] = newPassword; // Update the password

                try {
                    BufferedReader br = new BufferedReader(new FileReader("staff.txt"));
                    StringBuilder sb = new StringBuilder();

                    String line;
                    while ((line = br.readLine()) != null) {
                        String[] lineData = line.split(";");
                        if (lineData.length >= 3 && lineData[1].equals(email)) {
                            lineData[2] = newPassword; // Update the password in the line
                        }
                        sb.append(String.join(";", lineData)).append("\n");
                    }

                    br.close();

                    // Write updated contents back to staff.txt
                    FileWriter fw = new FileWriter("staff.txt");
                    fw.write(sb.toString());
                    fw.close();

                    JOptionPane.showMessageDialog(this, "Password updated successfully. You may log in again now.");

                    // Open Login.java window
                    Login login = new Login();
                    login.setVisible(true);

                    // Close forgetPassword.java window
                    this.dispose();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Incorrect security answer. Please try again.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "User not found. Please enter a valid email.");
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        Login lg = new Login();
        lg.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnBackActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnCheck;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSearchUserName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel labelAns;
    private javax.swing.JLabel labelConfirmPass;
    private javax.swing.JLabel labelEmail;
    private javax.swing.JLabel labelNewPass;
    private javax.swing.JLabel labelSecurityQues;
    private javax.swing.JPasswordField pfConfirmPass;
    private javax.swing.JPasswordField pfNewPass;
    private javax.swing.JTextField tfAnswer;
    private javax.swing.JTextField tfEmail;
    private javax.swing.JTextField tfSecurityQues;
    // End of variables declaration//GEN-END:variables
}
