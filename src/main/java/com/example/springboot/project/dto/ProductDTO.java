package com.example.springboot.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Long productId;
    private String productName;
    private Double productPrice;
    private String productDescription;
    private Date createdAt;
    private Date updatedAt;
    private Long categoryId;
    private String categoryName;


}
