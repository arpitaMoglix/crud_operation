package com.example.springboot.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDtoWithoutCategory {
    private Long productId;
    private String productName;
    private Double productPrice;
    private String productDescription;
}
