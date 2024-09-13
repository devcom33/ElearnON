package com.system.training.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.system.training.model.AppRole;
import com.system.training.service.AppRoleService;


@RequestMapping("/api/role")
@RestController
public class RoleController {
	@Autowired
	public AppRoleService appRoleService;
	
	@PostMapping("/addRole")
	public ResponseEntity<?> addNewRole(@RequestBody AppRole role) {
		return ResponseEntity.ok().body(appRoleService.addNewRole(role));
	}
}
