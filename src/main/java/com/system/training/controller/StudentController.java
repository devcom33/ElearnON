package com.system.training.controller;


import java.util.List;

import com.system.training.DTO.CreateStudentRequest;
import com.system.training.DTO.StudentDto;
import com.system.training.mappers.StudentMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    private final StudentMapper studentMapper;
    private final ProgressService progressService;
    
    @PreAuthorize("hasRole('INSTRUCTOR')")
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }
    
    @PreAuthorize("hasRole('INSTRUCTOR')")
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) throws StudentNotFoundException {
        return studentService.getStudentById(id);
    }
   	
    @PostMapping("/addStudent")
    public ResponseEntity<StudentDto> createStudent(@Valid @RequestBody CreateStudentRequest student) {
        Student studentEntity = studentMapper.toEntity(student);
        Student savedStudent = studentService.registerStudent(studentEntity);
        return new ResponseEntity<>(studentMapper.toStudentDto(savedStudent), HttpStatus.CREATED);
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

