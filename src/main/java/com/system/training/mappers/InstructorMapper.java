package com.system.training.mappers;

import com.system.training.DTO.InstructorRequest;
import com.system.training.model.Instructor;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface InstructorMapper {
    Instructor toEntity(InstructorRequest instructorRequest);
}
