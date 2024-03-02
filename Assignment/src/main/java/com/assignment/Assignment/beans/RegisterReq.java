package com.assignment.Assignment.beans;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterReq {
    private String fullName;
    private String lastName;
    private String email;
    private String password;

}