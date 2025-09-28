package com.geekzone.store.services;

import com.geekzone.store.dtos.user.request.UpsetUser;
import com.geekzone.store.dtos.user.response.UserInfo;
import com.geekzone.store.entities.user.User;
import com.geekzone.store.exceptions.ConflictException;
import com.geekzone.store.exceptions.NotFoundException;
import com.geekzone.store.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserInfo> getUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserInfo::new).collect(Collectors.toList());
    }

    public UserInfo createUser(UpsetUser userData) {
        verifyIfEmailExists(userData.getEmail());
        userData.setPassword(passwordEncoder.encode(userData.getPassword()));
        User user = new User(userData);
        userRepository.save(user);
        return new UserInfo(user);
    }

    public UserInfo getUserById(long id) {
        User user = verifyIfUserExists(id);
        return new UserInfo(user);
    }

    public UserInfo updateUser(long id, UpsetUser userData) {
        User user = verifyIfUserExists(id);
        User emailAlreadyExists = userRepository.findByEmail(userData.getEmail());
        if (emailAlreadyExists != null && !(emailAlreadyExists.getId() == id)) {
            throw new ConflictException("A user with this email already exists.");
        }
        user.setPassword(passwordEncoder.encode(userData.getPassword()));
        user.setName(userData.getName());
        user.setBirthdate(userData.getBirthdate());
        user.setRole(userData.getRole());
        user.setEmail(userData.getEmail());
        userRepository.save(user);
        return new UserInfo(user);
    }

    public void deleteUser(long id) {
        User user = verifyIfUserExists(id);
        userRepository.delete(user);
    }

    private User verifyIfUserExists(long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new NotFoundException("User not found.");
        }
        return user.get();
    }

    private void verifyIfEmailExists(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            throw new ConflictException("A user with this email already exists.");
        }
    }
}
