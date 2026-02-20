package com.pet.petbackend.entity;


import java.security.SecureRandom;

public class OtpGenerator {

    private static final SecureRandom random = new SecureRandom();

    /**
     * Generates numeric OTP of given length
     */
    public static String generateOtp(int length) {
        if (length <= 0) throw new IllegalArgumentException("Length must be positive");

        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < length; i++) {
            otp.append(random.nextInt(10)); // 0-9
        }
        return otp.toString();
    }
}