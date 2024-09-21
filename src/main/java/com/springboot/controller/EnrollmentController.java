package com.springboot.controller;

import com.springboot.dto.EnrollmentDto;
import com.springboot.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @GetMapping("/{id}")
    public ResponseEntity<EnrollmentDto> getEnrollmentById(@PathVariable Long id) {
        EnrollmentDto enrollment = enrollmentService.getEnrollmentById(id);
        return ResponseEntity.ok(enrollment);
    }

    @GetMapping
    public ResponseEntity<List<EnrollmentDto>> getAllEnrollments() {
        List<EnrollmentDto> enrollments = enrollmentService.getAllEnrollments();
        return ResponseEntity.ok(enrollments);
    }

    @PostMapping
    public ResponseEntity<EnrollmentDto> createEnrollment(@RequestBody EnrollmentDto enrollmentDto) {
        EnrollmentDto enrollment = enrollmentService.createEnrollment(enrollmentDto);
        return ResponseEntity.ok(enrollment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnrollmentDto> updateEnrollment(@PathVariable Long id, @RequestBody EnrollmentDto enrollmentDto) {
        EnrollmentDto enrollment = enrollmentService.updateEnrollment(id, enrollmentDto);
        return ResponseEntity.ok(enrollment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEnrollment(@PathVariable Long id) {
        enrollmentService.deleteEnrollment(id);
        return ResponseEntity.ok("Enrollment deleted successfully");
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<Set<EnrollmentDto>> getEnrollmentsByCourseId(@PathVariable Long courseId){
        return ResponseEntity.ok(enrollmentService.getEnrollmentsByCourseId(courseId));
    }

    @GetMapping("/instructor/{instructorId}")
    public ResponseEntity<Set<EnrollmentDto>> getEnrollmentsByInstructorId(@PathVariable Long instructorId){
        return ResponseEntity.ok(enrollmentService.getEnrollmentsByInstructorId(instructorId));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<Set<EnrollmentDto>> getEnrollmentsByStudentId(@PathVariable Long studentId){
        return ResponseEntity.ok(enrollmentService.getEnrollmentsByStudentId(studentId));
    }
}
