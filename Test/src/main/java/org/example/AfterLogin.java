package org.example;

import org.example.models.DBHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;


public class AfterLogin extends JFrame {

    public AfterLogin() {

        setTitle("Flapy Bird");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(235, 255, 240)); // Light blue-gray



        JButton playBtn = new RoundedButton("PLAY NOW");
        playBtn.setBounds(105, 200, 200, 60);
        playBtn.setFont(new Font("Outfit Black", Font.BOLD, 16));
        playBtn.setBackground(new Color(4, 191, 51));
        playBtn.setForeground(Color.WHITE);
        playBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(playBtn);

        playBtn.addActionListener(e -> {
            dispose();
            new GameActivity();
        });


        JButton changePassBtn = new RoundedButton("Change Password");
        changePassBtn.setBounds(120, 435, 150, 40);
        changePassBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        changePassBtn.setForeground(new Color(4, 191, 51));
        changePassBtn.setBackground(new Color(0, 0, 0, 0));
        changePassBtn.setContentAreaFilled(false); // No fill
        changePassBtn.setBorderPainted(true);
        changePassBtn.setFocusPainted(false);
        changePassBtn.setBorder(BorderFactory.createLineBorder(new Color(4, 191, 51), 2));
        changePassBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(changePassBtn);

        changePassBtn.addActionListener(e -> {
            dispose();
            new ChangePass();
        });



        JButton rankBtn = new RoundedButton("Leaderboard");
        rankBtn.setBounds(120, 390, 150, 40);
        rankBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        rankBtn.setForeground(new Color(4, 191, 51));
        rankBtn.setBackground(new Color(0, 0, 0, 0));
        rankBtn.setContentAreaFilled(false); // No fill
        rankBtn.setBorderPainted(true);
        rankBtn.setFocusPainted(false);
        rankBtn.setBorder(BorderFactory.createLineBorder(new Color(4, 191, 51), 2));
        rankBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(rankBtn);

        rankBtn.addActionListener(e -> {
            dispose();
            new RankPage();
        });


        JButton regiBtn = new RoundedButton("Logout");
        regiBtn.setBounds(120, 490, 150, 40);
        regiBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        regiBtn.setForeground(new Color(176, 2, 2)); // Match stroke color
        regiBtn.setBackground(new Color(0, 0, 0, 0)); // Transparent background
        regiBtn.setContentAreaFilled(false); // No fill
        regiBtn.setBorderPainted(true);
        regiBtn.setFocusPainted(false);
        regiBtn.setBorder(BorderFactory.createLineBorder(new Color(176, 2, 2), 2));
        regiBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(regiBtn);

        regiBtn.addActionListener(e -> {
            PreferenceManager.clearPreferences();
            dispose();
            new NoLogin();
        });

        String username = getUserNamefromFile();


        JLabel title = new JLabel("<html>Welcome,<br>" + username + "</html>");
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setBounds(100, 50, 500, 60);
        title.setForeground(new Color(33, 37, 41));
        add(title);

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

            String rawUsername = prop.getProperty("username").trim();
            user = rawUsername;
        } catch (Exception e) {
            System.out.println("Error loading properties: " + e.getMessage());
        }


        return user;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AfterLogin::new);
    }

    public void gotoToRegister(){
        dispose();
        new Register();
    }
}

