package oodj.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;
import oodj.Dashboard.main.Dashboard;

public class Login extends javax.swing.JFrame {

    public Login() {
        initComponents();
        this.getRootPane().setDefaultButton(btnLogin);

    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        labelLogin = new javax.swing.JLabel();
        labelEmail = new javax.swing.JLabel();
        labelPassword = new javax.swing.JLabel();
        tfEmail = new javax.swing.JTextField();
        checkPassword = new javax.swing.JCheckBox();
        btnLogin = new javax.swing.JButton();
        labelForgetPassword = new javax.swing.JLabel();
        labelSignup = new javax.swing.JLabel();
        pfPassword = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        exitLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/oodj/Dashboard/icon/yoyoLogo.png"))); // NOI18N
        jLabel1.setText("logo");

        jPanel1.setBackground(new java.awt.Color(229, 211, 179));

        labelLogin.setFont(new java.awt.Font("Sitka Text", 1, 36)); // NOI18N
        labelLogin.setForeground(new java.awt.Color(102, 66, 41));
        labelLogin.setText("LOG IN");

        labelEmail.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        labelEmail.setForeground(new java.awt.Color(102, 66, 41));
        labelEmail.setText("Email");

        labelPassword.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        labelPassword.setForeground(new java.awt.Color(102, 66, 41));
        labelPassword.setText("Password");

        tfEmail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfEmail.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        checkPassword.setBackground(new java.awt.Color(229, 211, 179));
        checkPassword.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        checkPassword.setForeground(new java.awt.Color(102, 66, 41));
        checkPassword.setText("Show Password");
        checkPassword.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        checkPassword.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        checkPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkPasswordMouseClicked(evt);
            }
        });

        btnLogin.setBackground(new java.awt.Color(102, 66, 41));
        btnLogin.setFont(new java.awt.Font("Sitka Text", 1, 22)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setText("LOG IN");
        btnLogin.setBorder(null);
        btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        labelForgetPassword.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labelForgetPassword.setForeground(new java.awt.Color(102, 66, 41));
        labelForgetPassword.setText("Forget Password?");
        labelForgetPassword.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelForgetPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelForgetPasswordMouseClicked(evt);
            }
        });

        labelSignup.setFont(new java.awt.Font("Sitka Text", 1, 14)); // NOI18N
        labelSignup.setForeground(new java.awt.Color(102, 66, 41));
        labelSignup.setText("Sign Up");
        labelSignup.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelSignup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelSignupMouseClicked(evt);
            }
        });

        pfPassword.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI Historic", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 66, 41));
        jLabel4.setText("OR");

        jLabel2.setFont(new java.awt.Font("Segoe UI Historic", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 66, 41));
        jLabel2.setText("______________________");

        jLabel3.setFont(new java.awt.Font("Segoe UI Historic", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 66, 41));
        jLabel3.setText("______________________");

        exitLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        exitLabel.setForeground(new java.awt.Color(102, 66, 41));
        exitLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        exitLabel.setText("X");
        exitLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitLabelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(186, 186, 186)
                        .addComponent(labelForgetPassword))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(checkPassword)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(pfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelPassword)
                                    .addComponent(labelEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(218, 218, 218)
                        .addComponent(labelSignup, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(123, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(labelLogin)
                        .addGap(170, 170, 170))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(exitLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(exitLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelLogin)
                .addGap(18, 18, 18)
                .addComponent(labelEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelPassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkPassword)
                .addGap(11, 11, 11)
                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelForgetPassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelSignup)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        authenticateUser();
    }//GEN-LAST:event_btnLoginActionPerformed
    
    private void authenticateUser(){
        String enteredEmail = tfEmail.getText();
        String enteredPassword = String.valueOf(pfPassword.getPassword());
        
        if (tfEmail.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter email");
        } else if (String.valueOf(pfPassword.getPassword()).equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter password");
        } else {
            String filePath = "staff.txt";
            
            StaffInformation staff = null;
            
            boolean isAdministrator = false;
            boolean isApprovedStaff = false;
            boolean isProcessingStaff = false;
            
            try {
                BufferedReader br = new BufferedReader(new FileReader(filePath));
                String line;
                while ((line = br.readLine()) != null){
                    String[] staffDetails = line.split(";");
                    if (staffDetails.length >= 8) {
                        String name = staffDetails[0];
                        String email = staffDetails[1];
                        String password = staffDetails[2];
                        String phoneNumber = staffDetails[3];
                        String securityQuestion = staffDetails[4];
                        String securityAnswer = staffDetails[5];
                        String role = staffDetails[6];
                        String status = staffDetails[7];
                    
                        if (email.equals(enteredEmail) && password.equals(enteredPassword)) {
                            staff = new StaffInformation(name, email, password, phoneNumber, securityQuestion, securityAnswer, role, status);
                            staff.setStaffName(name);
                            staff.setStaffEmail(email);
                            staff.setStaffPassword(password);
                            staff.setStaffPhoneNumber(phoneNumber);
                            staff.setSecurityQuestion(securityQuestion);
                            staff.setSecurityAnswer(securityAnswer);
                            staff.setRole(role);
                            staff.setStatus(status);
                            
                            name = staffDetails[0];
                            
                            if (role.equals("Administrator")) {
                                isAdministrator = true;
                            } else if (role.equals("Officer")) {
                                if (status.equals("Approved")) {
                                    isApprovedStaff = true;
                                } else if (status.equals("Processing")) {
                                    isProcessingStaff = true;
                                }
                            } else if (role.equals("SalesPerson")) {
                                if (status.equals("Approved")) {
                                    isApprovedStaff = true;
                                } else if (status.equals("Processing")) {
                                    isProcessingStaff = true;
                                }
                            }
                        }
                    }
                }
                br.close();
            } catch (IOException e) {
                System.out.println("Error reading staff file:" +e.getMessage());
            }
            
            if (isAdministrator || isApprovedStaff) {
                JOptionPane.showMessageDialog(this,"Login successful ! Welcome " + staff.getStaffName());

                if (staff != null) {
                    Dashboard dashboard = new Dashboard(staff.getStaffName(), staff.getStaffEmail(), staff.getRole());
                    dashboard.setVisible(true);
                    this.dispose();
                } else {
                    // Handle the case where staff is null
                    JOptionPane.showMessageDialog(this, "Error retrieving staff information");
                }
            } else if (isProcessingStaff) {
                JOptionPane.showMessageDialog(this, "Please wait for approval");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password");
            }
        }
    }
    
    private void checkPasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkPasswordMouseClicked
        if (checkPassword.isSelected()) {
            pfPassword.setEchoChar((char)0);
        } else {
            pfPassword.setEchoChar('*');
        }
    }//GEN-LAST:event_checkPasswordMouseClicked

    private void labelSignupMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelSignupMouseClicked
        SignUp signup = new SignUp();
        signup.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_labelSignupMouseClicked

    private void labelForgetPasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelForgetPasswordMouseClicked
        forgetPassword fp = new forgetPassword();
        fp.setVisible(true);
    }//GEN-LAST:event_labelForgetPasswordMouseClicked

    private void exitLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitLabelMouseClicked
        int result = JOptionPane.showConfirmDialog(
                this,
                "Do you want to exit the application?",
                "Exit Confirmation",
                JOptionPane.YES_NO_OPTION
        );

        if (result == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_exitLabelMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JCheckBox checkPassword;
    private javax.swing.JLabel exitLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelEmail;
    private javax.swing.JLabel labelForgetPassword;
    private javax.swing.JLabel labelLogin;
    private javax.swing.JLabel labelPassword;
    private javax.swing.JLabel labelSignup;
    private javax.swing.JPasswordField pfPassword;
    private javax.swing.JTextField tfEmail;
    // End of variables declaration//GEN-END:variables
}
