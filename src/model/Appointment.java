package model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Appointment {
    private int appointmentId;
    private String title;
    private String description;
    private String location;
    private String type;
    private LocalDateTime start;
    private LocalDateTime end;
    private LocalDateTime createTime;
    private String author;
    private LocalDateTime lastUpdate;
    private String lastUpdateAuthor;
    private int customerId;
    private String customer;
    private int contactId;
    private String contact;
    private int userId;
    private String user;

    public Appointment(Integer aId, String aTitle, String aDescription, String aLocation, String aType, LocalDateTime aStart, LocalDateTime aEnd, LocalDateTime aCreatedTime, String aAuthor, LocalDateTime aLastUpdate, String aLastUpdateAuthor, Integer aCustomerId, String aCustomer, Integer aContactId, String aContact, Integer aUserId, String aUser){
        appointmentId = aId;
        title = aTitle;
        description = aDescription;
        location = aLocation;
        type = aType;
        start = aStart;
        end = aEnd;
        createTime = aCreatedTime;
        author = aAuthor;
        lastUpdate = aLastUpdate;
        lastUpdateAuthor  = aLastUpdateAuthor;
        customer = aCustomer;
        customerId = aCustomerId;
        contactId = aContactId;
        contact = aContact;
        userId = aUserId;
        user = aUser;
    }

    /**Get method for appt id*/
    public int getAppointmentId(
    ){ return appointmentId;}

    /**Set method for appt id*/
    public void setAppointmentId(int appointmentId){
        this.appointmentId = appointmentId;
    }

    /**Get method for title id*/
    public String getTitle(
    ){ return title;}

    /**Set method for customer id*/
    public void setTitle(String title){
        this.title = title;
    }

    /**Get method for customer id*/
    public String getDescription(
    ){ return description;}

    /**Get method for customer id*/
    public String getLocation(
    ){ return location;}

    /**Get method for customer id*/
    public String getType(
    ){ return type;}

    public void setType(String type) {
        this.type = type;
    }

    /**Get method for start time*/
    public LocalDateTime getStart() {
        return start;
    }

    /**Set method for start time*/
    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    /**Get method for end time*/
    public LocalDateTime getEnd() {
        return end;
    }

    /**Set method for end time*/
    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    /**Get method for created time*/
    public LocalDateTime getCreatedTime() {
        return createTime;
    }

    /**Set method for created time*/
    public void setCreatedTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    /**Get method for author*/
    public String getAuthor() {
        return author;
    }

    /**Set method for author*/
    public void setAuthor(String author) {
        this.author = author;
    }

    /**Get method for last update*/
    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    /**Set method for last update*/
    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**Get method for last update author*/
    public String getLastUpdateAuthor() {
        return lastUpdateAuthor;
    }

    /**Set method for last update author*/
    public void setLastUpdateAuthor(String lastUpdateAuthor) {
        this.lastUpdateAuthor = lastUpdateAuthor;
    }

    /**Get method for customer id*/
    public int getCustomerId() {
        return customerId;
    }

    /**Set method for customer id*/
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**Get method for customer*/
    public String getCustomer() {
        return customer;
    }

    /**Set method for start time*/
    public void setCustomer(String customer) {
        this.customer = customer;
    }

    /**Get method for contact id*/
    public int getContactId() {
        return contactId;
    }

    /**Set method for contact id*/
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /**Get method for contact*/
    public String getContact() {
        return contact;
    }

    /**Set method for contact*/
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**Get method for user id*/
    public int getUserId() {
        return userId;
    }

    /**set method for user id*/
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**Get method for user*/
    public String getUser() {
        return user;
    }

    /**set method for user*/
    public void setUser(String user) {
        this.user = user;
    }

}




