package com.example.Ex7_Restful.API_Spring.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {

    @NotBlank(message = "Tên không được để trống")
    private String name;

    @NotNull(message = "Giá không được null")
    @Min(value = 0, message = "Giá phải >= 0")
    private Double price;

    @NotNull(message = "CategoryId không được null")
    private Long categoryId;
}