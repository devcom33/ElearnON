package com.system.training.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.system.training.model.Progress;
import com.system.training.repository.ProgressRepository;


@Service
public class ProgressService {
	
	public final ProgressRepository progressRepository;
	public LessonService lessonSevice;
	
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
	
	
}
