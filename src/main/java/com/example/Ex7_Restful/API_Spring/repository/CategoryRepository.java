package com.example.Ex7_Restful.API_Spring.repository;

import com.example.Ex7_Restful.API_Spring.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByName(String name);
}