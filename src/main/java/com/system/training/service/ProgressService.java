package com.system.training.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.system.training.model.Progress;
import com.system.training.repository.ProgressRepository;
/*
 * (1, 0, 2, null, 9)
 * insert into progress (completion_progress,course_id,lesson_id,student_id) 
 * 
 */

@Service
public class ProgressService {
	
	public final ProgressRepository progressRepository;
	
	public ProgressService(ProgressRepository progressRepository) {
		this.progressRepository = progressRepository;
	}
	
	public Progress saveProgress(Progress progress) {
		return progressRepository.save(progress);
	}
	
	public List<Progress> getAllProgress(){
		return progressRepository.findAll();
	}
	
	
}
