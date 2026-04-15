package com.example.Ex7_Restful.API_Spring.mapper;

import com.example.Ex7_Restful.API_Spring.dto.response.ProductResponse;
import com.example.Ex7_Restful.API_Spring.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductResponse toResponse(Product p) {
        return new ProductResponse(
                p.getId(),
                p.getName(),
//                p.getPrice(),
                p.getCategory().getName()
        );
    }
}