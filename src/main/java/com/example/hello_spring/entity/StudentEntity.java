package com.example.hello_spring.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "student")
public class StudentEntity {
    
    @Id
    @GeneratedValue
    private Integer studentId;
    private String studentCode;
    private String studentFirstName;
    private String studentLastName;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private FacultyEntity faculty;

    @OneToMany(mappedBy = "student")
    private List<EnrollEntity> enrolls;
}
