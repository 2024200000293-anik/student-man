package com.example.studentmanagement.service;

import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// The service holds the business logic. The controller talks to it, never to the repository directly.
@Service
public class StudentService {

    private final StudentRepository repository;

    // Constructor injection: Spring passes in the repository automatically
    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    public List<Student> searchStudents(String keyword) {
        return repository.findByNameContainingIgnoreCase(keyword);
    }

    public Student getStudentById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student not found: " + id));
    }

    // save() inserts when id is null, updates when the id already exists
    public void saveStudent(Student student) {
        repository.save(student);
    }

    public void deleteStudent(Long id) {
        repository.deleteById(id);
    }
}
