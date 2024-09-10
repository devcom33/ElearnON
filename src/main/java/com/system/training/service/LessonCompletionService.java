package com.system.training.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.system.training.exception.LessonNotFoundException;
import com.system.training.exception.StudentNotFoundException;
import com.system.training.model.Lesson;
import com.system.training.model.LessonCompletion;
import com.system.training.model.Student;
import com.system.training.repository.LessonCompletionRepository;


@Service
public class LessonCompletionService {
	private static final Logger logger = LoggerFactory.getLogger(LessonCompletionService.class);
	@Autowired
	public LessonCompletionRepository lcRepository;
	@Autowired
	public LessonService lessonService;
	@Autowired
	public StudentService studentService;

	public Long countCompletedLessons(Long courseId, Long studentId){
		return lcRepository.countbyCompleted(courseId, studentId);
	}
	
	/*
	 * markLessonAsComplete - A function To mark the lesson as completed
	 * Limits:
	 * this method need to verify is enrolled by student or not
	 */
	@Transactional
	public void markLessonAsComplete(Long studentId, Long lessonId) throws LessonNotFoundException, StudentNotFoundException {
		LessonCompletion lessonCompletion = new LessonCompletion();
		
		Lesson lesson = lessonService.getLessonById(lessonId);
		Student student = studentService.getStudentById(studentId);
		
		lessonCompletion.setLesson(lesson);
		lessonCompletion.setStudent(student);
		lessonCompletion.setCompleted(true);
		logger.info("[+] Completion Lesson {}  Student {}",lesson, student);
		lcRepository.save(lessonCompletion);
	}


}
