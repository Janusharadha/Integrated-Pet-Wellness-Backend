package com.pet.petbackend.service;

import com.pet.petbackend.entity.HealthRecord;
import com.pet.petbackend.repository.HealthRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HealthRecordService {

    @Autowired
    private HealthRecordRepository healthRecordRepository;

    // Add vaccination record
    public HealthRecord addRecord(HealthRecord record) {
        return healthRecordRepository.save(record);
    }

    // Update vaccination record
    public HealthRecord updateRecord(Long id, HealthRecord updatedRecord) {

        HealthRecord record = healthRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Health record not found"));

        record.setVaccineName(updatedRecord.getVaccineName());
        record.setVaccinationDate(updatedRecord.getVaccinationDate());
        record.setNextDueDate(updatedRecord.getNextDueDate());
        record.setVeterinarian(updatedRecord.getVeterinarian());
        record.setNotes(updatedRecord.getNotes());

        return healthRecordRepository.save(record);
    }

    // View records by pet
    public List<HealthRecord> getRecordsByPet(String petName) {
        return healthRecordRepository.findByPetName(petName);
    }
}
