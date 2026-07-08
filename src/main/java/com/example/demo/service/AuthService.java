package com.example.demo.service;

import com.example.demo.entity.SystemUser;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.SystemUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final SystemUserRepository repo;
    private final PasswordEncoder encoder;

    public AuthService(SystemUserRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    public SystemUser register(SystemUser user) 
    {
        user.setPassword(encoder.encode(user.getPassword()));
        if (user.getRole() == null) {
            user.setRole(SystemUser.Role.ROLE_INVESTOR);
        }
        if (user.getStatus() == null) {
            user.setStatus(SystemUser.Status.ACTIVE);
        }
        return repo.save(user);
    }

    public SystemUser findByEmail(String email) {
        return repo.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found !"));
    }
}
