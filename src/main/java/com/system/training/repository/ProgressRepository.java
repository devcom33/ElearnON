package com.system.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.system.training.model.Course;
import com.system.training.model.Progress;
import com.system.training.model.Student;


@Repository
public interface ProgressRepository extends JpaRepository<Progress, Long>{

    Progress findByStudentAndCourse(Student student, Course course);
}
