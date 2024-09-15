package oodj.Dashboard.event;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import oodj.Dashboard.component.StarRating;

public class TableRatingRender extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable jtable, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component component = super.getTableCellRendererComponent(jtable, value, isSelected, hasFocus, row, column);

        // Check if the value is a double (rating)
        if (value instanceof Double) {
            double rating = (Double) value;

            // Create a StarRating component
            StarRating starRating = new StarRating();
            starRating.setStar(rating);

            // Set the background color based on selection and row index
            if (!isSelected && row % 2 == 0) {
                starRating.setBackground(new Color(204, 204, 204));
            } else {
                starRating.setBackground(component.getBackground());
            }

            // Return the StarRating component
            return starRating;
        }

        // For other types of values, return the default rendering
        return component;
    }
}
