package oodj.Dashboard.event;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import oodj.Dashboard.component.DeleteButton;

public class CartDeleteRender extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable jtable, Object o, boolean isSeleted, boolean bln1, int row, int column) {
        Component com3 = super.getTableCellRendererComponent(jtable, o, isSeleted, bln1, row, column);
        DeleteButton delete = new DeleteButton();
        if (isSeleted == false && row % 2 == 0) {
            delete.setBackground(Color.WHITE);
        } else {
            delete.setBackground(com3.getBackground());
        }
        return delete;
    }
}
