package com.radian.jpaTutorial.JpaTuts.repository;
import com.radian.jpaTutorial.JpaTuts.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    List<Product> findByTitleOrderByPrice(String title);

    List<Product> findByOrderById();

    Page<Product> findByTitleContainingIgnoreCase(String title, Pageable pageable);
}
