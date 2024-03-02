package com.assignment.Assignment.service;

import com.assignment.Assignment.beans.UserDTO;
import com.assignment.Assignment.dto.UserMapper;
import com.assignment.Assignment.entity.UserEntity;
import com.assignment.Assignment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;


    public UserDTO getUserById(Long id) {
        return userMapper.userEntityToDTO(userRepository.findById(id).orElse(null));
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(x -> userMapper.userEntityToDTO(x)).collect(Collectors.toList());
    }

    public UserDTO updateUser(Long id, UserDTO user) {
        UserEntity userEntity = userRepository.findById(id).orElse(null);
        if (userEntity != null) {
            user.setId(id);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRole(userEntity.getRole());
            return userMapper.userEntityToDTO(userRepository.save(userMapper.userDTOToEntity(user)));
        }
        return null;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}

