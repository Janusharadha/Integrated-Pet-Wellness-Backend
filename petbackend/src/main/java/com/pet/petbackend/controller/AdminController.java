package com.pet.petbackend.controller;

import com.pet.petbackend.entity.User;
import com.pet.petbackend.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    // ✅ View all users
    @GetMapping("/users")
    public List<User> getAllUsers(@RequestParam String email) {

        User admin = userRepository.findByEmail(email).orElse(null);

        if (admin == null || !admin.getRole().equals("ADMIN")) {
            throw new RuntimeException("Access Denied - Not Admin");
        }

        return userRepository.findAll();
    }

    // ✅ Approve user
    @PutMapping("/approve/{id}")
    public String approveUser(@PathVariable Long id,
                              @RequestParam String email) {

        User admin = userRepository.findByEmail(email).orElse(null);

        if (admin == null || !admin.getRole().equals("ADMIN")) {
            return "Access Denied";
        }

        User user = userRepository.findById(id).orElse(null);

        if (user == null) {
            return "User not found";
        }

        user.setApproved(true);
        userRepository.save(user);

        return "User approved successfully";
    }

    // ✅ Delete user
    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id,
                             @RequestParam String email) {

        User admin = userRepository.findByEmail(email).orElse(null);

        if (admin == null || !admin.getRole().equals("ADMIN")) {
            return "Access Denied";
        }

        userRepository.deleteById(id);

        return "User deleted successfully";
    }
}
