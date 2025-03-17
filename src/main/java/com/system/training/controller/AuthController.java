package com.system.training.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.system.training.config.util.JwtUtil;
import com.system.training.model.AppUser;
import com.system.training.service.UserService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;


@RequestMapping("/api/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
	public final UserService userService;
	private final UserDetailsService userDetailsService;
	private final JwtUtil jwtUtil;
	
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody @Valid AppUser user){
        AppUser savedUser = userService.createUser(user);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(savedUser.getUsername());
        final String jwt = jwtUtil.generateToken(savedUser.getUsername(), savedUser.getRoles());
        
        return new ResponseEntity<>(new AuthenticationResponse(jwt), HttpStatus.CREATED);
	}
	@CrossOrigin(origins = "http://localhost:5173")
	@PostMapping("/login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception
	{
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        AppUser appUser = userService.findUserByUsername(userDetails.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername(), appUser.getRoles());

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}

	
	
    @Data
    public static class AuthenticationRequest {
        private String username;
        private String password;
    }

    @Data
    @AllArgsConstructor
    public static class AuthenticationResponse {
        private final String jwt;
    }
}
