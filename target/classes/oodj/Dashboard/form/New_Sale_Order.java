package oodj.Dashboard.form;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import oodj.Dashboard.event.TableInfoEditor;
import oodj.Dashboard.event.TableActionEvent;
import oodj.Dashboard.event.TableAddOrderEditor;
import oodj.Dashboard.event.TableAddOrderRender;
import oodj.Dashboard.event.TableInfoRender;
import oodj.Dashboard.swing.TableCustom;


public class New_Sale_Order extends javax.swing.JPanel {
    
    private String staffName;
    private TableRowSorter<TableModel> rowSorter;
    
    public New_Sale_Order(String staffName) {
        initComponents();
        setOpaque(false);
        this.staffName = staffName;
        
        loadTableData("productList.txt");
        adjustColumnWidths();
        getInitialCartBadges(staffName);
        setupSearchBarListener();
        TableCustom.apply(jScrollPane1, TableCustom.TableType.DEFAULT);
        
        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onViewDetail(int row) {
                if (jTable1.isEditing()) {
                    jTable1.getCellEditor().stopCellEditing();
                }

                    String itemId = jTable1.getValueAt(row, 1).toString();
                    String furnitureName = jTable1.getValueAt(row, 2).toString();
                    String category = jTable1.getValueAt(row, 3).toString();
                    String price = jTable1.getValueAt(row, 4).toString();
                    
                    String filePath = "productList.txt";
                    String[] details = readDetailsFromFile(itemId, filePath);

                    String designer = details[0];
                    String description = details[1];
                    String size = details[2];
                    String imagePath = getCategoryImagePath(category);
                    
                    Product_Detail detail = new Product_Detail(itemId,furnitureName,category, designer, description, size, price,imagePath);
                    detail.setVisible(true);
            }
            
            private int quantityValue;
            @Override
            public void setQuantity(int row, int quantity) {
                this.quantityValue = quantity;
            }
            
            @Override
            public void onOrder(int row) {
                if (jTable1.isEditing()) {
                        jTable1.getCellEditor().stopCellEditing();
                    }
                if (quantityValue > 0){
                    storeInCart(row,quantityValue);
                    quantityValue = 0;
                    cart.setBadges(cart.getBadges() + 1);
                }else{
                    JOptionPane.showMessageDialog(New_Sale_Order.this, "No Quantity is Selected!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            
        };
        jTable1.getColumnModel().getColumn(0).setCellRenderer(new TableInfoRender());
        jTable1.getColumnModel().getColumn(0).setCellEditor(new TableInfoEditor(event));
        jTable1.getColumnModel().getColumn(5).setCellRenderer(new TableAddOrderRender());
        jTable1.getColumnModel().getColumn(5).setCellEditor(new TableAddOrderEditor(event));  
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new oodj.Dashboard.swing.RoundPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        cart = new oodj.Dashboard.swing.ButtonBadges();
        searchBar = new javax.swing.JTextField();
        filterCategory = new javax.swing.JComboBox<>();
        filterLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        roundPanel1.setBackground(new java.awt.Color(245, 240, 228));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Details", "Item ID", "Furniture Name", "Category", "Price", "Action"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, true
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

        cart.setBackground(new java.awt.Color(245, 240, 228));
        cart.setBorder(null);
        cart.setForeground(new java.awt.Color(204, 102, 0));
        cart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/oodj/Dashboard/icon/cart.png"))); // NOI18N
        cart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cartActionPerformed(evt);
            }
        });

        searchBar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        searchBar.setMinimumSize(new java.awt.Dimension(64, 25));
        searchBar.setName(""); // NOI18N
        searchBar.setPreferredSize(new java.awt.Dimension(71, 25));

        filterCategory.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        filterCategory.setMaximumRowCount(18);
        filterCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All Categories", "Bar furniture", "Beds", "Bookcases & shelving units", "Cabinets & cupboards", "Café furniture", "Chairs", "Chests of drawers & drawer units", "Children's furniture", "Nursery furniture", "Outdoor furniture", "Room dividers", "Sideboards, buffets & console tables", "Sofas & armchairs", "Tables & desks", "Trolleys", "TV & media furniture", "Wardrobes" }));
        filterCategory.setPreferredSize(new java.awt.Dimension(226, 25));

        filterLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        filterLabel.setText("Filter :");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/oodj/Dashboard/icon/search.png"))); // NOI18N

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 970, Short.MAX_VALUE))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(filterLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(filterCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(168, 168, 168)
                        .addComponent(searchBar, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cart, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                        .addComponent(cart, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(filterCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(filterLabel)
                                .addComponent(searchBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 613, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
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

    private void cartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cartActionPerformed
        OrderCart ordercart = new OrderCart(staffName);
        ordercart.setVisible(true);
    }//GEN-LAST:event_cartActionPerformed
    
    private void setupSearchBarListener() {
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
                filterTable();
            }
        });
        
        filterCategory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterTable();
            }
        });
    }
    
    private void adjustColumnWidths() {
        jTable1.getTableHeader().setResizingAllowed(false);
        TableColumnModel columnModel = jTable1.getColumnModel();

        columnModel.getColumn(0).setPreferredWidth(5);
        columnModel.getColumn(1).setPreferredWidth(40);
        columnModel.getColumn(2).setPreferredWidth(120);
        columnModel.getColumn(3).setPreferredWidth(130);
        columnModel.getColumn(4).setPreferredWidth(40);
        columnModel.getColumn(5).setPreferredWidth(100);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        
        columnModel.getColumn(0).setCellRenderer(centerRenderer);
        columnModel.getColumn(1).setCellRenderer(centerRenderer);
        columnModel.getColumn(2).setCellRenderer(centerRenderer);
        columnModel.getColumn(3).setCellRenderer(centerRenderer);
        columnModel.getColumn(4).setCellRenderer(centerRenderer);
        columnModel.getColumn(5).setCellRenderer(centerRenderer);
    }
    
    private void loadTableData(String filePath) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("@");
                if (data.length >= 4) {
                    Object[] row = new Object[model.getColumnCount()];

                    row[1] = data[1];
                    row[2] = data[2];
                    row[3] = data[3];
                    row[4] = "RM " + data[4];

                    model.addRow(row);
                    rowSorter = new TableRowSorter<>(jTable1.getModel());
                    jTable1.setRowSorter(rowSorter);
                } else {
                    // Handle the case where a line doesn't have enough columns
                    System.err.println("Invalid data format: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
  
    private String[] readDetailsFromFile(String itemId, String filePath) {
        String[] details = new String[3];  // Assuming there are 3 details: designer, description, size

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("@");
                if (parts.length >= 9 && parts[1].equals(itemId)) {
                    details[0] = parts[6];  // designer
                    details[1] = parts[5];  // description
                    details[2] = constructSizeString(parts[7], parts[8], parts[9]);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }

        return details;
    }
    
    private String getCategoryImagePath(String category) {
        String basePath = "/oodj/Dashboard/icon/";
        String imagePath = null;

        if ("Bar furniture".equals(category)) {
            imagePath = basePath + "Bar Furniture.jpg";
            return imagePath;
        } else if ("Beds".equals(category)) {
            imagePath = basePath + "Beds.png";
            return imagePath;
        } else if ("Bookcases & shelving units".equals(category)) {
            imagePath = basePath + "Bookcases.png";
            return imagePath;
        } else if ("Cabinets & cupboards".equals(category)) {
            imagePath = basePath + "Cabinet.png";
        } else if ("Café furniture".equals(category)) {
            imagePath = basePath + "Cafe Furniture.png";
        } else if ("Chairs".equals(category)) {
            imagePath = basePath + "Chairs.png";
        } else if ("Chests of drawers & drawer units".equals(category)) {
            imagePath = basePath + "Chests and Drawers.png";
        } else if ("Children's furniture".equals(category)) {
            imagePath = basePath + "Children Furniture.png";
        } else if ("Nursery furniture".equals(category)) {
            imagePath = basePath + "Nursery Furniture.png";
        } else if ("Outdoor furniture".equals(category)) {
            imagePath = basePath + "Outdoor Furniture.png";
        } else if ("Room dividers".equals(category)) {
            imagePath = basePath + "Room Dividers.png";
        } else if ("Sideboards, buffets & console tables".equals(category)) {
            imagePath = basePath + "Sideboards and Buffet.png";
        } else if ("Sofas & armchairs".equals(category)) {
            imagePath = basePath + "Sofa.png";
        } else if ("Tables & desks".equals(category)) {
            imagePath = basePath + "Table & Desk.png";
        } else if ("Trolleys".equals(category)) {
            imagePath = basePath + "Trolley.png";
        } else if ("TV & media furniture".equals(category)) {
            imagePath = basePath + "TV Furniture.png";
        } else {
            imagePath = basePath + "Wardrobe.jpg";
            return imagePath;
        }
        return imagePath;
    }

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
    
    private void storeInCart(int row, int quantityValue) {
        try {
            File file = new File("cart.txt");
            String itemId = jTable1.getValueAt(row, 1).toString();
            String furnitureName = jTable1.getValueAt(row, 2).toString();
            String priceWithRM = jTable1.getValueAt(row, 4).toString();
            String price = priceWithRM.replaceAll("[^\\d.]", "");
            int priceStr = Integer.parseInt(price);
            int totalPrice = priceStr * quantityValue;

            // Read existing content from the file
            List<String> lines = Files.readAllLines(file.toPath());

            boolean found = false;

            // Iterate through existing lines to find and update the record
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                String[] parts = line.split(",");
                
                if (parts.length >= 6) {
                    String existingItemId = parts[0];
    //                String existingFurnitureName = parts[1];
                    int existingPrice = Integer.parseInt(parts[3]);
                    String cartStaffName = parts[5];

                    if ((cartStaffName.equals(staffName)) && (existingItemId.equals(itemId))) {
                        // Update quantity and total price
                        int existingQuantity = Integer.parseInt(parts[2]);
    //                    int existingTotalPrice = Integer.parseInt(parts[3]);
                        int newQuantity = existingQuantity + quantityValue;
                        int newTotalPrice = existingPrice * newQuantity;

                        // Update the line in the list
                        lines.set(i,itemId + "," + furnitureName + "," + newQuantity + "," + priceStr + "," + newTotalPrice + "," + staffName);
                        found = true;
                        break;
                    }
                }else{
                    
                }
            }

            // If not found, add a new line
            if (!found) {
                lines.add(itemId + "," + furnitureName + "," + quantityValue + "," + priceStr + "," + totalPrice + "," + staffName);
            }

            // Write the updated content back to the file
            Files.write(file.toPath(), lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getInitialCartBadges(String staffName){
        String filePath = "cart.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            int lineCount = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                String [] parts = line.split(",");
                if (parts.length >= 6 && parts[5].equals(staffName)) {
                    lineCount++;
                }
            }
            
            cart.setBadges(lineCount);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void filterTable() {
    String text = searchBar.getText().trim();
    String selectedCategory = (String) filterCategory.getSelectedItem();

    List<RowFilter<Object, Object>> filters = new ArrayList<>();

    // Text filter
    RowFilter<Object, Object> textFilter = RowFilter.regexFilter("(?i)" + text);
    filters.add(textFilter);

    // Category filter (applied only if a specific category is selected)
    if (!"All Categories".equals(selectedCategory)) {
        RowFilter<Object, Object> categoryFilter = RowFilter.regexFilter("(?i)" + selectedCategory, 3); // Assuming the category column is at index 3
        filters.add(categoryFilter);
    }

    RowFilter<Object, Object> compoundRowFilter = RowFilter.andFilter(filters);

    rowSorter.setRowFilter(compoundRowFilter);
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private oodj.Dashboard.swing.ButtonBadges cart;
    private javax.swing.JComboBox<String> filterCategory;
    private javax.swing.JLabel filterLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private oodj.Dashboard.swing.RoundPanel roundPanel1;
    private javax.swing.JTextField searchBar;
    // End of variables declaration//GEN-END:variables
}
