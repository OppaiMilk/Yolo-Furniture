package oodj.Dashboard.component;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import oodj.Dashboard.swing.EventStarRating;

public class StarRating extends javax.swing.JPanel {

    public int getStar() {
        return star;
    }

    public void setStar(double rating) {
            if (rating < 0.0 || rating > 5.0) {
                throw new IllegalArgumentException("Rating should be between 0.0 and 5.0");
            }

            int numberOfStars = (int) Math.round(rating);
            selectStars(numberOfStars);
        }

    private void selectStars(int numberOfStars) {
        for (int i = 1; i <= 5; i++) {
            boolean selected = (i <= numberOfStars);
            selectStar(i, selected);
        }
    }

    private void selectStar(int starNumber, boolean selected) {
        switch (starNumber) {
            case 1:
                star1.setSelected(selected);
                break;
            case 2:
                star2.setSelected(selected);
                break;
            case 3:
                star3.setSelected(selected);
                break;
            case 4:
                star4.setSelected(selected);
                break;
            case 5:
                star5.setSelected(selected);
                break;
            default:
                throw new IllegalArgumentException("Invalid star number: " + starNumber);
        }
    }

    private List<EventStarRating> events = new ArrayList<>();
    private int star;

    public StarRating() {
        initComponents();
        init();
    }

    private void init() {
        setOpaque(false);
        setBackground(new Color(204, 204, 204));
        setForeground(new Color(238, 236, 0));
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        star1 = new oodj.Dashboard.swing.Star();
        star2 = new oodj.Dashboard.swing.Star();
        star3 = new oodj.Dashboard.swing.Star();
        star4 = new oodj.Dashboard.swing.Star();
        star5 = new oodj.Dashboard.swing.Star();

        setMinimumSize(new java.awt.Dimension(282, 23));
        setLayout(new java.awt.GridLayout(1, 5));

        star1.setBorder(null);
        star1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                star1ActionPerformed(evt);
            }
        });
        add(star1);

        star2.setBorder(null);
        star2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                star2ActionPerformed(evt);
            }
        });
        add(star2);

        star3.setBorder(null);
        star3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                star3ActionPerformed(evt);
            }
        });
        add(star3);

        star4.setBorder(null);
        star4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                star4ActionPerformed(evt);
            }
        });
        add(star4);

        star5.setBorder(null);
        star5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                star5ActionPerformed(evt);
            }
        });
        add(star5);
    }// </editor-fold>//GEN-END:initComponents

    private void star1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_star1ActionPerformed
        selectStars(1);
        star = 1;
        runEvent();
    }//GEN-LAST:event_star1ActionPerformed

    private void star2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_star2ActionPerformed
        selectStars(2);
        star = 2;
        runEvent();
    }//GEN-LAST:event_star2ActionPerformed

    private void star3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_star3ActionPerformed
        selectStars(3);
        star = 3;
        runEvent();
    }//GEN-LAST:event_star3ActionPerformed

    private void star4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_star4ActionPerformed
        selectStars(4);
        star = 4;
        runEvent();
    }//GEN-LAST:event_star4ActionPerformed

    private void star5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_star5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_star5ActionPerformed

        @Override
    public void setBackground(Color color) {
        super.setBackground(color);
        for (Component com : getComponents()) {
            com.setBackground(color);
        }
    }

    @Override
    public void setForeground(Color color) {
        super.setForeground(color);
        for (Component com : getComponents()) {
            com.setForeground(color);
        }
    }

    public void addEventStarRating(EventStarRating event) {
        events.add(event);
    }

    private void runEvent() {
        for (EventStarRating event : events) {
            event.selected(star);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private oodj.Dashboard.swing.Star star1;
    private oodj.Dashboard.swing.Star star2;
    private oodj.Dashboard.swing.Star star3;
    private oodj.Dashboard.swing.Star star4;
    private oodj.Dashboard.swing.Star star5;
    // End of variables declaration//GEN-END:variables
}
