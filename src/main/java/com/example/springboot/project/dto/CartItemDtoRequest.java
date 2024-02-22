package com.example.springboot.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDtoRequest {
    private Long cartItemId;
    private ProductDtoWithIdOnly product;
    private int quantity;
}

