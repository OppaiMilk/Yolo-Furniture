package oodj.Dashboard.form;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import oodj.Dashboard.event.SaleRecord;
import oodj.Dashboard.swing.TableCustom;

public class ReportGeneration extends javax.swing.JPanel {
    
    private static List<SaleRecord> saleRecords;
    private static String[] months = {"Annual", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    private String staffRoles;
    private String staffName;
    
    public ReportGeneration(String staffRoles, String staffName) {
        initComponents();
        setOpaque(false);
        this.staffRoles = staffRoles;
        this.staffName = staffName;
        TableCustom.apply(jScrollPane1, TableCustom.TableType.DEFAULT);
        TableCustom.apply(jScrollPane2, TableCustom.TableType.DEFAULT);

        updateTable();
    }

    private void updateTable(){
        DefaultTableModel workdoneModel = (DefaultTableModel) workdoneJTable.getModel();
        DefaultTableModel approvedModel = (DefaultTableModel) approvedJTable.getModel();
        
        // Clear existing data in tables
        workdoneModel.setRowCount(0);
        approvedModel.setRowCount(0);
    
        try {
            BufferedReader br = new BufferedReader(new FileReader("order.txt"));
            String line;

            Set<String> approvedOrderIdsProcessed = new HashSet<>();
            Set<String> workdoneOrderIdsProcessed = new HashSet<>();

            String selectedMonth = (String) monthComboBox.getSelectedItem();
            String selectedYear = (String) yearComboBox.getSelectedItem();

            while ((line = br.readLine()) != null) {
                String[] orderParts = line.split(",");
                if (orderParts.length == 10){
                    String orderId = orderParts[0];
                    String orderStatus = orderParts[8];
                    String orderType = orderParts[9];
                    String orderDate = orderParts[6];

                    boolean isMatchingData = isMatchingData(orderDate, selectedMonth, selectedYear);

                    if (isMatchingData) {
                        if ("Approved".equals(orderStatus)) {
                            if (approvedOrderIdsProcessed.add(orderId)) {
                                Object[] rowData = {
                                        orderId, orderParts[1], orderParts[2], orderParts[3],
                                        orderParts[4], orderParts[5], orderParts[6], orderParts[7]
                                };
                                approvedModel.addRow(rowData);
                            } else {
                                // For subsequent rows with the same Order ID, leave the Order ID empty
                                Object[] rowData = {
                                        "", orderParts[1], orderParts[2], orderParts[3],
                                        orderParts[4], orderParts[5], orderParts[6], orderParts[7]
                                };
                                approvedModel.addRow(rowData);
                            }
                        }

                        if ("WorkDone".equals(orderType)) {
                            if (isAllItemsWorkDone(orderId)) {
                                if (workdoneOrderIdsProcessed.add(orderId)) {
                                    Object[] rowData = {
                                            orderId, orderParts[1], orderParts[2], orderParts[3],
                                            orderParts[4], orderParts[5], orderParts[6], orderParts[7]
                                    };
                                    workdoneModel.addRow(rowData);
                                } else {
                                    // For subsequent rows with the same Order ID, leave the Order ID empty
                                    Object[] rowData = {
                                            "", orderParts[1], orderParts[2], orderParts[3],
                                            orderParts[4], orderParts[5], orderParts[6], orderParts[7]
                                    };
                                    workdoneModel.addRow(rowData);
                                }
                            }
                        }
                    }
                }
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Add this code to adjust column widths and center align data
        adjustColumnProperties(workdoneJTable);
        adjustColumnProperties(approvedJTable);
    }
    
    private static boolean isAllItemsWorkDone(String orderId) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("order.txt"));
            String line;

            while ((line = br.readLine()) != null) {
                String[] orderParts = line.split(",");
                if (orderParts.length == 10){
                    String currentOrderId = orderParts[0];
                    String orderType = orderParts[9];

                    if (orderId.equals(currentOrderId) && !"WorkDone".equals(orderType)) {
                        // If any item in the order has a status other than "WorkDone", return false
                        br.close();
                        return false;
                    }
                }
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // All items have "WorkDone" status for this order
        return true;
    }
    
    private boolean isMatchingData(String orderDate, String selectedMonth, String selectedYear) {
        if ("Annual".equals(selectedMonth) && "All Year".equals(selectedYear)) {
            return true; // Display all data
        } else if ("Annual".equals(selectedMonth) && !"All Year".equals(selectedYear)) {
            return isMatchingYear(orderDate, selectedYear); // Display data for the selected year
        } else if (!"Annual".equals(selectedMonth) && "All Year".equals(selectedYear)) {
            return isMatchingMonth(orderDate, selectedMonth); // Display data for the selected year
        } else {
            return isMatchingMonthYear(orderDate, selectedMonth, selectedYear); // Display data for the selected month and year
        }
    }

    private boolean isMatchingYear(String orderDate, String selectedYear) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        try {
            Date date = dateFormat.parse(orderDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int year = calendar.get(Calendar.YEAR);

            return selectedYear.equals(Integer.toString(year));
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    private boolean isMatchingMonth(String orderDate, String selectedMonth) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        try {
            Date date = dateFormat.parse(orderDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int month = calendar.get(Calendar.MONTH);
            int selectedMonthNumeric = Month.valueOf(selectedMonth.toUpperCase()).getValue();

            return month == selectedMonthNumeric - 1;    
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean isMatchingMonthYear(String orderDate, String selectedMonth, String selectedYear) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        try {
            Date date = dateFormat.parse(orderDate);

            // Use Calendar class to get month and year
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int month = calendar.get(Calendar.MONTH); // Month is 0-based in Calendar class
            int year = calendar.get(Calendar.YEAR);

            // Convert the selected month to its corresponding numeric value
            int selectedMonthNumeric = Month.valueOf(selectedMonth.toUpperCase()).getValue();

            return month == selectedMonthNumeric - 1 && selectedYear.equals(Integer.toString(year));
        } catch (ParseException e) {
            // Log the error instead of just printing the stack trace
            return false;
        }
    }

    private void adjustColumnProperties(javax.swing.JTable table) {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

        for (int columnIndex = 0; columnIndex < table.getColumnCount(); columnIndex++) {
            TableColumn column = table.getColumnModel().getColumn(columnIndex);
            int maxWidth = 0;

            for (int rowIndex = 0; rowIndex < table.getRowCount(); rowIndex++) {
                Object value = table.getValueAt(rowIndex, columnIndex);
                int cellWidth = table.getFontMetrics(table.getFont()).stringWidth(value.toString());
                maxWidth = Math.max(maxWidth, cellWidth);
            }

            column.setPreferredWidth(maxWidth + 10); // Add some padding
            column.setCellRenderer(centerRenderer);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new oodj.Dashboard.swing.RoundPanel();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        workdoneJTable = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        approvedJTable = new javax.swing.JTable();
        btnGenerateReport = new oodj.Dashboard.swing.CustomButton();
        monthComboBox = new oodj.Dashboard.swing.Combobox();
        yearComboBox = new oodj.Dashboard.swing.Combobox();

        roundPanel1.setBackground(new java.awt.Color(245, 240, 228));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(103, 74, 2));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Report Generation");

        workdoneJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Order ID", "Item ID", "Item Name", "Quantity", "Price", "Total Price", "Date & Time", "Salesperson"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        workdoneJTable.setRowHeight(40);
        jScrollPane2.setViewportView(workdoneJTable);

        jTabbedPane1.addTab("Work Done Order", jScrollPane2);

        approvedJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Order ID", "Item ID", "Item Name", "Quantity", "Price", "Total Price", "Date & Time", "Salesperson"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        approvedJTable.setRowHeight(40);
        jScrollPane1.setViewportView(approvedJTable);

        jTabbedPane1.addTab("Approved Order", jScrollPane1);

        btnGenerateReport.setBorder(null);
        btnGenerateReport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/oodj/Dashboard/icon/printReport.png"))); // NOI18N
        btnGenerateReport.setText("Generate Report");
        btnGenerateReport.setBorderColor(new java.awt.Color(0, 153, 204));
        btnGenerateReport.setColorClick(new java.awt.Color(0, 153, 153));
        btnGenerateReport.setColorOver(new java.awt.Color(0, 204, 255));
        btnGenerateReport.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnGenerateReport.setIconTextGap(15);
        btnGenerateReport.setRadius(45);
        btnGenerateReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerateReportActionPerformed(evt);
            }
        });

        monthComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Annual", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        monthComboBox.setLabeText("");
        monthComboBox.setLineColor(new java.awt.Color(255, 255, 255));
        monthComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monthComboBoxActionPerformed(evt);
            }
        });

        yearComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2024", "2023" }));
        yearComboBox.setLabeText("");
        yearComboBox.setLineColor(new java.awt.Color(255, 255, 255));
        yearComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yearComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(361, 361, 361)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 958, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                        .addComponent(btnGenerateReport, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(384, 384, 384))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                        .addComponent(monthComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(yearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42))))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(yearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(monthComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnGenerateReport, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("Approved");

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

    private void btnGenerateReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerateReportActionPerformed
        // Check if the "Approved" tab is selected
        if (jTabbedPane1.getSelectedIndex() == jTabbedPane1.indexOfTab("Approved Order")) {
            String selectedMonth = (String) monthComboBox.getSelectedItem();
            String selectedYear = (String) yearComboBox.getSelectedItem();
            saleRecords = readApprovedSaleRecordsFromFile("order.txt");
            if ("Annual".equals(selectedMonth)) {
                // Generate report for all months of the selected year
                Approved_Report approvedReportGenerator = new Approved_Report(saleRecords);
                approvedReportGenerator.generateReportForSelectedYear(selectedYear, selectedMonth, staffRoles, staffName);
            } else {
                // Generate report for the selected month of the selected year
                Approved_Report approvedReportGenerator = new Approved_Report(saleRecords);
                approvedReportGenerator.generateReportForSelectedMonth(selectedMonth, selectedYear, staffRoles, staffName);
            }
        } else if (jTabbedPane1.getSelectedIndex() == jTabbedPane1.indexOfTab("Work Done Order")) {
            String selectedMonth = (String) monthComboBox.getSelectedItem();
            String selectedYear = (String) yearComboBox.getSelectedItem();
            saleRecords = readWorkDoneSaleRecordsFromFile("order.txt");
            if ("Annual".equals(selectedMonth)) {
                // Generate report for all months of the selected year
                Workdone_Report workdoneReportGenerator = new Workdone_Report(saleRecords);
                workdoneReportGenerator.generateReportForSelectedYear(selectedYear, selectedMonth, staffRoles, staffName);
            } else {
                // Generate report for the selected month of the selected year
                Workdone_Report workdoneReportGenerator = new Workdone_Report(saleRecords);
                workdoneReportGenerator.generateReportForSelectedMonth(selectedMonth, selectedYear, staffRoles, staffName);
            }
        }
    }//GEN-LAST:event_btnGenerateReportActionPerformed

    private void monthComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monthComboBoxActionPerformed
        updateTable();
    }//GEN-LAST:event_monthComboBoxActionPerformed

    private void yearComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yearComboBoxActionPerformed
        updateTable();
    }//GEN-LAST:event_yearComboBoxActionPerformed

   private static List<SaleRecord> readApprovedSaleRecordsFromFile(String filePath) {
       List<SaleRecord> saleRecords = new ArrayList<>();

       try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
           String line;
           while ((line = reader.readLine()) != null) {
               String[] data = line.split(",");

               if (data.length == 10 && "Approved".equals(data[8])) {
                   String orderId = data[0];
                   String dateTime = data[6];
                   String salespersonName = data[7];

                   // Check if a sale record with the same order ID, salesperson name, and date & time already exists
                   SaleRecord existingRecord = findExistingRecord(saleRecords, orderId, salespersonName, dateTime);

                   if (existingRecord != null) {
                       // Update the existing sale record
                       SaleRecord.Item item = new SaleRecord.Item();
                       item.setItemId(data[1]);
                       item.setItemName(data[2]);
                       item.setItemQuantity(Integer.parseInt(data[3]));
                       item.setItemPrice(Double.parseDouble(data[4]));
                       item.setTotalPrice(Double.parseDouble(data[5]));

                       existingRecord.getItems().add(item);
                   } else {
                       // Create a new sale record
                       SaleRecord saleRecord = new SaleRecord();
                       saleRecord.setOrderId(orderId);
                       saleRecord.setDateTime(dateTime);
                       saleRecord.setSalespersonName(salespersonName);

                       SaleRecord.Item item = new SaleRecord.Item();
                       item.setItemId(data[1]);
                       item.setItemName(data[2]);
                       item.setItemQuantity(Integer.parseInt(data[3]));
                       item.setItemPrice(Double.parseDouble(data[4]));
                       item.setTotalPrice(Double.parseDouble(data[5]));

                       saleRecord.getItems().add(item);
                       saleRecords.add(saleRecord);
                   }
               }
           }
       } catch (IOException e) {
           e.printStackTrace();
       }

       return saleRecords;
   }
   
    private static List<SaleRecord> readWorkDoneSaleRecordsFromFile(String filePath) {
        List<SaleRecord> saleRecords = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                if (data.length == 10 && "WorkDone".equals(data[9])) {
                    String orderId = data[0];
                    String dateTime = data[6];
                    String salespersonName = data[7];

                    // Check if a sale record with the same order ID, salesperson name, and date & time already exists
                    SaleRecord existingRecord = findExistingRecord(saleRecords, orderId, salespersonName, dateTime);

                    if (existingRecord != null) {
                        // Update the existing sale record
                        SaleRecord.Item item = new SaleRecord.Item();
                        item.setItemId(data[1]);
                        item.setItemName(data[2]);
                        item.setItemQuantity(Integer.parseInt(data[3]));
                        item.setItemPrice(Double.parseDouble(data[4]));
                        item.setTotalPrice(Double.parseDouble(data[5]));

                        existingRecord.getItems().add(item);
                    } else {
                        // Create a new sale record
                        SaleRecord saleRecord = new SaleRecord();
                        saleRecord.setOrderId(orderId);
                        saleRecord.setDateTime(dateTime);
                        saleRecord.setSalespersonName(salespersonName);

                        SaleRecord.Item item = new SaleRecord.Item();
                        item.setItemId(data[1]);
                        item.setItemName(data[2]);
                        item.setItemQuantity(Integer.parseInt(data[3]));
                        item.setItemPrice(Double.parseDouble(data[4]));
                        item.setTotalPrice(Double.parseDouble(data[5]));

                        saleRecord.getItems().add(item);

                        // Check if all items in the order have "WorkDone" status
                        if (isAllItemsWorkDone(orderId)) {
                            saleRecords.add(saleRecord);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return saleRecords;
    }


   private static SaleRecord findExistingRecord(List<SaleRecord> saleRecords, String orderId, String salespersonName, String dateTime) {
        for (SaleRecord record : saleRecords) {
            if (record.getOrderId().equals(orderId) && record.getSalespersonName().equals(salespersonName) && record.getDateTime().equals(dateTime)) {
                return record;
            }
        }
        return null;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable approvedJTable;
    private oodj.Dashboard.swing.CustomButton btnGenerateReport;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private oodj.Dashboard.swing.Combobox monthComboBox;
    private oodj.Dashboard.swing.RoundPanel roundPanel1;
    private javax.swing.JTable workdoneJTable;
    private oodj.Dashboard.swing.Combobox yearComboBox;
    // End of variables declaration//GEN-END:variables
}
