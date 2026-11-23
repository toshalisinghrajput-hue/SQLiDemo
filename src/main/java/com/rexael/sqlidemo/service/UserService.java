package com.rexael.sqlidemo.service;

import com.rexael.sqlidemo.model.User;
import com.rexael.sqlidemo.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(User user) {

        // Store the password directly for this
        // intentionally vulnerable SQL injection demo.
        userRepository.save(user);
    }

    public User loginUser(String email, String password) {

        try {
            return userRepository.findByEmailAndPassword(email, password);
        } catch (Exception e) {
            return null;
        }
    }

    public User vulnerableLogin(String email, String password) {

        try {
            return userRepository.findUserVulnerable(email, password);
        } catch (Exception e) {
            return null;
        }
    }
}
