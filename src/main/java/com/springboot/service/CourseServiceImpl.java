package com.springboot.service;

import com.springboot.dto.CourseDto;
import com.springboot.entity.Course;
import com.springboot.entity.Instructor;
import com.springboot.entity.Student;
import com.springboot.repository.CourseRepository;
import com.springboot.repository.InstructorRepository;
import com.springboot.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService{

    private CourseRepository courseRepository;
    private ModelMapper modelMapper;
    private InstructorRepository instructorRepository;
    private StudentRepository studentRepository;

    private Course mapToEntity(CourseDto courseDto){
        Course course = modelMapper.map(courseDto, Course.class);
        return course;
    }

    private CourseDto mapToDto(Course course){
        CourseDto courseDto = modelMapper.map(course, CourseDto.class);
        return courseDto;
    }

    @Override
    public CourseDto createCourse(CourseDto courseDto) {
        Instructor instructor = instructorRepository.findById(courseDto.getInstructorId())
                .orElseThrow(()->new RuntimeException("Instructor not found with id : "+ courseDto.getInstructorId()));

        Course course = mapToEntity(courseDto);
        course.setInstructor(instructor);

        Course created = courseRepository.save(course);
        return mapToDto(created);
    }

    @Override
    public CourseDto updateCourse(Long id, CourseDto courseDto) {
        Course course = courseRepository.findById(id)
                .orElseThrow(()->new RuntimeException("course not found with id : "+id));

        Instructor instructor = instructorRepository.findById(courseDto.getInstructorId())
                .orElseThrow(()->new RuntimeException("Instructor not found with id : "+ courseDto.getInstructorId()));

        course.setTitle(courseDto.getTitle());
        course.setDescription(courseDto.getDescription());
        course.setDuration(courseDto.getDuration());
        course.setInstructor(instructor);

        Course updated = courseRepository.save(course);
        return mapToDto(updated);
    }

    @Override
    public CourseDto getCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(()->new RuntimeException("course not found with : "+id));

        return mapToDto(course);
    }

    @Override
    public List<CourseDto> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public void deleteCourse(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(()->new RuntimeException("course not found with : "+id));

        courseRepository.deleteById(id);
    }

    @Override
    public Set<CourseDto> getAllCoursesByInstructorId(Long instructorId) {
        Instructor instructor = instructorRepository.findById(instructorId)
                .orElseThrow(()->new RuntimeException("Instructor not found with id : "+ instructorId));

        Set<Course> courses = instructor.getCourses();
        return courses.stream().map(this::mapToDto).collect(Collectors.toSet());
    }
}
