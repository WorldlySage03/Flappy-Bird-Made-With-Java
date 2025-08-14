package org.example;

import javax.swing.*;
import java.awt.*;

public class Countdown extends JFrame {
    private JLabel countdownLabel;
    private int countdown = 3;

    public Countdown() {
        setTitle("Game Countdown");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        countdownLabel = new JLabel("Get Ready!", SwingConstants.CENTER);
        countdownLabel.setFont(new Font("Segoe UI", Font.BOLD, 48));
        add(countdownLabel, BorderLayout.CENTER);

        setVisible(true);

        startCountdown();
    }

    private void startCountdown() {
        Timer timer = new Timer(1000, e -> {
            if (countdown > 0) {
                countdownLabel.setText(String.valueOf(countdown));
                countdown--;
            } else {
                ((Timer) e.getSource()).stop();
                countdownLabel.setText("GO!");
                // Optional: after short delay, start your game logic here
                new Timer(1000, evt -> {
                    dispose(); // Close countdown window
                    new GameActivity();
                    // Launch your game window here
                    // Example: new GameWindow();
                }).start();
            }
        });
        timer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Countdown::new);
    }
}
