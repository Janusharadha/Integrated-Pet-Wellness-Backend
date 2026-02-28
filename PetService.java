package com.pet.petbackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pet.petbackend.entity.Pet;
import com.pet.petbackend.repository.PetRepository;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    public Pet addPet(Pet pet, String ownerUsername) {
        pet.setOwnerUsername(ownerUsername);
        return petRepository.save(pet);
    }

    public Pet updatePet(Long id, Pet updatedPet, String ownerUsername) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pet not found"));

        if (pet.getOwnerUsername() == null ||
            !pet.getOwnerUsername().equals(ownerUsername)) {
            throw new RuntimeException("Unauthorized access");
        }

        pet.setName(updatedPet.getName());
        pet.setType(updatedPet.getType());
        pet.setBreed(updatedPet.getBreed());
        pet.setAge(updatedPet.getAge());

        return petRepository.save(pet);
    }


    public Pet updatePetById(Long petId, Pet pet) {
    Pet existingPet = petRepository.findById(petId)
            .orElseThrow(() -> new RuntimeException("Pet not found"));

    existingPet.setName(pet.getName());
    existingPet.setType(pet.getType());
    existingPet.setAge(pet.getAge());

    return petRepository.save(existingPet);
}

public void deletePetById(Long petId) {
    if (!petRepository.existsById(petId)) {
        throw new RuntimeException("Pet not found");
    }
    petRepository.deleteById(petId);
}

    public void deletePet(Long id, String ownerUsername) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pet not found"));

        if (pet.getOwnerUsername() == null ||
            !pet.getOwnerUsername().equals(ownerUsername)) {
            throw new RuntimeException("Unauthorized access");
        }

        petRepository.delete(pet);
    }

    public List<Pet> getPetsByOwner(String ownerUsername) {
        return petRepository.findByOwnerUsername(ownerUsername);
    }

    public List<Pet> addMultiplePets(List<Pet> pets, String owner) {
    for (Pet pet : pets) {
        pet.setOwnerUsername(owner);
    }
    return petRepository.saveAll(pets);
}
}