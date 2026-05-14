//package com.radian.jpaTutorial.JpaTuts.controller;
//
//import com.radian.jpaTutorial.JpaTuts.dto.StudentDto;
//import com.radian.jpaTutorial.JpaTuts.service.StudentService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//public class StudentController {
//
//    private final StudentService studentService;
//
//    @GetMapping("/students")
//    public List<StudentDto> getAllStudents() {
//        return studentService.getAllStudents();
//    }
//
//    @GetMapping("/students/{id}")
//    public StudentDto getStudentById(@PathVariable Long id) {
//        return studentService.getStudentById(id);
//    }
//
//    @PutMapping("/students/{id}")
//    public StudentDto updateStudent(@PathVariable Long id, StudentDto studentDto) {
//        return studentService.updateStudent(id, studentDto);
//    }
//
//    @PostMapping("/students/{studentId}/subjects/{subjectId}")
//    public StudentDto assignStudentToCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
//        return studentService.assignStudentToCourse(studentId, courseId);
//    }
//
//    @PostMapping("/students/{studentId}/professors/{professorId}")
//    public StudentDto assignStudentToProfessor(@PathVariable Long studentId, @PathVariable Long professorId) {
//        return studentService.assignStudentToProfessor(studentId, professorId);
//    }
//
//    @DeleteMapping("/students/{studentId}/subjects/{subjectId}")
//    public StudentDto removeStudentFromCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
//        return studentService.removeStudentFromCourse(studentId, courseId);
//    }
//
//    @DeleteMapping("/students/{studentId}/professors/{professorId}")
//    public StudentDto removeStudentFromProfessor(@PathVariable Long studentId, @PathVariable Long professorId) {
//        return studentService.removeStudentFromProfessor(studentId, professorId);
//    }
//
//
//}
