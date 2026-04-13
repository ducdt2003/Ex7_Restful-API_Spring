package com.example.Ex7_Restful.API_Spring.controller;

import com.example.Ex7_Restful.API_Spring.dto.request.CategoryRequest;
import com.example.Ex7_Restful.API_Spring.dto.response.ApiResponse;
import com.example.Ex7_Restful.API_Spring.dto.response.CategoryResponse;
import com.example.Ex7_Restful.API_Spring.entity.Category;
import com.example.Ex7_Restful.API_Spring.mapper.CategoryMapper;
import com.example.Ex7_Restful.API_Spring.repository.CategoryRepository;
import com.example.Ex7_Restful.API_Spring.service.impl.CategoryServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryRepository repo;
    private final CategoryServiceImpl service;
    private final CategoryMapper mapper;

    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoryResponse>>> getAll() {

        List<CategoryResponse> data = service.getAll()
                .stream()
                .map(mapper::toResponse)
                .toList();

        return ResponseEntity.ok(
                new ApiResponse<>(200, "Lấy danh sách danh mục thành công", data)
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponse>> getById(@PathVariable Long id) {

        CategoryResponse data = mapper.toResponse(service.getById(id));

        return ResponseEntity.ok(
                new ApiResponse<>(200, "Lấy danh mục thành công", data)
        );
    }
    @PostMapping
    public ResponseEntity<ApiResponse<CategoryResponse>> create(@Valid @RequestBody CategoryRequest req) {

        CategoryResponse data = mapper.toResponse(service.create(req));

        return ResponseEntity.status(201)
                .body(new ApiResponse<>(201, "Tạo danh mục thành công", data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> delete(@PathVariable Long id) {

        service.delete(id);

        ApiResponse<Object> response = new ApiResponse<>(
                200,
                "Xóa danh mục thành công",
                null
        );

        return ResponseEntity.ok(response);
    }
}