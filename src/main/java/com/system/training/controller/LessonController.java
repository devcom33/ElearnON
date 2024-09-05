package com.system.training.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.system.training.DTO.LessonRequest;
import com.system.training.exception.CourseNotFoundException;
import com.system.training.model.Course;
import com.system.training.model.Lesson;
import com.system.training.service.CourseService;
import com.system.training.service.LessonService;

import jakarta.validation.Valid;

@RequestMapping("/api/lesson")
@RestController
public class LessonController {

	private final LessonService lessonService;
	private final CourseService courseService;
	
	public LessonController(LessonService lessonService, CourseService courseService)
	{
		this.lessonService = lessonService;
		this.courseService = courseService;
	}
	
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
	public List<Lesson> getAllLessons(){
		return lessonService.getLessons();
	}
	
}
