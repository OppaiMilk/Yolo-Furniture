/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oodj.Dashboard.event;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import oodj.Dashboard.component.OrderCartButton;

/**
 *
 * @author USER
 */
public class TableAddOrderRender extends DefaultTableCellRenderer {
    public Component getTableCellRendererComponent(JTable jtable, Object o, boolean isSeleted, boolean bln1, int row, int column) {
        Component com2 = super.getTableCellRendererComponent(jtable, o, isSeleted, bln1, row, column);
        OrderCartButton cart = new OrderCartButton();
        if (isSeleted == false && row % 2 == 0) {
            cart.setBackground(Color.WHITE);
        } else {
            cart.setBackground(com2.getBackground());
        }
        return cart;
    }
}
