package com.springboot.service;

import com.springboot.dto.InstructorDto;

import java.util.List;

public interface InstructorService {
    InstructorDto getInstructorById(Long id);
    List<InstructorDto> getAllInstructors();
    InstructorDto createInstructor(InstructorDto instructorDTO);
    InstructorDto updateInstructor(Long id, InstructorDto instructorDTO);
    void deleteInstructor(Long id);

}
