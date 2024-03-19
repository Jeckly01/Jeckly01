package app;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

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
    public static Double Balancelogged;
 public static Double newBal;
 private static double amountToSend1;
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
        JPanel homePanel = new JPanel(new GridLayout(5, 1));
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
        homePanel.add(backButton);
        homePanel.add(nextPageButton);

        JPanel SendMoneyPanel = new JPanel(new GridLayout(5, 2));
        JLabel recipientLabel = new JLabel("Recipient Account ID:");
        JTextField recipientField = new JTextField();
        JLabel amountLabel = new JLabel("Amount to Send:");
        JTextField amountField = new JTextField();
        JButton sendButton = new JButton("Send");
        JButton SendMoneyBackButton = new JButton("Back");
        JButton SendMoneyNextPageButton = new JButton("Next Page");

        SendMoneyPanel.add(recipientLabel);
        SendMoneyPanel.add(recipientField);
        SendMoneyPanel.add(amountLabel);
        SendMoneyPanel.add(amountField);
        SendMoneyPanel.add(sendButton);
        SendMoneyPanel.add(SendMoneyBackButton);
        SendMoneyPanel.add(SendMoneyNextPageButton);

        JPanel BuyAirtimePanel = new JPanel(new GridLayout(3, 2));
        JLabel usernameLabel2 = new JLabel("Airtime brand");
        JTextField usernameField2 = new JTextField();
        JLabel passwordLabel2 = new JLabel("amount");
        JPasswordField passwordField2 = new JPasswordField();
        JButton BuyAirtimeBackButton = new JButton("Back");
        JButton BuyAirtimeNextPageButton = new JButton("Next Page");
        BuyAirtimePanel.add(usernameLabel2);
        BuyAirtimePanel.add(usernameField2);
        BuyAirtimePanel.add(passwordLabel2);
        BuyAirtimePanel.add(passwordField2);
        BuyAirtimePanel.add(BuyAirtimeBackButton);
        BuyAirtimePanel.add(BuyAirtimeNextPageButton);

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
        JLabel usernameLabel4 = new JLabel("To buy Electricity");
        JTextField usernameField4 = new JTextField();
        JLabel passwordLabel4 = new JLabel("amount");
        JPasswordField passwordField4 = new JPasswordField();
        JButton BuyElectricityBackButton = new JButton("Back");
        JButton BuyElectricityNextPage = new JButton("Next Page");
        BuyElectricityPanel.add(usernameLabel4);
        BuyElectricityPanel.add(usernameField4);
        BuyElectricityPanel.add(passwordLabel4);
        BuyElectricityPanel.add(passwordField4);
        BuyElectricityPanel.add(BuyElectricityBackButton);
        BuyElectricityPanel.add(BuyElectricityNextPage);

        JPanel PayTVPanel = new JPanel(new GridLayout(3, 2));
        JLabel usernameLabel5 = new JLabel("To PayTv");
        JTextField usernameField5 = new JTextField();
        JLabel passwordLabel5 = new JLabel("amount");
        JPasswordField passwordField5 = new JPasswordField();
        JButton PayTVBackButton = new JButton("Back");
        JButton PayTVNextPage = new JButton("Next Page");
        PayTVPanel.add(usernameLabel5);
        PayTVPanel.add(usernameField5);
        PayTVPanel.add(passwordLabel5);
        PayTVPanel.add(passwordField5);
        PayTVPanel.add(PayTVBackButton);
        PayTVPanel.add(PayTVNextPage);

        JPanel CustomerCarePanel = new JPanel(new GridLayout(3, 2));
        JLabel usernameLabel6 = new JLabel("contact");
        JTextField usernameField6 = new JTextField();
        JLabel passwordLabel6 = new JLabel("input email");
        JPasswordField passwordField6 = new JPasswordField();
        JButton CustomerCareBackButton = new JButton("Back");
        JButton CustomerCareNextPage = new JButton("Next Page");
        CustomerCarePanel.add(usernameLabel6);
        CustomerCarePanel.add(usernameField6);
        CustomerCarePanel.add(passwordLabel6);
        CustomerCarePanel.add(passwordField6);
        CustomerCarePanel.add(CustomerCareBackButton);
        CustomerCarePanel.add(CustomerCareNextPage);

        JPanel ChangePasswordPanel = new JPanel(new GridLayout(3, 2));
        JLabel usernameLabel7 = new JLabel("old password");
        JTextField usernameField7 = new JTextField();
        JLabel passwordLabel7 = new JLabel("new password");
        JPasswordField passwordField7 = new JPasswordField();
        JButton ChangePasswordBackButton = new JButton("Back");
        JButton ChangePasswordNextPage = new JButton("Next Page");
        ChangePasswordPanel.add(usernameLabel7);
        ChangePasswordPanel.add(usernameField7);
        ChangePasswordPanel.add(passwordLabel7);
        ChangePasswordPanel.add(passwordField7);
        ChangePasswordPanel.add(ChangePasswordBackButton);
        ChangePasswordPanel.add(ChangePasswordNextPage);


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

                sendButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {


                        sendMoney("jeckly01",10);

                        JOptionPane.showMessageDialog(null, "Money sent successfully! Wait for confirmation message", "Success", JOptionPane.INFORMATION_MESSAGE);


                        revalidate();
                        repaint();

                        System.exit(0);


                    }

                });




                remove(homePanel);
                add(SendMoneyPanel);
                revalidate();
                repaint();


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

        ChangePasswordNextPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


        SendMoneyNextPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        BuyElectricityNextPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        PayTVNextPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        BuyAirtimeNextPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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


        // Helper class for storing account information




        buyElectricityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                remove(homePanel);
                add(BuyElectricityPanel);
                revalidate();
                repaint();


                // Add logic to navigate to the check balance page
            }
        });


        // Buy Airtime button action listener
        buyAirtimeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                remove(homePanel);
                add(BuyAirtimePanel);
                revalidate();
                repaint();


            }
        });


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

                        String storedPassword = rs.getString("user_password");
                        // Check if the stored password matches the input password
                        if (storedPassword.equals(password)) {
                            loggedInUsername = username; // Store the logged-in username
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

    public static void sendMoney(String receiverUserId, double amount) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            // Prepare SQL statement to update sender's balance
            String updateSenderBalanceSQL = "UPDATE users SET Balance = Balance - ? WHERE Username = ?";
            PreparedStatement updateSenderBalanceStatement = connection.prepareStatement(updateSenderBalanceSQL);
            updateSenderBalanceStatement.setDouble(1, amount);
            updateSenderBalanceStatement.setString(2,loggedInUsername); // Replace "sender_user_id" with actual sender's userID
            updateSenderBalanceStatement.executeUpdate();

            // Prepare SQL statement to update receiver's balance
            String updateReceiverBalanceSQL = "UPDATE users SET Balance = Balance + ? WHERE Username = ?";
            PreparedStatement updateReceiverBalanceStatement = connection.prepareStatement(updateReceiverBalanceSQL);
            updateReceiverBalanceStatement.setDouble(1, amount);
            updateReceiverBalanceStatement.setString(2, receiverUserId);
            updateReceiverBalanceStatement.executeUpdate();

            System.out.println("Successfully sent $" + amount + " to user with userID: " + receiverUserId);
        } catch (SQLException e) {
            System.out.println("Error occurred while sending money: " + e.getMessage());
        }
    }

}








