package oodj.Dashboard.form;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import oodj.Dashboard.component.MenuBar.ImageLoader;

public class manageProfile extends javax.swing.JPanel {
    private String loggedInEmail;
    private String[] userDetailsArray;
    private String loggedInStaffName;
    private BufferedImage profileImage;

    public manageProfile(String email, String staffName) {
        initComponents();
        setOpaque(false);
        
        this.loggedInEmail= email;
        this.loggedInStaffName = staffName;
        populateUserDetails();
        this.loggedInStaffName = tfStaffName.getText(); 
        loadProfilePhoto(loggedInStaffName);
        
    }
    
    // Add a new method to load profile photo based on staff name
    private void loadProfilePhoto(String staffName) {
        String profilePhotoPath = "Profile Photo/" + staffName + ".png";

        try {
            File profilePhotoFile = new File(profilePhotoPath);

            // Check if the file exists before reading it
            if (profilePhotoFile.exists()) {
                BufferedImage profileImage = ImageIO.read(profilePhotoFile);
                ImageIcon newProfilePhotoIcon = new ImageIcon(profileImage);
                imageAvatar.setIcon(newProfilePhotoIcon);
            } else {
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    private void populateUserDetails() {
        String filePath = "staff.txt"; // Update this with the actual file path
        String userDetails = "";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] staffDetails = line.split(";");
                String email = staffDetails[1];

                if (email.equals(loggedInEmail)) {
                    userDetails = line;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!userDetails.isEmpty()) {
            userDetailsArray = userDetails.split(";");
            tfStaffName.setText(userDetailsArray[0]);
            tfStaffEmail.setText(userDetailsArray[1]);
            tfStaffPassword.setText(userDetailsArray[2]);
            tfStaffPhone.setText(userDetailsArray[3]);
            comboSecQuestion.setSelectedItem(userDetailsArray[4]);
            tfSecAnswer.setText(userDetailsArray[5]);
            comboRole.setSelectedItem(userDetailsArray[6]);
        }
       
    }
    
    private boolean isDetailsChanged() {
        String currentName = tfStaffName.getText();
        String currentEmail = tfStaffEmail.getText();
        String currentPassword = tfStaffPassword.getText();
        String currentPhone = tfStaffPhone.getText();
        String currentSecurityQuestion = (String) comboSecQuestion.getSelectedItem();
        String currentAnswer = tfSecAnswer.getText();
        String currentRole = (String) comboRole.getSelectedItem();

        if (!currentName.equals(userDetailsArray[0]) || !currentEmail.equals(userDetailsArray[1])
                || !currentPassword.equals(userDetailsArray[2]) || !currentPhone.equals(userDetailsArray[3]) 
                || !currentSecurityQuestion.equals(userDetailsArray[4]) || !currentAnswer.equals(userDetailsArray[5])
                || !currentRole.equals(userDetailsArray[6])) {
            return true;
        } else {
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new oodj.Dashboard.swing.RoundPanel();
        jLabel2 = new javax.swing.JLabel();
        tfStaffName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tfStaffEmail = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tfStaffPassword = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tfStaffPhone = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        comboSecQuestion = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        tfSecAnswer = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        comboRole = new javax.swing.JComboBox<>();
        btnSave = new javax.swing.JButton();
        jPanel1 = new oodj.Dashboard.swing.RoundPanel();
        jLabel1 = new javax.swing.JLabel();
        imageAvatar = new oodj.Dashboard.swing.ImageAvatar();
        btnChangeAvatar = new oodj.Dashboard.swing.CustomButton();

        roundPanel1.setBackground(new java.awt.Color(245, 240, 228));

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        jLabel2.setText("Name");

        tfStaffName.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        jLabel3.setText("Email");

        tfStaffEmail.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        jLabel4.setText("Password");

        tfStaffPassword.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        jLabel5.setText("Phone Number");

        tfStaffPhone.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        jLabel6.setText("Security Question");

        comboSecQuestion.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        comboSecQuestion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "What is your favourite colour?", "What is your nick name?", "What is your pet's name?" }));

        jLabel7.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        jLabel7.setText("Security Answer");

        tfSecAnswer.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        jLabel8.setText("Registered Role");

        comboRole.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        comboRole.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrator", "Officer", "SalesPerson" }));

        btnSave.setBackground(new java.awt.Color(214, 190, 150));
        btnSave.setFont(new java.awt.Font("Segoe UI Black", 1, 22)); // NOI18N
        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(102, 66, 41));

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 0, 40)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Personal Profile");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        imageAvatar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/oodj/Dashboard/icon/user.png"))); // NOI18N

        btnChangeAvatar.setBorder(null);
        btnChangeAvatar.setText("Change Avatar");
        btnChangeAvatar.setBorderColor(new java.awt.Color(102, 66, 41));
        btnChangeAvatar.setColorClick(new java.awt.Color(204, 153, 0));
        btnChangeAvatar.setColorOver(new java.awt.Color(153, 102, 0));
        btnChangeAvatar.setRadius(35);
        btnChangeAvatar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangeAvatarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfStaffName, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfStaffPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfStaffPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboSecQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfSecAnswer, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboRole, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(243, 243, 243)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfStaffEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 102, Short.MAX_VALUE)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                        .addComponent(imageAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                        .addComponent(btnChangeAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(119, 119, 119))))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(tfStaffName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(82, 82, 82)
                        .addComponent(tfStaffPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(tfStaffPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(comboSecQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfSecAnswer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnChangeAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addComponent(comboRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(440, 440, 440)
                        .addComponent(btnSave))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(imageAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfStaffEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(54, 54, 54))
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

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        if (isDetailsChanged()) {
            ImageLoader.updateNewStaffName(tfStaffName.getText());
            String updatedDetails = tfStaffName.getText() + ";" + tfStaffEmail.getText() + ";"
                    + tfStaffPassword.getText() + ";" + tfStaffPhone.getText() + ";" +
                    comboSecQuestion.getSelectedItem() + ";" + tfSecAnswer.getText() + ";" + comboRole.getSelectedItem() + ";Approved";

            String filePath = "staff.txt";
            File file = new File(filePath);
            File tempFile = new File("temp.txt");

            String oldStaffName = userDetailsArray[0]; // Get the current staffName before updating details

            try (BufferedReader br = new BufferedReader(new FileReader(file)); BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] userDetails = line.split(";");
                    String email = userDetails[1];  // Use index 1 for the email field

                    if (email.equals(loggedInEmail)) {
                        bw.write(updatedDetails);
                    } else {
                        bw.write(line);
                    }
                    bw.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Delete the original file
            if (file.delete()) {
                // Rename the temp file to the original file
                if (tempFile.renameTo(file)) {
                    JOptionPane.showMessageDialog(this, "Save successful");

                    // Check if the staffName has changed
                    if (!oldStaffName.equals(tfStaffName.getText())) {
                        String profilePhotoPath = "Profile Photo/" + oldStaffName + ".png";
                        String newProfilePhotoPath = "Profile Photo/" + tfStaffName.getText() + ".png";

                        // Rename the profile photo file
                        File oldProfilePhotoFile = new File(profilePhotoPath);
                        File newProfilePhotoFile = new File(newProfilePhotoPath);
                        loadProfilePhoto(tfStaffName.getText());

                        if (oldProfilePhotoFile.exists()) {
                            if (oldProfilePhotoFile.renameTo(newProfilePhotoFile)) {
//                                JOptionPane.showMessageDialog(this, "Profile photo renamed successfully");
                                ImageLoader.loadProfilePhoto(tfStaffName.getText());

                            } else {
                                JOptionPane.showMessageDialog(this, "Failed to rename profile photo");
                            }
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to save information");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Failed to save information");
            }
        } else {
            JOptionPane.showMessageDialog(this, "No changes made");
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnChangeAvatarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangeAvatarActionPerformed
         // Create a file chooser
    JFileChooser fileChooser = new JFileChooser();

    // Set the file filter to accept only image files
    FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("Image files", "png", "jpg", "jpeg", "gif");
    fileChooser.setFileFilter(imageFilter);

    // Show the file dialog
    int result = fileChooser.showOpenDialog(this);

    // Check if a file is selected
    if (result == JFileChooser.APPROVE_OPTION) {
        // Get the selected file
        File selectedFile = fileChooser.getSelectedFile();

        // Check if the selected file is an image
        if (selectedFile.isFile()) {
            // Get the file extension
            String extension = "";
            int i = selectedFile.getName().lastIndexOf('.');
            if (i > 0) {
                extension = selectedFile.getName().substring(i + 1);
            }
            // Set the target file path in the "Profile Photo/" directory
            String targetFilePath = "Profile Photo/" + loggedInStaffName + ".png";
            
            try {
                // Copy the selected image file to the target file path
                Files.copy(selectedFile.toPath(), Paths.get(targetFilePath), StandardCopyOption.REPLACE_EXISTING);

                profileImage = ImageIO.read(selectedFile);
                ImageIcon newProfilePhotoIcon = new ImageIcon(profileImage);
                imageAvatar.setIcon(newProfilePhotoIcon);               

                ImageLoader.loadProfilePhoto(loggedInStaffName);

                JOptionPane.showMessageDialog(this, "Avatar changed successfully");
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Failed to change avatar");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a valid image file");
        }
    }
    }//GEN-LAST:event_btnChangeAvatarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private oodj.Dashboard.swing.CustomButton btnChangeAvatar;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<String> comboRole;
    private javax.swing.JComboBox<String> comboSecQuestion;
    private oodj.Dashboard.swing.ImageAvatar imageAvatar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private oodj.Dashboard.swing.RoundPanel jPanel1;
    private oodj.Dashboard.swing.RoundPanel roundPanel1;
    private javax.swing.JTextField tfSecAnswer;
    private javax.swing.JTextField tfStaffEmail;
    private javax.swing.JTextField tfStaffName;
    private javax.swing.JTextField tfStaffPassword;
    private javax.swing.JTextField tfStaffPhone;
    // End of variables declaration//GEN-END:variables
}
