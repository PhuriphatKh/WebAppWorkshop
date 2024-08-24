package com.example.hello_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hello_spring.entity.CourseEntity;

public interface CourseRepository extends JpaRepository<CourseEntity, Integer> {
    
}
