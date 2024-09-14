package com.system.training.service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.system.training.controller.EnrollmentController;
import com.system.training.enums.RoleName;
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

		//Fetching existing roles from repo
		List<AppRole> rolesFromDb = appRoleRepository.findAll();
		Map<RoleName, AppRole> roleMap = rolesFromDb.stream()
				.collect(Collectors.toMap(AppRole::getName, Function.identity()));
		
		Set<AppRole> roles = appUser.getRoles().stream()
				.filter(role -> roleMap.containsKey(role.getName()))
				.map(role -> roleMap.get(role.getName()))
				.collect(Collectors.toSet());

		appUser.setRoles(roles);
		appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
		return userRepository.save(appUser);
	}
	
	public AppUser findUserByUsername(String username) throws UsernameNotFoundException{
		return userRepository.findByUsername(username);
	}
	
	public void assignRoleToUser(String username, AppRole appRole) {
		AppUser user = findUserByUsername(username);
		AppRole role = appRoleRepository.findByName(appRole.getName());
		user.getRoles().add(role);
		userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser user = userRepository.findByUsername(username);
		
		if (user != null) {
			return user;
		}
		else {
			throw new UsernameNotFoundException("User with Username : "+ username + " not found!");
		}
	}
}
