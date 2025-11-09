package ASimulatorSystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Login extends JFrame implements ActionListener 
{
    JLabel l1, l2, l3;
    JTextField tf1;
    JPasswordField pf2;
    JButton b1, b2, b3;

    Color primaryColor = new Color(0x5B8FF9);      // Nice Blue
    Color backgroundColor = new Color(0xF5F7FA);   // Light Grey
    Color buttonColor = new Color(0x5B8FF9);       // Blue
    Color clearButtonColor = new Color(0xFF6B6B);  // Coral Red
    Color signupButtonColor = new Color(0x61CDBB); // Teal
    Color textColor = new Color(0x2C3E50);         // Dark Grey
    
    Font headingFont = new Font("Arial", Font.BOLD, 36);
    Font labelFont = new Font("Arial", Font.PLAIN, 20);
    Font textFont = new Font("Arial", Font.PLAIN, 16);
    Font buttonFont = new Font("Arial", Font.BOLD, 15);

    public Login() 
    {
        setTitle("AUTOMATED TELLER MACHINE");
        getContentPane().setBackground(backgroundColor);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; 
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 50, 40, 50));

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/profile.png"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(i2));
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(logoLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));


        l1 = new JLabel("WELCOME TO ATM", JLabel.CENTER);
        l1.setFont(headingFont);
        l1.setForeground(primaryColor);
        l1.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(l1);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints fgbc = new GridBagConstraints();
        fgbc.insets = new Insets(5, 5, 5, 5);
        fgbc.fill = GridBagConstraints.HORIZONTAL;
        fgbc.weightx = 1.0;

        l2 = new JLabel("Card No:");
        l2.setFont(labelFont);
        l2.setForeground(textColor);
        fgbc.gridx = 0; 
        fgbc.gridy = 0;
        fgbc.anchor = GridBagConstraints.EAST;
        formPanel.add(l2, fgbc);

        tf1 = new JTextField();
        tf1.setFont(textFont);
        tf1.setPreferredSize(new Dimension(200, 35));
        fgbc.gridx = 1; 
        fgbc.anchor = GridBagConstraints.WEST;
        formPanel.add(tf1, fgbc);

        l3 = new JLabel("PIN:");
        l3.setFont(labelFont);
        l3.setForeground(textColor);
        fgbc.gridx = 0; 
        fgbc.gridy = 1; 
        fgbc.anchor = GridBagConstraints.EAST;
        formPanel.add(l3, fgbc);

        pf2 = new JPasswordField();
        pf2.setFont(textFont);
        pf2.setPreferredSize(new Dimension(200, 35));
        fgbc.gridx = 1; 
        fgbc.anchor = GridBagConstraints.WEST;
        formPanel.add(pf2, fgbc);

        mainPanel.add(formPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 25)));

        
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        buttonsPanel.setBackground(Color.WHITE);

        b1 = new JButton("SIGN IN");
        styleButton(b1, buttonColor);
        b2 = new JButton("CLEAR");
        styleButton(b2, clearButtonColor);
        buttonsPanel.add(b1);
        buttonsPanel.add(b2);

        mainPanel.add(buttonsPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));

      
        b3 = new JButton("SIGN UP");
        styleButton(b3, signupButtonColor);
        b3.setPreferredSize(new Dimension(260, 35));
        JPanel signupPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        signupPanel.setBackground(Color.WHITE);
        signupPanel.add(b3);
        mainPanel.add(signupPanel);

        add(mainPanel, gbc);

        b1.setFocusPainted(false);
        b2.setFocusPainted(false);
        b3.setFocusPainted(false);
        
        b1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b3.setCursor(new Cursor(Cursor.HAND_CURSOR));

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);

        setSize(900, 620);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void styleButton(JButton btn, Color color) 
    {
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFont(buttonFont);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(120, 35));
    }

    public void actionPerformed(ActionEvent ae) 
    {
        if (ae.getSource() == b1) 
        {
            String cardno = tf1.getText().trim();
            String pin = pf2.getText().trim();

            if (cardno.isEmpty() || pin.isEmpty()) 
            {
                JOptionPane.showMessageDialog(this,
                        "Please enter both your Card Number and PIN.",
                        "Missing Information",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            JOptionPane.showMessageDialog(
                this,
                "Login successful!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
            );
            
            setVisible(false);
            //new Transactions(pin, cardno).setVisible(true);
        } 
        else if (ae.getSource() == b2) 
        {
            tf1.setText("");
            pf2.setText("");
        } 
        else if (ae.getSource() == b3) 
        {
            setVisible(false);
            //new Signup().setVisible(true);
        }
    }

    public static void main(String[] args) 
    {
        new Login().setVisible(true);
    }
}