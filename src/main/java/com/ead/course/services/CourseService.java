package com.ead.course.services;

import com.ead.course.models.CourseModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface CourseService {
    CourseModel save(CourseModel courseModel);

    Optional<CourseModel> findById(UUID courseUUID);

    void delete(UUID courseUUID);

    Page<CourseModel> findAll(Pageable pageable);

    Page<CourseModel> findByUserId(UUID userId, Pageable pageable);
}
