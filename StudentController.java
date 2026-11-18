package com.example.studentmanagement.controller;

import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

// @Controller returns the NAME of an HTML template (not JSON).
// "students" -> templates/students.html
@Controller
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/students";
    }

    // READ: list all students, or search by name (?keyword=...)
    @GetMapping("/students")
    public String listStudents(@RequestParam(name = "keyword", required = false) String keyword,
                               Model model) {
        List<Student> students = (keyword == null )
                ? studentService.getAllStudents()
                : studentService.searchStudents(keyword);

        // Data put in the Model is available inside the HTML as ${students}
        model.addAttribute("students", students);
        model.addAttribute("keyword", keyword);
        return "students";
    }

    // CREATE (step 1): show an empty form
    @GetMapping("/students/new")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student());
        return "student-form";
    }

    // UPDATE (step 1): show the form filled with the existing student
    @GetMapping("/students/{id}/edit")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("student", studentService.getStudentById(id));
        return "student-form";
    }

    // CREATE / UPDATE (step 2): the form posts here
    @PostMapping("/students/save")
    public String saveStudent(@Valid @ModelAttribute("student") Student student,
                              BindingResult result,
                              RedirectAttributes redirectAttributes) {
        // If validation failed, show the form again with error messages
        if (result.hasErrors()) {
            return "student-form";
        }
        boolean isNew = student.getId() == null;
        studentService.saveStudent(student);

        // Flash attributes survive one redirect, perfect for a success message
        redirectAttributes.addFlashAttribute("message",
                isNew ? "Student added." : "Student updated.");
        return "redirect:/students";
    }

    // DELETE
    @PostMapping("/students/{id}/delete")
    public String deleteStudent(@PathVariable("id") Long id,
                                RedirectAttributes redirectAttributes) {
        studentService.deleteStudent(id);
        redirectAttributes.addFlashAttribute("message", "Student deleted.");
        return "redirect:/students";
    }
}
