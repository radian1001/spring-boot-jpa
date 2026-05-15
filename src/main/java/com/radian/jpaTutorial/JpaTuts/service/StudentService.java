package com.radian.jpaTutorial.JpaTuts.service;

import com.radian.jpaTutorial.JpaTuts.dto.StudentDto;
import com.radian.jpaTutorial.JpaTuts.entity.Student;
import com.radian.jpaTutorial.JpaTuts.entity.Subject;
import com.radian.jpaTutorial.JpaTuts.entity.Professor;
import com.radian.jpaTutorial.JpaTuts.entity.AdmissionList;
import com.radian.jpaTutorial.JpaTuts.repository.StudentRepo;
import com.radian.jpaTutorial.JpaTuts.repository.SubjectRepo;
import com.radian.jpaTutorial.JpaTuts.repository.ProfessorRepo;
import com.radian.jpaTutorial.JpaTuts.repository.AdmissionListRepo;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepo studentRepository;
    private final SubjectRepo subjectRepository;
    private final ProfessorRepo professorRepository;
    private final AdmissionListRepo admissionListRepository;
    private final ModelMapper modelMapper;

    public StudentService(StudentRepo studentRepository,
                          SubjectRepo subjectRepository,
                          ProfessorRepo professorRepository,
                          AdmissionListRepo admissionListRepository,
                          ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
        this.professorRepository = professorRepository;
        this.admissionListRepository = admissionListRepository;
        this.modelMapper = modelMapper;
    }

    public Page<StudentDto> getAllStudents(int pageNumber, int pageSize, String sortBy) {
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.ASC, sortBy));
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        
        return studentRepository.findAll(pageable)
                .map(student -> modelMapper.map(student, StudentDto.class));
    }

    public StudentDto getStudentById(Long id){
        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        return modelMapper.map(student, StudentDto.class);
    }

    @Transactional
    public StudentDto updateStudent(Long id, StudentDto studentDto){
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        
        // Update simple fields
        student.setName(studentDto.getName());
        
        // Convert Subject IDs to Subject entities (no null check needed due to @Builder.Default)
        if (!studentDto.getSubjectIds().isEmpty()) {
            List<Subject> subjects = subjectRepository.findAllById(studentDto.getSubjectIds());
            student.setSubjectList(subjects);
        }
        
        // Convert Professor IDs to Professor entities (no null check needed due to @Builder.Default)
        if (!studentDto.getProfessorIds().isEmpty()) {
            List<Professor> professors = professorRepository.findAllById(studentDto.getProfessorIds());
            student.setProfessorList(professors);
        }
        
        // Convert AdmissionList ID to AdmissionList entity (nullable case)
        if (studentDto.getAdmissionListId() != null) {
            AdmissionList admissionList = admissionListRepository.findById(studentDto.getAdmissionListId())
                    .orElseThrow(() -> new RuntimeException("AdmissionList not found with id: " + studentDto.getAdmissionListId()));
            student.setAdmissionList(admissionList);
        }
        
        Student updatedStudent = studentRepository.save(student);
        return modelMapper.map(updatedStudent, StudentDto.class);
    }

    @Transactional
    public StudentDto assignStudentToCourse(Long studentId,Long courseId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));
        Subject subject = subjectRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found with id: " + courseId));
        student.getSubjectList().add(subject);

        //saving the inverse side of the relationship
        Student updatedStudent = studentRepository.save(student);

        //saving the owning side of the relationship
        subject.getStudentList().add(student);
        subjectRepository.save(subject);

        return modelMapper.map(updatedStudent, StudentDto.class);
    }

    @Transactional
    public StudentDto assignStudentToProfessor(Long studentId, Long professorId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));
        Professor professor = professorRepository.findById(professorId).orElseThrow(() -> new RuntimeException("Professor not found with id: " + professorId));
        //savign the owning side of the relationship
        student.getProfessorList().add(professor);
        StudentDto updatedStudent = modelMapper.map(studentRepository.save(student), StudentDto.class);
        //saving the inverse side of the relationship
        professor.getStudentList().add(student);
        professorRepository.save(professor);
        return updatedStudent;
    }

    @Transactional
    public void removeStudentFromCourse(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));
        Subject subject = subjectRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found with id: " + courseId));
        student.getSubjectList().remove(subject);
        studentRepository.save(student);
        subject.getStudentList().remove(student);
        subjectRepository.save(subject);
    }

    @Transactional
    public void removeStudentFromProfessor(Long studentId, Long professorId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));
        Professor professor = professorRepository.findById(professorId).orElseThrow(() -> new RuntimeException("Professor not found with id: " + professorId));
        student.getProfessorList().remove(professor);
        studentRepository.save(student);
        professor.getStudentList().remove(student);
        professorRepository.save(professor);
    }



//    @Transactional
//    public StudentDto assignStudentToCourse(Long studentId, Long subjectId) {
//        Student student = studentRepository.findById(studentId)
//                .orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));
//        Subject subject = subjectRepository.findById(subjectId)
//                .orElseThrow(() -> new RuntimeException("Subject not found with id: " + subjectId));
//
//        // Add subject to student's subject list if not already present
//        if (student.getSubjectList() == null) {
//            student.setSubjectList(new ArrayList<>());
//        }
//        if (!student.getSubjectList().contains(subject)) {
//            student.getSubjectList().add(subject);
//        }
//
//        Student savedStudent = studentRepository.save(student);
//        return modelMapper.map(savedStudent, StudentDto.class);
//    }
//
//    @Transactional
//    public StudentDto assignStudentToProfessor(Long studentId, Long professorId) {
//        Student student = studentRepository.findById(studentId)
//                .orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));
//        Professor professor = professorRepository.findById(professorId)
//                .orElseThrow(() -> new RuntimeException("Professor not found with id: " + professorId));
//
//        // Add professor to student's professor list if not already present
//        if (student.getProfessorList() == null) {
//            student.setProfessorList(new ArrayList<>());
//        }
//        if (!student.getProfessorList().contains(professor)) {
//            student.getProfessorList().add(professor);
//        }
//
//        Student savedStudent = studentRepository.save(student);
//        return modelMapper.map(savedStudent, StudentDto.class);
//    }
//
//    @Transactional
//    public StudentDto removeStudentFromCourse(Long studentId, Long subjectId) {
//        Student student = studentRepository.findById(studentId)
//                .orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));
//        Subject subject = subjectRepository.findById(subjectId)
//                .orElseThrow(() -> new RuntimeException("Subject not found with id: " + subjectId));
//
//        // Remove subject from student's subject list
//        if (student.getSubjectList() != null) {
//            student.getSubjectList().remove(subject);
//        }
//
//        Student savedStudent = studentRepository.save(student);
//        return modelMapper.map(savedStudent, StudentDto.class);
//    }
//
//    @Transactional
//    public StudentDto removeStudentFromProfessor(Long studentId, Long professorId) {
//        Student student = studentRepository.findById(studentId)
//                .orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));
//        Professor professor = professorRepository.findById(professorId)
//                .orElseThrow(() -> new RuntimeException("Professor not found with id: " + professorId));
//
//        // Remove professor from student's professor list
//        if (student.getProfessorList() != null) {
//            student.getProfessorList().remove(professor);
//        }
//
//        Student savedStudent = studentRepository.save(student);
//        return modelMapper.map(savedStudent, StudentDto.class);
//    }
}
