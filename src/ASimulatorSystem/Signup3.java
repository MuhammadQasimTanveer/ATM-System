package ASimulatorSystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.*;

public class Signup3 extends JFrame implements ActionListener 
{

    JLabel l1, l2, l10, cardPinMessage;
    JRadioButton r1, r2, r3, r4;
    JButton b1, b2;
    JCheckBox c1, c2, c3, c4;
    String formno;

    Color primaryColor = new Color(0x5B8FF9);
    Color backgroundColor = new Color(0xF5F7FA);
    Color buttonColor = new Color(0x5B8FF9);
    Color cancelButtonColor = new Color(0xFF6B6B);
    Color textColor = new Color(0x2C3E50);
    Color infoColor = new Color(0x64748B);

    Font headingFont = new Font("Arial", Font.BOLD, 30);
    Font subHeadingFont = new Font("Arial", Font.PLAIN, 15);
    Font labelFont = new Font("Arial", Font.BOLD, 16);
    Font textFont = new Font("Arial", Font.PLAIN, 14);
    Font infoFont = new Font("Arial", Font.PLAIN, 12);
    Font buttonFont = new Font("Arial", Font.BOLD, 15);

    Signup3(String formno) 
    {
        this.formno = formno;
        setTitle("Bank Account Signup - Step 3");
        getContentPane().setBackground(backgroundColor);

        setLayout(new GridBagLayout());
        GridBagConstraints gbcOuter = new GridBagConstraints();
        gbcOuter.gridx = 0;
        gbcOuter.gridy = 0;
        gbcOuter.anchor = GridBagConstraints.CENTER;

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(25, 70, 25, 70));

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("imagesicons/bank.png"));
        Image i2 = i1.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(i2));
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(logoLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 14)));

        l1 = new JLabel("ACCOUNT DETAILS", JLabel.CENTER);
        l1.setFont(headingFont);
        l1.setForeground(primaryColor);
        l1.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(l1);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 6)));

        JLabel subtitle = new JLabel("Step 3 of 3", JLabel.CENTER);
        subtitle.setFont(subHeadingFont);
        subtitle.setForeground(textColor);
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(subtitle);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 22))); 

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 8, 6, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

      
        l2 = new JLabel("Account Type:");
        l2.setFont(labelFont);
        l2.setForeground(textColor);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        formPanel.add(l2, gbc);

        JPanel accountTypePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        accountTypePanel.setBackground(Color.WHITE);
        r1 = new JRadioButton("Saving");
        r1.setFont(textFont);
        r1.setBackground(Color.WHITE);
        r2 = new JRadioButton("Fixed Deposit");
        r2.setFont(textFont);
        r2.setBackground(Color.WHITE);
        r3 = new JRadioButton("Current");
        r3.setFont(textFont);
        r3.setBackground(Color.WHITE);
        r4 = new JRadioButton("Recurring Deposit");
        r4.setFont(textFont);
        r4.setBackground(Color.WHITE);
        ButtonGroup groupAccount = new ButtonGroup();
        groupAccount.add(r1);
        groupAccount.add(r2);
        groupAccount.add(r3);
        groupAccount.add(r4);
        accountTypePanel.add(r1);
        accountTypePanel.add(r2);
        accountTypePanel.add(r3);
        accountTypePanel.add(r4);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 8, 14, 8);
        formPanel.add(accountTypePanel, gbc);

        r1.setFocusPainted(false);
        r2.setFocusPainted(false);
        r3.setFocusPainted(false);
        r4.setFocusPainted(false);

        
        gbc.gridy = 2;
        gbc.insets = new Insets(10, 8, 4, 8);
        l10 = new JLabel("Services Offered:");
        l10.setFont(labelFont);
        l10.setForeground(textColor);
        formPanel.add(l10, gbc);

        JPanel servicesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        servicesPanel.setBackground(Color.WHITE);
        c1 = new JCheckBox("ATM Card", true);
        c1.setBackground(Color.WHITE);
        c1.setFont(textFont);
        c1.setEnabled(false);
        c2 = new JCheckBox("Mobile Banking", true);
        c2.setBackground(Color.WHITE);
        c2.setFont(textFont);
        c2.setEnabled(false);
        c3 = new JCheckBox("Transaction Notifications", true);
        c3.setBackground(Color.WHITE);
        c3.setFont(textFont);
        c3.setEnabled(false);
        servicesPanel.add(c1);
        servicesPanel.add(c2);
        servicesPanel.add(c3);
        gbc.gridy = 3;
        gbc.insets = new Insets(0, 8, 12, 8);
        formPanel.add(servicesPanel, gbc);

        mainPanel.add(formPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 16)));

        cardPinMessage = new JLabel(
                "<html><div style='text-align:center;'>"
                + "(A 16-digit card number and 4-digit PIN will be generated after submission)"
                + "</div></html>", JLabel.CENTER);
        cardPinMessage.setFont(infoFont);
        cardPinMessage.setOpaque(true);
        cardPinMessage.setBackground(new Color(0xFFF3CD));
        cardPinMessage.setForeground(new Color(0x856404));
        cardPinMessage.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        cardPinMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(cardPinMessage);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 22)));

        
        c4 = new JCheckBox("I declare that the above details are correct to the best of my knowledge.", false);
        c4.setBackground(Color.WHITE);
        c4.setFont(textFont);
        c4.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(c4);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 22))); 

        
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonsPanel.setBackground(Color.WHITE);
        b1 = new JButton("SUBMIT");
        b1.setFont(buttonFont);
        b1.setBackground(buttonColor);
        b1.setForeground(Color.WHITE);
        b1.setFocusPainted(false);
        b1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b1.setPreferredSize(new Dimension(130, 36));
        b2 = new JButton("CANCEL");
        b2.setFont(buttonFont);
        b2.setBackground(cancelButtonColor);
        b2.setForeground(Color.WHITE);
        b2.setFocusPainted(false);
        b2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b2.setPreferredSize(new Dimension(130, 36));
        buttonsPanel.add(b1);
        buttonsPanel.add(b2);
        mainPanel.add(buttonsPanel);

        b1.addActionListener(this);
        b2.addActionListener(this);

        add(mainPanel, gbcOuter);
        setSize(850, 650); 
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) 
    {
        String atype = null;
        if (r1.isSelected()) 
        {
            atype = "Saving Account";
        } 
        else if (r2.isSelected()) 
        {
            atype = "Fixed Deposit Account";
        } 
        else if (r3.isSelected()) 
        {
            atype = "Current Account";
        } 
        else if (r4.isSelected()) {
            atype = "Recurring Deposit Account";
        }

        Random ran = new Random();
        long first7 = (ran.nextLong() % 90000000L) + 5040936000000000L;
        String cardno = "" + Math.abs(first7);
        long first3 = (ran.nextLong() % 9000L) + 1000L;
        String pin = "" + Math.abs(first3);

        if (ae.getSource() == b1) 
        {
            if (atype == null) 
            {
                JOptionPane.showMessageDialog(this, "Please select an account type to proceed.", "Missing Information", JOptionPane.WARNING_MESSAGE);
                return;
            } 
            else if (!c4.isSelected()) 
            {
                JOptionPane.showMessageDialog(this, "You must accept the declaration to continue.", "Declaration Required", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Conn c1 = new Conn();

            try 
            {
                c1.c.setAutoCommit(false);
                String q1 = "INSERT INTO signup3(formNo, accountType, cardno, pin) VALUES (?, ?, ?, ?)";
                // Prepare the statement using the connection object
                PreparedStatement ps1 = c1.c.prepareStatement(q1);   // c1.c refers to your Connection object
                
                // Set the values for each placeholder in the SQL query
                ps1.setString(1, formno);
                ps1.setString(2, atype);
                ps1.setString(3, cardno);
                ps1.setString(4, pin);
                ps1.executeUpdate();    // Execute the insert query

                String[] services = {"ATM Card", "Mobile Banking", "Transaction Notifications"};
                for (String service : services) 
                {
                    String q2 = "INSERT INTO account_services(formNo, serviceName) VALUES (?, ?)";
                    PreparedStatement ps2 = c1.c.prepareStatement(q2);
                    ps2.setString(1, formno);
                    ps2.setString(2, service);
                    ps2.executeUpdate();
                }

                String q3 = "INSERT INTO users(cardno, pin) VALUES (?, ?)";
                PreparedStatement psUsers = c1.c.prepareStatement(q3);
                psUsers.setString(1, cardno);
                psUsers.setString(2, pin);
                psUsers.executeUpdate();

                c1.c.commit();
                c1.c.setAutoCommit(true);

                JOptionPane.showMessageDialog(this, 
                        "<html><div style='text-align:center;'>"
                        + "<h2 style='color:green;'>Account Created Successfully!</h2><br>"
                        + "<p><b>Card Number:</b> " + cardno + "<br><br>"
                        + "<b>PIN:</b> " + pin + "</p></div></html>",
                        "Success", 
                        JOptionPane.INFORMATION_MESSAGE
                );

                new Login().setVisible(true);
                setVisible(false);
            } 
            catch (Exception e) 
            {
                try 
                {
                    c1.c.rollback();
                } 
                catch (SQLException rbEx) 
                {
                    rbEx.printStackTrace();
                }
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Something went wrong. Please try again.", "System Error", JOptionPane.ERROR_MESSAGE);
            }
        } 
        else if (ae.getSource() == b2) 
        {
            System.exit(0);
        }
    }

    public static void main(String[] args) 
    {
        new Signup3("").setVisible(true);
    }
}