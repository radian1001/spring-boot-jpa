package com.radian.jpaTutorial.JpaTuts.controller;

import com.radian.jpaTutorial.JpaTuts.dto.StudentDto;
import com.radian.jpaTutorial.JpaTuts.service.StudentService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StudentController {

   private final StudentService studentService;

   @GetMapping("/students")
   public Page<StudentDto> getAllStudents(@RequestParam(defaultValue = "0") int pageNumber,
                                         @RequestParam(defaultValue = "5") int pageSize,
                                         @RequestParam(defaultValue = "id") String sortBy) {
       return studentService.getAllStudents(pageNumber, pageSize, sortBy);
   }

   @GetMapping("/students/{id}")
   public StudentDto getStudentById(@PathVariable Long id) {
       return studentService.getStudentById(id);
   }

   @PutMapping("/students/{id}")
   public StudentDto updateStudent(@PathVariable Long id, StudentDto studentDto) {
       return studentService.updateStudent(id, studentDto);
   }

   @PostMapping("/students/{studentId}/subjects/{subjectId}")
   public StudentDto assignStudentToCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
       return studentService.assignStudentToCourse(studentId, courseId);
   }

   @PostMapping("/students/{studentId}/professors/{professorId}")
   public StudentDto assignStudentToProfessor(@PathVariable Long studentId, @PathVariable Long professorId) {
       return studentService.assignStudentToProfessor(studentId, professorId);
   }

   @DeleteMapping("/students/{studentId}/subjects/{subjectId}")
   public void removeStudentFromCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
       studentService.removeStudentFromCourse(studentId, courseId);
   }

   @DeleteMapping("/students/{studentId}/professors/{professorId}")
   public void removeStudentFromProfessor(@PathVariable Long studentId, @PathVariable Long professorId) {
       studentService.removeStudentFromProfessor(studentId, professorId);
   }


}
