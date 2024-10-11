package com.example.prect3.service;

import com.example.prect3.entity.CourseEntity;
import com.example.prect3.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<CourseEntity> findAll() {
        return courseRepository.findAll();
    }

    public CourseEntity findById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    public CourseEntity save(CourseEntity courseEntity) {
        return courseRepository.save(courseEntity);
    }

    public void deleteById(Long id) {
        courseRepository.deleteById(id);
    }
}
