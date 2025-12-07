package ASimulatorSystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Pin extends JFrame implements ActionListener 
{
    JPasswordField t1, t2;
    JButton b1, b2;
    JLabel l1, l2, l3;
    String pin;
    String cardno;

    Pin(String pin, String cardno) 
    {
        this.pin = pin;
        this.cardno = cardno;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 700, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l4 = new JLabel(i3);
        l4.setBounds(0, 0, 900, 700);
        add(l4);

        l1 = new JLabel("CHANGE YOUR PIN");
        l1.setFont(new Font("System", Font.BOLD, 18));
        l1.setForeground(Color.WHITE);

        l2 = new JLabel("New PIN:");
        l2.setFont(new Font("System", Font.BOLD, 15));
        l2.setForeground(Color.WHITE);

        l3 = new JLabel("Re-Enter New PIN:");
        l3.setFont(new Font("System", Font.BOLD, 15));
        l3.setForeground(Color.WHITE);

        t1 = new JPasswordField();
        t1.setFont(new Font("Raleway", Font.BOLD, 20));

        t2 = new JPasswordField();
        t2.setFont(new Font("Raleway", Font.BOLD, 20));

        b1 = new JButton("CHANGE");
        b2 = new JButton("BACK");
        
        b1.setFocusPainted(false);
        b2.setFocusPainted(false);
        
        b1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b2.setCursor(new Cursor(Cursor.HAND_CURSOR));

        b1.addActionListener(this);
        b2.addActionListener(this);

        setLayout(null);

        l1.setBounds(234, 137, 800, 35);
        l4.add(l1);

        l2.setBounds(161, 205, 150, 35);
        l4.add(l2);

        l3.setBounds(161, 248, 200, 35);
        l4.add(l3);

        t1.setBounds(320, 205, 180, 25);
        l4.add(t1);

        t2.setBounds(320, 248, 180, 25);
        l4.add(t2);

        b1.setBounds(349, 317, 150, 35);
        l4.add(b1);

        b2.setBounds(349, 359, 150, 35);
        l4.add(b2);

        setSize(900, 700);
        setLocation(232, 0);
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        String pin1 = t1.getText().trim();
        String pin2 = t2.getText().trim();

        if (ae.getSource() == b1) 
        {
            if (pin1.isEmpty()) 
            {
                JOptionPane.showMessageDialog(
                        this,
                        "Please enter your new PIN.",
                        "Input Required",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            if (pin2.isEmpty()) 
            {
                JOptionPane.showMessageDialog(
                        this,
                        "Please re-enter your new PIN.",
                        "Input Required",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            if (!pin1.equals(pin2)) 
            {
                JOptionPane.showMessageDialog(
                        this,
                        "<html><div style='text-align:center;'>"
                        + "<h3 style='color:red;'>PIN Mismatch</h3>"
                        + "<p>The PINs entered do not match.<br>Please try again.</p>"
                        + "</div></html>",
                        "Mismatch Error",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            Conn c1 = new Conn();

            try 
            {
                c1.c.setAutoCommit(false);

                String q1 = "UPDATE users SET pin = ? WHERE pin = ?";
                String q2 = "UPDATE login SET pin = ? WHERE pin = ?";
                String q3 = "UPDATE signup3 SET pin = ? WHERE pin = ?";

                PreparedStatement ps1 = c1.c.prepareStatement(q1);
                ps1.setString(1, pin2);
                ps1.setString(2, pin);
                ps1.executeUpdate();

                PreparedStatement ps2 = c1.c.prepareStatement(q2);
                ps2.setString(1, pin2);
                ps2.setString(2, pin);
                ps2.executeUpdate();

                PreparedStatement ps3 = c1.c.prepareStatement(q3);
                ps3.setString(1, pin2);
                ps3.setString(2, pin);
                ps3.executeUpdate();

                c1.c.commit();
                c1.c.setAutoCommit(true);

                JOptionPane.showMessageDialog(
                    this,
                    "PIN changed successfully.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
                );
                setVisible(false);
                new Transactions(pin2, cardno).setVisible(true);

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
            new Transactions(pin, cardno).setVisible(true);
            setVisible(false);
        }
    }
}