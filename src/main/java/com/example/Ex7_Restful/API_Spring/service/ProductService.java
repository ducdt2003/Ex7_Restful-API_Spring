package com.example.Ex7_Restful.API_Spring.service;

import com.example.Ex7_Restful.API_Spring.dto.request.ProductRequest;
import com.example.Ex7_Restful.API_Spring.dto.response.ProductResponse;
import org.springframework.data.domain.Page;

public interface ProductService {

    ProductResponse create(ProductRequest request);

    ProductResponse update(Long id, ProductRequest request);

    ProductResponse getById(Long id);

    Page<ProductResponse> search(String name, int page, int size);
}