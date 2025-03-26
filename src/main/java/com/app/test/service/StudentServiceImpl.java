package com.app.test.service;

import com.app.test.model.Student;
import com.app.test.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        return student.orElse(null);
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }
    @Override
    public void updateStudent(Long id, Student student) {
        Student existingStudent = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
        existingStudent.setName(student.getName());
        existingStudent.setAge(student.getAge());
        // Оновіть інші поля за необхідності
        studentRepository.save(existingStudent);
}
    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

}