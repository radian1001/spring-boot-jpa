package com.radian.jpaTutorial.JpaTuts.repository;
import com.radian.jpaTutorial.JpaTuts.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    List<Product> findByTitleOrderByPrice(String title);

    List<Product> findByOrderById();

    Page<Product> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    // This JPQL query selects only title and sku instead of loading the full Product entity.
    // The aliases must match the projection getter names:
    // - title -> getTitle()
    // - sku   -> getSku()
    @Query("SELECT p.title AS title, p.sku AS sku FROM Product p WHERE p.price > :price")
    List<ProjectionClass> findByPriceGreaterThan(BigDecimal price);


}
