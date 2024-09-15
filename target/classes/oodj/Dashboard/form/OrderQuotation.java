package oodj.Dashboard.form;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import oodj.Dashboard.component.OrderDisplayBox;
import oodj.Dashboard.event.ModelOrder;

public class OrderQuotation extends javax.swing.JPanel {
    
    private String staffName;
    private Map<String, OrderDisplayBox> orderDisplayBoxMap;
    private OrderDisplayBox selectedOrderDisplayBox;
    

    public OrderQuotation(String staffName) {
        initComponents();
        setOpaque(false);
        this.staffName = staffName;
        
        orderDisplayBoxMap = new HashMap<>();
        loadOrdersFromFile("order.txt");
        
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
    
    private ModelOrder createModelOrder(String[] orderData) {
        return new ModelOrder(orderData[0], orderData[2], Integer.parseInt(orderData[3]),
            orderData[6], orderData[7], orderData[8]);
    }

    private void loadOrdersFromFile(String filePath) {
        boolean hasPendingOrders = false;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] orderData = line.split(",");
                if (orderData.length == 9) {
                    String orderId = orderData[0];
                    String orderStaffName = orderData[7];
                    String orderStatus = orderData[8];
                    if ("Pending".equals(orderStatus) && staffName.equals(orderStaffName)) {
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
    
    private void deleteSelectedOrder() {
        if (selectedOrderDisplayBox != null) {
            String orderIdToDelete = selectedOrderDisplayBox.getData().getOrderID();
            removeOrderFromFile("order.txt", orderIdToDelete);

            OrderDisplayBox removedOrderDisplayBox = orderDisplayBoxMap.remove(orderIdToDelete);
            if (removedOrderDisplayBox != null) {
                removedOrderDisplayBox.setVisible(false);
                selectedOrderDisplayBox = null;
                panelOrder1.revalidate();
                panelOrder1.repaint();
            } else {
                System.out.println("Failed to find OrderDisplayBox with ID: " + orderIdToDelete);
            }
        } else {
            System.out.println("No order selected to delete.");
        }
    }

    private void removeOrderFromFile(String filePath, String orderIdToRemove) {
        File inputFile = new File(filePath);
        File tempFile = new File("temp.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
             BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] orderData = line.split(",");
                if (orderData.length == 9 && orderData[0].equals(orderIdToRemove)) {
                    continue;
                }
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Files.copy(tempFile.toPath(), inputFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Order removed successfully.");
        } catch (IOException e) {
            System.out.println("Failed to remove order: " + e.getMessage());
        }
        tempFile.delete();
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new oodj.Dashboard.swing.RoundPanel();
        searchBar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        panelOrder1 = new oodj.Dashboard.swing.PanelOrder();
        jLabel1 = new javax.swing.JLabel();
        btnRefresh = new javax.swing.JLabel();
        btnModify = new oodj.Dashboard.swing.CustomButton();
        btnDelete = new oodj.Dashboard.swing.CustomButton();

        roundPanel1.setBackground(new java.awt.Color(245, 240, 228));

        searchBar.setText("Search Order ID");

        jScrollPane1.setBackground(new java.awt.Color(245, 240, 228));
        jScrollPane1.setBorder(null);
        jScrollPane1.setOpaque(false);
        jScrollPane1.setViewportView(panelOrder1);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/oodj/Dashboard/icon/search.png"))); // NOI18N

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

        btnDelete.setBorder(null);
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/oodj/Dashboard/icon/DeleteIcon.png"))); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.setBorderColor(new java.awt.Color(255, 51, 51));
        btnDelete.setColorClick(new java.awt.Color(255, 51, 51));
        btnDelete.setColorOver(new java.awt.Color(255, 102, 102));
        btnDelete.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDelete.setRadius(30);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(btnRefresh)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(searchBar, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addGap(66, 66, 66)
                .addComponent(btnModify, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 971, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addComponent(btnRefresh)
                                .addGap(7, 7, 7))
                            .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(searchBar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnModify, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(5, 5, 5)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 606, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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

    private void btnRefreshMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRefreshMouseClicked
        orderDisplayBoxMap.clear(); // Clear the existing order display boxes
        panelOrder1.removeAll(); // Remove components from the panel
        loadOrdersFromFile("order.txt"); // Reload orders
        panelOrder1.repaint();
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

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        String displayBoxOrderId = selectedOrderDisplayBox.getData().getOrderID();
        int result = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete Order " + displayBoxOrderId + " ?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            deleteSelectedOrder();
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private oodj.Dashboard.swing.CustomButton btnDelete;
    private oodj.Dashboard.swing.CustomButton btnModify;
    private javax.swing.JLabel btnRefresh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private oodj.Dashboard.swing.PanelOrder panelOrder1;
    private oodj.Dashboard.swing.RoundPanel roundPanel1;
    private javax.swing.JTextField searchBar;
    // End of variables declaration//GEN-END:variables
}
