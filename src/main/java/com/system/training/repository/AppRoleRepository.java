package com.system.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.system.training.model.AppRole;



@Repository
public interface AppRoleRepository extends JpaRepository<AppRole, Long>{

}
