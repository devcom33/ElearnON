package com.system.training.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.training.model.Course;
import com.system.training.model.Progress;
import com.system.training.repository.LessonCompletionRepository;
import com.system.training.repository.ProgressRepository;


@Service
public class ProgressService {
	
	public final ProgressRepository progressRepository;
	public LessonService lessonSevice;
	public LessonService lessonService;
	public ProgressService progressService;
	public LessonCompletionService lessonCompletionService;
	
	
	
	public ProgressService(ProgressRepository progressRepository, LessonService lessonSevice) {
		this.progressRepository = progressRepository;
		this.lessonSevice = lessonSevice;
	}
	
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
	public void updateProgress(Long progressId, Course course) {
		Long countLessons = lessonSevice.countByCourse(course);
		Long percentByCourse = 100 / countLessons;
	}*/
	
	public Double calculateProgress(Long courseId, Long studentId) {
	    Long totalLessons = lessonSevice.countByCourse(courseId);
	    Long completedLessons = lessonCompletionService.countCompletedLessons(courseId, studentId);
	    
	    return (double) completedLessons / totalLessons * 100;
	}
	public void updateProgressAfterLessonCompletion(Long studentId, Long courseId) {
	    Double progress = calculateProgress(courseId, studentId);
	    Progress progressRecord = progressRepository.findByStudentAndCourse(studentId, courseId);
	    progressRecord.setCompletionProgress(progress);
	    progressRepository.save(progressRecord);
	}


}
