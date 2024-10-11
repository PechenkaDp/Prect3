package com.example.prect3.controller;

import com.example.prect3.entity.CourseEntity;
import com.example.prect3.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public String getAllCourses(Model model) {
        model.addAttribute("courses", courseService.findAll());
        return "courseList";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("course", new CourseEntity());
        return "createCourse";
    }

    @PostMapping("/create")
    public String createCourse(@ModelAttribute("course") CourseEntity courseEntity) {
        courseService.save(courseEntity);
        return "redirect:/courses";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("course", courseService.findById(id));
        return "editCourse";
    }

    @PostMapping("/edit/{id}")
    public String updateCourse(@PathVariable Long id, @ModelAttribute("course") CourseEntity courseEntity) {
        courseEntity.setId(id);
        courseService.save(courseEntity);
        return "redirect:/courses";
    }

    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Long id) {
        courseService.deleteById(id);
        return "redirect:/courses";
    }
}
