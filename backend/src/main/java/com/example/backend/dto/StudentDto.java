package com.example.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDto {

    private Long id;
    private String name;
    private String surname;
    private Integer age;
    private Double height;
    private Double weight;
    private Boolean isActive;
    private Long groupId;
}
