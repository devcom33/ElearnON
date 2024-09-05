package com.system.training.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.system.training.model.Lesson;
import com.system.training.repository.LessonRepository;

@Service
public class LessonService {
	private static final Logger logger = LoggerFactory.getLogger(LessonService.class);
	public final LessonRepository lessonRepository;
	
	public LessonService(LessonRepository lessonRepository) {
		this.lessonRepository = lessonRepository;
	}
	
	public Lesson saveLesson(Lesson lesson) {
		logger.info("Saving lesson with title: {}", lesson.getTitle());
		return lessonRepository.save(lesson);
	}
	
	public boolean checkLessonExists(String title) {
		return lessonRepository.existsByTitle(title);
	}
	
	public List<Lesson> getLessons(){
		return lessonRepository.findAll();
	}

}
