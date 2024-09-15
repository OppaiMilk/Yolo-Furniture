package oodj.Dashboard.form;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import oodj.Dashboard.component.OrderDisplayBox;
import oodj.Dashboard.event.ModelOrder;

public class saleOrderProcess extends javax.swing.JPanel {
    
    private Map<String, OrderDisplayBox> orderDisplayBoxMap;
    private OrderDisplayBox selectedOrderDisplayBox;
    private String staffName;
    /**
     * Creates new form saleOrderProcess
     */
    public saleOrderProcess() {
        initComponents();
        setOpaque(false);
        orderDisplayBoxMap = new HashMap<>();
        loadOrdersFromFile();
        
        searchBar.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filterOrders(searchBar.getText().trim());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterOrders(searchBar.getText().trim());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Plain text components do not fire these events
            }
        });
    }
    
    private void filterOrders(String orderId) {
        String lowercaseOrderId = orderId.toLowerCase();
        
        for (OrderDisplayBox orderDisplayBox : orderDisplayBoxMap.values()) {
            String displayBoxOrderId = orderDisplayBox.getData().getOrderID().toLowerCase();

            if (lowercaseOrderId.isEmpty() || displayBoxOrderId.equals(lowercaseOrderId)) {
                orderDisplayBox.setVisible(true);
            } else {
                orderDisplayBox.setVisible(false);
            }
        }
    }
    
    private ModelOrder createModelOrder(String[] orderData) {
        return new ModelOrder(orderData[0], orderData[2], Integer.parseInt(orderData[3]),
            orderData[6], orderData[7], orderData[8]);
    }
    
    public void loadOrdersFromFile() {
        boolean hasPendingOrders = false;

        try (BufferedReader br = new BufferedReader(new FileReader("order.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] orderData = line.split(",");
                if (orderData.length >= 9) {
                    String orderId = orderData[0];
                    String orderStatus = orderData[8];
                    String staffName = orderData[7];
                    this.staffName = staffName;

                    // Check if the order has the status "Pending"
                    if ("Pending".equals(orderStatus)) {
                        hasPendingOrders = true;
                        ModelOrder modelOrder = createModelOrder(orderData);

                        OrderDisplayBox orderDisplayBox = orderDisplayBoxMap.get(orderId);

                        if (orderDisplayBox == null) {
                            orderDisplayBox = new OrderDisplayBox();
                            orderDisplayBoxMap.put(orderId, orderDisplayBox);
                            panelOrder1.add(orderDisplayBox);

                            final OrderDisplayBox finalOrderDisplayBox = orderDisplayBox; // Declare as final
                            orderDisplayBox.addMouseListener(new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    selectedOrderDisplayBox = finalOrderDisplayBox;
                                    handleOrderDisplayBoxSelection(selectedOrderDisplayBox);
                                }
                            });
                        }

                        // Add furniture name and quantity to the orderList of the OrderDisplayBox
                        orderDisplayBox.getOrderListData().add(modelOrder.getFurnitureName()
                                + "   x " + modelOrder.getQuantity());

                        orderDisplayBox.setData(modelOrder);
                    }
                }
            }
            
            if (!hasPendingOrders) {
                panelOrder1.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 250));
                JLabel noPendingOrderLabel = new JLabel("No Pending Order");
                noPendingOrderLabel.setHorizontalAlignment(JLabel.CENTER);
                noPendingOrderLabel.setVerticalAlignment(JLabel.CENTER);
                noPendingOrderLabel.setFont(new Font("Arial", Font.PLAIN, 48)); // Set the desired font size
                noPendingOrderLabel.setForeground(new Color(137, 137, 137)); // Set the desired color
                panelOrder1.add(noPendingOrderLabel);
            }
            
            panelOrder1.repaint();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void handleOrderDisplayBoxSelection(OrderDisplayBox selectedOrderDisplayBox) {
        for (OrderDisplayBox orderDisplayBox : orderDisplayBoxMap.values()) {
            orderDisplayBox.setSelected(orderDisplayBox == selectedOrderDisplayBox);
            if (orderDisplayBox.isSelected()) {
                System.out.println("Selected Order ID: " + orderDisplayBox.getData().getOrderID());
            }
        }
    }

    public void refresh(){
        orderDisplayBoxMap.clear(); // Clear the existing order display boxes
        panelOrder1.removeAll(); // Remove components from the panel
        loadOrdersFromFile(); // Reload orders
        panelOrder1.repaint(); 
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel2 = new oodj.Dashboard.swing.RoundPanel();
        searchBar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        panelOrder1 = new oodj.Dashboard.swing.PanelOrder();
        btnRefresh = new javax.swing.JLabel();
        btnModify = new oodj.Dashboard.swing.CustomButton();
        btnApproval = new oodj.Dashboard.swing.CustomButton();

        roundPanel2.setBackground(new java.awt.Color(245, 240, 228));

        searchBar.setText("Search Order ID");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/oodj/Dashboard/icon/search.png"))); // NOI18N

        jScrollPane1.setBackground(new java.awt.Color(245, 240, 228));
        jScrollPane1.setBorder(null);
        jScrollPane1.setViewportView(panelOrder1);

        btnRefresh.setBackground(new java.awt.Color(255, 204, 204));
        btnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/oodj/Dashboard/icon/Refresh.png"))); // NOI18N
        btnRefresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRefreshMouseClicked(evt);
            }
        });

        btnModify.setBorder(null);
        btnModify.setIcon(new javax.swing.ImageIcon(getClass().getResource("/oodj/Dashboard/icon/Modify.png"))); // NOI18N
        btnModify.setText("Modify");
        btnModify.setBorderColor(new java.awt.Color(0, 102, 204));
        btnModify.setColorClick(new java.awt.Color(0, 153, 204));
        btnModify.setColorOver(new java.awt.Color(102, 204, 255));
        btnModify.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnModify.setRadius(30);
        btnModify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModifyActionPerformed(evt);
            }
        });

        btnApproval.setBorder(null);
        btnApproval.setIcon(new javax.swing.ImageIcon(getClass().getResource("/oodj/Dashboard/icon/Approval.png"))); // NOI18N
        btnApproval.setText("Approval");
        btnApproval.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnApproval.setRadius(30);
        btnApproval.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApprovalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, roundPanel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(btnRefresh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(searchBar, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1)
                        .addGap(97, 97, 97)
                        .addComponent(btnModify, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnApproval, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 946, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 52, Short.MAX_VALUE))
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(btnRefresh))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel2Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnApproval, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnModify, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(searchBar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 605, Short.MAX_VALUE)
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(roundPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnRefreshMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRefreshMouseClicked
        refresh(); 
    }//GEN-LAST:event_btnRefreshMouseClicked

    private void btnModifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModifyActionPerformed
        if (selectedOrderDisplayBox != null) {
            String orderID = selectedOrderDisplayBox.getData().getOrderID();
            ModifyOrder modifyOrder = new ModifyOrder(orderID, staffName);
            modifyOrder.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Please select an order to modify.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnModifyActionPerformed

    private void btnApprovalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApprovalActionPerformed
        if (selectedOrderDisplayBox != null) {
            String orderID = selectedOrderDisplayBox.getData().getOrderID();
            orderApproval oa = new orderApproval(orderID);
            oa.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Please select an order for Approval.", "Error", JOptionPane.ERROR_MESSAGE);
        }		
    }//GEN-LAST:event_btnApprovalActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private oodj.Dashboard.swing.CustomButton btnApproval;
    private oodj.Dashboard.swing.CustomButton btnModify;
    private javax.swing.JLabel btnRefresh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private oodj.Dashboard.swing.PanelOrder panelOrder1;
    private oodj.Dashboard.swing.RoundPanel roundPanel2;
    private javax.swing.JTextField searchBar;
    // End of variables declaration//GEN-END:variables
}
