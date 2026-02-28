package com.pet.petbackend.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.pet.petbackend.entity.Vaccination;
import com.pet.petbackend.service.VaccinationService;

@RestController
@RequestMapping("/api/vaccinations")
public class VaccinationController {

    private final VaccinationService service;

    public VaccinationController(VaccinationService service) {
        this.service = service;
    }

    // ✅ ADD VACCINATION
    @PostMapping
    public Vaccination add(@RequestBody Vaccination v) {
        return service.save(v);
    }

    // ✅ UPDATE VACCINATION
    @PutMapping("/{id}")
    public Vaccination update(@PathVariable Long id,
                              @RequestBody Vaccination v) {
        return service.updateVaccination(id, v);
    }

    // ✅ DELETE VACCINATION
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteById(id);
        return "Vaccination deleted successfully";
    }

    // ✅ GET ALL
    @GetMapping
    public List<Vaccination> getAll() {
        return service.getAll();
    }
}