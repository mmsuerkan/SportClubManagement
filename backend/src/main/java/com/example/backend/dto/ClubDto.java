package com.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClubDto {

    private Long id;
    private String name;
    private String branchName;


}
