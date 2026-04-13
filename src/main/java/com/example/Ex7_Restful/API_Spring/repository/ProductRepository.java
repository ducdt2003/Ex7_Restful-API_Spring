package com.example.Ex7_Restful.API_Spring.repository;

import com.example.Ex7_Restful.API_Spring.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findByNameContaining(String name, Pageable pageable);

    @Modifying
    @Query("DELETE FROM Product p WHERE p.category.id = :categoryId")
    void deleteByCategoryId(Long categoryId);
}