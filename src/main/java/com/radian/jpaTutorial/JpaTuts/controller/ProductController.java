package com.radian.jpaTutorial.JpaTuts.controller;

import com.radian.jpaTutorial.JpaTuts.entity.Product;
import com.radian.jpaTutorial.JpaTuts.repository.ProductRepo;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepo productRepo;

    public ProductController(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    // GET /products/by-title?title=Mazza
    @GetMapping("/by-title")
    public List<Product> getProductsByTitle(@RequestParam(defaultValue = "Mazza") String title) {
        return productRepo.findByTitleOrderByPrice(title); // keep if repo defines this
    }

    // GET /products/by-id
    @GetMapping("/by-id")
    public List<Product> getAllProductsOrderedById() {
        // 1. Single-field sorting with the default direction ASC
        return productRepo.findAll(Sort.by("id"));
    }

    // GET /products/sorted?sortBy=id&direction=DESC
    @GetMapping("/sorted")
    public List<Product> getAllProductsSorted(
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "DESC") Sort.Direction direction) {
        // This endpoint demonstrates multi-field sorting in one method.
        //
        // 1. Dynamic single-field sorting:
        //    Sort.by(direction, sortBy)
        //
        // 2. Multi-field sorting with chaining:
        //    Sort.by(direction, sortBy).and(Sort.by(direction, "price"))
        //
        // 3. Multi-field sorting with Sort.Order:
        //    Sort.by(
        //            new Sort.Order(direction, sortBy),
        //            new Sort.Order(direction, "price")
        //    )
        //
        // Both multi-field styles above produce the same SQL ordering.
        // The Sort.Order style is often easier to extend when you want explicit
        // control over each field.
        //
        // Important:
        // - sortBy must exactly match a Product field name.
        // - Valid fields here are: id, sku, title, price, quantity, createdAt, updatedAt
        // - findAll accepts one Sort object only.
        // - If you need multiple columns, combine them into a single Sort.
        Sort sort = Sort.by(
                new Sort.Order(direction, sortBy),
                new Sort.Order(direction, "price")
        );

        return productRepo.findAll(sort);
    }


    // Pagination
    // GET /products/paginated?page=0&size=5
    @GetMapping("/paginated")
    public List<Product> getPaginatedProducts(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "5") Integer pageSize) {
        // PageRequest uses zero-based page indexes: page 0 is the first page.
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return productRepo.findAll(pageable).getContent();
    }

    // Pagination + sorting + title filter
    // GET /products/paginated-sorted?title=ma&pageNumber=0&pageSize=5&sortBy=price&direction=ASC
    @GetMapping("/paginated-sorted")
    public List<Product> getPaginatedSortedProducts(
            @RequestParam(defaultValue = "") String title,
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "5") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") Sort.Direction direction) {
        // PageRequest can carry both pagination and sorting together.
        // This is the standard Spring Data way to paginate sorted results.
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(direction, sortBy));

        // "ContainingIgnoreCase" means:
        // - "Containing"  -> SQL LIKE %title%
        // - "IgnoreCase"  -> case-insensitive comparison
        return productRepo.findByTitleContainingIgnoreCase(title, pageable).getContent();
    }


}
