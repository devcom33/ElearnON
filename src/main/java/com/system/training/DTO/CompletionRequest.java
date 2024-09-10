package com.system.training.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CompletionRequest {
	private Long studentId; 
	private Long lessonId;
}
