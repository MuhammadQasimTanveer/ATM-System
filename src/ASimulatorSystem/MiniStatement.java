package ASimulatorSystem;

import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MiniStatement 
{
    private String pin, cardno;

    public MiniStatement(String pin, String cardno) 
    {
        this.pin = pin;
        this.cardno = cardno;
    }

    public void generatePDF() 
    {
        try (PDDocument document = new PDDocument()) 
        {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            PDPageContentStream cs = new PDPageContentStream(document, page);

            PDFont font = PDType1Font.HELVETICA;
            PDFont fontBold = PDType1Font.HELVETICA_BOLD;

            float margin = 50;
            float yStart = 750;
            float y = yStart;
            float leading = 18;        //gap between each line

            String bankName = "Aurora Bank - MINI STATEMENT";
            float titleWidth = fontBold.getStringWidth(bankName) / 1000 * 22;  //width in pixels
            
            //make title center 
            float titleX = (PDRectangle.A4.getWidth() - titleWidth) / 2;

            cs.setNonStrokingColor(new Color(0, 51, 102)); //Text fill color stroking = outline, non-stroking = fill).
            cs.beginText();                 //Start Writing Text
            cs.setFont(fontBold, 22);
            cs.newLineAtOffset(titleX, y);    //position
            cs.showText(bankName);          //Draw the Title
            cs.endText();
            y -= 70;                        //create space/gap after 

            Conn c = new Conn();
            String name = "";
            double balance = 0;

            PreparedStatement ps = c.c.prepareStatement(
                    "SELECT s.name FROM signup3 s3 JOIN signup s ON s3.formNo = s.formNo WHERE s3.cardno=? AND s3.pin=?"
            );
            ps.setString(1, cardno);
            ps.setString(2, pin);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) 
            {
                name = rs.getString("name");
            }

            PreparedStatement psBal = c.c.prepareStatement(
                    "SELECT currentBalance FROM users WHERE cardno=? AND pin=?"
            );
            psBal.setString(1, cardno);
            psBal.setString(2, pin);
            ResultSet rsBal = psBal.executeQuery();
            if (rsBal.next()) 
            {
                balance = rsBal.getDouble("currentBalance");
            }

            cs.setNonStrokingColor(Color.BLACK);
            cs.beginText();
            cs.setFont(fontBold, 14);
            cs.newLineAtOffset(margin, y);
            cs.showText("ACCOUNT INFORMATION");
            cs.endText();
            y -= 20;

            cs.beginText();
            cs.setFont(font, 12);
            cs.newLineAtOffset(margin, y);
            cs.showText("Account Holder: " + name);
            cs.endText();
            y -= 16;

            cs.beginText();
            cs.setFont(font, 12);
            cs.newLineAtOffset(margin, y);
            cs.showText("Generated On: " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
            cs.endText();
            y -= 2 * leading;

            //TABLE HEADER
            float colDateX = margin;
            float colTypeX = margin + 200;
            float colAmountX = margin + 350;
            float rowHeight = 16;

            cs.setNonStrokingColor(Color.LIGHT_GRAY);
            cs.addRect(margin - 5, y - 2, 500, rowHeight);    //built-in-method to draw rectangle
            cs.fill();
            cs.setNonStrokingColor(Color.BLACK);       // rectangle border draw

            cs.beginText();
            cs.setFont(fontBold, 12);
            cs.newLineAtOffset(colDateX, y);
            cs.showText("DATE & TIME");
            cs.newLineAtOffset(colTypeX - colDateX, 0);
            cs.showText("TYPE");
            cs.newLineAtOffset(colAmountX - colTypeX, 0);
            cs.showText("AMOUNT");
            cs.endText();
            y -= leading;

  
            Timestamp lastLogin = null;
            PreparedStatement psLogin = c.c.prepareStatement(
                    "SELECT login_time FROM login WHERE cardno=? AND pin=? ORDER BY login_time DESC LIMIT 1"
            );
            psLogin.setString(1, cardno);
            psLogin.setString(2, pin);
            ResultSet rsLogin = psLogin.executeQuery();
            if (rsLogin.next()) 
            {
                lastLogin = rsLogin.getTimestamp("login_time");
            }

            boolean hasTransactions = false;
            if (lastLogin != null) 
            {
                PreparedStatement psTran = c.c.prepareStatement(
                        "SELECT datetime, type, transactionAmount FROM transactions " +
                                "WHERE cardno=? AND datetime >= ? ORDER BY datetime DESC LIMIT 15"
                );
                psTran.setString(1, cardno);
                psTran.setTimestamp(2, lastLogin);

                ResultSet rsTran = psTran.executeQuery();
                boolean alternate = false;

                while (rsTran.next()) 
                {
                    hasTransactions = true;
                    Timestamp dt = rsTran.getTimestamp("datetime");
                    String type = rsTran.getString("type");
                    double amount = rsTran.getDouble("transactionAmount");
                    String dateFormatted = new SimpleDateFormat("dd/MM/yy HH:mm").format(dt);
                    String sign = type.equalsIgnoreCase("Deposit") ? "+" : "-";

                    if (alternate) 
                    {
                        cs.setNonStrokingColor(new Color(245, 245, 245));
                        cs.addRect(margin - 5, y - 2, 500, rowHeight);
                        cs.fill();
                    }
                    cs.setNonStrokingColor(Color.BLACK);

                    cs.beginText();
                    cs.setFont(font, 10);
                    cs.newLineAtOffset(colDateX, y);
                    cs.showText(dateFormatted);
                    cs.newLineAtOffset(colTypeX - colDateX, 0);
                    cs.showText(type);
                    cs.newLineAtOffset(colAmountX - colTypeX, 0);
                    cs.showText(sign + String.format("%.2f", amount));
                    cs.endText();

                    y -= rowHeight;
                    alternate = !alternate;

                    if (y < 80) {
                        cs.close();
                        page = new PDPage(PDRectangle.A4);
                        document.addPage(page);
                        cs = new PDPageContentStream(document, page);
                        y = yStart;
                    }
                }
            }

            if (!hasTransactions) 
            {
                String message = "No transactions found after last login";
                cs.beginText();
                cs.setFont(font, 12);
                float textWidth = font.getStringWidth(message) / 1000 * 12;
                cs.newLineAtOffset(margin + (500 - textWidth) / 2, y - 10);
                cs.showText(message);
                cs.endText();
                y -= 50;
            }

            y -= 2 * leading;
            cs.setNonStrokingColor(new Color(220, 230, 241));
            cs.addRect(margin - 5, y - 2, 500, rowHeight);
            cs.fill();
            cs.setNonStrokingColor(Color.BLACK);

            cs.beginText();
            cs.setFont(fontBold, 12);
            cs.newLineAtOffset(margin, y);
            cs.showText("Available Balance: Rs. " + String.format("%,.2f", balance));
            cs.endText();

            cs.close();

            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String fileName = name.replaceAll("\\s+", "") + "_" + timestamp + ".pdf";
            String filePath = System.getProperty("user.home") + "/Desktop/" + fileName;

            document.save(filePath);

            //AUTO OPEN PDF
            Desktop.getDesktop().open(new File(filePath));
            System.out.println("PDF Generated & Opened Successfully");
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
}