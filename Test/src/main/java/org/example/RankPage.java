package org.example;

import org.example.models.DBHelper;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;

public class RankPage extends JFrame {

    private DBHelper dbManager;

    public RankPage() {
        dbManager = new DBHelper();

        setTitle("Leaderboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 600);
        setLocationRelativeTo(null);

        String[] columnNames = {"Rank", "User Name", "High Score"};

        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        List<Player> players = dbManager.getScoresSorted();
        int rank = 1;
        for (Player player : players) {
            model.addRow(new Object[]{rank++, player.getName(), player.getScore()});
        }

        JTable table = new JTable(model);
        table.setFillsViewportHeight(true);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(28);
        table.setGridColor(new Color(220, 220, 220));
        table.setShowHorizontalLines(true);
        table.setShowVerticalLines(false);


        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
        header.setBackground(new Color(4, 191, 51));
        header.setForeground(Color.WHITE);
        header.setReorderingAllowed(false);
        header.setPreferredSize(new Dimension(header.getWidth(), 35));


        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            private final Color evenColor = new Color(242, 255, 240);
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                           boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setBackground(row % 2 == 0 ? evenColor : Color.WHITE);
                setHorizontalAlignment(column == 0 || column == 1 || column == 2 ? CENTER : LEFT);
                return this;
            }
        });




        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 50, 10));
        scrollPane.getViewport().setBackground(Color.WHITE);



        JButton rankBtn = new RoundedButton("Home");
        rankBtn.setBounds(135, 520, 120, 40);
        rankBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        rankBtn.setForeground(new Color(4, 191, 51)); // Match stroke color
        rankBtn.setBackground(new Color(0, 0, 0, 0)); // Transparent background
        rankBtn.setContentAreaFilled(false); // No fill
        rankBtn.setBorderPainted(true);
        rankBtn.setFocusPainted(false);
        rankBtn.setBorder(BorderFactory.createLineBorder(new Color(4, 191, 51), 2));
        rankBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(rankBtn);

        rankBtn.addActionListener(e -> {
            dispose();
            new AfterLogin();
        });

        add(scrollPane);

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
        SwingUtilities.invokeLater(RankPage::new);
    }
}