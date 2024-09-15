package oodj.Dashboard.swing;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JScrollBar;
import oodj.Dashboard.scrollbar.ModernScrollBarUI;

public class ScrollBarCustom extends JScrollBar {

    public ScrollBarCustom() {
        setUI(new ModernScrollBarUI());
        setPreferredSize(new Dimension(8, 8));
        setForeground(new Color(48, 144, 216));
        setBackground(Color.WHITE);
    }
}
