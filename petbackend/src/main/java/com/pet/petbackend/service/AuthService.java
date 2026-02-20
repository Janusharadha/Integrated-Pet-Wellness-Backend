package com.pet.petbackend.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import com.pet.petbackend.dto.LoginRequest;
import com.pet.petbackend.dto.RegisterRequest;
import com.pet.petbackend.entity.User;
import com.pet.petbackend.repository.UserRepository;
import com.pet.petbackend.security.JwtUtil;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final EmailService emailService;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // ================= REGISTER WITH USER =================
   public String register(User user) {

    user.setApproved(false);
    user.setRole("ROLE_USER");

    // Generate 6-digit OTP
    String otp = String.valueOf(new Random().nextInt(900000) + 100000);
    user.setOtp(otp);

    // Set OTP expiry for 5 minutes
    user.setOtpExpiry(LocalDateTime.now().plusMinutes(5));
    user.setVerified(false);

    userRepository.save(user);

    // Send OTP via your existing EmailService
    emailService.sendEmail(
            user.getEmail(),
            "OTP Verification",
            "Your OTP is: " + otp + "\nValid for 5 minutes."
    );

    return "OTP sent to email. Please verify to activate your account.";
}
//==========otp - resend==================
public String resendOtp(String email) {

    Optional<User> optionalUser = userRepository.findByEmail(email);

    if (optionalUser.isEmpty()) {
        return "User not found";
    }

    User user = optionalUser.get();

    String otp = String.valueOf(new Random().nextInt(900000) + 100000);
    user.setOtp(otp);
    user.setOtpExpiry(LocalDateTime.now().plusMinutes(5));
    userRepository.save(user);

    emailService.sendEmail(
            user.getEmail(),
            "Resend OTP",
            "Your new OTP is: " + otp + "\nValid for 5 minutes."
    );

    return "OTP resent successfully. Check your email.";
}

    // ================= REGISTER WITH DTO =================
    public String register(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return "Email already exists!";
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("ROLE_ADMIN");
        user.setApproved(false);

        userRepository.save(user);

        return "Registration successful. Wait for admin approval.";
    }
//================verifyOtp=================
    
public String verifyOtp(String email, String otp) {

    Optional<User> optionalUser = userRepository.findByEmail(email);

    if (optionalUser.isEmpty()) {
        return "User not found";
    }

    User user = optionalUser.get();

    // Check if OTP is present
    if (user.getOtp() == null) {
        return "OTP not generated. Please register again.";
    }

    // Check if OTP expired
    if (user.getOtpExpiry().isBefore(LocalDateTime.now())) {
        return "OTP expired. Please request a new OTP.";
    }

    // Check if OTP matches
    if (!user.getOtp().equals(otp)) {
        return "Invalid OTP. Please try again.";
    }

    // OTP correct → verify user
    user.setVerified(true);
    user.setOtp(null);          // clear OTP
    user.setOtpExpiry(null);    // clear expiry
    userRepository.save(user);

    return "OTP verified successfully. You can now login.";
}

    // ================= LOGIN =================
    public String login(LoginRequest request) {

        Optional<User> optionalUser =
                userRepository.findByEmail(request.getEmail());

        if (optionalUser.isEmpty()) {
            return "User not found";
        }

        User user = optionalUser.get();

        // Check password
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return "Invalid password";
        }
      if (!user.isVerified()){
        return "please verify your email otp before login";
      }
        // Check admin approval
        if (!user.isApproved()) {
            return "Wait for admin approval";
        }

        // Generate JWT
        String token = jwtUtil.generateToken(user.getEmail());

        return token;
    }
}