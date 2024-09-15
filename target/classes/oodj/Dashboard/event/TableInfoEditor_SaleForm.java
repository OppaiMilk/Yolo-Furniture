package oodj.Dashboard.event;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import oodj.Dashboard.component.Info1;

public class TableInfoEditor_SaleForm extends DefaultCellEditor {

    private TableActionEvent_SaleForm event;

    public TableInfoEditor_SaleForm(TableActionEvent_SaleForm event) {
        super(new JCheckBox());
        this.event =  event;
    }

    @Override
    public Component getTableCellEditorComponent(JTable jtable, Object o, boolean bln, int row, int column) {
        Info1 info = new Info1();
        info.initEvent(event, row);
        info.setBackground(jtable.getSelectionBackground());
        return info;
    }
}