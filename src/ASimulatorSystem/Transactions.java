package ASimulatorSystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Transactions extends JFrame implements ActionListener{

    JLabel l1;
    JButton b1,b2,b3,b4,b5,b6,b7;
    String pin;
    String cardno;
    
    Transactions(String pin, String cardno)
    {
        this.pin = pin;
        this.cardno = cardno;
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 700, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l2 = new JLabel(i3);
        l2.setBounds(0, 0, 900, 700);
        add(l2);
        
        l1 = new JLabel("Please Select Your Transaction");
        l1.setForeground(Color.WHITE);
        l1.setFont(new Font("System", Font.BOLD, 17));
        
        b1 = new JButton("DEPOSIT");
        b2 = new JButton("CASH WITHDRAWL");
        b3 = new JButton("FAST CASH");
        b4 = new JButton("MINI STATEMENT");
        b5 = new JButton("PIN CHANGE");
        b6 = new JButton("BALANCE ENQUIRY");
        b7 = new JButton("EXIT");
        
        setLayout(null);
        
        l1.setBounds(200,135,700,35);
        l2.add(l1);
        
        b1.setBounds(153,230,150,35);
        l2.add(b1);
        
        b2.setBounds(347,230,150,35);
        l2.add(b2);
        
        b3.setBounds(153,274,150,35);
        l2.add(b3);
        
        b4.setBounds(347,274,150,35);
        l2.add(b4);
        
        b5.setBounds(153,319,150,35);
        l2.add(b5);
        
        b6.setBounds(347,319,150,35);
        l2.add(b6);
        
        b7.setBounds(347,364,150,35);
        l2.add(b7);
        
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
        
        setSize(900,700);
        setLocation(232,0);
        setUndecorated(true);
        setVisible(true);   
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==b1)
        { 
            setVisible(false);
            new Deposit(pin, cardno).setVisible(true);
        }
        
        else if(ae.getSource()==b2)
        { 
            setVisible(false);
            //new Withdrawl(pin, cardno).setVisible(true);
        }
        
        else if(ae.getSource()==b3)
        { 
            setVisible(false);
            //new FastCash(pin, cardno).setVisible(true);
        }
        
        else if(ae.getSource()==b4)
        { 
            //new MiniStatement(pin, cardno).setVisible(true);
        }
        
        else if(ae.getSource()==b5)
        { 
            setVisible(false);
            //new Pin(pin, cardno).setVisible(true);
        }
        
        else if(ae.getSource()==b6)
        { 
            this.setVisible(false);
            new BalanceEnquiry(pin, cardno).setVisible(true);
        }
        
        else if(ae.getSource()==b7)
        { 
            System.exit(0);
        }
    }
    
    public static void main(String[] args)
    {
        new Transactions("","").setVisible(true);
    }
}