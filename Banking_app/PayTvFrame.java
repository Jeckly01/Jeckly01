package app;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PayTvFrame extends JFrame {

    public PayTvFrame() {
        setTitle("Send Money");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose this frame only
        setLocationRelativeTo(null); // Center the frame

        // Create components for the send money page
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel titleLabel = new JLabel("Send Money");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);

        // Add more components as needed for the send money functionality

        // Add panel to the frame
        add(panel);

        setVisible(true);
    }
}


