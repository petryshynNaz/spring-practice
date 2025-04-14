package com.app.test.controller;

import com.app.test.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final StudentService studentService;

    @GetMapping({"/", "/home"})
    public String home(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("students", studentService.getAllStudents());
            model.addAttribute("username", principal.getName());
        }
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "access-denied";
    }
}