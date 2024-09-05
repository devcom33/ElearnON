package com.system.training.service;



import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.system.training.exception.EnrollmentNotFoundException;
import com.system.training.exception.InvalidStateTransitionException;
import com.system.training.model.Enrollement;
import com.system.training.model.EnrollmentState;
import com.system.training.repository.EnrollmentRepository;



@Service
public class EnrollmentService {

	private EnrollmentRepository enrollmentRepository;
	
	
	public EnrollmentService(EnrollmentRepository enrollmentRepository) {
		this.enrollmentRepository = enrollmentRepository;
	}

	@Transactional
	public Enrollement createEnrollement(Enrollement enrollment) {

		return enrollmentRepository.save(enrollment);
	}
	
	public List<Enrollement> getAllEnrollments(){
		return enrollmentRepository.findAll();
	}
	
	public boolean isEnrolled(Long studentId, Long courseId) {
		return enrollmentRepository.existsByStudentIdAndCourseId(studentId, courseId);
		//return true;
	}
	@Transactional
	public void updateEnrollementState(Long enrollomentId, EnrollmentState newState) throws EnrollmentNotFoundException, InvalidStateTransitionException{
		
		Enrollement enrollment = enrollmentRepository.findById(enrollomentId)
				.orElseThrow(() -> new EnrollmentNotFoundException("Enrollment not found"));
		
		EnrollmentState currentState = enrollment.getState();
		
		if (isValidStateTransition(currentState, newState)) {
			enrollment.setState(newState);
			enrollmentRepository.save(enrollment);
		}
		else {
			throw new InvalidStateTransitionException("Cannot transition from " + currentState + " to " + newState);
		}
	}
	
	private boolean isValidStateTransition(EnrollmentState currentState, EnrollmentState newState) 
	{
		switch (currentState) 
		{
			case ACTIVE:
				return newState == EnrollmentState.COMPLETED || newState == EnrollmentState.CANCELED;
			case COMPLETED:
				return false;
			
			default:
				return false;
		}
	}

}
