package com.example.Ex7_Restful.API_Spring.service;


import com.example.Ex7_Restful.API_Spring.dto.request.CategoryRequest;
import com.example.Ex7_Restful.API_Spring.entity.Category;

import java.util.List;

public interface CategoryService {

    Category create(CategoryRequest req);

    void delete(Long id);

    List<Category> getAll();

    Category getById(Long id);
}