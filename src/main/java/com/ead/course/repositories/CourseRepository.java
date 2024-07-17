package com.ead.course.repositories;

import com.ead.course.models.CourseModel;
import com.ead.course.models.ModuleModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface CourseRepository extends JpaRepository<CourseModel, UUID> {

    @Query(value = "SELECT * FROM tb_modules WHERE course_id = :courseId", nativeQuery = true)
    List<ModuleModel> findModulesByCourseId(@Param("courseId") UUID courseId);

    @Query("SELECT c FROM CourseModel c JOIN c.coursesUsers cu WHERE cu.userId = ?1")
    Page<CourseModel> findByUserId(UUID userId, Pageable pageable);
}
