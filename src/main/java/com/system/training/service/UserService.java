package com.system.training.service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.system.training.enums.RoleName;
import com.system.training.model.AppRole;
import com.system.training.model.AppUser;
import com.system.training.repository.AppRoleRepository;
import com.system.training.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
	private final UserRepository userRepository;
	private final AppRoleRepository appRoleRepository;
	private final PasswordEncoder passwordEncoder;

	public AppUser createUser(AppUser appUser) {
		log.error("1");
		// Fetching existing roles from repo
		List<AppRole> rolesFromDb = appRoleRepository.findAll();
		log.error("2");
		Map<RoleName, AppRole> roleMap = rolesFromDb.stream()
				.collect(Collectors.toMap(AppRole::getName, Function.identity()));
		log.error("3");
		Set<AppRole> roles = appUser.getRoles().stream()
				.filter(role -> roleMap.containsKey(role.getName()))
				.map(role -> roleMap.get(role.getName()))
				.collect(Collectors.toSet());
		log.error("4");
		appUser.setRoles(roles);
		log.error("5");
		appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
		log.error("6");
		return userRepository.save(appUser);
	}

	public AppUser findUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByUsername(username);
	}

	public void assignRoleToUser(String username, AppRole appRole) {
		AppUser user = findUserByUsername(username);
		AppRole role = appRoleRepository.findByName(appRole.getName());
		user.getRoles().add(role);
		userRepository.save(user);
	}
}