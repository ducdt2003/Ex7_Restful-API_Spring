package com.example.Ex7_Restful.API_Spring.mapper;


import com.example.Ex7_Restful.API_Spring.dto.response.CategoryResponse;
import com.example.Ex7_Restful.API_Spring.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryResponse toResponse(Category c) {
        return new CategoryResponse(
                c.getId(),
                c.getName()
        );
    }
}