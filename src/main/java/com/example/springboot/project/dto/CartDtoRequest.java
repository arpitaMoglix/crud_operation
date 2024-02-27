package com.example.springboot.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDtoRequest {
    private Long Id;
    private List<ProductDtoWithIdOnly> products;
    private Integer productQuantityInCart;
    private Date createdAt;
    private Date updatedAt;
}

