package ASimulatorSystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import com.toedter.calendar.JDateChooser;
import java.util.*;

public class Signup extends JFrame implements ActionListener
{
    
    JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11;
    JTextField t1, t2, t3, t4, t5, t6;
    JRadioButton r1, r2, r3, r4;
    JDateChooser dateChooser;
    JButton b;

    Random ran = new Random();
    long first4 = (ran.nextLong() % 9000L) + 1000L;
    String formno = "" + Math.abs(first4);

    Color primaryColor = new Color(0x5B8FF9);      // Blue
    Color backgroundColor = new Color(0xF5F7FA);   // Light Grey
    Color buttonColor = new Color(0x5B8FF9);       // Blue
    Color textColor = new Color(0x2C3E50);         // Dark Grey
    
    Font headingFont = new Font("Arial", Font.BOLD, 32);
    Font subHeadingFont = new Font("Arial", Font.PLAIN, 16);
    Font labelFont = new Font("Arial", Font.PLAIN, 16);
    Font textFont = new Font("Arial", Font.PLAIN, 15);
    Font buttonFont = new Font("Arial", Font.BOLD, 16);

    public Signup() 
    {
        setTitle("Bank Account Signup");
        getContentPane().setBackground(backgroundColor);

        setLayout(new GridBagLayout());
        GridBagConstraints gbcOuter = new GridBagConstraints();
        gbcOuter.anchor = GridBagConstraints.CENTER;

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS)); //make components of panel in Y axis
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40)); //add padding

        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/profile (2).png"));
        Image i2 = i1.getImage().getScaledInstance(85, 85, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(i2));
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(logoLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));  //create gap around or below image

        
        l1 = new JLabel("APPLICATION FORM NO. " + formno, JLabel.CENTER);
        l1.setFont(headingFont);
        l1.setForeground(primaryColor);
        l1.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(l1);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 8))); 

        l2 = new JLabel("Step 1: Personal Details", JLabel.CENTER);
        l2.setFont(subHeadingFont);
        l2.setForeground(textColor);
        l2.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(l2);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 25)));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);             //gap/margin around
        gbc.fill = GridBagConstraints.HORIZONTAL;          //make form components horizontal
        gbc.anchor = GridBagConstraints.WEST;

        
        l3 = new JLabel("Name:");
        l3.setFont(labelFont);
        l3.setForeground(textColor);
        gbc.gridx = 0; 
        gbc.gridy = 0; 
        gbc.gridwidth = 1;
        formPanel.add(l3, gbc);

        t1 = new JTextField(25);
        t1.setFont(textFont);
        t1.setPreferredSize(new Dimension(250, 32));
        gbc.gridx = 1; 
        gbc.gridy = 0; 
        gbc.gridwidth = 1;
        formPanel.add(t1, gbc);

        
        l4 = new JLabel("Father's Name:");
        l4.setFont(labelFont);
        l4.setForeground(textColor);
        gbc.gridx = 0; 
        gbc.gridy = 1;
        formPanel.add(l4, gbc);

        t2 = new JTextField(25);
        t2.setFont(textFont);
        t2.setPreferredSize(new Dimension(250, 32));
        gbc.gridx = 1; 
        gbc.gridy = 1;
        formPanel.add(t2, gbc);

        
        l5 = new JLabel("Date of Birth:");
        l5.setFont(labelFont);
        l5.setForeground(textColor);
        gbc.gridx = 0; 
        gbc.gridy = 2;
        formPanel.add(l5, gbc);

        dateChooser = new JDateChooser();
        dateChooser.setFont(textFont);
        dateChooser.setPreferredSize(new Dimension(250, 32));
        gbc.gridx = 1; 
        gbc.gridy = 2;
        formPanel.add(dateChooser, gbc);

        
        l6 = new JLabel("Gender:");
        l6.setFont(labelFont);
        l6.setForeground(textColor);
        gbc.gridx = 0; 
        gbc.gridy = 3;
        formPanel.add(l6, gbc);

        r1 = new JRadioButton("Male");
        r2 = new JRadioButton("Female");
        r1.setFont(textFont); 
        r2.setFont(textFont);
        r1.setBackground(Color.WHITE); 
        r2.setBackground(Color.WHITE);
        ButtonGroup groupGender = new ButtonGroup();
        groupGender.add(r1); 
        groupGender.add(r2);
        
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        genderPanel.setBackground(Color.WHITE);
        genderPanel.add(r1); 
        genderPanel.add(r2);

        gbc.gridx = 1; 
        gbc.gridy = 3;
        formPanel.add(genderPanel, gbc);

        
        l7 = new JLabel("Email Address:");
        l7.setFont(labelFont);
        l7.setForeground(textColor);
        gbc.gridx = 0; 
        gbc.gridy = 4;
        formPanel.add(l7, gbc);

        t3 = new JTextField(25);
        t3.setFont(textFont);
        t3.setPreferredSize(new Dimension(250, 32));
        gbc.gridx = 1; 
        gbc.gridy = 4;
        formPanel.add(t3, gbc);

        
        l8 = new JLabel("Marital Status:");
        l8.setFont(labelFont);
        l8.setForeground(textColor);
        gbc.gridx = 0; 
        gbc.gridy = 5;
        formPanel.add(l8, gbc);

        r3 = new JRadioButton("Married");
        r4 = new JRadioButton("Unmarried");
        r3.setFont(textFont); 
        r4.setFont(textFont); 
        r3.setBackground(Color.WHITE); 
        r4.setBackground(Color.WHITE); 
        ButtonGroup groupStatus = new ButtonGroup();
        groupStatus.add(r3); 
        groupStatus.add(r4); 

        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        statusPanel.setBackground(Color.WHITE);
        statusPanel.add(r3); 
        statusPanel.add(r4); 

        gbc.gridx = 1; 
        gbc.gridy = 5;
        formPanel.add(statusPanel, gbc);

        
        l9 = new JLabel("Address:");
        l9.setFont(labelFont);
        l9.setForeground(textColor);
        gbc.gridx = 0; 
        gbc.gridy = 6;
        formPanel.add(l9, gbc);

        t4 = new JTextField(25);
        t4.setFont(textFont);
        t4.setPreferredSize(new Dimension(250, 32));
        gbc.gridx = 1; 
        gbc.gridy = 6;
        formPanel.add(t4, gbc);

        
        l10 = new JLabel("City:");
        l10.setFont(labelFont);
        l10.setForeground(textColor);
        gbc.gridx = 0; 
        gbc.gridy = 7;
        formPanel.add(l10, gbc);

        t5 = new JTextField(25);
        t5.setFont(textFont);
        t5.setPreferredSize(new Dimension(250, 32));
        gbc.gridx = 1; 
        gbc.gridy = 7;
        formPanel.add(t5, gbc);

        
        l11 = new JLabel("State:");
        l11.setFont(labelFont);
        l11.setForeground(textColor);
        gbc.gridx = 0; 
        gbc.gridy = 8;
        formPanel.add(l11, gbc);

        t6 = new JTextField(25);
        t6.setFont(textFont);
        t6.setPreferredSize(new Dimension(250, 32));
        gbc.gridx = 1; 
        gbc.gridy = 8;
        formPanel.add(t6, gbc);

        mainPanel.add(formPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 25)));

        
        b = new JButton("NEXT");
        b.setFont(buttonFont);
        b.setBackground(buttonColor);
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b.setPreferredSize(new Dimension(200, 40));
        b.setMaximumSize(new Dimension(200, 40));
        b.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(b);

        b.addActionListener(this);

        add(mainPanel, gbcOuter);

        setSize(600, 750);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        String name = t1.getText().trim();
        String father_name = t2.getText().trim();
        String dob = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
        String gender = null;
        if(r1.isSelected())
        { 
            gender = "Male";
        }
        else if(r2.isSelected())
        { 
            gender = "Female";
        }
            
        String email = t3.getText().trim();
        String marital_status = null;
        if(r3.isSelected())
        { 
            marital_status = "Married";
        }
        else if(r4.isSelected())
        { 
            marital_status = "Unmarried";
        }
           
        String address = t4.getText().trim();
        String city = t5.getText().trim();
        String state = t6.getText().trim();
        
        String lettersRegex = "^[a-zA-Z ]+$";
        String emailRegex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$";
        
        if(name.isEmpty() || father_name.isEmpty() || dob.isEmpty() || email.isEmpty() || address.isEmpty() 
            || city.isEmpty() || state.isEmpty() ||  gender == null || marital_status == null )
        {
            JOptionPane.showMessageDialog(
                this,
                "Please complete all required fields",
                "Incomplete Form",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }
        else if(!name.matches(lettersRegex)) 
        {
            JOptionPane.showMessageDialog(this, "Please enter a valid name.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
            return;
        }
            
        else if(!father_name.matches(lettersRegex)) 
        {
            JOptionPane.showMessageDialog(this, "Please enter a valid father's name.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
            return;
        }
            
        else if(!email.matches(emailRegex)) 
        {
            JOptionPane.showMessageDialog(
                this, "Please enter a valid email address", 
                "Invalid Input", JOptionPane.WARNING_MESSAGE
            );
            return;
        }
            
        else if(!city.matches(lettersRegex)) 
        {
            JOptionPane.showMessageDialog(this, "Please enter a valid city.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
            return;
        }
            
        else if(!state.matches(lettersRegex)) 
        {
            JOptionPane.showMessageDialog(this, "Please enter a valid state.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
            return;
        }
            
        Conn c1 = new Conn();
        
        try
        {
            c1.c.setAutoCommit(false);
           
            String q1 = "INSERT INTO signup VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                
            PreparedStatement ps = c1.c.prepareStatement(q1);

            ps.setString(1, formno);
            ps.setString(2, name);
            ps.setString(3, father_name);
            ps.setString(4, dob);
            ps.setString(5, gender);
            ps.setString(6, email);
            ps.setString(7, marital_status);
            ps.setString(8, address);
            ps.setString(9, city);
            ps.setString(10, state);

            ps.executeUpdate();
                
            c1.c.commit();
            c1.c.setAutoCommit(true);
                
            JOptionPane.showMessageDialog(
                this,
                "Personal details saved successfully. Continue to the next step.",
                "Step 1 Completed",
                JOptionPane.INFORMATION_MESSAGE
            );

            new Signup2(formno).setVisible(true);
            setVisible(false);
        }
        catch(Exception e)
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
}