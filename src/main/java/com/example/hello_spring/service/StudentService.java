package com.example.hello_spring.service;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hello_spring.entity.StudentEntity;
import com.example.hello_spring.repository.StudentRepository;

@Service
public class StudentService {
    
    @Autowired
    private StudentRepository studentRepository;

    public List<StudentEntity> getStudentAll() {
        return studentRepository.findAll();
    }

    public StudentEntity getStudentById(Integer studentId) {
        Optional<StudentEntity> student = studentRepository.findById(studentId);
        if(student.isPresent()) {
            return student.get();
        }
        return null;
    }

    public StudentEntity saveStudent(StudentEntity studentEntity) {
        StudentEntity student = studentRepository.save(studentEntity);
        return student;
    }

    public void deleteStudentById(Integer studentId) {
        studentRepository.deleteById(studentId);
    }
}
