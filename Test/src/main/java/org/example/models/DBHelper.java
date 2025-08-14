package org.example.models;

import org.example.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DBHelper {

    private Connection connection;
    static final String url = "jdbc:sqlite:playerdb.db";

    public DBHelper() {

        try {

            connection = DriverManager.getConnection(url);

            System.out.println("Connection Successful");



        } catch (SQLException e) {

            System.out.println("Error Connecting to Database");

            e.printStackTrace();

        }

    }


    public static void createTable() {
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS players " +
                    "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    " username TEXT UNIQUE, " +
                    " password TEXT, " +
                    " highscore INTEGER DEFAULT 0)";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean registerUser(String username, String password, int highscore) {
        try (Connection conn = DriverManager.getConnection(url)) {
            String sql = "INSERT INTO players(username, password, highscore) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setInt(3, highscore);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean loginUser(String username, String password) {
        try (Connection conn = DriverManager.getConnection(url)) {
            String sql = "SELECT * FROM players WHERE username = ? AND password = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Player> getScoresSorted() {
        List<Player> p = new ArrayList<>();

        String querySQL = "SELECT username, highscore FROM players ORDER BY highscore DESC";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(querySQL)) {

            while (rs.next()) {
                String name = rs.getString("username");
                int score = rs.getInt("highscore");
                p.add(new Player(name, score));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching scores: " + e.getMessage());
        }
        return p;
    }


    public int getHighScore(String username) {
        String sql = "SELECT highscore FROM players WHERE username = ?";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("highscore");
            }
        } catch (SQLException e) {
            System.err.println("Error fetching highscore: " + e.getMessage());
        }
        return -1;
    }


    public boolean updateHighScore(String username, int newHighScore) {
        String sql = "UPDATE players SET highscore = ? WHERE username = ?";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, newHighScore);
            pstmt.setString(2, username);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("Error updating highscore: " + e.getMessage());
            return false;
        }
    }

    public boolean updatePassword(String username, String newPassword) {
        String sql = "UPDATE players SET password = ? WHERE username = ?";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newPassword);
            pstmt.setString(2, username);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("Error updating password: " + e.getMessage());
            return false;
        }
    }

    public DBHelper(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {

        return connection;

    }


    public void closeConnection() {

        try {

            if (connection != null) {

                System.out.println("Connection Closed");

                connection.close();

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

}