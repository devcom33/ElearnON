package com.system.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.system.training.model.Enrollement;


@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollement, Long> {
	
	public boolean existsByStudentIdAndCourseId(Long student_id,  Long courseId);
}
