package com.ead.course.services.impl;

import com.ead.course.models.CourseModel;
import com.ead.course.repositories.CourseRepository;
import com.ead.course.services.CourseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public CourseModel save(CourseModel courseModel) {
        return this.courseRepository.save(courseModel);
    }

    @Override
    public Optional<CourseModel> findById(UUID courseUUID) {
        return this.courseRepository.findById(courseUUID);
    }

    @Override
    public void delete(UUID courseUUID) {
        this.courseRepository.deleteById(courseUUID);
    }

    @Override
    public Page<CourseModel> findAll(Pageable pageable) {
        return this.courseRepository.findAll(pageable);
    }
}
