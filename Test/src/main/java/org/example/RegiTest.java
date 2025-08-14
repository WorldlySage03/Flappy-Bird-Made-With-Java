package org.example;

import org.example.models.DBHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RegiTest extends JFrame {

    public RegiTest() {

        setTitle("Sign Up");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(235, 255, 240)); // Light blue-gray


        JLabel title = new JLabel("Sign Up");
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setBounds(100, 50, 300, 40);
        title.setForeground(new Color(33, 37, 41));
        add(title);

       /* // Username icon
        JLabel userIcon = new JLabel(new ImageIcon("C:\\Users\\user\\Downloads\\profile.png")); // replace with your actual icon
        userIcon.setBounds(60, 140, 30, 30);
        add(userIcon);*/


        JLabel usertitle = new JLabel("Enter Username");
        usertitle.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        usertitle.setBounds(100, 105, 300, 40);
        usertitle.setForeground(new Color(33, 37, 41));
        add(usertitle);


        JTextField usernameField = new JTextField();
        usernameField.setBounds(100, 140, 220, 30);
        usernameField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        usernameField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        add(usernameField);



        JLabel userpass = new JLabel("Enter Password");
        userpass.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        userpass.setBounds(100, 180, 300, 40);
        userpass.setForeground(new Color(33, 37, 41));
        add(userpass);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(100, 215, 220, 30);
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        passwordField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        add(passwordField);


        JButton loginBtn = new RoundedButton("Sign Up");
        loginBtn.setBounds(135, 280, 120, 40);
        loginBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        loginBtn.setBackground(new Color(4, 191, 51));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(loginBtn);

        loginBtn.addActionListener(e -> {
            String user = usernameField.getText();
            String pass = new String(passwordField.getPassword());
            if (pass.length() < 4) {
                JOptionPane.showMessageDialog(null, "Password must be at least 4 characters long.", "Invalid Password", JOptionPane.ERROR_MESSAGE);
                return;
            }
            regiWork(user, pass);
        });



        JLabel useractext = new JLabel("Already Have an Account?");
        useractext.setFont(new Font("Segoe UI", Font.ITALIC, 15));
        useractext.setBounds(120, 385, 300, 40);
        useractext.setForeground(new Color(33, 37, 41));
        add(useractext);


        JButton regiBtn = new RoundedButton("Login");
        regiBtn.setBounds(135, 425, 120, 40);
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
            new LoginTest();
        });


        JButton homeBtn = new RoundedButton("Home");
        homeBtn.setBounds(135, 510, 120, 40);
        homeBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        homeBtn.setForeground(new Color(4, 191, 51)); // Match stroke color
        homeBtn.setBackground(new Color(0, 0, 0, 0)); // Transparent background
        homeBtn.setContentAreaFilled(false); // No fill
        homeBtn.setBorderPainted(true);
        homeBtn.setFocusPainted(false);
        homeBtn.setBorder(BorderFactory.createLineBorder(new Color(4, 191, 51), 2));
        homeBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(homeBtn);

        homeBtn.addActionListener(e -> {
            dispose();
            new NoLogin();
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
        SwingUtilities.invokeLater(RegiTest::new);
    }

    public void regiWork(String user, String pass){

        if (DBHelper.registerUser(user, pass, 0)) {
            JOptionPane.showMessageDialog(this, "Registered successfully!");
            dispose();
            new LoginTest();
        } else {
            JOptionPane.showMessageDialog(this, "User Name Taken. Try another.");
        }
    }
}

