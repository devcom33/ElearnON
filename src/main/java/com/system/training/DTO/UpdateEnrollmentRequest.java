package com.system.training.DTO;

import com.system.training.enums.EnrollmentState;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class UpdateEnrollmentRequest {
	@NotNull
    private Long enrollmentId;
	@NotNull
    private EnrollmentState newState;
}
