package com.system.training.DTO;

import com.system.training.model.AppUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InstructorRequest {
    private AppUser user;
    private String department;
    private String languageSpoken;
    private String gender;
}
