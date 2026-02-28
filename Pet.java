package com.pet.petbackend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "pets")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;
    private String species;
    private int age;

    // ✅ NEW FIELD
    private String breed;

    // Owner username/email
    @Column(name = "owner_username")
    private String ownerUsername;

    // Constructors
    public Pet() {}

    public Pet(String name, String type, int age, String breed, String ownerUsername) {
        this.name = name;
        this.type = type;
        this.age = age;
        this.breed = breed;
        this.ownerUsername = ownerUsername;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    // ✅ BREED getters/setters
    public String getBreed() { return breed; }
    public void setBreed(String breed) { this.breed = breed; }

    public String getOwnerUsername() { return ownerUsername; }
    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }
}