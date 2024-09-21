package com.springboot.repository;

import com.springboot.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByEnrollments_Course_Id(Long courseId);
}
