package com.example.backend.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse {
    private  boolean success;
    private  String message;

}
