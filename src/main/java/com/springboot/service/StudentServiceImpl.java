package com.springboot.service;

import com.springboot.dto.CourseDto;
import com.springboot.dto.EnrollmentDto;
import com.springboot.dto.StudentDto;
import com.springboot.entity.Course;
import com.springboot.entity.Enrollment;
import com.springboot.entity.Student;
import com.springboot.repository.CourseRepository;
import com.springboot.repository.EnrollmentRepository;
import com.springboot.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService{

    private StudentRepository studentRepository;
    private ModelMapper modelMapper;

    private Student mapToEntity(StudentDto studentDto){
        Student student = modelMapper.map(studentDto, Student.class);
        return student;
    }

    private StudentDto mapToDto(Student student){
        StudentDto studentDto = modelMapper.map(student, StudentDto.class);
        return studentDto;
    }

    @Override
    public StudentDto createStudent(StudentDto studentDto) {
        Student student = mapToEntity(studentDto);
        Student created = studentRepository.save(student);
        return mapToDto(created);
    }

    @Override
    public StudentDto updateStudent(Long id, StudentDto studentDto) {
        Student student = studentRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Student not found with : "+id));

        student.setEmail(studentDto.getEmail());
        student.setName(studentDto.getName());
        student.setPhone(studentDto.getPhone());
        Student updated = studentRepository.save(student);
        return mapToDto(updated);
    }

    @Override
    public StudentDto getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Student not found with : "+id));

        return mapToDto(student);
    }

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Student not found with : "+id));

        studentRepository.deleteById(id);
    }
}
