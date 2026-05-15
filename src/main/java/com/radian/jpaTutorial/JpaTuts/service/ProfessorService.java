package com.radian.jpaTutorial.JpaTuts.service;

import com.radian.jpaTutorial.JpaTuts.dto.ProfessorDto;
import com.radian.jpaTutorial.JpaTuts.entity.Professor;
import com.radian.jpaTutorial.JpaTuts.entity.Student;
import com.radian.jpaTutorial.JpaTuts.entity.Subject;
import com.radian.jpaTutorial.JpaTuts.repository.ProfessorRepo;
import com.radian.jpaTutorial.JpaTuts.repository.StudentRepo;
import com.radian.jpaTutorial.JpaTuts.repository.SubjectRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfessorService {

    private final ProfessorRepo professorRepo;
    private final SubjectRepo subjectRepo;
    private final StudentRepo studentRepo;
    private final ModelMapper modelMapper;

    public ProfessorService(ProfessorRepo professorRepo,
                            SubjectRepo subjectRepo,
                            StudentRepo studentRepo,
                            ModelMapper modelMapper) {
        this.professorRepo = professorRepo;
        this.subjectRepo = subjectRepo;
        this.studentRepo = studentRepo;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public ProfessorDto addProfessor(ProfessorDto professorDto) {
        // Create professor entity
        Professor professor = new Professor();
        professor.setTitle(professorDto.getTitle());

        // Set Subject (ManyToOne relationship)
        if (professorDto.getSubjectId() != null) {
            Subject subject = subjectRepo.findById(professorDto.getSubjectId())
                    .orElseThrow(() -> new RuntimeException("Subject not found with id: " + professorDto.getSubjectId()));
            professor.setSubject(subject);

            // Add professor to subject's professorList (inverse side synchronization)
            if (subject.getProfessorList() == null) {
                subject.setProfessorList(new ArrayList<>()); //we are doing this to avoid null pointer exception
            }
            if (!subject.getProfessorList().contains(professor)) {
                subject.getProfessorList().add(professor);
            }
            subjectRepo.save(subject);
        }

        // Set Students (ManyToMany relationship)
        if (professorDto.getStudentIds() != null && !professorDto.getStudentIds().isEmpty()) {
            List<Student> students = studentRepo.findAllById(professorDto.getStudentIds());

            // Initialize student list if null
            if (professor.getStudentList() == null) {
                professor.setStudentList(new ArrayList<>());
            }

            // Add professor to each student's professorList (bidirectional sync)
            for (Student student : students) {
                if (!professor.getStudentList().contains(student)) {
                    professor.getStudentList().add(student);
                }

                if (student.getProfessorList() == null) {
                    student.setProfessorList(new ArrayList<>());
                }
                if (!student.getProfessorList().contains(professor)) {
                    student.getProfessorList().add(professor);
                }
                studentRepo.save(student);
            }
        }

        // Save professor and return DTO
        Professor savedProfessor = professorRepo.save(professor);
        return modelMapper.map(savedProfessor, ProfessorDto.class);
    }

    @Transactional
    public ProfessorDto assignProfessorToSubject(Long professorId, Long subjectId){
        Professor professor= professorRepo.findById(professorId).orElseThrow(()-> new RuntimeException("Professor not found with id: " + professorId));
        Subject subject= subjectRepo.findById(subjectId).orElseThrow(()-> new RuntimeException("Subject not found with id: " + subjectId));
        professor.setSubject(subject);
        if (subject.getProfessorList() == null) {
            subject.setProfessorList(new ArrayList<>());
        }
        if(!subject.getProfessorList().contains(professor)){
            subject.getProfessorList().add(professor);
        }
        subjectRepo.save(subject);
        Professor savedProfessor= professorRepo.save(professor);
        return modelMapper.map(savedProfessor, ProfessorDto.class);
    }


    public List<ProfessorDto> getProfessorBySubjectId(Long subjectId){
        List<Professor> professor= professorRepo.findBySubjectId(subjectId);
        List<ProfessorDto>professorDto=new ArrayList<>();
        for(Professor p: professor){
            professorDto.add(modelMapper.map(p, ProfessorDto.class));
        }
        return professorDto;

    }



}


