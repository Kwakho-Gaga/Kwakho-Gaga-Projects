/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.cput.sikunyecharity.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import za.ac.cput.sikunyecharity.DAO.ContactDAO;
import za.ac.cput.sikunyecharity.Domain.Contact;

/**
 *
 * @author Gaga Kwakho
 */

public class ContactSTable extends JFrame implements ActionListener, ItemListener {
    
    private JPanel queryPanel, panelHeader, sendButtonPanel, mainPanel,panelButton,panelUserForm,panelAdmin,westPanel;
    private JSplitPane split;
    private JLabel formHeadingLabel;
    private JLabel userFullNameLabel;
    private JTextField userFullNameTextField;
    private JLabel userEmailLabel;
    private JTextField userEmailTextField;
    private JLabel userMobileLabel;
    private JTextField userMobileTextField;
    private JLabel subjectLabel;
    private JComboBox subjectCombo;
    // private JTextField subjectTextField;
    private JLabel messageLabel;
    private JTextArea messageTextArea;
    private JButton sendMessageButton;
    private JButton viewButton;
    private JButton updateButton;
    private JButton deleteButton;
    private int submissionId;
    private JTable table;
    private DefaultTableModel tableModel;

        Font font6 = new Font("Inter", Font.BOLD, 14);

    Color color1 = new Color(0, 192, 232);
    Color color2 = Color.WHITE;
    Color color3 = new Color(245, 239, 247);
    Color color4 = new Color(245, 245, 245);
    // footer
    Color color5 = new Color(43, 55, 63, 230);
    //strokein footer
    Color color6 = new Color(142, 142, 147);
    Color color7 = new Color(179, 179, 179);
     private JPanel createLeftAlignedPanel(JLabel label, JComponent field) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        field.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(0, 5))); // small space between label and field
        panel.add(field);
        panel.setOpaque(false);
        return panel;
    }
    
    public ContactSTable() {
        split=new JSplitPane();
        panelUserForm=new JPanel();
        panelAdmin=new JPanel();
        
        queryPanel = new JPanel();
        panelHeader = new JPanel();
        panelButton = new JPanel();
        mainPanel = new JPanel();
        sendButtonPanel=new JPanel();
        westPanel=new JPanel();
        
        formHeadingLabel = new JLabel("Have Inquiries or Urgent Matters? Contact Us Anytime.");
        userFullNameLabel = new JLabel("Full Name");
        userFullNameTextField = new JTextField(50);
        
        userEmailLabel = new JLabel("Email");
        userEmailTextField = new JTextField();
        
        userMobileLabel = new JLabel("Phone Number");
        userMobileTextField = new JTextField();
        
        subjectLabel = new JLabel("Subject");
        subjectCombo = new JComboBox();
        subjectCombo.setBackground(color2);
        subjectCombo.addItem("Select your query type");
        subjectCombo.addItem("Financial Donation");
        subjectCombo.addItem("Volunteer Opportunities");
        subjectCombo.addItem("Partnership and Collaboration");
        subjectCombo.addItem("Specific Project");
        subjectCombo.addItem("Fundraising Events");
        subjectCombo.addItemListener(this);
        
        messageLabel = new JLabel("Message");
        messageTextArea = new JTextArea();
        messageTextArea.setBorder(new LineBorder(color7, 1));
        
        sendMessageButton = new JButton("Send Message");
         sendMessageButton.setFont(font6);
        sendMessageButton.setBackground(color1);
        sendMessageButton.setBorder(new EmptyBorder(6, 30, 6, 35));
        sendMessageButton.setForeground(color2);
        sendMessageButton.setOpaque(true);

        viewButton = new JButton("VIEW");
        viewButton.setFont(font6);
        viewButton.setBackground(Color.LIGHT_GRAY);
        viewButton.setBorder(new EmptyBorder(5, 30, 5, 35));
        viewButton.setForeground(color2);
        viewButton.setOpaque(true);
        
        
        updateButton = new JButton("UPDATE");
        updateButton.setFont(font6);
        updateButton.setBackground(Color.ORANGE);
        updateButton.setBorder(new EmptyBorder(5, 30, 5, 35));
        updateButton.setForeground(color2);
        updateButton.setOpaque(true);
        deleteButton = new JButton("DELETE");
        deleteButton.setFont(font6);
        deleteButton.setBackground(Color.red);
        deleteButton.setBorder(new EmptyBorder(5, 30, 5, 35));
        deleteButton.setForeground(color2);
        deleteButton.setOpaque(true);
        sendMessageButton.addActionListener(this);
        viewButton.addActionListener(this);
        updateButton.addActionListener(this);
        deleteButton.addActionListener(this);
        
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        
        tableModel.addColumn("Contact_ID");
        tableModel.addColumn("Full_Name");
        tableModel.addColumn("Email");
        tableModel.addColumn("Phone_Number");
        tableModel.addColumn("Subject");
        tableModel.addColumn("Message");
        
    table.addMouseListener(new MouseAdapter() {
           @Override
           public void mouseClicked(MouseEvent arg0){
    int i=table.getSelectedRow();
      if (i != -1) {
            userFullNameTextField.setText(tableModel.getValueAt(i, 1).toString());
            userFullNameTextField.setForeground(Color.BLACK);
            userEmailTextField.setText(tableModel.getValueAt(i, 2).toString());
            userEmailTextField.setForeground(Color.BLACK);
            userMobileTextField.setText(tableModel.getValueAt(i, 3).toString());
            userMobileTextField.setForeground(Color.BLACK);
            subjectCombo.setSelectedItem(tableModel.getValueAt(i, 4).toString());
            subjectCombo.setForeground(Color.BLACK);
            messageTextArea.setText(tableModel.getValueAt(i, 5).toString());
            messageTextArea.setForeground(Color.BLACK);
            
          
      }   
 
    }
    });
    
    
    
    
    
    
    }
    
    public void setGui() {
        panelUserForm.setLayout(new BoxLayout(panelUserForm, BoxLayout.Y_AXIS));
        
      queryPanel.setLayout(new BoxLayout(queryPanel, BoxLayout.Y_AXIS));
        queryPanel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        queryPanel.setOpaque(true);
       queryPanel.setBackground(color4);
       queryPanel. setBorder(new EmptyBorder(10, 0, 85, 35));
        Dimension panelQuerySize = new Dimension(530, 600);
        queryPanel.setPreferredSize(panelQuerySize);
        queryPanel.setMinimumSize(panelQuerySize);
        queryPanel.setMaximumSize(panelQuerySize);

        Dimension myCustomSize = new Dimension(400, 40);

       queryPanel.add(formHeadingLabel);
        formHeadingLabel.setPreferredSize(myCustomSize);
        formHeadingLabel.setMinimumSize(myCustomSize);
        formHeadingLabel.setBorder(new EmptyBorder(0, 100, 0, 0));

        queryPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        queryPanel.add(createLeftAlignedPanel(userFullNameLabel, userFullNameTextField));
        userFullNameLabel.setOpaque(false);
        userFullNameTextField.setPreferredSize(myCustomSize);
        userFullNameTextField.setMinimumSize(myCustomSize);
        userFullNameTextField.setMaximumSize(myCustomSize);
       queryPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        queryPanel.add(createLeftAlignedPanel(userEmailLabel, userEmailTextField));
        userEmailLabel.setOpaque(false);
        userEmailTextField.setPreferredSize(myCustomSize);
        userEmailTextField.setMinimumSize(myCustomSize);
        userEmailTextField.setMaximumSize(myCustomSize);
        queryPanel.add(Box.createRigidArea(new Dimension(0, 10)));

       queryPanel.add(createLeftAlignedPanel(userMobileLabel, userMobileTextField));
        userMobileLabel.setOpaque(false);
        userMobileTextField.setPreferredSize(myCustomSize);
        userMobileTextField.setMinimumSize(myCustomSize);
        userMobileTextField.setMaximumSize(myCustomSize);
        queryPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        queryPanel.add(createLeftAlignedPanel(subjectLabel, subjectCombo));
        subjectCombo.setPreferredSize(myCustomSize);
        subjectCombo.setMinimumSize(myCustomSize);
        subjectCombo.setMaximumSize(myCustomSize);
        queryPanel.add(Box.createRigidArea(new Dimension(0, 10)));

       queryPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        queryPanel.add(messageLabel);
        queryPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        JScrollPane message = new JScrollPane(messageTextArea);
        Dimension scrollPaneSize = new Dimension(600, 70);
        messageTextArea.setLineWrap(true);
        messageTextArea.setWrapStyleWord(true);
        messageTextArea.setOpaque(true);
        messageTextArea.setBackground(color4);
        message.setPreferredSize(scrollPaneSize);
        message.setMinimumSize(scrollPaneSize);
        message.setMaximumSize(scrollPaneSize);
        message.getVerticalScrollBar().setUnitIncrement(16);
        message.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        message.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        message.setOpaque(false);
       queryPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        queryPanel.add(createLeftAlignedPanel(messageLabel, message));
        queryPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        sendButtonPanel.setLayout(new FlowLayout());
        sendButtonPanel.setOpaque(false);
        Dimension buttonSize = new Dimension(175, 40);
        sendMessageButton.setPreferredSize(buttonSize);
        sendMessageButton.setMinimumSize(buttonSize);
        sendMessageButton.setMaximumSize(buttonSize);
        queryPanel.add(sendMessageButton);
        sendButtonPanel.add(sendMessageButton);
        queryPanel.add(sendButtonPanel);

        westPanel.setOpaque(false);
        westPanel.setBackground(color3);
        westPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
        westPanel.add(queryPanel);


                panelAdmin.setLayout(new BoxLayout(panelAdmin, BoxLayout.Y_AXIS));

 panelButton.setLayout(new FlowLayout());
 panelButton.add(viewButton);
 panelButton.add(updateButton);
 panelButton.add(deleteButton);
        JScrollPane scroll = new JScrollPane(table);
        
        panelUserForm.add(panelHeader);
        panelUserForm.add(westPanel);
       
        panelAdmin.add(panelButton);
        panelAdmin.add(scroll);
         
        split.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        split.setTopComponent(panelUserForm);
        split.setBottomComponent(panelAdmin);
        split.setDividerLocation(300);
        this.add(split);
    }
    public void refresh(){
    userFullNameTextField.setText("");
    userEmailTextField.setText("");
    userMobileTextField.setText("");
 subjectCombo.setSelectedItem("Select your query type");
    messageTextArea.setText("");
    userFullNameTextField.requestFocus();
    
    
    

}
    public void refreshTable() {
    
                ContactDAO contactDAO=new ContactDAO();

        
        try {
        // Fetch latest data from the database
        ArrayList<Contact> contactList = contactDAO.getAllContact();

        // Clear existing data in the table
        tableModel.setRowCount(0);

        // Populate table with new data
        for (Contact c : contactList) {
            Object[] row = {
                c.getContactId(),    // assuming getContactId() returns the contact's ID
                c.getFullName(),
                c.getEmail(),
                c.getPhoneNumber(),
                c.getSubject(),
                c.getMessage()
            };
            tableModel.addRow(row);
        }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error refreshing data");
    }
}
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sendMessageButton) {
            String fullName = userFullNameTextField.getText();
            String email = userEmailTextField.getText();
            String mobile = userMobileTextField.getText();
 String subject = (subjectCombo.getSelectedItem() != null) 
                                ? subjectCombo.getSelectedItem().toString() 
                                : "";
            String message = messageTextArea.getText();
           
           if(!fullName.isEmpty()&&!email.isEmpty()&&!mobile.isEmpty()&&!subject.equals("Select your query type")&&!message.isEmpty()){

            Contact contact = new Contact(0,fullName, email, mobile, subject, message);
            ContactDAO contactdao = new ContactDAO();
                try {
                    contactdao.sendMessage(contact);
                } catch (SQLException ex) {
                    Logger.getLogger(ContactSTable.class.getName()).log(Level.SEVERE, null, ex);
                }
                   
            
            JOptionPane.showMessageDialog(null, "Thank you "+ fullName +" your QUERY has been recieved we will get back to you soon");
            refresh();
           }else
               JOptionPane.showMessageDialog(null, "Message not sent please enter all required fields");
        }
        else if (e.getSource()==viewButton) {
            try {
                ContactDAO contactDAO = new ContactDAO();
                // Clear existing rows
                tableModel.setRowCount(0);
                
                ArrayList<Contact> contactList = contactDAO.getAll();
                 if (contactList.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No records found,wait for user to submit query");

                } else {
                for (Contact c : contactList) {
                    String[] rowData = {
                   String.valueOf(c.getContactId()),
                        c.getFullName(),
                        c.getEmail(),
                        c.getPhoneNumber(),
                        c.getSubject(),
                        c.getMessage()
                    };
                    tableModel.addRow(rowData);
                    
                }   } }
            catch (SQLException ex) {
                Logger.getLogger(ContactSTable.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
        
        else if  (e.getSource() == deleteButton) {
        ContactDAO contactDAO=new ContactDAO();
            int selectedRow = table.getSelectedRow();
    
  if (selectedRow != -1) {
    // Assuming ID is in column 0
    int contactId = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
    
    // Call your DAO delete method
    int response = JOptionPane.showConfirmDialog(
                        null,
                        "Are you sure you want to delete this row?",
                        "Confirmation",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );
                if (response == JOptionPane.YES_OPTION) {
                    int result = contactDAO.deleteById(contactId);
                    if (result > 0) {
                        JOptionPane.showMessageDialog(null, "Record deleted successfully!");
                        refresh();
                        refreshTable();
                    } else {

                        refresh();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please select a row to delete");
            }

    }      
    else if (e.getSource() == updateButton) {
    int selectedRow = table.getSelectedRow();
    if (selectedRow != -1) {
        int contactId = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
        String fullName = userFullNameTextField.getText();
        String email = userEmailTextField.getText();
        String mobile = userMobileTextField.getText();
       String subject = (subjectCombo.getSelectedItem() != null) 
                                ? subjectCombo.getSelectedItem().toString() 
                                : "";
        String message = messageTextArea.getText();

       if(!fullName.isEmpty()&&!email.isEmpty()&&!mobile.isEmpty()&&!subject.equals("Select your query type")&&!message.isEmpty()){

            Contact contact = new Contact(contactId,fullName, email, mobile, subject, message);
            ContactDAO contactdao = new ContactDAO();
            int result = contactdao .updateById(contact);
            if (result > 0) {
                JOptionPane.showMessageDialog(null, "Record updated successfully!");
                refresh();
                refreshTable();
            } else {
                JOptionPane.showMessageDialog(null, "No record found with that ID");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please fill in all fields before updating");
        }
    } else {
        JOptionPane.showMessageDialog(null, "Please select a row to update");
    }
}

        
        
    }       
        
        
        
        
        
        
    
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange()==ItemEvent.SELECTED) {
          
            
        }
    }
    
}
