package com.example.demo.service;

import com.example.demo.entity.SystemUser;
import com.example.demo.repository.SystemUserRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class SystemUserService {

    private final SystemUserRepository repo;

    public SystemUserService(SystemUserRepository repo) {
        this.repo = repo;
    }
    
    public Optional<SystemUser> getUserByEmail(String email) {
        return repo.findByEmail(email);
    }


}