package com.denim.csc360;

import javax.swing.*;
import java.awt.*;

public class App {

    public static void main(String[] args) {

        int size = Integer.parseInt(
                JOptionPane.showInputDialog("Enter square size:")
        );

        JFrame frame = new JFrame("CSC360 - Square");

        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                int x = (getWidth() - size) / 2;
                int y = (getHeight() - size) / 2;

                g.drawRect(x, y, size, size);
            }
        };

        frame.add(panel);
        frame.setVisible(true);
    }
}