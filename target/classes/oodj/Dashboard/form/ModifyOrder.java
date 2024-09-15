package oodj.Dashboard.form;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import oodj.Dashboard.event.CartDeleteEditor;
import oodj.Dashboard.event.CartDeleteRender;
import oodj.Dashboard.event.SpinnerCellEditor;
import oodj.Dashboard.event.TableDeleteEvent;
import oodj.Dashboard.swing.TableCustom;

public class ModifyOrder extends javax.swing.JFrame {
    
    private String orderID;
    private String staffName;
    private SpinnerNumberModel spinnerModel;

    public ModifyOrder(String orderID, String staffName) {
        this.orderID = orderID;
        this.staffName = staffName;
        this.setTitle("Order " + orderID);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int result = JOptionPane.showConfirmDialog(
                        ModifyOrder.this,
                        "Changes you made may not be saved! \nDo you want to Exit?",
                        "Confirmation",
                        JOptionPane.YES_NO_OPTION
                );

                if (result == JOptionPane.YES_OPTION) {
                    dispose();
                }
            }
        });
        
        initComponents();
        TableCustom.apply(jScrollPane1, TableCustom.TableType.DEFAULT);
        adjustColumnWidths();
        spinnerModel = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1); ;
        jTable1.getColumnModel().getColumn(2).setCellEditor(new SpinnerCellEditor(spinnerModel));
        loadDataFromOrderFile();
        spinnerModel.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                updateTotalColumn();
                updateTotalValuesAfterSpinnerChange();
            }
        });
        
        TableDeleteEvent event = new TableDeleteEvent() {
        @Override
            public void onDelete(int row) {
                if (jTable1.isEditing()) {
                    jTable1.getCellEditor().stopCellEditing();
                }

                DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                String itemID = model.getValueAt(row, 0).toString(); // Assuming Item ID is in the first column

                // Ask for confirmation
                int result = JOptionPane.showConfirmDialog(
                    ModifyOrder.this,
                    "Are you sure you want to delete this item?",
                    "Confirmation",
                    JOptionPane.YES_NO_OPTION
                );

                if (result == JOptionPane.YES_OPTION) {
                    model.removeRow(row);
                    for (int allRow = 0; allRow < model.getRowCount(); allRow++) {
                        int quantity = (int) model.getValueAt(allRow, 2);
                        int price = (int) model.getValueAt(allRow, 3);
                        int total = quantity * price;
                        model.setValueAt(total, allRow, 4);
                    }
                    updateOrderFileAfterDeletion(itemID);
                    updateTotalQuantityAndAmount();
                }
            }
        };

        jTable1.getColumnModel().getColumn(5).setCellRenderer(new CartDeleteRender());
        jTable1.getColumnModel().getColumn(5).setCellEditor(new CartDeleteEditor(event));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        lbSalesperson = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        totalAmountValue = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        totalQuantityValue = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lbDateTime = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lbStatus = new javax.swing.JLabel();
        btnUpdate = new oodj.Dashboard.swing.CustomButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Order ID");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item ID", "Furniture Name", "Quantity", "Price", "Total", "Action"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setRowHeight(40);
        jScrollPane1.setViewportView(jTable1);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Salesperson :");

        lbSalesperson.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Total Amount  :");

        totalAmountValue.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Total Quantity :");

        totalQuantityValue.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Date & Time :");

        lbDateTime.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Status :");

        lbStatus.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnUpdate.setBackground(new java.awt.Color(255, 204, 153));
        btnUpdate.setBorder(null);
        btnUpdate.setText("Update");
        btnUpdate.setBorderColor(new java.awt.Color(255, 153, 0));
        btnUpdate.setColor(new java.awt.Color(255, 204, 153));
        btnUpdate.setColorClick(new java.awt.Color(255, 204, 102));
        btnUpdate.setColorOver(new java.awt.Color(255, 255, 255));
        btnUpdate.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnUpdate.setRadius(18);
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 579, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addComponent(jLabel6)
                                .addGap(10, 10, 10)
                                .addComponent(lbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(11, 11, 11)
                                        .addComponent(jLabel5)
                                        .addGap(10, 10, 10))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbSalesperson, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                                    .addComponent(lbDateTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(totalQuantityValue, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(totalAmountValue, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(25, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(275, 275, 275)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(243, 243, 243))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbSalesperson, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                            .addComponent(lbDateTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(totalQuantityValue, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(totalAmountValue, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        updateOrderFile();
        this.dispose();
        
    }//GEN-LAST:event_btnUpdateActionPerformed
    private void updateOrderFileAfterDeletion(String deletedItemID) {
    // Read all lines from the order file
    Path path = Paths.get("order.txt");
    List<String> lines;
    try {
        lines = Files.readAllLines(path, StandardCharsets.UTF_8);
    } catch (IOException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error reading data from order.txt", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Remove the line corresponding to the deleted item from the order file
    lines.removeIf(line -> line.startsWith(orderID + "," + deletedItemID + ","));

    // Write the updated lines back to the order file
    try {
        Files.write(path, lines, StandardCharsets.UTF_8);
    } catch (IOException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error writing data to order.txt", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    private void updateTotalColumn() {
        int selectedRow = jTable1.getSelectedRow();

        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

            // Get the quantity and price from the corresponding columns
            int quantity = (int) spinnerModel.getValue();
            int price = (int) model.getValueAt(selectedRow, 3);

            // Calculate the total price (quantity * price)
            int total = quantity * price;

            // Update the total column
            model.setValueAt(total, selectedRow, 4);
        }
    }

    private void updateTotalQuantityAndAmount() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

        int totalQuantity = 0;
        int totalAmount = 0;

        for (int row = 0; row < model.getRowCount(); row++) {
            Object quantityObj = model.getValueAt(row, 2);
            Object priceObj = model.getValueAt(row, 3);

            // Check for null values before converting to Integer
            if (quantityObj != null && priceObj != null) {
                int quantity = (int) quantityObj;
                int price = (int) priceObj;

                totalQuantity += quantity;
                totalAmount += quantity * price;
            }
        }

        totalQuantityValue.setText(Integer.toString(totalQuantity));
        totalAmountValue.setText("RM " + Integer.toString(totalAmount));
    }
    
    private void updateTotalValuesAfterSpinnerChange() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

        int totalQuantity = 0;
        int totalAmount = 0;
        int selectedRowQuantity = 0;

        // Calculate total quantity and amount for unselected rows
        for (int row = 0; row < model.getRowCount(); row++) {
            int quantity = (int) model.getValueAt(row, 2);
            int price = (int) model.getValueAt(row, 3);

            if (!jTable1.isRowSelected(row)) {
                totalQuantity += quantity;
                totalAmount += quantity * price;
            } else {
                // Save the quantity of the selected row
                selectedRowQuantity = (int) spinnerModel.getValue();
            }
        }

        // Check if there is a selected row
        if (jTable1.getSelectedRow() != -1) {
            // Add the quantity of the selected row to the total
            totalQuantity += selectedRowQuantity;
            totalAmount += selectedRowQuantity * (int) model.getValueAt(jTable1.getSelectedRow(), 3);
        }

        totalQuantityValue.setText(Integer.toString(totalQuantity));
        totalAmountValue.setText("RM " + Integer.toString(totalAmount));
    }

    private void loadDataFromOrderFile() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        
        try (BufferedReader reader = new BufferedReader(new FileReader("order.txt"))) {
            String line;
            
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String currentOrderID = parts[0].trim();
                
                if (currentOrderID.equals(orderID)) {
                    jLabel1.setText(orderID);
                    lbDateTime.setText(parts[6].trim());
                    lbSalesperson.setText(parts[7].trim());
                    lbStatus.setText(parts[8].trim());
                    
                    // Create a JSpinner with the current quantity value
                    JSpinner spinner = new JSpinner(spinnerModel);
                    spinner.setValue(Integer.valueOf(parts[3].trim()));
                    model.addRow(new Object[] {
                        parts[1].trim(), // Item ID
                        parts[2].trim(), // Furniture Name
                        spinner.getValue(), // Quantity
                        Integer.valueOf(parts[4].trim()), // Price
                        Integer.valueOf(parts[5].trim()), // Total
                        "" //Action
                    });
                }
            }       
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading data from order.txt", "Error", JOptionPane.ERROR_MESSAGE);
        }
        updateTotalQuantityAndAmount();
    }
    
    private void updateOrderFile(){
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int rowCount = model.getRowCount();

        // Read all lines from the order file
        Path path = Paths.get("order.txt");
        List<String> lines;
        try {
            lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error reading data from order.txt", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Update the lines with the new data from jTable1
        for (int i = 0; i < rowCount; i++) {
            String updatedOrderID = jLabel1.getText();
            String updatedFurnitureName = model.getValueAt(i, 1).toString(); // Assuming furniture name is in the second column

            for (int j = 0; j < lines.size(); j++) {
                String line = lines.get(j);
                String[] parts = line.split(",");
                String currentOrderID = parts[0].trim();
                String currentFurnitureName = parts[2].trim();

                if (currentOrderID.equals(updatedOrderID) && currentFurnitureName.equals(updatedFurnitureName)) {
                    // Update the line with the new data
                    int newQuantity = (int) model.getValueAt(i, 2);
                    int newTotal = (int) model.getValueAt(i, 4);

                    lines.set(j, String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s",
                            updatedOrderID, parts[1],currentFurnitureName, newQuantity, parts[4], newTotal,
                            parts[6], parts[7], parts[8])); // Update other columns as needed

                    break;
                }
            }
        }

        try {
            Files.write(path, lines, StandardCharsets.UTF_8);
            JOptionPane.showMessageDialog(this, "Order " + orderID + " updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error writing data to order.txt", "Error", JOptionPane.ERROR_MESSAGE);
        }
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
    private oodj.Dashboard.swing.CustomButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lbDateTime;
    private javax.swing.JLabel lbSalesperson;
    private javax.swing.JLabel lbStatus;
    private javax.swing.JLabel totalAmountValue;
    private javax.swing.JLabel totalQuantityValue;
    // End of variables declaration//GEN-END:variables
}
