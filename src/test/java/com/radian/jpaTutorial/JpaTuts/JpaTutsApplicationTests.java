package com.radian.jpaTutorial.JpaTuts;

import com.radian.jpaTutorial.JpaTuts.entity.Product;
import com.radian.jpaTutorial.JpaTuts.repository.ProductRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class JpaTutsApplicationTests {
    @Autowired
    private ProductRepo productRepo;

	@Test
	void contextLoads() {
	}

    @Test
    void testRepository() {
        // You can write tests for your repository here
        Product product = Product.builder()
                .sku("SKU123")
                .title("Sample Product")
                .price(BigDecimal.valueOf(19.99))
                .quantity(100)
                .build();
        System.out.println(productRepo.save(product));
    }

    @Test
    void getRepository() {
        System.out.println(productRepo.findAll());
    }




}
