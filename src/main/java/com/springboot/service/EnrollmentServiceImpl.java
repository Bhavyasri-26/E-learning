package com.springboot.service;

import com.springboot.dto.EnrollmentDto;
import com.springboot.entity.Course;
import com.springboot.entity.Enrollment;
import com.springboot.entity.Instructor;
import com.springboot.entity.Student;
import com.springboot.repository.CourseRepository;
import com.springboot.repository.EnrollmentRepository;
import com.springboot.repository.InstructorRepository;
import com.springboot.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class EnrollmentServiceImpl implements EnrollmentService{

    private EnrollmentRepository enrollmentRepository;
    private ModelMapper modelMapper;
    private CourseRepository courseRepository;
    private StudentRepository studentRepository;
    private InstructorRepository instructorRepository;

    private EnrollmentDto mapToDto(Enrollment enrollment){
        EnrollmentDto enrollmentDto = modelMapper.map(enrollment, EnrollmentDto.class);
        return enrollmentDto;
    }

    private Enrollment mapToEntity(EnrollmentDto enrollmentDto){
        Enrollment enrollment = modelMapper.map(enrollmentDto, Enrollment.class);
        return enrollment;
    }

    @Override
    public EnrollmentDto getEnrollmentById(Long id) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enrollment not found with id : "+id));
        return mapToDto(enrollment);
    }

    @Override
    public List<EnrollmentDto> getAllEnrollments() {
        return enrollmentRepository.findAll().stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public EnrollmentDto createEnrollment(EnrollmentDto enrollmentDto) {
        Enrollment enrollment = mapToEntity(enrollmentDto);
        enrollment = enrollmentRepository.save(enrollment);
        return mapToDto(enrollment);
    }

    @Override
    public EnrollmentDto updateEnrollment(Long id, EnrollmentDto enrollmentDto) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enrollment not found with id : "+id));

        enrollment.setCourse(courseRepository.findById(enrollmentDto.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found with id : "+enrollmentDto.getCourseId())));

        enrollment.setStudent(studentRepository.findById(enrollmentDto.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found with id : "+enrollmentDto.getStudentId())));

        enrollment.setEnrollmentDate(enrollmentDto.getEnrollmentDate());
        enrollment = enrollmentRepository.save(enrollment);
        return mapToDto(enrollment);
    }

    @Override
    public void deleteEnrollment(Long id) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enrollment not found with id : "+id));

        enrollmentRepository.deleteById(id);

    }

    @Override
    public Set<EnrollmentDto> getEnrollmentsByCourseId(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(()->new RuntimeException("course not found with id : "+courseId));

        Set<Enrollment> enrollments = course.getEnrollments();
        return enrollments.stream().map(this::mapToDto).collect(Collectors.toSet());
    }

    @Override
    public Set<EnrollmentDto> getEnrollmentsByInstructorId(Long instructorId) {
        Instructor instructor = instructorRepository.findById(instructorId)
                .orElseThrow(()->new RuntimeException("course not found with id : "+instructorId));

        Set<Enrollment> enrollments = enrollmentRepository.findByCourse_Instructor_Id(instructorId);
        return enrollments.stream().map(this::mapToDto).collect(Collectors.toSet());
    }

    @Override
    public Set<EnrollmentDto> getEnrollmentsByStudentId(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(()->new RuntimeException("student not found with id : "+studentId));

        Set<Enrollment> enrollments = student.getEnrollments();
        return enrollments.stream().map(this::mapToDto).collect(Collectors.toSet());
    }
}
