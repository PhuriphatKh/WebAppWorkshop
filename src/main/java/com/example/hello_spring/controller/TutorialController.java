package com.example.hello_spring.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/tutorial")
public class TutorialController {

    @GetMapping("/")
    public String getTutorial(
        @RequestParam(name = "id", required = false, defaultValue = "0") Integer id
    ) {
        System.out.println("------ getTutorial ------");
        System.out.println("ID: " + id);
        return "index";
    }
    

    @GetMapping("/{id}")
    public String getTutorialPath(
        @PathVariable(name = "id") Integer id
    ) {
        System.out.println("------ getTutorialPath ------");
        System.out.println("ID: " + id);
        return "index";
    }

    @PostMapping("/")
    public String posToturial(
    @RequestParam() Map<String, String> param
    ) {
    System.out.println("------ postTutorial ------");
    System.out.println("ID: " + param.get("id"));
    System.out.println("Code: " + param.get("code"));
    return "index";
    }
}

