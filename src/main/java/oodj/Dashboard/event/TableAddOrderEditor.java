/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oodj.Dashboard.event;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import oodj.Dashboard.component.OrderCartButton;

/**
 *
 * @author USER
 */
public class TableAddOrderEditor extends DefaultCellEditor{
    private TableActionEvent event;

    public TableAddOrderEditor(TableActionEvent event) {
        super(new JCheckBox());
        this.event = event;
    }

    public Component getTableCellEditorComponent(JTable jtable, Object o, boolean bln, int row, int column) {
        OrderCartButton cart = new OrderCartButton();
        cart.initEvent2(event, row);
        cart.setBackground(jtable.getSelectionBackground());
        return cart;
    }
}
