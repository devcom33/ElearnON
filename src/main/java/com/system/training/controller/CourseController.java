package com.system.training.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.system.training.model.Course;
import com.system.training.model.Instructor;
import com.system.training.service.CourseService;
import com.system.training.service.InstructorService;



@RestController
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private InstructorService instructorService; // To associate courses with instructors

    // Get all courses
    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

/*
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        Course course = courseService.getCourseById(id);
        if (course != null) {
            return ResponseEntity.ok(course);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
*/
    // Create a new course
    @PostMapping("/createCourse")
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        // Ensure the instructor exists before creating the course
        Instructor instructor = instructorService.getInstructorById(course.getInstructor().getId());
        if (instructor != null) {
            course.setInstructor(instructor);
            Course savedCourse = courseService.createCourse(course);
            return ResponseEntity.ok(savedCourse);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    /*@PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course course) {
        // Check if the course exists
        if (courseService.getCourseById(id) != null) {
            course.setId(id);
            Course updatedCourse = courseService.updateCourse(course);
            return ResponseEntity.ok(updatedCourse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        if (courseService.getCourseById(id) != null) {
            courseService.deleteCourse(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }*/
}
