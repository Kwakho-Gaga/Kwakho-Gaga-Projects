/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.cput.sikunyecharity.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import za.ac.cput.sikunyecharity.DBConection.DBConnection;
import za.ac.cput.sikunyecharity.Domain.Contact;
import za.ac.cput.sikunyecharity.Domain.Feedback;

/**
 *
 * @author Gaga Kwakho
 */
public class FeedbackDAO {

    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;

    public FeedbackDAO() {

        try {
            this.connection = DBConnection.derbyConnection();
        } catch (SQLException ex) {
            Logger.getLogger(FeedbackDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void createTable() {
        String dropTableSQL ="DROP TABLE IF EXISTS Feedback CASCADE";
        String table = "CREATE TABLE Feedback ("
                + "id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "
                + "Name VARCHAR(100), "
                + "Email VARCHAR(50), "
                + "Subject VARCHAR(100), "
                + "Feedback VARCHAR(500),"
                + "Rating INT "
                + ")";
        try {
            statement = this.connection.createStatement();
            statement.executeUpdate(dropTableSQL);
            System.out.println("Existing table dropped.");
        } catch (SQLException ex) {
            Logger.getLogger(FeedbackDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            this.statement.executeUpdate(table);
            System.out.println("Table successfully created");
        } catch (SQLException ex) {
            Logger.getLogger(FeedbackDAO.class.getName()).log(Level.SEVERE, null, ex);
        }  finally {

            if (statement != null) {

            }

            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(FeedbackDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void submit(Feedback feedback) throws SQLException {
        String sql = "INSERT INTO Feedback (Name, Email,  Subject, Feedback,Rating) VALUES (?, ?, ?, ?, ?)";

        this.preparedStatement = this.connection.prepareStatement(sql);
        this.connection.setAutoCommit(true);

        preparedStatement.setString(1, feedback.getName());
        preparedStatement.setString(2, feedback.getEmail());
        preparedStatement.setString(3, feedback.getSubject());
        preparedStatement.setString(4, feedback.getFeedback());
        preparedStatement.setInt(5, feedback.getRating());

        int ok = preparedStatement.executeUpdate();
        if (ok > 0) {
            System.out.println("Database successfully saved the row.");
        }

    }

    public ArrayList<Feedback> getAll() throws SQLException {

        ArrayList<Feedback> feedbackList = new ArrayList();
        String getAll = "SELECT * FROM feedback";
        try {
            preparedStatement = this.connection.prepareStatement(getAll);
        } catch (SQLException ex) {
            Logger.getLogger(FeedbackDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        ResultSet rs;
        rs = preparedStatement.executeQuery();
        if (rs != null) {
            try {
                while (rs.next()) {

                    try {
                        System.out.println("DataBase record:" + rs.getInt(1)
                                + "" + rs.getString(2)
                                + "" + rs.getString(3)
                                + "" + rs.getString(4)
                                + "" + rs.getString(5)
                                + "" + rs.getInt(6)
                        );
                    } catch (SQLException ex) {
                        Logger.getLogger(FeedbackDAO.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        feedbackList.add(new Feedback(rs.getInt(1),
                                rs.getString(2),
                                rs.getString(3),
                                rs.getString(4),
                                rs.getString(5),
                                rs.getInt(6)
                        ));
                    } catch (SQLException ex) {
                        Logger.getLogger(FeedbackDAO.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(FeedbackDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(FeedbackDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {

                if (preparedStatement != null) {
                    preparedStatement.close();

                }

            }
        }

        return feedbackList;
    }

    public int deleteById(int feedbackId) {
        int rowsAffected = 0;
        String sql = "DELETE FROM Feedback WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, feedbackId);
            rowsAffected = pstmt.executeUpdate();
        } catch (SQLException ex) {
            // handle exception
            Logger.getLogger(Feedback.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowsAffected;
    }

  
    
      public ArrayList<Feedback> getAllFeedback() {
    ArrayList<Feedback> feedback = new ArrayList<>();
           String sql = "SELECT * FROM feedback";

    try (Statement stmt = connection.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {
        while (rs.next()) {
            Feedback f = new Feedback(
                rs.getInt(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                rs.getInt(6)
            );
            feedback.add(f);
        }
    } catch (SQLException ex) {
   JOptionPane.showInternalMessageDialog(null, ex);
    }
    
    return feedback;
} 
public int updateById(Feedback feedback) {
    int rowsAffected = 0;
    String sql = "UPDATE Feedback SET Name = ?, Email = ?, Subject = ?, Feedback = ?, Rating = ? WHERE id = ?";
    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
        pstmt.setString(1, feedback.getName());
        pstmt.setString(2, feedback.getEmail());
        pstmt.setString(3, feedback.getSubject());
        pstmt.setString(4, feedback.getFeedback());
        pstmt.setInt(5, feedback.getRating());
        pstmt.setInt(6, feedback.getFeedbackId());
        rowsAffected = pstmt.executeUpdate();
    } catch (SQLException ex) {
        Logger.getLogger(FeedbackDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return rowsAffected;
}
}

     
      
      
      
      
     
   
    

    






