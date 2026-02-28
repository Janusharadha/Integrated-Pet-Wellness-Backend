package com.pet.petbackend.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import com.pet.petbackend.entity.Vaccination;
import com.pet.petbackend.repository.VaccinationRepository;

@Service
public class VaccinationReminderScheduler {

    private final VaccinationRepository repository;

    public VaccinationReminderScheduler(VaccinationRepository repository) {
        this.repository = repository;
    }

    // Runs once every day
    @Scheduled(cron = "0 0 10 * * ?")
    public void sendReminders() {

        List<Vaccination> dueVaccines =
                repository.findByNextDueDateLessThanEqual(LocalDate.now());

        for (Vaccination v : dueVaccines) {

            v.setReminderSent(true);
            v.setReminderCount(v.getReminderCount() + 1);

            if (v.getReminderCount() >= 3) {
                v.setStatus("OVERDUE");
            }

            repository.save(v);

            System.out.println("🔔 Reminder sent for Pet ID: " +
                    v.getPetId() + " Vaccine: " + v.getVaccineName());
        }
    }
}