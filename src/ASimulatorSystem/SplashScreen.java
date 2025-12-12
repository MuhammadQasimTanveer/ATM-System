package ASimulatorSystem;

import javax.swing.*;
import java.awt.*;

public class SplashScreen extends JFrame implements Runnable {

    Thread thread;
    JLabel statusLabel;

    private final Color backgroundColor = new Color(0xF5F7FA);
    private final Color textColor = new Color(0x2C3E50);

    public SplashScreen() 
    {
        setLayout(new BorderLayout());
        getContentPane().setBackground(backgroundColor);
        setUndecorated(true);
        setSize(600, 500);

        JPanel panel = new JPanel();
        panel.setBackground(backgroundColor);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(60, 0, 0, 20));

        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("images/splashlogo.png"));
        Image scaled = img.getImage().getScaledInstance(330, 250, Image.SCALE_SMOOTH);
        JLabel logo = new JLabel(new ImageIcon(scaled));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);

        statusLabel = new JLabel("Starting ATM System...");
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        statusLabel.setForeground(textColor);
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add components
        panel.add(logo);
        panel.add(Box.createVerticalStrut(40));  //creates 40 pixels of empty vertical space and adds to panel
        panel.add(statusLabel);

        add(panel, BorderLayout.CENTER);
        setLocationRelativeTo(null);

        setVisible(true);
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() 
    {
        try 
        {
            String[] messages = { "Loading ATM Interface...", "Preparing Dashboard...",
                "Fetching Transactions...", "Finalizing Setup...","Welcome!"
            };

            for (String msg : messages) 
            {
                statusLabel.setText(msg);
                Thread.sleep(800);
            }
            Thread.sleep(500); // small pause before opening login

            setVisible(false);
            new Login().setVisible(true);

        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) 
    {
        new SplashScreen();
    }
}