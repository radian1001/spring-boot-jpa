package com.radian.jpaTutorial.JpaTuts.service;

import com.radian.jpaTutorial.JpaTuts.dto.StudentDto;
import com.radian.jpaTutorial.JpaTuts.entity.Student;
import com.radian.jpaTutorial.JpaTuts.repository.StudentRepo;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepo studentRepository;
    private final ModelMapper modelMapper;

    public StudentService(StudentRepo studentRepository, ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }

    public Page<StudentDto> getAllStudents(int pageNumber, int pageSize, String sortBy) {
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.ASC, sortBy));
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        
        return studentRepository.findAll(pageable)
                .map(student -> modelMapper.map(student, StudentDto.class));
    }
}
