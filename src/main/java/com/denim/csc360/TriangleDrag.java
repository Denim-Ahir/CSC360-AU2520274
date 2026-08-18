package com.denim.csc360;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TriangleDrag extends JPanel implements MouseMotionListener {

    private int x = 300;
    private int y = 250;

    public TriangleDrag() {
        addMouseMotionListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int[] xPoints = {x, x - 50, x + 50};
        int[] yPoints = {y - 50, y + 50, y + 50};

        g.fillPolygon(xPoints, yPoints, 3);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        x = e.getX();
        y = e.getY();

        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // Nothing needed here
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Triangle Dragging");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(new TriangleDrag());
        frame.setVisible(true);
    }
}