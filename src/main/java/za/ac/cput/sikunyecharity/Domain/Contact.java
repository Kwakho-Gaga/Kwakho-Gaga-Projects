/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.cput.sikunyecharity.Domain;

/**
 *
 * @author Gaga Kwakho
 */
public class Contact {

    private String fullName;
    private String email;
    private String phoneNumber;
    private String subject;
    private String message;
    private int contactId;

    public Contact(int contactId, String fullName, String email, String phoneNumber, String subject, String message) {
        this.contactId = contactId;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.subject = subject;
        this.message = message;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    @Override
    public String toString() {
        return "Contact{"+ ", contactId=" + contactId + "fullName=" + fullName + ", email=" + email + ", phoneNumber=" + phoneNumber + ", subject=" + subject + ", message=" + message  + '}';
    }

}
