package com.pet.petbackend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pet.petbackend.entity.Appointment;
import com.pet.petbackend.repository.AppointmentRepository;

@Service
public class AppointmentService {

    private final AppointmentRepository repo;

    public AppointmentService(AppointmentRepository repo) {
        this.repo = repo;
    }

    public Appointment save(Appointment a) {
        return repo.save(a);
    }

    public Appointment update(Long id, Appointment a) {
        Appointment old = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        old.setPetName(a.getPetName());
        old.setOwnerName(a.getOwnerName());
        old.setAppointmentTime(a.getAppointmentTime());
        old.setBooked(a.isBooked());
        old.setBookedByRole(a.getBookedByRole());

        return repo.save(old);
    }

    public List<Appointment> getAll() {
        return repo.findAll();
    }
}