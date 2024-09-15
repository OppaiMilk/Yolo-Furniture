package oodj.Dashboard.event;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import oodj.Dashboard.component.Info1;

public class TableManufactureRender extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable jtable, Object o, boolean isSeleted, boolean bln1, int row, int column) {
        Component com = super.getTableCellRendererComponent(jtable, o, isSeleted, bln1, row, column);
        Info1 info = new Info1();
        if (isSeleted == false && row % 2 == 0) {
            info.setBackground(Color.WHITE);
        } else {
            info.setBackground(com.getBackground());
        }
        return info;
    }
}
