package com.example.prect3.controller;

import com.example.prect3.entity.TeacherEntity;
import com.example.prect3.service.TeacherServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    private TeacherServices teacherServices;

    @GetMapping
    public String getAllTeachers(Model model) {
        List<TeacherEntity> teachers = teacherServices.getAllTeachers();
        model.addAttribute("teachers", teachers);
        return "teacherList";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("teacher", new TeacherEntity());
        return "createTeacher";
    }

    @PostMapping("/create")
    public String createTeacher(@ModelAttribute("teacher") TeacherEntity teacher) {
        teacherServices.saveTeacher(teacher);
        return "redirect:/teachers";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        TeacherEntity teacher = teacherServices.getTeacherById(id);
        if (teacher == null) {
            return "error";
        }
        model.addAttribute("teacher", teacher);
        return "editTeacher";
    }

    @PostMapping("/edit/{id}")
    public String updateTeacher(@PathVariable Long id, @ModelAttribute("teacher") TeacherEntity teacher) {
        teacher.setId(id);
        teacherServices.saveTeacher(teacher);
        return "redirect:/teachers";
    }

    @GetMapping("/delete/{id}")
    public String deleteTeacher(@PathVariable Long id) {
        teacherServices.deleteTeacher(id);
        return "redirect:/teachers";
    }
}
