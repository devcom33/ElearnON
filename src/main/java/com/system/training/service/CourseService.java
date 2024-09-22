package com.system.training.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.training.exception.CourseNotFoundException;
import com.system.training.model.Course;
import com.system.training.repository.CourseRepository;

@Service
public class CourseService {
	
	@Autowired
	public CourseRepository courseRepository;
	
	
	public List<Course> getAllCourses() {
		return courseRepository.findAll();
	}
	
    public Course getCourseById(Long id) throws CourseNotFoundException{
        return courseRepository.findById(id)
        		.orElseThrow(() -> new CourseNotFoundException("Course with ID " + id + " not found"));
    }
	
	public Course createCourse(Course course) {
		try {
			return courseRepository.save(course);
		}
		catch (Exception e) {
			System.out.println("Error : "+ e);
		}
		return null;
	}

}
