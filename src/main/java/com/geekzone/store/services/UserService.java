package com.geekzone.store.services;

import com.geekzone.store.dtos.UserRequest;
import com.geekzone.store.entities.User;
import com.geekzone.store.exceptions.ConflictException;
import com.geekzone.store.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getUsers() { return userRepository.findAll(); }

    public User createUser(UserRequest userData) {
        User existingUser = userRepository.findByEmail(userData.getEmail());
        if (existingUser != null) {
            throw new ConflictException("A user with this email already exists.");
        }
        String password = passwordEncoder.encode(userData.getPassword());
        User user = new User(userData, password);
        return userRepository.save(user);
    }
}
