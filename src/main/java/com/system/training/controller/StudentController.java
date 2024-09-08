package com.system.training.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.system.training.exception.StudentNotFoundException;
import com.system.training.model.Student;
import com.system.training.service.StudentService;


@RestController
@RequestMapping("/api/students")
public class StudentController {
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> getAllStudents() {
    	long startTime = System.currentTimeMillis();
    	List<Student> students = studentService.getAllStudents();
        long endTime = System.currentTimeMillis();
        logger.info("getAllStudents executed in {} ms", (endTime - startTime));
        
        return students;
    }

   @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) throws StudentNotFoundException {
        return studentService.getStudentById(id);
    }

    @PostMapping("/register")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student savedStudent = studentService.registerStudent(student);
        return ResponseEntity.ok(savedStudent);
    }


    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }
}

