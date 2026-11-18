package com.example.studentmanagement;

import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StudentManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentManagementApplication.class, args);
    }

    // Runs once at startup: adds a few students so the list isn't empty.
    @Bean
    CommandLineRunner loadSampleData(StudentRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.save(new Student("Ayesha Rahman", "ayesha@example.com", "CSE", 3));
                repository.save(new Student("Tanvir Ahmed", "tanvir@example.com", "EEE", 5));
                repository.save(new Student("Nusrat Jahan", "nusrat@example.com", "BBA", 2));
            }
        };
    }
}
