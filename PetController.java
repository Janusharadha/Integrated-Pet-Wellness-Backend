package com.pet.petbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.pet.petbackend.entity.Pet;
import com.pet.petbackend.repository.PetRepository;
import com.pet.petbackend.service.PetService;

@RestController
@RequestMapping("/api/pets")
public class PetController {

    @Autowired
    private PetService petService;

    @PostMapping("/{owner}")
    public Pet addPet(@PathVariable String owner, @RequestBody Pet pet) {
        return petService.addPet(pet, owner);
    }

    @PutMapping("/{id}/{owner}")
    public Pet updatePet(@PathVariable Long id,
                         @PathVariable String owner,
                         @RequestBody Pet pet) {
        return petService.updatePet(id, pet, owner);
    }

    @DeleteMapping("/{id}/{owner}")
    public String deletePet(@PathVariable Long id,
                            @PathVariable String owner) {
        petService.deletePet(id, owner);
        return "Pet deleted";
    }

    @PostMapping("/bulk/{owner}")
public List<Pet> addMultiplePets(
        @PathVariable String owner,
        @RequestBody List<Pet> pets) {
    return petService.addMultiplePets(pets, owner);
}

    @GetMapping("/{owner}")
    public List<Pet> getPets(@PathVariable String owner) {
        return petService.getPetsByOwner(owner);
    }


    // UPDATE PET BY pet_id
    @PutMapping("/{petId}")
    public Pet updatePet(
            @PathVariable Long petId,
            @RequestBody Pet pet) {
        return petService.updatePetById(petId, pet);
    }

    // DELETE PET BY pet_id
    @DeleteMapping("/{petId}")
    public String deletePet(@PathVariable Long petId) {
        petService.deletePetById(petId);
        return "Pet deleted successfully";
    }

}