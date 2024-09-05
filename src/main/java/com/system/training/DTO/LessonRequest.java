package com.system.training.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LessonRequest {
	@NotNull
	private Long course_id;
	@Size(min=8)
	private String title;
	@NotNull
	private Integer lessonNumber;
	@Size(min=10)
	private String contentSummary;
}
