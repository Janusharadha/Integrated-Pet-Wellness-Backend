package com.pet.petbackend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.pet.petbackend.entity.Appointment;
import com.pet.petbackend.service.AppointmentService;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService service;

    // ✅ CONSTRUCTOR INJECTION (IMPORTANT)
    public AppointmentController(AppointmentService service) {
        this.service = service;
    }

    // ADD APPOINTMENT
    @PostMapping
    public Appointment add(@RequestBody Appointment a) {
        return service.save(a);
    }

    // UPDATE APPOINTMENT
    @PutMapping("/{id}")
    public Appointment update(@PathVariable Long id,
                              @RequestBody Appointment a) {
        return service.update(id, a);
    }

    // GET ALL
    @GetMapping
    public List<Appointment> getAll() {
        return service.getAll();
    }
}