/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.cput.sikunyecharity.Domain;

/**
 *
 * @author Gaga Kwakho
 */
public class Feedback {
    
    private int feedbackId;
   private  String name;
      private  String email;
    private  String subject;
   private  String feedback;
   private int rating;

    public Feedback(int feedbackId, String name, String email, String subject, String feedback, int rating) {
        this.feedbackId = feedbackId;
        this.name = name;
        this.email = email;
        this.subject = subject;
        this.feedback = feedback;
        this.rating = rating;
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Feedback{" + "feedbackId=" + feedbackId + ", name=" + name + ", email=" + email + ", subject=" + subject + ", feedback=" + feedback + ", rating=" + rating + '}';
    }

   


}
