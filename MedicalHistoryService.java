package com.pet.petbackend.service;

import com.pet.petbackend.entity.MedicalHistory;
import com.pet.petbackend.repository.MedicalHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalHistoryService {

    private final MedicalHistoryRepository repository;

    public MedicalHistoryService(MedicalHistoryRepository repository) {
        this.repository = repository;
    }

    // INSERT
    public MedicalHistory save(MedicalHistory history) {
        return repository.save(history);
    }

    // UPDATE (NO INSERT)
    public MedicalHistory update(Long id, MedicalHistory history) {

        MedicalHistory existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("MedicalHistory ID not found"));

        existing.setPetId(history.getPetId());
        existing.setDiagnosis(history.getDiagnosis());
        existing.setTreatment(history.getTreatment());
        existing.setVisitDate(history.getVisitDate());

        return repository.save(existing);
    }


    public void delete(Long id) {
    if (!repository.existsById(id)) {
        throw new RuntimeException("MedicalHistory ID not found");
    }
    repository.deleteById(id);
}

    public MedicalHistory getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<MedicalHistory> getAll() {
        return repository.findAll();
    }
}