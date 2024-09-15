package oodj.Dashboard.main;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JOptionPane;
import oodj.Dashboard.event.EventMenu;
import oodj.Dashboard.form.Check_Product_Status;
import oodj.Dashboard.form.Form;
import oodj.Dashboard.form.Form_1;
import oodj.Dashboard.form.New_Sale_Order;
import oodj.Dashboard.form.OrderQuotation;
import oodj.Dashboard.form.ReportGeneration;
import oodj.Dashboard.form.SalesForm;
import oodj.Dashboard.form.listSaleOrder;
import oodj.Dashboard.form.manageProfile;
import oodj.Dashboard.form.manageWorkerProfile;
import oodj.Dashboard.form.productStatus;
import oodj.Dashboard.form.saleOrderProcess;
import oodj.main.Login;

public class Dashboard extends javax.swing.JFrame {

    private String staffName;
    private String staffEmail;
    private String staffRole;
    
    public Dashboard(String staffName, String staffEmail, String staffRole) {
        initComponents();
        setBackground(new Color(0, 0, 0, 0));
        this.staffName = staffName;
        this.staffEmail = staffEmail;
        this.staffRole = staffRole;
        
        EventMenu event = new EventMenu() {
            @Override
            public void selected(int index) {
                if (index == 0) {
                    showForm(new Form_1());
                }else if (index == 1) {
                    showForm(new saleOrderProcess());
                }else if(index == 2){
                    showForm(new SalesForm());
//                }else if(index == 3){
//                    showForm(new productStatus());
                }else if (index == 4) {
                    showForm(new ReportGeneration(staffRole,staffName));
                }else if (index == 5) {
                    showForm(new manageProfile(staffEmail,staffName));
                }else if (index == 6) {
                    showForm(new manageWorkerProfile());
                }else if (index == 7) {
                    showForm(new Check_Product_Status());
                }else if (index == 9) {
                    showForm(new New_Sale_Order(staffName));
                }else if (index == 10) {
                    showForm(new OrderQuotation(staffName));
                }else if (index == 11) {
                    showForm(new listSaleOrder(staffName));
                }else if (index == 12) {
                    int YesOrNo = JOptionPane.showConfirmDialog(null,"Do You Want To Exit","Exit Dashboard", JOptionPane.YES_NO_OPTION);
                    if(YesOrNo == 0){
                        Login login = new Login();
                        login.setVisible(true);
                        Dashboard.this.dispose();
                    }else{
                        index = 0;
                        showForm(new Form_1());
                    }
                } else {
                    showForm(new Form(index));
                }
            }
        };
        menuBar1.initMenu(event, staffName, staffEmail, staffRole);
        showForm(new Form_1());
    }
    
    public void showForm(Component com) {
        body.removeAll();
        body.add(com);
        body.revalidate();
        body.repaint();
    }
    
    public String getStaffName() {
        return staffName;
    }

    public String getStaffEmail() {
        return staffEmail;
    }

    public String getStaffRole() {
        return staffRole;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new oodj.Dashboard.swing.RoundPanel();
        body = new javax.swing.JPanel();
        menuBar1 = new oodj.Dashboard.component.MenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        roundPanel1.setBackground(new java.awt.Color(214, 190, 150));

        body.setOpaque(false);
        body.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(menuBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, 1031, Short.MAX_VALUE))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE)
                    .addComponent(menuBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Dashboard().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JPanel body;
    private oodj.Dashboard.component.MenuBar menuBar1;
    private oodj.Dashboard.swing.RoundPanel roundPanel1;
    // End of variables declaration//GEN-END:variables
}
