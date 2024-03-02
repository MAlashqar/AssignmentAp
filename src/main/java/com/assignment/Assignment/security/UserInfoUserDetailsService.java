package com.assignment.Assignment.security;

import com.assignment.Assignment.entity.UserEntity;
import com.assignment.Assignment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
@RequiredArgsConstructor
public class UserInfoUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserEntity> userInfo = repository.findByEmail(email);
        return userInfo.map(x ->
                        User.builder().
                                username(x.getEmail())
                                .password(x.getPassword())
                                .authorities(x.getRole().toString())
                                .build()
                )
                .orElseThrow(() -> new UsernameNotFoundException("email  not found " + email));


    }
}
