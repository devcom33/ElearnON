package com.system.training.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.training.repository.LessonCompletionRepository;


@Service
public class LessonCompletionService {
	
	@Autowired
	public LessonCompletionRepository lcService;

	public Long countCompletedLessons(Long courseId, Long studentId){
		return lcService.countbyCompleted(courseId, studentId);
	}
}
