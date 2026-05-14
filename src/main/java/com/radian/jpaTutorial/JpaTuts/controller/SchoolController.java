//package com.radian.jpaTutorial.JpaTuts.controller;
//
//import com.radian.jpaTutorial.JpaTuts.entity.AdmissionList;
//import com.radian.jpaTutorial.JpaTuts.entity.Professor;
//import com.radian.jpaTutorial.JpaTuts.entity.Student;
//import com.radian.jpaTutorial.JpaTuts.entity.Subject;
//import com.radian.jpaTutorial.JpaTuts.repository.AdmissionListRepo;
//import com.radian.jpaTutorial.JpaTuts.repository.ProfessorRepo;
//import com.radian.jpaTutorial.JpaTuts.repository.StudentRepo;
//import com.radian.jpaTutorial.JpaTuts.repository.SubjectRepo;
//import org.springframework.data.domain.Sort;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/school")
//public class SchoolController {
//
//    private final StudentRepo studentRepo;
//    private final ProfessorRepo professorRepo;
//    private final SubjectRepo subjectRepo;
//    private final AdmissionListRepo admissionListRepo;
//
//    public SchoolController(StudentRepo studentRepo,
//                            ProfessorRepo professorRepo,
//                            SubjectRepo subjectRepo,
//                            AdmissionListRepo admissionListRepo) {
//        this.studentRepo = studentRepo;
//        this.professorRepo = professorRepo;
//        this.subjectRepo = subjectRepo;
//        this.admissionListRepo = admissionListRepo;
//    }
//
//    @GetMapping("/students")
//    public List<Student> getStudents(@RequestParam(defaultValue = "id") String sortBy) {
//        return studentRepo.findAll(Sort.by(sortBy));
//    }
//
//    @GetMapping("/professors")
//    public List<Professor> getProfessors(@RequestParam(defaultValue = "id") String sortBy) {
//        return professorRepo.findAll(Sort.by(sortBy));
//    }
//
//    @GetMapping("/subjects")
//    public List<Subject> getSubjects(@RequestParam(defaultValue = "id") String sortBy) {
//        return subjectRepo.findAll(Sort.by(sortBy));
//    }
//
//    @GetMapping("/admissions")
//    public List<AdmissionList> getAdmissions(@RequestParam(defaultValue = "id") String sortBy) {
//        return admissionListRepo.findAll(Sort.by(sortBy));
//    }
//}
