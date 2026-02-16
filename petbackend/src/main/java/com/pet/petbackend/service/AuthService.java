package com.pet.petbackend.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.pet.petbackend.dto.LoginRequest;
import com.pet.petbackend.dto.RegisterRequest;
import com.pet.petbackend.entity.User;
import com.pet.petbackend.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public String register(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return "Email already exists!";
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("OWNER");

        userRepository.save(user);

        return "Registration successful. Wait for admin approval.";
    }

    public String login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return "Invalid password!";
        }

        if (!user.isApproved()) {
            return "Wait for admin approval.";
        }

        return "Login successful!";
    }
}