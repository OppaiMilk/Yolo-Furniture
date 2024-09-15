package oodj.Dashboard.form;

import java.awt.Desktop;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import oodj.Dashboard.event.TableActionEvent_SaleForm;
import oodj.Dashboard.event.TableInfoEditor_SaleForm;
import oodj.Dashboard.event.TableInfoRender;

public class productStatus extends javax.swing.JPanel {

    private TableRowSorter<DefaultTableModel> sorter;

    
    public productStatus() {
        initComponents();
        setOpaque(false);

        loadTableData("order.txt");
        adjustColumnWidths();
        setupTableSorter();
        addTableFilterListener();

        TableActionEvent_SaleForm event = new TableActionEvent_SaleForm() {
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
        
            
        
        jTable1.getColumnModel().getColumn(4).setCellRenderer(new TableInfoRender());
        jTable1.getColumnModel().getColumn(4).setCellEditor(new TableInfoEditor_SaleForm(event));
    }
    
    
    private void setupTableSorter() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        sorter = new TableRowSorter<>(model);
        jTable1.setRowSorter(sorter);
    }

    private String getSelectedCategory() {
        return filterStatus.getSelectedItem().toString();
    }
   
    
    private void addTableFilterListener() {
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
                // not needed for plain text fields
            }
        });
        
        filterStatus.addItemListener(new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                filterTable();
                
            }
        }
    });
    }

    private void filterTable() {
        //variable filters
        ArrayList<RowFilter<DefaultTableModel, Object>> filters = new ArrayList<>();
        
        //search bar
        RowFilter<DefaultTableModel, Object> rowFilter = RowFilter.regexFilter("(?i)" + searchBar.getText());
        filters.add(rowFilter);
        
        //filter categary
        RowFilter<DefaultTableModel, Object> statusFilter = RowFilter.regexFilter(getSelectedCategory(), 3);
        filters.add(statusFilter);
        
        //mix search and filter
        RowFilter<DefaultTableModel, Object> combinedFilter = RowFilter.andFilter(filters);
        sorter.setRowFilter(combinedFilter);
    }
    
    
    private String[] readDetailsFromFile(String orderID, String filePath) {
        String[] details = new String[3];  // Assuming there are 3 details: designer, description, size

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 9 && parts[0].equals(orderID)) {
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
    
    private void loadTableData(String filePath) {
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    model.setRowCount(0);

    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            if (data.length >= 4 ) {
                // Check if the same data is already in the table
                if (!containsRow(model, data[0], data[7], data[6], data[8])) {
                    Object[] row = {data[0], data[7], data[6], data[8]};
                    model.addRow(row);
                }
            } else {
                System.err.println("Invalid data format: " + line);
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    private boolean containsRow(DefaultTableModel model, String col1, String col2, String col3, String col4) {
    int rowCount = model.getRowCount();
    for (int i = 0; i < rowCount; i++) {
        if (col1.equals(model.getValueAt(i, 0)) && 
            col2.equals(model.getValueAt(i, 1)) &&
            col3.equals(model.getValueAt(i, 2)) &&
            col4.equals(model.getValueAt(i, 3))) {
            return true;  // The row is already in the table
        }
    }
    return false;  // The row is not in the table
}
    
    private void adjustColumnWidths() {
        jTable1.getTableHeader().setResizingAllowed(false);
        TableColumnModel columnModel = jTable1.getColumnModel();

        // 设置各列的首选宽度
        columnModel.getColumn(0).setPreferredWidth(5);
        columnModel.getColumn(1).setPreferredWidth(40);
        columnModel.getColumn(2).setPreferredWidth(120);
        columnModel.getColumn(3).setPreferredWidth(130);
        columnModel.getColumn(4).setPreferredWidth(40);

        // 设置各列的居中渲染器
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

        columnModel.getColumn(0).setCellRenderer(centerRenderer);
        columnModel.getColumn(1).setCellRenderer(centerRenderer);
        columnModel.getColumn(2).setCellRenderer(centerRenderer);
        columnModel.getColumn(3).setCellRenderer(centerRenderer);
        columnModel.getColumn(4).setCellRenderer(centerRenderer);
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new oodj.Dashboard.swing.RoundPanel();
        jPanel1 = new oodj.Dashboard.swing.RoundPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        searchBar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        filterLabel = new javax.swing.JLabel();
        filterStatus = new javax.swing.JComboBox<>();

        roundPanel1.setBackground(new java.awt.Color(245, 240, 228));

        jPanel1.setBackground(new java.awt.Color(102, 66, 41));

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 0, 40)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Review Invoices");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel2)
                .addContainerGap(653, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel2)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/oodj/Dashboard/icon/search.png"))); // NOI18N

        searchBar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        searchBar.setMinimumSize(new java.awt.Dimension(64, 25));
        searchBar.setName(""); // NOI18N
        searchBar.setPreferredSize(new java.awt.Dimension(71, 25));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Order ID", "Sales Person", "Date", "Status", "Action"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
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

        jButton1.setText("Open Invoice Folder");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        filterLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        filterLabel.setText("Filter :");

        filterStatus.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        filterStatus.setMaximumRowCount(18);
        filterStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All Status", "Approved", "UnApproved" }));
        filterStatus.setPreferredSize(new java.awt.Dimension(226, 25));
        filterStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterStatusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(filterLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filterStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(searchBar, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addContainerGap())
            .addComponent(jScrollPane1)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(355, 355, 355)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(filterStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(filterLabel))
                    .addComponent(searchBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jButton1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String filePath = "Invoice/";
        try {
            Desktop desktop = Desktop.getDesktop();

            if (desktop.isSupported(Desktop.Action.OPEN)) {  
                File pdfFile = new File(filePath);  
                desktop.open(pdfFile);
            } else {
                JOptionPane.showMessageDialog(this, "Didn't support open.", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void filterStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterStatusActionPerformed
        filterTable();
    }//GEN-LAST:event_filterStatusActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel filterLabel;
    private javax.swing.JComboBox<String> filterStatus;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private oodj.Dashboard.swing.RoundPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private oodj.Dashboard.swing.RoundPanel roundPanel1;
    private javax.swing.JTextField searchBar;
    // End of variables declaration//GEN-END:variables
}
