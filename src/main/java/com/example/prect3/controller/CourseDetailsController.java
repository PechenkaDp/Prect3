package com.example.prect3.controller;

import com.example.prect3.entity.CourseDetailsEntity;
import com.example.prect3.service.CourseDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/course-details")
public class CourseDetailsController {

    private final CourseDetailsService courseDetailsService;

    @Autowired
    public CourseDetailsController(CourseDetailsService courseDetailsService) {
        this.courseDetailsService = courseDetailsService;
    }

    @GetMapping
    public String getAllCourseDetails(Model model) {
        model.addAttribute("courseDetails", courseDetailsService.findAll());
        return "courseDetailsList";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("courseDetails", new CourseDetailsEntity());
        return "createCourseDetails";
    }

    @PostMapping("/create")
    public String createCourseDetails(@ModelAttribute("courseDetails") CourseDetailsEntity courseDetailsEntity) {
        courseDetailsService.save(courseDetailsEntity);
        return "redirect:/course-details";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        CourseDetailsEntity courseDetails = courseDetailsService.findById(id);
        if (courseDetails == null) {
            return "error";
        }
        model.addAttribute("courseDetails", courseDetails);
        return "editCourseDetails";
    }

    @PostMapping("/edit/{id}")
    public String updateCourseDetails(@PathVariable Long id, @ModelAttribute("courseDetails") CourseDetailsEntity courseDetailsEntity) {
        courseDetailsEntity.setId(id);
        courseDetailsService.save(courseDetailsEntity);
        return "redirect:/course-details";
    }

    @GetMapping("/delete/{id}")
    public String deleteCourseDetails(@PathVariable Long id) {
        courseDetailsService.deleteById(id);
        return "redirect:/course-details";
    }
}
