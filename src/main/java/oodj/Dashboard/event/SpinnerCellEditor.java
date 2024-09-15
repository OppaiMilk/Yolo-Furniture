package oodj.Dashboard.event;

import java.awt.Component;
import javax.swing.AbstractCellEditor;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableCellEditor;

public class SpinnerCellEditor extends AbstractCellEditor implements TableCellEditor {
    private final JSpinner spinner;
    private final SpinnerNumberModel spinnerModel;

    public SpinnerCellEditor(SpinnerNumberModel spinnerModel) {
        this.spinnerModel = spinnerModel;
        this.spinner = new JSpinner(spinnerModel);

        spinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
//                fireEditingStopped();
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (value instanceof Number) {
            Number numberValue = (Number) value;

            if (compareNumbers((Number) spinnerModel.getMinimum(), numberValue) <= 0 &&
                compareNumbers((Number) spinnerModel.getMaximum(), numberValue) >= 0) {
                spinner.setValue(value);
            } else {
                spinner.setValue(spinnerModel.getMinimum());
            }
        }

        return spinner;
    }

    @Override
    public Object getCellEditorValue() {
        return spinner.getValue();
    }

    private int compareNumbers(Number num1, Number num2) {
        return Double.compare(num1.doubleValue(), num2.doubleValue());
    }
}
