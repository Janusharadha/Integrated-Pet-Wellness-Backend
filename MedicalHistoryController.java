package com.pet.petbackend.controller;

import com.pet.petbackend.entity.MedicalHistory;
import com.pet.petbackend.service.MedicalHistoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medical_history")
@CrossOrigin("*")
public class MedicalHistoryController {

    private final MedicalHistoryService service;

    public MedicalHistoryController(MedicalHistoryService service) {
        this.service = service;
    }

    @PutMapping("/{id}")
public MedicalHistory update(
        @PathVariable Long id,
        @RequestBody MedicalHistory history) {

    return service.update(id, history);
}

    // POST
    @PostMapping("/")
    public MedicalHistory add(@RequestBody MedicalHistory history) {
        return service.save(history);
    }

    // GET by ID
    @GetMapping("/{id}")
    public MedicalHistory get(@PathVariable Long id) {
        return service.getById(id);
    }

    // GET all
    @GetMapping("/")
    public List<MedicalHistory> getAll() {
        return service.getAll();
    }


    @DeleteMapping("/{id}")
public String delete(@PathVariable Long id) {
    service.delete(id);
    return "Medical history deleted successfully";
}

}