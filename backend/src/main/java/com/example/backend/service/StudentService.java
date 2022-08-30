package com.example.backend.service;

import com.example.backend.model.Coordinator;
import com.example.backend.model.Student;
import com.example.backend.repository.CoordinatorRepository;
import com.example.backend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> listAllStudent(){
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id){
        return studentRepository.findById(id).get();
    }

    public void deleteStudentById(Long id){
        studentRepository.deleteById(id);
    }

    public void addStudent(Student newStudent){
        studentRepository.save(newStudent);
    }

    public void updateStudent(Long updatedStudentId,Student updatedStudent){

        Student oldStudent = studentRepository.findById(updatedStudent.getId()).get();
        updatedStudent.setId(oldStudent.getId());
        studentRepository.save(updatedStudent);

    }
}
