package com.system.training.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.system.training.model.AppRole;
import com.system.training.service.AppRoleService;


@RequestMapping("/api/role")
@RestController
@RequiredArgsConstructor
public class RoleController {
	private final AppRoleService appRoleService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/addRole")
	public ResponseEntity<?> addNewRole(@RequestBody AppRole role) {
		return ResponseEntity.ok().body(appRoleService.addNewRole(role));
	}
}
