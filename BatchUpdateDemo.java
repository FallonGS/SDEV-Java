import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class BatchUpdateDemo extends JFrame {
    private JButton jbtConnect = new JButton("connect to database");
    private JButton jbtNonBatch = new JButton("insert without batch");
    private JButton jbtBatch = new JButton("insert with batch");
    private JTextArea jtaResult = new JTextArea();

    private Connection connection;

    public BatchUpdateDemo() {
        JPanel panel = new JPanel();
        panel.add(jbtConnect);
        panel.add(jbtNonBatch);
        panel.add(jbtBatch);

        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(jtaResult), BorderLayout.CENTER);

        jbtConnect.addActionListener(e -> connectToDB());
        jbtNonBatch.addActionListener(e -> insertWithoutBatch());
        jbtBatch.addActionListener(e -> insertWithBatch());

        jtaResult.setEditable(false);
    }

    private void connectToDB() {
        DBConnectionPanel connectionPanel = new DBConnectionPanel();
        int result = JOptionPane.showConfirmDialog(
                this, connectionPanel, "connect to database",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                connection = connectionPanel.getConnection();
                jtaResult.append("connected to database\n");
            } catch (Exception ex) {
                jtaResult.append("connection failed: " + ex.getMessage() + "\n");
            }
        }
    }

    private void insertWithoutBatch() {
        if (connection == null) {
            jtaResult.append("please connect to the database first\n");
            return;
        }

        try {
            Statement stmt = connection.createStatement();
            long start = System.currentTimeMillis();

            for (int i = 0; i < 1000; i++) {
                double num1 = Math.random();
                double num2 = Math.random();
                double num3 = Math.random();

                stmt.executeUpdate("INSERT INTO Temp VALUES (" +
                        num1 + ", " + num2 + ", " + num3 + ")");
            }

            long end = System.currentTimeMillis();
            jtaResult.append("time without batch: " + (end - start) + " ms\n");

        } catch (SQLException ex) {
            jtaResult.append("error: " + ex.getMessage() + "\n");
        }
    }

    private void insertWithBatch() {
        if (connection == null) {
            jtaResult.append("please connect to the database first\n");
            return;
        }

        try {
            Statement stmt = connection.createStatement();
            long start = System.currentTimeMillis();

            for (int i = 0; i < 1000; i++) {
                double num1 = Math.random();
                double num2 = Math.random();
                double num3 = Math.random();

                stmt.addBatch("INSERT INTO Temp VALUES (" +
                        num1 + ", " + num2 + ", " + num3 + ")");
            }

            stmt.executeBatch();
            long end = System.currentTimeMillis();

            jtaResult.append("time with batch: " + (end - start) + " ms\n");

        } catch (SQLException ex) {
            jtaResult.append("error: " + ex.getMessage() + "\n");
        }
    }

    public static void main(String[] args) {
        BatchUpdateDemo frame = new BatchUpdateDemo();
        frame.setTitle("batch update performance demo");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
