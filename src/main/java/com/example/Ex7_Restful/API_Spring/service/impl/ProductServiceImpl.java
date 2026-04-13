package com.example.Ex7_Restful.API_Spring.service.impl;

import com.example.Ex7_Restful.API_Spring.Exception.NotFoundException;
import com.example.Ex7_Restful.API_Spring.dto.request.ProductRequest;
import com.example.Ex7_Restful.API_Spring.dto.response.ProductResponse;
import com.example.Ex7_Restful.API_Spring.entity.Category;
import com.example.Ex7_Restful.API_Spring.entity.Product;
import com.example.Ex7_Restful.API_Spring.mapper.ProductMapper;
import com.example.Ex7_Restful.API_Spring.repository.CategoryRepository;
import com.example.Ex7_Restful.API_Spring.repository.ProductRepository;
import com.example.Ex7_Restful.API_Spring.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepo;
    private final CategoryRepository categoryRepo;
    private final ProductMapper mapper;

    @Override
    public ProductResponse create(ProductRequest request) {

        Category c = categoryRepo.findById(request.getCategoryId())
                .orElseThrow(() -> new NotFoundException("Category không tồn tại"));

        Product p = new Product();
        p.setName(request.getName());
        p.setPrice(request.getPrice());
        p.setCategory(c);

        return mapper.toResponse(productRepo.save(p));
    }

    @Override
    public ProductResponse update(Long id, ProductRequest request) {

        Product p = productRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Product không tồn tại"));

        Category c = categoryRepo.findById(request.getCategoryId())
                .orElseThrow(() -> new NotFoundException("Category không tồn tại"));

        p.setName(request.getName());
        p.setPrice(request.getPrice());
        p.setCategory(c);

        return mapper.toResponse(productRepo.save(p));
    }

    @Override
    public ProductResponse getById(Long id) {
        return mapper.toResponse(
                productRepo.findById(id)
                        .orElseThrow(() -> new NotFoundException("Product không tồn tại"))
        );
    }

    @Override
    public Page<ProductResponse> search(String name, int page, int size) {

        Pageable pageable = PageRequest.of(page, Math.min(size, 10));

        return productRepo.findByNameContaining(name, pageable)
                .map(mapper::toResponse);
    }
}