package com.app.test.controller;

import com.app.test.model.Student;
import com.app.test.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentRestController {

    private final StudentService studentService;
    private static final Logger logger = LoggerFactory.getLogger(StudentRestController.class);  // Логер

    public StudentRestController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getAllStudents() {
        logger.info("Request to receive all students");
        List<Student> students = studentService.getAllStudents();
        logger.info("Students {} returned", students.size());
        return students;
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        logger.info("Request to get student with id: {}", id);
        Student student = studentService.getStudentById(id);
        if (student != null) {
            logger.info("The student was found: {}", student);
        } else {
            logger.warn("Student with id {} cannot be found", id);
        }
        return student;
    }

   @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Student saveStudent(@ModelAttribute Student student) {
    logger.info("Request to save a new student: {}", student);
    Student savedStudent = studentService.saveStudent(student);
    logger.info("Student saved: {}", savedStudent);
    return savedStudent;
}

    @PutMapping("/{id}")
    public void updateStudent(@PathVariable Long id, @Valid @RequestBody Student student) {
        logger.info("Request to update student with id: {}", id);
        studentService.updateStudent(id, student);
        logger.info("Student with id {} updated", id);
    }

    @DeleteMapping("{id}")
    public void deleteStudent(@PathVariable Long id) {
        logger.info("Request to delete student with id: {}", id);
        studentService.deleteStudent(id);
        logger.info("Student with id {} deleted", id);
    }
}