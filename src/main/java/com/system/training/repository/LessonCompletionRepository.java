package com.system.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.system.training.model.LessonCompletion;


@Repository
public interface LessonCompletionRepository extends JpaRepository<LessonCompletion, Long>{
	
    @Query("SELECT COUNT(lc) FROM LessonCompletion lc WHERE lc.lesson.course.id = :courseId AND lc.student.id = :studentId AND lc.completed = true")
    Long countbyCompleted(@Param("courseId") Long courseId, @Param("studentId") Long studentId);
}
