package org.example;

import org.example.models.DBHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ChangePass extends JFrame {

    public ChangePass() {

        setTitle("Change Password");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(235, 255, 240)); // Light blue-gray


        JLabel title = new JLabel("Change Password");
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setBounds(100, 50, 300, 40);
        title.setForeground(new Color(33, 37, 41));
        add(title);

       /*
        JLabel userIcon = new JLabel(new ImageIcon("C:\\Users\\user\\Downloads\\profile.png")); // replace with your actual icon
        userIcon.setBounds(60, 140, 30, 30);
        add(userIcon);*/

        JLabel usertitle = new JLabel("Enter Current Password");
        usertitle.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        usertitle.setBounds(100, 105, 300, 40);
        usertitle.setForeground(new Color(33, 37, 41));
        add(usertitle);


        JPasswordField oldPasswordField = new JPasswordField();
        oldPasswordField.setBounds(100, 140, 220, 30);
        oldPasswordField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        oldPasswordField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        add(oldPasswordField);



        JLabel userpass = new JLabel("Enter New Password");
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


        JButton loginBtn = new RoundedButton("Change Password");
        loginBtn.setBounds(105, 280, 200, 40);
        loginBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        loginBtn.setBackground(new Color(4, 191, 51));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(loginBtn);

        loginBtn.addActionListener(e -> {
            String user = getUserNamefromFile();
            String oldpass = new String(oldPasswordField.getPassword());
            String newpass = new String(passwordField.getPassword());

            if (newpass.length() < 4) {
                JOptionPane.showMessageDialog(null, "New Password must be at least 4 characters long.", "Invalid Password", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (DBHelper.loginUser(user, oldpass)) {
                DBHelper db = new DBHelper();
                boolean success = db.updatePassword(user, newpass);
                if (success) {
                    JOptionPane.showMessageDialog(this, "Password updated successfully.");
                    dispose();
                    new AfterLogin();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to update password.");
                }



            } else {
                JOptionPane.showMessageDialog(this, "Wrong Current Password.");
            }
        });




        JButton regiBtn = new RoundedButton("Home");
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
            new AfterLogin();
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

    public static String getUserNamefromFile(){

        String user = "";
        try {
            InputStream input = new FileInputStream("config.properties");

            Properties prop = new Properties();
            prop.load(input);


            String rawUsername = prop.getProperty("username");
            if (rawUsername == null || rawUsername.trim().isEmpty()) {
                System.out.println("⚠️ Username not found in config.properties");
                return null;

            } else {
                user = rawUsername.trim();
                //System.out.println("Username: " + username);
            }
        } catch (Exception e) {
            System.out.println("Error loading properties: " + e.getMessage());
        }


        return user;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ChangePass::new);
    }

    public void gotoToRegister(){
        dispose();
        new RegiTest();
    }
}

