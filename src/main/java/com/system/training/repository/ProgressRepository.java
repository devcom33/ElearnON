package com.system.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.system.training.model.Progress;


@Repository
public interface ProgressRepository extends JpaRepository<Progress, Long>{

	Progress findByStudentAndCourse(Long student_id, Long course_id);

}
