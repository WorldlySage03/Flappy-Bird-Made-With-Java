package org.example;

import org.example.models.DBHelper;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {

        //PreferenceManager.savePreferences("shawon", true);
        //PreferenceManager.clearPreferences();

        DBHelper.createTable();

        String username = "";
        boolean isLoggedIn = false;

        try {
            InputStream input = new FileInputStream("config.properties");

            Properties prop = new Properties();
            prop.load(input);

            String rawUsername = prop.getProperty("username");

            if (rawUsername == null || rawUsername.trim().isEmpty()) {
                System.out.println("Username not found in config.properties");
            } else {
                username = rawUsername.trim();
                System.out.println("Username: " + username);
            }


            isLoggedIn = Boolean.parseBoolean(prop.getProperty("isLoggedIn", "false"));
            System.out.println("Is Logged In: " + isLoggedIn);
        } catch (Exception e) {
            System.out.println("Error loading properties: " + e.getMessage());
        }


        if (isLoggedIn && !username.isEmpty()) {
            System.out.println("Welcome back, " + username + "!");
            new AfterLogin();
        } else {
            new NoLogin();
        }
    }

}