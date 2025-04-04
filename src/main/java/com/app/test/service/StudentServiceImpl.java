package com.app.test.service;

import com.app.test.model.Student;
import com.app.test.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);  // Логер

    @Override
    public List<Student> getAllStudents() {
        logger.info("Request to receive all students");
        List<Student> students = studentRepository.findAll();
        logger.info("Students found: {}", students.size());
        return students;
    }

    @Override
    public Student getStudentById(Long id) {
        logger.info("Запит на отримання студента з id: {}", id);
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            logger.info("Студент знайдений: {}", student.get());
        } else {
            logger.warn("Студент з id {} не знайдений", id);
        }
        return student.orElse(null);
    }

    @Override
    public Student saveStudent(Student student) {
        logger.info("Request to save student: {}", student);
        Student savedStudent = studentRepository.save(student);
        logger.info("Student saved: {}", savedStudent);
        return savedStudent;
    }

    @Override
    public void updateStudent(Long id, Student student) {
        logger.info("Request to update student id: {}", id);
        Student existingStudent = studentRepository.findById(id).orElseThrow(() -> {
            logger.error("Student with id {} not found for update", id);
            return new RuntimeException("Student not found");
        });
        existingStudent.setName(student.getName());
        existingStudent.setAge(student.getAge());
        // Оновіть інші поля за необхідності
        studentRepository.save(existingStudent);
        logger.info("Student with id {} updated", id);
    }

    @Override
    public void deleteStudent(Long id) {
        logger.info("Request to delete a student with id: {}", id);
        studentRepository.deleteById(id);
        logger.info("Student with id {} deleted", id);
    }
}
