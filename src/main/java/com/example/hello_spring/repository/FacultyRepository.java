package com.example.hello_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hello_spring.entity.FacultyEntity;

public interface FacultyRepository extends JpaRepository<FacultyEntity, Integer>{
    
}
