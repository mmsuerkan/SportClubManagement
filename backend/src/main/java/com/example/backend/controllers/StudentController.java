package com.example.backend.controllers;

import com.example.backend.common.ApiResponse;
import com.example.backend.dto.StudentDto;
import com.example.backend.model.Club;
import com.example.backend.model.Coordinator;
import com.example.backend.model.Student;
import com.example.backend.service.CoordinatorService;
import com.example.backend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/")
    public ResponseEntity<List<Student>> getStudents() {

        List<Student> students = studentService.listAllStudent();
        return new ResponseEntity<List<Student>>(students, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addStudent(@RequestBody StudentDto studentDto) {

        studentService.addStudent(studentDto);
        return new ResponseEntity<>(new ApiResponse(true, "Student has been added"), HttpStatus.CREATED);
    }

    @PutMapping("/update/{studentId}")
    public ResponseEntity<ApiResponse> updateStudent(@PathVariable("studentId") Long studentId, @RequestBody StudentDto studentDto) {

        studentService.updateStudent(studentId, studentDto);
        return new ResponseEntity<>(new ApiResponse(true, "Student has been updated"), HttpStatus.OK);

    }

    @DeleteMapping("/delete/{studentId}")
    public ResponseEntity<ApiResponse> deleteStudentById(@PathVariable("studentId") Long studentId)  {

        try {
            studentService.deleteStudentById(studentId);
            return new ResponseEntity<>(new ApiResponse(true, "Student has been deleted"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, "Student not found"), HttpStatus.NO_CONTENT);
        }
    }
}
