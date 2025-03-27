package com.system.training.mappers;

import com.system.training.DTO.CreateStudentRequest;
import com.system.training.DTO.StudentDto;
import com.system.training.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StudentMapper {
    StudentDto toStudentDto(Student student);
    Student toEntity(CreateStudentRequest createStudentRequest);
}
