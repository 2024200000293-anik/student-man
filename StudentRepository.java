package com.example.studentmanagement.repository;

import com.example.studentmanagement.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Extending JpaRepository gives us save(), findAll(), findById(), deleteById() ... for free.
public interface StudentRepository extends JpaRepository<Student, Long> {

    // Spring builds the query from the method name:
    // SELECT * FROM students WHERE LOWER(name) LIKE %keyword%
    List<Student> findByNameContainingIgnoreCase(String keyword);
}
