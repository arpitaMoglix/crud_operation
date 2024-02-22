package com.example.springboot.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDtoResponse {
    private Long cartItemId;
    private ProductDTO product;
    private int quantity;
}
