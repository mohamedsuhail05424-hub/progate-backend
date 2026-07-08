package com.example.demo.controller;

import com.example.demo.entity.SystemUser;
import com.example.demo.entity.SystemUser.Status;
import com.example.demo.repository.SystemUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController 
{
    @Autowired
    private SystemUserRepository userRepository;


    @GetMapping("/users")
    public List<SystemUser> getAllUsers()
    {
        return userRepository.findAll();
    }

    @PutMapping("/users/{id}/status")
    public SystemUser updateUserStatus(@PathVariable Long id, @RequestParam(name = "status") Status status)
    {
        SystemUser user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        user.setStatus(status);

        return userRepository.save(user);
    }
}
