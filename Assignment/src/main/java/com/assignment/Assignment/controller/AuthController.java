package com.assignment.Assignment.controller;

import com.assignment.Assignment.beans.AuthRequest;
import com.assignment.Assignment.beans.AuthResponse;
import com.assignment.Assignment.beans.UserDTO;
import com.assignment.Assignment.security.JwtAuthenticationFilter;
import com.assignment.Assignment.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @Valid @RequestBody UserDTO request
    ) throws Exception {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(
            @Valid  @RequestBody AuthRequest request
    ) throws Exception {
        return ResponseEntity.ok(service.authenticate(request));
    }
    @PostMapping("/logout")
    public void logout(HttpServletRequest request) {
        JwtAuthenticationFilter.blacklist.add(request.getHeader("Authorization"));

    }
}