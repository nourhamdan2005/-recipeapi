package com.example.recipeapi.controller;
import com.example.recipeapi.model.User;
import com.example.recipeapi.UserRepository;
import com.example.recipeapi.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/auth")
public class AuthController {
	 @Autowired
	    private UserRepository userRepository;

	    @Autowired
	    private PasswordEncoder passwordEncoder;

	    @Autowired
	    private AuthenticationManager authenticationManager;

	    @Autowired
	    private JwtUtil jwtUtil;

	    @PostMapping("/register")
	    public String register(@RequestBody User user) {
	        user.setPassword(passwordEncoder.encode(user.getPassword()));
	        if (user.getRoles() == null) {
	            user.setRoles(new ArrayList<>());
	            user.getRoles().add("USER");
	        }
	        userRepository.save(user);
	        return "User registered successfully!";
	    }

	    @PostMapping("/login")
	    public String login(@RequestBody User user) {
	        try {
	            authenticationManager.authenticate(
	                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
	            );
	        } catch (AuthenticationException e) {
	            throw new RuntimeException("Invalid username or password");
	        }
	        return jwtUtil.generateToken(user.getUsername());
	    }
}
