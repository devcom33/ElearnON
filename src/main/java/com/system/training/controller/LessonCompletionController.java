package com.system.training.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class LessonCompletionController {
    private static final Logger logger = LoggerFactory.getLogger(LessonCompletionController.class);

	@Autowired
	public LessonCompletionService lessonCompletionService;
	
	@PreAuthorize("hasRole('STUDENT')")
	@PostMapping("/complete")
	public ResponseEntity<?> markLessonAsComplete(@RequestBody CompletionRequest completion) throws LessonNotFoundException, StudentNotFoundException {
		logger.info("[+] Completion {} {}",completion.getStudentId(), completion.getLessonId());
		lessonCompletionService.markLessonAsComplete(completion.getStudentId(), completion.getLessonId());
		
		return ResponseEntity.ok().body(null);
	}
	

}
