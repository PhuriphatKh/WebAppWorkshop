package com.example.hello_spring.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.hello_spring.entity.CourseEntity;
import com.example.hello_spring.entity.EnrollEntity;
import com.example.hello_spring.entity.StudentEntity;
import com.example.hello_spring.service.CourseService;
import com.example.hello_spring.service.EnrollService;
import com.example.hello_spring.service.StudentService;

@Controller
@RequestMapping("/enroll")
public class EnrollController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private EnrollService enrollService;

    @GetMapping({"", "/"})
    public String getAll(ModelMap model) {
        System.out.println("------ EnrollController getAll() ------");
        
        // List<EnrollEntity> enrolls = enrollService.getEnrollAll();
        // System.out.println("------ EnrollController getAll() Result ------");
        // System.out.println("Size: " + enrolls.size());

        List<CourseEntity> courses = courseService.getCourseAll();
        model.addAttribute("courses", courses);

        List<StudentEntity> students = studentService.getStudentAll();
        model.addAttribute("students", students);

        return "enroll/index";
    }

    @GetMapping("/{student-id}")
    public String getById(
        ModelMap model,
        @PathVariable(name = "student-id") Integer studentId
    ) {
        // System.out.println("------ EnrollController getById() ------");
        // System.out.println("enroll-id: " + enrollId);

        // EnrollEntity entity = enrollService.getEnrollById(enrollId);
        // System.out.println("------ EnrollController getById() Result ------");
        // System.out.println("Course Name: " + entity.getCourse().getCourseName());
        // System.out.println("Student First Name: " + entity.getStudent().getStudentFirstName());
        // System.out.println("Student Last Name: " + entity.getStudent().getStudentLastName());

        StudentEntity entity = studentService.getStudentById(studentId);
        model.addAttribute("student", entity);

        List<EnrollEntity> enrolls = enrollService.getEnrollAll();
        List<EnrollEntity> resultList = enrolls.stream()
            .filter(enroll -> enroll.getStudent().getStudentId() == studentId)
            .collect(Collectors.toList());
        model.addAttribute("enrolls", resultList);

        List<CourseEntity> courses = courseService.getCourseAll();
        model.addAttribute("courses", courses);

        List<StudentEntity> students = studentService.getStudentAll();
        model.addAttribute("students", students);

        return "enroll/index";
    }

    @GetMapping("/delete/{enroll-id}")
    public String getDeleteById(
        ModelMap model,
        @PathVariable(name = "enroll-id") Integer enrollId,
        RedirectAttributes redirectAttributes
    ) {
        // System.out.println("------ EnrollController getDeleteById() ------");
        // System.out.println("enroll-id: " + enrollId);

        // System.out.println("------ EnrollController getDeleteById() Result ------");
        EnrollEntity enrolls = enrollService.getEnrollById(enrollId);
        StudentEntity student = enrolls.getStudent();
        
        enrollService.deleteEnrollById(enrollId);

        redirectAttributes.addAttribute("student-id", student.getStudentId());

        List<CourseEntity> courses = courseService.getCourseAll();
        model.addAttribute("courses", courses);

        List<StudentEntity> students = studentService.getStudentAll();
        model.addAttribute("students", students);

        return "redirect:/enroll/{student-id}";
    }

    @PostMapping("/")
    public String postInsertAndUpdate(
        ModelMap model,
        @RequestParam() Map<String, String> param,
        RedirectAttributes redirectAttributes
    ) {
        // System.out.println("------ EnrollController postInsertAndUpdate() ------");
        // System.out.println("enroll-id: " + param.get("enroll-id"));
        // System.out.println("course-id: " + param.get("course-id"));
        // System.out.println("student-id: " + param.get("student-id"));

        System.out.println("----- EnrollController postInsertAndUpdate() Result Result -----");
        Integer courseId = Integer.parseInt(param.get("course-id"));
        CourseEntity courseEntity = courseService.getCourseById(courseId);
        // System.out.println("Course ID: " + courseEntity.getCourseId());

        Integer studentId = Integer.parseInt(param.get("student-id"));
        StudentEntity studentEntity = studentService.getStudentById(studentId);
        // System.out.println("Student ID: " + studentEntity.getStudentId());

        EnrollEntity entity = new EnrollEntity();
        if(null != param.get("enroll-id")) {
            entity.setEnrollId((Integer.parseInt(param.get("enroll-id"))));
        }
        entity.setCourse(courseEntity);
        entity.setStudent(studentEntity);
        EnrollEntity result = enrollService.saveEnroll(entity);
        System.out.println("Enroll ID: " + result.getEnrollId());
        System.out.println("Course Name: " + result.getCourse().getCourseName());
        System.out.println("Student Code: " + result.getStudent().getStudentCode());
        
        List<CourseEntity> courses = courseService.getCourseAll();
        model.addAttribute("courses", courses);

        List<StudentEntity> students = studentService.getStudentAll();
        model.addAttribute("students", students);

        
        redirectAttributes.addAttribute("student-id", result.getStudent().getStudentId());
        
        return "redirect:/enroll/{student-id}";
    }

}
