package com.system.training.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.training.exception.LessonNotFoundException;
import com.system.training.exception.StudentNotFoundException;
import com.system.training.model.Lesson;
import com.system.training.model.LessonCompletion;
import com.system.training.model.Student;
import com.system.training.repository.LessonCompletionRepository;


@Service
public class LessonCompletionService {
	
	@Autowired
	public LessonCompletionRepository lcRepository;
	public LessonService lessonService;
	public StudentService studentService;

	public Long countCompletedLessons(Long courseId, Long studentId){
		return lcRepository.countbyCompleted(courseId, studentId);
	}
	
	/*
	 * markLessonAsComplete - A function To mark the lesson as completed
	 */
	public void markLessonAsComplete(Long studentId, Long lessonId) throws LessonNotFoundException, StudentNotFoundException {
		LessonCompletion lessonCompletion = new LessonCompletion();
		
		Lesson lesson = lessonService.getLessonById(lessonId);
		Student student = studentService.getStudentById(lessonId);
		
		lessonCompletion.setLesson(lesson);
		lessonCompletion.setStudent(student);
		lessonCompletion.setCompleted(true);
		
		lcRepository.save(lessonCompletion);
	}
}
