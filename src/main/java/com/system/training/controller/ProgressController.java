package com.system.training.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.system.training.model.Progress;
import com.system.training.service.ProgressService;


@RestController
@RequestMapping("/api/progress")
@RequiredArgsConstructor
public class ProgressController {
	private final ProgressService progressService;
	
	@GetMapping
	public ResponseEntity<List<Progress>> getProgress(){
		List<Progress> progress = progressService.getAllProgress();
		
		if (progress.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(progress);
	}
	
	
}
