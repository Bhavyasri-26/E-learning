package com.springboot.service;

import com.springboot.dto.EnrollmentDto;

import java.util.List;
import java.util.Set;

public interface EnrollmentService {
    EnrollmentDto getEnrollmentById(Long id);
    List<EnrollmentDto> getAllEnrollments();
    EnrollmentDto createEnrollment(EnrollmentDto enrollmentDto);
    EnrollmentDto updateEnrollment(Long id, EnrollmentDto enrollmentDto);
    void deleteEnrollment(Long id);

    Set<EnrollmentDto> getEnrollmentsByCourseId(Long courseId);
    Set<EnrollmentDto> getEnrollmentsByInstructorId(Long instructorId);
    Set<EnrollmentDto> getEnrollmentsByStudentId(Long studentId);



}
