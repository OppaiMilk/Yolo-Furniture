package oodj.main;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import oodj.Dashboard.main.Dashboard;

public class WelcomePage extends javax.swing.JFrame {

    public WelcomePage() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backgroundPanel = new javax.swing.JPanel();
        Loading = new javax.swing.JProgressBar();
        LoadingValue = new javax.swing.JLabel();
        LoadingLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(21, 21, 21));
        setUndecorated(true);

        backgroundPanel.setBackground(new java.awt.Color(102, 66, 41));
        backgroundPanel.setPreferredSize(new java.awt.Dimension(1000, 520));

        Loading.setBackground(new java.awt.Color(245, 240, 228));
        Loading.setForeground(new java.awt.Color(214, 190, 150));
        Loading.setBorder(null);
        Loading.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        LoadingValue.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        LoadingValue.setForeground(new java.awt.Color(225, 225, 225));
        LoadingValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LoadingValue.setText("0%");

        LoadingLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        LoadingLabel.setForeground(new java.awt.Color(225, 225, 225));
        LoadingLabel.setText("Loading.......");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(225, 225, 225));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/oodj/Dashboard/icon/yoyo.gif"))); // NOI18N

        javax.swing.GroupLayout backgroundPanelLayout = new javax.swing.GroupLayout(backgroundPanel);
        backgroundPanel.setLayout(backgroundPanelLayout);
        backgroundPanelLayout.setHorizontalGroup(
            backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundPanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(LoadingLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 955, Short.MAX_VALUE)
                .addComponent(LoadingValue, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(Loading, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(backgroundPanelLayout.createSequentialGroup()
                .addGap(293, 293, 293)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        backgroundPanelLayout.setVerticalGroup(
            backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundPanelLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LoadingValue)
                    .addComponent(LoadingLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Loading, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backgroundPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1098, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backgroundPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 646, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        WelcomePage wel = new WelcomePage();
        wel.setVisible(true);

        try {
            for (int i = 0; i <= 100; i++) {
                Thread.sleep(20);
                wel.LoadingValue.setText(i + "%");

                if (i == 10) {
                    wel.LoadingLabel.setText("Turning On Modules...");
                }
                if (i == 20) {
                    wel.LoadingLabel.setText("Loading Modules...");
                }
                if (i == 50) {
                    wel.LoadingLabel.setText("Connecting To Database...");
                }
                if (i == 70) {
                    wel.LoadingLabel.setText("Connection Successful...");
                }
                if (i == 80) {
                    wel.LoadingLabel.setText("Launching Application...");
                }
                wel.Loading.setValue(i);

                if (i == 100){
                    wel.dispose();
                    SwingUtilities.invokeLater(() -> {
                        Login login = new Login();
//                        login.pack();
//                        login.setLocationRelativeTo(null);
                        login.setVisible(true);
                    });
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar Loading;
    private javax.swing.JLabel LoadingLabel;
    private javax.swing.JLabel LoadingValue;
    private javax.swing.JPanel backgroundPanel;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
