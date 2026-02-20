package com.pet.petbackend.controller;
  // ⚠ Change to your package name

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pet.petbackend.service.EmailService;  // ⚠ Change to your package name

@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/send")
    public String sendMail() {

        emailService.sendEmail(
                "receiveremail@gmail.com",   // change to your email
                "Test Mail",
                "SMTP is working correctly"
        );

        return "Email sent successfully";
    }
}