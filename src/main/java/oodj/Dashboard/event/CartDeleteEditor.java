package oodj.Dashboard.event;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import oodj.Dashboard.component.DeleteButton;

public class CartDeleteEditor extends DefaultCellEditor {
    private TableDeleteEvent event;

    public CartDeleteEditor(TableDeleteEvent event) {
        super(new JCheckBox());
        this.event = event;
    }

    @Override
    public Component getTableCellEditorComponent(JTable jtable, Object o, boolean bln, int row, int column) {
        DeleteButton delete = new DeleteButton();
        delete.initEvent(event, row);
        delete.setBackground(jtable.getSelectionBackground());
        return delete;
    }
}
