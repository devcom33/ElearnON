package com.system.training.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.system.training.exception.StudentNotFoundException;
import com.system.training.model.Student;
import com.system.training.repository.StudentRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    
    private final PasswordEncoder passwordEncoder;

    StudentService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    
    
    public Student registerStudent(Student student) {
        if (student.getPassword() != null && !student.getPassword().isEmpty()) {
            String encodedPassword = passwordEncoder.encode(student.getPassword());
            student.setPassword(encodedPassword);
        }
        System.out.println("Student to be saved: " + student);
        Student savedStudent = studentRepository.save(student);
        System.out.println("Saved student: " + savedStudent);
        return savedStudent;
    }
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) throws StudentNotFoundException{
        return studentRepository.findById(id).
        		orElseThrow(() -> 
        		new StudentNotFoundException("Student with ID " + id + " not found"));
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}

