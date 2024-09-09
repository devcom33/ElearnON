package com.system.training.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.training.exception.CourseNotFoundException;
import com.system.training.exception.StudentNotFoundException;
import com.system.training.model.Course;
import com.system.training.model.Lesson;
import com.system.training.model.Progress;
import com.system.training.model.Student;
import com.system.training.repository.LessonCompletionRepository;
import com.system.training.repository.ProgressRepository;


@Service
public class ProgressService {
	@Autowired
	public ProgressRepository progressRepository;
	@Autowired
	public LessonService lessonSevice;
	@Autowired
	public LessonCompletionService lessonCompletionService;
	@Autowired
	public StudentService studentService;
	@Autowired
	public CourseService courseService;
	

	public Progress saveProgress(Progress progress) {
		return progressRepository.save(progress);
	}
	
	public List<Progress> getAllProgress(){
		return progressRepository.findAll();
	}
	
	public Progress getProgressById(Long id) {
		return progressRepository.findById(id).orElse(null);
	}

	/*
	 * calculateProgress - a method for calculate progress by nums of Lessons & completed lessons
	 * Return: progress of a course By student
	 */
	
	public Double calculateProgress(Long courseId, Long studentId) throws CourseNotFoundException {
	
	    Long totalLessons = lessonSevice.countByCourse(courseId);
	    Long completedLessons = lessonCompletionService.countCompletedLessons(courseId, studentId);
	    
	    return (double) completedLessons / totalLessons * 100;
	}
	public void updateProgressAfterLessonCompletion(Long studentId, Long courseId) throws CourseNotFoundException, StudentNotFoundException {
	    Double progress = calculateProgress(courseId, studentId);
		Course course = courseService.getCourseById(courseId);
		Student student = studentService.getStudentById(studentId);
	    Progress progressRecord = progressRepository.findByStudentAndCourse(student, course);
	    progressRecord.setCompletionProgress(progress);
	    progressRepository.save(progressRecord);
	}


}
