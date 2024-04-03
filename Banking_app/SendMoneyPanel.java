package app;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class SendMoneyPanel extends JPanel {
    private JTextField recipientField;
    private JTextField amountField;
    private JButton sendButton;

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/banking_app";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public SendMoneyPanel() {
        setLayout(new GridLayout(3, 2));

        JLabel recipientLabel = new JLabel("Recipient Account ID:");
        recipientField = new JTextField();
        JLabel amountLabel = new JLabel("Amount to Send:");
        amountField = new JTextField();
        sendButton = new JButton("Send");

        add(recipientLabel);
        add(recipientField);
        add(amountLabel);
        add(amountField);
        add(new JLabel()); // Placeholder for spacing
        add(sendButton);

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String recipientIdText = recipientField.getText();
                String amountText = amountField.getText();

                // Parse recipient account ID and amount
                try {
                    int recipientAccountId = Integer.parseInt(recipientIdText);
                    double amount = Double.parseDouble(amountText);

                    // Call method to transfer money
                    boolean success = MoneyTransfer.transferMoney(getLoggedInUserId(), recipientAccountId, amount);
                    if (success) {
                        JOptionPane.showMessageDialog(SendMoneyPanel.this, "Money sent successfully.");
                    } else {
                        JOptionPane.showMessageDialog(SendMoneyPanel.this, "Failed to send money.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(SendMoneyPanel.this, "Invalid input. Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    // Method to retrieve the logged-in user's account balance
    private double getLoggedInUserBalance() {

        try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            int UserID = 1; // Get the logged-in user's ID (method implementation not provided here)
            String sql = "SELECT Balance FROM users WHERE UserID = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1,UserID);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        return rs.getDouble("Balance");
                    } else {
                        JOptionPane.showMessageDialog(this, "Account balance not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to retrieve account balance.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return 0.0; // Default value if balance retrieval fails
    }

    // Example method to get the logged-in user's ID (replace with actual implementation)
    private int getLoggedInUserId() {
        // Replace this with your implementation to get the logged-in user's ID
        return 1; // Example value, replace with actual implementation
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Send Money");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(400, 150);

                SendMoneyPanel sendMoneyPanel = new SendMoneyPanel();
                frame.add(sendMoneyPanel);

                frame.setVisible(true);
            }
        });
    }
}


