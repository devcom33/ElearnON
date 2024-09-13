package com.system.training.service;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.system.training.controller.EnrollmentController;
import com.system.training.model.AppRole;
import com.system.training.model.AppUser;
import com.system.training.repository.AppRoleRepository;
import com.system.training.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{
	private static final Logger logger = LoggerFactory.getLogger(EnrollmentController.class);
	@Autowired
	public UserRepository userRepository;
	@Autowired
	public AppRoleRepository appRoleRepository;
    @Autowired
    public PasswordEncoder passwordEncoder;	
	
	public AppUser createUser(AppUser appUser) {
		logger.info("AppUser "+ appUser.getRoles());
		Set<AppRole> roles = appUser.getRoles();
		AppRole role = new AppRole();
		Set<AppRole> newRoles = new HashSet<AppRole>();
		
		for (AppRole roleElement: roles) {
			role.setId(roleElement.getId());
			role.setName(roleElement.getName());
			logger.error("complete[-1]");
			if (appRoleRepository.findByName(role.getName()) != null) {
				logger.error("complete[0]");
				newRoles.add(role);
				logger.error("complete[1]");

			}
			
			logger.error("complete[2]");
		}
		logger.info(role.getAuthority());
		appUser.setRoles(newRoles);
		appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
		return userRepository.save(appUser);
	}
	
	public AppUser findUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	/*
	public void assignRoleToUser(String username, AppRole appRole) {
		AppUser user = findUserByUsername(username);
		AppRole role = appRoleRepository.findByName(appRole);
		user.getRoles().add(role);
		userRepository.save(user);
	}*/

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByUsername(username);
	}
}
