package com.radian.jpaTutorial.JpaTuts.controller;

import com.radian.jpaTutorial.JpaTuts.dto.ProfessorDto;
import com.radian.jpaTutorial.JpaTuts.dto.StudentDto;
import com.radian.jpaTutorial.JpaTuts.entity.Professor;
import com.radian.jpaTutorial.JpaTuts.service.ProfessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProfessorController {

    private final ProfessorService professorService;
    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @PostMapping("/addProfessor")
    public ResponseEntity<String> addProfessor(@RequestBody ProfessorDto professorDto){
       ProfessorDto professor = professorService.addProfessor(professorDto);
        return ResponseEntity.ok("Professor added successfully with ID: " + professor.getId());
    }

    @PostMapping("/api/professors/{professorId}/subjects/{subjectId}")
    public ResponseEntity<String> assignProfessorToSubject(@PathVariable Long professorId, @PathVariable Long subjectId) {
          ProfessorDto professorDto= professorService.assignProfessorToSubject(professorId, subjectId);
        return ResponseEntity.ok("Professor with ID: " + professorId + " assigned to Subject with ID: " + subjectId);
    }

    @GetMapping("/api/professors/by-subject/{subjectId}")
    public ResponseEntity<List<ProfessorDto>> getProfessorBySubjectId(@PathVariable Long subjectId) {
        List<ProfessorDto> professorDto = professorService.getProfessorBySubjectId(subjectId);
        return ResponseEntity.ok(professorDto);
    }

//    @GetMapping("/api/professors/top-experienced")
//    public ResponseEntity<List<ProfessorDto>> getTopExperiencedProfessors(
//            @RequestParam(defaultValue = "id") String sortBy) {
//        List<ProfessorDto> topProfessors = professorService.getTopExperiencedProfessors(sortBy);
//        return ResponseEntity.ok(topProfessors);
    }
}
