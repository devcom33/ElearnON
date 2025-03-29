package com.system.training.mappers;


import com.system.training.DTO.RegisterRequest;
import com.system.training.model.AppUser;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthMapper {
    AppUser toEntity(RegisterRequest registerRequest);
}
