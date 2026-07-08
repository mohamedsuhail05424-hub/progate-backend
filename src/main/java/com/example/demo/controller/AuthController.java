package com.example.demo.controller;

import com.example.demo.entity.SystemUser;
import com.example.demo.service.AuthService;
import com.example.demo.service.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController 
{

    private final JwtService jwtService;
    private final AuthService service;
    private final AuthenticationManager authenticationManager;

    public AuthController(AuthService service, AuthenticationManager authenticationManager, JwtService jwtService) 
    {
        this.service = service;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<SystemUser> register(@RequestBody SystemUser user) 
    {
        SystemUser savedUser = service.register(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }
  
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody SystemUser user) 
    {
        try 
        {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
            );

            SystemUser authenticatedUser = service.findByEmail(user.getEmail());

            String token = jwtService.generateToken(authenticatedUser);
            return ResponseEntity.ok(token);

        } 
        catch (AuthenticationException e) 
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }
}
