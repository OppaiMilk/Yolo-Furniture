package oodj.Dashboard.form;

import java.awt.Color;
import java.awt.Component;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.PatternSyntaxException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import oodj.Dashboard.event.TableActionEvent_productStatus;
import oodj.Dashboard.event.TableInfoEditor_productStatus;
import oodj.Dashboard.event.TableInfoRender;
import oodj.Dashboard.swing.ProgressBarCustom;
import oodj.Dashboard.swing.TableCustom;

public class Check_Product_Status extends javax.swing.JPanel {
    private TableRowSorter<TableModel> rowSorter;
    private Map<String, Order> orders;
    private String selectedStatus = "All Status";

    public Check_Product_Status() {
        initComponents();
        setOpaque(false);
        loadDataIntoTable();
        setupSearch();
        TableCustom.apply(jScrollPane1, TableCustom.TableType.DEFAULT);
               
        TableActionEvent_productStatus event = new TableActionEvent_productStatus() {
            @Override
            public void onViewDetail(int row) {
                if (jTable1.isEditing()) {
                    jTable1.getCellEditor().stopCellEditing();
                }
                    String orderID = jTable1.getValueAt(row,0).toString();
                    String SalesPerson= jTable1.getValueAt(row, 1).toString();
                    String Status = jTable1.getValueAt(row, 3).toString();
                    
                    String filePath = "order.txt";
                    String[] details = readDetailsFromFile(orderID, filePath);

                    String date = details[2];
                    StatusDetail detail = new StatusDetail(orderID,SalesPerson,date,Status);
        
                    detail.setVisible(true);
            }         
        };
        
        jTable1.getColumnModel().getColumn(5).setCellRenderer(new TableInfoRender());
        jTable1.getColumnModel().getColumn(5).setCellEditor(new TableInfoEditor_productStatus(event));
    }
    
    private String[] readDetailsFromFile(String orderID, String filePath) {
        String[] details = new String[3];  // Assuming there are 3 details: designer, description, size

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >=10 && parts[0].equals(orderID)) {
                    details[0] = parts[5];  // total price
                    details[1] = parts[4];  // price
                    details[2] = parts[6]; // Date
                    //details[2] = constructSizeString(parts[7], parts[8], parts[9]);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }

        return details;
    }
    
    private void setupSearch() {
        // Set up the row sorter for the JTable
        rowSorter = new TableRowSorter<>(jTable1.getModel());
        jTable1.setRowSorter(rowSorter);

        // Add a listener to the search bar for real-time filtering
        searchBar.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filterTable();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterTable();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Not used for plain text documents
            }
        });
    }

    private void filterTable() {
    String searchText = searchBar.getText();

    if (searchText.trim().length() == 0 || searchText.equals("Search Order ID")) {
        // If the search bar is empty, show all rows based on selected status
        if ("All Status".equals(selectedStatus)) {
            rowSorter.setRowFilter(null);
        } else {
            try {
                rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + selectedStatus, 3));
            } catch (PatternSyntaxException e) {
                // Handle the exception appropriately
            }
        }
    } else {
        // If the search bar has text, filter based on order ID and selected status
        try {
            String regex = "(?i)" + searchText;

            // If "In Progress" is selected, filter based on order ID and "In Progress" status
            if ("In Progress".equals(selectedStatus)) {
                regex += "|In Progress";
            }

            rowSorter.setRowFilter(RowFilter.regexFilter(regex, 0, 3));
        } catch (PatternSyntaxException e) {
            // Handle the exception appropriately
        }
    }
}

    
    public class CenterRenderer extends DefaultTableCellRenderer {
        public CenterRenderer() {
            setHorizontalAlignment(JLabel.CENTER);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        }
    }
        
    private void loadDataIntoTable() {
        // Read data from the order.txt file and process it
        orders = readAndProcessData("order.txt");

        // Create the table model
        DefaultTableModel model = new DefaultTableModel(
                new Object[][]{},  // Empty data
                new String[]{"Order ID", "SalesPerson", "Date & Time", "Status", "Progress", "Action"}
        ){
            @Override
            public boolean isCellEditable(int row, int column) {
                return true;
            }
        };
        // Populate the table model with processed data
        for (Order order : orders.values()) {
            model.addRow(new Object[]{
                    order.getOrderId(),
                    order.getSalesPerson(),
                    order.getDateTime(),
                    order.getStatus(),
                    order.getProgress(),
                    ""
            });
        }

        // Set the table model to the jTable1
        jTable1.setModel(model);
        
        for (int i = 0; i < jTable1.getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setCellRenderer(new CenterRenderer());
        }

        // Set the custom cell renderer for the "Progress" column
        jTable1.getColumnModel().getColumn(4).setCellRenderer(new ProgressRenderer(orders));
    }

    private Map<String, Order> readAndProcessData(String filePath) {
        Map<String, Order> orders = new LinkedHashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 10) {
                    String orderId = parts[0];
                    String salesPerson = parts[7];
                    String dateTime = parts[6];
                    int progress = Integer.parseInt(parts[4]);
                    String status = parts[9];

                    // Check if the order already exists
                    if (orders.containsKey(orderId)) {
                        // Update the progress, status, and totalItemCount if needed
                        Order existingOrder = orders.get(orderId);
                        existingOrder.setProgress(existingOrder.getProgress() + progress);

                        // Update the status based on the conditions
                        if ("WorkDone".equals(status)) {
                            existingOrder.incrementWorkDoneCount();
                            existingOrder.setStatus("In Progress");
                        } else if ("In Progress".equals(status)) {
                            existingOrder.setStatus("In Progress");
                        }

                        // Increment totalItemCount for the order
                        existingOrder.incrementTotalItemCount();
                        
                    } else {
                        // Create a new order
                        Order newOrder = new Order(orderId, salesPerson, dateTime, progress, status);
                        orders.put(orderId, newOrder);

                        // Increment work done count if status is "WorkDone"
                        if ("WorkDone".equals(status)) {
                            newOrder.incrementWorkDoneCount();
                        }

                        // Set totalItemCount to 1 for the new order
                        newOrder.incrementTotalItemCount();
                    }
                }
            }
            
            // Check and update the final status for each order
            for (Order order : orders.values()) {
                if (order.getTotalItemCount() == order.getWorkDoneCount()) {
                    order.setStatus("WorkDone");
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Log or handle the IOException appropriately
        } catch (NumberFormatException e) {
            e.printStackTrace(); // Log or handle the NumberFormatException appropriately
        }

        return orders;
    }

    private static class Order {
        private final String orderId;
        private final String salesPerson;
        private final String dateTime;
        private int progress;
        private String status;
        private int workDoneCount;
        private int totalItemCount;
    
        public Order(String orderId, String salesPerson, String dateTime, int progress, String status) {
            this.orderId = orderId;
            this.salesPerson = salesPerson;
            this.dateTime = dateTime;
            this.progress = progress;
            this.status = status;
            this.workDoneCount = 0;
            this.totalItemCount=0;
        }

        public String getOrderId() {
            return orderId;
        }

        public String getSalesPerson() {
            return salesPerson;
        }

        public String getDateTime() {
            return dateTime;
        }

        public int getProgress() {
            return progress;
        }

        public void setProgress(int progress) {
            this.progress = progress;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public void incrementWorkDoneCount() {
            workDoneCount++;
        }

        public int getWorkDoneCount() {
            return workDoneCount;
        }
        
        public int getTotalItemCount() {
            return totalItemCount;
        }

        public void incrementTotalItemCount() {
            totalItemCount++;
        }
    }

    // Custom cell renderer for the progress column
    private class ProgressRenderer extends DefaultTableCellRenderer {
        private ProgressBarCustom progressBar;
        private Map<String, Order> orders;

        public ProgressRenderer(Map<String, Order> orders) {
            // Set up the progress bar
            progressBar = new ProgressBarCustom();
            progressBar.setStringPainted(true); // Display the percentage as text
            this.orders = orders;
        }

        @Override
        public ProgressBarCustom getTableCellRendererComponent(
                JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

            // Set the progress value based on the "WorkDone" count for the order
            String orderId = (String) table.getValueAt(row, 0);
            Order order = orders.get(orderId);

            int totalItems = order != null ? orders.get(orderId).getTotalItemCount() : 0;
            int workDoneCount = order != null ? order.getWorkDoneCount() : 0;
            int progressValue = totalItems > 0 ? (workDoneCount * 100) / totalItems : 0;

            progressBar.setValue(progressValue);

            // Customize the appearance based on the progress value
            if (progressValue < 100) {
                progressBar.setForeground(new Color(255,204,102));
            } else {
                progressBar.setForeground(new Color(0,204,102));
            }

            return progressBar;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new oodj.Dashboard.swing.RoundPanel();
        jPanel1 = new oodj.Dashboard.swing.RoundPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        searchBar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnViewStatusDetails = new oodj.Dashboard.swing.CustomButton();
        comboboxStatus = new oodj.Dashboard.swing.Combobox();

        roundPanel1.setBackground(new java.awt.Color(245, 240, 228));

        jPanel1.setBackground(new java.awt.Color(102, 66, 41));

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Product Status");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Order ID", "SalesPerson", "Date & TIme", "Progress", "", "Action"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class
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
        jTable1.setRowHeight(40);
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
        }

        searchBar.setForeground(new java.awt.Color(153, 153, 153));
        searchBar.setText("Search Order ID");
        searchBar.setName(""); // NOI18N
        searchBar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                searchBarFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                searchBarFocusLost(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/oodj/Dashboard/icon/search.png"))); // NOI18N

        btnViewStatusDetails.setBorder(null);
        btnViewStatusDetails.setText("View Status Details");
        btnViewStatusDetails.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        btnViewStatusDetails.setRadius(40);
        btnViewStatusDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewStatusDetailsActionPerformed(evt);
            }
        });

        comboboxStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All Status", "In Progress", "WorkDone" }));
        comboboxStatus.setSelectedIndex(-1);
        comboboxStatus.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        comboboxStatus.setLabeText("Status");
        comboboxStatus.setLightWeightPopupEnabled(false);
        comboboxStatus.setLineColor(new java.awt.Color(255, 255, 255));
        comboboxStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboboxStatusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(413, 413, 413)
                        .addComponent(btnViewStatusDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addComponent(comboboxStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(searchBar, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel1)
                                .addGap(338, 338, 338))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 976, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchBar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboboxStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnViewStatusDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnViewStatusDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewStatusDetailsActionPerformed
        // Get the selected row index
        int selectedRow = jTable1.getSelectedRow();

        if (selectedRow != -1) {
            // Retrieve data from the selected row
            String orderId = (String) jTable1.getValueAt(selectedRow, 0);
            Order order = orders.get(orderId);
            String status = (String) jTable1.getValueAt(selectedRow, 3); // Assuming status is in the 4th column
            int workDoneCount = orders.get(orderId).getWorkDoneCount();
            int totalItems = orders.get(orderId).getTotalItemCount();
            int progressValue = totalItems > 0 ? (workDoneCount * 100) / totalItems : 0;

            // Pass the data to the Product_Status_View.java class
            Product_Status_View productStatusView = new Product_Status_View(orderId, status, progressValue);
            // Show the Product_Status_View frame or perform other actions as needed
            productStatusView.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to view details.", "No Row Selected", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnViewStatusDetailsActionPerformed

    private void comboboxStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboboxStatusActionPerformed
        selectedStatus = (String) comboboxStatus.getSelectedItem();
        filterTable();
    }//GEN-LAST:event_comboboxStatusActionPerformed

    private void searchBarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchBarFocusGained
        if (searchBar.getText().equals("Search Order ID")){
            searchBar.setText("");
            searchBar.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_searchBarFocusGained

    private void searchBarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchBarFocusLost
        if (searchBar.getText().equals("")){
            searchBar.setText("Search Order ID");
            searchBar.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_searchBarFocusLost



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private oodj.Dashboard.swing.CustomButton btnViewStatusDetails;
    private oodj.Dashboard.swing.Combobox comboboxStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private oodj.Dashboard.swing.RoundPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private oodj.Dashboard.swing.RoundPanel roundPanel1;
    private javax.swing.JTextField searchBar;
    // End of variables declaration//GEN-END:variables
}
