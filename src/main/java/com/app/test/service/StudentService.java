package com.app.test.service;

import com.app.test.model.Student;
import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
    Student getStudentById(Long id);
    Student saveStudent(Student student);  // Додано цей метод
    void updateStudent(Long id, Student student);
    void deleteStudent(Long id);
}