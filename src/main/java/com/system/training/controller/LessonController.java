package com.system.training.controller;

import java.net.URI;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.system.training.DTO.LessonRequest;
import com.system.training.exception.CourseNotFoundException;
import com.system.training.exception.LessonNotFoundException;
import com.system.training.model.Course;
import com.system.training.model.Lesson;
import com.system.training.service.CourseService;
import com.system.training.service.LessonService;

import jakarta.validation.Valid;

@RequestMapping("/api/lesson")
@RestController
@RequiredArgsConstructor
public class LessonController {
	private final LessonService lessonService;
	private final CourseService courseService;

	
	@PreAuthorize("hasRole('INSTRUCTOR')")
	@PostMapping
	public ResponseEntity<Lesson> createLesson(@Valid @RequestBody LessonRequest lesson) throws CourseNotFoundException{
		
		Course course = courseService.getCourseById(lesson.getCourse_id());
		
		Lesson savedLesson = new Lesson(course, lesson.getTitle(), lesson.getLessonNumber(), lesson.getContentSummary());

		
		lessonService.saveLesson(savedLesson);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedLesson.getId())
				.toUri();
		
		return ResponseEntity.created(location).body(savedLesson);
	}
	@GetMapping
	public ResponseEntity<List<Lesson>> getAllLessons(){
		List<Lesson> lessons = lessonService.getLessons();
		
		if (lessons.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(lessons);
	}
	@GetMapping("/{id}")
	public ResponseEntity<Lesson> getLessonById(@PathVariable Long id) throws LessonNotFoundException{
		Lesson lesson = lessonService.getLessonById(id);

		if (lesson == null){
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lesson);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteLesson(@PathVariable Long id) {
	    Lesson existingLesson;
		try {
			existingLesson = lessonService.getLessonById(id);
		    if (existingLesson == null) {
		        return ResponseEntity.notFound().build();
		    }
		    lessonService.deleteLessonById(id);

		} catch (LessonNotFoundException e) {
			e.printStackTrace();
		}
	    return ResponseEntity.noContent().build();
	}
}
