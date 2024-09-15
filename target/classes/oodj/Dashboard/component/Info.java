package oodj.Dashboard.component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import oodj.Dashboard.event.TableActionEvent;
import oodj.Dashboard.event.TableActionEvent_productStatus;

public class Info extends javax.swing.JPanel {

    public Info() {
        initComponents();
    }
    public void initEvent(TableActionEvent event, int row) {
        InfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                event.onViewDetail(row);
            }
        });
    }
    
    public void initEvent2(TableActionEvent_productStatus event, int row) {
        InfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                event.onViewDetail(row);
            }
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        InfoButton = new oodj.Dashboard.swing.ButtonBadges();

        setBackground(new java.awt.Color(255, 255, 255));

        InfoButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/oodj/Dashboard/icon/info.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(InfoButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(InfoButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private oodj.Dashboard.swing.ButtonBadges InfoButton;
    // End of variables declaration//GEN-END:variables
}
