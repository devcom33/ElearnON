package com.system.training.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.system.training.model.Instructor;


@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long>{
	/*
	 * we used findByUserId because we wanna fetch the id by username
	 */
    Optional<Instructor> findByUserId(Long userId);
}
