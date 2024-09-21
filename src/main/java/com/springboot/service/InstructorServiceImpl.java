package com.springboot.service;

import com.springboot.dto.InstructorDto;
import com.springboot.entity.Instructor;
import com.springboot.repository.InstructorRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InstructorServiceImpl implements InstructorService{

    private InstructorRepository instructorRepository;
    private ModelMapper modelMapper;

    private InstructorDto convertToDto(Instructor instructor){
        InstructorDto instructorDto = modelMapper.map(instructor, InstructorDto.class);
        return instructorDto;
    }

    private Instructor convertToEntity(InstructorDto instructorDto){
        Instructor instructor = modelMapper.map(instructorDto, Instructor.class);
        return instructor;
    }

    @Override
    public InstructorDto getInstructorById(Long id) {
        Instructor instructor = instructorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Instructor not found with id : "+id));
        return convertToDto(instructor);
    }

    @Override
    public List<InstructorDto> getAllInstructors() {
        return instructorRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public InstructorDto createInstructor(InstructorDto instructorDTO) {
        Instructor instructor = convertToEntity(instructorDTO);
        instructor = instructorRepository.save(instructor);
        return convertToDto(instructor);
    }

    @Override
    public InstructorDto updateInstructor(Long id, InstructorDto instructorDTO) {
        Instructor instructor = instructorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Instructor not found with id : "+id));

        instructor.setName(instructorDTO.getName());
        instructor.setEmail(instructorDTO.getEmail());
        instructor.setDepartment(instructorDTO.getDepartment());
        instructor = instructorRepository.save(instructor);

        return convertToDto(instructor);
    }

    @Override
    public void deleteInstructor(Long id) {
        Instructor instructor = instructorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Instructor not found with id : "+id));

        instructorRepository.deleteById(id);

    }
}
