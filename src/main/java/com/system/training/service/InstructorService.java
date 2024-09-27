package com.system.training.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.system.training.model.Instructor;
import com.system.training.repository.InstructorRepository;

@Service
public class InstructorService {

    private InstructorRepository instructorRepository;
    
    
    
    InstructorService(InstructorRepository instructorRepository){
    	this.instructorRepository = instructorRepository;
    }
    
    public Instructor createInstructor(Instructor instructor) {
        System.out.println("Instructor to be saved: " + instructor);
        Instructor savedInstructor = instructorRepository.save(instructor);
        System.out.println("Saved instructor: " + savedInstructor);
        return savedInstructor;
    }
    
    public Instructor getInstructorById(Long id) {
        return instructorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Instructor not found with id: " + id));
    }

    public Instructor getInstructorByAppUserId(Long appUserId) {
        return instructorRepository.findByUserId(appUserId)
               .orElseThrow(() -> new RuntimeException("Instructor not found with AppUser ID: " + appUserId));
    }

    public List<Instructor> getAllInstructors() {
        return instructorRepository.findAll();
    }


    public void deleteInstructor(Long id) {
        instructorRepository.deleteById(id);
    }
}
