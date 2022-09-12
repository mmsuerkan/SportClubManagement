package com.example.backend.service;

import com.example.backend.dto.StudentDto;
import com.example.backend.model.Coordinator;
import com.example.backend.model.Group;
import com.example.backend.model.Student;
import com.example.backend.repository.CoordinatorRepository;
import com.example.backend.repository.GroupRepository;
import com.example.backend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private GroupRepository groupRepository;

    public List<StudentDto> listAllStudent(){
        List<StudentDto> studentDtos = new ArrayList<>();

        List<Student> all = studentRepository.findAll();

        for (Student student: all) {
            studentDtos.add(new StudentDto(student.getName(),student.getSurname(),student.getAge(),student.getHeight(),student.getWeight(),student.getIsActive(),student.getGroup().getId()));
        }
        return studentDtos;

    }

    public Student getStudentById(Long id){
        return studentRepository.findById(id).get();
    }

    public void deleteStudentById(Long id){
        studentRepository.deleteById(id);
    }

    public void addStudent(StudentDto studentDto){

        Student student = new Student();

        student.setName(studentDto.getName());
        student.setSurname(studentDto.getSurname());
        student.setAge(studentDto.getAge());
        student.setHeight(studentDto.getHeight());
        student.setWeight(studentDto.getWeight());
        student.setIsActive(studentDto.getIsActive());
        student.setGroup(groupRepository.findById(studentDto.getGroupId()).get());
        studentRepository.save(student);
    }

    public void updateStudent(Long updatedStudentId,StudentDto studentDto){

        Student updateStudent = new Student();

        updateStudent.setId(updatedStudentId);
        updateStudent.setName(studentDto.getName());
        updateStudent.setSurname(studentDto.getSurname());
        updateStudent.setAge(studentDto.getAge());
        updateStudent.setWeight(studentDto.getWeight());
        updateStudent.setHeight(studentDto.getHeight());
        updateStudent.setIsActive(studentDto.getIsActive());
        updateStudent.setGroup(groupRepository.findById(studentDto.getGroupId()).get());
        studentRepository.save(updateStudent);

    }
}
