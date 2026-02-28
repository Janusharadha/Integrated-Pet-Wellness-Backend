package com.pet.petbackend.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "health_records")
public class HealthRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String petName;

    private String vaccineName;

    private LocalDate vaccinationDate;

    private LocalDate nextDueDate;

    private String veterinarian;

    private String notes;

    // ===== GETTERS =====
    public Long getId() {
        return id;
    }

    public String getPetName() {
        return petName;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public LocalDate getVaccinationDate() {
        return vaccinationDate;
    }

    public LocalDate getNextDueDate() {
        return nextDueDate;
    }

    public String getVeterinarian() {
        return veterinarian;
    }

    public String getNotes() {
        return notes;
    }

    // ===== SETTERS =====
    public void setId(Long id) {
        this.id = id;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public void setVaccinationDate(LocalDate vaccinationDate) {
        this.vaccinationDate = vaccinationDate;
    }

    public void setNextDueDate(LocalDate nextDueDate) {
        this.nextDueDate = nextDueDate;
    }

    public void setVeterinarian(String veterinarian) {
        this.veterinarian = veterinarian;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
