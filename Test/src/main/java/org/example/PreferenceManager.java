package org.example;

import java.io.*;
import java.util.Properties;

public class PreferenceManager {
    private static final String FILE_NAME = "config.properties";

    public static void loadPreferences() {
        try (InputStream input = new FileInputStream(FILE_NAME)) {
            Properties prop = new Properties();
            prop.load(input);

            String username = prop.getProperty("username", "default_user");
            boolean isLoggedIn = Boolean.parseBoolean(prop.getProperty("isLoggedIn", "false"));


            System.out.println(username + " " + isLoggedIn);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void savePreferences(String username, boolean isLoggedIn) {
        try (OutputStream output = new FileOutputStream(FILE_NAME)) {
            Properties prop = new Properties();

            prop.setProperty("username", username);
            prop.setProperty("isLoggedIn", String.valueOf(isLoggedIn));

            prop.store(output, null);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public static void clearPreferences() {
        Properties prop = new Properties(); // empty

        try (OutputStream output = new FileOutputStream(FILE_NAME)) {
            prop.store(output, "All preferences cleared");
            System.out.println("All preferences cleared.");
        } catch (IOException io) {
            System.out.println("Failed to clear preferences: " + io.getMessage());
        }
    }
}

