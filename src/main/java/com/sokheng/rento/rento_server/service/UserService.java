package com.sokheng.rento.rento_server.service;

import com.sokheng.rento.rento_server.entity.UserEntity;
import com.sokheng.rento.rento_server.repository.UserRepository;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Get all users
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    // Create a new user
    public UserEntity createUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    // Save or update a user
    public UserEntity save(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    // Update an existing user
    public UserEntity updateUser(String id, UserEntity userDetails) {
        // Find the existing user or throw an exception
        UserEntity existingUser = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        
        // Validate input
        if (userDetails == null) {
            throw new IllegalArgumentException("User details cannot be null");
        }

        // Selectively update fields
        if (userDetails.getEmail() != null && !userDetails.getEmail().isEmpty()) {
            existingUser.setEmail(userDetails.getEmail());
        }

        // Only update password if a new password is provided
        // In a real-world scenario, you'd want to add password hashing here
        if (userDetails.getPassword() != null && !userDetails.getPassword().isEmpty()) {
            existingUser.setPassword(userDetails.getPassword());
        }

        // Add more field updates as needed, with null checks
        
        // Save and return the updated user
        return userRepository.save(existingUser);
    }

    // Delete a user by ID
    public void deleteUser(String id) {
        UserEntity existingUser = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        
        userRepository.delete(existingUser);
    }

    // Find user by email and password (for login)
    public UserEntity findByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    // Find user by auth token
    public UserEntity findUserByAuthToken(String authToken) {
        return userRepository.findByAuthToken(authToken);
    }

    // Find user by user ID
    public UserEntity findByUserId(String userId) {
        return userRepository.findByUserId(userId);
    }

    // Check if email is already registered
    public boolean isEmailAlreadyRegistered(String email) {
        return userRepository.existsByEmail(email);
    }
}
