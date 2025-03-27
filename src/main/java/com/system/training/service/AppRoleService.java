package com.system.training.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.training.model.AppRole;
import com.system.training.repository.AppRoleRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AppRoleService {
	private final AppRoleRepository appRoleRepository;
	
	/*
	 * addNewRole - A method that create new Role
	 */
    @Transactional
    public AppRole addNewRole(AppRole appRole) {
        return appRoleRepository.save(appRole);
    }
    
    public List<AppRole> getAllRoles() {
        return appRoleRepository.findAll();
    }

}
