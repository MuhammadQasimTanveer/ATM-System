package ASimulatorSystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

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
        Image i2 = i1.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
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

            Conn c1 = new Conn();
            try 
            {
                c1.c.setAutoCommit(false);
                
                String q1 = "SELECT * FROM signup3 WHERE cardno = ? AND pin = ?";

                // Prepare the statement
                PreparedStatement ps = c1.c.prepareStatement(q1);
                ps.setString(1, cardno); // Set first placeholder
                ps.setString(2, pin);    // Set second placeholder

                // Execute the query
                ResultSet rs = ps.executeQuery();
                if(rs.next())
                {
                    // User exists in signup3, now check in login table
                    String q2 = "SELECT * FROM login WHERE cardno = ? AND pin=?";
                    PreparedStatement psLogin = c1.c.prepareStatement(q2);
                    
                    psLogin.setString(1, cardno);
                    psLogin.setString(2, pin);
                    
                    ResultSet rsLogin = psLogin.executeQuery();

                   if(rsLogin.next()) 
                   {
                      // User already exists in login → update PIN (optional)
                      String q3 = "UPDATE login SET login_time = CURRENT_TIMESTAMP WHERE cardno = ? AND pin=?";
                      PreparedStatement psUpdate = c1.c.prepareStatement(q3);
                  
                      psUpdate.setString(1, cardno);
                      psUpdate.setString(2, pin);
                    
                      psUpdate.executeUpdate();
                   } 
                   else 
                   {
                      // User does not exist in login, insert new record
                      String insertLogin = "INSERT INTO login(formno, cardno, pin) VALUES (?, ?, ?)";
                    
                      PreparedStatement psInsert = c1.c.prepareStatement(insertLogin);
                    
                      psInsert.setString(1, rs.getString("formNo")); // get formNo from signup3
                      psInsert.setString(2, cardno);
                      psInsert.setString(3, pin);
                    
                      psInsert.executeUpdate();
                   } 
                   
                   c1.c.commit();
                   c1.c.setAutoCommit(true);
                   
                    JOptionPane.showMessageDialog(
                        this,
                        "You have successfully logged in.",
                        "Login Successful",
                        JOptionPane.INFORMATION_MESSAGE
                    );
                    
                    setVisible(false);
                    new Transactions(pin, cardno).setVisible(true);
                }
                else
                {
                    JOptionPane.showMessageDialog(
                        this,
                        "Incorrect Card Number or PIN.",
                        "Invalid Credentials",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            } 
            catch (Exception e) 
            {
                try { 
                    c1.c.rollback(); 
                } 
                catch (SQLException rbEx) 
                { 
                    rbEx.printStackTrace(); 
                }
                e.printStackTrace();
                JOptionPane.showMessageDialog(
                        this,
                        "Something went wrong. Please try again.",
                        "System Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        } 
        else if (ae.getSource() == b2) 
        {
            tf1.setText("");
            pf2.setText("");
        } 
        else if (ae.getSource() == b3) 
        {
            setVisible(false);
            new Signup().setVisible(true);
        }
    }
}