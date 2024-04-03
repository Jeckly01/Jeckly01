package app;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BankingApp extends JFrame {

    // Database connection details
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/banking_app";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static Connection connection;

    private JTextField recipientField;
    private JTextField amountField;
    private JButton sendButton;

    public static String loggedInUsername;
    public static String newPassword;
    public static Double Balancelogged;
    public static String RecepientUsername;
    public static String RecepientUsername1;
    public static String encryptedNewPassword;

    public static Double newBal;
    private static double AmountToSend1;
    private static double AmountToSend2;
    private static double AmountToSend3;
    private static String toUsername1;

    public BankingApp() {
        setTitle("Banking App");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame
        setLayout(new BorderLayout());

        // Login panel
        JPanel loginPanel = new JPanel(new GridLayout(3, 2));
        JLabel usernameLabel = new JLabel("username");
        JTextField usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("password");
        JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");
        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(new JLabel()); // Empty label for spacing
        loginPanel.add(loginButton);

        // Home panel
        JPanel homePanel = new JPanel(new GridLayout(5, 2));
        JButton sendMoneyButton = new JButton("Send Money");
        JButton checkBalanceButton = new JButton("Check Balance");
        JButton buyAirtimeButton = new JButton("Buy Airtime");
        JButton buyElectricityButton = new JButton("Buy Electricity");
        JButton payTVButton = new JButton("Pay TV");
        JButton contactCustomerButton = new JButton("Contact Customer");
        JButton changePasswordButton = new JButton("Change Password");
        JButton backButton = new JButton("Back");
        JButton nextPageButton = new JButton("Next Page");
        homePanel.add(sendMoneyButton);
        homePanel.add(checkBalanceButton);
        homePanel.add(buyAirtimeButton);
        homePanel.add(buyElectricityButton);
        homePanel.add(payTVButton);
        homePanel.add(contactCustomerButton);
        homePanel.add(changePasswordButton);
        homePanel.add(new JLabel());
        homePanel.add(backButton);
        homePanel.add(nextPageButton);

        JPanel SendMoneyPanel = new JPanel(new GridLayout(4, 2));
        JLabel recipientIDLabel = new JLabel("Recipient Account Name:");
        JTextField recipientIDField = new JTextField();
        JLabel amountLabel = new JLabel("Amount to Send:");
        JTextField amountField = new JTextField();
        JButton sendButton = new JButton("Send");
        JButton SendMoneyBackButton = new JButton("Back");


        SendMoneyPanel.add(recipientIDLabel);
        SendMoneyPanel.add(recipientIDField);
        SendMoneyPanel.add(amountLabel);
        SendMoneyPanel.add(amountField);
        SendMoneyPanel.add(SendMoneyBackButton);
        SendMoneyPanel.add(sendButton);


        JPanel BuyAirtimePanel = new JPanel(new GridLayout(4, 2));
        JLabel usernameLabel2 = new JLabel("Airtime brand");
        JTextField usernameField2 = new JTextField();
        JLabel passwordLabel2 = new JLabel("amount");
        JTextField passwordField2 = new JTextField();
        JButton BuyAirtimeButton = new JButton("Buy Airtime");
        JButton BuyAirtimeBackButton = new JButton("Back");

        BuyAirtimePanel.add(usernameLabel2);
        BuyAirtimePanel.add(usernameField2);
        BuyAirtimePanel.add(passwordLabel2);
        BuyAirtimePanel.add(passwordField2);
        BuyAirtimePanel.add(BuyAirtimeBackButton);
        BuyAirtimePanel.add(BuyAirtimeButton);


        JPanel CheckBalancePanel = new JPanel(new GridLayout(3, 2));
        JLabel balanceLabel = new JLabel("Your balance is: ");
        JTextField balanceField = new JTextField();
        balanceField.setEditable(false);
        JButton CheckBalanceBackButton = new JButton("Back");
        JButton CheckBalanceNextPage = new JButton("Next Page");
        CheckBalancePanel.add(balanceLabel);
        CheckBalancePanel.add(balanceField);
        CheckBalancePanel.add(CheckBalanceBackButton);
        CheckBalancePanel.add(CheckBalanceNextPage);

        JPanel BuyElectricityPanel = new JPanel(new GridLayout(3, 2));
        JLabel usernameLabel4 = new JLabel("Electricty account number: ");
        JTextField usernameField4 = new JTextField();
        JLabel passwordLabel4 = new JLabel("amount");
        JTextField passwordField4 = new JTextField();
        JButton BuyElectricityBackButton = new JButton("Back");
        JButton BuyElectricityButton12 = new JButton("Buy Electricity");
        BuyElectricityPanel.add(usernameLabel4);
        BuyElectricityPanel.add(usernameField4);
        BuyElectricityPanel.add(passwordLabel4);
        BuyElectricityPanel.add(passwordField4);
        BuyElectricityPanel.add(BuyElectricityBackButton);
        BuyElectricityPanel.add(BuyElectricityButton12);

        JPanel PayTVPanel = new JPanel(new GridLayout(4, 2));
        JLabel usernameLabel5 = new JLabel("Tv Brand");
        JTextField usernameField5 = new JTextField();
        JLabel usernameLabel9 = new JLabel("amount");
        JTextField textField9 = new JTextField();
        JButton PayTVBackButton = new JButton("Back");
        JButton PayTVButton = new JButton("Pay");
        PayTVPanel.add(usernameLabel5);
        PayTVPanel.add(usernameField5);
        PayTVPanel.add(usernameLabel9);
        PayTVPanel.add(textField9);
        PayTVPanel.add(PayTVBackButton);
        PayTVPanel.add(PayTVButton);

        JPanel CustomerCarePanel = new JPanel(new GridLayout(7, 2));
       JLabel emailLabel = new JLabel("Email: support@example.com");
        JLabel hotlineLabel1 = new JLabel("Hotline 1: +1-800-123-4567");
       JLabel hotlineLabel2 = new JLabel("Hotline 2: +1-800-987-6543");
       JLabel hotlineLabel3 = new JLabel("Hotline 3: +1-800-555-1234");
       JLabel whatsappLabel = new JLabel("WhatsApp: +1-123-456-7890");
      JLabel  ussdLabel = new JLabel("USSD: *123# for self-service");

        JButton CustomerCareBackButton = new JButton("Back");
        JButton CustomerCareNextPage = new JButton("Next Page");
       CustomerCarePanel.add(emailLabel);
        CustomerCarePanel.add(new JLabel()); // Placeholder for alignment
        CustomerCarePanel.add(hotlineLabel1);
        CustomerCarePanel.add(new JLabel()); // Placeholder for alignment
        CustomerCarePanel.add(hotlineLabel2);
        CustomerCarePanel.add(new JLabel()); // Placeholder for alignment
        CustomerCarePanel.add(hotlineLabel3);
        CustomerCarePanel.add(new JLabel()); // Placeholder for alignment
        CustomerCarePanel.add(whatsappLabel);
        CustomerCarePanel.add(new JLabel()); // Placeholder for alignment
        CustomerCarePanel.add(ussdLabel);
        CustomerCarePanel.add(new JLabel()); // Placeholder for alignment

        CustomerCarePanel.add(CustomerCareBackButton);
        CustomerCarePanel.add(CustomerCareNextPage);

        JPanel ChangePasswordPanel = new JPanel(new GridLayout(3, 2));
        JLabel usernameLabel7 = new JLabel("old password");
        JTextField oldPasswordField = new JTextField();
        JLabel passwordLabel7 = new JLabel("new password");
        JPasswordField newPasswordField = new JPasswordField();
        JButton ChangePasswordBackButton = new JButton("Back");
        JButton ChangePasswordButton = new JButton("Change Password");
        ChangePasswordPanel.add(usernameLabel7);
        ChangePasswordPanel.add(oldPasswordField);
        ChangePasswordPanel.add(passwordLabel7);
        ChangePasswordPanel.add(newPasswordField);
        ChangePasswordPanel.add(ChangePasswordBackButton);
        ChangePasswordPanel.add(ChangePasswordButton);


        // Add login panel to the frame
        add(loginPanel, BorderLayout.CENTER);
        setVisible(true);

        // Login button action listener
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                if (checkPassword(username, password)) {
                    // If authentication is successful, switch to the home panel
                    remove(loginPanel);
                    add(homePanel, BorderLayout.CENTER);
                    revalidate();
                    repaint();
                } else {
                    JOptionPane.showMessageDialog(BankingApp.this, "Login failed. Incorrect username or password.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        // Send Money button action listener
        // Send Money button action listener
        sendMoneyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {



                remove(homePanel);
                add(SendMoneyPanel);
                revalidate();
                repaint();


            }
        });
        buyAirtimeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {



                remove(homePanel);
                add(BuyAirtimePanel);
                revalidate();
                repaint();


            }
        });
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String recipientIDField1 = recipientIDField.getText();
                RecepientUsername = recipientIDField1;

                Double AmountToSend = Double.parseDouble(amountField.getText());
                AmountToSend1 = AmountToSend;

                if (AmountToSend1 > Balancelogged) {
                    JOptionPane.showMessageDialog(null, "Sorry we can not send " + AmountToSend1 + " to " + RecepientUsername, "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    sendMoney();
                    JOptionPane.showMessageDialog(null, "Money sent successfully! Wait for confirmation message" + RecepientUsername, "Success", JOptionPane.INFORMATION_MESSAGE);
                }

                revalidate();
                repaint();

                System.exit(0);


            }

        });

        BuyElectricityButton12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                String recipientIDField4 = usernameField4.getText();
                RecepientUsername = recipientIDField4;

                Double AmountToSend = Double.parseDouble(passwordField4.getText());
                AmountToSend1 = AmountToSend;

                if (AmountToSend1 > Balancelogged) {
                    JOptionPane.showMessageDialog(null, "Sorry we can not send " + AmountToSend1 + " to " + RecepientUsername1, "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    buyElectricity();
                    JOptionPane.showMessageDialog(null, "Money sent successfully! Wait for confirmation message" + RecepientUsername, "Success", JOptionPane.INFORMATION_MESSAGE);
                }

                revalidate();
                repaint();

                System.exit(0);


            }});
        BuyAirtimeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                String recipientIDField2 = usernameField2.getText();
                RecepientUsername = recipientIDField2;

                Double AmountToSend = Double.parseDouble(passwordField2.getText());
                AmountToSend1 = AmountToSend;

                if (AmountToSend1 > Balancelogged) {
                    JOptionPane.showMessageDialog(null, "Sorry we can not send " + AmountToSend1 + " to " + RecepientUsername1, "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    buyAirtime();
                    JOptionPane.showMessageDialog(null, "Money sent successfully! Wait for confirmation message" + RecepientUsername, "Success", JOptionPane.INFORMATION_MESSAGE);
                }

                revalidate();
                repaint();

                System.exit(0);


            }});

        PayTVButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String recipientIDField2 = usernameField5.getText();
                RecepientUsername = recipientIDField2;

                Double AmountToSend = Double.parseDouble(textField9.getText());
                AmountToSend1 = AmountToSend;

                if (AmountToSend1 > Balancelogged) {
                    JOptionPane.showMessageDialog(null, "Sorry we can not send " + AmountToSend1 + " to " + RecepientUsername1, "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    payTV();
                    JOptionPane.showMessageDialog(null, "Money sent successfully! Wait for confirmation message" + RecepientUsername, "Success", JOptionPane.INFORMATION_MESSAGE);
                }

                revalidate();
                repaint();

                System.exit(0);



            }
        });



        CustomerCareBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(CustomerCarePanel);
                add(homePanel, BorderLayout.CENTER);
                revalidate();
                repaint();
            }
        });


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Go back to login panel

            }
        });

        SendMoneyBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(SendMoneyPanel);
                add(homePanel, BorderLayout.CENTER);
                revalidate();
                repaint();
            }
        });
        BuyElectricityBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(BuyElectricityPanel);
                add(homePanel, BorderLayout.CENTER);
                revalidate();
                repaint();
            }
        });

        BuyAirtimeBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(BuyAirtimePanel);
                add(homePanel);
                revalidate();
                repaint();


            }
        });
        PayTVBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(PayTVPanel);
                add(homePanel);
                revalidate();
                repaint();


            }
        });

        CheckBalanceBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(CheckBalancePanel);
                add(homePanel);
                revalidate();
                repaint();


            }
        });

        CheckBalanceNextPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


            }
        });

        ChangePasswordBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                remove(ChangePasswordPanel);
                add(homePanel);
                revalidate();
                repaint();


            }
        });
        ChangePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String oldPassword = oldPasswordField.getText();
                char[] newPasswordChars = newPasswordField.getPassword();
                newPassword = new String(newPasswordChars);

                if (newPassword.length() < 8) {
                    JOptionPane.showMessageDialog(null, "New password must be at least 8 characters long", "Password Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Encrypt the new password before saving it to the database
                 encryptedNewPassword = encryptPassword();

                // Update the password in the database
                boolean success = updatePasswordInDatabase();
                if (success) {
                    JOptionPane.showMessageDialog(null, "Password changed successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to change password", "Error", JOptionPane.ERROR_MESSAGE);
                }

                revalidate();
                repaint();

                System.exit(0);



            }
        });







        nextPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add logic to navigate to the next page
                // For example, you can create another panel and add it to the cardsPanel
            }
        });

        // Check Balance button action listener
        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Retrieve the balance from the database
                retrieveAmountFromDatabase();


                // Display the balance
                // Update the balance field on the panel
                balanceField.setText("$" + Balancelogged);


                // Add logic to navigate to the check balance page

                remove(homePanel);
                add(CheckBalancePanel);
                revalidate();
                repaint();


            }
        });
        buyElectricityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {





                remove(homePanel);
                add(BuyElectricityPanel);
                revalidate();
                repaint();

                // Add logic to navigate to the next page
                // For example, you can create another panel and add it to the cardsPanel
            }
        });


        // Helper class for storing account information




                // Buy Airtime button action listener


                // Pay TV button action listener
                payTVButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        remove(homePanel);
                        add(PayTVPanel);
                        revalidate();
                        repaint();

                        // Add logic to navigate to the pay TV page
                    }
                });

                // Contact Customer button action listener
                contactCustomerButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        remove(homePanel);
                        add(CustomerCarePanel);
                        revalidate();
                        repaint();


                        // Add logic to navigate to the contact customer page
                    }
                });

                // Change Password button action listener
                changePasswordButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        remove(homePanel);
                        add(ChangePasswordPanel);
                        revalidate();
                        repaint();

                        // Add logic to navigate to the change password page
                    }
                });
            }

            public static void main(String[] args) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new BankingApp();
                    }
                });
            }

            // Method to check password from database
            public static boolean checkPassword(String username, String password) {
                try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
                    String sql = "SELECT user_password FROM users WHERE Username = ?";
                    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                        pstmt.setString(1, username);
                        try (ResultSet rs = pstmt.executeQuery()) {
                            if (rs.next()) {
                                String storedHashedPassword = rs.getString("user_password");
                                String hashedInputPassword = hashPassword(password);

                                // Check if the stored hashed password matches the hashed input password
                                if (storedHashedPassword.equals(hashedInputPassword)) {
                                    return true; // Return true for successful login
                                }
                            }
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return false; // Return false for unsuccessful login
            }
    private static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(password.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to retrieve the amount from the database for the logged-in user
            private double retrieveAmountFromDatabase() {
                // Assuming you have stored the logged-in user's ID somewhere
                // Example user ID, replace with actual user ID

                double balance = 0.0;
                try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
                    String sql = "SELECT Balance FROM users WHERE Username = ?";

                    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                        pstmt.setString(1, loggedInUsername);
                        try (ResultSet rs = pstmt.executeQuery()) {
                            if (rs.next()) {
                                balance = rs.getDouble("Balance");
                                Balancelogged = balance;
                            } else {
                                System.out.println("No balance found for user ID: " + loggedInUsername);
                            }
                        }
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                return balance;

            }

            public static void sendMoney() {


                try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
                    // Prepare SQL statement to update sender's balance
                    String updateSenderBalanceSQL = "UPDATE users SET Balance = Balance - ? WHERE Username = ?";
                    PreparedStatement updateSenderBalanceStatement = connection.prepareStatement(updateSenderBalanceSQL);
                    updateSenderBalanceStatement.setDouble(1, AmountToSend1);
                    updateSenderBalanceStatement.setString(2, loggedInUsername); // Replace "sender_user_id" with actual sender's userID
                    updateSenderBalanceStatement.executeUpdate();

                    // Prepare SQL statement to update receiver's balance
                    String updateReceiverBalanceSQL = "UPDATE users SET Balance = Balance + ? WHERE Username = ?";
                    PreparedStatement updateReceiverBalanceStatement = connection.prepareStatement(updateReceiverBalanceSQL);
                    updateReceiverBalanceStatement.setDouble(1, AmountToSend1);
                    updateReceiverBalanceStatement.setString(2, RecepientUsername);
                    updateReceiverBalanceStatement.executeUpdate();

                    System.out.println("Successfully sent $" + AmountToSend1 + " to user with userID: " + RecepientUsername);
                } catch (SQLException e) {
                    System.out.println("Error occurred while sending money: " + e.getMessage());
                }
            }

            public static void buyAirtime() {


                try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
                    // Prepare SQL statement to update sender's balance
                    String updateSenderBalanceSQL = "UPDATE users SET Balance = Balance - ? WHERE Username = ?";
                    PreparedStatement updateSenderBalanceStatement = connection.prepareStatement(updateSenderBalanceSQL);
                    updateSenderBalanceStatement.setDouble(1, AmountToSend1);
                    updateSenderBalanceStatement.setString(2, loggedInUsername); // Replace "sender_user_id" with actual sender's userID
                    updateSenderBalanceStatement.executeUpdate();

                    // Prepare SQL statement to update receiver's balance
                    String updateReceiverBalanceSQL = "UPDATE airtime_brand SET amount = amount + ? WHERE name = ?";
                    PreparedStatement updateReceiverBalanceStatement = connection.prepareStatement(updateReceiverBalanceSQL);
                    updateReceiverBalanceStatement.setDouble(1, AmountToSend1);
                    updateReceiverBalanceStatement.setString(2, RecepientUsername);
                    updateReceiverBalanceStatement.executeUpdate();

                    System.out.println("Successfully sent $" + AmountToSend1 + " to user with userID: " + RecepientUsername);
                } catch (SQLException e) {
                    System.out.println("Error occurred while sending money: " + e.getMessage());
                }


            }

            public static void buyElectricity() {


                try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
                    // Prepare SQL statement to update sender's balance
                    String updateSenderBalanceSQL = "UPDATE users SET Balance = Balance - ? WHERE Username = ?";
                    PreparedStatement updateSenderBalanceStatement = connection.prepareStatement(updateSenderBalanceSQL);
                    updateSenderBalanceStatement.setDouble(1, AmountToSend1);
                    updateSenderBalanceStatement.setString(2, loggedInUsername); // Replace "sender_user_id" with actual sender's userID
                    updateSenderBalanceStatement.executeUpdate();

                    // Prepare SQL statement to update receiver's balance
                    String updateReceiverBalanceSQL = "UPDATE electricity_brand SET Amount = Amount + ? WHERE name = ?";
                    PreparedStatement updateReceiverBalanceStatement = connection.prepareStatement(updateReceiverBalanceSQL);
                    updateReceiverBalanceStatement.setDouble(1, AmountToSend1);
                    updateReceiverBalanceStatement.setString(2, RecepientUsername);
                    updateReceiverBalanceStatement.executeUpdate();

                    System.out.println("Successfully sent $" + AmountToSend1 + " to user with userID: " + RecepientUsername);
                } catch (SQLException e) {
                    System.out.println("Error occurred while sending money: " + e.getMessage());
                }


            }

    public static void payTV() {


        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            // Prepare SQL statement to update sender's balance
            String updateSenderBalanceSQL = "UPDATE users SET Balance = Balance - ? WHERE Username = ?";
            PreparedStatement updateSenderBalanceStatement = connection.prepareStatement(updateSenderBalanceSQL);
            updateSenderBalanceStatement.setDouble(1, AmountToSend1);
            updateSenderBalanceStatement.setString(2, loggedInUsername); // Replace "sender_user_id" with actual sender's userID
            updateSenderBalanceStatement.executeUpdate();

            // Prepare SQL statement to update receiver's balance
            String updateReceiverBalanceSQL = "UPDATE tv_brand SET Amount = Amount + ? WHERE name = ?";
            PreparedStatement updateReceiverBalanceStatement = connection.prepareStatement(updateReceiverBalanceSQL);
            updateReceiverBalanceStatement.setDouble(1, AmountToSend1);
            updateReceiverBalanceStatement.setString(2, RecepientUsername);
            updateReceiverBalanceStatement.executeUpdate();

            System.out.println("Successfully sent $" + AmountToSend1 + " to user with userID: " + RecepientUsername);
        } catch (SQLException e) {
            System.out.println("Error occurred while sending money: " + e.getMessage());
        }


    }
    private String encryptPassword() {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(newPassword.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            // Handle error
            return null;
        }
    }

    private boolean updatePasswordInDatabase() {

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String sql = "UPDATE users SET user_password = ? WHERE Username = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1,encryptedNewPassword );
                statement.setString(2, loggedInUsername);
                int rowsAffected = statement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle error
            return false;
        }
    }


}













