package com.system.training.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.system.training.model.Instructor;
import com.system.training.repository.InstructorRepository;

@Service
public class InstructorService {
    @Autowired
    private InstructorRepository instructorRepository;
    
    private final PasswordEncoder passwordEncoder;
    
    
    InstructorService(PasswordEncoder passwordEncoder){
    	this.passwordEncoder = passwordEncoder;
    }

    public Instructor createInstructor(Instructor instructor) {
        if (instructor.getPassword() != null && !instructor.getPassword().isEmpty()) {
            String encodedPassword = passwordEncoder.encode(instructor.getPassword());
            instructor.setPassword(encodedPassword);
        }
        System.out.println("Instructor to be saved: " + instructor);
        Instructor savedInstructor = instructorRepository.save(instructor);
        System.out.println("Saved instructor: " + savedInstructor);
        return savedInstructor;
    }
    
    public Instructor getInstructorById(Long id) {
    	return instructorRepository.findById(id).orElse(null);
    }
    public List<Instructor> getAllInstructors() {
        return instructorRepository.findAll();
    }


    public void deleteInstructor(Long id) {
        instructorRepository.deleteById(id);
    }
}
