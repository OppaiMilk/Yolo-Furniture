package oodj.Dashboard.event;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import oodj.Dashboard.component.Info;

public class TableInfoEditor extends DefaultCellEditor {

    private TableActionEvent event;

    public TableInfoEditor(TableActionEvent event) {
        super(new JCheckBox());
        this.event = event;
    }

    @Override
    public Component getTableCellEditorComponent(JTable jtable, Object o, boolean bln, int row, int column) {
        Info info = new Info();
        info.initEvent(event, row);
        info.setBackground(jtable.getSelectionBackground());
        return info;
    }
}