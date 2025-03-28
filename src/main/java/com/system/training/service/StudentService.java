package com.system.training.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.system.training.exception.StudentNotFoundException;
import com.system.training.model.Student;
import com.system.training.repository.StudentRepository;

import jakarta.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class StudentService {
	private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;


    public Student registerStudent(Student student) {
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
    public Student getStudentByUserId(Long id) throws StudentNotFoundException{
        return studentRepository.findByUserId(id).
        		orElseThrow(() -> 
        		new StudentNotFoundException("Student with ID " + id + " not found"));
    }
    

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}

