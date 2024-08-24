package com.example.hello_spring.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.hello_spring.entity.CourseEntity;
import com.example.hello_spring.service.CourseService;

@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;
    
    @GetMapping({"", "/"})
    public String getAll() {
        System.out.println("------ CourseController getAll() ------");
        
        List<CourseEntity> courses = courseService.getCourseAll();
        System.out.println("----- CourseController getAll() Result -----");
        System.out.println("Size: " + courses.size());
        return "index";
    }

    @GetMapping("/{course-id}")
    public String getById(
        @PathVariable(name = "course-id") Integer courseId
    ) {
        System.out.println("------ CourseController getById() ------");
        System.out.println("course-id: " + courseId);
        
        CourseEntity entity = courseService.getCourseById(courseId);
        System.out.println("----- CourseController getById() Result -----");
        System.out.println("Course Name: " + entity.getCourseName());

        return "index";
    }

    @GetMapping("/delete/{course-id}")
    public String getDeleteById(
        @PathVariable(name = "course-id") Integer courseId
    ) {
        System.out.println("------ CourseController getDeleteById() ------");
        System.out.println("course-id: " + courseId);
        
        System.out.println(("----- CourseController getById() Result -----"));
        courseService.deleteCourseById(courseId);

        return "index";
    }

    @PostMapping("/")
    public String postInsertAndUpdate(
        @RequestParam() Map<String, String> param
    ) {
        System.out.println("------ CourseController postInsertAndUpdate() ------");
        System.out.println("course-id: " + param.get("course-id"));
        System.out.println("course-name: " + param.get("course-name"));
        System.out.println("course-desc: " + param.get("course-desc"));

        System.out.println("----- CourseController postInsertAndUpdate() Result -----");
        CourseEntity entity = new CourseEntity();
        if(null != param.get("course-id")) {
            entity.setCourseId(Integer.parseInt(param.get("course-id")));
        }
        entity.setCourseName(param.get("course-name"));
        CourseEntity result = courseService.saveCourse(entity);
        System.out.println("Course ID:" + result.getCourseId());
        System.out.println("Course Name: " + result.getCourseName());
        return "index";
    }

}
