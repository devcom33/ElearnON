package com.system.training.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.system.training.DTO.EnrollmentRequest;
import com.system.training.config.SecurityConfig;
import com.system.training.enums.EnrollmentState;
import com.system.training.model.Course;
import com.system.training.model.Enrollement;
import com.system.training.model.Student;
import com.system.training.service.CourseService;
import com.system.training.service.EnrollmentService;
import com.system.training.service.StudentService;



@Import(SecurityConfig.class)
@WebMvcTest(EnrollmentController.class)
public class EnrollmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @MockBean
    private CourseService courseService;

    @MockBean
    private EnrollmentService enrollementService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void enroll_ShouldReturnCreated_WhenValidRequest() throws Exception {
        // Arrange
        EnrollmentRequest request = new EnrollmentRequest();
        request.setStudentId(1L);
        request.setCourseId(1L);

        Student student = new Student();
        student.setId(1L);
        Course course = new Course();
        course.setId(1L);

        Enrollement enrollement = new Enrollement();
        enrollement.setStudent(student);
        enrollement.setCourse(course);
        enrollement.setState(EnrollmentState.ACTIVE);

        when(studentService.getStudentById(1L)).thenReturn(student);
        when(courseService.getCourseById(1L)).thenReturn(course);
        when(enrollementService.createEnrollement(any(Enrollement.class))).thenReturn(enrollement);

        // Act & Assert
        mockMvc.perform(post("/api/enroll")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.student.id").value(1L))
                .andExpect(jsonPath("$.course.id").value(1L))
                .andExpect(jsonPath("$.state").value("ACTIVE"));
    }



    @Test
    public void enroll_ShouldReturnNotFound_WhenStudentOrCourseNotFound() throws Exception {
        // Arrange
        EnrollmentRequest request = new EnrollmentRequest();
        request.setStudentId(1L);
        request.setCourseId(1L);

        when(studentService.getStudentById(1L)).thenReturn(null);
        when(courseService.getCourseById(1L)).thenReturn(null);

        // Act & Assert
        mockMvc.perform(post("/api/enroll")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound());

        // Verify
        verify(enrollementService, never()).createEnrollement(any(Enrollement.class));
    }
}
