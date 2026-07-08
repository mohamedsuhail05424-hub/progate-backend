package com.example.demo.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.SystemUser;
import com.example.demo.repository.SystemUserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final SystemUserRepository repo;

    public CustomUserDetailsService(SystemUserRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException 
    {
        SystemUser user = repo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found !"));
                
        return User.builder()
               .username(user.getEmail())
               .password(user.getPassword())
               .authorities(user.getRole().name())
               .build();
    }

}
