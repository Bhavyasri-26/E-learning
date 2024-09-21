package com.springboot.repository;

import com.springboot.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    Set<Enrollment> findByCourseId(Long courseId);
    Set<Enrollment> findByStudentId(Long studentId);
    Set<Enrollment> findByCourse_Instructor_Id(Long instructorId);
}
