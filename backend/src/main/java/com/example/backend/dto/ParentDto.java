package com.example.backend.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class ParentDto {


    private Long id;
    private String name;
    private String surname;
}

