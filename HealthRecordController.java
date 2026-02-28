package com.pet.petbackend.controller;

import com.pet.petbackend.entity.HealthRecord;
import com.pet.petbackend.service.HealthRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/health-records")
public class HealthRecordController {

    @Autowired
    private HealthRecordService healthRecordService;

    // Add vaccination record
    @PostMapping
    public HealthRecord addRecord(@RequestBody HealthRecord record) {
        return healthRecordService.addRecord(record);
    }

    // Update vaccination record
    @PutMapping("/{id}")
    public HealthRecord updateRecord(@PathVariable Long id,
                                     @RequestBody HealthRecord record) {
        return healthRecordService.updateRecord(id, record);
    }

    // View records by pet
    @GetMapping("/pet/{petName}")
    public List<HealthRecord> getRecordsByPet(@PathVariable String petName) {
        return healthRecordService.getRecordsByPet(petName);
    }
}
