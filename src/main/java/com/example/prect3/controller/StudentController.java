package com.example.prect3.controller;

import com.example.prect3.entity.StudentEntity;
import com.example.prect3.service.StudentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentServices studentServices;

    @GetMapping
    public String getAllStudents(Model model) {
        List<StudentEntity> students = studentServices.getAllStudents();
        model.addAttribute("students", students);
        return "studentList";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("student", new StudentEntity());
        return "createStudent";
    }

    @PostMapping("/create")
    public String createStudent(@ModelAttribute("student") StudentEntity student) {
        studentServices.saveStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        StudentEntity student = studentServices.getStudentById(id);
        if (student == null) {
            return "error";
        }
        model.addAttribute("student", student);
        return "editStudent";
    }

    @PostMapping("/edit/{id}")
    public String updateStudent(@PathVariable Long id, @ModelAttribute("student") StudentEntity student) {
        student.setId(id);
        studentServices.saveStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentServices.deleteStudent(id);
        return "redirect:/students";
    }
}
