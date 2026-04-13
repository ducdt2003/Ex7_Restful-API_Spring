package com.example.Ex7_Restful.API_Spring.controller;

import com.example.Ex7_Restful.API_Spring.dto.request.ProductRequest;
import com.example.Ex7_Restful.API_Spring.dto.response.ApiResponse;
import com.example.Ex7_Restful.API_Spring.dto.response.ProductResponse;
import com.example.Ex7_Restful.API_Spring.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @PostMapping
    public ApiResponse<ProductResponse> create(@Valid @RequestBody ProductRequest req) {
        return new ApiResponse<>(
                201,
                "Tạo sản phẩm thành công",
                service.create(req)
        );
    }

    @PutMapping("/{id}")
    public ApiResponse<ProductResponse> update(@PathVariable Long id,
                                               @Valid @RequestBody ProductRequest req) {
        return new ApiResponse<>(
                200,
                "Cập nhật sản phẩm thành công",
                service.update(id, req)
        );
    }

    @GetMapping("/{id}")
    public ApiResponse<ProductResponse> get(@PathVariable Long id) {
        return new ApiResponse<>(
                200,
                "Lấy sản phẩm thành công",
                service.getById(id)
        );
    }
    @GetMapping
    public ApiResponse<Page<ProductResponse>> search(
            @RequestParam(defaultValue = "") String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return new ApiResponse<>(
                200,
                "Lấy danh sách sản phẩm thành công",
                service.search(name, page, size)
        );
    }
}