/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package za.ac.cput.sikunyecharity; 
import javax.swing.ImageIcon;
import za.ac.cput.sikunyecharity.DAO.ContactDAO;
import za.ac.cput.sikunyecharity.DAO.FeedbackDAO;
import za.ac.cput.sikunyecharity.GUI.ContactGUI;
import za.ac.cput.sikunyecharity.GUI.ContactTable;
import za.ac.cput.sikunyecharity.GUI.FeedbackGUI;

/**
 *
 * @author Gaga Kwakho
 */
public class SikunyeCharity {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        FeedbackDAO dao = new FeedbackDAO();
        ContactGUI contact = new ContactGUI();
        FeedbackGUI feedback = new FeedbackGUI();
        ContactTable table = new ContactTable();
//        contact.setGUI();
//        contact.setVisible(true);
//        contact.setSize(1000, 2000);
//        contact.pack();

        feedback.setGui();
        feedback.setVisible(true);
        feedback.setSize(1000, 1000);
        feedback.pack();

        table.setGui();
        table.setVisible(true);
        table.setSize(1000, 2000);
        table.pack();

        // dao.createTable();
    }

}
