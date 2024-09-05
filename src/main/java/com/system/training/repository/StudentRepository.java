package com.system.training.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.system.training.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}

