package oodj.Dashboard.form;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import java.io.FileNotFoundException;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceGray;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.borders.DashedBorder;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.properties.TextAlignment;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import oodj.Dashboard.swing.TableCustom;




public class SaleFormDetail extends javax.swing.JFrame {
    public static String OrderId;
    public static String SPerson;
    public static String tPrice;
    
    //Show Detail 
    public SaleFormDetail(String orderId, String salesPerson, String date) {
        
        initComponents();
        loadTableData("order.txt",orderId);
        adjustColumnWidths();
        TableCustom.apply(jScrollPane1, TableCustom.TableType.DEFAULT);
        
        //Read order total price and total qty
        String totalprice= String.format("%.2f",ReadTablePrice());
        String totalqty = String.valueOf(ReadTableQty());
        
        //form variable
        Order_Id.setText(orderId);
        SalesPerson.setText(salesPerson);
        Date.setText(date);
        totalPrice.setText("RM"+totalprice);
        totalQty.setText(totalqty);
        
        // Global varible
        OrderId=orderId;
        SPerson =salesPerson;
        tPrice="RM"+totalprice;
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
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int i=1;
                if (data.length >=9 && data[0].equalsIgnoreCase(OrderID) && data[8].equals("Approved")) {
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
    
    private List<String> readDetailsFromFile(String orderID, String filePath) {
        List<String> myList = new ArrayList<>();  // Assuming there are 3 details: designer, description, size
        int i=1;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 9 && parts[0].equals(orderID)) {
                    
                    myList.add(String.valueOf(i));  // total price
                    myList.add(parts[2]);
                    myList.add(parts[3]);
                    myList.add("RM"+parts[4]);
                    myList.add("RM"+parts[5]);// Date
                    i+=1;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }

        return myList;
    }
    
    private void StatusChange(String orderId){
     String filePath = "order.txt";
     
     try {
            // 读取文件内容
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(orderId)&& line.contains("Pending")) {
                    // 在这里进行对特定文字后面内容的修改
                    line = line.replace("Pending", "Approved,In Progress");
                }
                content.append(line).append("\n");
            }
            reader.close();


            // 将修改后的内容写回文件
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            writer.write(content.toString());
            writer.close();

            System.out.println("File modification successful.");
            JOptionPane.showMessageDialog(this, "\t" + orderId + " has been sent to manufacturing for progress.\n\n\tInvoices" + orderId + " has been generated automatically!", "Success", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error to change the status", "Error", JOptionPane.ERROR_MESSAGE);
        }
}
    
    public interface DataRefreshCallback {
        void onDataRefresh();
}
    private DataRefreshCallback dataRefreshCallback;

    public void setDataRefreshCallback(DataRefreshCallback callback) {
        this.dataRefreshCallback = callback;
}

    
    //Print Invoice
    private void GenerateInvoice(String OrderID) throws FileNotFoundException{
        String path="Invoice/"+OrderID +".pdf";
        PdfWriter pdfWriter = new PdfWriter(path); 
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        pdfDocument.setDefaultPageSize(PageSize.A4);
        Document document = new Document(pdfDocument);
        
        if (dataRefreshCallback != null) {
            dataRefreshCallback.onDataRefresh();
        }
        
        //image path
        String imagepath="src" + File.separator + "main" + File.separator + "java" + File.separator + "oodj" + File.separator + "Dashboard" + File.separator + "icon" + File.separator + "yoyoLogo.png";
        
        //new line 
        Paragraph line = new Paragraph("\n");
        
        //Border and color  line setting
        Color grayColor = new DeviceGray(0.5f); 
        Color White = new DeviceGray(1f);
        Color BLACK = new DeviceRgb(0, 0, 0);
        Color WHITE = new DeviceRgb(255, 255, 255);
        float borderWidth =  0.5f;
        
        //Table width
        float twocol = 285f;
        float twocol150=twocol + 150f;
        float fivecol = 168f;
        float twocolumnWidth[] = {twocol150,twocol};
        float fivecolumnWidth[] = {fivecol/3,fivecol*3,fivecol/2,fivecol,fivecol};
        float threecol = 190f;
        float fullwidth []={threecol*3};
        float threeColumnWidth[]={threecol,threecol,threecol};
        float totalwidth []={threecol+125f,threecol*2};
        
        
        //Create Table
        Table company=new Table(fullwidth);
        Table customertable = new Table(twocolumnWidth);
        Table nestedtable = new Table(new float[]{twocol/2 , twocol/2});
        Table producttable = new Table(fivecolumnWidth);
        Table totaltable = new Table(totalwidth);
        Table tableDivider=new Table(fullwidth);
        Table totalcontent=new Table(threeColumnWidth);
        Table Term = new Table(fullwidth);
       
        //Divider line with space
        Border dgb=new DashedBorder(grayColor,0.5f);
        tableDivider.setBorder(dgb);
        
        //Add content to the company information table
        company.addCell(new Cell().add(new Paragraph("YOYO FURNITURE SDN BHD")).setFontSize(15).setBorder(Border.NO_BORDER).setBold().setTextAlignment(TextAlignment.CENTER));
        company.addCell(getHeaderTextCellValue("A1-1,JALAN FURNITURE,BANDAR DURIAN,00000 KLANG,SELANGOR").setTextAlignment(TextAlignment.CENTER));
        company.addCell(getHeaderTextCellValue("Tel: 011-1313151 Fax:03-031115565").setTextAlignment(TextAlignment.CENTER));
        company.addCell(getHeaderTextCellValue("Email: yoyofurniture@gmail.com").setTextAlignment(TextAlignment.CENTER));
        company.addCell(getHeaderTextCellValue("\n").setTextAlignment(TextAlignment.CENTER));
        company.addCell(getHeaderTextCell("Cash Sales",10f,true).setTextAlignment(TextAlignment.CENTER));
    
        //add a customer information Table
        nestedtable.addCell(getHeaderTextCell("Order No : ",10f,false));
        nestedtable.addCell(getHeaderTextCellValue(OrderId));
        nestedtable.addCell(getHeaderTextCell("Invoice Date : ",10f,false));
        nestedtable.addCell(getHeaderTextCellValue(LocalDate.now().toString()));
        nestedtable.addCell(getHeaderTextCell("Invoice Time : ",10f,false));
        nestedtable.addCell(getHeaderTextCellValue(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))));
        nestedtable.addCell(getHeaderTextCell("Term : ",10f,false));
        nestedtable.addCell(getHeaderTextCellValue("Cash"));
        
        //Fill content to the customer table
        customertable.addCell(getHeaderTextCellValue("Sales Persona : "+SPerson));
        customertable.addCell(new Cell().add(nestedtable).setBorder(Border.NO_BORDER));
        
        //Product
        producttable.setBackgroundColor(BLACK, 0.7f);
        producttable.addCell(getHeaderTextCellValueCenter("No").setBold().setFontColor(WHITE));
        producttable.addCell(getHeaderTextCellValueCenter("Furniture").setBold().setFontColor(WHITE));
        producttable.addCell(getHeaderTextCellValueCenter("Qty").setBold().setFontColor(WHITE));
        producttable.addCell(getHeaderTextCellValueCenter("Unit Price").setBold().setFontColor(WHITE));
        producttable.addCell(getHeaderTextCellValueCenter("Total").setBold().setFontColor(WHITE));

        //Product bought
        List<String> newList =readDetailsFromFile(OrderID,"order.txt");
        int i=newList.size();
        for (String bought: newList){
            producttable.addCell(getHeaderTextCellValue(bought).setBackgroundColor(White));        
        }
        while(i<(75)){
                producttable.addCell(getHeaderTextCellValue("\n").setBackgroundColor(White));
                i+=1;
        }
        
        //Total table
        totaltable.addCell(getHeaderTextCellValue(""));
        totaltable.addCell(new Cell().add(tableDivider).setBorder(Border.NO_BORDER));
        
        //Term
        Term.addCell(getHeaderTextCellTerm("TERMS AND CONDITIONS",15f,true));
        Term.addCell(getHeaderTextCellTerm("1. The Seller shall not be liable to the Buyer directly or indirectly for any loss or damage suggered by the Buyer.",10f,false));
        Term.addCell(getHeaderTextCellTerm("2. The Seller warrants the product for one(1) year from the date of invoice.",10f,false));

        //Total content
        totalcontent.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER)).setMarginLeft(10f);
        totalcontent.addCell(new Cell().add(new Paragraph("Total")).setTextAlignment(TextAlignment.CENTER).setFontSize(10f).setBorder(Border.NO_BORDER));
        totalcontent.addCell(new Cell().add(new Paragraph(tPrice)).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER)).setFontSize(10f).setMarginRight(15f);
        
        //Create a border to the table
        Border gb = new SolidBorder(grayColor, borderWidth);
        Table divider = new Table(fullwidth); 
        divider.setBorder(gb);
        
        //Fill the table to the document
        document.add(company); //the last word is Cash Sales
        document.add(divider.setMarginBottom(2f));
        document.add(customertable);
        document.add(producttable);
        document.add(line);
        document.add(totaltable);
        document.add(totalcontent);
        document.add(tableDivider);
        document.add(line);
        document.add(divider.setMarginBottom(15f));
        document.add(Term);
        
        try {
            ImageData imageData = ImageDataFactory.create(imagepath);
            Image image = new Image(imageData);
            float x = pdfDocument.getDefaultPageSize().getWidth()/2;
            float y = pdfDocument.getDefaultPageSize().getHeight()/2;
            float SizeWidth = 300f;
            float SizeHeight = 300f;
            image.scaleToFit(SizeWidth, SizeHeight);
            image.setFixedPosition(x-150,y-150);
            image.setOpacity(0.1f);
            document.add(image);
        } catch (MalformedURLException e) {
              e.printStackTrace();
        }
        
        document.close();
    }

    // Add Bold Cell word
    static Cell getHeaderTextCell(String textValue,float Size,Boolean isBold){
        Cell myCell= new Cell().add(new Paragraph(textValue)).setBorder(Border.NO_BORDER).setFontSize(Size).setTextAlignment(TextAlignment.RIGHT);
        return isBold ? myCell.setBold():myCell;
    }
    
    //Term
    static Cell getHeaderTextCellTerm(String textValue,float Size,Boolean isBold){
        Cell myCell= new Cell().add(new Paragraph(textValue)).setBorder(Border.NO_BORDER).setFontSize(Size).setTextAlignment(TextAlignment.LEFT);
        return isBold ? myCell.setBold():myCell;
    }
    
    //Add cell word
    static Cell getHeaderTextCellValue(String textValue){
        return new Cell().add(new Paragraph(textValue)).setBorder(Border.NO_BORDER).setFontSize(10f).setTextAlignment(TextAlignment.LEFT);
}
    
    static Cell getHeaderTextCellValueCenter(String textValue){
        return new Cell().add(new Paragraph(textValue)).setBorder(Border.NO_BORDER).setFontSize(10f).setTextAlignment(TextAlignment.LEFT);
}
     
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
        jButton1 = new oodj.Dashboard.swing.CustomButton();

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
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
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

        jButton1.setBorder(null);
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/oodj/Dashboard/icon/Submit Sales & Invoices.png"))); // NOI18N
        jButton1.setText("Submit Sales & Invoices");
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setRadius(25);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
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
                        .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TotalQuantityLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TotalPriceLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(backgroundPanelLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(totalQty, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundPanelLayout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(totalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32))
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
                    .addComponent(jScrollPane1))
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
                .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backgroundPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(TotalQuantityLabel)
                            .addComponent(totalQty, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TotalPriceLabel)
                            .addComponent(totalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(66, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backgroundPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backgroundPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            GenerateInvoice(OrderId);
            StatusChange(OrderId);
            dispose();
            if (dataRefreshCallback != null) {
                dataRefreshCallback.onDataRefresh();
            }
            } catch (FileNotFoundException ex) {
            // 处理异常，例如打印堆栈跟踪
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Date;
    private javax.swing.JLabel DateLabel;
    private javax.swing.JLabel OrderIDLabel;
    private javax.swing.JLabel Order_Id;
    private javax.swing.JLabel SalePersonLabel;
    private javax.swing.JLabel SalesPerson;
    private javax.swing.JLabel TotalPriceLabel;
    private javax.swing.JLabel TotalQuantityLabel;
    private javax.swing.JPanel backgroundPanel;
    private oodj.Dashboard.swing.CustomButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel totalPrice;
    private javax.swing.JLabel totalQty;
    // End of variables declaration//GEN-END:variables
}
