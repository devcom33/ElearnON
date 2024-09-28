package com.system.training.controller;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.system.training.DTO.EnrollmentRequest;
import com.system.training.DTO.UpdateEnrollmentRequest;
import com.system.training.enums.EnrollmentState;
import com.system.training.exception.CourseNotFoundException;
import com.system.training.exception.EnrollmentAlreadyExistsException;
import com.system.training.exception.EnrollmentNotFoundException;
import com.system.training.exception.InvalidStateTransitionException;
import com.system.training.exception.StudentNotFoundException;
import com.system.training.model.Course;
import com.system.training.model.Enrollement;
import com.system.training.model.Progress;
import com.system.training.model.Student;
import com.system.training.service.CourseService;
import com.system.training.service.EnrollmentService;
import com.system.training.service.LessonService;
import com.system.training.service.ProgressService;
import com.system.training.service.StudentService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/enroll")
public class EnrollmentController {
    private static final Logger logger = LoggerFactory.getLogger(EnrollmentController.class);
	@Autowired
	public EnrollmentService enrollementService;
	
	@Autowired
	public StudentService studentService;
	
	@Autowired
	public CourseService courseService;
	
	@Autowired
	public ProgressService progressService;
	
	@Autowired
	public LessonService lessonService;
	
	
	@PreAuthorize("hasRole('STUDENT')")
	@PostMapping
	public ResponseEntity<Enrollement> enroll(@Valid @RequestBody EnrollmentRequest enrollmentRequest) {
	    try {
	    	//this student id is who comes from user not id of table student
	        Long studentId = enrollmentRequest.getStudentId();
	        Long courseId = enrollmentRequest.getCourseId();

	        Student student = studentService.getStudentByUserId(studentId);
	        Course course = courseService.getCourseById(courseId);
	        

	        if (student == null || course == null) {
	            throw new StudentNotFoundException("Student or Course not found");
	        } else if (enrollementService.isEnrolled(student.getId(), courseId)) {
	            throw new EnrollmentAlreadyExistsException("Enrollment already exists");
	        }

	        Enrollement enroll = new Enrollement();
	        enroll.setCourse(course);
	        enroll.setStudent(student);
	        enroll.setState(EnrollmentState.ACTIVE);

	        Enrollement savedEnroll = enrollementService.createEnrollement(enroll);
	        if (savedEnroll == null) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	        }
	        Progress progress = new Progress();
	        progress.setCompletionProgress(0.0);
	        progress.setCourse(course);
	        progress.setStudent(student);
	        progressService.saveProgress(progress);
	        URI location = ServletUriComponentsBuilder
	                .fromCurrentRequest()
	                .path("/{id}")
	                .buildAndExpand(savedEnroll.getId())
	                .toUri();
	        return ResponseEntity.created(location).body(savedEnroll);
	    } catch (StudentNotFoundException | CourseNotFoundException e) {
	        logger.error("Enrollment error: {}", e.getMessage());
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	    } catch (EnrollmentAlreadyExistsException e) {
	        logger.error("Enrollment error: {}", e.getMessage());
	        return ResponseEntity.status(HttpStatus.CONFLICT).body(null); //409
	    } catch (Exception e) {
	        logger.error("Unexpected error: {}", e.getMessage());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	    }
	}
	
	@PreAuthorize("hasRole('STUDENT')")
	@GetMapping
	public List<Enrollement> getAllEnrollments(){
		List<Enrollement> enrolls = enrollementService.getAllEnrollments();
		return enrolls;
	}
	
	@PreAuthorize("hasRole('STUDENT')")
	@PostMapping("/update-state")
	public ResponseEntity<Void> updateEnrollmentState(@RequestBody UpdateEnrollmentRequest updateRequest){
        try {
        	enrollementService.updateEnrollementState(updateRequest.getEnrollmentId(), updateRequest.getNewState());
            return ResponseEntity.ok().build();
        } catch (EnrollmentNotFoundException e) {
            logger.error("Enrollment not found: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (InvalidStateTransitionException e) {
            logger.error("Invalid state transition: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            logger.error("Unexpected error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
	}
	/*
	@PreAuthorize("hasRole('STUDENT')")
	@GetMapping("/enrollment/status")
	public ResponseEntity<Boolean> checkEnrollmentStatus(@RequestParam Long courseId){
		Long studentId = getCurrentUserId(); 
	    boolean isEnrolled = enrollementService.isEnrolled(studentId, courseId);
	    return ResponseEntity.ok(isEnrolled);
	}*/
}
