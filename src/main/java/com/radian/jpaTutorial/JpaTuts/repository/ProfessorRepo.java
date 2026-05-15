package com.radian.jpaTutorial.JpaTuts.repository;

import com.radian.jpaTutorial.JpaTuts.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessorRepo extends JpaRepository<com.radian.jpaTutorial.JpaTuts.entity.Professor, Long> {
    List<Professor> findBySubjectId(Long subjectId);
}
