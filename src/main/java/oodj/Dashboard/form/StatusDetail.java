package oodj.Dashboard.form;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import oodj.Dashboard.swing.TableCustom;

public class StatusDetail extends javax.swing.JFrame {
    public static String OrderId;
    
    
    //Show Detail 
    public StatusDetail(String orderId, String salesPerson, String date , String status) {
        
        initComponents();
        loadTableData("order.txt",orderId);
        adjustColumnWidths();
        TableCustom.apply(jScrollPane1, TableCustom.TableType.DEFAULT);
//        updateButtonVisible(status);
        //Read order total price and total qty
        String totalprice= String.format("%.2f",ReadTablePrice());
        String totalqty = String.valueOf(ReadTableQty());
        
        //form variable
        Order_Id.setText(orderId);
        SalesPerson.setText(salesPerson);
        Date.setText(date);
        totalPrice.setText("RM"+totalprice);
        totalQty.setText(totalqty);
        Status.setText(status);
        
        // Global varible
        OrderId=orderId;
        
    }
    
    private double ReadTablePrice(){
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int rowCount = model.getRowCount();
        double totalPrice = 0.0;

        for (int i = 0; i < rowCount; i++) {
            // 获取价格列的索引，这里假设价格在第4列
            Object priceObject = model.getValueAt(i, 3);

            // 将获取到的对象转换为字符串，然后去掉"RM"，再转换为数字类型
            if (priceObject instanceof String) {
                String priceString = (String) priceObject;
                priceString = priceString.replace("RM", "").trim();

                // 将获取到的字符串转换为数字类型，然后相加
                double price = Double.parseDouble(priceString);
                totalPrice += price;
            }
        }
        return totalPrice;
    }
    
    private int ReadTableQty(){
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int rowCount = model.getRowCount();
        int totalQty = 0;

        for (int i = 0; i < rowCount; i++) {
            // 获取价格列的索引，这里假设价格在第4列
            Object priceObject = model.getValueAt(i, 4);

            // 将获取到的对象转换为字符串，然后去掉"RM"，再转换为数字类型
            if (priceObject instanceof String) {
                String qtyString = (String) priceObject;
                qtyString = qtyString.replace("RM", "").trim();

                // 将获取到的字符串转换为数字类型，然后相加
                int price = Integer.parseInt(qtyString);
                totalQty += price;
            }
        }
        return totalQty;
    }
    
    private void loadTableData(String filePath,String OrderID) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int i=1;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 4 && data[0].equalsIgnoreCase(OrderID)) {
                    // Check if the same data is already in the table
                        Object[] row = {i,data[1], data[2], ("RM"+data[4]), data[3],("RM"+data[5])};
                        model.addRow(row);
                        i+=1;
                } else {
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
}
    
    private void adjustColumnWidths() {
        jTable1.getTableHeader().setResizingAllowed(false);
        TableColumnModel columnModel = jTable1.getColumnModel();

        // 设置各列的首选宽度
        columnModel.getColumn(0).setPreferredWidth(2);
        columnModel.getColumn(1).setPreferredWidth(40);
        columnModel.getColumn(2).setPreferredWidth(130);
        columnModel.getColumn(3).setPreferredWidth(40);
        columnModel.getColumn(4).setPreferredWidth(5);
        columnModel.getColumn(5).setPreferredWidth(40);

        // 设置各列的居中渲染器
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

        columnModel.getColumn(0).setCellRenderer(centerRenderer);
        columnModel.getColumn(1).setCellRenderer(centerRenderer);
        columnModel.getColumn(2).setCellRenderer(centerRenderer);
        columnModel.getColumn(3).setCellRenderer(centerRenderer);
        columnModel.getColumn(4).setCellRenderer(centerRenderer);
        columnModel.getColumn(5).setCellRenderer(centerRenderer);
    }
    
    private void OpenPdf(String orderId){
        String filePath = "Invoice/"+orderId+".pdf";
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
    }


    private void OpenFolder(){
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
    }

    
//    private void updateButtonVisible(String Status){
//        if ("Approved".equals(Status)){
//            pdfBtn.setVisible(true);
//            folderBtn.setVisible(true);
//        }else{
//            pdfBtn.setVisible(false);           
//            folderBtn.setVisible(false);
//        }
//        
//    }
    
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backgroundPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        TotalPriceLabel = new javax.swing.JLabel();
        totalQty = new javax.swing.JLabel();
        totalPrice = new javax.swing.JLabel();
        Order_Id = new javax.swing.JLabel();
        TotalQuantityLabel = new javax.swing.JLabel();
        OrderIDLabel = new javax.swing.JLabel();
        SalesPerson = new javax.swing.JLabel();
        SalePersonLabel = new javax.swing.JLabel();
        DateLabel = new javax.swing.JLabel();
        Date = new javax.swing.JLabel();
        TotalPriceLabel1 = new javax.swing.JLabel();
        Status = new javax.swing.JLabel();
        pdfBtn = new javax.swing.JButton();
        folderBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Product Detail");
        setResizable(false);

        backgroundPanel.setBackground(new java.awt.Color(245, 240, 228));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "No", "Iteam ID", "Furniture", "Unity Price", "Quantity", "Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false
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

        TotalPriceLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        TotalPriceLabel.setText("Total Price       :");

        totalQty.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        totalPrice.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        Order_Id.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        TotalQuantityLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        TotalQuantityLabel.setText("Total Quantity :");

        OrderIDLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        OrderIDLabel.setText("Order ID :");

        SalesPerson.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        SalePersonLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        SalePersonLabel.setText("Sales Person :");

        DateLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        DateLabel.setText("Date :");

        Date.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        TotalPriceLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        TotalPriceLabel1.setText("Status               :");

        Status.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        pdfBtn.setText("Open Invoice PDF");
        pdfBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pdfBtnActionPerformed(evt);
            }
        });

        folderBtn.setText("Open Invoice Folder");
        folderBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                folderBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout backgroundPanelLayout = new javax.swing.GroupLayout(backgroundPanel);
        backgroundPanel.setLayout(backgroundPanelLayout);
        backgroundPanelLayout.setHorizontalGroup(
            backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backgroundPanelLayout.createSequentialGroup()
                        .addComponent(OrderIDLabel)
                        .addGap(18, 18, 18)
                        .addComponent(Order_Id, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(SalePersonLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(SalesPerson, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(DateLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Date, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE))
                    .addComponent(jScrollPane1)
                    .addGroup(backgroundPanelLayout.createSequentialGroup()
                        .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(TotalPriceLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TotalQuantityLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TotalPriceLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(backgroundPanelLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(totalQty, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundPanelLayout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Status, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(totalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(folderBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                            .addComponent(pdfBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        backgroundPanelLayout.setVerticalGroup(
            backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DateLabel)
                    .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(OrderIDLabel)
                        .addComponent(SalePersonLabel))
                    .addComponent(Date, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Order_Id, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SalesPerson, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(TotalQuantityLabel)
                    .addComponent(totalQty, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(TotalPriceLabel)
                        .addComponent(totalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pdfBtn))
                .addGap(12, 12, 12)
                .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TotalPriceLabel1)
                    .addComponent(Status, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(folderBtn))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backgroundPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backgroundPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void pdfBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pdfBtnActionPerformed
        // TODO add your handling code here:
        OpenPdf(OrderId);
        dispose();
    }//GEN-LAST:event_pdfBtnActionPerformed

    private void folderBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_folderBtnActionPerformed
        // TODO add your handling code here:
        OpenFolder();
        dispose();
    }//GEN-LAST:event_folderBtnActionPerformed

    
    
    
    
    
    
//    public static void main(String args[]) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//            new Product_Detail("123", "Sofa", "Furniture", "Designer Name",
//                                    "A comfortable sofa is very nice and nice and nice nice.", "Large", "$599.99", "/oodj/Dashboard/icon/Bar Furniture.jpg").setVisible(true);            }
//                    });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Date;
    private javax.swing.JLabel DateLabel;
    private javax.swing.JLabel OrderIDLabel;
    private javax.swing.JLabel Order_Id;
    private javax.swing.JLabel SalePersonLabel;
    private javax.swing.JLabel SalesPerson;
    private javax.swing.JLabel Status;
    private javax.swing.JLabel TotalPriceLabel;
    private javax.swing.JLabel TotalPriceLabel1;
    private javax.swing.JLabel TotalQuantityLabel;
    private javax.swing.JPanel backgroundPanel;
    private javax.swing.JButton folderBtn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton pdfBtn;
    private javax.swing.JLabel totalPrice;
    private javax.swing.JLabel totalQty;
    // End of variables declaration//GEN-END:variables
}
