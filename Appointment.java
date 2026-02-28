package com.pet.petbackend.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String petName;
    private String ownerName;
    private LocalDateTime appointmentTime;
    private boolean booked;

    // ✅ ADD THIS FIELD
    private String bookedByRole;

    // ===== Getters & Setters =====

    public Long getId() { return id; }

    public String getPetName() { return petName; }
    public void setPetName(String petName) { this.petName = petName; }

    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }

    public LocalDateTime getAppointmentTime() { return appointmentTime; }
    public void setAppointmentTime(LocalDateTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public boolean isBooked() { return booked; }
    public void setBooked(boolean booked) { this.booked = booked; }

    // ✅ ADD THESE METHODS
    public String getBookedByRole() { return bookedByRole; }
    public void setBookedByRole(String bookedByRole) {
        this.bookedByRole = bookedByRole;
    }
}
