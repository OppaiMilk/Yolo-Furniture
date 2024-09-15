package oodj.Dashboard.component;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;
import oodj.Dashboard.event.EventMenu;
import oodj.Dashboard.scrollbar.ScrollBarCustom;
import oodj.Dashboard.swing.ButtonMenu;

public class MenuBar extends javax.swing.JPanel {

    private EventMenu event;
    
    public MenuBar() {
        initComponents();
        setOpaque(false);
        ScrollBarCustom sb = new ScrollBarCustom();
        sb.setForeground(new Color(130, 130, 130, 100));
        jScrollPane1.setVerticalScrollBar(sb);
        panelMenu.setLayout(new MigLayout("wrap, fillx, inset 3", "[fill]", "[]0[]"));
    }
    
    public class ImageLoader {

        public static void loadProfilePhoto(String staffName) {
            String profilePhotoPath = "Profile Photo/" + staffName + ".png";

            try {
                File profilePhotoFile = new File(profilePhotoPath);
                BufferedImage profileImage = ImageIO.read(profilePhotoFile);
                ImageIcon newProfilePhotoIcon = new ImageIcon(profileImage);
                imageAvatar1.setIcon(newProfilePhotoIcon);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        public static void updateNewStaffName(String staffName) {
            jLabel1.setText(staffName);
        }
    }
    
    public void initMenu(EventMenu event, String staffName, String staffEmail, String staffRole) {
        ImageLoader.loadProfilePhoto(staffName);

        this.event = event;
        jLabel1.setText(staffName);
        jLabel2.setText(staffRole);
        addMenu(new ImageIcon(getClass().getResource("/oodj/Dashboard/icon/1.png")), "Dashboard", 0);

        
        if ("Officer".equals(staffRole)) {
            addMenu(new ImageIcon(getClass().getResource("/oodj/Dashboard/icon/5.png")), "Approval of Sale Order", 1);
            addMenu(new ImageIcon(getClass().getResource("/oodj/Dashboard/icon/6.png")), "Submit Sales & Invoices", 2);
//            addMenu(new ImageIcon(getClass().getResource("/oodj/Dashboard/icon/6.png")), "Review Invoices", 3);
            addMenu(new ImageIcon(getClass().getResource("/oodj/Dashboard/icon/8.png")), "Product Status", 7);
            addMenu(new ImageIcon(getClass().getResource("/oodj/Dashboard/icon/4.png")), "Report", 4);
            addMenu(new ImageIcon(getClass().getResource("/oodj/Dashboard/icon/3.png")), "Profile", 5);

        } else if ("Administrator".equals(staffRole)) {
            addMenu(new ImageIcon(getClass().getResource("/oodj/Dashboard/icon/5.png")), "Manage Worker", 6);
//            addMenu(new ImageIcon(getClass().getResource("/oodj/Dashboard/icon/8.png")), "Manage Officer", 7);
            addMenu(new ImageIcon(getClass().getResource("/oodj/Dashboard/icon/4.png")), "Report", 4);
            addMenu(new ImageIcon(getClass().getResource("/oodj/Dashboard/icon/3.png")), "Profile", 5);
        
        } else if ("SalesPerson".equals(staffRole)){
            addMenu(new ImageIcon(getClass().getResource("/oodj/Dashboard/icon/6.png")), "New Sale Order", 9);
            addMenu(new ImageIcon(getClass().getResource("/oodj/Dashboard/icon/5.png")), "Modify Orders", 10);
            addMenu(new ImageIcon(getClass().getResource("/oodj/Dashboard/icon/2.png")), "SaleOrders List", 11);
            addMenu(new ImageIcon(getClass().getResource("/oodj/Dashboard/icon/3.png")), "Profile", 5);
        }
        
        imageAvatar1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                event.selected(5);

                Component[] components = panelMenu.getComponents();
                if (components.length > 5 && components[5] instanceof ButtonMenu) {
                    ButtonMenu menu = (ButtonMenu) components[5];
                    setSelected(menu);
                }
            }
        });
        
//        addMenu(new ImageIcon(getClass().getResource("/oodj/Dashboard/icon/1.png")), "Dashboard", 0);
//        addMenu(new ImageIcon(getClass().getResource("/oodj/Dashboard/icon/2.png")), "Application", 1);
//        addMenu(new ImageIcon(getClass().getResource("/oodj/Dashboard/icon/3.png")), "Staff", 2);
//        addMenu(new ImageIcon(getClass().getResource("/oodj/Dashboard/icon/4.png")), "Report", 3);
//        addMenu(new ImageIcon(getClass().getResource("/oodj/Dashboard/icon/5.png")), "Note", 4);
//        addMenu(new ImageIcon(getClass().getResource("/oodj/Dashboard/icon/6.png")), "Export", 5);
//        addMenu(new ImageIcon(getClass().getResource("/oodj/Dashboard/icon/7.png")), "Import", 6);
//        addMenu(new ImageIcon(getClass().getResource("/oodj/Dashboard/icon/8.png")), "Setting", 7);
        addEmpty();
        addMenu(new ImageIcon(getClass().getResource("/oodj/Dashboard/icon/logout.png")), "Logout", 12);
    }

    private void addEmpty() {
        panelMenu.add(new JLabel(), "push");
        panelMenu.add(new JLabel(), "push");
        panelMenu.add(new JLabel(), "push");

    }

    private void addMenu(Icon icon, String text, int index) {
        ButtonMenu menu = new ButtonMenu();
        menu.setIcon(icon);
        menu.setText("  " + text);
        panelMenu.add(menu);
        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                event.selected(index);
                setSelected(menu);
            }
        });
    }

    private void setSelected(ButtonMenu menu) {
        for (Component com : panelMenu.getComponents()) {
            if (com instanceof ButtonMenu) {
                ButtonMenu b = (ButtonMenu) com;
                b.setSelected(false);
            }
        }
        menu.setSelected(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new oodj.Dashboard.swing.RoundPanel();
        roundPanel2 = new oodj.Dashboard.swing.RoundPanel();
        imageAvatar1 = new oodj.Dashboard.swing.ImageAvatar();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        roundPanel3 = new oodj.Dashboard.swing.RoundPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        panelMenu = new javax.swing.JPanel();

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        roundPanel2.setBackground(new java.awt.Color(102, 66, 41));

        imageAvatar1.setForeground(new java.awt.Color(231, 231, 231));
        imageAvatar1.setBorderSize(2);
        imageAvatar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/oodj/Dashboard/icon/user.png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("User Name");

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Admin");

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)))
                .addGap(10, 10, 10))
        );

        roundPanel3.setBackground(new java.awt.Color(102, 66, 41));

        jScrollPane1.setBackground(new java.awt.Color(0, 194, 223));
        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        panelMenu.setBackground(new java.awt.Color(102, 66, 41));

        javax.swing.GroupLayout panelMenuLayout = new javax.swing.GroupLayout(panelMenu);
        panelMenu.setLayout(panelMenuLayout);
        panelMenuLayout.setHorizontalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 208, Short.MAX_VALUE)
        );
        panelMenuLayout.setVerticalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 524, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(panelMenu);

        javax.swing.GroupLayout roundPanel3Layout = new javax.swing.GroupLayout(roundPanel3);
        roundPanel3.setLayout(roundPanel3Layout);
        roundPanel3Layout.setHorizontalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundPanel3Layout.setVerticalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(roundPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(roundPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(roundPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static oodj.Dashboard.swing.ImageAvatar imageAvatar1;
    private static javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelMenu;
    private oodj.Dashboard.swing.RoundPanel roundPanel1;
    private oodj.Dashboard.swing.RoundPanel roundPanel2;
    private oodj.Dashboard.swing.RoundPanel roundPanel3;
    // End of variables declaration//GEN-END:variables
}
