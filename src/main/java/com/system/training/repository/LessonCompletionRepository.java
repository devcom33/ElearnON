package com.system.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.system.training.model.LessonCompletion;


@Repository
public interface LessonCompletionRepository extends JpaRepository<LessonCompletion, Long>{
	
	@Query("SELECT COUNT(lc) FROM LessonCompletion lc where lc.lesson.course.id := courseId AND lc.student.id := studentId AND completed := True")
	long countbyCompleted(@Param("courseId") long courseId, @Param("studentId") long studentId);
}
