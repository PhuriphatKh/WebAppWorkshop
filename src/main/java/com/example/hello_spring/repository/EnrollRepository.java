package com.example.hello_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hello_spring.entity.EnrollEntity;

public interface EnrollRepository extends JpaRepository<EnrollEntity, Integer> {
    
}
