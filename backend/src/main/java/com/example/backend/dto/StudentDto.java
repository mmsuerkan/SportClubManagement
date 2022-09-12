package com.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StudentDto {

    private String name;
    private String surname;
    private Integer age;
    private Double height;
    private Double weight;
    private Boolean isActive;
    private Long groupId;
}
