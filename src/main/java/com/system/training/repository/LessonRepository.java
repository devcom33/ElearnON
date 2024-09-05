package com.system.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.system.training.model.Lesson;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long>{
	public boolean existsByTitle(String title);
}
