package com.system.training.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.system.training.DTO.CompletionRequest;
import com.system.training.exception.LessonNotFoundException;
import com.system.training.exception.StudentNotFoundException;
import com.system.training.service.LessonCompletionService;


@RequestMapping("/api/completion")
@RestController
@RequiredArgsConstructor
public class LessonCompletionController {
	private final LessonCompletionService lessonCompletionService;
	
	@PreAuthorize("hasRole('STUDENT')")
	@PostMapping("/complete")
	public ResponseEntity<?> markLessonAsComplete(@RequestBody CompletionRequest completion) throws LessonNotFoundException, StudentNotFoundException {
		lessonCompletionService.markLessonAsComplete(completion.getStudentId(), completion.getLessonId());
		
		return ResponseEntity.ok().body(null);
	}
}
