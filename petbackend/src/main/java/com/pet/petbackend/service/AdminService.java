package com.pet.petbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import com.pet.petbackend.entity.User;
import com.pet.petbackend.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public String approveUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setApproved(true);
        userRepository.save(user);

        return "User approved successfully";
    }

    public String deleteUser(Long id) {

        userRepository.deleteById(id);

        return "User deleted successfully";
    }
}