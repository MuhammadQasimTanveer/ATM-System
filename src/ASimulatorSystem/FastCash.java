package ASimulatorSystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class FastCash extends JFrame implements ActionListener 
{
    JLabel l1;
    JButton b1, b2, b3, b4, b5, b6, b7;
    String pin;
    String cardno;

    FastCash(String pin, String cardno) 
    {
        this.pin = pin;
        this.cardno = cardno;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 700, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(0, 0, 900, 700);
        add(l3);

        l1 = new JLabel("SELECT WITHDRAWL AMOUNT");
        l1.setForeground(Color.WHITE);
        l1.setFont(new Font("System", Font.BOLD, 16));

        b1 = new JButton("Rs 100");
        b2 = new JButton("Rs 500");
        b3 = new JButton("Rs 1000");
        b4 = new JButton("Rs 2000");
        b5 = new JButton("Rs 5000");
        b6 = new JButton("Rs 10000");
        b7 = new JButton("BACK");

        setLayout(null);

        l1.setBounds(203, 140, 700, 35);
        l3.add(l1);

        b1.setBounds(153, 225, 150, 35);
        l3.add(b1);

        b2.setBounds(347, 225, 150, 35);
        l3.add(b2);

        b3.setBounds(153, 270, 150, 35);
        l3.add(b3);

        b4.setBounds(347, 270, 150, 35);
        l3.add(b4);

        b5.setBounds(153, 315, 150, 35);
        l3.add(b5);

        b6.setBounds(347, 315, 150, 35);
        l3.add(b6);

        b7.setBounds(347, 360, 150, 35);
        l3.add(b7);

        b1.setFocusPainted(false);
        b2.setFocusPainted(false);
        b3.setFocusPainted(false);
        b4.setFocusPainted(false);
        b5.setFocusPainted(false);
        b6.setFocusPainted(false);
        b7.setFocusPainted(false);
        
        b1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b4.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b5.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b6.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b7.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
        b7.addActionListener(this);

        setSize(900, 700);
        setLocation(232, 0);
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) 
    {
        if (ae.getSource() == b7) 
        {
            setVisible(false);
            new Transactions(pin, cardno).setVisible(true);
            return;
        }

        String amountStr = ((JButton) ae.getSource()).getText().substring(3);
        double amount = Double.parseDouble(amountStr);

        Conn c1 = new Conn();

        try 
        {
            c1.c.setAutoCommit(false);

            // 1. Fetch current balance from users table
            String balanceQuery = "SELECT currentBalance FROM users WHERE cardno =? AND pin = ?";
            PreparedStatement ps1 = c1.c.prepareStatement(balanceQuery);
            ps1.setString(1, cardno);
            ps1.setString(2, pin);
            ResultSet rs = ps1.executeQuery();

            double currentBalance = 0;
            if (rs.next()) {
                currentBalance = rs.getDouble("currentBalance");
            }

            // 2. Check if balance is sufficient
            if (currentBalance < amount) 
            {
                JOptionPane.showMessageDialog(
                   this,
                   "Insufficient balance to perform this transaction.",
                   "Transaction Failed",
                   JOptionPane.WARNING_MESSAGE
                );
                return;
            }
            
            // 3. Deduct amount from users table
            String updateBalance = "UPDATE users SET currentBalance = currentBalance - ? WHERE cardno =? AND pin = ?";
            PreparedStatement ps2 = c1.c.prepareStatement(updateBalance);

            ps2.setDouble(1, amount);
            ps2.setString(2, cardno);
            ps2.setString(3, pin);

            ps2.executeUpdate();

            // 4. Insert transaction record
            String insertTransaction = "INSERT INTO transactions (cardno, transactionAmount, type) VALUES (?, ?, ?)";
            PreparedStatement ps3 = c1.c.prepareStatement(insertTransaction);

            ps3.setString(1, cardno);
            ps3.setDouble(2, amount);
            ps3.setString(3, "Withdrawal");

            ps3.executeUpdate();

            c1.c.commit();
            c1.c.setAutoCommit(true);

            JOptionPane.showMessageDialog(
               this,
               "Withdrawal Successful\n\n" +
               "Amount Debited: Rs. " + amount + "\n" +
               "Available Balance: Rs. " + (currentBalance - amount),
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

    public static void main(String[] args) 
    {
        new FastCash("", "").setVisible(true);
    }
}