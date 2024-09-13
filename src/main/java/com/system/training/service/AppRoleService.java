package com.system.training.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.training.model.AppRole;
import com.system.training.repository.AppRoleRepository;

@Service
public class AppRoleService {
	@Autowired
	AppRoleRepository appRoleRepository;
	
	/*
	 * addNewRole - A method that create new Role
	 */
    public AppRole addNewRole(AppRole appRole) {
        return appRoleRepository.save(appRole);
    }
    
    public List<AppRole> getAllRoles() {
        return appRoleRepository.findAll();
    }

}
