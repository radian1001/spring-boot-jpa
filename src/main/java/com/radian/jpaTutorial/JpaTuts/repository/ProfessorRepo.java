package com.radian.jpaTutorial.JpaTuts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepo extends JpaRepository<com.radian.jpaTutorial.JpaTuts.entity.Professor, Long> {
}
