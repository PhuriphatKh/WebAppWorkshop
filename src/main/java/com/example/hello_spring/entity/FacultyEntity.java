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
@Table(name = "faculty")
public class FacultyEntity {
    
    @Id
    @GeneratedValue
    private Integer facultyId;
    private String facultyName;

    @OneToMany(mappedBy = "faculty")
    private List<StudentEntity> students;
}
