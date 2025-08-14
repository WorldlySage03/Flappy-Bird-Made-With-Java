package org.example;

import org.example.models.DBHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NoLogin extends JFrame {

    public NoLogin() {

        setTitle("Flapy Bird");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(235, 255, 240)); // Light blue-gray


        JLabel title = new JLabel("Welcome!");
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setBounds(150, 50, 300, 40);
        title.setForeground(new Color(33, 37, 41));
        add(title);


        JButton playBtn = new RoundedButton("PLAY AS GUEST");
        playBtn.setBounds(105, 150, 200, 60);
        playBtn.setFont(new Font("Outfit Black", Font.BOLD, 16));
        playBtn.setBackground(new Color(4, 191, 51));
        playBtn.setForeground(Color.WHITE);
        playBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(playBtn);



        playBtn.addActionListener(e -> {
            dispose();
            new GameActivity();
        });


        JButton loginBtn = new RoundedButton("Login");
        loginBtn.setBounds(135, 435, 120, 40);
        loginBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        loginBtn.setForeground(new Color(4, 191, 51)); // Match stroke color
        loginBtn.setBackground(new Color(0, 0, 0, 0)); // Transparent background
        loginBtn.setContentAreaFilled(false); // No fill
        loginBtn.setBorderPainted(true);
        loginBtn.setFocusPainted(false);
        loginBtn.setBorder(BorderFactory.createLineBorder(new Color(4, 191, 51), 2));
        loginBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(loginBtn);

        loginBtn.addActionListener(e -> {
            dispose();
            new LoginTest();
        });



        JButton regiBtn = new RoundedButton("Sign Up");
        regiBtn.setBounds(135, 490, 120, 40);
        regiBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        regiBtn.setForeground(new Color(4, 191, 51)); // Match stroke color
        regiBtn.setBackground(new Color(0, 0, 0, 0)); // Transparent background
        regiBtn.setContentAreaFilled(false); // No fill
        regiBtn.setBorderPainted(true);
        regiBtn.setFocusPainted(false);
        regiBtn.setBorder(BorderFactory.createLineBorder(new Color(4, 191, 51), 2));
        regiBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(regiBtn);

        regiBtn.addActionListener(e -> {
            dispose();
            new RegiTest();
        });

        setVisible(true);
    }


    class RoundedButton extends JButton {
        public RoundedButton(String label) {
            super(label);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
            super.paintComponent(g2);
            g2.dispose();
        }


    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(NoLogin::new);
    }


}

