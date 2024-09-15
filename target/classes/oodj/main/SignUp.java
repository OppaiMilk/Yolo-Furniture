package oodj.main;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.JOptionPane;

public class SignUp extends javax.swing.JFrame {

    public SignUp() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        tfStaffName = new javax.swing.JTextField();
        labelEmail = new javax.swing.JLabel();
        labelPhone = new javax.swing.JLabel();
        tfStaffEmail = new javax.swing.JTextField();
        tfStaffPhone = new javax.swing.JTextField();
        labelPassword = new javax.swing.JLabel();
        tfSecAnswer = new javax.swing.JTextField();
        labelSecQuestion = new javax.swing.JLabel();
        comboSecQuestion = new javax.swing.JComboBox<>();
        labelSecAnswer = new javax.swing.JLabel();
        labelRole = new javax.swing.JLabel();
        labelName = new javax.swing.JLabel();
        comboRole = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        tfStaffPassword = new javax.swing.JTextField();
        btnSignUp = new oodj.Dashboard.swing.CustomButton();
        btnBack = new oodj.Dashboard.swing.CustomButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/oodj/Dashboard/icon/consult_feat-NEW_042523.gif"))); // NOI18N

        jPanel1.setBackground(new java.awt.Color(229, 211, 179));

        tfStaffName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        labelEmail.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        labelEmail.setText("Email");

        labelPhone.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        labelPhone.setText("Phone Number");

        tfStaffEmail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        tfStaffPhone.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        labelPassword.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        labelPassword.setText("Password");

        tfSecAnswer.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfSecAnswer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfSecAnswerActionPerformed(evt);
            }
        });

        labelSecQuestion.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        labelSecQuestion.setText("Security Question");

        comboSecQuestion.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        comboSecQuestion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "What is your favourite colour?", "What is your nick name?", "What is your pet's name?", " " }));

        labelSecAnswer.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        labelSecAnswer.setText("Security Answer");

        labelRole.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        labelRole.setText("Register role");

        labelName.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        labelName.setText("Name");

        comboRole.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        comboRole.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Officer", "SalesPerson" }));

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 66, 41));
        jLabel2.setText("Create an account");

        tfStaffPassword.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N

        btnSignUp.setBorder(null);
        btnSignUp.setForeground(new java.awt.Color(255, 255, 255));
        btnSignUp.setText("Sign Up");
        btnSignUp.setBorderColor(new java.awt.Color(102, 66, 41));
        btnSignUp.setColor(new java.awt.Color(102, 66, 41));
        btnSignUp.setColorClick(new java.awt.Color(204, 153, 0));
        btnSignUp.setColorOver(new java.awt.Color(153, 102, 0));
        btnSignUp.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        btnSignUp.setRadius(10);
        btnSignUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSignUpActionPerformed(evt);
            }
        });

        btnBack.setBorder(null);
        btnBack.setForeground(new java.awt.Color(255, 255, 255));
        btnBack.setText("Back");
        btnBack.setBorderColor(new java.awt.Color(102, 66, 41));
        btnBack.setColor(new java.awt.Color(102, 66, 41));
        btnBack.setColorClick(new java.awt.Color(204, 153, 0));
        btnBack.setColorOver(new java.awt.Color(153, 102, 0));
        btnBack.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        btnBack.setRadius(10);
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(186, 186, 186))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfStaffPhone, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                            .addComponent(labelPassword)
                            .addComponent(labelName, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelPhone)
                            .addComponent(tfStaffEmail)
                            .addComponent(tfStaffName)
                            .addComponent(tfStaffPassword)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnSignUp, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelRole)
                            .addComponent(labelSecQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboSecQuestion, 0, 252, Short.MAX_VALUE)
                            .addComponent(labelSecAnswer)
                            .addComponent(tfSecAnswer)
                            .addComponent(comboRole, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 80, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel2)
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelName)
                    .addComponent(labelSecQuestion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfStaffName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboSecQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelSecAnswer)
                    .addComponent(labelEmail))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfStaffEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfSecAnswer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelPassword)
                    .addComponent(labelRole))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfStaffPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addComponent(labelPhone)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfStaffPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSignUp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(51, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1)
            .addGroup(layout.createSequentialGroup()
                .addGap(394, 394, 394)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tfSecAnswerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfSecAnswerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfSecAnswerActionPerformed

    private void btnSignUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSignUpActionPerformed
        if (tfStaffName.getText().equals("") || tfStaffEmail.getText().equals("")
                || tfStaffPassword.getText().equals("") || tfStaffPhone.getText().equals("") || comboSecQuestion.getSelectedItem() == null
                || tfSecAnswer.getText().equals("") || comboRole.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Please enter all data");
        } else {
            String sName = tfStaffName.getText();
            String sEmail = tfStaffEmail.getText();
            String sPassword = tfStaffPassword.getText();
            String sPhone = tfStaffPhone.getText();
            String sSecurityQues = (String) comboSecQuestion.getSelectedItem();
            String sAnswer = tfSecAnswer.getText();
            String sRole = (String) comboRole.getSelectedItem();
            String status = "Processing";

            String staffDetails = sName + ";" + sEmail + ";" + sPassword + ";" + sPhone + ";" + sSecurityQues + ";" + sAnswer + ";" + sRole + ";" + status + "\n";

            String filePath = "staff.txt";

            try (FileWriter fw = new FileWriter(filePath, true)) {
                fw.write(staffDetails);

                // Copy the profile photo
                String sourcePath = "Profile Photo/user.png";
                String destinationPath = "Profile Photo/" + sName + ".png";

                Path source = Paths.get(sourcePath);
                Path destination = Paths.get(destinationPath);

                Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);

                String successMessage = "Account created successfully for " + sName;
                JOptionPane.showMessageDialog(this, successMessage);

                Login login = new Login();
                login.setVisible(true);
                this.dispose();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnSignUpActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        Login login = new Login();
        login.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private oodj.Dashboard.swing.CustomButton btnBack;
    private oodj.Dashboard.swing.CustomButton btnSignUp;
    private javax.swing.JComboBox<String> comboRole;
    private javax.swing.JComboBox<String> comboSecQuestion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelEmail;
    private javax.swing.JLabel labelName;
    private javax.swing.JLabel labelPassword;
    private javax.swing.JLabel labelPhone;
    private javax.swing.JLabel labelRole;
    private javax.swing.JLabel labelSecAnswer;
    private javax.swing.JLabel labelSecQuestion;
    private javax.swing.JTextField tfSecAnswer;
    private javax.swing.JTextField tfStaffEmail;
    private javax.swing.JTextField tfStaffName;
    private javax.swing.JTextField tfStaffPassword;
    private javax.swing.JTextField tfStaffPhone;
    // End of variables declaration//GEN-END:variables
}
