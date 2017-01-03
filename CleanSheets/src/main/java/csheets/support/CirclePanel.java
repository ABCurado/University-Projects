/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.support;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 *
 * @author Rui Bento
 */
public class CirclePanel extends JPanel {

    public static JPanel createCircle(Dimension d, Color c) {
        DrawingClass dc = new DrawingClass();
        JPanel circle = new JPanel() {
            @Override
            protected void paintComponent(Graphics grphcs) {
                super.paintComponent(grphcs);
                Graphics2D g2d = (Graphics2D) grphcs;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                dc.draw(g2d, getWidth(), getHeight());
                g2d.setColor(c);
            }

            @Override
            public Dimension getPreferredSize() {
                return d;
            }
        };
        circle.setEnabled(true);
        circle.setVisible(true);
        circle.setForeground(c);
        circle.setSize(d);
        return circle;
    }

    private static class DrawingClass {
        public void draw(Graphics2D g2d, int w, int h) {
            g2d.fillOval(5, 5, w / 2, h / 2);
        }
    }
}
