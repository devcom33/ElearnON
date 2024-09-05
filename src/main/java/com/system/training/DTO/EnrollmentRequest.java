package com.system.training.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class EnrollmentRequest {
	
	@NotNull
	private Long studentId;
	@NotNull
	private Long courseId;
}
