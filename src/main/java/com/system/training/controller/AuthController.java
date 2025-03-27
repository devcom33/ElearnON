package com.system.training.controller;

import com.system.training.DTO.AuthResponse;
import com.system.training.DTO.AuthenticationRequest;
import com.system.training.config.CustomUserDetailsService;
import com.system.training.service.UserService;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.system.training.config.util.JwtUtil;
import com.system.training.model.AppUser;


import jakarta.validation.Valid;


@RequestMapping("/api/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
	public final UserService userService;
	private final CustomUserDetailsService customUserDetailsService;
	private final JwtUtil jwtUtil;
	
	
	@PostMapping("/register")
	public ResponseEntity<AuthResponse> register(@RequestBody @Valid AppUser user){
        AppUser savedUser = userService.createUser(user);
        final UserDetails userDetails = customUserDetailsService.loadUserByUsername(savedUser.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername(), savedUser.getRoles());

        AuthResponse authResponse = AuthResponse.builder().jwt(jwt).build();

        return ResponseEntity.ok(authResponse);
	}
	@CrossOrigin(origins = "http://localhost:5173")
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception
	{
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}
        final UserDetails userDetails = customUserDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        AppUser appUser = userService.findUserByUsername(userDetails.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername(), appUser.getRoles());

		AuthResponse authResponse = AuthResponse.builder().jwt(jwt).build();
        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
	}


}
