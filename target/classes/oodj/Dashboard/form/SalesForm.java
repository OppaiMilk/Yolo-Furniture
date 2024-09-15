package oodj.Dashboard.form;

import oodj.Dashboard.form.SaleFormDetail.DataRefreshCallback;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import oodj.Dashboard.event.TableActionEvent_SaleForm;
import oodj.Dashboard.event.TableInfoEditor_SaleForm;
import oodj.Dashboard.event.TableManufactureRender;
import oodj.Dashboard.swing.TableCustom;

public class SalesForm extends javax.swing.JPanel {
    private TableInfoEditor_SaleForm tableEditor;
    
    public SalesForm() {
        initComponents();
        setOpaque(false);
        
        loadTableData("order.txt");
        adjustColumnWidths();
        TableCustom.apply(jScrollPane1, TableCustom.TableType.DEFAULT);
        
        TableActionEvent_SaleForm event = new TableActionEvent_SaleForm() {
            @Override
            public void onViewDetail(int row) {
                if (jTable1.isEditing()) {
                    jTable1.getCellEditor().stopCellEditing();
                }//Ensure that when viewing the order details, there are no issues with the edited state.
                
                    String orderID = jTable1.getValueAt(row,0).toString();
                    String SalesPerson= jTable1.getValueAt(row, 1).toString();
                    
                    String filePath = "order.txt";
                    String[] details = readDetailsFromFile(orderID, filePath);


                    String date = details[2];

                    
                    SaleFormDetail detail = new SaleFormDetail(orderID,SalesPerson,date);
                    
                    detail.setDataRefreshCallback(new DataRefreshCallback() {
                    @Override
                    public void onDataRefresh() {
                        SwingUtilities.invokeLater(() -> {
                            // In here add SalesForm to update the table 
                            loadTableData("order.txt");
                            adjustColumnWidths();

                            jTable1.getColumnModel().getColumn(4).setCellRenderer(new TableManufactureRender());
                            jTable1.getColumnModel().getColumn(4).setCellEditor(tableEditor);
                        });
                    }
                    });
                    
                    detail.setVisible(true);
            }         
        };
        
            
        
        jTable1.getColumnModel().getColumn(4).setCellRenderer(new TableManufactureRender());
        jTable1.getColumnModel().getColumn(4).setCellEditor(new TableInfoEditor_SaleForm(event));

        
    
    }

                
                
                
private void loadTableData(String filePath) {
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    model.setRowCount(0);

    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            if (data.length >=9&& data[8].equalsIgnoreCase("Approved")) {
                // Check if the same data is already in the table
                if (!containsRow(model, data[0], data[7], data[6], data[8])) {
                    Object[] row = {data[0], data[7], data[6], data[8]};
                    model.addRow(row);
                }
            } else {
//                System.err.println("Order has been (Approved/Declined): " + line);
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
} //make sure the table have the same detail
    
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
    
private String[] readDetailsFromFile(String orderID, String filePath) {
        String[] details = new String[3];  // Assuming there are 3 details: designer, description, size

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 9 || parts.length >=10 && parts[0].equals(orderID)) {
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
    } //return detail Array 
    

    /*  
    private String constructSizeString(String part7, String part8, String part9) {
        StringBuilder sizeBuilder = new StringBuilder();

        if (!part7.isEmpty()) {
            sizeBuilder.append(part7);
        }

        if (!part8.isEmpty()) {
            if (sizeBuilder.length() > 0) {
                sizeBuilder.append(" X ");
            }
            sizeBuilder.append(part8);
        }

        if (!part9.isEmpty()) {
            if (sizeBuilder.length() > 0) {
                sizeBuilder.append(" X ");
            }
            sizeBuilder.append(part9);
        }

        return sizeBuilder.toString();
    }
    */
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new oodj.Dashboard.swing.RoundPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new oodj.Dashboard.swing.RoundPanel();
        jLabel2 = new javax.swing.JLabel();

        roundPanel1.setBackground(new java.awt.Color(245, 240, 228));

        jScrollPane1.setPreferredSize(new java.awt.Dimension(453, 403));

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
        jTable1.setRowHeight(45);
        jScrollPane1.setViewportView(jTable1);

        jPanel1.setBackground(new java.awt.Color(102, 66, 41));

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 0, 40)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Submit Sales & Invoice ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel2)
                .addContainerGap(507, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel2)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 964, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 544, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    
    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private oodj.Dashboard.swing.RoundPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private oodj.Dashboard.swing.RoundPanel roundPanel1;
    // End of variables declaration//GEN-END:variables
}
