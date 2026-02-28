package com.pet.petbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pet.petbackend.entity.Vaccination;
import java.time.LocalDate;
import java.util.List;

public interface VaccinationRepository extends JpaRepository<Vaccination, Long> {

    List<Vaccination> findByNextDueDateLessThanEqual(LocalDate date);
}