package com.system.training.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRawValue;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @JsonProperty("first_name")
    private String firstname;

    @Column(nullable = false)
    @JsonProperty("last_name")
    private String lastname;

    @Column(nullable = false, unique = true)
    @JsonProperty("email")
    private String email;
    
    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonRawValue
    private String password;
    
    @Column(nullable = false)
    @JsonProperty("department")
    private String department;

    @Column(nullable = false)
    @JsonProperty("language_spoken")
    private String languageSpoken;

    @Column(nullable = false)
    @JsonProperty("gender")
    private String gender;
    
}
