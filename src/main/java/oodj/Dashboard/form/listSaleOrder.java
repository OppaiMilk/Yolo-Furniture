package oodj.Dashboard.form;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import oodj.Dashboard.component.OrderDisplayBox;
import oodj.Dashboard.event.ModelOrder;
import oodj.Dashboard.swing.WrapLayout;

public class listSaleOrder extends javax.swing.JPanel {
    
    private String staffName;
    private Map<String, OrderDisplayBox> orderDisplayBoxMap;

    public listSaleOrder(String staffName) {
        initComponents();
        setOpaque(false);
        this.staffName = staffName;
        orderDisplayBoxMap = new HashMap<>();
        loadOrdersByStatus("Pending", PendingOrder);
        
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
        
        jTabbedPane2.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int selectedIndex = jTabbedPane2.getSelectedIndex();
                switch (selectedIndex) {
                    case 0: // Pending Orders
                        loadOrdersByStatus("Pending", PendingOrder);
                        break;
                    case 1: // Approved Orders
                        loadOrdersByStatus("Approved", ApprovedOrder);
                        break;
                    case 2: // Declined Orders
                        loadOrdersByStatus("Declined", DeclinedOrder);
                        break;
                    default:
                        break;
                }
            }
        });
        
    }
    
    private ModelOrder createModelOrder(String[] orderData) {
        return new ModelOrder(orderData[0], orderData[2], Integer.parseInt(orderData[3]),
            orderData[6], orderData[7], orderData[8]);
    }
    
    private void loadOrdersByStatus(String status, JPanel panel) {
        panel.removeAll();
        orderDisplayBoxMap.clear(); // Clear the map before loading new orders
        boolean hasPendingOrders = false;

        try (BufferedReader br = new BufferedReader(new FileReader("order.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] orderData = line.split(",");
                if ((orderData.length == 10 || orderData.length == 9) && status.equals(orderData[8]) && staffName.equals(orderData[7])) {
                    hasPendingOrders = true;
                    ModelOrder modelOrder = createModelOrder(orderData);
                    String orderID = modelOrder.getOrderID();

                    OrderDisplayBox orderDisplayBox = orderDisplayBoxMap.get(orderID);
                    if (orderDisplayBox == null) {
                        orderDisplayBox = new OrderDisplayBox();
                        orderDisplayBoxMap.put(orderID, orderDisplayBox);
                        panel.add(orderDisplayBox);
                    }

                    // Add furniture name and quantity to the orderList of the OrderDisplayBox
                    orderDisplayBox.getOrderListData().add(modelOrder.getFurnitureName() + "   x " + modelOrder.getQuantity());
                    orderDisplayBox.setData(modelOrder);
                }
            }
            
            if (!hasPendingOrders) {
                PendingOrder.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 200));
                JLabel noPendingOrderLabel = new JLabel("No Pending Order");
                noPendingOrderLabel.setHorizontalAlignment(JLabel.CENTER);
                noPendingOrderLabel.setVerticalAlignment(JLabel.CENTER);
                noPendingOrderLabel.setFont(new Font("Arial", Font.PLAIN, 48)); // Set the desired font size
                noPendingOrderLabel.setForeground(new Color(137, 137, 137)); // Set the desired color
                PendingOrder.add(noPendingOrderLabel);
                
                ApprovedOrder.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 200));
                JLabel noApprovedOrderLabel = new JLabel("No Approved Order");
                noApprovedOrderLabel.setHorizontalAlignment(JLabel.CENTER);
                noApprovedOrderLabel.setVerticalAlignment(JLabel.CENTER);
                noApprovedOrderLabel.setFont(new Font("Arial", Font.PLAIN, 48)); // Set the desired font size
                noApprovedOrderLabel.setForeground(new Color(137, 137, 137)); // Set the desired color
                ApprovedOrder.add(noApprovedOrderLabel);
                
                DeclinedOrder.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 200));
                JLabel noDeclinedOrderLabel = new JLabel("No Declined Order");
                noDeclinedOrderLabel.setHorizontalAlignment(JLabel.CENTER);
                noDeclinedOrderLabel.setVerticalAlignment(JLabel.CENTER);
                noDeclinedOrderLabel.setFont(new Font("Arial", Font.PLAIN, 48)); // Set the desired font size
                noDeclinedOrderLabel.setForeground(new Color(137, 137, 137)); // Set the desired color
                DeclinedOrder.add(noDeclinedOrderLabel);
            } else{
                PendingOrder.setLayout(new WrapLayout(WrapLayout.LEFT, 50, 15));
                ApprovedOrder.setLayout(new WrapLayout(WrapLayout.LEFT, 50, 15));
                DeclinedOrder.setLayout(new WrapLayout(WrapLayout.LEFT, 50, 15));

            }

            panel.revalidate();  // Revalidate the panel to apply the new layout
            panel.repaint();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void filterOrders(String orderId) {
        for (OrderDisplayBox orderDisplayBox : orderDisplayBoxMap.values()) {
            String displayBoxOrderId = orderDisplayBox.getData().getOrderID().toLowerCase();

            if (matchesOrderId(displayBoxOrderId, orderId)) {
                orderDisplayBox.setVisible(true);
            } else {
                orderDisplayBox.setVisible(false);
            }
        }
    }

    private boolean matchesOrderId(String displayBoxOrderId, String orderId) {
        if (orderId.isEmpty()) {
            return true; // Show all if orderId is empty
        }

        // Use a regular expression to check if orderId contains both alphabets and numbers
        String regex = "^(?=.*[a-zA-Z])(?=.*[0-9]).+$";
        return displayBoxOrderId.matches(regex) && displayBoxOrderId.contains(orderId.toLowerCase());
    }

    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new oodj.Dashboard.swing.RoundPanel();
        searchBar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        PendingOrder = new oodj.Dashboard.swing.PanelOrder();
        jScrollPane2 = new javax.swing.JScrollPane();
        ApprovedOrder = new oodj.Dashboard.swing.PanelOrder();
        jScrollPane3 = new javax.swing.JScrollPane();
        DeclinedOrder = new oodj.Dashboard.swing.PanelOrder();
        jLabel2 = new javax.swing.JLabel();

        roundPanel1.setBackground(new java.awt.Color(245, 240, 228));

        searchBar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        searchBar.setMinimumSize(new java.awt.Dimension(64, 25));
        searchBar.setName(""); // NOI18N
        searchBar.setPreferredSize(new java.awt.Dimension(71, 25));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/oodj/Dashboard/icon/search.png"))); // NOI18N

        jTabbedPane2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setViewportView(PendingOrder);

        jTabbedPane2.addTab("Pending", jScrollPane1);

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setViewportView(ApprovedOrder);

        jTabbedPane2.addTab("Approved", jScrollPane2);

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane3.setViewportView(DeclinedOrder);

        jTabbedPane2.addTab("Declined", jScrollPane3);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 66, 41));
        jLabel2.setText("List of Sale Orders");

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(390, 390, 390)
                        .addComponent(searchBar, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 938, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(398, 398, 398)
                        .addComponent(jLabel2)))
                .addGap(32, 32, 32))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 585, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(9, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 23, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private oodj.Dashboard.swing.PanelOrder ApprovedOrder;
    private oodj.Dashboard.swing.PanelOrder DeclinedOrder;
    private oodj.Dashboard.swing.PanelOrder PendingOrder;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane2;
    private oodj.Dashboard.swing.RoundPanel roundPanel1;
    private javax.swing.JTextField searchBar;
    // End of variables declaration//GEN-END:variables
}
