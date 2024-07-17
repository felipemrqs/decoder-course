package com.ead.course.controllers;

import com.ead.course.dtos.CourseDTO;
import com.ead.course.models.CourseModel;
import com.ead.course.services.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/courses")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<Object> saveCourse(@RequestBody @Valid CourseDTO courseDTO) {
        var courseModel = new CourseModel();
        BeanUtils.copyProperties(courseDTO, courseModel);
        courseModel.setCreatedAt(LocalDateTime.now());
        courseModel.setUpdatedAt(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.save(courseModel));
    }

    @DeleteMapping("/{courseUUID}")
    public ResponseEntity<Object> deleteCourse(@PathVariable(value = "courseUUID") UUID courseUUID) {
        var courseModelOptional = courseService.findById(courseUUID);
        return courseModelOptional.map(courseModel -> {
            courseService.delete(courseUUID);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{courseUUID}")
    public ResponseEntity<Object> update(@PathVariable(value = "courseUUID") UUID courseUUID, @RequestBody @Validated CourseDTO courseDTO) {
        var courseModelOptional = courseService.findById(courseUUID);
        return courseModelOptional.map(courseModel -> {
            BeanUtils.copyProperties(courseDTO, courseModel);
            courseModel.setUpdatedAt(LocalDateTime.now());
            courseService.save(courseModel);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping
    public ResponseEntity<Page<CourseModel>> getAllCourses(@RequestParam(required = false) UUID userId, @PageableDefault Pageable pageable) {
        return ResponseEntity.ok(
                Optional.ofNullable(userId)
                        .map(id -> courseService.findByUserId(id, pageable))
                        .orElseGet(() -> courseService.findAll(pageable)));
    }
}
