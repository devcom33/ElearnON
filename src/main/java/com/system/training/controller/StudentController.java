package com.system.training.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.system.training.exception.CourseNotFoundException;
import com.system.training.exception.StudentNotFoundException;
import com.system.training.model.Student;
import com.system.training.service.ProgressService;
import com.system.training.service.StudentService;


@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private ProgressService progressService;
    
    @PreAuthorize("hasRole('INSTRUCTOR')")
    @GetMapping
    public List<Student> getAllStudents() {
    	List<Student> students = studentService.getAllStudents();
        
        return students;
    }
    
    @PreAuthorize("hasRole('INSTRUCTOR')")
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) throws StudentNotFoundException {
        return studentService.getStudentById(id);
    }
   	
    @PostMapping("/addStudent")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student savedStudent = studentService.registerStudent(student);
        return ResponseEntity.ok(savedStudent);
    }

    @PreAuthorize("hasRole('INSTRUCTOR')")
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }
    
    @PreAuthorize("hasRole('INSTRUCTOR')")
    @GetMapping("/{studentId}/courses/{courseId}/progress")
    public ResponseEntity<Double> getCourseProgress(
          @PathVariable Long studentId, @PathVariable Long courseId) throws CourseNotFoundException {
        Double progress = progressService.calculateProgress(courseId, studentId);
        return ResponseEntity.ok(progress);
    }

}

