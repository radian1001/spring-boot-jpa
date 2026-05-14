package com.radian.jpaTutorial.JpaTuts.service;

import com.radian.jpaTutorial.JpaTuts.entity.Student;
import com.radian.jpaTutorial.JpaTuts.repository.StudentRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepo studentRepository;

    public StudentService(StudentRepo studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}
