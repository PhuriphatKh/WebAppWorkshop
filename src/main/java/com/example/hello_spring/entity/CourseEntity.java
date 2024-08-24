package com.example.hello_spring.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "course")
public class CourseEntity {
    
    @Id
    @GeneratedValue
    private Integer courseId;
    private String courseName;
    private String courseDescription;

    @OneToMany(mappedBy = "course")
    private List<EnrollEntity> enrolls;
}
