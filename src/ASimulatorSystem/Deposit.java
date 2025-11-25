package ASimulatorSystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.sql.*;

public class Deposit extends JFrame implements ActionListener 
{
    JTextField t1;
    JButton b1, b2;
    JLabel l1;
    String pin;
    String cardno;

    Deposit(String pin, String cardno) 
    {
        this.pin = pin;
        this.cardno = cardno;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 700, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(0, 0, 900, 700);
        add(l3);

        l1 = new JLabel("ENTER AMOUNT YOU WANT TO DEPOSIT");
        l1.setForeground(Color.WHITE);
        l1.setFont(new Font("System", Font.BOLD, 15));

        t1 = new JTextField();
        t1.setFont(new Font("Raleway", Font.BOLD, 17));

        b1 = new JButton("DEPOSIT");
        b2 = new JButton("BACK");

        setLayout(null);

        l1.setBounds(180, 160, 400, 20);
        l3.add(l1);

        t1.setBounds(170, 220, 320, 32);
        l3.add(t1);

        b1.setBounds(350, 305, 150, 35);
        l3.add(b1);

        b2.setBounds(350, 354, 150, 35);
        l3.add(b2);

        b1.setFocusPainted(false);
        b2.setFocusPainted(false);
        
        b1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        b1.addActionListener(this);
        b2.addActionListener(this);

        setSize(900, 700);
        setLocation(232, 0);
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) 
    {
        String depositamount = t1.getText().trim();

        String moneyRegex = "^[0-9]+(\\.[0-9]{1,2})?$";

        if (ae.getSource() == b1) 
        {
            if (depositamount.isEmpty()) 
            {
                JOptionPane.showMessageDialog(
                        this,
                        "Please enter a valid amount to deposit.",
                        "Input Required",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            if (!depositamount.matches(moneyRegex)) 
            {
                JOptionPane.showMessageDialog(
                        this,
                        "Please enter a valid amount.",
                        "Invalid Amount",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            double amount = Double.parseDouble(depositamount);
            if (amount <= 0) 
            {
                JOptionPane.showMessageDialog(
                        this,
                        "Deposit Amount must be greater than zero.",
                        "Invalid Amount",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            if (amount > 100000) 
            {
                JOptionPane.showMessageDialog(
                        this,
                        "Maximum deposit limit is Rs. 100,000 per transaction.",
                        "Limit Exceeded",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            Conn c1 = new Conn();

            try 
            {
                c1.c.setAutoCommit(false);

                // 1. First determine the current balance 
                String balanceQuery = "SELECT currentBalance FROM users WHERE cardno =? AND pin = ?";
                PreparedStatement ps1 = c1.c.prepareStatement(balanceQuery);

                ps1.setString(1, cardno);
                ps1.setString(2, pin);

                ResultSet rs = ps1.executeQuery();

                double currentBalance = 0.0;
                if (rs.next()) {
                    //getDouble() is a prebuilt method in Java’s ResultSet class
                    currentBalance = rs.getDouble("currentBalance");
                }

                // 2. Calculate the new balance
                double newBalance = currentBalance + amount;

                // 3. Insert into transactions table 
                String insertQuery = "INSERT INTO transactions (cardno, transactionAmount, type) VALUES (?, ?, ?)";
                PreparedStatement ps2 = c1.c.prepareStatement(insertQuery);

                ps2.setString(1, cardno);
                ps2.setDouble(2, amount);
                ps2.setString(3, "Deposit");

                ps2.executeUpdate();

                // 4. Update balance in users table
                String updateQuery = "UPDATE users SET currentBalance = ? WHERE cardno =? AND pin = ?";
                PreparedStatement ps3 = c1.c.prepareStatement(updateQuery);

                ps3.setDouble(1, newBalance);
                ps3.setString(2, cardno);
                ps3.setString(3, pin);

                ps3.executeUpdate();

                c1.c.commit();
                c1.c.setAutoCommit(true);

                JOptionPane.showMessageDialog(
                        this,
                        "Deposit Successful\n\n"
                        + "Amount Debited: Rs. " + amount + "\n"
                        + "Available Balance: Rs. " + newBalance,
                        "Transaction Successful",
                        JOptionPane.INFORMATION_MESSAGE
                );

                setVisible(false);
                new Transactions(pin, cardno).setVisible(true);
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

                System.out.println(e.getMessage());
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
            setVisible(false);
            new Transactions(pin, cardno).setVisible(true);
        }
    }

    public static void main(String[] args) 
    {
        new Deposit("", "").setVisible(true);
    }
}