package com.pet.petbackend.service;

import org.springframework.stereotype.Service;
import java.util.List;
import com.pet.petbackend.entity.Vaccination;
import com.pet.petbackend.repository.VaccinationRepository;

@Service
public class VaccinationService {

    private final VaccinationRepository repo;

    public VaccinationService(VaccinationRepository repo) {
        this.repo = repo;
    }

    public Vaccination save(Vaccination v) {
        return repo.save(v);
    }

    public Vaccination updateVaccination(Long id, Vaccination v) {
        Vaccination existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Vaccination not found"));

        existing.setVaccineName(v.getVaccineName());
        existing.setVaccinationDate(v.getVaccinationDate());
        existing.setNextDueDate(v.getNextDueDate());
        existing.setStatus(v.getStatus());
        existing.setReminderSent(v.isReminderSent());

        return repo.save(existing);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    public List<Vaccination> getAll() {
        return repo.findAll();
    }
}