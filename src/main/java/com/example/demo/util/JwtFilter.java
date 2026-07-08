package com.example.demo.util;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter{

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public JwtFilter(JwtService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
         String authHeader = request.getHeader("Authorization");

        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);
        //eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtYW5vakBnbWFpbC5jb20iLCJ1c2VybmFtZSI6Im1hbm9qIiwicm9sZSI6IlRSQUlORVIiLCJpYXQiOjE3ODI3MjM1NTgsImV4cCI6MTc4MzU4NzU1OH0.RkNecartDj0dWmHs-11PCmSMg87bx5IKHRM-eESVYZI
        String username = jwtService.extractUsername(token);

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null ){
            UserDetails user = userDetailsService.loadUserByUsername(username);
            if(jwtService.validateToken(token, user)){
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null , user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }

        }

        filterChain.doFilter(request, response);

    }

   
    
}
