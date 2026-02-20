package com.pet.petbackend.controller;

import com.pet.petbackend.dto.LoginRequest;
import com.pet.petbackend.dto.RegisterRequest;
import com.pet.petbackend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor 
public class AuthController {

    // These must be 'final' for @RequiredArgsConstructor to inject them
    
    private final AuthService authService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

@PostMapping("/verify-otp")
    public String verifyOtp(@RequestParam String email, @RequestParam String otp) {
        return authService.verifyOtp(email, otp);
    }

    @PostMapping("/resend-otp")
    public String resendOtp(@RequestParam String email) {
        return authService.resendOtp(email);
    }

    @PostMapping(value = "/login")
    public String login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}