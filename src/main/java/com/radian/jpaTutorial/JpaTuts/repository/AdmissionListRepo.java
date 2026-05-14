package com.radian.jpaTutorial.JpaTuts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdmissionListRepo extends JpaRepository<com.radian.jpaTutorial.JpaTuts.entity.AdmissionList, Long> {
}
