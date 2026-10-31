/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.cput.sikunyecharity.GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 *
 * @author Gaga Kwakho
 */
public class HomeGUI extends JFrame {

    private JPanel charityInfoFooterPanel, quickLinksPanel, sociallmediaLinkPanel,
            socialIconPanel, rightReservedPanel, footerPanel, mainPanel;
    private JLabel charityNameFooterLabel;
    private JLabel charitySloganLabel;
    private JLabel quickLinkLabel;
    private JLabel homeLinkLabel;
    private JLabel itemsDonatedLinkLabel;
    private JLabel projectLinkLabel;
    private JLabel donorLinkLabel;
    private JLabel donationRecievedLinkLabel;
    private JLabel contactLinkLabel;
    private JLabel feedbackLinkLabel;
    private JLabel followUsLabel;
    private JLabel rightReservedLabel;

    Font font1 = new Font("Inter", Font.BOLD, 20);
    Font font2 = new Font("Inter", Font.PLAIN, 20);
    Font font3 = new Font("Inter", Font.PLAIN, 13);
    Font font4 = new Font("Inter", Font.BOLD, 15);
    Font font5 = new Font("Inter", Font.BOLD, 16);
    Font font6 = new Font("Inter", Font.BOLD, 14);
    Font font7 = new Font("Inter", Font.PLAIN, 17);

    Color color1 = new Color(0, 192, 232);
    Color color2 = Color.WHITE;
    Color color3 = new Color(245, 239, 247);
    Color color4 = new Color(245, 245, 245);
    // footer
    Color color5 = new Color(43, 55, 63, 230);
    //strokein footer
    Color color6 = new Color(142, 142, 147);
    Color color7 = new Color(179, 179, 179);

    public HomeGUI() {
        charityInfoFooterPanel = new JPanel();
        quickLinksPanel = new JPanel();
        sociallmediaLinkPanel = new JPanel();
        socialIconPanel = new JPanel();
        rightReservedPanel = new JPanel();
        footerPanel= new JPanel();
        mainPanel = new JPanel();

        charityNameFooterLabel = new JLabel("Sikunye Charity");
        charityNameFooterLabel.setForeground(color2);
        charityNameFooterLabel.setFont(font1);
        charitySloganLabel = new JLabel("Together we are one");
        charitySloganLabel.setForeground(color2);
        charitySloganLabel.setFont(font5);
        quickLinkLabel = new JLabel("Quick Links");
        quickLinkLabel.setForeground(color2);
        quickLinkLabel.setFont(font1);
        homeLinkLabel = new JLabel("Home");
        homeLinkLabel.setForeground(color2);
        homeLinkLabel.setFont(font4);
        itemsDonatedLinkLabel = new JLabel("ItemsDonated");
        itemsDonatedLinkLabel.setForeground(color2);
        itemsDonatedLinkLabel.setFont(font4);
        projectLinkLabel = new JLabel("Projects");
        projectLinkLabel.setForeground(color2);
        projectLinkLabel.setFont(font4);
        donorLinkLabel = new JLabel("Donor");
        donorLinkLabel.setForeground(color2);
        donorLinkLabel.setFont(font4);
        donationRecievedLinkLabel = new JLabel("DonationsRecieved");
        donationRecievedLinkLabel.setForeground(color2);
        donationRecievedLinkLabel.setFont(font4);
        contactLinkLabel = new JLabel("Contact");
        contactLinkLabel.setForeground(color2);
        contactLinkLabel.setFont(font4);
        feedbackLinkLabel = new JLabel("Feedback");
        feedbackLinkLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        feedbackLinkLabel.setForeground(color2);
        feedbackLinkLabel.setFont(font4);

        feedbackLinkLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                feedbackLinkLabel.setForeground(color1);

                FeedbackGUI feedback = new FeedbackGUI();
                // Keep this line only if the FeedbackGUI constructor doesn't call setGui().
                feedback.setGui();

                feedback.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                feedback.setSize(800, 700);
                //feedback.setLocationRelativeTo(ContactGUI.this);
                feedback.setVisible(true);
            }
        });
        feedbackLinkLabel.setForeground(color2);
        feedbackLinkLabel.setFont(font4);
        followUsLabel = new JLabel("Follow us");
        followUsLabel.setForeground(color2);
        followUsLabel.setFont(font1);
        rightReservedLabel = new JLabel("© 2020 SikunyeCharity.All rights reserved");
        rightReservedLabel.setForeground(color2);
        rightReservedLabel.setFont(font6);
    }

    public void setGui() {
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        mainPanel.setPreferredSize(new Dimension(1390, 1552));

        charityInfoFooterPanel.setLayout(new BoxLayout(charityInfoFooterPanel, BoxLayout.Y_AXIS));
        charityInfoFooterPanel.setOpaque(false);
        charityInfoFooterPanel.add(Box.createRigidArea(new Dimension(20, 20)));
        charityInfoFooterPanel.add(charityNameFooterLabel);
        charityInfoFooterPanel.add(Box.createRigidArea(new Dimension(20, 20)));
        charityInfoFooterPanel.add(charitySloganLabel);

        quickLinksPanel.setLayout(new GridLayout(8, 1));
        quickLinksPanel.setOpaque(false);
        quickLinksPanel.add(quickLinkLabel);
        quickLinksPanel.add(homeLinkLabel);
        quickLinksPanel.add(itemsDonatedLinkLabel);
        quickLinksPanel.add(projectLinkLabel);
        quickLinksPanel.add(donorLinkLabel);
        quickLinksPanel.add(donationRecievedLinkLabel);
        quickLinksPanel.add(contactLinkLabel);
        quickLinksPanel.add(feedbackLinkLabel);

        sociallmediaLinkPanel.setLayout(new BoxLayout(sociallmediaLinkPanel, BoxLayout.Y_AXIS));
        sociallmediaLinkPanel.setOpaque(false);
        sociallmediaLinkPanel.add(followUsLabel);
        socialIconPanel.setLayout(new FlowLayout());
        socialIconPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

        socialIconPanel.setOpaque(false);
        URL facebookUrl = getClass().getResource("/facebook.png"
        );
        ImageIcon facebook = new ImageIcon(facebookUrl);
        JLabel facebookIcon = new JLabel(facebook);
        URL instagramUrl = getClass().getResource("/Instagram.png"
        );
        ImageIcon instagram = new ImageIcon(instagramUrl);
        JLabel instagramIcon = new JLabel(instagram);
        URL linkedinUrl = getClass().getResource("/Linkedin.png"
        );
        ImageIcon linkedin = new ImageIcon(linkedinUrl);
        JLabel linkedinIcon = new JLabel(linkedin);
        socialIconPanel.add(facebookIcon);
        socialIconPanel.add(instagramIcon);
        socialIconPanel.add(linkedinIcon);
        sociallmediaLinkPanel.add(socialIconPanel);

        rightReservedPanel.setLayout(new FlowLayout());
        rightReservedPanel.setOpaque(true);
        rightReservedPanel.setBackground(color5);
        rightReservedPanel.setBorder(new LineBorder(color6, 1));
        rightReservedPanel.add(rightReservedLabel);

        footerPanel.setLayout(new GridLayout(1, 3, 10, 0));
        footerPanel.setOpaque(true);
        footerPanel.setBackground(color5);
        footerPanel.setBorder(new EmptyBorder(10, 20, 10, 20));
        footerPanel.add(charityInfoFooterPanel);
        footerPanel.add(quickLinksPanel);
        footerPanel.add(sociallmediaLinkPanel);
        mainPanel.add(footerPanel);
        mainPanel.add(rightReservedPanel);
        this.add(mainPanel);
    }
 public static void main(String[] args) {
        
        
        HomeGUI home = new HomeGUI();
        home .setGui();
       home .setVisible(true);
        home .setSize(1000, 2000);
       home .pack();}
}
