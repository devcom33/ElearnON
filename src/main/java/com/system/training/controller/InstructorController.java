package com.system.training.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.system.training.model.AppUser;
import com.system.training.model.Instructor;
import com.system.training.service.InstructorService;
import com.system.training.service.UserService;



@RestController
@RequestMapping("/api/instructor")
public class InstructorController {
	
	@Autowired
	private InstructorService instructorService;
	@Autowired
	private UserService userService;
	
	
    @GetMapping("/instructors")
    public List<Instructor> getAllInstructors() {
        return instructorService.getAllInstructors();
    }
    
    @GetMapping("/{id}")
    public Instructor getInstructorById(@PathVariable Long id) {
        return instructorService.getInstructorById(id);
    }
    @GetMapping("/user/{username}")
    public AppUser getInstructorByUsername(@PathVariable String username) {
        return userService.findUserByUsername(username);
    }
	
	
	@PostMapping("/register")
	public ResponseEntity<Instructor> createInstructor(@RequestBody Instructor instructor)
	{
		Instructor savedInstructor = instructorService.createInstructor(instructor);
		return ResponseEntity.ok(savedInstructor);
	}
	
	
	@DeleteMapping("/{id}")
	public void deleteInstuctor(@PathVariable Long id) {
		instructorService.deleteInstructor(id);
	}
}
