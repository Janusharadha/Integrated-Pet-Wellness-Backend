package com.pet.petbackend.entity;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;
@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean verified=false;
    private String name;

    @Column(unique = true)
    private String email;

@Column(name = "otp")
private String otp;

@Column(name = "otp_expiry")
private LocalDateTime otpExpiry;

@Column(name = "is_verified")
private boolean isVerified =false;

    private String password;

    private String role ="ROLE_USER"; // ADMIN or OWNER

    private boolean approved = false;

    private boolean emailVerified = false;

    private boolean firstLogin = true;

    
}