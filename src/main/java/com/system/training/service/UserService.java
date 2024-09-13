package com.system.training.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.system.training.model.AppRole;
import com.system.training.model.AppUser;
import com.system.training.repository.AppRoleRepository;
import com.system.training.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{
	@Autowired
	public UserRepository userRepository;
	@Autowired
	public AppRoleRepository appRoleRepository;
    @Autowired
    public PasswordEncoder passwordEncoder;	
	
	public AppUser createUser(AppUser appUser) {
		appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
		return userRepository.save(appUser);
	}
	
	public AppUser findUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	public void assignRoleToUser(String username, AppRole appRole) {
		AppUser user = findUserByUsername(username);
		AppRole role = appRoleRepository.findByName(appRole);
		user.getRoles().add(role);
		userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByUsername(username);
	}
}
