package com.springboot.service;

import com.springboot.dto.CourseDto;

import java.util.List;
import java.util.Set;

public interface CourseService {
    CourseDto createCourse(CourseDto courseDto);
    CourseDto updateCourse(Long id, CourseDto courseDto);
    CourseDto getCourseById(Long id);
    List<CourseDto> getAllCourses();
    void deleteCourse(Long id);

    Set<CourseDto> getAllCoursesByInstructorId(Long instructorId);

}
