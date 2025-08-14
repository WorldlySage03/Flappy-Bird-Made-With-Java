package org.example;
import org.example.models.DBHelper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Register extends JFrame {
    public Register() {
        setTitle("Register");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel userLabel = new JLabel("Username:");
        JTextField userField = new JTextField(15);

        JLabel passLabel = new JLabel("Password:");
        JPasswordField passField = new JPasswordField(15);

        JButton registerButton = new JButton("Register");
        JButton toLogin = new JButton("Go to Login");

        JPanel panel = new JPanel();
        panel.add(userLabel);
        panel.add(userField);
        panel.add(passLabel);
        panel.add(passField);
        panel.add(registerButton);
        panel.add(toLogin);

        add(panel);
        setVisible(true);

        registerButton.addActionListener(e -> {
            String user = userField.getText();
            String pass = new String(passField.getPassword());

            if (DBHelper.registerUser(user, pass, 0)) {
                JOptionPane.showMessageDialog(this, "Registered successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Registration failed! User may exist.");
            }
        });

        toLogin.addActionListener(e -> {
            dispose();
            new Login();
        });
    }
}