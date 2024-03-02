package com.assignment.Assignment.service;

import com.assignment.Assignment.beans.AuthRequest;
import com.assignment.Assignment.beans.AuthResponse;
import com.assignment.Assignment.beans.UserDTO;
import com.assignment.Assignment.dto.UserMapper;
import com.assignment.Assignment.repository.UserRepository;
import com.assignment.Assignment.security.JwtService;
import com.assignment.Assignment.security.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final UserMapper userMapper;

    public AuthResponse register(UserDTO request) throws Exception {
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        if (UserRole.ROLE_ADMIN == request.getRole()) {
            throw new IllegalArgumentException("Cannot create user with admin role");
        }
        if (repository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }
        repository.save(userMapper.userDTOToEntity(request));
        var jwtToken = jwtService.generateToken(User.builder().
                username(request.getEmail())
                .password(request.getPassword())
                .authorities(request.getRole().toString())
                .build());
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthResponse authenticate(AuthRequest request) throws Exception {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(User.builder().
                username(user.getEmail())
                .password(user.getPassword())
                .authorities(user.getRole().toString())
                .build());
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }
}