package com.example.prect3.service;

import com.example.prect3.entity.CourseDetailsEntity;
import com.example.prect3.repository.CourseDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseDetailsService {

    private final CourseDetailsRepository courseDetailsRepository;

    @Autowired
    public CourseDetailsService(CourseDetailsRepository courseDetailsRepository) {
        this.courseDetailsRepository = courseDetailsRepository;
    }

    public List<CourseDetailsEntity> findAll() {
        return courseDetailsRepository.findAll();
    }

    public CourseDetailsEntity findById(Long id) {
        return courseDetailsRepository.findById(id).orElse(null);
    }

    public CourseDetailsEntity save(CourseDetailsEntity courseDetailsEntity) {
        return courseDetailsRepository.save(courseDetailsEntity);
    }

    public void deleteById(Long id) {
        courseDetailsRepository.deleteById(id);
    }
}
