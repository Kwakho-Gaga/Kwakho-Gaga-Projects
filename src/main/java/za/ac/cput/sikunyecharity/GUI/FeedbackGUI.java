/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.cput.sikunyecharity.GUI;

/**
 *
 * @author Gaga Kwakho
 */
import static java.awt.AWTEventMulticaster.add;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.JTextComponent;
import za.ac.cput.sikunyecharity.DAO.ContactDAO;
import za.ac.cput.sikunyecharity.DAO.FeedbackDAO;
import za.ac.cput.sikunyecharity.Domain.Contact;
import za.ac.cput.sikunyecharity.Domain.Feedback;

/**
 *
 * @author Gaga Kwakho
 */
public class FeedbackGUI extends JFrame implements ActionListener, ItemListener {

    private JSplitPane splitPane;
    private JPanel mainPanel, ratePanel, buttonPanel;
    private JPanel userFormPanel, adminPanel;

    private JLabel headerLabel;
    private JTextField nameTextField;
    private JTextField emailTextField;
    private JTextField subjectTextField;
    private JTextArea commentTextArea;
    private JLabel rateLabel;
    private JButton submitButton;
    private JButton viewButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JTable table;
    private DefaultTableModel tableModel;
    private boolean isUpdating = false;
    private JToggleButton[] star = new JToggleButton[5];
    private int currentRating;

    Font font1 = new Font("Inter", Font.BOLD, 20);
    Font font2 = new Font("Inter", Font.PLAIN, 20);
    Font font3 = new Font("Inter", Font.PLAIN, 15);
    Font font4 = new Font("Inter", Font.BOLD, 15);
    Font font5 = new Font("Inter", Font.BOLD, 16);
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

    public FeedbackGUI() {
        splitPane = new JSplitPane();

        userFormPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2.dispose();
            }
        };

        adminPanel = new JPanel();

        mainPanel = new JPanel();
        ratePanel = new JPanel();
        buttonPanel = new JPanel();

        headerLabel = new JLabel("Feedback");
        headerLabel.setForeground(color1);
        headerLabel.setFont(font1);

        nameTextField = new JTextField();
        emailTextField = new JTextField();
        subjectTextField = new JTextField();
        commentTextArea = new JTextArea();

        setupTextField(nameTextField, "Name");
        setupTextField(emailTextField, "Email");
        setupTextField(subjectTextField, "Subject");
        setupTextArea(commentTextArea, "Comment/Feedback");

        rateLabel = new JLabel("Rate Us");
        for (int i = 0; i < 5; i++) {
            star[i] = createStar();
        }

        submitButton = new JButton("SUBMIT");
        submitButton.setFont(font6);
        submitButton.setBackground(color1);
        submitButton.setBorder(new EmptyBorder(6, 30, 10, 35));
        submitButton.setForeground(color2);
        submitButton.setOpaque(true);

        viewButton = new JButton("VIEW");
        viewButton.setFont(font6);
        viewButton.setBackground(Color.LIGHT_GRAY);
        viewButton.setBorder(new EmptyBorder(5, 30, 5, 35));
        viewButton.setForeground(color2);
        viewButton.setOpaque(true);
        deleteButton = new JButton("DELETE");
        deleteButton.setFont(font6);
        deleteButton.setBackground(Color.red);
        deleteButton.setBorder(new EmptyBorder(5, 30, 5, 35));
        deleteButton.setForeground(color2);
        deleteButton.setOpaque(true);
        updateButton = new JButton("UPDATE");
        updateButton.setFont(font6);
        updateButton.setBackground(Color.ORANGE);
        updateButton.setBorder(new EmptyBorder(5, 30, 5, 35));
        updateButton.setForeground(color2);
        updateButton.setOpaque(true);
        submitButton.addActionListener(this);
        viewButton.addActionListener(this);
        deleteButton.addActionListener(this);
        updateButton.addActionListener(this);

        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        tableModel.addColumn("Feedback_Id");
        tableModel.addColumn("Name");
        tableModel.addColumn("Email");
        tableModel.addColumn("Subject");
        tableModel.addColumn("Comment");
        tableModel.addColumn("Rating");
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                int i = table.getSelectedRow();
                if (i != -1) {
                    nameTextField.setText(tableModel.getValueAt(i, 1).toString());
                    nameTextField.setForeground(Color.BLACK);
                    emailTextField.setText(tableModel.getValueAt(i, 2).toString());
                    emailTextField.setForeground(Color.BLACK);
                    subjectTextField.setText(tableModel.getValueAt(i, 3).toString());
                    subjectTextField.setForeground(Color.BLACK);
                    commentTextArea.setText(tableModel.getValueAt(i, 4).toString());
                    commentTextArea.setForeground(Color.BLACK);
                    int rating = Integer.parseInt(tableModel.getValueAt(i, 5).toString());
                    setStar(rating);

                }

            }
        });

    }

    public void setGui() {
        this.setTitle("Feedback");
        Dimension panelSize = new Dimension(500, 600);

        mainPanel.setOpaque(false);
        mainPanel.setBackground(color3);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(50, 150, 50, 150));
        Dimension mainPanelSize = new Dimension(600, 400);

        mainPanel.setPreferredSize(mainPanelSize);
        mainPanel.setMinimumSize(mainPanelSize);
        mainPanel.setMaximumSize(mainPanelSize);
        ratePanel.setOpaque(false);

        userFormPanel.setLayout(new BoxLayout(userFormPanel, BoxLayout.Y_AXIS));
        userFormPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                " USER PANEL ",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("SansSerif", Font.BOLD, 14),
                Color.CYAN
        ));
        userFormPanel.setOpaque(false);
        userFormPanel.setPreferredSize(panelSize);
        userFormPanel.setMinimumSize(panelSize);
        userFormPanel.setMaximumSize(panelSize);
        userFormPanel.setBackground(color4);
        userFormPanel.setBorder(new EmptyBorder(25, 30, 85, 35));
        userFormPanel.add(headerLabel);
        headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        userFormPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        Dimension myCustomSize = new Dimension(20, 40);

        nameTextField.setPreferredSize(myCustomSize);
        userFormPanel.add(nameTextField);
        userFormPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        nameTextField.setMinimumSize(panelSize);
        nameTextField.setMaximumSize(panelSize);
        nameTextField.setOpaque(false);

        emailTextField.setPreferredSize(myCustomSize);
        emailTextField.setMinimumSize(panelSize);
        emailTextField.setMaximumSize(panelSize);
        emailTextField.setOpaque(false);
        userFormPanel.add(emailTextField);
        userFormPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        subjectTextField.setPreferredSize(myCustomSize);
        subjectTextField.setMinimumSize(panelSize);
        subjectTextField.setMaximumSize(panelSize);
        subjectTextField.setOpaque(false);
        userFormPanel.add(subjectTextField);
        userFormPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JScrollPane comment = new JScrollPane(commentTextArea);
        Dimension scrollPaneSize = new Dimension(500, 75);
        commentTextArea.setLineWrap(true);
        commentTextArea.setWrapStyleWord(true);
        commentTextArea.setOpaque(true);
        commentTextArea.setBackground(color4);
        comment.setPreferredSize(scrollPaneSize);
        comment.setMinimumSize(scrollPaneSize);
        comment.setMaximumSize(scrollPaneSize);
        comment.getVerticalScrollBar().setUnitIncrement(16);
        comment.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        comment.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        userFormPanel.add(comment);
        userFormPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        userFormPanel.add(rateLabel);
        rateLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);

        ratePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        Dimension ratePanelSize = new Dimension(350, 50);

        ratePanel.setPreferredSize(ratePanelSize);
        ratePanel.setMinimumSize(ratePanelSize);
        ratePanel.setMaximumSize(ratePanelSize);

        for (JToggleButton stars : star) {
            ratePanel.add(stars);
        }
        userFormPanel.add(ratePanel);
        userFormPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        Dimension submit = new Dimension(288, 65);
        submitButton.setPreferredSize(submit);
        submitButton.setMinimumSize(submit);
        submitButton.setMaximumSize(submit);

        userFormPanel.add(submitButton);

        adminPanel.setLayout(new BoxLayout(adminPanel, BoxLayout.Y_AXIS));

        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(viewButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(updateButton);

        adminPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        adminPanel.add(buttonPanel);
        adminPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JScrollPane scroll = new JScrollPane(table);
        adminPanel.add(scroll);

        mainPanel.add(userFormPanel);

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, mainPanel, adminPanel);
        splitPane.setResizeWeight(0.25);       // 50/50 initial split; adjust as you like
        splitPane.setOneTouchExpandable(false);
        splitPane.setDividerSize(10);

        this.add(splitPane);
    }

    private void setPlaceholder(JTextComponent field, String placeholder) {
        field.setText(placeholder);
        field.setForeground(Color.GRAY);
    }

// For JTextField
    private void setupTextField(JTextField field, String placeholder) {
        setPlaceholder(field, placeholder);
        field.setForeground(Color.GRAY);
        field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (field.getForeground().equals(Color.GRAY)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                }
            }
        });
        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) {
                    setPlaceholder(field, placeholder);
                }
            }
        });
    }

// For JTextArea
    private void setupTextArea(JTextArea area, String placeholder) {
        setPlaceholder(area, placeholder);
        area.setForeground(Color.GRAY);
        area.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (area.getForeground().equals(Color.GRAY)) {
                    area.setText("");
                    area.setForeground(Color.BLACK);
                }
            }
        });
        area.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (area.getText().isEmpty()) {
                    setPlaceholder(area, placeholder);
                }
            }
        });
    }

    private JToggleButton createStar() {
        JToggleButton star = new JToggleButton("\u2606");
        star.setBorderPainted(false);
        star.setContentAreaFilled(false);
        star.setFocusPainted(false);
        star.setOpaque(false);
        star.setFont(new Font("SansSerif", Font.PLAIN, 25));
        star.addItemListener(this);
        return star;
    }

    private void setStar(int rating) {
        isUpdating = true;
        currentRating = rating;

        for (int i = 0; i < star.length; i++) {
            if (i < rating) {
                star[i].setForeground(Color.YELLOW);
                star[i].setSelected(true);
            } else {
                star[i].setForeground(Color.BLACK);
                star[i].setSelected(true);
            }
        }
        isUpdating = false; // allow user interaction
    }

    private void clearStar() {
        isUpdating = true;
        for (JToggleButton star : star) {
            star.setSelected(false);
            star.setForeground(Color.BLACK);
        }
        currentRating = 0;
        isUpdating = false;
    }

    public int getRate() {
        return currentRating;
    }

    public void refresh() {
        setupTextField(nameTextField, "Name");
        setupTextField(emailTextField, "Email");
        setupTextField(subjectTextField, "Subject");
        setupTextArea(commentTextArea, "Comment/Feedback");

        clearStar();
        nameTextField.requestFocus();

    }

    public void refreshTable() {

        FeedbackDAO feedbackDAO = new FeedbackDAO();

        try {

            ArrayList<Feedback> feedbackList = feedbackDAO.getAllFeedback();

            tableModel.setRowCount(0);

            for (Feedback f : feedbackList) {
                Object[] row = {
                    f.getFeedbackId(),
                    f.getName(),
                    f.getEmail(),
                    f.getSubject(),
                    f.getFeedback(),
                    f.getRating()
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
        if (e.getSource() == submitButton) {
            String name = nameTextField.getText();
            String email = emailTextField.getText();
            String subject = subjectTextField.getText();
            String comment = commentTextArea.getText();
            int rate = getRate();

            if (!name.isEmpty() && !name.equals("Name") && !email.isEmpty() && !email.equals("Email") && !subject.isEmpty()
                    && !subject.equals("Subject") && !comment.isEmpty() && !comment.equals("Comment/Feedback")) {

                Feedback feedback = new Feedback(0, name, email, subject, comment, rate);
                FeedbackDAO feedbackDAO = new FeedbackDAO();
                try {
                    feedbackDAO.submit(feedback);
                } catch (SQLException ex) {
                    Logger.getLogger(FeedbackGUI.class.getName()).log(Level.SEVERE, null, ex);
                }

                JOptionPane.showMessageDialog(null, "Thank you " + name + " for helping us improve !!we have recieved your feedback.");
                refresh();
            } else {
                JOptionPane.showMessageDialog(null, "Message not sent please enter all required fields");
            }
        } else if (e.getSource() == viewButton) {
            try {
                FeedbackDAO feedbackDAO = new FeedbackDAO();
                // Clear existing rows
                tableModel.setRowCount(0);

                ArrayList<Feedback> feedbackList = feedbackDAO.getAll();
                if (feedbackList.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No records found,wait for user to submit feedback");

                } else {

                    for (Feedback f : feedbackList) {
                        String[] rowData = {
                            String.valueOf(f.getFeedbackId()),
                            f.getName(),
                            f.getEmail(),
                            f.getSubject(),
                            f.getFeedback(),
                            String.valueOf(f.getRating()),};
                        tableModel.addRow(rowData);

                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(FeedbackGUI.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (e.getSource() == deleteButton) {
            FeedbackDAO feedbackDAO = new FeedbackDAO();
            int selectedRow = table.getSelectedRow();

            if (selectedRow != -1) {
                // Assuming ID is in column 0
                int feedbackId = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());

                // Call your DAO delete method
                int response = JOptionPane.showConfirmDialog(
                        null,
                        "Are you sure you want to delete this row?",
                        "Confirmation",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );
                if (response == JOptionPane.YES_OPTION) {
                    int result = feedbackDAO.deleteById(feedbackId);
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

        } else if (e.getSource() == updateButton) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int feedbackId = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
                String name = nameTextField.getText();
                String email = emailTextField.getText();
                String subject = subjectTextField.getText();
                String comment = commentTextArea.getText();
                int rate = getRate();

                if (!name.isEmpty() && !name.equals("Name") && !email.isEmpty() && !email.equals("Email") && !subject.isEmpty()
                        && !subject.equals("Subject") && !comment.isEmpty() && !comment.equals("Comment/Feedback")) {
                    Feedback feedback = new Feedback(feedbackId, name, email, subject, comment, rate);
                    FeedbackDAO feedbackDAO = new FeedbackDAO();
                    int result = feedbackDAO.updateById(feedback);
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
        if (isUpdating) {
            return;
        }
        for (int i = 0; i < star.length; i++) {
            if (e.getSource() == star[i]) {
                setStar(i + 1); // always set rating to this star's position, no toggle-to-0
                if (getRate() > 0) {
                    JOptionPane.showMessageDialog(null, "You have rated us " + getRate());
                }

                break;

            }
        }

    }
}
