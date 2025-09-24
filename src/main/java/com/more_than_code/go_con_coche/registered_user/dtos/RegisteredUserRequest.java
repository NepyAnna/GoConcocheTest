package com.more_than_code.go_con_coche.registered_user.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record RegisteredUserRequest(
        @NotBlank(message = "First name is required")
        @Size(max = 30, message = "First name must be less than 30 characters")
        String firstName,

        @NotBlank(message = "Last name is required")
        @Size(max = 25, message = "Last name must be less than 25 characters")
        String lastName,

        @NotBlank(message = "Date of birth is required")
        @Past(message = "Date of birth must be in the past")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate dateOfBirth,

        @NotBlank(message = "Phone number is required")
        @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Invalid phone number format")
        String phoneNumber,

        @NotBlank(message = "Username is required")
        @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
        @Pattern(regexp = "^[a-zA-Z0-9._-]{3,20}$", message = "Username can only contain letters, numbers and the special characters . _ -")
        String username,

        @NotBlank(message = "Email is required")
        @Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\\\.[a-zA-Z]{2,}$", message = "Email address must be at most 254 characters long")
        String email,

        @NotBlank (message = "Password is required")
        @Pattern(message = "Password must contain a minimum of 8 characters, including a number, one uppercase letter, one lowercase letter and one special character", regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=.])(?=\\S+$).{8,}$")
        String password
) {
}
