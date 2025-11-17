package ASimulatorSystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Signup2 extends JFrame implements ActionListener
{
    JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9;
    JRadioButton r1,r2,r3,r4;
    JComboBox c1,c2,c3,c4,c5;
    JTextField t1;
    JButton b;
    
    String formno;
    
    Color primaryColor = new Color(0x5B8FF9);      // Blue
    Color backgroundColor = new Color(0xF5F7FA);   // Light Grey
    Color buttonColor = new Color(0x5B8FF9);       // Blue
    Color textColor = new Color(0x2C3E50);         // Dark Grey
    
    Font headingFont = new Font("Arial", Font.BOLD, 32);
    Font subHeadingFont = new Font("Arial", Font.PLAIN, 16);
    Font labelFont = new Font("Arial", Font.PLAIN, 16);
    Font textFont = new Font("Arial", Font.PLAIN, 15);
    Font buttonFont = new Font("Arial", Font.BOLD, 16);
    
    Signup2(String formno){
        
        this.formno = formno;
        setTitle("Bank Account Signup - Step 2");
        getContentPane().setBackground(backgroundColor);
        
        setLayout(new GridBagLayout());
        GridBagConstraints gbcOuter = new GridBagConstraints();
        gbcOuter.gridx = 0;
        gbcOuter.gridy = 0;
        gbcOuter.anchor = GridBagConstraints.CENTER;

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/info.png"));
        Image i2 = i1.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(i2));
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(logoLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        
        l1 = new JLabel("ADDITIONAL INFORMATION", JLabel.CENTER);
        l1.setFont(headingFont);
        l1.setForeground(primaryColor);
        l1.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(l1);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 8)));

        JLabel subtitle = new JLabel("Step 2 of 3", JLabel.CENTER);
        subtitle.setFont(subHeadingFont);
        subtitle.setForeground(textColor);
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(subtitle);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 25)));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        l2 = new JLabel("Religion:");
        l2.setFont(labelFont);
        l2.setForeground(textColor);
        gbc.gridx = 0; 
        gbc.gridy = 0;
        formPanel.add(l2, gbc);

        String religion[] = {"Muslim","Christian","Hindu","Sikh","Other"};
        c1 = new JComboBox(religion);
        c1.setBackground(Color.WHITE);
        c1.setFont(textFont);
        c1.setPreferredSize(new Dimension(250, 32));
        c1.setSelectedIndex(-1);
        gbc.gridx = 1; 
        gbc.gridy = 0;
        formPanel.add(c1, gbc);

        // Row 2: Nationality
        l3 = new JLabel("Nationality:");
        l3.setFont(labelFont);
        l3.setForeground(textColor);
        gbc.gridx = 0; 
        gbc.gridy = 1;
        formPanel.add(l3, gbc);

        String nationality[] = {"Pakistani", "Other"};
        c2 = new JComboBox(nationality);
        c2.setBackground(Color.WHITE);
        c2.setFont(textFont);
        c2.setPreferredSize(new Dimension(250, 32));
        gbc.gridx = 1; 
        gbc.gridy = 1;
        formPanel.add(c2, gbc);

        l4 = new JLabel("Income:");
        l4.setFont(labelFont);
        l4.setForeground(textColor);
        gbc.gridx = 0; 
        gbc.gridy = 2;
        formPanel.add(l4, gbc);

        String income[] = {
            "Below 100,000",
            "100,000 - 250,000",
            "250,000 - 500,000",
            "500,000 - 1,000,000", 
            "Above 1,000,000"  
        };
        c3 = new JComboBox(income);
        c3.setBackground(Color.WHITE);
        c3.setFont(textFont);
        c3.setPreferredSize(new Dimension(250, 32));
        c3.setSelectedIndex(-1);
        gbc.gridx = 1; 
        gbc.gridy = 2;
        formPanel.add(c3, gbc);

        // Row 4: Education
        l5 = new JLabel("Education:");
        l5.setFont(labelFont);
        l5.setForeground(textColor);
        gbc.gridx = 0; 
        gbc.gridy = 3;
        formPanel.add(l5, gbc);

        String education[] = {"BelowGraduate","Graduate","PostGraduate","Doctorate / PhD","Others"};
        c4 = new JComboBox(education);
        c4.setBackground(Color.WHITE);
        c4.setFont(textFont);
        c4.setPreferredSize(new Dimension(250, 32));
        c4.setSelectedIndex(-1);
        gbc.gridx = 1; 
        gbc.gridy = 3;
        formPanel.add(c4, gbc);

        // Row 5: Occupation
        l6 = new JLabel("Occupation:");
        l6.setFont(labelFont);
        l6.setForeground(textColor);
        gbc.gridx = 0; 
        gbc.gridy = 4;
        formPanel.add(l6, gbc);

        String occupation[] = {"Salaried","Self-Employmed","Businessman","Student","Retired","Others"};
        c5 = new JComboBox(occupation);
        c5.setBackground(Color.WHITE);
        c5.setFont(textFont);
        c5.setPreferredSize(new Dimension(250, 32));
        c5.setSelectedIndex(-1);
        gbc.gridx = 1; 
        gbc.gridy = 4;
        formPanel.add(c5, gbc);

        l7 = new JLabel("CNIC Number:");
        l7.setFont(labelFont);
        l7.setForeground(textColor);
        gbc.gridx = 0; 
        gbc.gridy = 5;
        formPanel.add(l7, gbc);

        t1 = new JTextField(20);
        t1.setFont(textFont);
        t1.setPreferredSize(new Dimension(250, 32));
        gbc.gridx = 1; 
        gbc.gridy = 5;
        formPanel.add(t1, gbc);

        l8 = new JLabel("Senior Citizen?");
        l8.setFont(labelFont);
        l8.setForeground(textColor);
        gbc.gridx = 0; 
        gbc.gridy = 6;
        formPanel.add(l8, gbc);

        r1 = new JRadioButton("Yes");
        r2 = new JRadioButton("No");
        r1.setFont(textFont);
        r2.setFont(textFont);
        r1.setBackground(Color.WHITE);
        r2.setBackground(Color.WHITE);
        
        ButtonGroup groupCitizen = new ButtonGroup();
        groupCitizen.add(r1);
        groupCitizen.add(r2);

        JPanel citizenPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        citizenPanel.setBackground(Color.WHITE);
        citizenPanel.add(r1);
        citizenPanel.add(r2);
        gbc.gridx = 1; 
        gbc.gridy = 6;
        formPanel.add(citizenPanel, gbc);

        l9 = new JLabel("Existing Account?");
        l9.setFont(labelFont);
        l9.setForeground(textColor);
        gbc.gridx = 0; 
        gbc.gridy = 7;
        formPanel.add(l9, gbc);

        r3 = new JRadioButton("Yes");
        r4 = new JRadioButton("No");
        r3.setFont(textFont);
        r4.setFont(textFont);
        r3.setBackground(Color.WHITE);
        r4.setBackground(Color.WHITE);
        
        ButtonGroup groupAccount = new ButtonGroup();
        groupAccount.add(r3);
        groupAccount.add(r4);

        JPanel accountPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        accountPanel.setBackground(Color.WHITE);
        accountPanel.add(r3);
        accountPanel.add(r4);
        gbc.gridx = 1; 
        gbc.gridy = 7;
        formPanel.add(accountPanel, gbc);

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
        String religion = (String)c1.getSelectedItem(); 
        String nationality = (String)c2.getSelectedItem();
        String income = (String)c3.getSelectedItem();
        String education = (String)c4.getSelectedItem();
        String occupation = (String)c5.getSelectedItem();
        
        String CNIC = t1.getText().trim();
        
        String seniorcitizen = "";
        if(r1.isSelected())
        { 
            seniorcitizen = "Yes";
        }
        else if(r2.isSelected())
        { 
            seniorcitizen = "No";
        }
           
        String existingaccount = "";
        if(r3.isSelected()){ 
            existingaccount = "Yes";
        }
        else if(r4.isSelected())
        { 
            existingaccount = "No";
        }
        
        if(religion == null || nationality == null || income == null || education == null || 
            occupation == null || CNIC.isEmpty() || existingaccount.isEmpty() || 
            seniorcitizen.isEmpty()) 
        {
            JOptionPane.showMessageDialog(
                this,
                "Please complete all required fields.",
                "Missing Information",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }
        else
        {
            if(nationality.equals("Pakistani")) 
            {
                if(!CNIC.matches("\\d{5}-\\d{7}-\\d{1}")) 
                {
                    JOptionPane.showMessageDialog(
                        this,
                        "Invalid CNIC format (xxxxx-xxxxxxx-x)",
                        "Invalid CNIC",
                        JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }
            } 
            else 
            {
                if(!CNIC.matches("[a-zA-Z0-9]{5,20}")) 
                {
                    JOptionPane.showMessageDialog(
                       this,
                       "Please enter a valid ID (5–20 characters).",
                       "Invalid ID",
                       JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }
            }
                
            Conn c1 = new Conn();
            
            try
            {
                c1.c.setAutoCommit(false);
                
                String q1 = "INSERT INTO signup2 VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                
                PreparedStatement ps = c1.c.prepareStatement(q1);

                ps.setString(1, formno);
                ps.setString(2, religion);
                ps.setString(3, nationality);
                ps.setString(4, income);
                ps.setString(5, education);
                ps.setString(6, occupation);
                ps.setString(7, CNIC);
                ps.setString(8, seniorcitizen);
                ps.setString(9, existingaccount);

                ps.executeUpdate();
                
                c1.c.commit();
                c1.c.setAutoCommit(true);
                
                JOptionPane.showMessageDialog(
                this,
                "Additional Information saved successfully. Continue to the next step.",
                "Step 2 Completed",
                JOptionPane.INFORMATION_MESSAGE
                );
                
                new Signup3(formno).setVisible(true);
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
    
    public static void main(String[] args)
    {
        new Signup2("").setVisible(true);
    }
}