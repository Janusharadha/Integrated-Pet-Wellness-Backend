package com.pet.petbackend.controller;

import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import com.pet.petbackend.dto.LoginRequest;
import com.pet.petbackend.dto.RegisterRequest;
import com.pet.petbackend.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}