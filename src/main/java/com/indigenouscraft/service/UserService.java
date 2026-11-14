package com.indigenouscraft.service;

import com.indigenouscraft.entity.User;
import com.indigenouscraft.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.dao.DataIntegrityViolationException;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User registerUser(User user) {

        String normalizedEmail = user.getEmail().trim().toLowerCase();
        user.setEmail(normalizedEmail);

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException(
                    "This email address is already registered.");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        try {
            return userRepository.saveAndFlush(user);
        } catch (DataIntegrityViolationException exception) {
            throw new IllegalArgumentException("This email address is already registered.",
                    exception);
        }
    }
}