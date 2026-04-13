package com.example.Ex7_Restful.API_Spring.service.impl;

import com.example.Ex7_Restful.API_Spring.Exception.BadRequestException;
import com.example.Ex7_Restful.API_Spring.Exception.NotFoundException;
import com.example.Ex7_Restful.API_Spring.dto.request.CategoryRequest;
import com.example.Ex7_Restful.API_Spring.entity.Category;
import com.example.Ex7_Restful.API_Spring.repository.CategoryRepository;
import com.example.Ex7_Restful.API_Spring.repository.ProductRepository;
import com.example.Ex7_Restful.API_Spring.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepo;
    private final ProductRepository productRepo;

    @Override
    @Transactional
    public Category create(CategoryRequest req) {

        if (categoryRepo.existsByName(req.getName())) {
            throw new BadRequestException("Tên danh mục đã tồn tại");
        }

        Category c = new Category();
        c.setName(req.getName());

        return categoryRepo.save(c);
    }

    @Override
    @Transactional
    public void delete(Long id) {

        if (!categoryRepo.existsById(id)) {
            throw new NotFoundException("Không tìm thấy danh mục");
        }

        productRepo.deleteByCategoryId(id);
        categoryRepo.deleteById(id);
    }

    @Override
    public List<Category> getAll() {
        return categoryRepo.findAll();
    }

    @Override
    public Category getById(Long id) {
        return categoryRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy danh mục"));
    }
}