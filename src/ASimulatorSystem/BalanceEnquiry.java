package ASimulatorSystem;

import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.*;
import javax.swing.*;
import java.util.*;

class BalanceEnquiry extends JFrame implements ActionListener 
{
    JButton b1;
    JLabel l1;
    String pin;
    String cardno;

    BalanceEnquiry(String pin, String cardno) 
    {
        this.pin = pin;
        this.cardno = cardno;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 700, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l2 = new JLabel(i3);
        l2.setBounds(0, 0, 900, 700);
        add(l2);

        l1 = new JLabel();
        l1.setForeground(Color.WHITE);
        l1.setFont(new Font("System", Font.BOLD, 16));

        b1 = new JButton("BACK");
        b1.setFocusPainted(false);
        
        b1.setCursor(new Cursor(Cursor.HAND_CURSOR));

        setLayout(null);

        l1.setBounds(180, 190, 400, 35);
        l2.add(l1);

        b1.setBounds(320, 333, 150, 35);
        l2.add(b1);

        try 
        {
            Conn c1 = new Conn();

            // Fetch balance from users table
            String q = "SELECT currentBalance FROM users WHERE cardno =? AND pin = ?";
            PreparedStatement ps = c1.c.prepareStatement(q);
            ps.setString(1, cardno);
            ps.setString(2, pin);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                double balance = rs.getDouble("currentBalance");
                l1.setText(
                    "<html><div style='text-align:center;'>"
                    + "Your Current Account Balance is<br>"
                    + "<b>Rs " + balance + "</b>"
                    + "</div></html>"
                );
            }

        } 
        catch (Exception e) 
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                    this,
                    "Something went wrong. Please try again.",
                    "System Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }

        b1.addActionListener(this);

        setSize(900, 700);
        setLocation(232, 0);
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) 
    {
        setVisible(false);
        new Transactions(pin, cardno).setVisible(true);
    }

    public static void main(String[] args) 
    {
        new BalanceEnquiry("", "").setVisible(true);
    }
}
