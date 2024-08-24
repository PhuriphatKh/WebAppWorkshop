package com.example.hello_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hello_spring.entity.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity, Integer> {
    
}
