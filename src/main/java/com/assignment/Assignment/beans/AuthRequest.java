package com.assignment.Assignment.beans;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
    @NotEmpty(message = "email cannot be empty")
    @Email(message = "Invalid email format")
    private String email;
    @NotEmpty(message = "password cannot be empty")
    private String password;
}