package app;

import java.sql.*;

public class MoneyTransfer {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/banking_app";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static boolean transferMoney(int senderAccountId, int recipientAccountId, double amount) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            conn.setAutoCommit(false); // Begin transaction

            double senderBalance = getAccountBalance(conn, senderAccountId);
            if (senderBalance < amount) {
                System.out.println("Insufficient funds in sender's account.");
                conn.rollback(); // Rollback transaction
                return false;
            }

            updateAccountBalance(conn, senderAccountId, senderBalance - amount);
            double recipientBalance = getAccountBalance(conn, recipientAccountId);
            updateAccountBalance(conn, recipientAccountId, recipientBalance + amount);

            conn.commit(); // Commit transaction
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static double getAccountBalance(Connection conn, int accountId) throws SQLException {
        String sql = "SELECT balance FROM accounts WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, accountId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("balance");
                } else {
                    throw new SQLException("Account not found: " + accountId);
                }
            }
        }
    }

    private static void updateAccountBalance(Connection conn, int accountId, double newBalance) throws SQLException {
        String sql = "UPDATE accounts SET balance = ? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, newBalance);
            pstmt.setInt(2, accountId);
            pstmt.executeUpdate();
        }
    }
}
