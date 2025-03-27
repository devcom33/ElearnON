package com.system.training.DTO;

import com.system.training.model.AppUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateStudentRequest {
    private AppUser user;
    private String major;
    private String gender;
}
