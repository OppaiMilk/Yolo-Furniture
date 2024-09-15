package oodj.Dashboard.form;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import oodj.Dashboard.event.CartDeleteEditor;
import oodj.Dashboard.event.CartDeleteRender;
import oodj.Dashboard.event.TableDeleteEvent;
import oodj.Dashboard.swing.TableCustom;

public class OrderCart extends javax.swing.JFrame {
    
    private String staffName;
    private int orderCounter = 0;
    
    public OrderCart(String staffName) {
        initComponents();
        this.staffName = staffName;
//        loadExistingOrders();
        loadCartData();
        adjustColumnWidths();
        TableCustom.apply(jScrollPane1, TableCustom.TableType.DEFAULT);
        
        TableDeleteEvent event = new TableDeleteEvent() {
            @Override
            public void onDelete(int row) {
                if (jTable1.isEditing()) {
                        jTable1.getCellEditor().stopCellEditing();
                    }
               DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                // Get the values from the selected row
                int quantity = (int) model.getValueAt(row, 2);
                String totalStr = String.valueOf(model.getValueAt(row, 4)).replaceAll("[^\\d.]", "");
                int total = Integer.parseInt(totalStr);                
                model.removeRow(row);
                updateCartFile();
                updateTotalValues(-quantity, -total);
            }
        };
        jTable1.getColumnModel().getColumn(5).setCellRenderer(new CartDeleteRender());
        jTable1.getColumnModel().getColumn(5).setCellEditor(new CartDeleteEditor(event));       
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        totalQuantityValue = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        totalAmountValue = new javax.swing.JLabel();
        completedButton = new oodj.Dashboard.swing.CustomButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Order Cart");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Order Cart");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Item ID", "Furniture Name", "Quantity", "Price", "Total", "Action"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setRowHeight(30);
        jScrollPane1.setViewportView(jTable1);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Total Quantity :");

        totalQuantityValue.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Total Amount  :");

        totalAmountValue.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        completedButton.setBorder(null);
        completedButton.setText("Create Order");
        completedButton.setColorOver(new java.awt.Color(51, 255, 0));
        completedButton.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        completedButton.setRadius(20);
        completedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                completedButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 579, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(18, 18, 18)
                                        .addComponent(totalAmountValue, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(18, 18, 18)
                                        .addComponent(totalQuantityValue, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(completedButton, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(250, 250, 250)
                        .addComponent(jLabel1)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(totalQuantityValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(totalAmountValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(completedButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void completedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_completedButtonActionPerformed
        if (jTable1.getRowCount() == 0) {
            JOptionPane.showMessageDialog(
                null,
                "There is no item being added into the order. Please order at least one furniture item.",
                "Empty Order",
                JOptionPane.WARNING_MESSAGE
            );
            return;  // Exit the method if no items are in the order
        }
        
        loadExistingOrders();
        String orderId = String.format("ORD%04d", orderCounter);
        saveCartDataToOrderFile(orderId);
        orderCounter++;
        clearCart();
//        loadCartData();
        JOptionPane.showMessageDialog(null, "Order has been created successfully.", "Order " + orderId + " Created", JOptionPane.INFORMATION_MESSAGE);
        this.dispose();
    }//GEN-LAST:event_completedButtonActionPerformed
    
    private void loadExistingOrders() {
        try (BufferedReader reader = new BufferedReader(new FileReader("order.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String orderId = parts[0];
                int orderIdNumber = Integer.parseInt(orderId.substring(3)); // Extract the numeric part
                orderCounter = Math.max(orderCounter, orderIdNumber);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        if (orderCounter > 0) {
            orderCounter++;
        } else {
            orderCounter = 1;
        }
    }
    
    private void saveCartDataToOrderFile(String orderId) {
        try (BufferedReader reader = new BufferedReader(new FileReader("cart.txt"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("order.txt", true));
             BufferedWriter cartWriter = new BufferedWriter(new FileWriter("cart_temp.txt"))) {

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length>=6){
                    String itemId = parts[0];
                    String furnitureName = parts[1];
                    int quantity = Integer.parseInt(parts[2]);
                    String priceStr = parts[3].replaceAll("[^\\d.]", "");
                    int price = Integer.parseInt(priceStr);
                    String totalStr = parts[4].replaceAll("[^\\d.]", "");
                    int total = Integer.parseInt(totalStr);
                    String cartStaffName = parts[5]; // Adjust this index based on your actual data structure

                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
                    LocalDateTime now = LocalDateTime.now();
                    String currentDateAndTime = dtf.format(now);

                    if (staffName.equals(cartStaffName)) {
                        // Write to order.txt
                        writer.write(orderId + "," + itemId + "," + furnitureName + "," + quantity + "," + price + "," + total + "," + currentDateAndTime + "," + staffName + "," + "Pending");
                        writer.newLine();
                    } else {
                        // Write to cart_temp.txt
                        cartWriter.write(line);
                        cartWriter.newLine();
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
    }

    // Rename cart_temp.txt to cart.txt after writing the modified cart data
    Path tempPath = Paths.get("cart_temp.txt");
    Path cartPath = Paths.get("cart.txt");
    try {
        Files.move(tempPath, cartPath, StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException e) {
        e.printStackTrace();
    }
}


    private void clearCart() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
//        updateCartFile(); // Update the cart.txt file after clearing the cart
        totalQuantityValue.setText("0");
        totalAmountValue.setText("RM 0.00");
    }
        
    private void loadCartData() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        int totalQuantity = 0;
        double totalAmount = 0.0;

        try (BufferedReader reader = new BufferedReader(new FileReader("cart.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length>=6){
                    String itemId = parts[0];
                    String furnitureName = parts[1];
                    int quantity = Integer.parseInt(parts[2]);
                    int price = Integer.parseInt(parts[3]);
                    int total = Integer.parseInt(parts[4]);
                    String cartStaffName = parts[5];

                    if (staffName.equals(cartStaffName)) {
                        model.addRow(new Object[]{itemId, furnitureName, quantity, "RM " + price, "RM " + total, "Delete"});

                        totalQuantity += quantity;
                        totalAmount += total;
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        totalQuantityValue.setText(String.valueOf(totalQuantity));
        totalAmountValue.setText(String.valueOf("RM " + totalAmount));
    }

       
    private void updateCartFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("cart.txt"))) {
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

            for (int row = 0; row < model.getRowCount(); row++) {
                String itemId = (String) model.getValueAt(row, 0);
                String furnitureName = (String) model.getValueAt(row, 1);
                int quantity = (int) model.getValueAt(row, 2);
                String priceStr = String.valueOf(model.getValueAt(row, 3)).replaceAll("[^\\d.]", "");
                int price = Integer.parseInt(priceStr);
                String totalStr = String.valueOf(model.getValueAt(row, 4)).replaceAll("[^\\d.]", "");
                int total = Integer.parseInt(totalStr);
                writer.write(itemId + "," + furnitureName + "," + quantity + "," + price + "," + total + "," + staffName);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateTotalValues(int quantityChange, double totalChange) {
        int currentTotalQuantity = Integer.parseInt(totalQuantityValue.getText());
        double currentTotalAmount = parseCurrency(totalAmountValue.getText());

        int newTotalQuantity = currentTotalQuantity + quantityChange;
        double newTotalAmount = currentTotalAmount + totalChange;

        totalQuantityValue.setText(String.valueOf(newTotalQuantity));
        totalAmountValue.setText(String.format("RM %.2f", newTotalAmount));
    }

    private double parseCurrency(String currencyString) {
        // Extract numeric part and convert to double
        String numericPart = currencyString.replaceAll("[^\\d.]", "");
        return Double.parseDouble(numericPart);
    }
    
    private void adjustColumnWidths() {
        jTable1.getTableHeader().setResizingAllowed(false);
        TableColumnModel columnModel = jTable1.getColumnModel();

        columnModel.getColumn(0).setPreferredWidth(5);
        columnModel.getColumn(1).setPreferredWidth(120);
        columnModel.getColumn(2).setPreferredWidth(2);
        columnModel.getColumn(3).setPreferredWidth(20);
        columnModel.getColumn(4).setPreferredWidth(20);
        columnModel.getColumn(5).setPreferredWidth(5);
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        
        columnModel.getColumn(0).setCellRenderer(centerRenderer);
        columnModel.getColumn(1).setCellRenderer(centerRenderer);
        columnModel.getColumn(2).setCellRenderer(centerRenderer);
        columnModel.getColumn(3).setCellRenderer(centerRenderer);
        columnModel.getColumn(4).setCellRenderer(centerRenderer);
        columnModel.getColumn(5).setCellRenderer(centerRenderer);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private oodj.Dashboard.swing.CustomButton completedButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel totalAmountValue;
    private javax.swing.JLabel totalQuantityValue;
    // End of variables declaration//GEN-END:variables
}
