package com.springboot.service;

import com.springboot.dto.CourseDto;
import com.springboot.dto.EnrollmentDto;
import com.springboot.dto.StudentDto;

import java.util.List;

public interface StudentService {
    StudentDto createStudent(StudentDto studentDto);
    StudentDto updateStudent(Long id, StudentDto studentDto);
    StudentDto getStudentById(Long id);
    List<StudentDto> getAllStudents();
    void deleteStudent(Long id);


}
