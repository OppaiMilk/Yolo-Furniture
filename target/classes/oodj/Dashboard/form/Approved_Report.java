package oodj.Dashboard.form;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import oodj.Dashboard.event.PDFGenerator;
import oodj.Dashboard.event.SaleRecord;

public class Approved_Report {
    private List<SaleRecord> approvedSaleRecords;
    private static String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December", "Annual"};
    
    public Approved_Report(List<SaleRecord> approvedSaleRecords) {
        this.approvedSaleRecords = approvedSaleRecords;
    }

    public void generateReportForSelectedMonth(String selectedMonth, String selectedYear, String staffRoles, String staffName) {
        List<SaleRecord> filteredRecords = filterSaleRecordsByMonthAndYear(selectedMonth, selectedYear);

        String outputPath = "Approved Report/" + staffName + "_" + selectedMonth + "_" + selectedYear + "_Approved_Sale_Report.pdf";
        PDFGenerator.generateSaleReport(filteredRecords, outputPath, selectedMonth, selectedYear, staffRoles, staffName);

        System.out.println("Approved Sale Report for " + selectedMonth + " " + selectedYear + " generated successfully!");
        
        // Display a JOptionPane with the success message and ask if the user wants to open the PDF
        int choice = JOptionPane.showConfirmDialog(null,
                "Approved Sale Report for " + selectedMonth + " " + selectedYear + " generated successfully!\n"
                        + "Do you want to open the generated PDF?",
                "Success", JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {
            // Open the PDF file
            openPDF(outputPath);
        }
    }

    public void generateReportForSelectedYear(String selectedYear, String selectedMonth, String staffRoles, String staffName) {
        List<SaleRecord> filteredRecords = filterSaleRecordsByYear(selectedYear);

        String outputPath = "Approved Report/" + staffName + "_" + selectedMonth + "_" + selectedYear + "_Approved_Sale_Report.pdf";
        PDFGenerator.generateSaleReport(filteredRecords, outputPath, selectedMonth, selectedYear, staffRoles, staffName);

        System.out.println("Approved Sale Report for the year " + selectedYear + " generated successfully!");
        
        // Display a JOptionPane with the success message and ask if the user wants to open the PDF
        int choice = JOptionPane.showConfirmDialog(null,
                "Approved Sale Report for " + selectedMonth + " " + selectedYear + " generated successfully!\n"
                        + "Do you want to open the generated PDF?",
                "Success", JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {
            // Open the PDF file
            openPDF(outputPath);
        }
    }

    private List<SaleRecord> filterSaleRecordsByMonthAndYear(String selectedMonth, String selectedYear) {
        if ("Annual".equals(selectedMonth)) {
            return filterSaleRecordsByYear(selectedYear);
        }

        List<SaleRecord> filteredRecords = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");

        for (SaleRecord record : approvedSaleRecords) {
            try {
                Date saleDate = dateFormat.parse(record.getDateTime());
                Calendar cal = Calendar.getInstance();
                cal.setTime(saleDate);

                int recordMonth = cal.get(Calendar.MONTH);
                int recordYear = cal.get(Calendar.YEAR);
                int selectedMonthIndex = Arrays.asList(months).indexOf(selectedMonth);
                int selectedYearInt = Integer.parseInt(selectedYear);

                if (recordMonth == selectedMonthIndex && recordYear == selectedYearInt) {
                    filteredRecords.add(record);
                }
            } catch (ParseException e) {
                System.err.println("Error parsing date: " + e.getMessage());
                e.printStackTrace();
            }
        }

        return filteredRecords;
    }

    private List<SaleRecord> filterSaleRecordsByYear(String selectedYear) {
        List<SaleRecord> filteredRecords = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");

        for (SaleRecord record : approvedSaleRecords) {
            try {
                Date saleDate = dateFormat.parse(record.getDateTime());
                Calendar cal = Calendar.getInstance();
                cal.setTime(saleDate);

                int recordYear = cal.get(Calendar.YEAR);
                int selectedYearInt = Integer.parseInt(selectedYear);

                if (recordYear == selectedYearInt) {
                    filteredRecords.add(record);
                }
            } catch (ParseException e) {
                System.err.println("Error parsing date: " + e.getMessage());
                e.printStackTrace();
            }
        }

        return filteredRecords;
    }
    
    private void openPDF(String filePath) {
        try {
            File file = new File(filePath);
            if (file.exists()) {
                Desktop.getDesktop().open(file);
            } else {
                System.out.println("File not found: " + filePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


