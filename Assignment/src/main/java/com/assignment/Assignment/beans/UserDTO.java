package com.assignment.Assignment.beans;

import com.assignment.Assignment.enums.UserCategory;
import com.assignment.Assignment.security.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private Long id;

    @NotNull(message = "fullName cannot be empty")
    private String fullName;

    @Email(message = "Invalid email format")
    private String email;

    @NotEmpty(message = "password cannot be empty")
    private String password;

    private LocalDate dateOfBirth;
    @NotNull(message = "UserRole cannot be empty")
    private UserRole role;
}
