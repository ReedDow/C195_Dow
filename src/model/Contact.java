package model;

import java.time.LocalDateTime;

public class Contact {

    private int appointmentId;
    private String title;
    private String description;
    private String type;
    private LocalDateTime start;
    private LocalDateTime end;
    private int customerId;

    public Contact(int appointmentId, String title, int customerId, LocalDateTime end, LocalDateTime start, String type, String description){
        appointmentId = appointmentId;


    }

    public Contact() {

    }


    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
